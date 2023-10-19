package com.habsida.moragoproject.service;

import com.habsida.moragoproject.configuration.security.JwtUtil;
import com.habsida.moragoproject.exception.NotFoundByIdException;
import com.habsida.moragoproject.exception.UserAlreadyExistAuthenticationException;
import com.habsida.moragoproject.exception.ValidationPhoneException;
import com.habsida.moragoproject.model.entity.RefreshToken;
import com.habsida.moragoproject.model.entity.Role;
import com.habsida.moragoproject.model.entity.User;
import com.habsida.moragoproject.model.entity.UserProfile;
import com.habsida.moragoproject.model.enums.ERole;
import com.habsida.moragoproject.model.input.RefreshTokenResponse;
import com.habsida.moragoproject.model.input.UserInput;
import com.habsida.moragoproject.repository.RoleRepository;
import com.habsida.moragoproject.repository.TranslatorProfileRepository;
import com.habsida.moragoproject.repository.UserProfileRepository;
import com.habsida.moragoproject.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Set;

import static java.util.Objects.isNull;

@Service
public class UserService {

    private UserRepository userRepository;
    private UserProfileRepository userProfileRepository;
    private TranslatorProfileRepository translatorProfileRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private RefreshTokenService refreshTokenService;
    private JwtUtil jwtUtil;

    public UserService(UserRepository userRepository,
                       UserProfileRepository userProfileRepository,
                       TranslatorProfileRepository translatorProfileRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder,
                       RefreshTokenService refreshTokenService,
                       JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.userProfileRepository = userProfileRepository;
        this.translatorProfileRepository = translatorProfileRepository;
        this.roleRepository = roleRepository;
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

    @Transactional
    public RefreshTokenResponse createUser(UserInput userInput){
        if(userRepository.existsByPhone(userInput.getPhone())){
            throw new UserAlreadyExistAuthenticationException("Phone already exist");
        }
        if(!userInput.getPhone().matches("^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$")){
            throw new ValidationPhoneException("phone number doesn't valid");
        }
        User user = new User();
        if(!isNull(userInput.getFirstName()) && !userInput.getFirstName().isEmpty()){
            user.setFirstName(userInput.getFirstName());
        }
        if(!isNull(userInput.getLastName()) && !userInput.getLastName().isEmpty()){
            user.setLastName(userInput.getLastName());
        }
        if(!isNull(userInput.getApnToken()) && !userInput.getApnToken().isEmpty()){
            user.setApnToken(userInput.getApnToken());
        }
        if(!isNull(userInput.getFcmToken()) && !userInput.getFcmToken().isEmpty()){
            user.setFcmToken(userInput.getFcmToken());
        }
        if(!isNull(userInput.getPassword()) && !userInput.getPassword().isEmpty()){
            user.setPassword(passwordEncoder.encode(userInput.getPassword()));
        }
        if(!isNull(userInput.getPhone()) && !userInput.getPhone().isEmpty()){
            user.setPhone(userInput.getPhone());
        }
        if(!isNull(userInput.getBalance())){
            user.setBalance(userInput.getBalance());
        }else {
            user.setBalance(0d);
        }
        if(!isNull(userInput.getRatings())){
            user.setRatings(userInput.getRatings());
        }else {
            user.setRatings(0d);
        }
        if(!isNull(userInput.getOnBoardingStatus())){
            user.setOnBoardingStatus(userInput.getOnBoardingStatus());
        }else {
            user.setOnBoardingStatus(0);
        }
        if(!isNull(userInput.getTotalRatings())){
            user.setTotalRatings(userInput.getTotalRatings());
        }else {
            user.setTotalRatings(0);
        }

        UserProfile userProfile= new UserProfile();
        userProfile.setIsFreeCallMade(false);
        userProfileRepository.save(userProfile);

        user.setIsActive(true);
        user.setIsDebtor(false);
        user.setUserProfile(userProfile);
        user.setTranslatorProfile(null);

        Set<Role> rolestoBD = user.getRoles();
        rolestoBD.add(roleRepository.findByName(ERole.USER)
            .orElseThrow(()-> new NotFoundByIdException("User -> Role.USER doesn't exist")));
        user.setRoles(rolestoBD);

        //
        User userDB = userRepository.save(user);
        //

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userInput.getPhone());
        userDB.setRefreshToken(refreshToken);

        RefreshTokenResponse refreshTokenResponse = new RefreshTokenResponse();
        refreshTokenResponse.setRefreshToken(refreshToken.getToken());
        refreshTokenResponse.setAccessToken(jwtUtil.generateToken(userInput.getPhone(), true));
        userRepository.save(userDB);

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
        if(!isNull(userInput.getBalance())){
            user.setBalance(userInput.getBalance());
        }else {
            user.setBalance(0d);
        }
        if(!isNull(userInput.getRatings())){
            user.setRatings(userInput.getRatings());
        }else {
            user.setRatings(0d);
        }
        if(!isNull(userInput.getIsActive())){
            user.setIsActive(userInput.getIsActive());
        }else {
            user.setIsActive(true);
        }
        if(!isNull(userInput.getIsDebtor())){
            user.setIsDebtor(userInput.getIsDebtor());
        }else {
            user.setIsDebtor(false);
        }
        if(!isNull(userInput.getOnBoardingStatus())){
            user.setOnBoardingStatus(userInput.getOnBoardingStatus());
        }else {
            user.setOnBoardingStatus(0);
        }
        if(!isNull(userInput.getTotalRatings())){
            user.setTotalRatings(userInput.getTotalRatings());
        }else {
            user.setTotalRatings(0);
        }
        return userRepository.save(user);
    }

