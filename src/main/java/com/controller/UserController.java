package com.controller;

import com.entity.User;
import com.model.payload.UserDto;
import com.model.response.Response;
import com.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Response<User>>  addUser(@RequestBody UserDto userDto){
        return userService.addUser(userDto);
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<Response<User>> getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @GetMapping
    public ResponseEntity<Response<List<User>>> getAllUser(){
        return userService.getAllCategories();
    }

    @PutMapping("/editUserById/{id}")
    public ResponseEntity<Response<User>> editUserById(@PathVariable Long id, @RequestBody UserDto userDto){
        return userService.editUserById(id, userDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<User>> deleteUserById(@PathVariable Long id){
        return  userService.deleteUserById(id);
    }

}
