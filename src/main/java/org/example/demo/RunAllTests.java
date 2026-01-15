package org.example.demo;

public class RunAllTests {
    public static void main(String[] args) {
        System.out.println("ЗАПУСК ВСЕХ ТЕСТОВ ПРИЛОЖЕНИЯ");
        System.out.println("==================================================");

        try {
            System.out.println("\n1. Запуск CarTest...");
            CarTest.main(args);

            System.out.println("\n2. Запуск ComparatorTest...");
            ComparatorTest.main(args);

            System.out.println("\n3. Запуск ComprehensiveTest...");
            ComprehensiveTest.main(args);

            System.out.println("\n4. Запуск IntegrationTest...");
            IntegrationTest.main(args);

            System.out.println("\n5. Запуск StrategyTest...");
            StrategyTest.main(args);

            System.out.println("\n==================================================");
            System.out.println("ВСЕ ТЕСТЫ УСПЕШНО ЗАВЕРШЕНЫ!");
            System.out.println("Приложение готово к использованию.");

        } catch (Exception e) {
            System.err.println("Ошибка при запуске тестов: " + e.getMessage());
            e.printStackTrace();
        }
    }
}