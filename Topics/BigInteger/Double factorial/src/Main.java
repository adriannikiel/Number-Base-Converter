import java.math.BigInteger;

class DoubleFactorial {
    public static BigInteger calcDoubleFactorial(int n) {
        // type your java code here
        BigInteger result = BigInteger.valueOf(1);
        BigInteger number = BigInteger.valueOf(n);

        while (number.intValue() > 0) {
            result = result.multiply(number);
            number = number.subtract(BigInteger.TWO);
        }

        return result;
    }

}