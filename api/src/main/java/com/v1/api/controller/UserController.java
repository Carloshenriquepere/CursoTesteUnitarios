package com.v1.api.controller;

import com.v1.api.entitie.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserController {



    public ResponseEntity<User> userFindById(){
        return null;
    }
}
