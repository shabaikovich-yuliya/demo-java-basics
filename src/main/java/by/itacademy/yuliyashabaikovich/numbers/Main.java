package by.itacademy.yuliyashabaikovich.numbers;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите целое число от 0 до 9");
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();

        System.out.println(convertToString(number));
    }

    public static String convertToString(int number) {
        switch (number) {
            case 0:
                return "Ноль";
            case 1:
                return "Один";
            case 2:
                return "Два";
            case 3:
                return "Три";
            case 4:
                return "Четыре";
            case 5:
                return "Пять";
            case 6:
                return "Шесть";
            case 7:
                return "Семь";
            case 8:
                return "Восемь";
            case 9:
                return "Девять";
            default:
                return "Ошибка";
        }
    }
}
