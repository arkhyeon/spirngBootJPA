package com.example.server.controller;

import com.example.server.dto.Req;
import com.example.server.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController
@RequestMapping("/api/server")
@Slf4j
public class ServerApiController {

    @GetMapping("/naver")
    public String naver(){

        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/local.json")
                .queryParam("query","갈비집")
                .queryParam("display", 10)
                .queryParam("sort", "random")
//                .encode(Charset.forName("UTF-8"))
                .encode()
                .build()
                .toUri();

        log.info("uri : {}",uri);

        RestTemplate restTemplate = new RestTemplate();

        RequestEntity<Void> req = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id","kiksCFo1tDAdlV9PmQgs")
                .header("X-Naver-Client-Secret","wBLeZmgyI6")
                .build();

        ResponseEntity<String> result = restTemplate.exchange(req, String.class);

        return result.getBody();


    }

    @GetMapping("na")
    public Mono<String> naver2(){

        WebClient webClient = WebClient
                .builder()
//                .defaultHeader("X-Naver-Client-Id","kiksCFo1tDAdlV9PmQgs")
//                .defaultHeader("X-Naver-Client-Secret","wBLeZmgyI6")
                .build();

        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/local.json")
                .queryParam("query","갈비집")
                .queryParam("display", 10)
                .queryParam("sort", "random")
//                .encode(Charset.forName("UTF-8"))
                .encode()
                .build()
                .toUri();

        return webClient.get().uri(uri).header("X-Naver-Client-Id","kiksCFo1tDAdlV9PmQgs")
                .header("X-Naver-Client-Secret","wBLeZmgyI6").retrieve().bodyToMono(String.class);


    }

    @GetMapping("/hello")
    public User hello(@RequestParam String name, @RequestParam int age){
        User user = new User();
        user.setName(name);
        user.setAge(age);
        return user;
    }

    @PostMapping("/user/{userId}/name/{userName}")
    public Req<User> post(
//            HttpEntity<String> entity,
                          @RequestBody Req<User> user,
                          @PathVariable int userId, @PathVariable String userName,
                          @RequestHeader("x-authorization") String authorization,
                          @RequestHeader("custom-header") String customHeader){
//        log.info("entity : {}", entity.getBody());
        log.info("userId : {}", userId);
        log.info("userName : {}", userName);
        log.info("client req : {}", user);
        log.info("authorization : {}", authorization);
        log.info("customHeader : {}", customHeader);

        Req<User> response = new Req<>();
        response.setHeader(new Req.Header());
        response.setResponseBody(user .getResponseBody());

        return response;
    }
}
