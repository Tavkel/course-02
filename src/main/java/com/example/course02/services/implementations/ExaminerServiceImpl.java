package com.example.course02.services.implementations;

import com.example.course02.exceptions.TooManyQuestionsException;
import com.example.course02.models.Question;
import com.example.course02.services.interfaces.ExaminerService;
import com.example.course02.services.interfaces.QuestionService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int n) {
        if (n > questionService.getAll().size()) throw new TooManyQuestionsException();
        var result = new ArrayList<Question>();
        while (result.size() < n){
            var q = questionService.getRandomQuestion();
            if (!result.contains(q)) result.add(q);
        }
        return result;
    }
}
