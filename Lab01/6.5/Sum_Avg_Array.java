import java.util.Arrays;
import java.util.Scanner;

public class Sum_Avg_Array {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = getPositiveInteger(scanner, "Input n: ");
        double[] arr = new double[n];

        for (int i = 0; i < n; i++) {
            arr[i] = getDoubleInput(scanner, "Enter element " + (i + 1) + ": ");
        }

        Arrays.sort(arr);
        double sum = 0;
        for (double num : arr) {
            sum += num;
        }
        double average = sum / n;

        System.out.println("Sorted Array: " + Arrays.toString(arr));
        System.out.println("Sum: " + sum);
        System.out.println("Average: " + String.format("%.2f", average));

        scanner.close();
    }

    private static int getPositiveInteger(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            if (scanner.hasNextInt()) {
                int num = scanner.nextInt();
                if (num > 0) {
                    return num;
                }
            }
            System.out.println("Invalid input! Please enter a positive integer.");
            scanner.nextLine();
        }
    }

    private static double getDoubleInput(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            if (scanner.hasNextDouble()) {
                return scanner.nextDouble();
            }
            System.out.println("Invalid input! Please enter a valid number.");
            scanner.nextLine();
        }
    }
}
