package org.example.service;

import org.example.improved.Car;
import org.example.strategy.SortStrategy;
import org.example.dopolnitelnoe3.CarData;
import org.example.dopolnitelnoe3.CarStreamService;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CarService {
    private List<Car> cars = new ArrayList<>();
    private final Random random = new Random();
    private final CarStreamService carStreamService = new CarStreamService();

    public void fillManually(Scanner scanner) {
        System.out.println("\n===   ===");
        System.out.print("Сколько автомобилей добавить? ");
        int count = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < count; i++) {
            System.out.println("\nАвтомобиль #" + (i + 1) + ":");
            System.out.print("Модель: ");
            String model = scanner.nextLine();

            System.out.print("Мощность (л.с.): ");
            double power = scanner.nextDouble();

            System.out.print("Год производства: ");
            int year = scanner.nextInt();
            scanner.nextLine();

            try {
                Car car = Car.create(model, power, year);
                cars.add(car);
                System.out.println("✅ Автомобиль добавлен: " + car.getModel());
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Ошибка: " + e.getMessage());
                i--;
            }
        }
    }

    public void fillRandom(int count) {
        System.out.println("\n===  СЫ Ы ===");
        String[] models = {"Toyota", "BMW", "Audi", "Mercedes", "Ford",
                "Honda", "Tesla", "Nissan", "Volkswagen", "Hyundai"};

        cars = Stream.generate(() -> {
            String model = models[random.nextInt(models.length)];
            double power = 100 + random.nextDouble() * 400;
            int year = 2000 + random.nextInt(25);
            return Car.create(model, power, year);
        }).limit(count).collect(Collectors.toList());

        System.out.println("✅ обавлено " + count + " случайных автомобилей");
    }

    public void fillWithStreamService() {
        System.out.println("\n===   STREAMS API (оп.3) ===");
        var carCollection = carStreamService.createSampleCollection();
        cars = carCollection.getCars().stream()
                .map(car -> Car.create(car.getModel(), car.getPower(), car.getYearOfCreate()))
                .collect(Collectors.toList());
        System.out.println("✅ обавлено " + cars.size() + " автомобилей через Streams API");
    }

    public void showStreamOperations() {
        System.out.println("\n=== ТЫ  STREAMS API (оп.3) ===");
        if (cars.isEmpty()) {
            System.out.println("❌ Список автомобилей пуст!");
            return;
        }
        System.out.println("сего автомобилей: " + cars.size());
        
        System.out.println("\n1.    ЫС:");
        Map<Integer, List<Car>> carsByYear = cars.stream()
                .collect(Collectors.groupingBy(Car::getManufactureYear));
        carsByYear.forEach((year, carList) -> {
            System.out.printf("   %d год: %d автомобилей%n", year, carList.size());
        });
        
        System.out.println("\n2. СТТСТ ЩСТ:");
        DoubleSummaryStatistics stats = cars.stream()
                .mapToDouble(Car::getPower)
                .summaryStatistics();
        System.out.printf("   Средняя мощность: %.1f л.с.%n", stats.getAverage());
        System.out.printf("   аксимальная: %.0f л.с.%n", stats.getMax());
        System.out.printf("   инимальная: %.0f л.с.%n", stats.getMin());
        
        System.out.println("\n3. Т СТШ 5 Т:");
        long oldCarsCount = cars.stream()
                .filter(car -> car.getAge() > 5)
                .count();
        System.out.printf("   айдено: %d автомобилей%n", oldCarsCount);
        
        System.out.println("\n4. ЬЫ :");
        long uniqueModels = cars.stream()
                .map(Car::getModel)
                .distinct()
                .count();
        System.out.printf("   никальных моделей: %d%n", uniqueModels);
    }

    public void fillFromFile(String filename) {
        System.out.println("\n=== Т   ===");
        System.out.println("тение из файла будет реализовано позже");
    }

    public void sortWithStrategy(SortStrategy strategy, Comparator<Car> comparator) {
        System.out.println("\n=== СТ ===");
        System.out.println("лгоритм: " + strategy.getName());
        System.out.println("ритерий: " + comparator);

        if (cars.isEmpty()) {
            System.out.println("❌ Список автомобилей пуст!");
            return;
        }

        List<Car> copy = new ArrayList<>(cars);
        long startTime = System.nanoTime();
        strategy.sort(copy, comparator);
        long endTime = System.nanoTime();

        cars = copy;
        double durationMs = (endTime - startTime) / 1_000_000.0;

        System.out.println("✅ тсортировано " + cars.size() + " автомобилей");
        System.out.printf("⏱️  ремя выполнения: %.3f мс%n", durationMs);
    }

    public void sortEvenPowerOnly(SortStrategy strategy) {
        System.out.println("\n=== СТ ТЬ ТЫХ  ЩСТ ===");
        if (cars.isEmpty()) {
            System.out.println("❌ Список автомобилей пуст!");
            return;
        }

        List<Car> sortedCars = new ArrayList<>(cars);
        List<Integer> evenPowerIndices = new ArrayList<>();
        for (int i = 0; i < sortedCars.size(); i++) {
            if ((int)sortedCars.get(i).getPower() % 2 == 0) {
                evenPowerIndices.add(i);
            }
        }

        List<Car> evenCars = evenPowerIndices.stream()
                .map(sortedCars::get)
                .collect(Collectors.toList());

        if (!evenCars.isEmpty()) {
            strategy.sort(evenCars, Comparator.comparingDouble(Car::getPower));
            for (int i = 0; i < evenPowerIndices.size(); i++) {
                sortedCars.set(evenPowerIndices.get(i), evenCars.get(i));
            }
            cars = sortedCars;
            System.out.println("✅ тсортировано " + evenCars.size() + " автомобилей с четной мощностью");
        } else {
            System.out.println("ℹ️  ет автомобилей с четной мощностью для сортировки");
        }
    }

    public void countOccurrencesParallel(String modelToFind) {
        System.out.println("\n=== ТЫ С ===");
        System.out.println("оиск модели: " + modelToFind);

        if (cars.isEmpty()) {
            System.out.println("❌ Список автомобилей пуст!");
            return;
        }

        long startTime = System.nanoTime();
        long count = cars.parallelStream()
                .filter(car -> car.getModel().equalsIgnoreCase(modelToFind))
                .count();
        long endTime = System.nanoTime();
        double durationMs = (endTime - startTime) / 1_000_000.0;

        System.out.println("✅ одель '" + modelToFind + "' найдена " + count + " раз");
        System.out.printf("⏱️  ремя выполнения: %.3f мс%n", durationMs);
    }

    public void displayCars() {
        System.out.println("\n=== СС Т ===");
        if (cars.isEmpty()) {
            System.out.println("Список пуст");
            return;
        }

        System.out.println("сего автомобилей: " + cars.size());
        System.out.println("┌─────┬────────────────────┬────────────┬──────────────┬───────┐");
        System.out.println("│  #  │       одель       │ ощность   │ од выпуска  │ озр. │");
        System.out.println("├─────┼────────────────────┼────────────┼──────────────┼───────┤");

        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            System.out.printf("│ %3d │ %-18s │ %6.0f л.с. │     %4d     │ %3d л. │%n",
                    i + 1,
                    car.getModel().length() > 18 ? car.getModel().substring(0, 15) + "..." : car.getModel(),
                    car.getPower(),
                    car.getManufactureYear(),
                    car.getAge());
        }
        System.out.println("└─────┴────────────────────┴────────────┴──────────────┴───────┘");
    }

    public void clear() {
        cars.clear();
        System.out.println("✅ Список автомобилей очищен");
    }

    public int getCount() {
        return cars.size();
    }

    public List<Car> getCars() {
        return new ArrayList<>(cars);
    }
}