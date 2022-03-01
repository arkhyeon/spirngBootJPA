package org.example.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.TodoModel;
import org.example.model.TodoRequest;
import org.example.model.TodoResponse;
import org.example.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/")
public class TodoController {

    private final TodoService service;

    @PostMapping
    public ResponseEntity<TodoResponse> create(@RequestBody TodoRequest request){
        log.info("Create");

        if(ObjectUtils.isEmpty(request.getTitle())){
            return ResponseEntity.badRequest().build();
        }
        if(ObjectUtils.isEmpty(request.getOrder())){
            request.setOrder(0L);
        }
        if(ObjectUtils.isEmpty(request.getCompleted())){
            request.setCompleted(false);
        }

        TodoModel result = this.service.add(request);
        return ResponseEntity.ok(new TodoResponse(result));
    }

    @GetMapping("{id}")
    public ResponseEntity<TodoResponse> readOne(@PathVariable Long id){
        log.info("Read One");

        TodoModel result = this.service.searchById(id);
        return ResponseEntity.ok(new TodoResponse(result));
    }

    @GetMapping()
    public ResponseEntity<List<TodoResponse>> readAll(){
        log.info("Read All");
        List<TodoModel> list = this.service.searchAll();
        List<TodoResponse> response = list.stream().map(TodoResponse::new)
                                                    .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @PatchMapping("{id}")
    public ResponseEntity<TodoResponse> update(@PathVariable Long id, @RequestBody TodoRequest request){
        log.info("Update");
        TodoModel result = this.service.updateById(id, request);
        return ResponseEntity.ok(new TodoResponse(result));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteOne(@PathVariable Long id){
        log.info("Delete");
        this.service.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAll(){
        log.info("Delete All");
        this.service.deleteAll();
        return ResponseEntity.ok().build();
    }
}
