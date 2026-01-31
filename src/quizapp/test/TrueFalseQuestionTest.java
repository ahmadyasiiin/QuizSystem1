package quizapp.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import quizapp.model.Difficulty;
import quizapp.model.TrueFalseQuestion;

class TrueFalseQuestionTest {

    @Test
    void testCorrectTrueAnswer() {
        TrueFalseQuestion question =
            new TrueFalseQuestion(
                "Java is object oriented",
                5,
                Difficulty.EASY,
                true
            );

        assertTrue(question.checkAnswer("true"));
    }

    @Test
    void testWrongFalseAnswer() {
        TrueFalseQuestion question =
            new TrueFalseQuestion(
                "Java supports multiple inheritance",
                5,
                Difficulty.MEDIUM,
                false
            );

        assertFalse(question.checkAnswer("true"));
    }
}
