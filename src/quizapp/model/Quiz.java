package quizapp.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a quiz consisting of multiple questions.
 */
public class Quiz implements Gradable {

    private List<Question> questions;
    private int totalScore;
    private long timeLimitMillis;

    public Quiz(long timeLimitSeconds) {
        questions = new ArrayList<>();
        totalScore = 0;
        this.timeLimitMillis = timeLimitSeconds * 1000;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void startQuiz(Scanner scanner) {

        Collections.shuffle(questions); // 🔀 shuffle

        long startTime = System.currentTimeMillis();

        for (Question question : questions) {

            long elapsedTime = System.currentTimeMillis() - startTime;
            if (elapsedTime > timeLimitMillis) {
                System.out.println("\n⏰ Time is up!");
                break;
            }

            System.out.println("[" + question.getDifficulty() + "] "
                    + question.getQuestionText());
            System.out.print("Your answer: ");

            String answer = scanner.nextLine();

            if (question.checkAnswer(answer)) {
                totalScore += question.getPoints();
                System.out.println("Correct! +" + question.getPoints() + "\n");
            } else {
                System.out.println("Wrong!\n");
            }
        }
    }

    @Override
    public int getScore() {
        return totalScore;
    }
}
