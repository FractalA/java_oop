package lab1;
import java.util.Scanner;

public class StringCalculator {
    public static int Add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }
        String[] delimiters = { ",|\\\\n" };
        String[] numArr = numbers.split(String.join("|", delimiters));
        int res = 0;
        for (String num : numArr) {
            num = num.trim();
            try {
                int x = Integer.parseInt(num);
                res += x;
            } catch (NumberFormatException e) {
                System.err.println("помилка: недійсне число ігнорується.");
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("введіть рядок: ");
        String input = scanner.nextLine();
        int res = Add(input);
        System.out.println("результат: "+res);
        scanner.close();
    }
}
