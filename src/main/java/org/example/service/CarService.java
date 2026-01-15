package org.example.service;

import org.example.improved.Car;
import org.example.strategy.SortStrategy;
import org.example.dopolnitelnoe3.CarData;
import org.example.dopolnitelnoe3.CarStreamService;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Сервис для работы с автомобилями.
 */
public class CarService {
    private List<ImmutableCar> cars = new ArrayList<>();
    private final Random random = new Random();
    private final CarStreamService carStreamService = new CarStreamService();

    public void fillManually(Scanner scanner) {
        System.out.println("\n=== ЗАПОЛНЕНИЕ ВРУЧНУЮ ===");
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
                ImmutableCar car = ImmutableCar.create(model, power, year);
                cars.add(car);
                System.out.println("✅ Автомобиль добавлен: " + car.getModel());
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Ошибка: " + e.getMessage());
                i--;
            }
        }
    }

    /**
     * Заполнение случайными данными (из задания).
     */
    public void fillRandom(int count) {
        System.out.println("\n=== ЗАПОЛНЕНИЕ СЛУЧАЙНЫМИ ДАННЫМИ ===");

        String[] models = {"Toyota", "BMW", "Audi", "Mercedes", "Ford",
                "Honda", "Tesla", "Nissan", "Volkswagen", "Hyundai"};

        // Доп. задание 3: использование Streams API
        cars = Stream.generate(() -> {
            String model = models[random.nextInt(models.length)];
            double power = 100 + random.nextDouble() * 400; // 100-500 л.с.
            int year = 2000 + random.nextInt(25); // 2000-2025
            return ImmutableCar.create(model, power, year);
        }).limit(count).collect(Collectors.toList());

        System.out.println("✅ Добавлено " + count + " случайных автомобилей");
    }

    public void fillWithStreamService() {
        System.out.println("\n===   STREAMS API (оп.3) ===");
        var carCollection = carStreamService.createSampleCollection();
        cars = carCollection.getCars().stream()
                .map(car -> Car.create(car.getModel(), car.getPower(), car.getYearOfCreate()))
                .collect(Collectors.toList());
        System.out.println("✅ Добавлено " + cars.size() + " автомобилей через Streams API");
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
        System.out.println("\n=== ЧТЕНИЕ ИЗ ФАЙЛА ===");
        // TODO: реализовать чтение из JSON/CSV файла
        System.out.println("Чтение из файла будет реализовано позже");
    }

    // Методы сортировки

    /**
     * Сортировка с использованием стратегии.
     */
    public void sortWithStrategy(SortStrategy strategy, Comparator<ImmutableCar> comparator) {
        System.out.println("\n=== СОРТИРОВКА ===");
        System.out.println("Алгоритм: " + strategy.getName());
        System.out.println("Критерий: " + comparator);

        if (cars.isEmpty()) {
            System.out.println("❌ Список автомобилей пуст!");
            return;
        }

        List<ImmutableCar> copy = new ArrayList<>(cars);
        long startTime = System.nanoTime();
        strategy.sort(copy, comparator);
        long endTime = System.nanoTime();

        cars = copy;
        double durationMs = (endTime - startTime) / 1_000_000.0;

        System.out.println("✅ Отсортировано " + cars.size() + " автомобилей");
        System.out.printf("⏱️  Время выполнения: %.3f мс%n", durationMs);
    }

    /**
     * Доп. задание 1: сортировка только четных значений мощности.
     */
    public void sortEvenPowerOnly(SortStrategy strategy) {
        System.out.println("\n=== СОРТИРОВКА ТОЛЬКО ЧЕТНЫХ ЗНАЧЕНИЙ МОЩНОСТИ ===");

        if (cars.isEmpty()) {
            System.out.println("❌ Список автомобилей пуст!");
            return;
        }

        // Создаем копию для работы
        List<ImmutableCar> sortedCars = new ArrayList<>(cars);

        // Находим индексы автомобилей с четной мощностью
        List<Integer> evenPowerIndices = new ArrayList<>();
        for (int i = 0; i < sortedCars.size(); i++) {
            if ((int)sortedCars.get(i).getPower() % 2 == 0) {
                evenPowerIndices.add(i);
            }
        }

        // Создаем список только четных автомобилей для сортировки
        List<ImmutableCar> evenCars = evenPowerIndices.stream()
                .map(sortedCars::get)
                .collect(Collectors.toList());

        // Сортируем четные автомобили по мощности
        if (!evenCars.isEmpty()) {
            strategy.sort(evenCars, Comparator.comparingDouble(ImmutableCar::getPower));

            // Возвращаем отсортированные четные автомобили на свои места
            for (int i = 0; i < evenPowerIndices.size(); i++) {
                sortedCars.set(evenPowerIndices.get(i), evenCars.get(i));
            }

            cars = sortedCars;
            System.out.println("✅ Отсортировано " + evenCars.size() +
                    " автомобилей с четной мощностью");
            System.out.println("   Автомобили с нечетной мощностью остались на своих местах");
        } else {
            System.out.println("ℹ️  Нет автомобилей с четной мощностью для сортировки");
        }
    }

    /**
     * Доп. задание 4: многопоточный подсчет вхождений.
     */
    public void countOccurrencesParallel(String modelToFind) {
        System.out.println("\n=== МНОГОПОТОЧНЫЙ ПОИСК ===");
        System.out.println("Поиск модели: " + modelToFind);

        if (cars.isEmpty()) {
            System.out.println("❌ Список автомобилей пуст!");
            return;
        }

        long startTime = System.nanoTime();

        // Используем parallelStream для многопоточного поиска
        long count = cars.parallelStream()
                .filter(car -> car.getModel().equalsIgnoreCase(modelToFind))
                .count();

        long endTime = System.nanoTime();
        double durationMs = (endTime - startTime) / 1_000_000.0;

        System.out.println("✅ Модель '" + modelToFind + "' найдена " + count + " раз");
        System.out.printf("⏱️  Время выполнения: %.3f мс%n", durationMs);

        // Для сравнения - однопоточный поиск
        startTime = System.nanoTime();
        long singleThreadCount = cars.stream()
                .filter(car -> car.getModel().equalsIgnoreCase(modelToFind))
                .count();
        endTime = System.nanoTime();
        double singleThreadMs = (endTime - startTime) / 1_000_000.0;

        System.out.println("   Однопоточный поиск: " + singleThreadCount + " раз (" +
                singleThreadMs + " мс)");
    }

    // Вспомогательные методы

    public void displayCars() {
        System.out.println("\n=== СПИСОК АВТОМОБИЛЕЙ ===");
        if (cars.isEmpty()) {
            System.out.println("Список пуст");
            return;
        }

        System.out.println("Всего автомобилей: " + cars.size());
        System.out.println("┌─────┬────────────────────┬────────────┬──────────────┬───────┐");
        System.out.println("│  #  │       Модель       │ Мощность   │ Год выпуска  │ Возр. │");
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

    public List<ImmutableCar> getCars() {
//        return new ArrayList<>(cars); // возвращаем копию для безопасности
        return cars;
    }
}