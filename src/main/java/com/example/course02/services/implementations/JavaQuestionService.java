package com.example.course02.services.implementations;

import com.example.course02.exceptions.QuestionAlreadyExistException;
import com.example.course02.models.Question;
import com.example.course02.services.interfaces.QuestionService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    private final Set<Question> questionList;
    private final Random rng = new Random();

    public JavaQuestionService() {
        questionList = seedQuestions();
    }

    @Override
    public Question add(String question, String answer) {
        var result = new Question(question, answer);
        if (questionList.contains(result)) throw new QuestionAlreadyExistException();
        questionList.add(result);
        return result;
    }

    @Override
    public Question add(Question question) {
        questionList.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (!questionList.contains(question)) throw new NoSuchElementException();
        questionList.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return Set.copyOf(questionList);
    }

    @Override
    public Question getRandomQuestion() {
        return questionList.toArray(Question[]::new)[rng.nextInt(questionList.size())];
    }

    private HashSet<Question> seedQuestions(){
        var q = new HashSet<Question>();
        q.add(new Question("Java?", "Yes"));
        q.add(new Question("How much primitive types there are in java?", "8"));
        q.add(new Question("How much bits \"int\" takes?", "32"));
        q.add(new Question("Do you need explicitly handle RuntimeException and it's children?", "No"));
        q.add(new Question("Is Map a collection?", "Yes, but no"));
        return q;
    }
}
