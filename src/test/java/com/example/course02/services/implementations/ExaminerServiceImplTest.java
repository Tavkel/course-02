package com.example.course02.services.implementations;

import com.example.course02.exceptions.TooManyQuestionsException;
import com.example.course02.models.Question;
import com.example.course02.services.interfaces.QuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import static com.example.course02.services.implementations.ExaminerServiceImplTest.ExaminerServiceTestData.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {
    @Mock
    private JavaQuestionService javaQuestionService;
    private ExaminerServiceImpl sut;

    @BeforeEach
    private void initializeSut() {
        when(javaQuestionService.getAll()).thenReturn(new HashSet<>(questionsDummy));

        sut = new ExaminerServiceImpl(javaQuestionService);
    }

    @Test
    void getQuestions_shouldReturnCollectionOfUniqueRandomQuestionsFromTheList() {
        when(javaQuestionService.getRandomQuestion())
                .thenReturn(questionsDummy.get(2))
                .thenReturn(questionsDummy.get(3))
                .thenReturn(questionsDummy.get(2))
                .thenReturn(questionsDummy.get(3))
                .thenReturn(questionsDummy.get(0))
                .thenReturn(questionsDummy.get(1));

        int count = 4;
        var actual = sut.getQuestions(count);
        verify(javaQuestionService, times(6)).getRandomQuestion();
        assertEquals(count, actual.stream().distinct().count());
    }

    @Test
    void getQuestions_shouldThrowTooManyQuestionsExceptionIfAmountProvidedIsMoreThanAmountOfQuestionsExisting() {
        assertThrows(TooManyQuestionsException.class, () -> sut.getQuestions(6));
    }

    static class ExaminerServiceTestData {
        static List<Question> questionsDummy = List.of(
                new Question("Java?", "Yes"),
                new Question("How much primitive types there are in java?", "8"),
                new Question("How much bits \"int\" takes?", "32"),
                new Question("Do you need explicitly handle RuntimeException and it's children?", "No"),
                new Question("Is Map a collection?", "Yes, but no")
        );
    }
}
