package ua.khpi.oop.pavlova07.util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ChoiceUtil {
	public static Scanner scanner = new Scanner(System.in);

	public static int listOfCommands(int command) {
		System.out.println("Выберите команду: ");
		System.out.println("1. Добавление в картотеку нового гостя.");
		System.out.println("2. Добавление в картотеку нового гостя по умолчанию.");
		System.out.println("3. Вывод картотеки на экран.");
		System.out.println("4. Выход из программы");
		try {
			command = Integer.valueOf(scanner.nextInt());
			scanner.nextLine();
		} catch (InputMismatchException inputMismatchException) {
			scanner.nextLine();
			System.out.println("Ошибка работы программы!");
		}
		if (command > 4 || command < 1) {
			System.out.println("Неверно введена команда! Будет выбрана команда по умолчанию.");
			return 1;
		}
		return command;
	}
}
