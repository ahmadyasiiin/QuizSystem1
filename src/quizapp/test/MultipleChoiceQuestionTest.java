package quizapp.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import quizapp.model.Difficulty;
import quizapp.model.MultipleChoiceQuestion;

class MultipleChoiceQuestionTest {

    @Test
    void testCorrectAnswer() {
        MultipleChoiceQuestion question =
            new MultipleChoiceQuestion(
                "Capital of France?",
                10,
                Difficulty.EASY,
                Arrays.asList("Paris", "London", "Berlin"),
                "Paris"
            );

        assertTrue(question.checkAnswer("Paris"));
    }

    @Test
    void testWrongAnswer() {
        MultipleChoiceQuestion question =
            new MultipleChoiceQuestion(
                "Capital of France?",
                10,
                Difficulty.EASY,
                Arrays.asList("Paris", "London", "Berlin"),
                "Paris"
            );

        assertFalse(question.checkAnswer("London"));
    }
}
