import Classes.Car;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean check = true;
        while (check) {
            System.out.println("Выберите пункт меню: \n" +
                    "1 - Создание объекта класса \n" +
                    "2 - Пункт 2 \n" +
                    "0 - Выход \n" +
                    "Ваш выбор:");

            Scanner in = new Scanner(System.in);
            String menu = in.nextLine();

            switch(menu){
                case "1":{
                    Car car =new Car.CarBuilder().setModel("Audi R8").setPower(20000).setYearOfCreate(2005).build();

                    System.out.println(car.toString());
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
}