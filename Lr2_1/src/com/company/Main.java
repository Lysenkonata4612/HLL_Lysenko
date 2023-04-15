package com.company;
import java.io.FileNotFoundException;
import java.util.Scanner;
/** Программа реализует выполнение 13 варианта 2 лабораторной
 */
public class Main {
 /** Метод main предназначен для выбора режима ввода данных,
 * выбора задачи и вызова соответствующего метода
  * @param args Не используется.
  * @exception FileNotFoundException При ошибке открытия файла.
 */
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Введите 1 для режима работы ввода параметров задач" +
                " с клавиатуры или 2 для ввода параметров из файла");
        Scanner scanner = new Scanner(System.in);
        int inputMode = scanner.nextInt();
        if (inputMode == 1 || inputMode == 2) {
            System.out.print("Введите номер задачи: ");
            int nTask = scanner.nextInt();
            Tasks tasks = new Tasks();
            switch (nTask) {
                case 1 -> tasks.task1(inputMode);
                case 2 -> tasks.task2(inputMode);
                case 3 -> tasks.task3(inputMode);
                case 4 -> tasks.task4(inputMode);
                case 5 -> tasks.task5(inputMode);
                case 6 -> tasks.task6(inputMode);
                case 7 -> tasks.task7(inputMode);
                case 8 -> tasks.task8(inputMode);
                default -> System.out.println("Такой задачи нет");
            }
        } else {
            System.out.println("Нет такого режима работы программы");
        }
    }
}