package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/** Класс Tasks описывает основные задания 2 лабораторной работы.
 */
public class Tasks {

    /** Метод task1 вычисляет значение выражения по формуле 13 варианта.
     * @param inputMode параметр передает режим ввода данных.
     * @exception FileNotFoundException При ошибке открытия файла.
     */
    public void task1(int inputMode) throws FileNotFoundException {
        double x;
        double y;
        double result;

        if (inputMode == 1) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите x ");
            x = scanner.nextDouble();
            System.out.print("Введите y ");
            y = scanner.nextDouble();
        } else {
            String path = "1taskData.txt";
            Scanner scanner = new Scanner(new File(path));
            x = scanner.nextDouble();
            System.out.println("Ввод x из файла, x = " + x);
            y = scanner.nextDouble();
            System.out.println("Ввод y из файла, y = " + y);
        }

        result = Math.pow(2, -x) - Math.cos(x) + Math.sin(2 * x * y);
        System.out.println("Результат: " + result);
    }

    /**Метод task2 находит площадь трапеции по заданым
     * основаниям и углу при большем основании а.
     * @param inputMode параметр передает режим ввода данных.
     * @exception FileNotFoundException При ошибке открытия файла.
     */
    public void task2(int inputMode) throws FileNotFoundException {
        double sideA;
        double sideB;
        double alpha;

        if (inputMode == 1) {
            Scanner scan = new Scanner(System.in);
            System.out.print("Введите большее основание трапеции a: ");
            sideA = scan.nextDouble();
            System.out.print("Введите меньшее основание трапеции b: ");
            sideB = scan.nextDouble();
            System.out.print("Введите угол при большем основании: ");
            alpha = scan.nextDouble();
        }
        else {
            String path = "2taskData.txt";
            Scanner scanner = new Scanner(new File(path));
            sideA = scanner.nextDouble();
            System.out.println("Ввод из файла, a = " + sideA);
            sideB = scanner.nextDouble();
            System.out.println("Ввод из файла, b = " + sideB);
            alpha = scanner.nextDouble();
            System.out.println("Ввод из файла, альфа = " + alpha);
        }
        System.out.println("Площадь трапеции = " + (0.5 * (Math.pow(sideA, 2) - Math.pow(sideB, 2)) * Math.tan(Math.toRadians(alpha))));
    }

    /** Метод task3 считает количество отрицательных чисел среди a, b, c
     * @param inputMode параметр передает режим ввода данных.
     * @exception FileNotFoundException При ошибке открытия файла.
     */
    public void task3(int inputMode) throws FileNotFoundException {
        float[] array = new float[3];
        int kol=0;
        if (inputMode == 1) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Введите числа a b c через пробел: ");
            for (int i = 0; i < 3; i++) {
                array[i] = scan.nextFloat(); // Заполняем массив элементами, введёнными с клавиатуры
            }
        }
        else{
            String path = "3taskData.txt";
            Scanner scanner = new Scanner(new File(path));
            System.out.println("Ввод a b c из файла: ");
            for (int i = 0; i < 3; i++) {
                array[i] = scanner.nextFloat(); // Заполняем массив элементами, введёнными из файла
                System.out.println(array[i]);
            }
        }
        for (int i = 0; i < 3; i++)
        {
           if (array[i]<0)
               kol++;
        }
        System.out.println("Колличество отрицательных чисел = "+ kol);
    }

    /** Метод task4 определяет, принадлежит ли точка A(x, y) треугольнику с вершинами в точках (x1, у1), (х2, у2), (х3, у3).
     * @param inputMode параметр передает режим ввода данных (1 - ввод данных с клавиатуры, любое другое число - ввод данных из файла).
     * @throws FileNotFoundException выбрасывает исключение, если происходит ошибка открытия файла.
     */
    public void task4(int inputMode) throws FileNotFoundException {
        // Создание массива, который содержит координаты вершин треугольника и точки A
        float[] tr = new float[8];
        // Ввод координат треугольника и точки А
        if (inputMode == 1) {
            Scanner scan = new Scanner(System.in);

            System.out.println("Введите координаты вершин треугольника (x1 у1 х2 у2 х3 у3): ");
            for (int i = 0; i < 6; i++) {
                tr[i] = scan.nextFloat();
            }
            System.out.println("Введите координаты точки А (x, у): ");
            for (int i = 6; i < 8; i++) {
                tr[i] = scan.nextFloat();
            }
        }
        // Ввод координат треугольника и точки А из файла
        else {
            String path = "4taskData.txt";
            Scanner scanner = new Scanner(new File(path));
            System.out.println("Ввод координат вершин треугольника (x1 у1 х2 у2 х3 у3) из файла: ");
            for (int i = 0; i < 8; i++) {
                tr[i] = scanner.nextFloat();
                if (i==6) {
                    System.out.println("A (x,y): ");
                }
                System.out.println(tr[i]);
            }
        }
        // a, b, c - вспомогательные, предназначены для вычисления площади треугольника ABC
        float a = (tr[6] - tr[0]) * (tr[3] - tr[1]) - (tr[7] - tr[1]) * (tr[2] - tr[0]);
        float b = (tr[6] - tr[2]) * (tr[5] - tr[3]) - (tr[7] - tr[3]) * (tr[4] - tr[2]);
        float c = (tr[6] - tr[4]) * (tr[1] - tr[5]) - (tr[7] - tr[5]) * (tr[0] - tr[4]);
        // Проверка принадлежности точки А треугольнику
        // Если все три знака площадей совпадают, то точка лежит внутри треугольника
        if ((a >= 0 && b >= 0 && c >= 0) || (a <= 0 && b <= 0 && c <= 0)) {
            System.out.println("Точка А (" + tr[6] + ", " + tr[7] + ") принадлежит треугольнику");
        } else {
            System.out.println("Точка А (" + tr[6] + ", " + tr[7] + ") не принадлежит треугольнику");
        }
    }

    /**Метод task5  по вводимому числу от 1 до 11 (номеру класса) выдает
     * соответствующее сообщение «Привет, k-классник».
     * @param inputMode параметр передает режим ввода данных.
     * @exception FileNotFoundException При ошибке открытия файла.
     */
    public void task5(int inputMode) throws FileNotFoundException {
        int number;
        if (inputMode == 1) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите число ");
            number = scanner.nextInt();
        } else {
            String path = "5taskData.txt";
            Scanner scanner = new Scanner(new File(path));
            number = scanner.nextInt();
            System.out.println("Ввод из файла числa " + number);
        }

        if (number > 0 && number < 12) {
            switch (number) {
                case 1 -> System.out.println("Привет, Первоклассник");
                case 2 -> System.out.println("Привет, Второклассник");
                case 3 -> System.out.println("Привет, Третьеклассник");
                case 4 -> System.out.println("Привет, Четвероклассник");
                case 5 -> System.out.println("Привет, Пятиклассник");
                case 6 -> System.out.println("Привет, Шестиклассник");
                case 7 -> System.out.println("Привет, Семиклассник");
                case 8 -> System.out.println("Привет, Восьмиклассник");
                case 9 -> System.out.println("Привет, Девятиклассник");
                case 10 -> System.out.println("Привет, Десятиклассник");
                case 11 -> System.out.println("Привет, Одиннадцатиклассник");
            }
        }
        else System.out.println("Такого класса не существует");
    }

    /** Метод task6 находит все делители натурального числа n.
     * @param inputMode параметр передает режим ввода данных.
     * @exception FileNotFoundException При ошибке открытия файла.
     */
    public void task6(int inputMode) throws FileNotFoundException {
        int n;
        if (inputMode == 1) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите число ");
            n = scanner.nextInt();
        } else {
            String path = "6taskData.txt";
            Scanner scanner = new Scanner(new File(path));
            n = scanner.nextInt();
            System.out.println("Ввод из файла числa " + n);
        }
        System.out.println("Делители числa " + n);
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                System.out.print(i + " ");
            }
        }
    }
    /** Метод task7 находит сумму n членов ряда
     * @param inputMode параметр передает режим ввода данных.
     * @exception FileNotFoundException При ошибке открытия файла.*/
    public void task7(int inputMode) throws FileNotFoundException {
        int n;
        float x;
        float sum = 0;

        if (inputMode == 1) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите число n ");
            n = scanner.nextInt();
            System.out.println("Введите число x ");
            x = scanner.nextFloat();
        } else {
            String path = "7taskData.txt";
            Scanner scanner = new Scanner(new File(path));
            n = scanner.nextInt();
            System.out.println("Ввод из файла числa n " + n);
            x = scanner.nextFloat();
            System.out.println("Ввод из файла числa x " + x);
        }

        for (int i = 1; i <= n; i++) {
            sum += Math.cos(2*i*x)/i;
        }
        System.out.println("Сумма n членов ряда = " + sum);
    }
    /** Метод task8 вычисляет сумму целых положительных чисел,
     * больших M, меньших N и кратных k. Полученное число выводится на экран.
     * @param inputMode параметр передает режим ввода данных.
     * @exception FileNotFoundException При ошибке открытия файла.*/
    public void task8(int inputMode) throws FileNotFoundException {
        int M;
        int N;
        int k;
        int sum = 0;

        if (inputMode == 1) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите число M: ");
            M = scanner.nextInt();
            System.out.print("Введите число N: ");
            N = scanner.nextInt();
            System.out.print("Введите число k: ");
            k = scanner.nextInt();
        } else {
            String path = "8taskData.txt";
            Scanner scanner = new Scanner(new File(path));
            M = scanner.nextInt();
            System.out.println("Ввод из файла числa M " + M);
            N = scanner.nextInt();
            System.out.println("Ввод из файла числa N " + N);
            k = scanner.nextInt();
            System.out.println("Ввод из файла числa k " + k);
        }

        for (int i = M + 1; i < N; i++) {
            if (i % k == 0) {
                sum += i;
            }
        }
        System.out.println("Сумма чисел, больших " + M + ", меньших " + N + " и кратных " + k + ": " + sum);
    }
}