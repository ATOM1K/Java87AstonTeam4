package org.example.comparator;

import org.example.classes.Car;

import java.util.Comparator;

public class BaseComparator implements Comparator<Car> {

    @Override
    public int compare(Car o1, Car o2) {
        int yearCompare = Integer.compare(o2.getYearOfCreate(), o1.getYearOfCreate()); // Новые вперед
        if(yearCompare != 0) return yearCompare;

        int powerCompare = Double.compare(o2.getPower(), o1.getPower());
        if(powerCompare != 0) return powerCompare;

        return o1.getModel().compareTo(o2.getModel());
    }
}
