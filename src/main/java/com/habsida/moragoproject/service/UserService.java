package com.habsida.moragoproject.service;

import com.habsida.moragoproject.configuration.utils.JwtUtil;
import com.habsida.moragoproject.exception.NotFoundByIdException;
import com.habsida.moragoproject.exception.UserAlreadyExistAuthenticationException;
import com.habsida.moragoproject.model.Profile;
import com.habsida.moragoproject.model.entity.*;
import com.habsida.moragoproject.model.enums.ERole;
import com.habsida.moragoproject.model.input.RefreshTokenResponse;
import com.habsida.moragoproject.model.input.UserInput;
import com.habsida.moragoproject.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
@Service
public class UserService {

    private UserRepository userRepository;
    private UserProfileService userProfileService;
    private TranslatorProfileService translatorProfileService;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;
    private RefreshTokenService refreshTokenService;
    private JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, UserProfileService userProfileService, TranslatorProfileService translatorProfileService, RoleService roleService, PasswordEncoder passwordEncoder, RefreshTokenService refreshTokenService, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.userProfileService = userProfileService;
        this.translatorProfileService = translatorProfileService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.refreshTokenService = refreshTokenService;
        this.jwtUtil = jwtUtil;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(Long id){
        return userRepository.findById(id)
                .orElseThrow(()->new NotFoundByIdException("User -> User doesn't find by Id " + id));
    }

    public List<User> findAll(int offset, int limit){
        if(offset < 0) offset = 0;
        if(limit < 0) limit = 5;
        Page<User> pages =userRepository.findAll(PageRequest.of(offset, limit));
        return pages.stream().collect(Collectors.toList());
    }

    public User findByPhone(String phone){
        return userRepository.findByPhone(phone)
                .orElseThrow(()->new NotFoundByIdException("User -> User doesn't find by phone " + phone));
    }

    public Boolean existsByPhone(String phone){
        return userRepository.existsByPhone(phone);
    }

    @Transactional
    public RefreshTokenResponse createUser(UserInput userInput){
        String userPhone = userInput.getPhone();
        userPhone = userPhone.replaceAll(" ","");
        userPhone = userPhone.replaceAll("-","");
        userPhone = userPhone.replaceAll("_","");
        userInput.setPhone(userPhone);
        if(userRepository.existsByPhone(userInput.getPhone())){
            throw new UserAlreadyExistAuthenticationException("Phone already exist");
        }

        User user = new User();
        if(!isNull(userInput.getFirstName()) && !userInput.getFirstName().isEmpty()){
            user.setFirstName(userInput.getFirstName());
        }
        if(!isNull(userInput.getLastName()) && !userInput.getLastName().isEmpty()){
            user.setLastName(userInput.getLastName());
        }
        if(!isNull(userInput.getPassword()) && !userInput.getPassword().isEmpty()){
            user.setPassword(passwordEncoder.encode(userInput.getPassword()));
        }
        if(!isNull(userInput.getPhone()) && !userInput.getPhone().isEmpty()){
            user.setPhone(userInput.getPhone());
        }
        user.setBalance(0d);
        user.setRatings(0d);
        user.setOnBoardingStatus(0);
        user.setTotalRatings(0);
        user.setIsActive(true);
        user.setIsDebtor(false);

        UserProfile userProfile = new UserProfile();
        userProfile.setIsFreeCallMade(false);
        userProfileService.createUserProfile(userProfile);

        user.setUserProfile(userProfile);

        Set<Role> rolestoBD = user.getRoles();
        rolestoBD.add(roleService.findByName(ERole.USER)
            .orElseThrow(()-> new NotFoundByIdException("User -> Role.USER doesn't exist")));
        user.setRoles(rolestoBD);

        userRepository.save(user);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userInput.getPhone());

        RefreshTokenResponse refreshTokenResponse = new RefreshTokenResponse();
        refreshTokenResponse.setRefreshToken(refreshToken.getToken());
        refreshTokenResponse.setAccessToken(jwtUtil.generateToken(userInput.getPhone(), true));


