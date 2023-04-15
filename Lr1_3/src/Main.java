/** Программа вычисляет числа Фибоначчи –
 * последовательность чисел, в котором каждое следующее
 * число равно сумме двух предыдущих.*/
public class Main {
    /**
     * Медод предназначен для вычисления чисел фибоначи
     * новый элемент массива явдляется суммой двух предыдущих элементов
     * @param args Не используется.
     * @return Ничего.
     */
    public static void main(String[] args) {
        int n = 15;
        int[] arr = new int[n];
        arr[0] = 1;
        arr[1] = 1;
        for (int i = 2; i<n; ++i)
        {
            System.out.println(arr[i-2]);
            arr[i]=arr[i-1]+arr[i-2];
        }
    }
}