package com.sumitsee.archival_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequestMapping("users")
@RequiredArgsConstructor
public class UserService {
//    private final UserService userService;

    // TO DO: we can add users logic here to get the users into database

//    public List<User> getAllUsers(){
//        return Arrays.asList(
//                new User(1L, "Sumit", "sumit@example.com"),
//                new User(2L, "Sunil", "sunil@example.com")
//        );
//    }

//    public User getUserById(Long id){
//        return getAllUsers().stream()
//                .filter(user -> user.getId().equals((id)))
//                .findFirst()
//                .orElse(null);
//    }

}
