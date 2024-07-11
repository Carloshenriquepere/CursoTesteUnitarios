package com.v1.api.entitie.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class UserDTO {


    private Integer id;

    private String name;

    private String email;

    @JsonIgnore
    private String password;


}
