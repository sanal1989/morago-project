package com.habsida.moragoproject.service;

import com.habsida.moragoproject.exception.NotFoundById;
import com.habsida.moragoproject.model.entity.RefreshToken;
import com.habsida.moragoproject.model.entity.User;
import com.habsida.moragoproject.repository.RefreshTokenRepository;
import com.habsida.moragoproject.repository.UserRepository;
import com.habsida.moragoproject.configuration.security.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class RefreshTokenService {

    RefreshTokenRepository refreshTokenRepository;

    UserRepository userRepository;

    private JwtUtil jwtUtil;

    @Value("#{new Long ('${jwt.jwtRefreshExpirationMs}')}")
    private Long refreshTokenDurationMs;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, UserRepository userRepository, JwtUtil jwtUtil) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public RefreshToken createRefreshToken(String phone) {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUser(userRepository.findByPhone(phone).get());
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(jwtUtil.generateToken(phone, false));

        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new RuntimeException(token.getToken() + " Refresh token was expired. Please make a new signin request");
        }
        return token;
    }

    public RefreshToken findByPhone(String phone) {
        User user = userRepository.findByPhone(phone).get();
        return refreshTokenRepository.findByUser(user)
                .orElseThrow(()->new NotFoundById("RefreshToken->RefreshToken doesn't find by Name"));
    }

    public RefreshToken updateRefreshToken(String token){
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(()->new NotFoundById("Refresh token is not in database!"));
        this.verifyExpiration(refreshToken);
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(jwtUtil.generateToken(refreshToken.getUser().getPhone(), false));
        return refreshTokenRepository.save(refreshToken);
    }
}
