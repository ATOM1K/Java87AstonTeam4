package org.example.classes;

import org.example.interfaces.SortStrategy;

import java.util.List;

// Алгоритм сортировки коллекции по четным значениям поля power, нечетные – остаются на исходных позициях
public class EvenPowerSort implements SortStrategy<Car> {
    @Override
    public List<Car> sort(List<Car> items) {
//       Заглушка. Написать реализацию метода сортировки четных.
        return null;
    }
}
