package com.example.hello.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/get")
public class GetApiController {

    @GetMapping(path = "/hello")
    public String hello(){
        return "get Hello";
    }

    @RequestMapping(path = "/hi", method = RequestMethod.GET)
    public String hi(){
        return "get hi";
    }

    // http://localhost:8081/api/get/pathVariable/{name}
    @GetMapping("/path-variable/{tech}")
    public String pathVariable(@PathVariable(name = "tech") String pathTech, String tech){
        tech = "java";
        System.out.println(pathTech);
        return "Hello " + pathTech + tech;
    }
}
