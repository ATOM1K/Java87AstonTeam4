package org.example.strategy;

import org.example.classes.ImmutableCar;
import org.example.comparator.BaseComparator;

import java.util.Comparator;
import java.util.List;

/**
 * Стратегия пузырьковой сортировки.
 */
public class BubbleSortStrategy implements SortStrategy<ImmutableCar> {

    @Override
    public void sort(List<ImmutableCar> cars) {
        sort(cars, new BaseComparator());
    }

    @Override
    public void sort(List<ImmutableCar> cars, Comparator<ImmutableCar> comparator) {
        int n = cars.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (comparator.compare(cars.get(j), cars.get(j + 1)) > 0) {
                    // Обмен элементов
                    ImmutableCar temp = cars.get(j);
                    cars.set(j, cars.get(j + 1));
                    cars.set(j + 1, temp);
                }
            }
        }
    }

    @Override
    public String getName() {
        return "Пузырьковая сортировка";
    }
}