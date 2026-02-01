package quizapp.model;

import java.util.List;

/**
 * Çoktan seçmeli soru tipini temsil eden sınıftır.
 * Soru için birden fazla seçenek ve doğru cevap içerir.
 */
public class MultipleChoiceQuestion extends Question {

    /** Soruya ait seçenekler */
    private List<String> options;

    /** Doğru seçenek */
    private String correctOption;

    /**
     * Çoktan seçmeli soru oluşturur.
     *
     * @param text soru metni
     * @param options cevap seçenekleri
     * @param correctOption doğru cevap
     * @param difficulty sorunun zorluk seviyesi
     */
    public MultipleChoiceQuestion(String text, List<String> options,
                                  String correctOption, Difficulty difficulty) {
        super(text, difficulty);
        this.options = options;
        this.correctOption = correctOption;
    }

    /**
     * Soruya ait tüm seçenekleri döndürür.
     *
     * @return cevap seçenekleri listesi
     */
    public List<String> getOptions() {
        return options;
    }

    /**
     * Kullanıcının verdiği cevabın doğru olup olmadığını kontrol eder.
     *
     * @param answer kullanıcının cevabı
     * @return cevap doğruysa true, yanlışsa false
     */
    @Override
    public boolean checkAnswer(Object answer) {
        return correctOption.equalsIgnoreCase(answer.toString());
    }
}
