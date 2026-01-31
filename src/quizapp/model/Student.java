package quizapp.model;

public class Student {

    private String name;
    private int totalScore;

    public Student(String name) {
        this.name = name;
        this.totalScore = 0;
    }

    public String getName() {
        return name;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void addScore(int score) {
        this.totalScore += score;
    }
}
