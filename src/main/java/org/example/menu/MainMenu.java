package org.example.menu;

import org.example.service.CarService;
import org.example.service.FileService;
import org.example.strategy.*;
import org.example.comparator.*;
import java.util.Scanner;

/**
 * Главное меню приложения.
 */
public class MainMenu {
    private final CarService carService = new CarService();
    private final FileService fileService = new FileService();
    private final Scanner scanner = new Scanner(System.in);
    private boolean running = true;

    /**
     * Запускает главный цикл приложения.
     */
    public void run() {
        System.out.println("ПРИЛОЖЕНИЕ ДЛЯ РАБОТЫ С АВТОМОБИЛЯМИ");
        System.out.println("==========================================\n");

        while (running) {
            printMainMenu();
            int choice = getMenuChoice(0, 9);

            switch (choice) {
                case 1 -> fillManually();
                case 2 -> fillRandom();
                case 3 -> fillFromFile();
                case 4 -> displayCars();
                case 5 -> sortMenu();
                case 6 -> sortEvenPowerMenu();
                case 7 -> saveToFileMenu();
                case 8 -> searchMenu();
                case 9 -> clearList();
                case 0 -> exit();
                default -> System.out.println("❌ Неверный выбор!");
            }

            if (running) {
                System.out.println("\n" + "─".repeat(50));
                System.out.print("Нажмите Enter для продолжения...");
                scanner.nextLine();
            }
        }
    }

    private void printMainMenu() {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║                 ГЛАВНОЕ МЕНЮ                 ║");
        System.out.println("╠══════════════════════════════════════════════╣");
        System.out.println("║ 1. Заполнить список вручную                  ║");
        System.out.println("║ 2. Заполнить список случайными данными       ║");
        System.out.println("║ 3. Заполнить список из файла                 ║");
        System.out.println("║ 4. Показать список автомобилей               ║");
        System.out.println("║ 5. Отсортировать список                      ║");
        System.out.println("║ 6. Сортировка только четных значений (Доп1)  ║");
        System.out.println("║ 7. Сохранить в файл (Доп2)                   ║");
        System.out.println("║ 8. Найти модель (Доп4 - многопоточный)       ║");
        System.out.println("║ 9. Очистить список                           ║");
        System.out.println("║ 0. Выйти                                     ║");
        System.out.println("╚══════════════════════════════════════════════╝");
        System.out.print("\nВыберите действие (0-9): ");
    }

    private void fillManually() {
        carService.fillManually(scanner);
    }

    private void fillRandom() {
        System.out.print("\nСколько автомобилей сгенерировать? ");
        int count = getMenuChoice(1, 1000);
        carService.fillRandom(count);
    }

    private void fillFromFile() {
        System.out.print("\nВведите имя файла: ");
        String filename = scanner.nextLine();
        carService.fillFromFile(filename);
    }

    private void displayCars() {
        carService.displayCars();
    }

    private void sortMenu() {
        System.out.println("\n╔══════════════════════════════════════════════╗");
        System.out.println("║              ВЫБОР АЛГОРИТМА                 ║");
        System.out.println("╠══════════════════════════════════════════════╣");
        System.out.println("║ 1. Пузырьковая сортировка                    ║");
        System.out.println("║ 2. Сортировка выбором                        ║");
        System.out.println("║ 3. Сортировка вставками                      ║");
        System.out.println("╚══════════════════════════════════════════════╝");
        System.out.print("Выберите алгоритм (1-3): ");

        int algoChoice = getMenuChoice(1, 3);
        SortStrategy strategy = switch (algoChoice) {
            case 1 -> new BubbleSortStrategy();
            case 2 -> new SelectionSortStrategy();
            case 3 -> new InsertionSortStrategy();
            default -> new BubbleSortStrategy();
        };

        System.out.println("\n╔══════════════════════════════════════════════╗");
        System.out.println("║              ВЫБОР КРИТЕРИЯ                  ║");
        System.out.println("╠══════════════════════════════════════════════╣");
        System.out.println("║ 1. По мощности                               ║");
        System.out.println("║ 2. По модели                                 ║");
        System.out.println("║ 3. По году производства                      ║");
        System.out.println("╚══════════════════════════════════════════════╝");
        System.out.print("Выберите критерий (1-3): ");

        int criteriaChoice = getMenuChoice(1, 3);
        var comparator = switch (criteriaChoice) {
            case 1 -> new CarPowerComparator();
            case 2 -> new CarModelComparator();
            case 3 -> new CarYearComparator();
            default -> new CarPowerComparator();
        };

        carService.sortWithStrategy(strategy, comparator);
    }

    private void sortEvenPowerMenu() {
        System.out.println("\n╔══════════════════════════════════════════════╗");
        System.out.println("║     СОРТИРОВКА ТОЛЬКО ЧЕТНЫХ ЗНАЧЕНИЙ        ║");
        System.out.println("╠══════════════════════════════════════════════╣");
        System.out.println("║ 1. Пузырьковая сортировка                    ║");
        System.out.println("║ 2. Сортировка выбором                        ║");
        System.out.println("║ 3. Сортировка вставками                      ║");
        System.out.println("╚══════════════════════════════════════════════╝");
        System.out.print("Выберите алгоритм (1-3): ");

        int choice = getMenuChoice(1, 3);
        SortStrategy strategy = switch (choice) {
            case 1 -> new BubbleSortStrategy();
            case 2 -> new SelectionSortStrategy();
            case 3 -> new InsertionSortStrategy();
            default -> new BubbleSortStrategy();
        };

        carService.sortEvenPowerOnly(strategy);
    }

    private void saveToFileMenu() {
        if (carService.getCount() == 0) {
            System.out.println("❌ Список автомобилей пуст!");
            return;
        }

        System.out.print("\nВведите имя файла (например: cars.txt): ");
        String filename = scanner.nextLine();

        System.out.print("Введите описание для сохранения: ");
        String description = scanner.nextLine();

        fileService.saveToFileAppend(carService.getCars(), filename, description);
    }

    private void searchMenu() {
        if (carService.getCount() == 0) {
            System.out.println("❌ Список автомобилей пуст!");
            return;
        }

        System.out.print("\nВведите модель для поиска: ");
        String model = scanner.nextLine();

        carService.countOccurrencesParallel(model);
    }

    private void clearList() {
        System.out.print("\nВы уверены, что хотите очистить список? (да/нет): ");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("да") || confirmation.equalsIgnoreCase("y")) {
            carService.clear();
        } else {
            System.out.println("❌ Отменено");
        }
    }

    private void exit() {
        System.out.println("\n==========================================");
        System.out.println("Спасибо за использование приложения!");
        System.out.println("До свидания! 👋");
        System.out.println("==========================================");
        running = false;
    }

    private int getMenuChoice(int min, int max) {
        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice >= min && choice <= max) {
                    return choice;
                } else {
                    System.out.printf("❌ Введите число от %d до %d: ", min, max);
                }
            } catch (NumberFormatException e) {
                System.out.printf("❌ Введите число от %d до %d: ", min, max);
            }
        }
    }
}