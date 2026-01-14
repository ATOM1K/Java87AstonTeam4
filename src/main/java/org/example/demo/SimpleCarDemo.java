package org.example.demo;

import org.example.improved.Car;
import org.example.comparator.*;
import java.util.*;

/**
 * Простая демонстрация работы с автомобилями.
 */
public class SimpleCarDemo {
    public static void main(String[] args) {
        System.out.println("ПРОСТАЯ ДЕМОНСТРАЦИЯ\n");

        // Создаем список автомобилей
        List<Car> cars = new ArrayList<>();
        cars.add(Car.create("Toyota Camry", 203, 2020));
        cars.add(Car.create("BMW X5", 306, 2019));
        cars.add(Car.create("Audi A4", 190, 2021));
        cars.add(Car.create("Honda Civic", 158, 2018));
        cars.add(Car.create("Tesla Model 3", 283, 2022));

        System.out.println("Исходный список:");
        printCars(cars);

        System.out.println("\n1. Сортировка по мощности (возрастание):");
        cars.sort(new CarPowerComparator());
        printCars(cars);

        System.out.println("\n2. Сортировка по модели (алфавитный порядок):");
        cars.sort(new CarModelComparator());
        printCars(cars);

        System.out.println("\n3. Сортировка по году (от новых к старым):");
        cars.sort(Comparator.comparingInt(Car::getManufactureYear).reversed());
        printCars(cars);

        System.out.println("\n4. Базовая сортировка (год ↓, мощность ↓, модель ↑):");
        cars.sort(new BaseComparator());
        printCars(cars);
    }

    private static void printCars(List<Car> cars) {
        System.out.println("+-----+--------------------+------------+--------------+");
        System.out.println("|  #  |       Модель       | Мощность   |     Год      |");
        System.out.println("+-----+--------------------+------------+--------------+");

        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            System.out.printf("| %3d | %-18s | %6.0f л.с. |     %4d     |%n",
                    i + 1,
                    car.getModel().length() > 18 ? car.getModel().substring(0, 15) + "..." : car.getModel(),
                    car.getPower(),
                    car.getManufactureYear());
        }
        System.out.println("+-----+--------------------+------------+--------------+");
    }
}