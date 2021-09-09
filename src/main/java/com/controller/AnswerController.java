package com.controller;

import com.entity.Answer;
import com.model.payload.AnswerDto;
import com.model.response.Response;
import com.service.AnswerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/answer")
public class AnswerController {

    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping
    public ResponseEntity<Response<Answer>>  addAnswer(@RequestBody AnswerDto answerDto){
        return answerService.addAnswer(answerDto);
    }

    @GetMapping("/getAnswerById/{id}")
    public ResponseEntity<Response<Answer>> getAnswerById(@PathVariable Long id){
        return answerService.getAnswerById(id);
    }

    @GetMapping
    public ResponseEntity<Response<List<Answer>>> getAllAnswer(){
        return answerService.getAllCategories();
    }

    @PutMapping("/editAnswerById/{id}")
    public ResponseEntity<Response<Answer>> editAnswerById(@PathVariable Long id, @RequestBody AnswerDto answerDto){
        return answerService.editAnswerById(id, answerDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Answer>> deleteAnswerById(@PathVariable Long id){
        return  answerService.deleteAnswerById(id);
    }

}
