import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean check = true;
        while (check) {
            System.out.println("Выберите пункт меню: \n" +
                    "1 - Пункт 1 \n" +
                    "2 - Пункт 2 \n" +
                    "0 - Выход \n" +
                    "Ваш выбор:");

            Scanner in = new Scanner(System.in);
            String menu = in.nextLine();

            switch(menu){
                case "1":{
                    System.out.println("Пункт 1");
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