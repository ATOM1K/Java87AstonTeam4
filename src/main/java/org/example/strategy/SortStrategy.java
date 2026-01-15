package org.example.strategy;

import java.util.Comparator;
import java.util.List;

/**
 * Интерфейс стратегии сортировки.
 * Паттерн Стратегия позволяет менять алгоритм сортировки во время выполнения.
 */
public interface SortStrategy<T> {
    void sort(List<T> cars);


    /**
     * Сортирует список автомобилей с использованием заданного компаратора.
     * @param cars список для сортировки
     * @param comparator компаратор для сравнения автомобилей
     */
    void sort(List<T> cars, Comparator<T> comparator);

    /**
     * Возвращает название стратегии.
     */
    String getName();
}