package quizapp.ui;

import java.util.Arrays;
import java.util.Scanner;

import quizapp.model.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        Quiz quiz = new Quiz(30); // 30 seconds timer

        quiz.addQuestion(
            new MultipleChoiceQuestion(
                "Which language is used for Android development?",
                10,
                Difficulty.EASY,
                Arrays.asList("Java", "Python", "C++", "Ruby"),
                "Java"
            )
        );

        quiz.addQuestion(
            new TrueFalseQuestion(
                "Java supports multiple inheritance using classes.",
                15,
                Difficulty.MEDIUM,
                false
            )
        );

        quiz.addQuestion(
            new TrueFalseQuestion(
                "JVM is platform dependent.",
                20,
                Difficulty.HARD,
                false
            )
        );

        Student student = new Student(name);
        student.takeQuiz(quiz, scanner);

        scanner.close();
    }
}
