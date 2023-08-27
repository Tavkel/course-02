package com.example.course02.controllers;

import com.example.course02.models.Question;
import com.example.course02.services.interfaces.ExaminerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/exam")
public class ExamController {
    ExaminerService examinerService;

    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping()
    public ResponseEntity<Collection<Question>> getQuestions(@RequestParam int amount){
        return new ResponseEntity<>(examinerService.getQuestions(amount), HttpStatus.OK);
    }
}
