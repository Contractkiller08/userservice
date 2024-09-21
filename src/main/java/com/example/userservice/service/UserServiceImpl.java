package com.example.userservice.service;

import com.example.userservice.dto.UserDTO;
import com.example.userservice.entity.User;
import com.example.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void registerUser(UserDTO userDTO){
        //check if user already exists
        Optional<User> existingUser = userRepository.findByUserName(userDTO.getUserName());

        if(existingUser.isPresent()){
            throw new RuntimeException("User Name already exists");
        }

        User user = new User(userDTO.getUserName(),
                            bCryptPasswordEncoder.encode(userDTO.getPassword()),
                            userDTO.getEmail());
        userRepository.save(user);
        }
    }
