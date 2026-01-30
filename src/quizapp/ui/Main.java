package quizapp.ui;

import java.util.Arrays;

import quizapp.model.MultipleChoiceQuestion;
import quizapp.model.Quiz;
import quizapp.model.Student;
import quizapp.model.TrueFalseQuestion;

public class Main {

    public static void main(String[] args) {

        // Create quiz
        Quiz quiz = new Quiz();

        // Add questions
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

        // Create student
        Student student = new Student("Ahmad");

        // Start quiz
        student.takeQuiz(quiz);
    }
}
