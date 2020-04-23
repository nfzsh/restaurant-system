package com.zsh.restaurantsystem.service;

import com.zsh.restaurantsystem.entity.User;
import com.zsh.restaurantsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void setUser(User user) {
        userRepository.save(user);
    }

    public User getUserByOpenid(int openid) {
        return userRepository.getUserByOpenid(openid);
    }

    //支持模糊搜索
    public List<User> getUserByName(String name) {
        return userRepository.getUserByName(name);
    }

    //支持模糊搜索
    public List<User> getUserByPh(String num) {
        return userRepository.getUser(num);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }
    public void deleteUser(int id){
        userRepository.deleteById(id);
    }
}
