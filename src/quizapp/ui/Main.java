package quizapp.ui;

import quizapp.model.*;
import quizapp.util.ResultManager;

import java.util.Scanner;
import java.util.concurrent.*;

/**
 * Uygulamanın giriş noktasıdır.
 * Kullanıcıdan bilgi alır, quiz'i başlatır ve sonucu ekranda gösterir.
 * Sonuçlar ayrıca metin dosyasına kaydedilir.
 */
public class Main {

    /** Her soru için süre limiti (saniye). */
    private static final int TIME_LIMIT = 20;

    /**
     * Programı başlatır.
     *
     * @param args komut satırı argümanları
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("=================================");
        System.out.println("        QUIZ SİSTEMİ");
        System.out.println("=================================");

        // Önceki sonuçları göster
        ResultManager.printAllResults();

        System.out.print("\nAdınızı giriniz: ");
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

        int questionNumber = 1;

        for (Question question : quiz.getQuestions()) {

            System.out.println("----------------------------------");
            System.out.println("Soru " + questionNumber++);
            System.out.println(question.getText());
            System.out.println("Zorluk: " + question.getDifficulty()
                    + " | Puan: " + question.getDifficulty().getPoint());
            System.out.println("⏳ Süre: " + TIME_LIMIT + " saniye");

            String answer = getAnswerWithCountdown(scanner);

            if (answer == null) {
                System.out.println("\n❌ Süre doldu! Cevap alınamadı.");
            } else {
                quiz.answerQuestion(question, answer);
            }
        }

        int totalScore = quiz.calculateScore();
        student.addScore(totalScore);

        System.out.println("\n=================================");
        System.out.println("SONUÇ");
        System.out.println("=================================");
        System.out.println("Öğrenci: " + student.getName());
        System.out.println("Toplam Puan: " + student.getTotalScore());

        // Sonucu dosyaya kaydet
        ResultManager.saveResult(student.getName(), student.getTotalScore());

        scanner.close();
    }

    /**
     * Kullanıcıdan cevap alır ve aynı anda geri sayım gösterir.
     * Süre dolarsa null döner.
     *
     * @param scanner kullanıcı girişi için Scanner
     * @return kullanıcının cevabı, süre dolarsa null
     */
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
            } catch (InterruptedException ignored) {
                // süre dolduğunda veya giriş alındığında thread kesilebilir
            }
        };

        Future<String> futureAnswer = executor.submit(inputTask);
        executor.submit(countdownTask);

        try {
            return futureAnswer.get(TIME_LIMIT, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            return null;
        } catch (Exception e) {
            return null;
        } finally {
            executor.shutdownNow();
            System.out.println();
        }
    }

    /**
     * Seçilen zorluğa göre örnek soruları quiz'e ekler.
     *
     * @param quiz soru eklenecek quiz
     * @param difficulty seçilen zorluk seviyesi
     */
    private static void loadQuestions(Quiz quiz, Difficulty difficulty) {

        if (difficulty == Difficulty.EASY) {
            quiz.addQuestion(new TrueFalseQuestion(
                    "Java bir programlama dili midir?",
                    true,
                    difficulty));
            quiz.addQuestion(new TrueFalseQuestion(
                    "2 + 2 = 4 müdür?",
                    true,
                    difficulty));
        } else if (difficulty == Difficulty.MEDIUM) {
            quiz.addQuestion(new TrueFalseQuestion(
                    "Java’da interface vardır.",
                    true,
                    difficulty));
            quiz.addQuestion(new TrueFalseQuestion(
                    "String primitive bir veri tipi midir?",
                    false,
                    difficulty));
        } else { // HARD
            quiz.addQuestion(new TrueFalseQuestion(
                    "Java çoklu kalıtımı (multiple inheritance) sınıflarla destekler.",
                    false,
                    difficulty));
            quiz.addQuestion(new TrueFalseQuestion(
                    "JVM bytecode'u çalıştırır.",
                    true,
                    difficulty));
        }
    }
}
