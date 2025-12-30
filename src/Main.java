import Classes.Car;

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
                    "2 - Пункт 2 \n" +
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
                    System.out.println("Пункт 2");
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
}