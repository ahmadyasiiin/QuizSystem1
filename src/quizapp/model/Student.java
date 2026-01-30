package quizapp.model;

import java.util.Scanner;

/**
 * Represents a student who can take quizzes.
 */
public class Student {

    private String name;

    public Student(String name) {
        this.name = name;
    }

    public void takeQuiz(Quiz quiz, Scanner scanner) {
        quiz.startQuiz(scanner);
        System.out.println("Student: " + name);
        System.out.println("Total Score: " + quiz.getScore());
    }
}
