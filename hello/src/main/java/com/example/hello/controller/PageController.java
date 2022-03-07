package com.example.hello.controller;

import com.example.hello.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //HTML Page 리소스 찾게 됨.
public class PageController {

    @RequestMapping("/main")
    public String main(){
        return "main.html";
    }

    @ResponseBody
    @GetMapping("/user")
    public User user(){
        var user = new User();
        user.setName("setext");
        user.setAddress("패스트 캠퍼스");

        return user;
    }
}
