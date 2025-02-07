package com.v1.api.service;

import com.v1.api.entitie.User;
import com.v1.api.entitie.dto.UserDTO;

import java.util.List;

public interface UserService {

    User findById(Integer id);

    List<User> finAll();

    User created(UserDTO userDTO);

    User update(UserDTO userDTO);

    void delete(Integer id);


}
