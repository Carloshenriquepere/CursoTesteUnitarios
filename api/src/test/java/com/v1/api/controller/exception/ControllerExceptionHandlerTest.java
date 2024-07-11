package com.v1.api.controller.exception;



import com.v1.api.service.exceptions.DataIntegratyViolationException;
import com.v1.api.service.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ControllerExceptionHandlerTest {

    public static final String USER_NOT_FOUND = "User Not Found";
    public static final String EMAIL_ALREADY_EXISTS = "Email Already Exists";

    @InjectMocks
    private ControllerExceptionHandler exceptionHandler;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenObjectNotFoundExceptionThenReturnAnResponseEntity() {
        ResponseEntity<StandardError> response = exceptionHandler
                .objectNotFound(
                        new ObjectNotFoundException(USER_NOT_FOUND), new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals(USER_NOT_FOUND, response.getBody().getError());
        assertEquals(404, response.getBody().getStatus());
        assertNotEquals("/users/2", response.getBody().getPath());
        assertNotEquals(LocalDateTime.now(), response.getBody().getTimestamp());

    }

    @Test
    void whenDataIntegratyViolationException() {

        ResponseEntity<StandardError> response = exceptionHandler
                .dataIntegratyViolationException(
                        new DataIntegratyViolationException(EMAIL_ALREADY_EXISTS), new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals(EMAIL_ALREADY_EXISTS, response.getBody().getError());
        assertEquals(400, response.getBody().getStatus());
    }
}