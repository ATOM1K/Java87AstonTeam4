package org.example.service;

import org.example.improved.Car;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Сервис для работы с файлами (доп. задание 2).
 */
public class FileService {

    /**
     * Сохраняет список автомобилей в файл (добавление данных).
     */
    public void saveToFileAppend(List<Car> cars, String filename, String description) {
        Path path = Paths.get(filename);

        try {
            // Создаем директорию если не существует
            if (path.getParent() != null) {
                Files.createDirectories(path.getParent());
            }

            // Формируем содержимое для записи
            StringBuilder content = new StringBuilder();

            // Добавляем заголовок с временем и описанием
            String timestamp = LocalDateTime.now().format(
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            content.append("\n").append("=".repeat(60)).append("\n");
            content.append("Сохранено: ").append(timestamp).append("\n");
            content.append("Описание: ").append(description).append("\n");
            content.append("Количество автомобилей: ").append(cars.size()).append("\n");
            content.append("=".repeat(60)).append("\n\n");

            // Добавляем данные об автомобилях
            for (int i = 0; i < cars.size(); i++) {
                Car car = cars.get(i);
                content.append(String.format("%3d. %-20s | %6.0f л.с. | %4d год | ID: %s%n",
                        i + 1,
                        car.getModel(),
                        car.getPower(),
                        car.getManufactureYear(),
                        car.getId()));
            }

            // Записываем в файл с ДОБАВЛЕНИЕМ (APPEND)
            Files.writeString(path, content.toString(),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);

            System.out.println("✅ Данные сохранены в файл: " + filename);
            System.out.println("   Режим: добавление (старые данные сохранены)");

        } catch (IOException e) {
            System.err.println("❌ Ошибка при сохранении в файл: " + e.getMessage());
        }
    }

    /**
     * Читает автомобили из файла (упрощенная версия).
     */
    public List<Car> readFromFile(String filename) {
        // TODO: реализовать полноценное чтение из файла
        System.out.println("Чтение из файла будет реализовано в следующей версии");
        return List.of();
    }
}