package com.habsida.moragoproject.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
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

    public String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationTime);
        SecretKey secretKey64 = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(secretKey64)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        SecretKey secretKey64 = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        return Jwts.parserBuilder().setSigningKey(secretKey64).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean  validateToken(String token) {
        try {
            SecretKey secretKey64 = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
            Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(secretKey64).build().parseClaimsJws(token);
            return !isTokenExpired(token);
        } catch (Exception ex) {
            System.out.println("Token validation failed");
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        SecretKey secretKey64 = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        return Jwts.parserBuilder().setSigningKey(secretKey64).build().parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }
}
