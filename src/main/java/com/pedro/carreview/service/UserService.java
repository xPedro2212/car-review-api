package com.pedro.carreview.service;

import com.pedro.carreview.model.User;
import com.pedro.carreview.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    private final PasswordEncoder passwordEncoder;


}
