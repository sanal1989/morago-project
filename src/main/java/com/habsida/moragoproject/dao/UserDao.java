package com.habsida.moragoproject.dao;

import com.habsida.moragoproject.dao.repository.UserRepository;
import com.habsida.moragoproject.entity.User;
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
        if(!user.getFirstName().equals(userFromBd.getFirstName()) && !user.getFirstName().isEmpty()){
            userFromBd.setFirstName(user.getFirstName());
        }
        if(!user.getLastName().equals(userFromBd.getLastName()) && !user.getLastName().isEmpty()){
            userFromBd.setLastName(user.getLastName());
        }
        if(!user.getApnToken().equals(userFromBd.getApnToken()) && !user.getApnToken().isEmpty()){
            userFromBd.setApnToken(user.getApnToken());
        }
        if(!user.getFcmToken().equals(userFromBd.getFcmToken()) && !user.getFcmToken().isEmpty()){
            userFromBd.setFcmToken(user.getFcmToken());
        }
        if(!user.getPassword().equals(userFromBd.getPassword()) && !user.getPassword().isEmpty()){
            userFromBd.setPassword(user.getPassword());
        }
        if(!user.getPhone().equals(userFromBd.getPhone()) && !user.getPhone().isEmpty()){
            userFromBd.setPhone(user.getPhone());
        }
        if(user.getBalance() != userFromBd.getBalance() && user.getBalance() != 0){
            userFromBd.setBalance(user.getBalance());
        }
        if(user.getRatings() != userFromBd.getRatings() && user.getRatings() != 0){
            userFromBd.setRatings(user.getRatings());
        }
        if(user.getActive() != userFromBd.getActive()){
            userFromBd.setActive(user.getActive());
        }
        if(user.getDebtor() != userFromBd.getDebtor()){
            userFromBd.setDebtor(user.getDebtor());
        }
        if(user.getOnBoardingStatus() != userFromBd.getOnBoardingStatus() && user.getOnBoardingStatus() != 0){
            userFromBd.setOnBoardingStatus(user.getOnBoardingStatus());
        }
        if(user.getTotalRatings() != userFromBd.getTotalRatings() && user.getTotalRatings() != 0){
            userFromBd.setTotalRatings(user.getTotalRatings());
        }
        return userRepository.save(userFromBd);
    }
}
