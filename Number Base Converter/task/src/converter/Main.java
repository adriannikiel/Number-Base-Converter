package converter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        boolean exit = false;
        while (!exit) {
            printFirstLevel();

            String lineFirstLevel = scanner.nextLine();

            if (lineFirstLevel.equals("/exit")) {
                exit = true;
            } else {
                String[] bases = lineFirstLevel.split(" ");

                int sourceBase = Integer.parseInt(bases[0]);
                int targetBase = Integer.parseInt(bases[1]);

                boolean back = false;
                while (!back) {
                    printSecondLevel(sourceBase, targetBase);

                    String lineSecondLevel = scanner.nextLine();

                    if (lineSecondLevel.equals("/back")) {
                        System.out.println();
                        back = true;
                    } else {
                        String number = lineSecondLevel;

                        String conversionResult = convertNumber(number, sourceBase, targetBase);
                        System.out.println("Conversion result: " + conversionResult);
                        System.out.println();
                    }
                }
            }
        }
    }

    private static void printFirstLevel() {
        System.out.print("Enter two numbers in format: {source base} {target base} (To quit type /exit) ");
    }

    private static void printSecondLevel(int a, int b) {
        System.out.printf("Enter number in base %d to convert to base %d (To go back type /back) ", a, b);
    }

    private static String convertNumber(String number, int sourceBase, int targetBase) {

        String[] splittedNumber = null;
        String integerPart = "0";
        String fractionalPart = "0";

        if (number.contains(".")) {
            splittedNumber = number.split("\\.");
            integerPart = splittedNumber[0];
            fractionalPart = splittedNumber[1];
        } else if (number.contains(",")) {
            splittedNumber = number.split(",");
            integerPart = splittedNumber[0];
            fractionalPart = splittedNumber[1];
        } else {
            integerPart = number;
            fractionalPart = "0";
        }

        String decimalIntegerPart = convertIntegerNumberToDecimal(integerPart, sourceBase);
        String decimalFractionalPart = convertFractionalNumberToDecimal(fractionalPart, sourceBase);

        String integerResult = convertIntegerNumberFromDecimal(decimalIntegerPart, targetBase);
        String fractionalPartlyResult = convertFractionalNumberFromDecimal(decimalFractionalPart, targetBase, 5);

        String result = null;
        if (number.contains(".") || number.contains(",")) {
            result = integerResult + "." + getFractionalResult(fractionalPartlyResult, 5);
        } else {
            result = integerResult;
        }

        return result;
    }


    private static String convertIntegerNumberToDecimal(String code, int baseAsInt) {

        BigInteger base = BigInteger.valueOf(baseAsInt);

        BigInteger sum = BigInteger.ZERO;
        int codeLength = code.length();

        for (int i = codeLength - 1, j = 0; i >= 0; i--, j++) {
            char c = code.toUpperCase().charAt(i);
            int number = mapCodeToNumber(c);

            //sum += number * Math.pow(base, j);
            sum = sum.add(BigInteger.valueOf(number).multiply(base.pow(j)));
        }

        return sum.toString();
    }

    private static String convertFractionalNumberToDecimal(String code, int baseAsInt) {

        BigDecimal base = BigDecimal.valueOf(baseAsInt);

        BigDecimal sum = BigDecimal.ZERO;
        int codeLength = code.length();

        for (int i = 0; i < codeLength; i++) {
            char c = code.toUpperCase().charAt(i);
            int number = mapCodeToNumber(c);

            //sum += number * Math.pow(base, j);
            sum = sum.add(BigDecimal.valueOf(number).divide(base.pow(i + 1),10, RoundingMode.DOWN) );
        }

        return sum.setScale(5, RoundingMode.DOWN).toString();
    }

    private static String convertIntegerNumberFromDecimal(String numberAsString, int baseAsInt) {

        StringBuilder builder = new StringBuilder();

        BigInteger number = new BigInteger(numberAsString);
        BigInteger base = BigInteger.valueOf(baseAsInt);

        while (number.compareTo(BigInteger.ZERO) > 0) {  //number > 0
            BigInteger reminder = number.remainder(base);  //reminder = number % base
            builder.append(mapNumberToCode(reminder.intValue()));
            number = number.divide(base);  //number /= base
        }

        return !builder.toString().equals("") ? builder.reverse().toString() : "0";
    }

    private static String convertFractionalNumberFromDecimal(String numberAsString, int baseAsInt, int scale) {

        StringBuilder builder = new StringBuilder();

        BigDecimal number = new BigDecimal(numberAsString);
        BigDecimal base = BigDecimal.valueOf(baseAsInt);

        int i = 0;
        while (number.compareTo(BigDecimal.ZERO) > 0 && i < scale) {  //number > 0
            BigDecimal multiplicationResult = number.multiply(base);

            BigDecimal integerReminder = multiplicationResult.setScale(0, RoundingMode.DOWN);
            builder.append(mapNumberToCode(integerReminder.intValue()));

            number = multiplicationResult.remainder(BigDecimal.ONE);
            i++;
        }

        return !builder.toString().equals("") ? builder.toString() : "0";
    }


    private static String getFractionalResult(String fractionalPartlyResult, int scale) {

        char[] fill = new char[scale];
        Arrays.fill(fill, '0');
        String zeroes = new String(fill);

        return (fractionalPartlyResult + zeroes).substring(0, scale);
    }


    private static char mapNumberToCode(int number) {   // '0'..'9', 'A'..'Z'
        return (number < 10) ? (char) (number + '0') : (char) (number + 'A' - 10);
    }

    private static int mapCodeToNumber(char codeAsChar) {  // 0 - 35
        return (codeAsChar <= '9') ? codeAsChar - '0' : codeAsChar - 'A' + 10;
    }

}
