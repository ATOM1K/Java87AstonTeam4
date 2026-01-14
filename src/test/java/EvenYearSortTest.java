import org.example.classes.ImmutableCar;
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
        List<ImmutableCar> actual1 = new ArrayList<>(
            List.of(
                ImmutableCar.create(CarModel.AUDI_A4.getModelName(),60,2022),
                ImmutableCar.create(CarModel.AUDI_A4.getModelName(),70,2024),
                ImmutableCar.create(CarModel.TOYOTA_CAMRY.getModelName(),70,2021),
                ImmutableCar.create(CarModel.TOYOTA_CAMRY.getModelName(),70,2026),
                ImmutableCar.create(CarModel.MAZDA_6.getModelName(),78,2019),
                ImmutableCar.create(CarModel.LADA_GRANTA.getModelName(),75,2020),
                ImmutableCar.create(CarModel.LADA_GRANTA.getModelName(),65,2021)
            )
        );

        List<ImmutableCar> expected1 = new ArrayList<>(
            List.of(
                actual1.get(5),
                actual1.get(0),
                actual1.get(2),
                actual1.get(1),
                actual1.get(4),
                actual1.get(3),
                actual1.get(6)
            )
        );

        CarSorter carSorter = new CarSorter(new EvenYearSort());
        carSorter.sort(actual1);

        Assert.assertEquals(expected1, actual1);
    }
}
