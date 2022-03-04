package com.example.hello.controller;

import com.example.hello.dto.PutRequestDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/put")
public class PutApiController {

    @PutMapping("/put/{userId}")
    public PutRequestDto put(@RequestBody PutRequestDto putRequestDto, @PathVariable Long userId){
        System.out.println(putRequestDto.toString());
        System.out.println(userId);
        return putRequestDto;
    }
}
