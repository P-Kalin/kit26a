package ua.khpi.oop.pavlova09.util;

import java.util.InputMismatchException;
import java.util.Scanner;

import ua.khpi.oop.pavlova08.HotelGuest;
import ua.khpi.oop.pavlova08.util.InPutUtil;

public class ChoiceUtil {
	public static Scanner scanner = new Scanner(System.in);

	public static int chooseCommand() {

		System.out.println("1. Добавление в конец списка");
		System.out.println("2. Добавление на выбранную позицию");
		System.out.println("3. Удаление из конца списка");
		System.out.println("4. Удаление с выбранной позиции");
		System.out.println("5. Очистка списка");
		System.out.println("6. Проверка на наличие элементов в списке");
		System.out.println("7. Вывод на экран");
		System.out.println("8. Проверка на наличие элемента по умолчанию");
		System.out.println("9. Выход");
		return InPutUtil.inputInteger();
	}

	public static HotelGuest createNewHotelGuest() {
		System.out.print("Введите Ф.И.О.: ");
		String name = InPutUtil.inputString();
		System.out.print("Введите дату рождения: ");
		String birth = InPutUtil.inputString();
		System.out.print("Введите страну и город: ");
		String land = InPutUtil.inputString();
		System.out.print("Введите номер паспорта");
		String passport = InPutUtil.inputString();
		System.out.print("Введите дату приезда: ");
		String arrival = InPutUtil.inputString();
		System.out.print("Введите дату выселения: ");
		String left = InPutUtil.inputString();
		System.out.print("Введите номер комнаты: ");
		String room = InPutUtil.inputString();
		System.out.print("Введите класс комнаты: ");
		String classR = InPutUtil.inputString();
		System.out.print("Введите количество мест: ");
		String number = InPutUtil.inputString();
		System.out.print("Введите причину приезда: ");
		String reason = InPutUtil.inputString();
		return new HotelGuest(name, birth, land, passport, arrival, left, room, classR, number, reason);
	}

	public static int chooseFirstOrSecond() {
		System.out.println("Использовать элемент по умолчанию?\n1. Да\n2. Нет");
		int choice = 0;
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
