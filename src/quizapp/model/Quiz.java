package quizapp.model;

import java.util.*;

public class Quiz {

    private String title;
    private List<Question> questions;
    private Map<Question, Object> answers;

    public Quiz(String title) {
        this.title = title;
        this.questions = new ArrayList<>();
        this.answers = new HashMap<>();
    }

    public String getTitle() {
        return title;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void answerQuestion(Question question, Object answer) {
        answers.put(question, answer);
    }

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

    public void shuffleQuestions() {
        Collections.shuffle(questions);
    }
}
