package org.example.comparator;

import org.example.classes.Car;
import org.example.classes.ImmutableCar;
import java.util.Comparator;

/**
 * Компаратор для сортировки автомобилей по году производства.
 */
public class CarYearComparator implements Comparator<ImmutableCar> {

    @Override
    public int compare(ImmutableCar car1, ImmutableCar car2) {
        return Integer.compare(car1.getManufactureYear(), car2.getManufactureYear());
    }

    @Override
    public String toString() {
        return "Сортировка по году производства";
    }
}