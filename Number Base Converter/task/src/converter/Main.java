package converter;

import java.math.BigInteger;
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

        String decimal = convertNumberToDecimal(number, sourceBase);
        String result = convertNumberFromDecimal(decimal, targetBase);

        return result;
    }

    private static String convertNumberToDecimal(String code, int baseAsInt) {

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

    private static String convertNumberFromDecimal(String numberAsString, int baseAsInt) {

        StringBuilder builder = new StringBuilder();

        BigInteger number = new BigInteger(numberAsString);
        BigInteger base = BigInteger.valueOf(baseAsInt);

        while (number.compareTo(BigInteger.ZERO) > 0) {  //number > 0
            BigInteger reminder = number.remainder(base);  //reminder = number % base
            builder.append(mapNumberToCode(reminder.intValue()));
            number = number.divide(base);  //number /= base
        }

        return !builder.toString().equals("") ?  builder.reverse().toString() : "0";
    }



    private static char mapNumberToCode(int number) {   // '0'..'9', 'A'..'Z'
        return (number < 10) ? (char) (number + '0') : (char) (number + 'A' - 10);
    }

    private static int mapCodeToNumber(char codeAsChar) {  // 0 - 35
        return (codeAsChar <= '9') ? codeAsChar - '0' : codeAsChar - 'A' + 10;
    }

}
