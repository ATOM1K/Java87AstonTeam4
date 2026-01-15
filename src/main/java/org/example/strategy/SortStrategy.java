package org.example.strategy;

import org.example.improved.Car;
import java.util.Comparator;
import java.util.List;

/**
 * Интерфейс стратегии сортировки.
 * Паттерн Стратегия позволяет менять алгоритм сортировки во время выполнения.
 */
public interface SortStrategy<T> {
    void sort(List<T> cars);
    void sort(List<T> cars, Comparator<T> comparator);

    /**
     * Сортирует список автомобилей с использованием заданного компаратора.
     * @param cars список для сортировки
     * @param comparator компаратор для сравнения автомобилей
     */
    void sort(List<Car> cars, Comparator<Car> comparator);

    /**
     * Возвращает название стратегии.
     */
    String getName();
}