package quizapp.ui;

import java.util.Arrays;
import java.util.Scanner;

import quizapp.model.MultipleChoiceQuestion;
import quizapp.model.Quiz;
import quizapp.model.Student;
import quizapp.model.TrueFalseQuestion;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Quiz quiz = new Quiz();

        quiz.addQuestion(
            new MultipleChoiceQuestion(
                "Which language is used for Android development?",
                10,
                Arrays.asList("Java", "Python", "C++", "Ruby"),
                "Java"
            )
        );

        quiz.addQuestion(
            new TrueFalseQuestion(
                "Java supports multiple inheritance using classes.",
                5,
                false
            )
        );

        Student student = new Student("Ahmad");

        student.takeQuiz(quiz, scanner);

        scanner.close();
    }
}
