package ua.khpi.oop.pavlova06.util;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Realization of data input by customer
 * 
 * @author pavlova-mv
 *
 */

public class InputUtil {

	public static Scanner scanner = new Scanner(System.in);

	public static int inputInteger(int value) {
		try {
			value = Integer.valueOf(scanner.nextInt());
			scanner.nextLine();
		} catch (InputMismatchException inputMismatchException) {
			scanner.nextLine();
			System.out.println("");
		}
		return value;
	}

	public static String inputString(String string) {
		try {
			string = String.valueOf(scanner.nextLine());
			scanner.nextLine();
		} catch (InputMismatchException inputMismatchException) {
			scanner.nextLine();
			System.out.println("");
		}
		return string;
	}
}
