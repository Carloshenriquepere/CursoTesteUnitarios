package com.v1.api.controller;

import com.v1.api.entitie.User;
import com.v1.api.entitie.dto.UserDTO;
import com.v1.api.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;


    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> FindById(@PathVariable Integer id){
        return ResponseEntity.ok().body(modelMapper.map(userService.finById(id), UserDTO.class));
    }
}
