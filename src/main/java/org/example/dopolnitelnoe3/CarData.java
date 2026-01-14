package org.example.dopolnitelnoe3;

public class CarData {
    private String model;
    private int power;
    private int yearOfCreate;

    public CarData(String model, int power, int yearOfCreate) {
        this.model = model;
        this.power = power;
        this.yearOfCreate = yearOfCreate;
    }

    public String getModel() { return model; }
    public int getPower() { return power; }
    public int getYearOfCreate() { return yearOfCreate; }
}