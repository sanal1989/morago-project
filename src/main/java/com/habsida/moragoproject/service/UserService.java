package com.habsida.moragoproject.service;

import com.habsida.moragoproject.dao.UserDao;
import com.habsida.moragoproject.entity.User;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> findAll(){
        return userDao.findAll();
    }

    public User findById(Long id){
        return userDao.findById(id);
    }

    public User addUser(User user){
        return userDao.addUser(user);
    }

    public void deleteUser(Long id){
        userDao.deleteUser(id);
    }

    public User editUser(User user){
        return userDao.editUser(user);
    }
}
