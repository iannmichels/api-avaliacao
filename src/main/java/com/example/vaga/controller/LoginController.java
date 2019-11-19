package com.example.vaga.controller;

import com.example.vaga.model.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin()
@RestController
@RequestMapping("/login")
public class LoginController {

    @GetMapping(produces = "application/json")
    @RequestMapping(value="/validateLogin" )
    public User validateLogin() {
        return new User("User successfully authenticated");
    }
}
