package quizapp.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import quizapp.model.*;

class QuizTest {

    @Test
    void testQuizScoreCalculation() {

        Quiz quiz = new Quiz("Test Quiz");

        Question q1 = new TrueFalseQuestion(
                "Java is platform independent",
                true,
                Difficulty.EASY
        );

        Question q2 = new TrueFalseQuestion(
                "Java supports multiple inheritance",
                false,
                Difficulty.MEDIUM
        );

        quiz.addQuestion(q1);
        quiz.addQuestion(q2);

        // simulate correct answers
        quiz.answerQuestion(q1, "true");
        quiz.answerQuestion(q2, "false");

        int expectedScore =
                Difficulty.EASY.getPoint()
              + Difficulty.MEDIUM.getPoint();

        assertEquals(expectedScore, quiz.calculateScore());
    }
}
