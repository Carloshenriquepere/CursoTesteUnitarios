package com.v1.api.controller;

import com.v1.api.entitie.dto.UserDTO;
import com.v1.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> FindById(@PathVariable Integer id){
        return ResponseEntity.ok().body(userService.finById(id));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        return ResponseEntity.ok().body(userService.finAll());
    }

    @PostMapping
    public ResponseEntity<UserDTO> save(@RequestBody UserDTO userDTO){
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(userService.created(userDTO).getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
