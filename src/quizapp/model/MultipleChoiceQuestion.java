package quizapp.model;

import java.util.List;

/**
 * Represents a multiple choice question.
 */
public class MultipleChoiceQuestion extends Question {

    private List<String> options;
    private String correctOption;

    public MultipleChoiceQuestion(String questionText, int points,
                                  Difficulty difficulty,
                                  List<String> options,
                                  String correctOption) {
        super(questionText, points, difficulty);
        this.options = options;
        this.correctOption = correctOption;
    }

    public List<String> getOptions() {
        return options;
    }

    @Override
    public boolean checkAnswer(String answer) {
        return correctOption.equalsIgnoreCase(answer);
    }
}
