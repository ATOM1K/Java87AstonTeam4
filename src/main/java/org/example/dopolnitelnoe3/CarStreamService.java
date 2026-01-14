package org.example.dopolnitelnoe3;

import org.example.classes.Car;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CarStreamService {

    /**
     * Создаёт коллекцию Car из потока данных
     */
    public CarCollection createCarCollectionFromStream(Stream<CarData> carDataStream) {
        CarCollection collection = new CarCollection();

        List<Car> cars = carDataStream
                .map(data -> new Car(data.getPower(), data.getModel(), data.getYearOfCreate()))
                .collect(Collectors.toList());

        collection.addAll(cars);
        return collection;
    }

    /**
     * Пример заполнения коллекции тестовыми данными через Stream
     */
    public CarCollection createSampleCollection() {
        List<CarData> sampleData = List.of(
                new CarData("Toyota Camry", 200, 2020),
                new CarData("BMW X5", 300, 2019),
                new CarData("Audi A4", 180, 2021),
                new CarData("Mercedes C-Class", 250, 2022)
        );

        return createCarCollectionFromStream(sampleData.stream());
    }
}
