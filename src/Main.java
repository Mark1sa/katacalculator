import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите арифметическое выражение: ");
        String input = scanner.nextLine();

        try {
            String result = calc(input);
            System.out.println("Ответ: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        scanner.close();
    }

    static String calc(String input) {
        String[] parts = input.split("\\s+");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Некорректное количество аргументов");
        }

        String firstStr = parts[0];
        String operator = parts[1];
        String secondStr = parts[2];

        int firstNumber, secondNumber;
        boolean isRoman = false;

        try {
            firstNumber = Integer.parseInt(firstStr);
            secondNumber = Integer.parseInt(secondStr);
        } catch (NumberFormatException e) {
            // Если не удалось преобразовать в арабские, попробуем в римские
            firstNumber = Roman.toArabic(firstStr);
            secondNumber = Roman.toArabic(secondStr);
            isRoman = true;
        }

        // Проверяем, что числа в пределах от 1 до 10
        if (firstNumber < 1 || firstNumber > 10 || secondNumber < 1 || secondNumber > 10) {
            throw new IllegalArgumentException("Числа должны быть от 1 до 10 включительно");
        }

        int result;
        switch (operator) {
            case "+":
                result = firstNumber + secondNumber;
                break;
            case "-":
                result = firstNumber - secondNumber;
                break;
            case "*":
                result = firstNumber * secondNumber;
                break;
            case "/":
                if (secondNumber != 0) {
                    result = firstNumber / secondNumber;
                } else {
                    throw new IllegalArgumentException("Ошибка: деление на ноль");
                }
                break;
            default:
                throw new IllegalArgumentException("Неверный оператор: " + operator);
        }

        return isRoman ? Roman.toRoman(result) : Integer.toString(result);
    }
}

class Roman {
    static int toArabic(String roman) {
        // Преобразование римских чисел в арабские
        // Примечание: Эта реализация поддерживает только числа от I до X

        if (roman.equals("I")) return 1;
        if (roman.equals("II")) return 2;
        if (roman.equals("III")) return 3;
        if (roman.equals("IV")) return 4;
        if (roman.equals("V")) return 5;
        if (roman.equals("VI")) return 6;
        if (roman.equals("VII")) return 7;
        if (roman.equals("VIII")) return 8;
        if (roman.equals("IX")) return 9;
        if (roman.equals("X")) return 10;

        throw new IllegalArgumentException("Некорректное римское число: " + roman);
    }

    static String toRoman(int arabic) {
        // Преобразование арабских чисел в римские
        // Примечание: Эта реализация поддерживает только числа от 1 до 10

        if (arabic < 1 || arabic > 10) {
            throw new IllegalArgumentException("Некорректное арабское число для преобразования в римское: " + arabic);
        }

        String[] romanNumerals = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        return romanNumerals[arabic - 1];
    }
}

