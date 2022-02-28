import java.math.BigInteger;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here

        Scanner scanner = new Scanner(System.in);
        BigInteger M = scanner.nextBigInteger();

        System.out.println(smallestValueN(M));
    }

    private static long smallestValueN(BigInteger M) {

        long i = 0;
        BigInteger factorialValue = BigInteger.ONE;

        while (factorialValue.compareTo(M) < 0) {
            i++;
            factorialValue = factorial(i);
        }

        return i;
    }

    private static BigInteger factorial(long n) {
        BigInteger result = BigInteger.valueOf(1);
        BigInteger number = BigInteger.valueOf(n);

        while (number.intValue() > 0) {
            result = result.multiply(number);
            number = number.subtract(BigInteger.ONE);
        }

        return result;
    }
}