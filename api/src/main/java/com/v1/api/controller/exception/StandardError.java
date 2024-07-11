package com.v1.api.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;



@AllArgsConstructor
@Getter
public class StandardError {

    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String path;
}
