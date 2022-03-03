import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // write your code here

        int k = scanner.nextInt();
        int n = scanner.nextInt();
        double m = scanner.nextDouble();

        long seed = k;

        do {
            Random random = new Random(seed);
            boolean isFound = true;

            for (int i = 0; i < n; i++) {
                if (random.nextGaussian() > m) {
                    isFound = false;
                    break;
                }
            }

            if (isFound) {
                break;
            }

            seed++;

        } while (true);

        System.out.println(seed);
    }
}