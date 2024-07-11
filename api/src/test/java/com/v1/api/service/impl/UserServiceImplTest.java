package com.v1.api.service.impl;

import com.v1.api.entitie.User;
import com.v1.api.entitie.dto.UserDTO;
import com.v1.api.repository.UserRepository;
import com.v1.api.service.exceptions.DataIntegratyViolationException;
import com.v1.api.service.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    public static final Integer ID = 1;
    public static final String NAME = "test";
    public static final String EMAIL = "teste@email.com";
    public static final String PASSWORD = "1254";
    public static final String USER_NOT_FOUND = "User Not Found";
    public static final int INDEX = 0;
    public static final String EMAIL_ALREADY_EXISTS = "Email Already Exists";

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;


    private User user;
    private UserDTO userDTO;
    private Optional<User> userOptional;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdThenReturnUser() {
        when(userRepository.findById(anyInt())).thenReturn(userOptional);

        User response =  userServiceImpl.findById(ID);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(NAME, response.getName());
        assertEquals(ID, response.getId());
        assertEquals(EMAIL, response.getEmail());
    }

    @Test
    void whenFindByIdThenReturnObjectNotFoundException() {

        when(userRepository.findById(anyInt())).thenThrow(new ObjectNotFoundException(USER_NOT_FOUND));

        try {
            userServiceImpl.findById(ID);

        }catch (Exception e) {
            assertEquals(ObjectNotFoundException.class, e.getClass());
            assertEquals(USER_NOT_FOUND, e.getMessage());
        }
    }

    @Test
    void whenFindAllThenReturnListOfUsers() {

        when(userRepository.findAll()).thenReturn(List.of(user));

        List<User> response = userServiceImpl.finAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(User.class, response.get(INDEX).getClass());
        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NAME, response.get(INDEX).getName());
        assertEquals(EMAIL, response.get(INDEX).getEmail());
        assertEquals(PASSWORD, response.get(INDEX).getPassword());
    }

    @Test
    void whenCreatedThenReturnSuccess() {
        when(userRepository.save(any())).thenReturn(user);

        User response = userServiceImpl.created(userDTO);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void whenCreatedThenReturnAnDataIntegratyViolationException() {
        when(userRepository.findByEmail(anyString())).thenReturn(userOptional);

        try {
            userOptional.get().setId(2);
            userServiceImpl.created(userDTO);
        }catch (Exception e) {
            assertEquals(DataIntegratyViolationException.class, e.getClass());
            assertEquals(EMAIL_ALREADY_EXISTS, e.getMessage());
        }

    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(userRepository.save(any())).thenReturn(user);

        User response = userServiceImpl.update(userDTO);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void whenUpdateThenReturnAnDataIntegratyViolationException() {
        when(userRepository.findByEmail(anyString())).thenReturn(userOptional);

        try {
            userOptional.get().setId(2);
            userServiceImpl.created(userDTO);
        }catch (Exception e) {
            assertEquals(DataIntegratyViolationException.class, e.getClass());
            assertEquals(EMAIL_ALREADY_EXISTS, e.getMessage());
        }

    }

    @Test
    void whenDeleteThenReturnDeleteUser() {

    }

    private void startUser(){
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
        userOptional = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }
}