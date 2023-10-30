package com.habsida.moragoproject.model;

import com.habsida.moragoproject.model.entity.RefreshToken;
import com.habsida.moragoproject.model.entity.Role;
import com.habsida.moragoproject.model.entity.UserProfile;
import graphql.schema.DataFetcher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.graphql.data.query.QuerydslDataFetcher;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrentUser {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String apnToken;
    private Double balance;
    private String fcmToken;
    private String firstName;
    private Boolean isActive;
    private Boolean isDebtor;
    private String lastName;
    private Integer onBoardingStatus;
    private String password;
    private String phone;
    private Double ratings;
    private Integer totalRatings;
    private String profile;
    private Set<Role> roles;
    private RefreshToken refreshToken;


}
