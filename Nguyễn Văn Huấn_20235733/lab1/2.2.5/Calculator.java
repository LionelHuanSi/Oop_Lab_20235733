import javax.swing.JOptionPane;

public class Calculator {
    public static void main(String[] args) {
        String strNum1, strNum2;
        String strNotification = "You've just entered: ";
        double sum, difference, product, quotient;

        // Nhập số thứ nhất
        strNum1 = JOptionPane.showInputDialog(null, "Please input the first number:", "Input", JOptionPane.INFORMATION_MESSAGE);
        strNotification += strNum1 + " and ";

        // Nhập số thứ hai
        strNum2 = JOptionPane.showInputDialog(null, "Please input the second number:", "Input", JOptionPane.INFORMATION_MESSAGE);
        strNotification += strNum2;

        // Chuyển đổi sang số thực
        double num1 = Double.parseDouble(strNum1);
        double num2 = Double.parseDouble(strNum2);

        // Tính toán
        sum = num1 + num2;
        difference = num1 - num2;
        product = num1 * num2;
        quotient = (num2 != 0) ? num1 / num2 : Double.NaN; // Kiểm tra chia cho 0

        // Hiển thị kết quả với 2 chữ số sau dấu phẩy
        strNotification += "\nSum = " + String.format("%.2f", sum) +
                           "\nDifference = " + String.format("%.2f", difference) +
                           "\nProduct = " + String.format("%.2f", product);

        if (!Double.isNaN(quotient)) {
            strNotification += "\nQuotient = " + String.format("%.2f", quotient);
        } else {
            strNotification += "\nQuotient = Undefined (Cannot divide by zero)";
        }

        // Hiển thị kết quả
        JOptionPane.showMessageDialog(null, strNotification, "Result", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
}
