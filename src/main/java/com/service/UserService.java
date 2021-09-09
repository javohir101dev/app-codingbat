package com.service;

import com.entity.Category;
import com.entity.Language;
import com.entity.User;
import com.model.enums.Badge;
import com.model.payload.UserDto;
import com.model.response.Response;
import com.repo.UserRepo;
import com.sun.deploy.net.HttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    public ResponseEntity<Response<User>> addUser(UserDto userDto){
        if (userRepo.existsByEmail(userDto.getEmail())){
            return new ResponseEntity<>(new Response<>("This email " +userDto.getEmail() +  " is already registred"), HttpStatus.CONFLICT);
        }

        User user = new User();
       user.setEmail(userDto.getEmail());
       user.setPassword(userDto.getPassword());

        User savedUser = userRepo.save(user);
        return ResponseEntity.ok(new Response<>(savedUser));
    }

    public ResponseEntity<Response<User>> getUserById(Long id){
        Optional<User> optionalUser = userRepo.findById(id);
        if (!optionalUser.isPresent()){
            return new ResponseEntity<>(new Response<>("User is not found"), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(new Response<>(optionalUser.get()));
    }

    public ResponseEntity<Response<List<User>>> getAllCategories(){
        return ResponseEntity.ok(new Response<>(userRepo.findAll()));
    }

    public ResponseEntity<Response<User>> editUserById(Long id, UserDto userDto){
        Optional<User> optionalUser = userRepo.findById(id);
        if (!optionalUser.isPresent()){
            return new ResponseEntity<>(new Response<>("User is not found"), HttpStatus.NOT_FOUND);
        }

        if (userRepo.existsByEmailAndIdNot(userDto.getEmail(), id)){
            return new ResponseEntity<>(new Response<>("This email " +userDto.getEmail() +  " is already registred"), HttpStatus.CONFLICT);
        }

        User user = optionalUser.get();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        User savedUser = userRepo.save(user);
        return ResponseEntity.ok(new Response<>(savedUser));
    }


    public ResponseEntity<Response<User>> deleteUserById(Long id){
        Optional<User> optionalUser = userRepo.findById(id);
        if (!optionalUser.isPresent()){
            return new ResponseEntity<>(new Response<>("User is not found"), HttpStatus.NOT_FOUND);
        }

        User user = optionalUser.get();
        userRepo.deleteById(id);
        return ResponseEntity.ok(new Response<>(user));
    }



}
