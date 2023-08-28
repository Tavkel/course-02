package com.example.course02.services.implementations;

import com.example.course02.exceptions.TooManyQuestionsException;
import com.example.course02.models.Question;
import com.example.course02.services.interfaces.ExaminerService;
import com.example.course02.services.interfaces.QuestionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService javaQuestionService;
    private final QuestionService mathQuestionService;

    public ExaminerServiceImpl(@Qualifier("javaService") QuestionService javaQuestionService, @Qualifier("mathService") QuestionService mathQuestionService) {
        this.javaQuestionService = javaQuestionService;
        this.mathQuestionService = mathQuestionService;
    }

    @Override
    public Collection<Question> getQuestions(int n) {
        if (n > mathQuestionService.getAll().size() + javaQuestionService.getAll().size()){
            throw new TooManyQuestionsException();
        }

        var rng = new Random();
        var result = new ArrayList<Question>();
        Question q;

        //оно работает, но алгоритм очень неочень.
        while (result.size() < n){
            if(rng.nextBoolean()) q = mathQuestionService.getRandomQuestion();
            else q = javaQuestionService.getRandomQuestion();

            if (!result.contains(q)) result.add(q);
        }
        return result;
    }
}
