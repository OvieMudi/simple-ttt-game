import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int unit = scanner.nextInt();
        String unitName;

        if (unit < 1) {
            unitName = "no army";
        } else if (unit <= 19) {
            unitName = "pack";
        } else if (unit <= 249) {
            unitName = "throng";
        } else if (unit <= 999) {
            unitName = "zounds";
        } else {
            unitName = "legion";
        }
        System.out.println(unitName);
        scanner.close();
    }
}
