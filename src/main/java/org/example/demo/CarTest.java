package org.example.demo;

import org.example.classes.Car;
import org.example.classes.ImmutableCar;

/**
 * Тесты для сравнения двух реализаций.
 */
public class CarTest {
    public static void main(String[] args) {
        System.out.println("🧪 ТЕСТЫ ДЛЯ СРАВНЕНИЯ\n");

        testImmutability();
        testValidation();
        testCollections();
    }

    private static void testImmutability() {
        System.out.println("1. ТЕСТ ИММУТАБЕЛЬНОСТИ:");

        // Оригинальный класс - ИЗМЕНЯЕМЫЙ
        Car original = new Car(200, "Audi", 2020);

        original.setPower(250); // МОЖНО ИЗМЕНИТЬ!
        System.out.println("   Оригинал: мощность изменена после создания: " + original.getPower());

        // Улучшенный класс - ИММУТАБЕЛЬНЫЙ
        ImmutableCar improved = ImmutableCar.create("Audi", 200, 2020);
        // improved.setPower(250); // КОМПИЛЯЦИЯ УПАДЕТ - нет сеттера!
        System.out.println("   Улучшенный: нельзя изменить после создания (компилятор не позволит)");
    }

    private static void testValidation() {
        System.out.println("\n2. ТЕСТ ВАЛИДАЦИИ:");

        System.out.println("   Оригинал: можно создать с невалидными данными:");
        Car invalidOriginal = new Car(-100, "", 1700);
        System.out.println("      Создан: " + invalidOriginal);

        System.out.println("\n   Улучшенный: НЕЛЬЗЯ создать с невалидными данными:");
        try {
            ImmutableCar invalidImproved = new ImmutableCar.Builder()
                    .model("")
                    .power(-100)
                    .manufactureYear(1700)
                    .build();
            System.out.println("      ❌ ОШИБКА: должно было быть исключение!");
        } catch (IllegalArgumentException e) {
            System.out.println("      ✅ ПРАВИЛЬНО: " + e.getMessage());
        }
    }

    private static void testCollections() {
        System.out.println("\n3. ТЕСТ РАБОТЫ С КОЛЛЕКЦИЯМИ (equals/hashCode):");

        // Создаем два одинаковых автомобиля
        ImmutableCar car1 = ImmutableCar.create("Toyota", 150, 2020);
        ImmutableCar car2 = ImmutableCar.create("Toyota", 150, 2020);

        System.out.println("   car1.equals(car2): " + car1.equals(car2));
        System.out.println("   car1.hashCode() == car2.hashCode(): " +
                (car1.hashCode() == car2.hashCode()));
        System.out.println("   (должно быть false, т.к. у них разные ID)");

        // Тест с одинаковыми ID
        ImmutableCar car3 = new ImmutableCar.Builder()
                .id("same_id")
                .model("Honda")
                .power(130)
                .manufactureYear(2019)
                .build();

        ImmutableCar car4 = new ImmutableCar.Builder()
                .id("same_id")  // ТОТ ЖЕ ID
                .model("Honda")
                .power(130)
                .manufactureYear(2019)
                .build();

        System.out.println("\n   С одинаковыми ID:");
        System.out.println("   car3.equals(car4): " + car3.equals(car4));
        System.out.println("   car3.hashCode() == car4.hashCode(): " +
                (car3.hashCode() == car4.hashCode()));
        System.out.println("   (должно быть true, т.к. ID одинаковые)");
    }
}