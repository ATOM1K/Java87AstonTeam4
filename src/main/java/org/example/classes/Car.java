package org.example.classes;

/// Класс Автомобиль
public class Car implements Comparable<Car> {
    /// Мощность
    double power;
    ///Модель
    String model;
    ///Год производства
    int yearOfCreate;

    public Car(double power, String model, int yearOfCreate) {
        this.power = power;
        this.model = model;
        this.yearOfCreate = yearOfCreate;
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

    @Override
    public int compareTo(Car otherСar) {
        int yearCompare = Integer.compare(otherСar.yearOfCreate, this.yearOfCreate); // Новые вперед
        if(yearCompare != 0) return yearCompare;

        int powerCompare = Double.compare(otherСar.power, this.power);
        if(powerCompare != 0) return powerCompare;

        return this.model.compareTo(otherСar.model);
    }
}
