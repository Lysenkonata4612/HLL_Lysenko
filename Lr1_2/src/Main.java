/**
 * Программа, в которой все переданные во входную строку
 * аргументы выводятся на экран в обратной порядке.
 */
import java.util.Scanner;
public class Main {
    /**
     * Метод предназначен для просмотра символов строки
     * в обратном порядке и посимвольный вывода в консоль
     * @param args Не используется.
     * @return Ничего.
     * */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите строку:");
        String str;
        str = in.nextLine();
        for (int i = str.length() - 1; i >= 0; i--){
            System.out.print(str.charAt(i));
        }
    }
}