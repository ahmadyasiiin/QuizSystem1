package quizapp.model;

/**
 * Doğru / yanlış tipindeki soruları temsil eder.
 * Kullanıcının verdiği cevap true veya false olarak kontrol edilir.
 */
public class TrueFalseQuestion extends Question {

    /** Sorunun doğru cevabı */
    private boolean correctAnswer;

    /**
     * Doğru-yanlış soru oluşturur.
     *
     * @param text soru metni
     * @param correctAnswer doğru cevap
     * @param difficulty sorunun zorluk seviyesi
     */
    public TrueFalseQuestion(String text, boolean correctAnswer, Difficulty difficulty) {
        super(text, difficulty);
        this.correctAnswer = correctAnswer;
    }

    /**
     * Kullanıcının verdiği cevabın doğru olup olmadığını kontrol eder.
     * Cevap String veya Boolean olarak değerlendirilebilir.
     *
     * @param answer kullanıcının cevabı
     * @return cevap doğruysa true, yanlışsa false
     */
    @Override
    public boolean checkAnswer(Object answer) {
        if (answer instanceof Boolean) {
            return correctAnswer == (Boolean) answer;
        }
        if (answer instanceof String) {
            return correctAnswer == Boolean.parseBoolean(answer.toString());
        }
        return false;
    }
}
