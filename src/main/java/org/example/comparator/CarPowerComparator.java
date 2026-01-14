package org.example.comparator;

import org.example.classes.Car;
import org.example.classes.ImmutableCar;
import java.util.Comparator;

/**
 * Компаратор для сортировки автомобилей по мощности.
 */
public class CarPowerComparator implements Comparator<ImmutableCar> {

    @Override
    public int compare(ImmutableCar car1, ImmutableCar car2) {
        return Double.compare(car1.getPower(), car2.getPower());
    }

    @Override
    public String toString() {
        return "Сортировка по мощности";
    }
}