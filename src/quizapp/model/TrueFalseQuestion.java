package quizapp.model;

/**
 * Represents a true/false question.
 */
public class TrueFalseQuestion extends Question {

    private boolean correctAnswer;

    public TrueFalseQuestion(String questionText, int points,
                             Difficulty difficulty,
                             boolean correctAnswer) {
        super(questionText, points, difficulty);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean checkAnswer(String answer) {
        return Boolean.parseBoolean(answer) == correctAnswer;
    }
}
