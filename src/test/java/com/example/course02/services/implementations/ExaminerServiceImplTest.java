package com.example.course02.services.implementations;

import com.example.course02.exceptions.TooManyQuestionsException;
import com.example.course02.models.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.List;

import static com.example.course02.services.implementations.ExaminerServiceImplTest.ExaminerServiceTestData.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {
    @Mock
    private JavaQuestionService javaQuestionService;
    @Mock
    private MathQuestionService mathQuestionService;
    private ExaminerServiceImpl sut;

    @BeforeEach
    private void initializeSut() {
        when(javaQuestionService.getAll()).thenReturn(new HashSet<>(javaQuestionsDummy));
        when(mathQuestionService.getAll()).thenReturn(new HashSet<>(mathQuestionsDummy));

        sut = new ExaminerServiceImpl(javaQuestionService, mathQuestionService);
    }

    @Test
    void getQuestions_shouldReturnCollectionOfUniqueRandomQuestionsFromTheList() {
        when(javaQuestionService.getRandomQuestion())
                .thenReturn(javaQuestionsDummy.get(2))
                .thenReturn(javaQuestionsDummy.get(3))
                .thenReturn(javaQuestionsDummy.get(2))
                .thenReturn(javaQuestionsDummy.get(3))
                .thenReturn(javaQuestionsDummy.get(0))
                .thenReturn(javaQuestionsDummy.get(1));
        
        when(mathQuestionService.getRandomQuestion())
                .thenReturn(mathQuestionsDummy.get(2))
                .thenReturn(mathQuestionsDummy.get(3))
                .thenReturn(mathQuestionsDummy.get(2))
                .thenReturn(mathQuestionsDummy.get(3))
                .thenReturn(mathQuestionsDummy.get(0))
                .thenReturn(mathQuestionsDummy.get(1));
        

        int count = 7;
        var actual = sut.getQuestions(count);
        assertEquals(count, actual.stream().distinct().count());
    }

    @Test
    void getQuestions_shouldThrowTooManyQuestionsExceptionIfAmountProvidedIsMoreThanAmountOfQuestionsExisting() {
        assertThrows(TooManyQuestionsException.class, () -> sut.getQuestions(11));
    }

    static class ExaminerServiceTestData {
        static List<Question> javaQuestionsDummy = List.of(
                new Question("Java?", "Yes"),
                new Question("How much primitive types there are in java?", "8"),
                new Question("How much bits \"int\" takes?", "32"),
                new Question("Do you need explicitly handle RuntimeException and it's children?", "No"),
                new Question("Is Map a collection?", "Yes, but no")
        );
        static List<Question> mathQuestionsDummy = List.of(
                new Question("1+1?", "2"),
                new Question("13*2?", "26"),
                new Question("sin(90)?", "1"),
                new Question("sqrt(4)?", "2"),
                new Question("Pi, two decimal places", "3.14")
        );
    }
}
