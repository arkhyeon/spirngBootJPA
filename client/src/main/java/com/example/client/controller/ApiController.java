package com.example.client.controller;

import com.example.client.dto.UserResponse;
import com.example.client.service.RestTemplateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client")
public class ApiController {

    private final RestTemplateService restTemlateService;

    public ApiController(RestTemplateService restTemlateService) {
        this.restTemlateService = restTemlateService;
    }

    @GetMapping("/hello")
    public UserResponse getHello(){
        return restTemlateService.post();
    }

}
