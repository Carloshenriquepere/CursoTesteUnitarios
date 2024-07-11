package com.v1.api.service.impl;

import com.v1.api.entitie.User;
import com.v1.api.entitie.dto.UserDTO;
import com.v1.api.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    public static final Integer ID = 1;
    public static final String NAME = "test";
    public static final String EMAIL = "teste@email.com";
    public static final String PASSWORD = "1254";

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
    void finAll() {
    }

    @Test
    void created() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUser(){
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
        userOptional = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }
}