package org.example.demo;

import org.example.classes.ImmutableCar;

/**
 * Простая демонстрация улучшенного класса Car.
 * Не использует import чтобы избежать конфликтов имен.
 */
public class SimpleCarDemo {
    public static void main(String[] args) {
        System.out.println("ДЕМОНСТРАЦИЯ УЛУЧШЕННОГО КЛАССА CAR\n");

        System.out.println("=".repeat(60));
        System.out.println("1. СОЗДАНИЕ ВАЛИДНОГО АВТОМОБИЛЯ:");
        System.out.println("=".repeat(60));

        try {
            // Создаем улучшенный автомобиль
            ImmutableCar tesla = new ImmutableCar.Builder()
                    .model("Tesla Model S")
                    .power(670)
                    .manufactureYear(2023)
                    .build();

            System.out.println("✅ Успешно создан:");
            System.out.println("   • Модель: " + tesla.getModel());
            System.out.println("   • Мощность: " + tesla.getPower() + " л.с.");
            System.out.println("   • Год выпуска: " + tesla.getManufactureYear());
            System.out.println("   • Возраст: " + tesla.getAge() + " лет");
            System.out.println("   • Винтажный? " + tesla.isVintage());
            System.out.println("   • Электромобиль? " + tesla.isElectric());
            System.out.println("   • ID: " + tesla.getId());
            System.out.println("   • Полное описание: " + tesla);

        } catch (Exception e) {
            System.out.println("❌ Ошибка: " + e.getMessage());
        }

        System.out.println("\n" + "=".repeat(60));
        System.out.println("2. ПРОВЕРКА ВАЛИДАЦИИ (должны быть ошибки):");
        System.out.println("=".repeat(60));

        // Тест 1: Пустая модель
        System.out.println("\nТест 1: Пустая модель");
        try {
            ImmutableCar invalid1 = new ImmutableCar.Builder()
                    .model("")  // ПУСТО - ДОЛЖНА БЫТЬ ОШИБКА
                    .power(150)
                    .manufactureYear(2020)
                    .build();
            System.out.println("   ❌ НЕПРАВИЛЬНО: создался невалидный автомобиль");
        } catch (IllegalArgumentException e) {
            System.out.println("   ✅ ПРАВИЛЬНО: " + e.getMessage());
        }

        // Тест 2: Отрицательная мощность
        System.out.println("\nТест 2: Отрицательная мощность");
        try {
            ImmutableCar invalid2 = new ImmutableCar.Builder()
                    .model("BMW")
                    .power(-50)  // ОТРИЦАТЕЛЬНАЯ - ДОЛЖНА БЫТЬ ОШИБКА
                    .manufactureYear(2020)
                    .build();
            System.out.println("   ❌ НЕПРАВИЛЬНО: создался невалидный автомобиль");
        } catch (IllegalArgumentException e) {
            System.out.println("   ✅ ПРАВИЛЬНО: " + e.getMessage());
        }

        // Тест 3: Нереалистичный год
        System.out.println("\nТест 3: Нереалистичный год выпуска");
        try {
            ImmutableCar invalid3 = new ImmutableCar.Builder()
                    .model("Ford")
                    .power(120)
                    .manufactureYear(1800)  // СЛИШКОМ РАННИЙ - ДОЛЖНА БЫТЬ ОШИБКА
                    .build();
            System.out.println("   ❌ НЕПРАВИЛЬНО: создался невалидный автомобиль");
        } catch (IllegalArgumentException e) {
            System.out.println("   ✅ ПРАВИЛЬНО: " + e.getMessage());
        }

        System.out.println("\n" + "=".repeat(60));
        System.out.println("3. СРАВНЕНИЕ С ОРИГИНАЛЬНЫМ КЛАССОМ:");
        System.out.println("=".repeat(60));

        System.out.println("\nОригинальный класс (org.example.Classes.Car):");
        System.out.println("   • Изменяемый (есть сеттеры)");
        System.out.println("   • Нет валидации при создании");
        System.out.println("   • Можно создать объект в невалидном состоянии");
        System.out.println("   • Простая реализация equals/hashCode");

        System.out.println("\nУлучшенный класс (org.example.classes.Car):");
        System.out.println("   • Иммутабельный (только геттеры)");
        System.out.println("   • Встроенная валидация в Builder");
        System.out.println("   • Гарантированно валидное состояние");
        System.out.println("   • Дополнительные методы: getAge(), isVintage(), isElectric()");
        System.out.println("   • Правильные equals() и hashCode() с Objects");
        System.out.println("   • Уникальный ID для каждого автомобиля");
        System.out.println("   • Дата создания объекта");

        System.out.println("\n" + "=".repeat(60));
        System.out.println("ВЫВОД: Улучшенная версия защищает от ошибок");
        System.out.println("   и делает код более надежным и предсказуемым!");
        System.out.println("=".repeat(60));
    }
}