package quizapp.model;

/**
 * Abstract class representing a quiz question.
 */
public abstract class Question {

    protected String questionText;
    protected int points;
    protected Difficulty difficulty;

    public Question(String questionText, int points, Difficulty difficulty) {
        this.questionText = questionText;
        this.points = points;
        this.difficulty = difficulty;
    }

    public String getQuestionText() {
        return questionText;
    }

    public int getPoints() {
        return points;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public abstract boolean checkAnswer(String answer);
}
