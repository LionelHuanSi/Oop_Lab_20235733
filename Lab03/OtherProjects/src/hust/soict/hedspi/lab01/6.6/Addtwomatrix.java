import java.util.Scanner;

public class Addtwomatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Nhập kích thước ma trận
        System.out.print("Enter number of rows: ");
        int rows = scanner.nextInt();
        System.out.print("Enter number of columns: ");
        int cols = scanner.nextInt();

        // Khai báo hai ma trận và ma trận tổng
        int[][] matrix1 = new int[rows][cols];
        int[][] matrix2 = new int[rows][cols];
        int[][] sumMatrix = new int[rows][cols];

        // Nhập giá trị cho ma trận 1
        System.out.println("Enter elements of the first matrix:");
        inputMatrix(scanner, matrix1, rows, cols);

        // Nhập giá trị cho ma trận 2
        System.out.println("Enter elements of the second matrix:");
        inputMatrix(scanner, matrix2, rows, cols);

        // Cộng hai ma trận
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sumMatrix[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }

        // Hiển thị kết quả
        System.out.println("Sum of the two matrices:");
        printMatrix(sumMatrix, rows, cols);

        scanner.close();
    }

    // Hàm nhập ma trận
    private static void inputMatrix(Scanner scanner, int[][] matrix, int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("Element [" + i + "][" + j + "]: ");
                matrix[i][j] = scanner.nextInt();
            }
        }
    }

    // Hàm hiển thị ma trận
    private static void printMatrix(int[][] matrix, int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
