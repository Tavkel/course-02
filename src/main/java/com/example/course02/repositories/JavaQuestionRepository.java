package com.example.course02.repositories;

import com.example.course02.exceptions.QuestionAlreadyExistException;
import com.example.course02.models.Question;
import com.example.course02.services.interfaces.QuestionRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@Service("javaRepository")
public class JavaQuestionRepository implements QuestionRepository {
    private Set<Question> questionSet;

    public JavaQuestionRepository() {
        this.init();
    }

    @Override
    public Question add(Question question) {
        if (questionSet.contains(question)) throw new QuestionAlreadyExistException();
        questionSet.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (!questionSet.contains(question)) throw new NoSuchElementException();
        questionSet.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return Set.copyOf(questionSet);
    }

    //@PostConstruct
    private void init() {
        this.questionSet = new HashSet<>();
        questionSet.add(new Question("Java?", "Yes"));
        questionSet.add(new Question("How much primitive types there are in java?", "8"));
        questionSet.add(new Question("How much bits \"int\" takes?", "32"));
        questionSet.add(new Question("Do you need explicitly handle RuntimeException and it's children?", "No"));
        questionSet.add(new Question("Is Map a collection?", "Yes, but no"));
    }
}
