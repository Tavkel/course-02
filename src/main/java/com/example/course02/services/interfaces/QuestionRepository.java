package com.example.course02.services.interfaces;

import com.example.course02.models.Question;

import java.util.Collection;

public interface QuestionRepository {
    Question add (Question question);
    Question remove (Question question);
    Collection<Question> getAll();
}
