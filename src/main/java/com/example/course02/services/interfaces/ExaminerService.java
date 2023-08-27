package com.example.course02.services.interfaces;

import com.example.course02.models.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int n);
}
