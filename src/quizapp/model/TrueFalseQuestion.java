package quizapp.model;

public class TrueFalseQuestion extends Question {

    private boolean correctAnswer;

    public TrueFalseQuestion(String text, boolean correctAnswer, Difficulty difficulty) {
        super(text, difficulty);
        this.correctAnswer = correctAnswer;
    }

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
