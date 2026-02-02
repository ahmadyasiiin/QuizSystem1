package quizapp.ui;

import quizapp.model.*;
import quizapp.util.ResultManager;

import java.util.List;
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

        // Önceki sonuçları göster (program açılışında bir kez)
        ResultManager.printAllResults();

        System.out.print("\nAdınızı giriniz: ");
        String name = scanner.nextLine();
        Student student = new Student(name);

        boolean playAgain = true;

        while (playAgain) {

            Difficulty difficulty = askDifficulty(scanner);

            Quiz quiz = new Quiz("Genel Bilgi Quiz");
            loadQuestions(quiz, difficulty);
            quiz.shuffleQuestions();

            System.out.println("\nQuiz başlıyor...\n");

            runQuiz(scanner, quiz);

            int totalScore = quiz.calculateScore();
            student.addScore(totalScore);

            System.out.println("\n=================================");
            System.out.println("SONUÇ");
            System.out.println("=================================");
            System.out.println("Öğrenci: " + student.getName());
            System.out.println("Bu Quiz Puanı: " + totalScore);
            System.out.println("Toplam Puan: " + student.getTotalScore());

            // Sonucu dosyaya kaydet
            ResultManager.saveResult(student.getName(), totalScore);

            playAgain = askPlayAgain(scanner);
        }

        System.out.println("\nProgram kapatılıyor...");
        scanner.close();
    }

    /**
     * Kullanıcıdan zorluk seviyesi seçmesini ister.
     */
    private static Difficulty askDifficulty(Scanner scanner) {

        System.out.println("\nZorluk Seviyesi Seçiniz:");
        System.out.println("1 - EASY   (" + Difficulty.EASY.getPoint() + " puan)");
        System.out.println("2 - MEDIUM (" + Difficulty.MEDIUM.getPoint() + " puan)");
        System.out.println("3 - HARD   (" + Difficulty.HARD.getPoint() + " puan)");
        System.out.print("Seçiminiz: ");

        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine().trim());
        } catch (Exception e) {
            choice = 1;
        }

        return switch (choice) {
            case 2 -> Difficulty.MEDIUM;
            case 3 -> Difficulty.HARD;
            default -> Difficulty.EASY;
        };
    }

    /**
     * Quiz sorularını sırayla sorar. Her sorudan sonra doğru/yanlış bilgisini gösterir.
     */
    private static void runQuiz(Scanner scanner, Quiz quiz) {

        int questionNumber = 1;

        for (Question question : quiz.getQuestions()) {

            System.out.println("----------------------------------");
            System.out.println("Soru " + questionNumber++);
            System.out.println(question.getText());
            System.out.println("Zorluk: " + question.getDifficulty()
                    + " | Puan: " + question.getPoints());
            System.out.println("⏳ Süre: " + TIME_LIMIT + " saniye");

            // Çoktan seçmeli soru ise seçenekleri göster
            if (question instanceof MultipleChoiceQuestion mcq) {
                List<String> opts = mcq.getOptions();
                for (int i = 0; i < opts.size(); i++) {
                    System.out.println((i + 1) + ") " + opts.get(i));
                }
            }

            String answer = getAnswerWithCountdown(scanner, question);

            if (answer == null) {
                System.out.println("\n❌ Süre doldu! Cevap alınamadı. (0 puan)");
                continue;
            }

            // Cevabı kaydet
            quiz.answerQuestion(question, answer);

            // Anında doğru/yanlış göster
            boolean correct = question.checkAnswer(answer);
            if (correct) {
                System.out.println("✅ Doğru! (+" + question.getPoints() + " puan)");
            } else {
                System.out.println("❌ Yanlış! (+0 puan)");
            }
        }
    }

    /**
     * Kullanıcıdan cevap alır ve aynı anda geri sayım gösterir.
     * Süre dolarsa null döner.
     */
    private static String getAnswerWithCountdown(Scanner scanner, Question question) {

        ExecutorService executor = Executors.newFixedThreadPool(2);

        Callable<String> inputTask = () -> {
            if (question instanceof MultipleChoiceQuestion) {
                System.out.print("\nCevabınız (seçenek yazın veya 1/2/3...): ");
            } else {
                System.out.print("\nCevabınız (true/false): ");
            }
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
            String raw = futureAnswer.get(TIME_LIMIT, TimeUnit.SECONDS);
            System.out.println();

            // Eğer çoktan seçmeli ise "1/2/3" girilirse seçeneğe çevir
            if (question instanceof MultipleChoiceQuestion mcq) {
                String trimmed = raw == null ? "" : raw.trim();
                if (trimmed.matches("\\d+")) {
                    int idx = Integer.parseInt(trimmed) - 1;
                    List<String> opts = mcq.getOptions();
                    if (idx >= 0 && idx < opts.size()) {
                        return opts.get(idx);
                    }
                }
            }

            return raw;
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
     * Kullanıcıya tekrar quiz çözmek isteyip istemediğini sorar.
     */
    private static boolean askPlayAgain(Scanner scanner) {
        System.out.print("\nTekrar quiz çözmek ister misiniz? (e/h): ");
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("e") || input.equals("evet") || input.equals("y") || input.equals("yes");
    }

    /**
     * Seçilen zorluğa göre örnek soruları quiz'e ekler.
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
