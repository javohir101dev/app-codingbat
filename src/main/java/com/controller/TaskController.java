package com.controller;

import com.entity.Task;
import com.model.payload.TaskDto;
import com.model.response.Response;
import com.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Response<Task>>  addTask(@RequestBody TaskDto taskDto){
        return taskService.addTask(taskDto);
    }

    @GetMapping("/getTaskById/{id}")
    public ResponseEntity<Response<Task>> getTaskById(@PathVariable Long id){
        return taskService.getTaskById(id);
    }

    @GetMapping
    public ResponseEntity<Response<List<Task>>> getAllTask(){
        return taskService.getAllCategories();
    }

    @PutMapping("/editTaskById/{id}")
    public ResponseEntity<Response<Task>> editTaskById(@PathVariable Long id, @RequestBody TaskDto taskDto){
        return taskService.editTaskById(id, taskDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Task>> deleteTaskById(@PathVariable Long id){
        return  taskService.deleteTaskById(id);
    }

}
