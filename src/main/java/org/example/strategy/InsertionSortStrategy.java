package org.example.strategy;

import org.example.classes.ImmutableCar;
import org.example.comparator.BaseComparator;

import java.util.Comparator;
import java.util.List;

/**
 * Стратегия сортировки вставками.
 */
public class InsertionSortStrategy implements SortStrategy<ImmutableCar> {

    @Override
    public void sort(List<ImmutableCar> cars) {
        sort(cars, new BaseComparator());
    }

    @Override
    public void sort(List<ImmutableCar> cars, Comparator<ImmutableCar> comparator) {
        int j = 0;
        for (int i = 1; i < cars.size(); i++) {
            ImmutableCar curCar = cars.get(i);
            j = i;
            while (j >= 1 && comparator.compare(cars.get(j-1), (curCar)) > 0) {
                cars.set(j, cars.get(j-1));
                j--;
            }
            cars.set(j,curCar);
        }
    }

    @Override
    public String getName() {
        return "Сортировка вставками";
    }
}