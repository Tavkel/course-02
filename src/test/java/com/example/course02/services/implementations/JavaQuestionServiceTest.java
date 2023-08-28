package com.example.course02.services.implementations;

import com.example.course02.exceptions.QuestionAlreadyExistException;
import com.example.course02.models.Question;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static com.example.course02.services.implementations.JavaQuestionServiceTest.JavaQuestionServiceTestData.*;

class JavaQuestionServiceTest {
    private final JavaQuestionService sut;

    JavaQuestionServiceTest() {
        // probably should reset dummy before each test instead of copying it in each test, modifying the list;
        fillDummy();
        this.sut = new JavaQuestionService();
    }

    @Test
    void add_shouldAddQuestionToTheSetAndReturnAddedQuestion() {
        var q = new Question("C#?", "No");
        var actual = sut.add("C#?", "No");
        assertEquals(q, actual);

        var expected = new HashSet<>(questionsDummy);
        expected.add(q);
        assertEquals(expected, sut.getAll());
    }

    @Test
    void add_Question_shouldAddQuestionToTheSetAndReturnAddedQuestion() {
        var q = new Question("Haskell?", "No");
        var actual = sut.add(q);
        assertEquals(q, actual);
        var expected = new HashSet<>(questionsDummy);
        expected.add(q);
        assertEquals(expected, sut.getAll());
    }

    @Test
    void add_shouldThrowQuestionAlreadyExistExceptionIfQuestionAlreadyExist() {
        assertThrows(QuestionAlreadyExistException.class, () -> sut.add("Java?", "Yes"));
    }

    @Test
    void remove_shouldRemoveQuestionFromCollectionAndReturnRemovedQuestion() {
        var q = new Question("Java?", "Yes");
        var actual = sut.remove(q);
        assertEquals(q, actual);
        var expected = new HashSet<>(questionsDummy);
        expected.remove(q);
        assertEquals(expected, sut.getAll());
    }


    @Test
    void remove_shouldThrowNoSuchElementExceptionIfProvidedQuestionIsNotInTheList() {
        assertThrows(NoSuchElementException.class, () -> sut.remove(new Question("Brainfuck?", "Yes")));
    }

    @Test
    void getAll_shouldReturnImmutableCollectionOfAllQuestions() {
        var actual = sut.getAll();
        var expected = new HashSet<>(questionsDummy);
        assertEquals(actual, expected);
        assertThrows(UnsupportedOperationException.class, () -> actual.add(new Question("a", "b")));
    }

    @Test
    void getRandomQuestion_shouldReturnRandomQuestionFromTheList() {
        var actual = sut.getRandomQuestion();
        assertTrue(questionsDummy.contains(actual));
    }

    static class JavaQuestionServiceTestData {
        static HashSet<Question> questionsDummy = new HashSet<>();

        public static void fillDummy() {
            questionsDummy.add(new Question("Java?", "Yes"));
            questionsDummy.add(new Question("How much primitive types there are in java?", "8"));
            questionsDummy.add(new Question("How much bits \"int\" takes?", "32"));
            questionsDummy.add(new Question("Do you need explicitly handle RuntimeException and it's children?", "No"));
            questionsDummy.add(new Question("Is Map a collection?", "Yes, but no"));
        }
    }
}