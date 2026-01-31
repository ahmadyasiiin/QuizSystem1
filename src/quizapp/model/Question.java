package quizapp.model;

public abstract class Question {

    protected String text;
    protected Difficulty difficulty;

    public Question(String text, Difficulty difficulty) {
        this.text = text;
        this.difficulty = difficulty;
    }

    public String getText() {
        return text;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public abstract boolean checkAnswer(Object answer);
}
