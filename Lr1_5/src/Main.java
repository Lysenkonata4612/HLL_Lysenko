/** Вариант 13. Программа считывает из консоли 3 целых числа
 * и выводит количество положительных чисел.*/
import java.util.Scanner;

public class Main {

    /**
     * Метод предназначен для определения количества положительных чисел
     * @param args Не используется.
     * @return Ничего.
     */
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int[] a = new int[3];
        System.out.println("Введите 3 целых числа");
        for (int i = 0; i < 3; i++)
            a[i] = in.nextInt();

        int k=0;
        for(int i=0; i<3; i++) {
            if (a[i]>0) {
                k++;}
        }
        if (k>0)
            System.out.println("Количество положительных чисел = "+ k);
        else System.out.println("Положительных чисел нет");
    }
}