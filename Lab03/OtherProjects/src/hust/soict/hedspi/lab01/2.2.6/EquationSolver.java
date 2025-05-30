import javax.swing.JOptionPane;

public class EquationSolver {
    public static void main(String[] args) {
        while (true) {
            String[] options = { "Linear Equation", "System of Equations", "Quadratic Equation", "Exit" };
            int choice = JOptionPane.showOptionDialog(null, "Choose an equation type to solve:",
                    "Equation Solver", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                    options, options[0]);

            switch (choice) {
                case 0:
                    solveLinearEquation();
                    break;
                case 1:
                    solveSystemOfLinearEquations();
                    break;
                case 2:
                    solveQuadraticEquation();
                    break;
                case 3:
                case -1: // User clicks "X" to close
                    JOptionPane.showMessageDialog(null, "Exiting program...", "Exit", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
            }
        }
    }

    // Solve a linear equation: ax + b = 0
    private static void solveLinearEquation() {
        double a = getDoubleInput("Enter coefficient a:");
        double b = getDoubleInput("Enter coefficient b:");

        String result;
        if (a == 0) {
            result = (b == 0) ? "The equation has infinitely many solutions."
                              : "The equation has no solution.";
        } else {
            double x = -b / a;
            result = String.format("The solution is: x = %.2f", x);
        }

        JOptionPane.showMessageDialog(null, result, "Result", JOptionPane.INFORMATION_MESSAGE);
    }

    // Solve a system of two linear equations
    private static void solveSystemOfLinearEquations() {
        double a11 = getDoubleInput("Enter a11:");
        double a12 = getDoubleInput("Enter a12:");
        double b1  = getDoubleInput("Enter b1:");
        double a21 = getDoubleInput("Enter a21:");
        double a22 = getDoubleInput("Enter a22:");
        double b2  = getDoubleInput("Enter b2:");

        double D  = a11 * a22 - a21 * a12;
        double D1 = b1 * a22 - b2 * a12;
        double D2 = a11 * b2 - a21 * b1;

        String result;
        if (D != 0) {
            double x1 = D1 / D;
            double x2 = D2 / D;
            result = String.format("The solution is: x1 = %.2f, x2 = %.2f", x1, x2);
        } else if (D1 == 0 && D2 == 0) {
            result = "The system has infinitely many solutions.";
        } else {
            result = "The system has no solution.";
        }

        JOptionPane.showMessageDialog(null, result, "Result", JOptionPane.INFORMATION_MESSAGE);
    }

    // Solve a quadratic equation: ax² + bx + c = 0
    private static void solveQuadraticEquation() {
        double a;
        do {
            a = getDoubleInput("Enter coefficient a (a ≠ 0):");
            if (a == 0) {
                JOptionPane.showMessageDialog(null, "Coefficient a must be nonzero!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (a == 0);

        double b = getDoubleInput("Enter coefficient b:");
        double c = getDoubleInput("Enter coefficient c:");

        double delta = b * b - 4 * a * c;
        String result;

        if (delta > 0) {
            double x1 = (-b + Math.sqrt(delta)) / (2 * a);
            double x2 = (-b - Math.sqrt(delta)) / (2 * a);
            result = String.format("The equation has two distinct solutions:\nx1 = %.2f\nx2 = %.2f", x1, x2);
        } else if (delta == 0) {
            double x = -b / (2 * a);
            result = String.format("The equation has one double root: x = %.2f", x);
        } else {
            result = "The equation has no real solutions.";
        }

        JOptionPane.showMessageDialog(null, result, "Result", JOptionPane.INFORMATION_MESSAGE);
    }

    // Function to get a valid double input
    private static double getDoubleInput(String message) {
        while (true) {
            try {
                String input = JOptionPane.showInputDialog(null, message, "Input", JOptionPane.QUESTION_MESSAGE);
                if (input == null) {
                    JOptionPane.showMessageDialog(null, "Operation cancelled.", "Exit", JOptionPane.WARNING_MESSAGE);
                    System.exit(0);
                }
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input! Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
