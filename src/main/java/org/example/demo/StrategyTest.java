package org.example.demo;

import org.example.improved.Car;
import org.example.strategy.*;
import org.example.comparator.CarPowerComparator;
import java.util.*;

/**
 * Тесты для сравнения разных стратегий сортировки.
 */
public class StrategyTest {
    public static void main(String[] args) {
        System.out.println("⚡ ТСТЫ СТТ СТ\n");
        
        // Создаем тестовые данные
        List<Car> testData = createTestData(100);
        
        // Тестируем каждую стратегию
        testStrategy(new BubbleSortStrategy(), "узырьковая", testData);
        testStrategy(new SelectionSortStrategy(), "ыбором", testData);
        testStrategy(new InsertionSortStrategy(), "ставками", testData);
        testStrategy(new EvenYearSort(), "етных годов", testData);
        
        comparePerformance();
    }
    
    private static List<Car> createTestData(int count) {
        List<Car> cars = new ArrayList<>();
        String[] models = {"Toyota", "BMW", "Audi", "Mercedes", "Ford", "Honda"};
        Random random = new Random();
        
        for (int i = 0; i < count; i++) {
            String model = models[random.nextInt(models.length)];
            double power = 100 + random.nextDouble() * 400;
            int year = 2000 + random.nextInt(25);
            cars.add(Car.create(model, power, year));
        }
        
        return cars;
    }
    
    private static void testStrategy(SortStrategy strategy, String name, List<Car> originalData) {
        System.out.println("🔍 Тест: " + name);
        System.out.println("   писание: " + strategy.getDescription());
        
        List<Car> testList = new ArrayList<>(originalData);
        long startTime = System.nanoTime();
        
        strategy.sort(testList, new CarPowerComparator());
        
        long endTime = System.nanoTime();
        double durationMs = (endTime - startTime) / 1_000_000.0;
        
        // роверяем что список отсортирован
        boolean sorted = true;
        for (int i = 0; i < testList.size() - 1; i++) {
            if (testList.get(i).getPower() > testList.get(i + 1).getPower()) {
                sorted = false;
                break;
            }
        }
        
        System.out.println("   езультат: " + (sorted ? "✅ тсортирован правильно" : "❌ шибка сортировки"));
        System.out.println("   ремя: " + String.format("%.2f", durationMs) + " мс");
        System.out.println();
    }
    
    private static void comparePerformance() {
        System.out.println("\n📊 С ТЬСТ:");
        
        int[] sizes = {50, 100, 200, 500};
        SortStrategy[] strategies = {
            new BubbleSortStrategy(),
            new SelectionSortStrategy(),
            new InsertionSortStrategy()
        };
        
        System.out.println("   азмер | " + 
            String.join(" | ", Arrays.stream(strategies).map(s -> s.getName()).toArray(String[]::new)));
        System.out.println("   " + "-".repeat(70));
        
        for (int size : sizes) {
            System.out.print("   " + String.format("%6d", size) + " | ");
            
            for (SortStrategy strategy : strategies) {
                List<Car> testData = createTestData(size);
                long startTime = System.nanoTime();
                strategy.sort(testData, new CarPowerComparator());
                long endTime = System.nanoTime();
                double ms = (endTime - startTime) / 1_000_000.0;
                
                System.out.print(String.format("%9.2f мс", ms) + " | ");
            }
            System.out.println();
        }
    }
}
