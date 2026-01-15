package org.example.strategy;

import org.example.improved.Car;
import java.util.Comparator;
import java.util.List;

/**
 * Стратегия сортировки вставками.
 */
public class InsertionSortStrategy implements SortStrategy {

    @Override
    public void sort(List<Car> items, Comparator<Car> comparator) {
        int n = items.size();
        for (int i = 1; i < n; ++i) {
            Car key = items.get(i);
            int j = i - 1;

            // еремещаем элементы items[0..i-1], которые больше key,
            // на одну позицию вперед от их текущей позиции
            while (j >= 0 && comparator.compare(items.get(j), key) > 0) {
                items.set(j + 1, items.get(j));
                j = j - 1;
            }
            items.set(j + 1, key);
        }
    }

    @Override
    public String getName() {
        return "Сортировка вставками";
    }

    @Override
    public String getDescription() {
        return "ффективный алгоритм для небольших наборов данных. " +
               "аботает аналогично тому, как человек сортирует карты в руке.";
    }
}
