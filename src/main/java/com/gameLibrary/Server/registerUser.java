package com.gameLibrary.Server;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class registerUser {
//
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public registerUser(PasswordEncoder passwordEncoder, UserRepository userRepository){
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }


    public void register(String username, String password){
        if(userRepository.existsByUsername(username)){
            throw new RuntimeException("Duplicate username detected.");
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));

        userRepository.save(user);
    }
}
