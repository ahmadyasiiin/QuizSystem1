package quizapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a quiz consisting of multiple questions.
 */
public class Quiz implements Gradable {

    private List<Question> questions;
    private int totalScore;

    public Quiz() {
        questions = new ArrayList<>();
        totalScore = 0;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void startQuiz() {
        Scanner scanner = new Scanner(System.in);

        for (Question question : questions) {
            System.out.println(question.getQuestionText());
            System.out.print("Your answer: ");
            String answer = scanner.nextLine();

            if (question.checkAnswer(answer)) {
                totalScore += question.getPoints();
                System.out.println("Correct!\n");
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
