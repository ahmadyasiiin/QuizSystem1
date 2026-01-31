package quizapp.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import quizapp.model.*;

class QuizTest {

    @Test
    void testQuizScoreCalculation() {

        Quiz quiz = new Quiz(60);

        quiz.addQuestion(
            new TrueFalseQuestion(
                "Java is platform independent",
                10,
                Difficulty.EASY,
                true
            )
        );

        quiz.addQuestion(
            new TrueFalseQuestion(
                "Java supports multiple inheritance",
                20,
                Difficulty.MEDIUM,
                false
            )
        );

        // Simulate user input
        String fakeInput = "true\nfalse\n";
        Scanner scanner =
            new Scanner(new ByteArrayInputStream(fakeInput.getBytes()));

        quiz.startQuiz(scanner);

        assertEquals(30, quiz.getScore());
    }
}
