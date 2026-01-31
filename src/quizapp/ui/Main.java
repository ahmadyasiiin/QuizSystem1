package quizapp.ui;

import quizapp.model.*;

import java.util.Scanner;
import java.util.concurrent.*;

public class Main {

    private static final int TIME_LIMIT = 20;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("=================================");
        System.out.println("        QUIZ SİSTEMİ");
        System.out.println("=================================\n");

        System.out.print("Adınızı giriniz: ");
        String name = scanner.nextLine();
        Student student = new Student(name);

        System.out.println("\nZorluk Seviyesi Seçiniz:");
        System.out.println("1 - EASY   (" + Difficulty.EASY.getPoint() + " puan)");
        System.out.println("2 - MEDIUM (" + Difficulty.MEDIUM.getPoint() + " puan)");
        System.out.println("3 - HARD   (" + Difficulty.HARD.getPoint() + " puan)");
        System.out.print("Seçiminiz: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Difficulty difficulty = switch (choice) {
            case 2 -> Difficulty.MEDIUM;
            case 3 -> Difficulty.HARD;
            default -> Difficulty.EASY;
        };

        Quiz quiz = new Quiz("Genel Bilgi Quiz");
        loadQuestions(quiz, difficulty);
        quiz.shuffleQuestions();

        System.out.println("\nQuiz başlıyor...\n");

        int number = 1;

        for (Question q : quiz.getQuestions()) {

            System.out.println("----------------------------------");
            System.out.println("Soru " + number++);
            System.out.println(q.getText());
            System.out.println("Zorluk: " + q.getDifficulty()
                    + " | Puan: " + q.getDifficulty().getPoint());
            System.out.println("⏳ Süre: " + TIME_LIMIT + " saniye");

            String answer = getAnswerWithCountdown(scanner);

            if (answer == null) {
                System.out.println("\n❌ Süre doldu! Puan alamadınız.");
            } else {
                quiz.answerQuestion(q, answer);
            }
        }

        int score = quiz.calculateScore();
        student.addScore(score);

        System.out.println("\n=================================");
        System.out.println("SONUÇ");
        System.out.println("=================================");
        System.out.println("Öğrenci: " + student.getName());
        System.out.println("Toplam Puan: " + student.getTotalScore());

        scanner.close();
    }

    // ⏳ إدخال + عدّ تنازلي مرئي
    private static String getAnswerWithCountdown(Scanner scanner) {

        ExecutorService executor = Executors.newFixedThreadPool(2);

        Callable<String> inputTask = () -> {
            System.out.print("\nCevabınız (true/false): ");
            return scanner.nextLine();
        };

        Runnable countdownTask = () -> {
            try {
                for (int i = TIME_LIMIT; i >= 1; i--) {
                    System.out.print("\r⏳ Kalan süre: " + i + " saniye ");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ignored) {}
        };

        Future<String> answerFuture = executor.submit(inputTask);
        executor.submit(countdownTask);

        try {
            return answerFuture.get(TIME_LIMIT, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            return null;
        } catch (Exception e) {
            return null;
        } finally {
            executor.shutdownNow();
            System.out.println();
        }
    }

    private static void loadQuestions(Quiz quiz, Difficulty d) {

        if (d == Difficulty.EASY) {
            quiz.addQuestion(new TrueFalseQuestion(
                    "Java bir programlama dili midir?", true, d));
            quiz.addQuestion(new TrueFalseQuestion(
                    "2 + 2 = 4 müdür?", true, d));
        }

        if (d == Difficulty.MEDIUM) {
            quiz.addQuestion(new TrueFalseQuestion(
                    "Java’da interface vardır.", true, d));
            quiz.addQuestion(new TrueFalseQuestion(
                    "String primitive bir veri tipi midir?", false, d));
        }

        if (d == Difficulty.HARD) {
            quiz.addQuestion(new TrueFalseQuestion(
                    "Java çoklu kalıtımı destekler.", false, d));
            quiz.addQuestion(new TrueFalseQuestion(
                    "JVM bytecode’u çalıştırır.", true, d));
        }
    }
}
