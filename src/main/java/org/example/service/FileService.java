package org.example.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.example.classes.ImmutableCar;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Сервис для работы с файлами (доп. задание 2).
 */
public class FileService {

    /**
     * Сохраняет список автомобилей в файл (добавление данных).
     */
    public void saveToFileAppend(List<ImmutableCar> cars, String filename) {
        try {
            Path resourcesDir = Paths.get("src/main/resources");
            JSONArray jsonArray = new JSONArray();

            for (ImmutableCar car : cars) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("model", car.getModel());
                jsonObject.put("power", car.getPower());
                jsonObject.put("yearOfCreate", car.getManufactureYear());

                jsonArray.add(jsonObject);
            }

            String jsonString = jsonArray.toJSONString();
            Files.writeString(resourcesDir.resolve(filename), jsonString);
        }
        catch (Exception e)
        {
            System.out.println("Ошибка сохранения данных");
            e.printStackTrace();
        }
    }
}