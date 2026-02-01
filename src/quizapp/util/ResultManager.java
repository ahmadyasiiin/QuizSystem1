package quizapp.util;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Quiz sonuçlarının dosya üzerinde
 * kaydedilmesi ve okunmasından sorumlu yardımcı sınıftır.
 */
public class ResultManager {

    /** Sonuçların kaydedileceği dosya yolu */
    private static final String FILE_NAME = "results/results.txt";

    /**
     * Öğrencinin sonucunu metin dosyasına kaydeder.
     *
     * @param studentName öğrencinin adı
     * @param score öğrencinin aldığı puan
     */
    public static void saveResult(String studentName, int score) {

        try {
            File file = new File(FILE_NAME);
            file.getParentFile().mkdirs();

            BufferedWriter writer =
                    new BufferedWriter(new FileWriter(file, true));

            String time = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

            writer.write(studentName + " | " + score + " | " + time);
            writer.newLine();
            writer.close();

        } catch (IOException e) {
            System.out.println("Sonuç kaydedilemedi!");
        }
    }

    /**
     * Daha önce kaydedilmiş tüm quiz sonuçlarını ekrana yazdırır.
     */
    public static void printAllResults() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("Henüz kayıtlı sonuç yok.");
            return;
        }

        System.out.println("\n📜 Önceki Sonuçlar:");
        System.out.println("--------------------------------");

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(file))) {

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            System.out.println("Sonuçlar okunamadı!");
        }
    }
}
