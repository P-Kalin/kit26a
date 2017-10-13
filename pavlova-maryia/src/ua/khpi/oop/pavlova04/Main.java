package ua.khpi.oop.pavlova04;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static Scanner scanner = new Scanner(System.in);

	public static void main(final String[] args) throws IOException {

		System.out.println("Лабораторна робота №4");
		System.out.println("Оберіть необхідну команду для виконання "
				+ "(рекомендується виконувати усі можливі команди в заданому порядку):");

		System.out.println("\t 1. input");
		System.out.println("\t 2. data");
		System.out.println("\t 3. calculate");
		System.out.println("\t 4. result");
		System.out.println("\t 5. exit");
		boolean exit = false;
		while (exit == false) {
			System.out.println("Додаток: введіть номер(код) обраної команди.");
			try {
				int command = Integer.valueOf(scanner.nextInt());
				scanner.nextLine();
				CommandParser.doActionByCommand(command);
			} catch (InputMismatchException inputMismatchException) {
				scanner.nextLine();
				System.out.println("Не вірний код команди");
			}
		}
	}
}