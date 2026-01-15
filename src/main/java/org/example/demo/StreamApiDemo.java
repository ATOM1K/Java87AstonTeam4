package org.example.demo;

import org.example.dopolnitelnoe3.*;
import org.example.improved.Car;
import java.util.List;
import java.util.stream.Stream;

/**
 * Демонстрация работы с Streams API (Доп. задание 3).
 */
public class StreamApiDemo {
    public static void main(String[] args) {
        System.out.println("ДЕМОНСТРАЦИЯ STREAMS API (Доп.3)\n");

        demoCarStreamService();
        demoStreamOperations();
        demoComparison();
    }

    private static void demoCarStreamService() {
        System.out.println("1. ИСПОЛЬЗОВАНИЕ CarStreamService:");

        CarStreamService streamService = new CarStreamService();

        // Создаем коллекцию через Stream
        CarCollection collection = streamService.createSampleCollection();

        System.out.println("   Создана коллекция через CarStreamService:");
        System.out.println("   Количество автомобилей: " + collection.size());

        // Показываем содержимое
        int i = 1;
        for (var car : collection) {
            System.out.printf("   %d. %s, %d л.с., %d год%n",
                    i++, car.getModel(), car.getPower(), car.getYearOfCreate());
        }
    }

    private static void demoStreamOperations() {
        System.out.println("\n2. ПРОДВИНУТЫЕ ОПЕРАЦИИ С STREAMS:");

        // Создаем поток данных CarData
        List<CarData> carDataList = List.of(
                new CarData("Toyota Camry", 200, 2020),
                new CarData("BMW X5", 300, 2019),
                new CarData("Audi A4", 180, 2021),
                new CarData("Mercedes C-Class", 250, 2022),
                new CarData("Toyota Camry", 220, 2020), // дубликат модели
                new CarData("BMW X5", 320, 2023)
        );

        CarStreamService streamService = new CarStreamService();

        // Преобразуем поток CarData в CarCollection
        CarCollection collection = streamService.createCarCollectionFromStream(carDataList.stream());

        System.out.println("   Исходные данные: " + carDataList.size() + " записей");
        System.out.println("   В коллекции: " + collection.size() + " автомобилей");

        // Демонстрация операций
        System.out.println("\n   АНАЛИЗ ДАННЫХ С STREAMS:");

        // Преобразуем в improved.Car для демонстрации
        var cars = collection.getCars().stream()
                .map(car -> Car.create(car.getModel(), car.getPower(), car.getYearOfCreate()))
                .toList();

        // 1. Группировка по модели
        System.out.println("   а) Группировка по модели:");
        var byModel = cars.stream()
                .collect(java.util.stream.Collectors.groupingBy(Car::getModel));

        byModel.forEach((model, carList) -> {
            System.out.printf("      - %s: %d автомобилей%n", model, carList.size());
        });

        // 2. Фильтрация
        System.out.println("\n   б) Автомобили мощнее 250 л.с.:");
        cars.stream()
                .filter(car -> car.getPower() > 250)
                .forEach(car -> System.out.printf("      - %s (%.0f л.с.)%n", car.getModel(), car.getPower()));

        // 3. Сортировка
        System.out.println("\n   в) Сортировка по году (новые сначала):");
        cars.stream()
                .sorted(java.util.Comparator.comparingInt(Car::getManufactureYear).reversed())
                .forEach(car -> System.out.printf("      - %s (%d год)%n", car.getModel(), car.getManufactureYear()));
    }

    private static void demoComparison() {
        System.out.println("\n3. СРАВНЕНИЕ С ОБЫЧНЫМ ПОДХОДОМ:");

        // Обычный подход
        System.out.println("   Обычный подход (без Streams):");
        CarCollection collection = new CarCollection();
        collection.add(new org.example.dopolnitelnoe3.Car("Toyota", 150, 2020));
        collection.add(new org.example.dopolnitelnoe3.Car("BMW", 250, 2021));

        int totalPower = 0;
        for (var car : collection) {
            totalPower += car.getPower();
        }
        System.out.println("      Суммарная мощность: " + totalPower + " л.с.");

        // Streams API подход
        System.out.println("\n   Streams API подход:");
        var carList = List.of(
                Car.create("Toyota", 150, 2020),
                Car.create("BMW", 250, 2021)
        );

        double streamTotalPower = carList.stream()
                .mapToDouble(Car::getPower)
                .sum();
        System.out.println("      Суммарная мощность: " + streamTotalPower + " л.с.");

        System.out.println("\n   ✅ Streams API позволяет писать более компактный и читаемый код");
    }
}