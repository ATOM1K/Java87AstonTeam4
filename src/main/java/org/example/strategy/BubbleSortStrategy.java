package org.example.strategy;

import org.example.improved.Car;
import java.util.Comparator;
import java.util.List;

/**
 * Стратегия пузырьковой сортировки.
 */
public class BubbleSortStrategy implements SortStrategy {

    @Override
    public void sort(List<Car> cars, Comparator<Car> comparator) {
        int n = cars.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (comparator.compare(cars.get(j), cars.get(j + 1)) > 0) {
                    // Обмен элементов
                    Car temp = cars.get(j);
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

    @Override
    public String getDescription() {
        return "Простой алгоритм сортировки, который многократно проходит по списку,\n" +
                "сравнивая соседние элементы и меняя их местами, если они находятся в неправильном порядке.";
    }
}