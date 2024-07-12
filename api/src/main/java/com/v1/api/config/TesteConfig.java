package com.v1.api.config;

import com.v1.api.entitie.User;
import com.v1.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("teste")
public class TesteConfig {


    @Autowired
    public UserRepository userRepository;

    @Bean
    public Boolean startDB(){
        User u1  =  new User(null, "Paty", "Pay@email.com","1254");
        User u2  =  new User(null, "graca", "garca@email.com","1254");

        userRepository.saveAll( List.of(u1,u2));

        return true;
    }
}
