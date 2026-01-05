package org.example;

import org.example.Classes.Car;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Car> listCar = new ArrayList<>();

        boolean check = true;
        while (check) {
            ViewList(listCar);
            System.out.println("Выберите пункт меню: \n" +
                    "1 - Заполнение списка вручную \n" +
                    "2 - Заполнение списка из файла \n" +
                    "3 - Запись списка в файл \n" +
                    "0 - Выход \n" +
                    "Ваш выбор:");

            Scanner in = new Scanner(System.in);
            String menu = in.nextLine();

            switch(menu){
                case "1":{
                    FillListHandle(listCar);
                    break;
                }
                case "2":{
                    System.out.println("Введите путь к файлу: ");
                    // "res/test.json
                    String pathFileStr = in.nextLine();

                    ReadFromFile(listCar, pathFileStr);
                    break;
                }
                case "3":{
                    System.out.println("Введите путь к файлу: ");
                    // "res/test2.json
                    String pathFileStr = in.nextLine();

                    WriteInFile(listCar, pathFileStr);
                    break;
                }
                case "0":{
                    check = false;
                    break;
                }
                default:
                    System.out.println("Введите корректное значение!");
            }
        }
    }

    /// Заполнение коллекции вручную
    private static void FillListHandle(ArrayList<Car> carList){
        String model = "";
        double power = 0;
        int yearOfCreate = 0;

        Scanner scan = new Scanner(System.in);

        System.out.println("Введите наименование модели автомобиля: ");
        model = scan.nextLine();
        System.out.println("Введите мощность автомобиля: ");
        power = scan.nextDouble();
        System.out.println("Введите год создания автомобиля: ");
        yearOfCreate = scan.nextInt();

        carList.add(new Car.CarBuilder().setModel(model).setPower(power).setYearOfCreate(yearOfCreate).build());
    }

    ///Визуальное представление списка
    private static void ViewList(ArrayList<Car> carList){
        System.out.println("Список автомобилей:");
        System.out.println("МОДЕЛЬ       | МОЩНОСТЬ        | ГОД ПРОИЗВОДСТВА     ");
        System.out.println("______________________________________________________");
        for (Car car : carList)
            System.out.printf("%s       | %f        | %d     \n",
                    car.getModel(), car.getPower(), car.getYearOfCreate());
    }

    /// Чтение из файла
    private static void ReadFromFile(ArrayList<Car> carList, String pathFileStr) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(pathFileStr)))
        {
            String lineFile;
            StringBuilder jsonString = new StringBuilder();
            while (null != (lineFile = reader.readLine()))
                jsonString.append(lineFile).append("\n");

            JSONParser parser = new JSONParser();
            JSONArray carJSONArray = (JSONArray) parser.parse(String.valueOf(jsonString));

            for(Object obj : carJSONArray){
                JSONObject car = (JSONObject) obj;

                String model = (String) car.get("model");
                double power = Double.parseDouble(car.get("power").toString());
                int yearOfCreate = Integer.parseInt(car.get("yearOfCreate").toString());

                carList.add(new Car.CarBuilder().setModel(model).setPower(power).setYearOfCreate(yearOfCreate).build());
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /// Запись в файл
    private static void WriteInFile(ArrayList<Car> carList, String pathFileStr){
        try {
            JSONArray jsonArray = new JSONArray();

            for (Car car : carList) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("model", car.getModel());
                jsonObject.put("power", car.getPower());
                jsonObject.put("yearOfCreate", car.getYearOfCreate());

                jsonArray.add(jsonObject);
            }

            String jsonString = jsonArray.toJSONString();
            Files.writeString(Path.of(pathFileStr), jsonString);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}