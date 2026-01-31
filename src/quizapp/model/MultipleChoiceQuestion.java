package quizapp.model;

import java.util.List;

public class MultipleChoiceQuestion extends Question {

    private List<String> options;
    private String correctOption;

    public MultipleChoiceQuestion(String text, List<String> options,
                                  String correctOption, Difficulty difficulty) {
        super(text, difficulty);
        this.options = options;
        this.correctOption = correctOption;
    }

    public List<String> getOptions() {
        return options;
    }

    @Override
    public boolean checkAnswer(Object answer) {
        return correctOption.equalsIgnoreCase(answer.toString());
    }
}
