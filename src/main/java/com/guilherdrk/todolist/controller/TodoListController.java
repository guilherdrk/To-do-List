package com.guilherdrk.todolist.controller;

import com.guilherdrk.todolist.domain.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TodoListController {

    private List<Task> tasks = new ArrayList<>();


    @GetMapping
    public ResponseEntity<List<Task>> listTasks(){
        return ResponseEntity.ok(tasks);
    }

    @PostMapping
    public ResponseEntity<Task> createTasks(@RequestBody Task task){
        Task newTask = new Task(task.id(), task.description());
        tasks.add(newTask);
        return ResponseEntity.ok(newTask);

    }

}
