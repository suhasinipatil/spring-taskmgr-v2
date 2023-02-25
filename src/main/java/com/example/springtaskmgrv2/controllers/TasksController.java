package com.example.springtaskmgrv2.controllers;

import com.example.springtaskmgrv2.dtos.ErrorResponse;
import com.example.springtaskmgrv2.entities.TaskEntity;
import com.example.springtaskmgrv2.services.TasksService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TasksController {
    final TasksService tasksService;

    public TasksController(TasksService tasksService) {
        this.tasksService = tasksService;
    }

    @GetMapping("")
    public ResponseEntity<List<TaskEntity>> getAllTasks(){
        return ResponseEntity.ok(tasksService.getAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskEntity> getTaskById(@PathVariable Integer id){
        return ResponseEntity.ok(tasksService.getTaskById(id));
    }

    @PostMapping("")
    public ResponseEntity<TaskEntity> createTask(@RequestBody TaskEntity task) throws URISyntaxException {
        var createdTask = tasksService.createTask(task);
        return ResponseEntity.created(new URI("/tasks/" + createdTask.getId())).body(createdTask);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskEntity> updateTask(@RequestBody TaskEntity task, @PathVariable Integer id){
        var updatedTask = tasksService.updateTaskById(id, task.getTitle(), task.getDescription(), task.getDueDate(), task.getCompleted());
        return ResponseEntity.accepted().body(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskEntity> deleteTaskById(@PathVariable Integer id){
        return ResponseEntity.accepted().body(tasksService.deleteTaskById(id));
    }

    @GetMapping("?title={title}")
    public ResponseEntity<List<TaskEntity>> getAllTasksByTitle(@PathVariable String title){
        return ResponseEntity.ok(tasksService.findAllTasksByTitle(title));
    }

    @GetMapping("?completed={completed}")
    public ResponseEntity<List<TaskEntity>> getAllTasksByCompletedStatus(@PathVariable Boolean completed){
        return ResponseEntity.ok(tasksService.findAllTaskByCompleted(completed));
    }

    @ExceptionHandler(TasksService.TaskNotFoundException.class)
    ResponseEntity<ErrorResponse> handleErrors(TasksService.TaskNotFoundException e){
        return new ResponseEntity<>(
                new ErrorResponse(e.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }
}
