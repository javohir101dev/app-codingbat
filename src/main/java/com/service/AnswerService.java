package com.service;

import com.entity.*;
import com.model.payload.AnswerDto;
import com.model.response.Response;
import com.repo.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

    private final AnswerRepo answerRepo;
    private final TaskRepo taskRepo;
    private final UserRepo userRepo;

    public AnswerService(AnswerRepo answerRepo, TaskRepo taskRepo, UserRepo userRepo) {
        this.answerRepo = answerRepo;
        this.taskRepo = taskRepo;
        this.userRepo = userRepo;
    }


    public ResponseEntity<Response<Answer>> addAnswer(AnswerDto answerDto){

        Optional<Task> optionalTask = taskRepo.findById(answerDto.getTaskId());
        if (!optionalTask.isPresent()){
            return new ResponseEntity<>(new Response<>("Task is not found"), HttpStatus.NOT_FOUND);
        }

        Optional<User> optionalUser = userRepo.findById(answerDto.getUserId());
        if (!optionalUser.isPresent()){
            return new ResponseEntity<>(new Response<>("User is not found"), HttpStatus.NOT_FOUND);
        }


        Answer answer = new Answer();
        answer.setCode(answerDto.getCode());
        answer.setTask(optionalTask.get());
        answer.setUser(optionalUser.get());

        Answer savedAnswer = answerRepo.save(answer);
        return ResponseEntity.ok(new Response<>(savedAnswer));
    }

    public ResponseEntity<Response<Answer>> getAnswerById(Long id){
        Optional<Answer> optionalAnswer = answerRepo.findById(id);
        if (!optionalAnswer.isPresent()){
            return new ResponseEntity<>(new Response<>("Answer is not found"), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(new Response<>(optionalAnswer.get()));
    }

    public ResponseEntity<Response<List<Answer>>> getAllCategories(){
        return ResponseEntity.ok(new Response<>(answerRepo.findAll()));
    }

    public ResponseEntity<Response<Answer>> editAnswerById(Long id, AnswerDto answerDto){
        Optional<Answer> optionalAnswer = answerRepo.findById(id);
        if (!optionalAnswer.isPresent()){
            return new ResponseEntity<>(new Response<>("Answer is not found"), HttpStatus.NOT_FOUND);
        }

        Optional<Task> optionalTask = taskRepo.findById(answerDto.getTaskId());
        if (!optionalTask.isPresent()){
            return new ResponseEntity<>(new Response<>("Task is not found"), HttpStatus.NOT_FOUND);
        }

        Optional<User> optionalUser = userRepo.findById(answerDto.getUserId());
        if (!optionalUser.isPresent()){
            return new ResponseEntity<>(new Response<>("User is not found"), HttpStatus.NOT_FOUND);
        }


        Answer answer = optionalAnswer.get();
        answer.setCode(answerDto.getCode());
        answer.setTask(optionalTask.get());
        answer.setUser(optionalUser.get());

        Answer savedAnswer = answerRepo.save(answer);
        return ResponseEntity.ok(new Response<>(savedAnswer));
    }


    public ResponseEntity<Response<Answer>> deleteAnswerById(Long id){
        Optional<Answer> optionalAnswer = answerRepo.findById(id);
        if (!optionalAnswer.isPresent()){
            return new ResponseEntity<>(new Response<>("Answer is not found"), HttpStatus.NOT_FOUND);
        }

        Answer answer = optionalAnswer.get();
        answerRepo.deleteById(id);
        return ResponseEntity.ok(new Response<>(answer));
    }



}
