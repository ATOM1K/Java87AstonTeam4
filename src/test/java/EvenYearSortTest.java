import org.example.classes.Car;
import org.example.classes.CarSorter;
import org.example.strategy.EvenYearSort;
import org.example.enums.CarModel;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class EvenYearSortTest {
    @Test
    public void evenSortByYear() {
        List<Car> actual1 = new ArrayList<>(
            List.of(
                new Car(60, CarModel.AUDI_A4.getModelName(),2022),
                new Car(70, CarModel.AUDI_A4.getModelName(),2024),
                new Car(70, CarModel.TOYOTA_CAMRY.getModelName(),2021),
                new Car(70, CarModel.TOYOTA_CAMRY.getModelName(),2026),
                new Car(78, CarModel.MAZDA_6.getModelName(),2019),
                new Car(75, CarModel.LADA_GRANTA.getModelName(),2020),
                new Car(65, CarModel.LADA_GRANTA.getModelName(),2021)
            )
        );

        List<Car> expected1 = new ArrayList<>(
            List.of(
                new Car(75, CarModel.LADA_GRANTA.getModelName(),2020),
                new Car(60, CarModel.AUDI_A4.getModelName(),2022),
                new Car(70, CarModel.TOYOTA_CAMRY.getModelName(),2021),
                new Car(70, CarModel.AUDI_A4.getModelName(),2024),
                new Car(78, CarModel.MAZDA_6.getModelName(),2019),
                new Car(70, CarModel.TOYOTA_CAMRY.getModelName(),2026),
                new Car(65, CarModel.LADA_GRANTA.getModelName(),2021)
            )
        );

        CarSorter carSorter = new CarSorter(new EvenYearSort());
        carSorter.sort(actual1);

        Assert.assertEquals(expected1, actual1);
    }
}
