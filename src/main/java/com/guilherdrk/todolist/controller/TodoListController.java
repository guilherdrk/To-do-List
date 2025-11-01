package com.guilherdrk.todolist.controller;

import com.guilherdrk.todolist.domain.Task;
import com.guilherdrk.todolist.dto.TaskDescription;
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
    public void createTasks(@RequestBody Task task){
        tasks.add(task);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteTasks(@PathVariable Long id){
        tasks.removeIf(task -> task.id().equals(id));
        return ResponseEntity.noContent().build();

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateTask(@PathVariable Long id, @RequestBody TaskDescription dtoDescription){

        tasks = tasks.stream()
                .map(task -> {
                    if (task.id().equals(id)) {
                        Task newTask = new Task(task.id(), dtoDescription.description());
                        return newTask;
                    }
                    return task;
                }).toList();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> clearTaskList(){
        tasks = new ArrayList<>();
        return ResponseEntity.noContent().build();

    }



}
