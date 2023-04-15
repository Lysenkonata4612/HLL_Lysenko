/** Программа вычисляет факториал целого числа */
import java.util.Scanner;
public class Main {
    /**
     * Метод расчитывает факториал умножением всех чисел от
     * одного до значения самого числа под факториалом
     * @param args Не используется.
     * @return Ничего.
     */
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите число:");
        int num = in.nextInt();//num - целое число для которого расчитывается факториал
        int fact = 1; //fact - искомый факториал

        for (int i = 1; i <= num; i++)
        {
            fact = fact * i;
        }
        System.out.println("Факториал числа " + num + " 4= " + fact);
    }
}
