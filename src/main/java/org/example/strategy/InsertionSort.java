package org.example.strategy;

import org.example.classes.Car;
import org.example.comparator.BaseComparator;
import org.example.interfaces.SortStrategy;

import java.util.Comparator;
import java.util.List;

public class InsertionSort implements SortStrategy<Car> {
    @Override
    public void sort(List<Car> items) {
        sort(items, new BaseComparator());
    }

    @Override
    public void sort(List<Car> items, Comparator<Car> comparator) {
        int j = 0;
        for (int i = 1; i < items.size(); i++) {
            Car curCar = items.get(i);
            j = i;
            while (j >= 1 && comparator.compare(items.get(j-1), (curCar)) > 0) {
                items.set(j, items.get(j-1));
                j--;
            }
            items.set(j,curCar);
        }
    }
}
