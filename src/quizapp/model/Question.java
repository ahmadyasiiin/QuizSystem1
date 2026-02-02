package quizapp.model;

/**
 * Bu soyut sınıf, tüm soru tipleri için temel yapıyı temsil eder.
 * Her soru bir metne ve zorluk seviyesine sahiptir.
 */
public abstract class Question implements Gradable {

    /** Soru metni */
    protected String text;

    /** Sorunun zorluk seviyesi */
    protected Difficulty difficulty;

    /**
     * Question sınıfı için kurucu metot.
     *
     * @param text soru metni
     * @param difficulty sorunun zorluk seviyesi
     */
    public Question(String text, Difficulty difficulty) {
        this.text = text;
        this.difficulty = difficulty;
    }

    /**
     * Soru metnini döndürür.
     *
     * @return soru metni
     */
    public String getText() {
        return text;
    }

    /**
     * Sorunun zorluk seviyesini döndürür.
     *
     * @return zorluk seviyesi
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

    /**
     * Sorunun puanını döndürür.
     * Puan değeri Difficulty üzerinden alınır.
     *
     * @return soru puanı
     */
    @Override
    public int getPoints() {
        return difficulty.getPoint();
    }

    /**
     * Kullanıcının verdiği cevabın doğru olup olmadığını kontrol eder.
     * Alt sınıflar tarafından uygulanır.
     *
     * @param answer kullanıcının cevabı
     * @return cevap doğruysa true, yanlışsa false
     */
    public abstract boolean checkAnswer(Object answer);
}
