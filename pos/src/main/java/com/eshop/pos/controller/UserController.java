package com.eshop.pos.controller;

import com.eshop.pos.entity.User;
import com.eshop.pos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    //Establishing connectivity with Persistence and Business layers
    @Autowired
    private UserService userService;

    //getting all users
    @GetMapping("/users")
    public ResponseEntity<?> findAllUsers(){

        List<User> users=userService.findAllUsers();

        try{
            if(!users.isEmpty()){
                return ResponseEntity.status(HttpStatus.OK).body(users);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Users Found");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    //creating a new user
    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody User user){
        User newUser=userService.createUser(user);

        try {
            if (newUser!=null){
                return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
            }else{
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("A null user was returned , please recheck details or contact admin");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> findUserById(@PathVariable Long id){
        User user= userService.findUserById(id);

        try{
            if(user!=null){
                return ResponseEntity.status(HttpStatus.FOUND).body(user);
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No user found for the entered id");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    //updating existing user
    @PutMapping("/update/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id,@RequestBody User user){

        User updatedUser=userService.updateUser(id,user);

        try{
            if(updatedUser!=null){
                return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
            }else {
                return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("No user found for entered id");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    //delete a customer
    @DeleteMapping("/delete/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUser(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
