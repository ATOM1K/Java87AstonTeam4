package org.example.demo;

import org.example.menu.MainMenu;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class FullAppDemo {
    public static void main(String[] args) {
        // Устанавливаем кодировку вывода
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println("🚗 ПОЛНАЯ ВЕРСИЯ ПРИЛОЖЕНИЯ (ВСЕ УЛУЧШЕНИЯ)");
        System.out.println("============================================\n");

        System.out.println("Реализовано согласно заданию:");
        System.out.println("1. ✅ Сортировка по 3 полям (мощность, модель, год)");
        System.out.println("2. ✅ Паттерн Стратегия (3 алгоритма сортировки)");
        System.out.println("3. ✅ Цикл программы с меню");
        System.out.println("4. ✅ 3 способа заполнения (вручную, случайно, из файла)");
        System.out.println("5. ✅ Доп.1: Сортировка только четных значений");
        System.out.println("6. ✅ Доп.2: Запись в файл с добавлением");
        System.out.println("7. ✅ Доп.3: Streams API для генерации");
        System.out.println("8. ✅ Доп.4: Многопоточный поиск");
        System.out.println("9. ✅ Валидация данных в Builder");
        System.out.println("10. ✅ Иммутабельный класс Car");
        System.out.println("11. ✅ Streams API операции (Доп.3)\n");

        MainMenu menu = new MainMenu();
        menu.run();
    }
}