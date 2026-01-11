package org.example.classes;

import org.example.interfaces.SortStrategy;

import java.util.ArrayList;
import java.util.List;

// Контекст (обработчик) для работы с кастомными сортировками
public class CarSorter {
    private SortStrategy sortStrategy;

    public CarSorter(SortStrategy sortStrategy) {
        this.sortStrategy = sortStrategy;
    }

    public void setStrategy(SortStrategy sortStrategy) {
        this.sortStrategy = sortStrategy;
    }

    public SortStrategy getStrategy() {
        return sortStrategy;
    }

    public void sort(List<Car> cars) {
        sortStrategy.sort(cars);
    }
}
