package assignments;

import java.util.Scanner;

public class Conditions {

	public static void main(String[] args) {
		System.out.println("Voer a.u.b. een heel getal in...");
		Scanner scanner = new Scanner(System.in);
		int input = scanner.nextInt();

		
		if (input != 50) {
			System.out.println("The number is not equal to 50");
		}
		if (input > 10) {
			System.out.println("The number is greater than 10");
		}
		if (input > 1 && input < 9) {
			System.out.println("The number is greater than 1 and smaller than 9");
		}
		if (input % 3 == 0 || input % 4 == 0) {
			System.out.println("The number is a multiple of 3 or 4");
		}
	}

}
