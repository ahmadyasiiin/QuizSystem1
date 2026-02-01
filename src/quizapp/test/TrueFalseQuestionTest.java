package quizapp.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import quizapp.model.Difficulty;
import quizapp.model.TrueFalseQuestion;

/**
 * TrueFalseQuestion sınıfı için yazılmış birim testlerdir.
 * Soruların doğru ve yanlış cevapları doğru şekilde
 * kontrol edip etmediği test edilir.
 */
class TrueFalseQuestionTest {

    /**
     * Doğru cevabın true olduğu durum test edilir.
     */
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

    /**
     * Yanlış cevap verilen durum test edilir.
     */
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
