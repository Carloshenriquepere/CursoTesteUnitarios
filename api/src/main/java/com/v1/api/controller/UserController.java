package com.v1.api.controller;

import com.v1.api.entitie.User;
import com.v1.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping(value = "/{id}")
    public ResponseEntity<User> FindById(@PathVariable Integer id){
        return ResponseEntity.ok().body(userService.finById(id));
    }
}
