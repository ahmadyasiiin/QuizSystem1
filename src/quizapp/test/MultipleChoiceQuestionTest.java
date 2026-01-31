package quizapp.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import quizapp.model.Difficulty;
import quizapp.model.MultipleChoiceQuestion;

class MultipleChoiceQuestionTest {

    @Test
    void testCorrectAnswer() {

        List<String> options =
                Arrays.asList("Paris", "London", "Berlin");

        MultipleChoiceQuestion question =
                new MultipleChoiceQuestion(
                        "Capital of France?",
                        options,
                        "Paris",
                        Difficulty.EASY
                );

        assertTrue(question.checkAnswer("Paris"));
    }

    @Test
    void testWrongAnswer() {

        List<String> options =
                Arrays.asList("Paris", "London", "Berlin");

        MultipleChoiceQuestion question =
                new MultipleChoiceQuestion(
                        "Capital of France?",
                        options,
                        "Paris",
                        Difficulty.EASY
                );

        assertFalse(question.checkAnswer("London"));
    }
}
