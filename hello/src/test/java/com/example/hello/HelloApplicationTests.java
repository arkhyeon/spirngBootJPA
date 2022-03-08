package com.example.hello;

import com.example.hello.dto.TestUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HelloApplicationTests {

    @Test
    void contextLoads() throws JsonProcessingException {
        System.out.println("===========================");

        // text json > object
        // object > Text json

        // controller req json(text) > object
        // response object > json(text)

        var objectMapper = new ObjectMapper();

        // object > text
        // object mapper get method 활용
        // object mapper 가 참조하는 클래스는 get 사용 금지(get으로 가져옴.)
        var user = new TestUser("steve", 10,"0102020202020");
        var text = objectMapper.writeValueAsString(user);
        System.out.println(text);

        // text > object
        // object mapper 는 default 생성자를 필요로 한다.
        var objectUser = objectMapper.readValue(text, TestUser.class);
        System.out.println(objectUser);

        System.out.println("===========================");
    }

}
