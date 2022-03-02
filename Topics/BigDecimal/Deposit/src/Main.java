import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);

        BigDecimal startingAmount = scanner.nextBigDecimal();
        BigDecimal yearlyPercent = scanner.nextBigDecimal();
        int years = scanner.nextInt();

        BigDecimal ONE_HUNDRED = new BigDecimal("100");

        BigDecimal division = yearlyPercent.divide(ONE_HUNDRED);
        BigDecimal addition = BigDecimal.ONE.add(division);
        BigDecimal power = addition.pow(years);

        BigDecimal finalAmount = startingAmount.multiply(power).setScale(2, RoundingMode.CEILING);

        System.out.println("Amount of money in the account: " + finalAmount);



    }
}