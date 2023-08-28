package com.example.course02.services.implementations;

import com.example.course02.exceptions.HorrendousException;
import com.example.course02.models.Question;
import com.example.course02.services.interfaces.QuestionRepository;
import com.example.course02.services.interfaces.QuestionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.server.MethodNotAllowedException;

import java.util.*;

@Service("mathService")
public class MathQuestionService implements QuestionService {
    private final Random rng = new Random();
    private final QuestionRepository questionRepository;

    public MathQuestionService(@Qualifier("mathRepository") QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question add(String question, String answer) {
        var q = new Question(question, answer);
        throw new MethodNotAllowedException("add", null);
        //return questionRepository.add(q);
    }

    @Override
    public Question add(Question question) {
        throw new MethodNotAllowedException("add", null);
        //return questionRepository.add(question);
    }

    @Override
    public Question remove(Question question) {
        throw new MethodNotAllowedException("remove", null);
//        return questionRepository.remove(question);
    }

    @Override
    public Collection<Question> getAll() {
        throw new MethodNotAllowedException("getAll", null);
        //return questionRepository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        return generateQuestion();
        //var questions = questionRepository.getAll();
        //return questions.toArray(Question[]::new)[rng.nextInt(questions.size())];
    }

    private Question generateQuestion() {
        int num1 = rng.nextInt(100);
        int num2 = rng.nextInt(100);
        String answer;
        String question;
        switch (rng.nextInt(4)) {
            case 0:
                answer = String.valueOf(num1 + num2);
                question = num1 + "+" + num2 + "?";
                return new Question(question, answer);
            case 1:
                answer = String.valueOf(num1 - num2);
                question = num1 + "-" + num2 + "?";
                return new Question(question, answer);
            case 2:
                answer = String.valueOf(num1 * num2);
                question = num1 + "*" + num2 + "?";
                return new Question(question, answer);
            case 3:
                while(num2 == 0){
                    num2 = rng.nextInt(100);
                }
                answer = String.valueOf(num1 / num2);
                question = num1 + "/" + num2 + "?";
                return new Question(question, answer);
            default:
                throw new HorrendousException();
        }
    }
}
