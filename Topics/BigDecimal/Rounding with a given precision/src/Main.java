import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // write your code here

        Scanner scanner = new Scanner(System.in);

        BigDecimal rational = scanner.nextBigDecimal();
        int scale = scanner.nextInt();

        System.out.println(rational.setScale(scale, RoundingMode.HALF_DOWN));
    }   
}