package com.example.course02.repositories;

import com.example.course02.exceptions.QuestionAlreadyExistException;
import com.example.course02.models.Question;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;

import static com.example.course02.repositories.JavaQuestionRepositoryTest.JavaRepositoryTestData.*;
import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionRepositoryTest {
    private final JavaQuestionRepository sut = new JavaQuestionRepository();

    @Test
    void add_shouldAddQuestionToTheSetAndReturnAddedQuestion() {
        var q = new Question("C#?", "No");

        var expected = new HashSet<>(javaQuestionsDummy);
        expected.add(q);

        assertEquals(q, sut.add(q));
        assertEquals(expected, sut.getAll());
    }

    @Test
    void add_shouldThrowQuestionAlreadyExistExceptionIfQuestionAlreadyExist() {
        assertThrows(QuestionAlreadyExistException.class, () -> sut.add(new Question("Java?", "Yes")));
    }

    @Test
    void remove_shouldRemoveQuestionFromCollectionAndReturnRemovedQuestion() {
        var q = new Question("Java?", "Yes");

        var expected = new HashSet<>(javaQuestionsDummy);
        expected.remove(q);

        assertEquals(q, sut.remove(q));
        assertEquals(expected, sut.getAll());
    }


    @Test
    void remove_shouldThrowNoSuchElementExceptionIfProvidedQuestionIsNotInTheList() {
        assertThrows(NoSuchElementException.class, () -> sut.remove(new Question("Brainfuck?", "Yes")));
    }

    @Test
    void getAll_shouldReturnImmutableCollectionOfAllQuestions() {
        var actual = sut.getAll();
        var expected = new HashSet<>(javaQuestionsDummy);

        assertEquals(actual, expected);
        assertThrows(UnsupportedOperationException.class, () -> actual.add(new Question("a", "b")));
    }
    static class JavaRepositoryTestData {
        static List<Question> javaQuestionsDummy = List.of(
                new Question("Java?", "Yes"),
                new Question("How much primitive types there are in java?", "8"),
                new Question("How much bits \"int\" takes?", "32"),
                new Question("Do you need explicitly handle RuntimeException and it's children?", "No"),
                new Question("Is Map a collection?", "Yes, but no")
        );
    }
}