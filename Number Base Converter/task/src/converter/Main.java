package converter;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // write your code here

        System.out.print("Enter number in decimal system: ");
        int decimal = scanner.nextInt();

        System.out.print("Enter target base: ");
        int base = scanner.nextInt();

        System.out.print("Conversion result: " + convertNumber(decimal, base));


    }

    private static String convertNumber(int number, int base) {

        StringBuilder builder = new StringBuilder();

        while (number > 0) {
            int reminder = number % base;
            
            if (reminder < 10) {
                builder.append(number % base);
            } else {
                switch (reminder) {
                    case 10:
                        builder.append("A");
                        break;
                    case 11:
                        builder.append("B");
                        break;
                    case 12:
                        builder.append("C");
                        break;
                    case 13:
                        builder.append("D");
                        break;
                    case 14:
                        builder.append("E");
                        break;
                    case 15:
                        builder.append("F");
                        break;
                }
            }

            number /= base;
        }

        return builder.reverse().toString();
    }
}
