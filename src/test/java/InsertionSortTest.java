import org.example.improved.Car;
import org.example.classes.CarSorter;
import org.example.strategy.InsertionSort;
import org.example.enums.CarModel;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class InsertionSortTest {
    @Test
    public void sortByYear() {
        List<Car> actual1 = new ArrayList<>(
            List.of(
                new Car(16, CarModel.AUDI_A4.getModelName(),2020),
                new Car(20, CarModel.AUDI_A4.getModelName(),2017),
                new Car(20, CarModel.TOYOTA_CAMRY.getModelName(),2021)
            )
        );

        List<Car> expected1 = new ArrayList<>(
            List.of(
                new Car(20, CarModel.TOYOTA_CAMRY.getModelName(),2021),
                new Car(16, CarModel.AUDI_A4.getModelName(),2020),
                new Car(20, CarModel.AUDI_A4.getModelName(),2017)
            )
        );

        CarSorter carSorter = new CarSorter(new InsertionSort());
        carSorter.sort(actual1);

        Assert.assertEquals(expected1, actual1);
    }

    @Test
    public void sortByPower() {
        List<Car> actual1 = new ArrayList<>(
            List.of(
                new Car(60, CarModel.AUDI_A4.getModelName(),2020),
                new Car(70, CarModel.AUDI_A4.getModelName(),2020),
                new Car(70, CarModel.TOYOTA_CAMRY.getModelName(),2020),
                new Car(78, CarModel.MAZDA_6.getModelName(),2020),
                new Car(75, CarModel.LADA_GRANTA.getModelName(),2020),
                new Car(65, CarModel.LADA_GRANTA.getModelName(),2020)
            )
        );

        List<Car> expected1 = new ArrayList<>(
            List.of(
                new Car(78, CarModel.MAZDA_6.getModelName(),2020),
                new Car(75, CarModel.LADA_GRANTA.getModelName(),2020),
                new Car(70, CarModel.AUDI_A4.getModelName(),2020),
                new Car(70, CarModel.TOYOTA_CAMRY.getModelName(),2020),
                new Car(65, CarModel.LADA_GRANTA.getModelName(),2020),
                new Car(60, CarModel.AUDI_A4.getModelName(),2020)
            )
        );

        CarSorter carSorter = new CarSorter(new InsertionSort());
        carSorter.sort(actual1);

        Assert.assertEquals(expected1, actual1);
    }

    @Test
    public void sortByModel() {
        List<Car> actual1 = new ArrayList<>(
            List.of(
                new Car(60, CarModel.KIA_RIO.getModelName(),2020),
                new Car(60, CarModel.AUDI_A4.getModelName(),2020),
                new Car(60, CarModel.TOYOTA_CAMRY.getModelName(),2020),
                new Car(60, CarModel.MAZDA_6.getModelName(),2020),
                new Car(60, CarModel.LADA_GRANTA.getModelName(),2020),
                new Car(60, CarModel.LADA_VESTA.getModelName(), 2020),
                new Car(60, CarModel.FORD_FOCUS.getModelName(),2020)
            )
        );

        List<Car> expected1 = new ArrayList<>(
            List.of(
                new Car(60, CarModel.AUDI_A4.getModelName(),2020),
                new Car(60, CarModel.FORD_FOCUS.getModelName(),2020),
                new Car(60, CarModel.KIA_RIO.getModelName(),2020),
                new Car(60, CarModel.LADA_GRANTA.getModelName(),2020),
                new Car(60, CarModel.LADA_VESTA.getModelName(), 2020),
                new Car(60, CarModel.MAZDA_6.getModelName(),2020),
                new Car(60, CarModel.TOYOTA_CAMRY.getModelName(),2020)
            )
        );

        CarSorter carSorter = new CarSorter(new InsertionSort());
        carSorter.sort(actual1);

        Assert.assertEquals(expected1, actual1);
    }
}

