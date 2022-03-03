import java.sql.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // write your code here

        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        int seed = a;
        int minOfMax = Integer.MAX_VALUE;

        for (int i = a; i <= b; i++) {

            Random random = new Random(i);

            int max = 0;
            for (int j = 0; j < n; j++) {

                int number = random.nextInt(k);

                if (number > max) {
                    max = number;
                }
            }

            if (max < minOfMax) {
                minOfMax = max;
                seed = i;
            }

        }


        System.out.println(seed);
        System.out.println(minOfMax);

    }
}