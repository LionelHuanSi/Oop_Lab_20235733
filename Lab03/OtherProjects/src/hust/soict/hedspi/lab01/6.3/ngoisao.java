import java. util.Scanner;
public class ngoisao{
	public static void main(String[] args) {
		Scanner a = new Scanner(System.in);
		int n = a.nextInt();
		for(int i = 0; i < n; i++) {
			for(int j = i; j < n - 1; j++) {
				System.out.print(" ");
			}
			for(int t = 1; t <= 2*i + 1; t++) {
				System.out.print("*");
			}
			System.out.println();
		}
		a.close();
	}
}
