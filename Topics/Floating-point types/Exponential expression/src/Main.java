import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // put your code here

        double number = scanner.nextDouble();

        System.out.println(number * number * number + number * number + number + 1);
    }
}