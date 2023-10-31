package com.habsida.moragoproject.configuration.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.habsida.moragoproject.exception.JwtException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("#{new Long ('${jwt.expiration}')}")
    private Long expirationTime;

    @Value("${jwt.refreshsecret}")
    private String refreshSecret;

    private String refreshPasswordSecretKey = "fergereehegefsdfsdfsdsgdfgertert";

    @Value("#{new Long ('${jwt.jwtRefreshExpirationMs}')}")
    private Long refreshTokenDurationMs;


    public String generateToken(String phone, boolean isAccessToken) {
        return JWT.create()
                .withExpiresAt(new Date(System.currentTimeMillis()+ (isAccessToken? expirationTime : refreshTokenDurationMs)))
                .withClaim("phone", phone)
                .sign(Algorithm.HMAC512(isAccessToken ? secret : refreshSecret));
    }


    public String getPhoneFromToken(String token, boolean isAccessToken) {
        return  JWT.require(Algorithm.HMAC512(isAccessToken ? secret : refreshSecret)).build().verify(token)
                .getClaim("phone").asString();
    }

    public boolean  validateToken(String token, boolean isAccessToken) throws JwtException{
        try {
            JWT.require(Algorithm.HMAC512(isAccessToken ? secret : refreshSecret)).build().verify(token);
            return true;
        } catch (Exception ex) {
            throw new JwtException("jwt not valid");
        }
    }

    public String generateTokenResetPassword(String phone, String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        LocalDateTime localDateTime = LocalDateTime.parse(date.substring(0,23).replace("T", " "),formatter);

        return JWT.create()
                .withExpiresAt(java.util.Date
                        .from(localDateTime.atZone(ZoneId.systemDefault())
                                .toInstant()))
                .withClaim("phone", phone)
                .withClaim("type", "password_reset")
                .sign(Algorithm.HMAC512(refreshPasswordSecretKey));
    }

    public boolean  validateRefreshPasswordToken(String token) throws JwtException{
        try {
            JWT.require(Algorithm.HMAC512(refreshPasswordSecretKey)).build().verify(token);
            return true;
        } catch (Exception ex) {
            throw new JwtException("refreshPassword jwt not valid");
        }
    }

    public String getPhoneFromRefreshPasswordToken(String token) {
        return  JWT.require(Algorithm.HMAC512(refreshPasswordSecretKey)).build().verify(token)
                .getClaim("phone").asString();
    }
}
