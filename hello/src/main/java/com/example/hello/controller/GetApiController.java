package com.example.hello.controller;

import com.example.hello.dto.UserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @GetMapping(path = "/query-param")
    public String queryParam(@RequestParam Map<String, String> queryParam){

        StringBuilder sb = new StringBuilder();
        queryParam.entrySet().forEach( entry -> {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("\n");

            sb.append(entry.getKey() + " = " + entry.getValue() + "\n");
        });

        return sb.toString();
    }

    @GetMapping("/query-param02")
    public String queryParam02(@RequestParam String name, @RequestParam String email, @RequestParam int age){

        System.out.println(name);
        System.out.println(email);
        System.out.println(age);

        return name + " " + email + " " + age;
    }

      @GetMapping("/query-param03")
    public String queryParam03(UserRequest userRequest){

        System.out.println(userRequest.getName());
        System.out.println(userRequest.getEmail());
        System.out.println(userRequest.getAge());

        return userRequest.toString();
    }

}
