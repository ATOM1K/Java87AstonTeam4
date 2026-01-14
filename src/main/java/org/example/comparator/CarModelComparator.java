package org.example.comparator;

import org.example.classes.Car;
import org.example.classes.ImmutableCar;
import java.util.Comparator;

/**
 * Компаратор для сортировки автомобилей по модели (алфавитный порядок).
 */
public class CarModelComparator implements Comparator<ImmutableCar> {

    @Override
    public int compare(ImmutableCar car1, ImmutableCar car2) {
        return car1.getModel().compareToIgnoreCase(car2.getModel());
    }

    @Override
    public String toString() {
        return "Сортировка по модели";
    }
}