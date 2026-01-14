package org.example.strategy;

import org.example.improved.Car;
import java.util.Comparator;
import java.util.List;

/**
 * нтерфейс стратегии сортировки.
 * аттерн Стратегия позволяет менять алгоритм сортировки во время выполнения.
 */
public interface SortStrategy {

    /**
     * Сортирует список автомобилей с использованием заданного компаратора.
     * @param cars список для сортировки
     * @param comparator компаратор для сравнения автомобилей
     */
    void sort(List<Car> cars, Comparator<Car> comparator);

    /**
     * озвращает название стратегии.
     */
    String getName();

    /**
     * озвращает описание алгоритма.
     */
    default String getDescription() {
        return "лгоритм сортировки";
    }
}
