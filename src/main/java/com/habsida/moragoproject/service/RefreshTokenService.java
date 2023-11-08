package com.habsida.moragoproject.service;

import com.habsida.moragoproject.exception.JwtException;
import com.habsida.moragoproject.exception.NotFoundByIdException;
import com.habsida.moragoproject.model.entity.RefreshToken;
import com.habsida.moragoproject.model.entity.User;
import com.habsida.moragoproject.repository.RefreshTokenRepository;
import com.habsida.moragoproject.repository.UserRepository;
import com.habsida.moragoproject.configuration.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class RefreshTokenService {

    RefreshTokenRepository refreshTokenRepository;

    UserService userService;

    private JwtUtil jwtUtil;

    @Value("#{new Long ('${jwt.jwtRefreshExpirationMs}')}")
    private Long refreshTokenDurationMs;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository,
                               @Lazy UserService userService,
                               JwtUtil jwtUtil) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    public RefreshToken createRefreshToken(String phone) {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUser(userService.findByPhone(phone));
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(jwtUtil.generateToken(phone, false));

        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().isBefore(Instant.now())) {
            refreshTokenRepository.delete(token);
            throw new JwtException(token.getToken() + " Refresh token was expired. Please make a new sign in request");
        }
        return token;
    }

    public RefreshToken findByPhone(String phone) {
        User user = userService.findByPhone(phone);
        return refreshTokenRepository.findByUser(user)
                .orElseThrow(()->new NotFoundByIdException("RefreshToken->RefreshToken doesn't find by Name"));
    }

    public RefreshToken updateRefreshToken(String token){
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(()->new NotFoundByIdException("Refresh token is not in database!"));
        this.verifyExpiration(refreshToken);
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(jwtUtil.generateToken(refreshToken.getUser().getPhone(), false));
        return refreshTokenRepository.save(refreshToken);
    }
}
