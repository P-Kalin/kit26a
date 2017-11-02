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

	/**
	 * <i>inputInteger</i> enables the customer to input value, which type is
	 * Integer.
	 * 
	 * @param value
	 * @return input value
	 */
	public static int inputInteger() {
		int value = 0;
		try {
			value = Integer.valueOf(scanner.nextInt());
			scanner.nextLine();
		} catch (InputMismatchException inputMismatchException) {
			scanner.nextLine();
			System.out.println("Ошибка работы прораммы!");
		}
		return value;
	}

	/**
	 * <i>inputString</i> enables the customer to input value, which type is String.
	 * 
	 * @param string
	 * @return input value
	 */
	public static String inputString() {
		String string = null;
		try {
			string = String.valueOf(scanner.nextLine());
			scanner.nextLine();
		} catch (InputMismatchException inputMismatchException) {
			scanner.nextLine();
			System.out.println("Ошибка работы прораммы!");
		}
		return string;
	}

	/**
	 * <i>inputFloat</i> enables the customer to input value, which type is Float.
	 * 
	 * @param value
	 * @return input value
	 */
	public static float inputFloat() {
		float value = 0;
		try {
			value = Float.valueOf(scanner.nextFloat());
			scanner.nextLine();
		} catch (InputMismatchException inputMismatchException) {
			scanner.nextLine();
			System.out.println("Ошибка работы прораммы!");
		}
		return value;
	}

	/**
	 * <i>inputDouble</i> enables the customer to input value, which type is Double.
	 * 
	 * @param value
	 * @return input value
	 */
	public static double inputDouble() {
		double value = 0;
		try {
			value = Double.valueOf(scanner.nextDouble());
			scanner.nextLine();
		} catch (InputMismatchException inputMismatchException) {
			scanner.nextLine();
			System.out.println("Ошибка работы прораммы!");
		}
		return value;
	}

	/**
	 * <i>inputLong</i> enables the customer to input value, which type is Long.
	 * 
	 * @param value
	 * @return
	 */
	public static long inputLong() {
		long value = 0;
		try {
			value = Long.valueOf(scanner.nextLong());
			scanner.nextLine();
		} catch (InputMismatchException inputMismatchException) {
			scanner.nextLine();
			System.out.println("Ошибка работы прораммы!");
		}
		return value;
	}

	/**
	 * <i>inputShort</i> enables the customer to input value, which type is Short.
	 * 
	 * @param value
	 * @return input value
	 */
	public static short inputShort() {
		short value = 0;
		try {
			value = Short.valueOf(scanner.nextShort());
			scanner.nextLine();
		} catch (InputMismatchException inputMismatchException) {
			scanner.nextLine();
			System.out.println("Ошибка работы прораммы!");
		}
		return value;
	}

	/**
	 * <i>inputByte</i> enables the customer to input value, which type is Byte.
	 * 
	 * @param value
	 * @return input value
	 */
	public static byte inputByte() {
		byte value = 0;
		try {
			value = Byte.valueOf(scanner.nextByte());
			scanner.nextLine();
		} catch (InputMismatchException inputMismatchException) {
			scanner.nextLine();
			System.out.println("");
		}
		return value;
	}
}
