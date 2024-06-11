package com.eshop.pos.service;

import com.eshop.pos.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    //To get all users in to a list
    List<User> findAllUsers();

    //To create a user
    User createUser(User user);

    //Get a user for a givenId
    User findUserById(Long id);

    //Update a user's details
    User updateUser(Long id,User user);

    //Delete a user
    String deleteUser(Long id);
}
