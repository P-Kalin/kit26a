package ua.khpi.oop.pavlova06.util;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ChoiceUtil {

	public static boolean exit = false;
	public static String text;

	public static Scanner scanner = new Scanner(System.in);

	private ChoiceUtil() {

	}

	public static int listOfCommands(int command) throws IOException {
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");

		try {
			command = Integer.valueOf(scanner.nextInt());
			scanner.nextLine();
		} catch (InputMismatchException inputMismatchException) {
			scanner.nextLine();
			System.out.println("");
		}
		if (command > 8 || command < 1) {
			System.out.println("");
			return 1;
		}
		return command;
	}

	public static int chooseFirstOrSecond(int choice) {
		try {
			choice = Integer.valueOf(scanner.nextInt());
			scanner.nextLine();
		} catch (InputMismatchException inputMismatchException) {
			scanner.nextLine();
			System.out.println("");
		}
		if (choice > 2 || choice < 1) {
			System.out.println("");
			return 1;
		}
		return choice;
	}
}
