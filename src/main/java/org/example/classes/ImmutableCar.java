package org.example.classes;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Улучшенная иммутабельная версия класса Car.
 * Демонстрирует best practices: валидация, иммутабельность.
 */
public final class ImmutableCar {
    // Все поля final - класс иммутабельный
    private final String id;
    private final String model;
    private final double power;
    private final int manufactureYear;
    private final LocalDate createdAt;

    // Приватный конструктор - только Builder может создавать
    private ImmutableCar(Builder builder) {
        this.id = builder.id;
        this.model = builder.model;
        this.power = builder.power;
        this.manufactureYear = builder.manufactureYear;
        this.createdAt = builder.createdAt;
    }

    // ТОЛЬКО ГЕТТЕРЫ - никаких сеттеров!
    public String getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public double getPower() {
        return power;
    }

    public int getManufactureYear() {
        return manufactureYear;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    // ДОПОЛНИТЕЛЬНЫЕ ПОЛЕЗНЫЕ МЕТОДЫ
    public int getAge() {
        return LocalDate.now().getYear() - manufactureYear;
    }

    public boolean isVintage() {
        return getAge() > 25;
    }

    public boolean isElectric() {
        return model.toLowerCase().contains("electric") ||
                model.toLowerCase().contains("ev") ||
                model.toLowerCase().contains("tesla");
    }

    @Override
    public String toString() {
        return String.format("Car[id=%s, model='%s', power=%.1f, year=%d, age=%d, vintage=%s]",
                id, model, power, manufactureYear, getAge(), isVintage());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImmutableCar car = (ImmutableCar) o;
        return Double.compare(car.power, power) == 0 &&
                manufactureYear == car.manufactureYear &&
                Objects.equals(id, car.id) &&
                Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, power, manufactureYear);
    }

    /**
     * Статический фабричный метод для удобного создания.
     */
    public static ImmutableCar create(String model, double power, int year) {
        return new Builder()
                .model(model)
                .power(power)
                .manufactureYear(year)
                .build();
    }

    /**
     * Builder с ВАЛИДАЦИЕЙ - главное улучшение!
     */
    public static class Builder {
        private String id;
        private String model;
        private double power;
        private int manufactureYear;
        private LocalDate createdAt;

        public Builder() {
            // Генерируем уникальный ID по умолчанию
            this.id = "car_" + System.currentTimeMillis() + "_" + (int)(Math.random() * 1000);
            this.createdAt = LocalDate.now();
        }

        public Builder id(String id) {
            this.id = Objects.requireNonNull(id, "ID не может быть null");
            return this;
        }

        public Builder model(String model) {
            // ВАЛИДАЦИЯ: модель не может быть пустой
            if (model == null || model.trim().isEmpty()) {
                throw new IllegalArgumentException("Модель не может быть пустой");
            }
            if (model.length() > 100) {
                throw new IllegalArgumentException("Название модели слишком длинное");
            }
            this.model = model.trim();
            return this;
        }

        public Builder power(double power) {
            // ВАЛИДАЦИЯ: мощность должна быть реалистичной
            if (power <= 0) {
                throw new IllegalArgumentException("Мощность должна быть положительной: " + power);
            }
            if (power > 1500) {
                throw new IllegalArgumentException("Нереалистичная мощность (максимум 1500 л.с.): " + power);
            }
            this.power = power;
            return this;
        }

        public Builder manufactureYear(int year) {
            // ВАЛИДАЦИЯ: год должен быть реалистичным
            int currentYear = LocalDate.now().getYear();
            if (year < 1886) {
                throw new IllegalArgumentException("Первый автомобиль был создан в 1886 году: " + year);
            }
            if (year > currentYear + 1) {
                throw new IllegalArgumentException("Год выпуска не может быть в будущем: " + year);
            }
            this.manufactureYear = year;
            return this;
        }

        public Builder createdAt(LocalDate date) {
            this.createdAt = Objects.requireNonNull(date, "Дата создания не может быть null");
            return this;
        }

        public ImmutableCar build() {
            // Финальная проверка перед созданием объекта
            validate();
            return new ImmutableCar(this);
        }

        private void validate() {
            if (model == null) {
                throw new IllegalStateException("Модель должна быть указана");
            }
            if (manufactureYear == 0) {
                throw new IllegalStateException("Год выпуска должен быть указан");
            }
            if (id == null) {
                throw new IllegalStateException("ID должен быть указан");
            }
        }
    }
}