package quizapp.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import quizapp.model.*;

/**
 * Quiz sınıfı için yazılmış birim testleri içerir.
 * Quiz puan hesaplama mantığının doğru çalışıp
 * çalışmadığı bu sınıfta test edilir.
 */
class QuizTest {

    /**
     * Tüm sorular doğru cevaplandığında
     * toplam puanın doğru hesaplanıp hesaplanmadığı test edilir.
     */
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

        // doğru cevaplar simüle edilir
        quiz.answerQuestion(q1, "true");
        quiz.answerQuestion(q2, "false");

        int expectedScore =
                Difficulty.EASY.getPoint()
              + Difficulty.MEDIUM.getPoint();

        assertEquals(expectedScore, quiz.calculateScore());
    }
}
