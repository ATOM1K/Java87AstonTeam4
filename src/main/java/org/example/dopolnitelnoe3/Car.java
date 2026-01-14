package org.example.dopolnitelnoe3;
public class Car {
    private String model;
    private int power; // мощность в л.с.
    private int yearOfCreate;

    public Car(String model, int power, int yearOfCreate) {
        this.model = model;
        this.power = power;
        this.yearOfCreate = yearOfCreate;
    }

    // Геттеры
    public String getModel() { return model; }
    public int getPower() { return power; }
    public int getYearOfCreate() { return yearOfCreate; }

    // Сеттеры
    public void setModel(String model) { this.model = model; }
    public void setPower(int power) { this.power = power; }
    public void setYearOfCreate(int yearOfCreate) { this.yearOfCreate = yearOfCreate; }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", power=" + power +
                ", yearOfCreate=" + yearOfCreate +
                '}';
    }
}