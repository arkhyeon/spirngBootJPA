package com.example.validation.controller;

import com.example.validation.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ApiController {

    @PostMapping("/user")
    public Object user(@Valid @RequestBody User user, BindingResult br){

        if(br.hasErrors()){
            StringBuilder sb = new StringBuilder();
            br.getAllErrors().forEach(objectError -> {
                FieldError field = (FieldError) objectError;
                String msg = objectError.getDefaultMessage();
                System.out.println(field.getField());
                System.out.println(msg);

                sb.append("filed : " + field.getField());
                sb.append("msg : " + msg);

            });

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sb.toString());
        }

        System.out.println(user);

        return ResponseEntity.ok(user);
    }
}
