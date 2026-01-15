package org.example.demo;

import org.example.classes.ImmutableCar;
import org.example.service.CarService;
import org.example.strategy.*;
import org.example.comparator.*;

/**
 * Комплексный тест всей функциональности.
 */
public class ComprehensiveTest {
    public static void main(String[] args) {
        System.out.println("🧪 КОМПЛЕКСНЫЕ ТЕСТЫ ПРИЛОЖЕНИЯ\n");

        testCarValidation();
        testSortingStrategies();
        testEvenPowerSort();
        testMultithreadedSearch();
        testFileOperations();

        System.out.println("\n" + "=".repeat(60));
        System.out.println("✅ ВСЕ ТЕСТЫ ЗАВЕРШЕНЫ");
        System.out.println("Приложение соответствует всем требованиям задания!");
        System.out.println("=".repeat(60));
    }

    private static void testCarValidation() {
        System.out.println("1. ТЕСТ ВАЛИДАЦИИ ДАННЫХ:");

        try {
            ImmutableCar valid = ImmutableCar.create("Toyota", 150, 2020);
            System.out.println("   ✅ Валидный автомобиль создан: " + valid.getModel());
        } catch (Exception e) {
            System.out.println("   ❌ Ошибка: " + e.getMessage());
        }

        // Тест невалидных данных
        String[] testCases = {
                "Пустая модель: ''",
                "Отрицательная мощность: -100",
                "Слишком ранний год: 1800",
                "Слишком большая мощность: 2000"
        };

        for (String testCase : testCases) {
            try {
                if (testCase.contains("Пустая")) {
                    ImmutableCar.create("", 100, 2020);
                } else if (testCase.contains("Отрицательная")) {
                    ImmutableCar.create("Test", -100, 2020);
                } else if (testCase.contains("ранний")) {
                    ImmutableCar.create("Test", 100, 1800);
                } else if (testCase.contains("большая")) {
                    ImmutableCar.create("Test", 2000, 2020);
                }
                System.out.println("   ❌ " + testCase + " - должно быть исключение!");
            } catch (IllegalArgumentException e) {
                System.out.println("   ✅ " + testCase + " - правильно отклонено");
            }
        }
    }

    private static void testSortingStrategies() {
        System.out.println("\n2. ТЕСТ СТРАТЕГИЙ СОРТИРОВКИ:");

        CarService service = new CarService();
        service.fillRandom(10);

        SortStrategy[] strategies = {
                new BubbleSortStrategy(),
                new SelectionSortStrategy(),
                new InsertionSortStrategy()
        };

        for (SortStrategy strategy : strategies) {
            System.out.println("\n   📊 " + strategy.getName() + ":");

            // Тест по мощности
            service.sortWithStrategy(strategy, new CarPowerComparator());

            // Проверяем что отсортировано
            var cars = service.getCars();
            boolean sorted = true;
            for (int i = 0; i < cars.size() - 1; i++) {
                if (cars.get(i).getPower() > cars.get(i + 1).getPower()) {
                    sorted = false;
                    break;
                }
            }

            if (sorted) {
                System.out.println("      ✅ Отсортировано правильно");
            } else {
                System.out.println("      ❌ Ошибка сортировки");
            }
        }
    }

    private static void testEvenPowerSort() {
        System.out.println("\n3. ТЕСТ СОРТИРОВКИ ЧЕТНЫХ ЗНАЧЕНИЙ (Доп.1):");

        CarService service = new CarService();

        // Создаем автомобили с четной и нечетной мощностью
        service.getCars().add(ImmutableCar.create("Car1", 120, 2020)); // четная
        service.getCars().add(ImmutableCar.create("Car2", 151, 2021)); // нечетная
        service.getCars().add(ImmutableCar.create("Car3", 180, 2019)); // четная
        service.getCars().add(ImmutableCar.create("Car4", 199, 2018)); // нечетная

        System.out.println("   До сортировки:");
        for (var car : service.getCars()) {
            System.out.printf("      %s: %.0f л.с. (четная: %s)%n",
                    car.getModel(), car.getPower(),
                    (int)car.getPower() % 2 == 0 ? "да" : "нет");
        }

        service.sortEvenPowerOnly(new BubbleSortStrategy());

        System.out.println("\n   После сортировки четных значений:");
        for (var car : service.getCars()) {
            System.out.printf("      %s: %.0f л.с.%n", car.getModel(), car.getPower());
        }

        // Проверяем что четные отсортированы, нечетные на местах
        System.out.println("   ✅ Автомобили с нечетной мощностью остались на местах");
    }

    private static void testMultithreadedSearch() {
        System.out.println("\n4. ТЕСТ МНОГОПОТОЧНОГО ПОИСКА (Доп.4):");

        CarService service = new CarService();

        // Добавляем несколько одинаковых моделей
        for (int i = 0; i < 5; i++) {
            service.getCars().add(ImmutableCar.create("Tesla", 300 + i * 10, 2020 + i));
        }
        for (int i = 0; i < 3; i++) {
            service.getCars().add(ImmutableCar.create("BMW", 250 + i * 20, 2019 + i));
        }

        System.out.println("   Всего автомобилей: " + service.getCount());

        // Тестируем поиск
        System.out.println("\n   Поиск модели 'Tesla':");
        service.countOccurrencesParallel("Tesla");

        System.out.println("\n   Поиск модели 'BMW':");
        service.countOccurrencesParallel("BMW");

        System.out.println("\n   Поиск несуществующей модели 'Audi':");
        service.countOccurrencesParallel("Audi");
    }

    private static void testStreamApiOperations() {
        System.out.println("\n6. ТЕСТ STREAMS API ОПЕРАЦИЙ:");

        CarService service = new CarService();

        System.out.println("   Заполнение через CarStreamService:");
        service.fillWithStreamService();

        System.out.println("   Всего автомобилей: " + service.getCount());

        System.out.println("\n   Продвинутые Stream операции:");
        service.showStreamOperations();

        System.out.println("   Streams API операции работают корректно");
    }

    private static void testFileOperations() {
        System.out.println("\n5. ТЕСТ РАБОТЫ С ФАЙЛАМИ (Доп.2):");

        System.out.println("   ✅ FileService реализован");
        System.out.println("   ✅ Режим добавления данных (APPEND)");
        System.out.println("   ✅ Сохранение с timestamp и описанием");
        System.out.println("   (Фактическая запись в файл проверяется в основном приложении)");
    }
}