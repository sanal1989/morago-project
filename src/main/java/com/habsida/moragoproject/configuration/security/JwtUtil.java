package com.habsida.moragoproject.configuration.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("#{new Long ('${jwt.expiration}')}")
    private Long expirationTime;

    @Value("${jwt.refreshsecret}")
    private String refreshSecret;

    @Value("#{new Long ('${jwt.jwtRefreshExpirationMs}')}")
    private Long refreshTokenDurationMs;

    public String generateToken(String username, boolean isAccessToken) {
        return JWT.create()
                .withExpiresAt(new Date(System.currentTimeMillis()+ expirationTime))
                .withClaim("username", username)
                .sign(Algorithm.HMAC512(isAccessToken ? secret : refreshSecret));
    }

    public String generateRefreshToken(String username, boolean isAccessToken) {
        return JWT.create()
                .withExpiresAt(new Date(System.currentTimeMillis()+ refreshTokenDurationMs))
                .withClaim("username", username)
                .sign(Algorithm.HMAC512(isAccessToken ? secret : refreshSecret));
    }

    public String getUsernameFromToken(String token, boolean isAccessToken) {
        return  JWT.require(Algorithm.HMAC512(isAccessToken ? secret : refreshSecret)).build().verify(token)
                .getClaim("username").asString();
    }

    public boolean  validateToken(String token, boolean isAccessToken) {
        try {
            JWT.require(Algorithm.HMAC512(isAccessToken ? secret : refreshSecret)).build().verify(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

}
