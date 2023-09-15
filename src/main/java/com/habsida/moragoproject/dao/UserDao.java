package com.habsida.moragoproject.dao;

import com.habsida.moragoproject.repository.UserRepository;
import com.habsida.moragoproject.model.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDao {

    UserRepository userRepository;

    public UserDao(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(Long id){
        return userRepository.findById(id).get();
    }

    public User addUser(User user){
        return userRepository.save(user);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public User editUser(User user){
        User userFromBd = userRepository.findById(user.getId()).get();
        if(!user.getFirstName().equals("EMPTY")){
            userFromBd.setFirstName(user.getFirstName());
        }
        if(!user.getLastName().equals("EMPTY")){
            userFromBd.setLastName(user.getLastName());
        }
        if(!user.getApnToken().equals("EMPTY")){
            userFromBd.setApnToken(user.getApnToken());
        }
        if(!user.getFcmToken().equals("EMPTY")){
            userFromBd.setFcmToken(user.getFcmToken());
        }
        if(!user.getPassword().equals("EMPTY")){
            userFromBd.setPassword(user.getPassword());
        }
        if(!user.getPhone().equals("EMPTY")){
            userFromBd.setPhone(user.getPhone());
        }
        if(user.getBalance() != 0 && user.getBalance() != userFromBd.getBalance()){
            userFromBd.setBalance(user.getBalance());
        }
        if(user.getRatings() != 0 && user.getRatings() != userFromBd.getRatings() ){
            userFromBd.setRatings(user.getRatings());
        }
        if(user.getIsActive() != userFromBd.getIsActive()){
            userFromBd.setIsActive(user.getIsActive());
        }
        if(user.getIsDebtor() != userFromBd.getIsDebtor()){
            userFromBd.setIsDebtor(user.getIsDebtor());
        }
        if(user.getOnBoardingStatus() != 0 && user.getOnBoardingStatus() != userFromBd.getOnBoardingStatus() ){
            userFromBd.setOnBoardingStatus(user.getOnBoardingStatus());
        }
        if(user.getTotalRatings() != 0 && user.getTotalRatings() != userFromBd.getTotalRatings()){
            userFromBd.setTotalRatings(user.getTotalRatings());
        }
        return userRepository.save(userFromBd);
    }
}
