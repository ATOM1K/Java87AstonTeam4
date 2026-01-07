package org.example.Classes;

/// Класс Автомобиль
public class Car {
    /// Мощность
    double power;
    ///Модель
    String model;
    ///Год производства
    int yearOfCreate;

    public Car(CarBuilder carBuilder) {
        this.power = carBuilder.power;
        this.model = carBuilder.model;
        this.yearOfCreate = carBuilder.yearOfCreate;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYearOfCreate() {
        return yearOfCreate;
    }

    public void setYearOfCreate(int yearOfCreate) {
        this.yearOfCreate = yearOfCreate;
    }

    @Override
    public String toString() {
        return "Car{" +
                "power=" + power +
                ", model='" + model + '\'' +
                ", yearOfCreate=" + yearOfCreate +
                '}';
    }

    @Override
    public int hashCode() {
        int hash = 13;
        hash += (int) (13*this.power);
        hash += 13*this.model.hashCode();
        hash += 13*this.yearOfCreate;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;

        if (obj == null || obj.getClass() != this.getClass()) return false;

        Car c = (Car) obj;

        return power == c.power
                & CharSequence.compare(model, c.model) == 0
                & yearOfCreate == c.yearOfCreate;
    }

    /// Класс Билдера
    public static class CarBuilder{
        /// Мощность
        private double power;
        ///Модель
        private String model;
        ///Год производства
        private int yearOfCreate;

        public CarBuilder setPower(double power) {
            this.power = power;
            return this;
        }

        public CarBuilder setModel(String model) {
            this.model = model;
            return this;
        }

        public CarBuilder setYearOfCreate(int yearOfCreate) {
            this.yearOfCreate = yearOfCreate;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }
}
