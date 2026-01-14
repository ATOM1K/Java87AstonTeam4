package org.example.dopolnitelnoe3;
import java.util.*;

    public class CarCollection implements Iterable<Car> {
        private final List<Car> cars = new ArrayList<>();

        public void add(Car car) {
            cars.add(car);
        }

        public void addAll(Collection<Car> carCollection) {
            cars.addAll(carCollection);
        }

        public List<Car> getCars() {
            return Collections.unmodifiableList(cars);
        }

        public int size() {
            return cars.size();
        }

        @Override
        public Iterator<Car> iterator() {
            return cars.iterator();
        }

        @Override
        public String toString() {
            return "CarCollection{" +
                    "cars=" + cars +
                    '}';
        }
    }
