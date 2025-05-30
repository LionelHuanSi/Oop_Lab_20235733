import java.util.Scanner;
public class Number_of_days_of_a_month {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int month;
		int year;
		while (true) {
            System.out.print("Enter a year (non-negative number): ");
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a valid non-negative year.");
                scanner.next();
                continue;
            }
            year = scanner.nextInt();
            if (year < 0) {
                System.out.println("Invalid input! Year must be non-negative.");
                continue;
            }
            break;
        }
        scanner.nextLine();
        while (true) {
            System.out.print("Enter a month (name, abbreviation, or number 1-12): ");
            String monthInput = scanner.nextLine().trim().toLowerCase();
            month = convertMonthToNumber(monthInput);
            if (month == -1) {
                System.out.println("Invalid month! Please enter again.");
                continue;
            }
            break;
        }
        int days = getDaysInMonth(month, year);
        System.out.println("The month " + getMonthName(month) + " of year " + year + " has " + days + " days.");

        scanner.close();
	}
	public static int convertMonthToNumber(String month) {
        switch (month) {
            case "january": case "jan.": case "jan": case "1": return 1;
            case "february": case "feb.": case "feb": case "2": return 2;
            case "march": case "mar.": case "mar": case "3": return 3;
            case "april": case "apr.": case "apr": case "4": return 4;
            case "may": case "5": return 5;
            case "june": case "jun.": case "jun": case "6": return 6;
            case "july": case "jul.": case "jul": case "7": return 7;
            case "august": case "aug.": case "aug": case "8": return 8;
            case "september": case "sep.": case "sep": case "9": return 9;
            case "october": case "oct.": case "oct": case "10": return 10;
            case "november": case "nov.": case "nov": case "11": return 11;
            case "december": case "dec.": case "dec": case "12": return 12;
            default: return -1;
        }
    }
	public static int getDaysInMonth(int month, int year) {
        switch (month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                return 31;
            case 4: case 6: case 9: case 11:
                return 30;
            case 2:
                return (isLeapYear(year)) ? 29 : 28;
            default:
                return -1;
        }
    }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
    public static String getMonthName(int month) {
        String[] months = {
            "", "January", "February", "March", "April", "May", "June", 
            "July", "August", "September", "October", "November", "December"
        };
        return months[month];
    }
}
