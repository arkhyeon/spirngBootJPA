package com.example.exception.controller;

import com.example.exception.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class ApiController {

    @GetMapping("")
    public User get(@RequestParam(required = false) String name, @RequestParam(required = false) Integer age){
        User user = new User();
        user.setName(name);
        user.setAge(age);

        int a = 10 + age;

        return user;
    }

    @PostMapping("")
    public User post(@Valid @RequestBody User user){
        System.out.println(user);
        return user;
    }

    //Global로 지정하더라도 컨트롤러에 지정한 ExceptionHandler가 우선 순위
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(Exception e){
        System.out.println("api Controller");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

    }
}
