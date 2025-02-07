package com.v1.api.service.impl;

import com.v1.api.entitie.User;
import com.v1.api.entitie.dto.UserDTO;
import com.v1.api.repository.UserRepository;
import com.v1.api.service.UserService;
import com.v1.api.service.exceptions.DataIntegratyViolationException;
import com.v1.api.service.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public User findById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(()-> new ObjectNotFoundException("User Not Found"));
    }

    @Override
    public List<User> finAll() {
        return userRepository.findAll();
    }

    @Override
    public User created(UserDTO users) {
        findByEmail(users);
        return userRepository.save(modelMapper.map(users, User.class));
    }

    @Override
    public User update(UserDTO userDTO) {
        findByEmail(userDTO);
        return userRepository.save(modelMapper.map(userDTO, User.class));
    }

    @Override
    public void delete(Integer id) {
        findById(id);
        userRepository.deleteById(id);
    }

    private void findByEmail(UserDTO email) {
        Optional<User> user = userRepository.findByEmail(email.getEmail());

        if (user.isPresent() && !user.get().getId().equals(email.getId())) {
            throw  new DataIntegratyViolationException("Email Already Exists");
        }
    }


}
