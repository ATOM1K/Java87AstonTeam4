package org.example.strategy;

import org.example.classes.Car;
import org.example.interfaces.SortStrategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Алгоритм сортировки коллекции по четным значениям поля yearOfCreate, нечетные – остаются на исходных позициях
public class EvenYearSort implements SortStrategy<Car> {
    @Override
    public void sort(List<Car> items) {
        sort(items, Comparator.comparingInt(Car::getYearOfCreate));
    }

    @Override
    public void sort(List<Car> items, Comparator<Car> comparator) {
        List<Integer> evenPositions = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getYearOfCreate() % 2 == 0) {
                evenPositions.add(i);
            }
        }

        List<Car> evenValues = new ArrayList<>();
        for (int pos : evenPositions) {
            evenValues.add(items.get(pos));
        }

        new InsertionSort().sort(evenValues, comparator);

        for (int i = 0; i < evenPositions.size(); i++) {
            int pos = evenPositions.get(i);
            items.set(pos, evenValues.get(i));
        }
    }
}
