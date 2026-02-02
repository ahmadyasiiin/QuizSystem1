package quizapp.model;

import java.util.*;

/**
 * Quiz sınıfı, soruların tutulduğu ve
 * kullanıcının cevaplarının değerlendirildiği yapıdır.
 */
public class Quiz {

    /** Quiz başlığı */
    private String title;

    /** Quiz'e ait sorular */
    private List<Question> questions;

    /** Kullanıcının verdiği cevaplar */
    private Map<Question, Object> answers;

    /**
     * Quiz nesnesi oluşturur.
     *
     * @param title quiz başlığı
     */
    public Quiz(String title) {
        this.title = title;
        this.questions = new ArrayList<>();
        this.answers = new HashMap<>();
    }

    /**
     * Quiz başlığını döndürür.
     *
     * @return quiz başlığı
     */
    public String getTitle() {
        return title;
    }

    /**
     * Quiz'e yeni bir soru ekler.
     *
     * @param question eklenecek soru
     */
    public void addQuestion(Question question) {
        questions.add(question);
    }

    /**
     * Quiz'e ait tüm soruları döndürür.
     *
     * @return soru listesi
     */
    public List<Question> getQuestions() {
        return questions;
    }

    /**
     * Kullanıcının bir soruya verdiği cevabı kaydeder.
     *
     * @param question cevaplanan soru
     * @param answer kullanıcının cevabı
     */
    public void answerQuestion(Question question, Object answer) {
        answers.put(question, answer);
    }

    /**
     * Kullanıcının verdiği cevaplara göre toplam puanı hesaplar.
     *
     * @return toplam puan
     */
    public int calculateScore() {
        int score = 0;
        for (Question q : questions) {
            Object ans = answers.get(q);
            if (ans != null && q.checkAnswer(ans)) {
                score += q.getDifficulty().getPoint();
            }
        }
        return score;
    }

    /**
     * Soruların sırasını rastgele karıştırır.
     */
    public void shuffleQuestions() {
        Collections.shuffle(questions);
    }
}