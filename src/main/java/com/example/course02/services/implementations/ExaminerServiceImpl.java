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
    private final List<QuestionService> questionServices;

    public ExaminerServiceImpl(@Qualifier("javaService") QuestionService javaQuestionService, @Qualifier("mathService") QuestionService mathQuestionService) {
        this.questionServices = List.of(javaQuestionService, mathQuestionService);
    }

    @Override
    public Collection<Question> getQuestions(int n) {
        int size = 0;
        for (QuestionService service : questionServices) {
            size += service.getAll().size();
        }

        if (n > size) {
            throw new TooManyQuestionsException();
        }

        var rng = new Random();
        var result = new ArrayList<Question>();

        //оно работает, но алгоритм очень неочень.
        while (result.size() < n) {
            var q = questionServices.get(rng.nextInt(questionServices.size())).getRandomQuestion();

            if (!result.contains(q)) result.add(q);
        }
        return result;
    }
}
