package org.example.comparator;

import org.example.improved.Car;
import java.util.Comparator;

/**
 * Компаратор для сортировки автомобилей по модели (алфавитный порядок).
 */
public class CarModelComparator implements Comparator<Car> {

    @Override
    public int compare(Car car1, Car car2) {
        return car1.getModel().compareToIgnoreCase(car2.getModel());
    }

    @Override
    public String toString() {
        return "Сортировка по модели";
    }
}