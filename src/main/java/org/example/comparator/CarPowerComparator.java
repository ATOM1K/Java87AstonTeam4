package org.example.comparator;

import org.example.improved.Car;
import java.util.Comparator;

/**
 * Компаратор для сортировки автомобилей по мощности.
 */
public class CarPowerComparator implements Comparator<Car> {

    @Override
    public int compare(Car car1, Car car2) {
        return Double.compare(car1.getPower(), car2.getPower());
    }

    @Override
    public String toString() {
        return "Сортировка по мощности";
    }
}
