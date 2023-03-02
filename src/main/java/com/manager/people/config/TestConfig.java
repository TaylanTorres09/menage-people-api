package com.manager.people.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.manager.people.services.StartDBService;

@Configuration
@Profile("test")
public class TestConfig {
    
    @Autowired
    private StartDBService startDB;

    @Bean
    public void instatiateDataBase() {
        this.startDB.instatiateDataBase();
    }

}
