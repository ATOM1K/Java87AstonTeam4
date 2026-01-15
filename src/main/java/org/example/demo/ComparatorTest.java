package org.example.demo;

import org.example.comparator.*;
import org.example.classes.ImmutableCar;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComparatorTest {
    public static void main(String[] args) {
        System.out.println("🧪 ТЕСТ КОМПАРАТОРОВ\n");

        // Создаем тестовые автомобили
        List<ImmutableCar> cars = new ArrayList<>();
        cars.add(ImmutableCar.create("BMW", 250, 2020));
        cars.add(ImmutableCar.create("Audi", 200, 2022));
        cars.add(ImmutableCar.create("Toyota", 150, 2018));
        cars.add(ImmutableCar.create("Tesla", 300, 2023));

        System.out.println("Исходный список:");
        printCars(cars);

        // Тест сортировки по мощности
        System.out.println("\n1. " + new CarPowerComparator() + ":");
        List<ImmutableCar> sortedByPower = new ArrayList<>(cars);
        Collections.sort(sortedByPower, new CarPowerComparator());
        printCars(sortedByPower);

        // Тест сортировки по модели
        System.out.println("\n2. " + new CarModelComparator() + ":");
        List<ImmutableCar> sortedByModel = new ArrayList<>(cars);
        Collections.sort(sortedByModel, new CarModelComparator());
        printCars(sortedByModel);

        // Тест сортировки по году
        System.out.println("\n3. " + new CarYearComparator() + ":");
        List<ImmutableCar> sortedByYear = new ArrayList<>(cars);
        Collections.sort(sortedByYear, new CarYearComparator());
        printCars(sortedByYear);
    }

    static void printCars(List<ImmutableCar> cars) {
        for (int i = 0; i < cars.size(); i++) {
            ImmutableCar car = cars.get(i);
            System.out.printf("  %d. %s (%.0f л.с., %d год)%n",
                    i + 1, car.getModel(), car.getPower(), car.getManufactureYear());
        }
    }
}