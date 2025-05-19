package com.sumitsee.archival_service.controller;

import com.sumitsee.archival_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

//    @GetMapping("/ping")
//    public String ping(){
//        return "Backed in workng";
//    }

//    @GetMapping
//    public List<User> getUsers(){
//        return userService.getAllUsers();
//    }
}
