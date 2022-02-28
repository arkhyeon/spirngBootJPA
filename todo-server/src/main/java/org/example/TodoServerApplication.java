package org.example;

import org.example.repository.TodoRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoServerApplication {
    public static void main(String[] args) {
        System.out.println("Hello TODO ");
        SpringApplication.run(TodoServerApplication.class, args);
    }
}
