package org.example.classes;

import org.example.interfaces.SortStrategy;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

// Алгоритм сортировки коллекции по четным значениям поля yearOfCreate, нечетные – остаются на исходных позициях
public class EvenYearSort implements SortStrategy<Car> {
    @Override
    public List<Car> sort(List<Car> items) {
//       Заглушка. Написать реализацию метода сортировки четных.
        return null;
    }
}
