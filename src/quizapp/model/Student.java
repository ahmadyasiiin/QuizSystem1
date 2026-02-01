package quizapp.model;

/**
 * Student sınıfı, quiz'i çözen öğrenciyi temsil eder.
 * Öğrencinin adı ve toplam puanı bu sınıfta tutulur.
 */
public class Student {

    /** Öğrencinin adı */
    private String name;

    /** Öğrencinin toplam puanı */
    private int totalScore;

    /**
     * Student nesnesi oluşturur.
     *
     * @param name öğrencinin adı
     */
    public Student(String name) {
        this.name = name;
        this.totalScore = 0;
    }

    /**
     * Öğrencinin adını döndürür.
     *
     * @return öğrenci adı
     */
    public String getName() {
        return name;
    }

    /**
     * Öğrencinin toplam puanını döndürür.
     *
     * @return toplam puan
     */
    public int getTotalScore() {
        return totalScore;
    }

    /**
     * Öğrencinin puanına yeni puan ekler.
     *
     * @param score eklenecek puan
     */
    public void addScore(int score) {
        this.totalScore += score;
    }
}
