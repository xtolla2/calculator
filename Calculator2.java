import java.util.Scanner;

public class Calculator2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите любое арефметическое выражение");
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Выход из калькулятора");
                break;
            }
            try {
                String result = calc(input);
                System.out.println("Результат: " + result);
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
        scanner.close();
    }
    static String calc(String input) {
        String[] tokens = input.split(" ");
        if (tokens.length != 3) {
            throw new IllegalArgumentException("Некоректное выражение");
        }
        String num1Str = tokens[0];
        String operator = tokens[1];
        String num2Str = tokens[2];
        int num1;
        int num2;
        try {
            num1 = RomanConvert.convertToArabic(num1Str);
            num2 = RomanConvert.convertToArabic(num2Str);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Только арабские цифры от 1 до 10");
        }
        int result;
        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 == 0) {
                    throw new IllegalArgumentException("Деление на ноль невозможно");
                }
                result = num1 / num2;
                break;
            default:
                throw new IllegalArgumentException("Операция не поддерживается");
        }

        if (result <= 0) {
            throw new IllegalArgumentException("Результат с римским числом должно быть равно или больше нуля");
        }
        return RomanConvert.convertToRoman(result);
    }
}
class RomanConvert{
    private static final String[] roman_nummerals = {
            "I" , "II" , "III" , "IV", "V" , "VI" , "VII" , "VIII" , "IX" , "X"};
    static int convertToArabic(String roman) {
        for (int i = 0; i < roman_nummerals.length; i++){
            if(roman_nummerals[i].equals(roman)){
                return i + 1;
            }
        }
        throw new IllegalArgumentException("Неккоректно введено число.");
    }
    static String convertToRoman (int arabic) {
        if (arabic < 1 || arabic > 10){
            throw new IllegalArgumentException("Число должно быть в диапозоне от 1 до 10");
        }
        return roman_nummerals[arabic - 1];
    }
}