package org.example.comparator;

import org.example.improved.Car;
import java.util.Comparator;

/**
 * Компаратор для сортировки автомобилей по году производства.
 */
public class CarYearComparator implements Comparator<Car> {

    @Override
    public int compare(Car car1, Car car2) {
        return Integer.compare(car1.getManufactureYear(), car2.getManufactureYear());
    }

    @Override
    public String toString() {
        return "Сортировка по году производства";
    }
}