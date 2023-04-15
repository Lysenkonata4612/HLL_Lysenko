package com.company;
import java.io.FileNotFoundException;
import java.util.Scanner;

/** Программа реализует выполнение 13 варианта 2 лабораторной
 */
public class Main {
 /** Метод main предназначен для выбора режима ввода данных,
 * выбора задачи и вызова соответствующего метода
  * @author Natalia @version 3.2
  * @exception FileNotFoundException При ошибке открытия файла.
 */
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Введите 1 для режима работы ввода параметров задач" +
                " с клавиатуры или 2 для ввода параметров из файла");
        Scanner scanner = new Scanner(System.in);
        int inputMode = scanner.nextInt();
        if (inputMode == 1 || inputMode == 2) {
            Tasks tasks = new Tasks();
                 tasks.task1(inputMode);
                 tasks.task2(inputMode);
                 tasks.task3(inputMode);
                 tasks.task4(inputMode);
                 tasks.task5(inputMode);
                 tasks.task6(inputMode);
                 tasks.task7(inputMode);
                 tasks.task8(inputMode);
        }
    }
}