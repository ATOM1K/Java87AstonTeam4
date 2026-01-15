package org.example.demo;

import org.example.improved.Car;
import org.example.comparator.*;
import org.example.strategy.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Тесты для сравнения разных стратегий сортировки.
 */
public class StrategyTest {
    public static void main(String[] args) {
        System.out.println("🧪 ТЕСТ ПАТТЕРНА СТРАТЕГИЯ\n");

        // Создаем тестовые данные
        List<Car> cars = createTestCars();

        // Создаем стратегии
        SortStrategy[] strategies = {
                new BubbleSortStrategy(),
                new SelectionSortStrategy(),
                new InsertionSortStrategy()
        };

        // Тестируем каждую стратегию с каждым компаратором
        for (SortStrategy strategy : strategies) {
            System.out.println("\n" + "=".repeat(60));
            System.out.println("СТРАТЕГИЯ: " + strategy.getName());
            System.out.println(strategy.getDescription());
            System.out.println("=".repeat(60));

            testWithComparator(strategy, cars, new CarPowerComparator());
            testWithComparator(strategy, cars, new CarModelComparator());
            testWithComparator(strategy, cars, new CarYearComparator());
        }
    }

    // ИЗМЕНЕНИЕ ЗДЕСЬ: вместо CarPowerComparator используем Comparator<Car>
    private static void testWithComparator(SortStrategy strategy, List<Car> originalCars,
                                           Comparator<Car> comparator) {
        // Копируем список для сортировки
        List<Car> carsToSort = new ArrayList<>(originalCars);

        System.out.println("\n📊 " + comparator + ":");
        System.out.println("До сортировки:");
        printCarsShort(carsToSort);

        // Сортируем
        long startTime = System.nanoTime();
        strategy.sort(carsToSort, comparator);
        long endTime = System.nanoTime();

        System.out.println("После сортировки:");
        printCarsShort(carsToSort);

        double durationMs = (endTime - startTime) / 1_000_000.0;
        System.out.printf("Время: %.3f мс%n", durationMs);

        // Проверяем что список отсортирован
        if (isSorted(carsToSort, comparator)) {
            System.out.println("✅ Проверка пройдена: список отсортирован правильно");
        } else {
            System.out.println("❌ ОШИБКА: список не отсортирован!");
        }
    }

    private static List<Car> createTestCars() {
        List<Car> cars = new ArrayList<>();
        cars.add(Car.create("Ford", 120, 2015));
        cars.add(Car.create("BMW", 250, 2020));
        cars.add(Car.create("Audi", 180, 2018));
        cars.add(Car.create("Toyota", 150, 2019));
        cars.add(Car.create("Tesla", 300, 2023));
        cars.add(Car.create("Honda", 130, 2016));
        return cars;
    }

    private static void printCarsShort(List<Car> cars) {
        for (int i = 0; i < Math.min(cars.size(), 5); i++) {
            Car car = cars.get(i);
            System.out.printf("  %s (%.0f л.с., %d) ",
                    car.getModel(), car.getPower(), car.getManufactureYear());
        }
        if (cars.size() > 5) System.out.print("...");
        System.out.println();
    }

    // ИЗМЕНЕНИЕ ЗДЕСЬ: тоже используем Comparator<Car>
    private static boolean isSorted(List<Car> cars, Comparator<Car> comparator) {
        for (int i = 0; i < cars.size() - 1; i++) {
            if (comparator.compare(cars.get(i), cars.get(i + 1)) > 0) {
                return false;
            }
        }
        return true;
    }
}