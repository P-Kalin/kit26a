package ua.khpi.oop.pavlova06.util;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * <b>ChoiceUtil </b> includes all the methods, that are connected with
 * customer's choice. This class enables to ask the customer about different
 * ways of continuation of the program.
 * 
 * @author pavlova-mv
 *
 */
public class ChoiceUtil {

	public static boolean exit = false;
	public static String text;

	public static Scanner scanner = new Scanner(System.in);

	private ChoiceUtil() {

	}

	/**
	 * <i>listOfCommands</i> enables the customer to choose the specific command.
	 * 
	 * @param command
	 * @return the chosen command
	 * @throws IOException
	 */
	public static int listOfCommands(int command) throws IOException {
		System.out.println("Перечень команд на выбор:");
		System.out.println("1. Добавление элемента в конец контейнера.");
		System.out.println("2. Добавление элемента на определенную позицию контейнера.");
		System.out.println("3. Удаление элемента по индексу.");
		System.out.println("4. Удаление элемента по определенному значению.");
		System.out.println("5. Проверка на наличие в контейнере определенного элемента.");
		System.out.println("6. Проверка на наличие в контейнере всех элементов другого контейнера.");
		System.out.println("7. Получение элемента по индексу.");
		System.out.println("8. Сортировка элементов контейнера.");
		System.out.println("9. Выход из программы.");

		try {
			command = Integer.valueOf(scanner.nextInt());
			scanner.nextLine();
		} catch (InputMismatchException inputMismatchException) {
			scanner.nextLine();
			System.out.println("Ошибка работы программы!");
		}
		if (command > 9 || command < 1) {
			System.out.println("Неверно введена команда! Будет выбрана команда по умолчанию.");
			return 1;
		}
		return command;
	}

	/**
	 * <i>listOfSorts</i> enables the customer to choose th sort algorithm.
	 * 
	 * @param command
	 * @return the chosen command
	 */
	public static int listOfSorts(int command) {
		System.out.println("Перечень сортировок на выбор:");
		System.out.println("1. Классическая сортировка пузырьком.");
		System.out.println("2. Модифицировання сортировка пузырьком.");
		System.out.println("3. Обменная сортировка простыми включениями.");
		System.out.println("4. Пирамидальная сортировка.");
		System.out.println("5. Сортировка вставками.");
		System.out.println("6. Карманная сортировка.");
		System.out.println("7. Сортировка Хоара.");
		System.out.println("8. Сортировка Шейкера.");
		System.out.println("9. Сортировка Шелла. ");
		System.out.println("10. Сортировка простыми включениями.");

		try {
			command = Integer.valueOf(scanner.nextInt());
			scanner.nextLine();
		} catch (InputMismatchException inputMismatchException) {
			scanner.nextLine();
			System.out.println("Ошибка работы программы!");
		}
		if (command > 10 || command < 1) {
			System.out.println("Неверно введена команда! Будет выбрана команда по умолчанию.");
			return 1;
		}
		return command;
	}

	/**
	 * <i>chooseFirstOrSecond</i> enables the customer to choose one of two proposed
	 * options.
	 * 
	 * @param choice
	 * @return the chosen variant
	 */
	public static int chooseFirstOrSecond(int choice) {
		try {
			choice = Integer.valueOf(scanner.nextInt());
			scanner.nextLine();
		} catch (InputMismatchException inputMismatchException) {
			scanner.nextLine();
			System.out.println("Ошибка работы программы!");
		}
		if (choice > 2 || choice < 1) {
			System.out.println("Неверно введена команда! Будет выбрана команда по умолчанию.");
			return 1;
		}
		return choice;
	}
}
