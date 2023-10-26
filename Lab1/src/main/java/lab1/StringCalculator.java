package lab1;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;

public class StringCalculator {
    public static int Add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        String delimiter = ",|\\\\n";

        if (numbers.startsWith("//")) {
            Pattern pattern = Pattern.compile("//(\\[(.*?)\\]|(.*?))\\\\n(.+)");
            Matcher matcher = pattern.matcher(numbers);
            if (matcher.find()) {
                String customDelimiterGroup = matcher.group(1);
                if (matcher.group(2) != null) {
                    // Формат с квадратными скобками
                    Pattern delimiterPattern = Pattern.compile("\\[(.*?)\\]");
                    Matcher delimiterMatcher = delimiterPattern.matcher(customDelimiterGroup);
                    List<String> customDelimiters = new ArrayList<>();
                    while (delimiterMatcher.find()) {
                        customDelimiters.add(Pattern.quote(delimiterMatcher.group(1)));
                    }
                    delimiter = String.join("|", customDelimiters);
                } else {
                    delimiter = Pattern.quote(customDelimiterGroup);
                }
                numbers = matcher.group(4);
            }
        }

        delimiter += "|,";

        String[] numArr = numbers.split(delimiter);
        int res = 0;
        List<Integer> negativeNumbers = new ArrayList<>();
        List<Integer> largeNumbers = new ArrayList<>();
        for (String num : numArr) {
            num = num.trim();
            try {
                int x = Integer.parseInt(num);
                if (x < 0) {
                    negativeNumbers.add(x);
                    System.err.println("помилка: від'ємне число " + x + " ігнорується.");
                } else if (x > 1000) {
                    largeNumbers.add(x);
                    System.err.println("помилка: велике число " + x + " ігнорується.");
                } else {
                    res += x;
                }
            } catch (NumberFormatException e) {
                System.err.println("помилка: недійсне число " + num + " ігнорується.");
            }
        }

        if (!negativeNumbers.isEmpty()) {
            System.err.println("Від'ємні числа: " + negativeNumbers);
        }

        if (!largeNumbers.isEmpty()) {
            System.err.println("Великі числа: " + largeNumbers);
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