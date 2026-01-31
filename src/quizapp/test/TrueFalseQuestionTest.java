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
                        true,
                        Difficulty.EASY
                );

        assertTrue(question.checkAnswer("true"));
    }

    @Test
    void testWrongFalseAnswer() {

        TrueFalseQuestion question =
                new TrueFalseQuestion(
                        "Java supports multiple inheritance",
                        false,
                        Difficulty.MEDIUM
                );

        assertFalse(question.checkAnswer("true"));
    }
}
