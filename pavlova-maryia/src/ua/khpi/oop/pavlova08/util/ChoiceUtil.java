package ua.khpi.oop.pavlova08.util;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Class <b>ChoiceUtil</b> contains methods for dialogue window with a customer.
 * It helps to get customer's choice by the index, and the this choice is used
 * in <b><i>CommandParser</b></i> to do one of many proposed commands. Number is
 * choosed using the <b><i>InPutUtil</i></b>
 * 
 * @see InPutUtil
 * @see CommandParser
 * 
 * @author pavlova-mv
 *
 */
public class ChoiceUtil {
	public static Scanner scanner = new Scanner(System.in);

	/**
	 * Method <b>listOfCommands</b> gives to customer a list of possible actions
	 * with a container.
	 * 
	 * @param command
	 *            is an index of a command
	 * @return chosen command
	 */
	public static int listOfCommands(int command) {
		System.out.println("Выберите команду: ");
		System.out.println("1. Добавление в картотеку нового гостя.");
		System.out.println("2. Добавление в картотеку нового гостя по умолчанию.");
		System.out.println("3. Изменить Ф.И.О. определенного гостя");
		System.out.println("4. Изменить дату рождения определенного гостя");
		System.out.println("5. Изменить страну и город определенного гостя");
		System.out.println("6. Изменить номер паспорта определенного гостя");
		System.out.println("7. Изменить дату заселения определенного гостя");
		System.out.println("8. Изменить дату выселения определенного гостя");
		System.out.println("9. Изменить номер определенного гостя");
		System.out.println("10. Изменить класс номера определенного гостя");
		System.out.println("11. Изменить количество мест в номере определенного гостя");
		System.out.println("12. Изменить причину заселения определенного гостя");
		System.out.println("13. Вывод картотеки на экран.");
		System.out.println("14. Выбор директории для записи");
		System.out.println("15. Выход из программы");
		try {
			command = Integer.valueOf(scanner.nextInt());
			scanner.nextLine();
		} catch (InputMismatchException inputMismatchException) {
			scanner.nextLine();
			System.out.println("Ошибка работы программы!");
		}
		if (command > 15 || command < 1) {
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
