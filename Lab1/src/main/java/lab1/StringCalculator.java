package lab1;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    public static int Add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        String delimiter = ",|\\\\n";

        if (numbers.startsWith("//")) {
            Pattern pattern = Pattern.compile("//(.*?)\\\\n");
            Matcher matcher = pattern.matcher(numbers);
            if (matcher.find()) {
                String customDelimiter = matcher.group(1);
                delimiter = Pattern.quote(customDelimiter) + "|\\\\n|,";
                numbers = numbers.substring(matcher.end());
            }
        }

        String[] numArr = numbers.split(delimiter);
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
        System.out.println("результат: " + res);
        scanner.close();
    }
}