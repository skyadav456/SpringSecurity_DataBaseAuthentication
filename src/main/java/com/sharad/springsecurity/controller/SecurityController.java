package com.sharad.springsecurity.controller;

import com.sharad.springsecurity.entity.UserEntity;
import com.sharad.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class SecurityController {

    @Autowired
    private UserService userService;

    @PostMapping("/register/user")
    private String userRegistration(@RequestBody UserEntity userEntity) {
        return userService.userRegistration(userEntity);
    }
    @GetMapping("/public/hello")
    public String publicApi(){
        return "This is the public api";
    }

    @GetMapping("/user/profile")
    public String userApi(){
        return "This is the user api";
    }

    @GetMapping("/admin/profile")
    public String adminApi(){
        return "This is the admin api";
    }
}
