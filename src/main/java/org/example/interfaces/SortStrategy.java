package org.example.interfaces;

import org.example.classes.Car;

import java.util.Comparator;
import java.util.List;

public interface SortStrategy<T> {
    void sort(List<T> items);
    void sort(List<T> items, Comparator<Car> comparator);
}
