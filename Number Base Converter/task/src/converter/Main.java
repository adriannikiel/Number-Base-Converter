package converter;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // write your code here

        while (true) {
            System.out.print("Do you want to convert /from decimal or /to decimal? (To quit type /exit) ");
            String command = scanner.next();

            switch (command) {
                case "/from":
                    fromDecimals();
                    break;
                case "/to":
                    toDecimals();
                    break;
                case "/exit":
                    System.exit(0);
            }
        }
    }

    private static void fromDecimals() {
        System.out.print("Enter number in decimal system: ");
        int decimal = scanner.nextInt();

        System.out.print("Enter target base: ");
        int base = scanner.nextInt();

        System.out.print("Conversion result: " + convertNumberFromDecimal(decimal, base));
        System.out.print("\n\n");
    }

    private static void toDecimals() {
        System.out.print("Enter source number: ");
        String source = scanner.next();

        System.out.print("Enter source base: ");
        int base = scanner.nextInt();

        System.out.print("Conversion to decimal result: " + convertNumberToDecimal(source, base));
        System.out.print("\n\n");
    }

    private static String convertNumberFromDecimal(int number, int base) {

        StringBuilder builder = new StringBuilder();

        while (number > 0) {
            int reminder = number % base;

            builder.append(mapNumberToCode(reminder));
            number /= base;
        }

        return builder.reverse().toString();
    }

    private static String convertNumberToDecimal(String code, int base) {

        int sum = 0;
        int codeLength = code.length();

        for (int i = codeLength - 1, j = 0; i >= 0; i--, j++) {
            char c = code.charAt(i);
            int number = mapCodeToNumber(c);

            sum += number * Math.pow(base, j);
        }

        return String.valueOf(sum);
    }

    private static String mapNumberToCode(int number) {

        String result = null;

        if (number < 10) {
            result = String.valueOf(number);
        } else {
            switch (number) {
                case 10:
                    result = "A";
                    break;
                case 11:
                    result = "B";
                    break;
                case 12:
                    result = "C";
                    break;
                case 13:
                    result = "D";
                    break;
                case 14:
                    result = "E";
                    break;
                case 15:
                    result = "F";
                    break;
            }
        }

        return result;
    }

    private static int mapCodeToNumber(char codeAsChar) {

        int result = 0;
        String code = String.valueOf(codeAsChar).toUpperCase();

        if ("0123456789".contains(code)) {
            result = Integer.parseInt(code);
        } else {
            switch (code) {
                case "A":
                    result = 10;
                    break;
                case "B":
                    result = 11;
                    break;
                case "C":
                    result = 12;
                    break;
                case "D":
                    result = 13;
                    break;
                case "E":
                    result = 14;
                    break;
                case "F":
                    result = 15;
                    break;
            }
        }

        return result;
    }
}