    public RefreshTokenResponse createAdmin(UserInput userInput) {
        if(userRepository.existsByPhone(userInput.getPhone())){
            throw new UserAlreadyExistAuthenticationException("Phone already exist");
        }
        if(!userInput.getPhone().matches("^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$")){
            throw new ValidationPhoneException("phone number doesn't valid");
        }
        User user = new User();
        if(!isNull(userInput.getFirstName()) && !userInput.getFirstName().isEmpty()){
            user.setFirstName(userInput.getFirstName());
        }
        if(!isNull(userInput.getLastName()) && !userInput.getLastName().isEmpty()){
            user.setLastName(userInput.getLastName());
        }
        if(!isNull(userInput.getApnToken()) && !userInput.getApnToken().isEmpty()){
            user.setApnToken(userInput.getApnToken());
        }
        if(!isNull(userInput.getFcmToken()) && !userInput.getFcmToken().isEmpty()){
            user.setFcmToken(userInput.getFcmToken());
        }
        if(!isNull(userInput.getPassword()) && !userInput.getPassword().isEmpty()){
            user.setPassword(passwordEncoder.encode(userInput.getPassword()));
        }
        if(!isNull(userInput.getPhone()) && !userInput.getPhone().isEmpty()){
            user.setPhone(userInput.getPhone());
        }
        if(!isNull(userInput.getBalance())){
            user.setBalance(userInput.getBalance());
        }else {
            user.setBalance(0d);
        }
        if(!isNull(userInput.getRatings())){
            user.setRatings(userInput.getRatings());
        }else {
            user.setRatings(0d);
        }
        if(!isNull(userInput.getOnBoardingStatus())){
            user.setOnBoardingStatus(userInput.getOnBoardingStatus());
        }else {
            user.setOnBoardingStatus(0);
        }
        if(!isNull(userInput.getTotalRatings())){
            user.setTotalRatings(userInput.getTotalRatings());
        }else {
            user.setTotalRatings(0);
        }

        UserProfile userProfile= new UserProfile();
        userProfile.setIsFreeCallMade(false);
        userProfileRepository.save(userProfile);

        user.setIsActive(true);
        user.setIsDebtor(false);
        user.setUserProfile(userProfile);
        user.setTranslatorProfile(null);

        Set<Role> rolestoBD = user.getRoles();
        rolestoBD.add(roleRepository.findByName(ERole.ADMIN)
                .orElseThrow(()-> new NotFoundByIdException("User -> Role.ADMIN doesn't exist")));
        user.setRoles(rolestoBD);

        User userDB = userRepository.save(user);

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userInput.getPhone());
        RefreshTokenResponse refreshTokenResponse = new RefreshTokenResponse();
        refreshTokenResponse.setRefreshToken(refreshToken.getToken());
        refreshTokenResponse.setAccessToken(jwtUtil.generateToken(userInput.getPhone(), true));
        userDB.setRefreshToken(refreshToken);
        userRepository.save(userDB);
        return refreshTokenResponse;
    }
}
