import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        int u1 = scanner.nextInt();
        int u2 = scanner.nextInt();
        int v1 = scanner.nextInt();
        int v2 = scanner.nextInt();

        double dotProduct = u1 * v1 + u2 * v2;
        double lenU = Math.sqrt(u1 * u1 + u2 * u2);
        double lenV = Math.sqrt(v1 * v1 + v2 * v2);

        double radian = Math.acos(dotProduct / (lenU * lenV));

        System.out.println(Math.round(Math.toDegrees(radian)));
    }
}