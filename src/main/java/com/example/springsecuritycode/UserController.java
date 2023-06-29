package com.example.springsecuritycode;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class UserController {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @GetMapping("/welcome")
    public String getWelcome(){
        return "Hi welcome to Spring Security Class";
    }


    @GetMapping("/admin") //This api should be under the admin role
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String getAdmin(){
        return "Hi Admin, you have been authenticated successfully";
    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String getUser(){
        return "Hi User, you have loggined successfully";
    }



    @PostMapping("/user/add")
    public String addUser(@RequestBody UserInfo userInfo){

        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfoRepository.save(userInfo);
        return "User has been sucessfully added to db";

    }

}
