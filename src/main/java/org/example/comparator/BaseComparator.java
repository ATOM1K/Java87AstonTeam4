package org.example.comparator;

import org.example.classes.ImmutableCar;
import org.example.classes.Car;

import java.util.Comparator;

public class BaseComparator implements Comparator<ImmutableCar> {

    @Override
    public int compare(ImmutableCar o1, ImmutableCar o2) {
        int yearCompare = Integer.compare(o2.getManufactureYear(), o1.getManufactureYear()); // Новые вперед
        if(yearCompare != 0) return yearCompare;

        int powerCompare = Double.compare(o2.getPower(), o1.getPower());
        if(powerCompare != 0) return powerCompare;

        return o1.getModel().compareTo(o2.getModel());
    }
}
