package com.example.hello.controller;

import com.example.hello.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // 해당 Class 는 Rest API 처리하는 Controller
@RequestMapping("/api") // RequestMapping URL 을 지정해주는 Annotation
public class ApiController {

    @GetMapping("/hello")
    public String hello(){

        return "<div style='background-color:green; width:200px; height:200px;'>a</div>";
    }

    @GetMapping("/text")
    public String text(@RequestParam String account){
        return account;
    }

    @PostMapping("/json")
    public User  json(@RequestBody User user){
        System.out.println(user);
        return user;
    }

    @PutMapping("/put")
    public ResponseEntity<User> pub(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
