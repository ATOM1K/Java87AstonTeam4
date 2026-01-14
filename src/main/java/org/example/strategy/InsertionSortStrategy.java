package org.example.strategy;

import org.example.improved.Car;
import java.util.Comparator;
import java.util.List;

/**
 * Стратегия сортировки вставками.
 */
public class InsertionSortStrategy implements SortStrategy {

    @Override
    public void sort(List<Car> cars, Comparator<Car> comparator) {
        int n = cars.size();

        for (int i = 1; i < n; i++) {
            Car key = cars.get(i);
            int j = i - 1;

            // Перемещаем элементы cars[0..i-1], которые больше key,
            // на одну позицию вперед
            while (j >= 0 && comparator.compare(cars.get(j), key) > 0) {
                cars.set(j + 1, cars.get(j));
                j = j - 1;
            }
            cars.set(j + 1, key);
        }
    }

    @Override
    public String getName() {
        return "Сортировка вставками";
    }

    @Override
    public String getDescription() {
        return "Алгоритм, который строит отсортированную последовательность\n" +
                "по одному элементу за раз, вставляя каждый новый элемент\n" +
                "в правильную позицию.";
    }
}