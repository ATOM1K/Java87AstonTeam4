package org.example.strategy;

import java.util.Comparator;
import java.util.List;

public interface SortStrategy<T> {
    void sort(List<T> cars);
    void sort(List<T> cars, Comparator<T> comparator);

    /**
     * Возвращает название стратегии.
     */
    String getName();
}