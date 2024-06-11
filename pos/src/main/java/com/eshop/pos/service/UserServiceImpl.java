package com.eshop.pos.service;

import com.eshop.pos.entity.User;
import com.eshop.pos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    //To establish a connectivity with persistence layer
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findUserById(Long id) {

        User existingUser=userRepository.findById(id).orElse(null);

        return existingUser;
    }

    @Override
    public User updateUser(Long id,User user) {

        User existingUser = userRepository.findById(id).orElse(null);

        if (existingUser != null) {
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());

            return userRepository.save(existingUser);
        } else {
            return null;
        }
    }


    @Override
    public String deleteUser(Long id) {

        Boolean isExisted=userRepository.existsById(id);

        if(isExisted){
            userRepository.deleteById(id);

            return "User deleted successfully";
        }else{
            return "No user found for entered ID";
        }
    }
}
