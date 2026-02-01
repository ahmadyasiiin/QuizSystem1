package quizapp.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import quizapp.model.Difficulty;
import quizapp.model.MultipleChoiceQuestion;

/**
 * MultipleChoiceQuestion sınıfı için yazılmış birim testlerdir.
 * Doğru ve yanlış cevapların doğru şekilde
 * kontrol edilip edilmediği test edilir.
 */
class MultipleChoiceQuestionTest {

    /**
     * Doğru cevap verildiğinde true dönmesi test edilir.
     */
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

    /**
     * Yanlış cevap verildiğinde false dönmesi test edilir.
     */
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
