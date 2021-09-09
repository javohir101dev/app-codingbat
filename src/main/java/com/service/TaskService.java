package com.service;

import com.entity.Category;
import com.entity.Task;
import com.entity.Language;
import com.model.payload.TaskDto;
import com.model.response.Response;
import com.repo.CategoryRepo;
import com.repo.TaskRepo;
import com.repo.LanguageRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepo taskRepo;
    private final LanguageRepo languageRepo;
    private final CategoryRepo categoryRepo;

    public TaskService(TaskRepo taskRepo, LanguageRepo languageRepo, CategoryRepo categoryRepo) {
        this.taskRepo = taskRepo;
        this.languageRepo = languageRepo;
        this.categoryRepo = categoryRepo;
    }

    public ResponseEntity<Response<Task>> addTask(TaskDto taskDto){

            Optional<Language> optionalLanguage = languageRepo.findById(taskDto.getLanguageId());
            if (!optionalLanguage.isPresent()){
                return new ResponseEntity<>(new Response<>("Language with id " + taskDto.getLanguageId() + " is not found"), HttpStatus.NOT_FOUND);
            }

        Optional<Category> optionalCategory = categoryRepo.findById(taskDto.getCategoryId());
            if (!optionalCategory.isPresent()){
                return new ResponseEntity<>(new Response<>("Category with id " + taskDto.getCategoryId() + " is not found"), HttpStatus.NOT_FOUND);
            }


        Task task = new Task();
        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription());
        task.setExample(taskDto.getExample());
        task.setBody(taskDto.getBody());
        task.setHint(taskDto.getHint());
        task.setSolution(taskDto.getSolution());
        task.setHasStar(taskDto.isHasStar());


        task.setLanguage(optionalLanguage.get());
        task.setCategory(optionalCategory.get());

        Task savedTask = taskRepo.save(task);
        return ResponseEntity.ok(new Response<>(savedTask));
    }

    public ResponseEntity<Response<Task>> getTaskById(Long id){
        Optional<Task> optionalTask = taskRepo.findById(id);
        if (!optionalTask.isPresent()){
            return new ResponseEntity<>(new Response<>("Task is not found"), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(new Response<>(optionalTask.get()));
    }

    public ResponseEntity<Response<List<Task>>> getAllCategories(){
        return ResponseEntity.ok(new Response<>(taskRepo.findAll()));
    }

    public ResponseEntity<Response<Task>> editTaskById(Long id, TaskDto taskDto){
        Optional<Task> optionalTask = taskRepo.findById(id);
        if (!optionalTask.isPresent()){
            return new ResponseEntity<>(new Response<>("Task is not found"), HttpStatus.NOT_FOUND);
        }

        Optional<Language> optionalLanguage = languageRepo.findById(taskDto.getLanguageId());
        if (!optionalLanguage.isPresent()){
            return new ResponseEntity<>(new Response<>("Language with id " + taskDto.getLanguageId() + " is not found"), HttpStatus.NOT_FOUND);
        }

        Optional<Category> optionalCategory = categoryRepo.findById(taskDto.getCategoryId());
        if (!optionalCategory.isPresent()){
            return new ResponseEntity<>(new Response<>("Category with id " + taskDto.getCategoryId() + " is not found"), HttpStatus.NOT_FOUND);
        }


        Task task = optionalTask.get();

        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription());
        task.setExample(taskDto.getExample());
        task.setBody(taskDto.getBody());
        task.setHint(taskDto.getHint());
        task.setSolution(taskDto.getSolution());
        task.setHasStar(taskDto.isHasStar());


        task.setLanguage(optionalLanguage.get());
        task.setCategory(optionalCategory.get());

        Task savedTask = taskRepo.save(task);
        return ResponseEntity.ok(new Response<>(savedTask));
    }


    public ResponseEntity<Response<Task>> deleteTaskById(Long id){
        Optional<Task> optionalTask = taskRepo.findById(id);
        if (!optionalTask.isPresent()){
            return new ResponseEntity<>(new Response<>("Task is not found"), HttpStatus.NOT_FOUND);
        }

        Task task = optionalTask.get();
        taskRepo.deleteById(id);
        return ResponseEntity.ok(new Response<>(task));
    }



}
