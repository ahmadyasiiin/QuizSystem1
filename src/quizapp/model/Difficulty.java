package quizapp.model;

/**
 * Soruların zorluk seviyelerini temsil eden enum yapısıdır.
 * Her zorluk seviyesi farklı bir puan değerine sahiptir.
 */
public enum Difficulty {

    /** Kolay seviye (düşük puan) */
    EASY(5),

    /** Orta seviye */
    MEDIUM(10),

    /** Zor seviye (yüksek puan) */
    HARD(20);

    /** Zorluk seviyesine ait puan değeri */
    private final int point;

    /**
     * Difficulty enum kurucusu.
     *
     * @param point zorluk seviyesine ait puan
     */
    Difficulty(int point) {
        this.point = point;
    }

    /**
     * Zorluk seviyesine ait puanı döndürür.
     *
     * @return puan değeri
     */
    public int getPoint() {
        return point;
    }
}
