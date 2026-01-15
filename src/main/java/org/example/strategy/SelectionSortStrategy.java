package org.example.strategy;

import org.example.classes.ImmutableCar;
import org.example.comparator.BaseComparator;

import java.util.Comparator;
import java.util.List;

/**
 * Стратегия сортировки выбором.
 */
public class SelectionSortStrategy implements SortStrategy<ImmutableCar> {

    @Override
    public void sort(List<ImmutableCar> cars) {
        sort(cars, new BaseComparator());
    }

    @Override
    public void sort(List<ImmutableCar> cars, Comparator<ImmutableCar> comparator) {
        int n = cars.size();

        for (int i = 0; i < n - 1; i++) {
            // Находим минимальный элемент в неотсортированной части
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (comparator.compare(cars.get(j), cars.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }

            // Меняем местами найденный минимальный элемент с первым элементом
            ImmutableCar temp = cars.get(minIndex);
            cars.set(minIndex, cars.get(i));
            cars.set(i, temp);
        }
    }

    @Override
    public String getName() {
        return "Сортировка выбором";
    }

}