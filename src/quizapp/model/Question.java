package quizapp.model;

/**
 * Abstract class representing a quiz question.
 */
public abstract class Question {

    protected String questionText;
    protected int points;

    public Question(String questionText, int points) {
        this.questionText = questionText;
        this.points = points;
    }

    public String getQuestionText() {
        return questionText;
    }

    public int getPoints() {
        return points;
    }

    public abstract boolean checkAnswer(String answer);
}
