package org.example.strategy;

import org.example.classes.ImmutableCar;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Алгоритм сортировки коллекции по четным значениям поля yearOfCreate, нечетные – остаются на исходных позициях
public class EvenYearSort implements SortStrategy<ImmutableCar> {
    @Override
    public void sort(List<ImmutableCar> cars) {
        sort(cars, Comparator.comparingInt(ImmutableCar::getManufactureYear));
    }

    @Override
    public void sort(List<ImmutableCar> cars, Comparator<ImmutableCar> comparator) {
        List<Integer> evenPositions = new ArrayList<>();
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getManufactureYear() % 2 == 0) {
                evenPositions.add(i);
            }
        }

        List<ImmutableCar> evenValues = new ArrayList<>();
        for (int pos : evenPositions) {
            evenValues.add(cars.get(pos));
        }

        new InsertionSortStrategy().sort(evenValues, comparator);

        for (int i = 0; i < evenPositions.size(); i++) {
            int pos = evenPositions.get(i);
            cars.set(pos, evenValues.get(i));
        }
    }

    @Override
    public String getName() {
        return "Четная сортировка по году выпуска";
    }
}
