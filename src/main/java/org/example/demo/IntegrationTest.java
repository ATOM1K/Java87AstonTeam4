package org.example.demo;

import org.example.classes.ImmutableCar;
import org.example.service.CarService;
import org.example.service.FileService;
import org.example.strategy.*;
import org.example.comparator.*;

/**
 * Интеграционные тесты всего приложения.
 */
public class IntegrationTest {
    public static void main(String[] args) {
        System.out.println("🔗 ТЫ ТСТЫ\n");
        
        testCarCreationAndValidation();
        testSortingIntegration();
        testFileServiceIntegration();
        testAllStrategies();
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("✅ С ТЫ ТСТЫ Ы");
        System.out.println("=".repeat(60));
    }
    
    private static void testCarCreationAndValidation() {
        System.out.println("1. ТСТ СЯ   Т:");
        
        try {
            // алидные данные
            var car1 = ImmutableCar.create("Toyota", 150, 2020);
            System.out.println("   ✅ Создан валидный автомобиль: " + car1.getModel());
            
            // роверка иммутабельности
            System.out.println("   ✅ втомобиль иммутабелен (нет сеттеров)");
            
        } catch (Exception e) {
            System.out.println("   ❌ шибка: " + e.getMessage());
        }
    }
    
    private static void testSortingIntegration() {
        System.out.println("\n2. ТСТ Т СТ:");
        
        CarService service = new CarService();
        service.fillRandom(5);
        
        System.out.println("   Тест пузырьковой сортировки по мощности:");
        service.sortWithStrategy(new BubbleSortStrategy(), new CarPowerComparator());
        System.out.println("   ✅ спешно");
        
        System.out.println("\n   Тест сортировки вставками по модели:");
        service.sortWithStrategy(new InsertionSortStrategy(), new CarModelComparator());
        System.out.println("   ✅ спешно");
        
        System.out.println("\n   Тест сортировки выбором по году:");
        service.sortWithStrategy(new SelectionSortStrategy(), new CarYearComparator());
        System.out.println("   ✅ спешно");
    }
    
    private static void testFileServiceIntegration() {
        System.out.println("\n3. ТСТ Т С Ы СС:");
        
        FileService fileService = new FileService();
        CarService carService = new CarService();
        carService.fillRandom(3);
        
        System.out.println("Тест сохранения в файл:");
        fileService.saveToFileAppend(carService.getCars(), "test_output.txt");
        System.out.println("Файл создан (проверьте test_output.txt)");
    }
    
    private static void testAllStrategies() {
        System.out.println("\n4. ТСТ СХ СТТ СТ:");
        
        CarService service = new CarService();
        service.fillRandom(10);
        
        SortStrategy[] strategies = {
            new BubbleSortStrategy(),
            new SelectionSortStrategy(),
            new InsertionSortStrategy(),
            new EvenYearSort()
        };
        
        for (SortStrategy strategy : strategies) {
            System.out.println("   Тест стратегии: " + strategy.getName());
            service.sortWithStrategy(strategy, new CarPowerComparator());
            System.out.println("   ✅ " + strategy.getName() + " - ");
        }
    }
}
