package ua.khpi.oop.pavlova15.util;

import java.util.InputMismatchException;
import java.util.Scanner;

import ua.khpi.oop.pavlova10.HotelGuest;

public class InPutUtil {
	public static Scanner scanner = new Scanner(System.in);

	public static HotelGuest inputGuest() {
		System.out.println("Введите Ф.И.О.");
		String guestNameSurname = InPutUtil.inputString();
		System.out.println("Введите дату рождения");
		String guestBith = InPutUtil.inputString();
		System.out.println("Введите странуу и город");
		String guestMotherland = InPutUtil.inputString();
		System.out.println("Введите номер паспорта");
		String guestPassport = InPutUtil.inputString();
		System.out.println("Введите дату заселения");
		String dateOfArrival = InPutUtil.inputString();
		System.out.println("Введите дату выселения");
		String dateOfEviction = InPutUtil.inputString();
		System.out.println("Введите номер комнаты");
		String roomNum = InPutUtil.inputString();
		System.out.println("Введите класс номера");
		String roomClass = InPutUtil.inputString();
		System.out.println("Введите количество мест в номере");
		String roomPlaces = InPutUtil.inputString();
		System.out.println("Введите причину заселения");
		String reasonOfArrival = InPutUtil.inputString();

		HotelGuest newEl = new HotelGuest(guestNameSurname, guestBith, guestMotherland, guestPassport, dateOfArrival,
				dateOfEviction, roomNum, roomClass, roomPlaces, reasonOfArrival);

		return newEl;
	}

	public static int inputInteger() {
		int value = 0;
		try {
			value = Integer.valueOf(scanner.nextInt());
		} catch (InputMismatchException inputMismatchException) {
			scanner.nextLine();
			System.out.println("Ошибка работы прораммы!");
		}
		return value;
	}

	public static String inputString() {
		String string = null;
		try {
			string = String.valueOf(scanner.nextLine());
		} catch (InputMismatchException inputMismatchException) {
			scanner.nextLine();
			System.out.println("Ошибка работы прораммы!");
		}
		return string;
	}

	public static int makeChoice() {
		System.out.println("Введите команду");
		return inputInteger();
	}

	public static int chooseSortingType() {
		System.out.println("Введите тип сортировки");
		System.out.println("1.min->max\n2.max->min");
		return inputInteger();
	}

}
