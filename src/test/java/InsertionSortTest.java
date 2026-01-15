import org.example.classes.ImmutableCar;
import org.example.classes.CarSorter;
import org.example.enums.CarModel;
import org.example.strategy.InsertionSortStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class InsertionSortTest {
    @Test
    public void sortByYear() {
        List<ImmutableCar> actual1 = new ArrayList<>(
            List.of(
                ImmutableCar.create(CarModel.AUDI_A4.getModelName(),16, 2020),
                ImmutableCar.create(CarModel.AUDI_A4.getModelName(),20,2017),
                ImmutableCar.create(CarModel.TOYOTA_CAMRY.getModelName(),20,2021)
            )
        );

        List<ImmutableCar> expected1 = new ArrayList<>(
            List.of(
                actual1.get(2),
                actual1.get(0),
                actual1.get(1)
            )
        );

        CarSorter carSorter = new CarSorter(new InsertionSortStrategy());
        carSorter.sort(actual1);

        Assert.assertEquals(expected1, actual1);
    }

    @Test
    public void sortByPower() {
        List<ImmutableCar> actual1 = new ArrayList<>(
            List.of(
                ImmutableCar.create(CarModel.AUDI_A4.getModelName(),60,2020),
                ImmutableCar.create(CarModel.AUDI_A4.getModelName(),70,2020),
                ImmutableCar.create(CarModel.TOYOTA_CAMRY.getModelName(),70,2020),
                ImmutableCar.create(CarModel.MAZDA_6.getModelName(),78,2020),
                ImmutableCar.create(CarModel.LADA_GRANTA.getModelName(),75,2020),
                ImmutableCar.create(CarModel.LADA_GRANTA.getModelName(),65,2020)
            )
        );

        List<ImmutableCar> expected1 = new ArrayList<>(
            List.of(
                actual1.get(3),
                actual1.get(4),
                actual1.get(1),
                actual1.get(2),
                actual1.get(5),
                actual1.get(0)
            )
        );

        CarSorter carSorter = new CarSorter(new InsertionSortStrategy());
        carSorter.sort(actual1);

        Assert.assertEquals(expected1, actual1);
    }

    @Test
    public void sortByModel() {
        List<ImmutableCar> actual1 = new ArrayList<>(
            List.of(
                ImmutableCar.create(CarModel.KIA_RIO.getModelName(),60,2020),
                ImmutableCar.create( CarModel.AUDI_A4.getModelName(),60,2020),
                ImmutableCar.create(CarModel.TOYOTA_CAMRY.getModelName(),60,2020),
                ImmutableCar.create(CarModel.MAZDA_6.getModelName(),60,2020),
                ImmutableCar.create(CarModel.LADA_GRANTA.getModelName(),60,2020),
                ImmutableCar.create(CarModel.LADA_VESTA.getModelName(),60, 2020),
                ImmutableCar.create(CarModel.FORD_FOCUS.getModelName(),60,2020)
            )
        );

        List<ImmutableCar> expected1 = new ArrayList<>(
            List.of(
                actual1.get(1),
                actual1.get(6),
                actual1.get(0),
                actual1.get(4),
                actual1.get(5),
                actual1.get(3),
                actual1.get(2)
            )
        );

        CarSorter carSorter = new CarSorter(new InsertionSortStrategy());
        carSorter.sort(actual1);

        Assert.assertEquals(expected1, actual1);
    }
}
