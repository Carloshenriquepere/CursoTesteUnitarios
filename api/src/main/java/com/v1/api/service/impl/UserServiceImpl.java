package com.v1.api.service.impl;

import com.v1.api.entitie.User;
import com.v1.api.repository.UserRepository;
import com.v1.api.service.UserService;
import com.v1.api.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User finById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(()-> new ObjectNotFoundException("User Not Found"));
    }
}