        return refreshTokenResponse;
    }

    public String deleteUserById(Long id){
        try{
            userRepository.deleteById(id);
        }catch (Exception e){
            throw  new NotFoundByIdException(e.getMessage());
        }
        return "User with Id "+id+" deleted";
    }

    public User updateUser(Long id, UserInput userInput){
        User user = userRepository.findById(id).get();
        if(!isNull(userInput.getLastName()) && !userInput.getLastName().isEmpty()){
            user.setLastName(userInput.getLastName());
        }
        if(!isNull(userInput.getApnToken()) && !userInput.getApnToken().isEmpty()){
            user.setApnToken(userInput.getApnToken());
        }
        if(!isNull(userInput.getFcmToken()) && !userInput.getFcmToken().isEmpty()){
            user.setFcmToken(userInput.getFcmToken());
        }
        if(!isNull(userInput.getPhone()) && !userInput.getPhone().isEmpty()){
            user.setPhone(userInput.getPhone());
        }
        return userRepository.save(user);
    }

    public RefreshTokenResponse createAdmin(UserInput userInput) {
        String userPhone = userInput.getPhone();
        userPhone = userPhone.replaceAll(" ","");
        userPhone = userPhone.replaceAll("-","");
        userPhone = userPhone.replaceAll("_","");
        userInput.setPhone(userPhone);
        if(userRepository.existsByPhone(userInput.getPhone())){
            throw new UserAlreadyExistAuthenticationException("Phone already exist");
        }
        User user = new User();
        if(!isNull(userInput.getFirstName()) && !userInput.getFirstName().isEmpty()){
            user.setFirstName(userInput.getFirstName());
        }
        if(!isNull(userInput.getLastName()) && !userInput.getLastName().isEmpty()){
            user.setLastName(userInput.getLastName());
        }
        user.setBalance(0d);
        user.setRatings(0d);
        user.setOnBoardingStatus(0);
        user.setTotalRatings(0);
        user.setIsActive(true);
        user.setIsDebtor(false);

        UserProfile userProfile= new UserProfile();
        userProfile.setIsFreeCallMade(false);
        userProfileService.createUserProfile(userProfile);

        user.setUserProfile(userProfile);
        user.setTranslatorProfile(null);

        Set<Role> rolestoBD = user.getRoles();
        rolestoBD.add(roleService.findByName(ERole.ADMIN)
                .orElseThrow(()-> new NotFoundByIdException("User -> Role.ADMIN doesn't exist")));
        user.setRoles(rolestoBD);

        userRepository.save(user);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userInput.getPhone());


        RefreshTokenResponse refreshTokenResponse = new RefreshTokenResponse();
        refreshTokenResponse.setRefreshToken(refreshToken.getToken());
        refreshTokenResponse.setAccessToken(jwtUtil.generateToken(userInput.getPhone(), true));


        return refreshTokenResponse;
    }

    public User currentUser() {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userRepository.findByPhone(currentPrincipalName).get();
        return user;
    }

    public void createUser(User user) {
        userRepository.save(user);

    }


    public Profile getProfile(User user) {
        Profile profile = new Profile();

        if(user.getUserProfile() != null) {
            profile.setIsFreeCallMade(user.getUserProfile().getIsFreeCallMade());
            profile.setSelfDescription("USER");
        }
        if (user.getTranslatorProfile() != null) {
            profile.setEmail(user.getTranslatorProfile().getEmail());
            profile.setLanguageList(user.getTranslatorProfile().getLanguageList());
            profile.setThemeList(user.getTranslatorProfile().getThemeList());
            profile.setDateOfBirth(user.getTranslatorProfile().getDateOfBirth());
            profile.setIsOnline(user.getTranslatorProfile().getIsOnline());
            profile.setIsAvailable(user.getTranslatorProfile().getIsAvailable());
            profile.setLevelOfKorean(user.getTranslatorProfile().getLevelOfKorean());
            profile.setSelfDescription("TRANSLATOR");
        }
        return profile;
    }

    public void deleteCurrentUser(){
        User user = currentUser();
        user.setIsDelete(true);
        user.setIsActive(false);
    }
}
