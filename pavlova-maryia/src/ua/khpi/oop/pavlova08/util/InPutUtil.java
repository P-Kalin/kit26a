package ua.khpi.oop.pavlova08.util;

import java.util.InputMismatchException;
import java.util.Scanner;

import ua.khpi.oop.pavlova06.NewContainerOfStrings;
import ua.khpi.oop.pavlova06.util.InputUtil;
import ua.khpi.oop.pavlova08.HotelGuest;

/**
 * Class <b>InPutUtil</b> contains methods for iformation input by customer. It
 * has method for modifying element from the container. It works via
 * <b><i>ModifyUtil</i></b>. Also it fas a method for filling object's fields.
 * It works via <b><i>InputUtil</i></b>
 * 
 * @see ModifyUtil
 * @see InputUtil
 * @author pavlova-mv
 *
 */
public class InPutUtil {
	public static Scanner scanner = new Scanner(System.in);

	/**
	 * Method <b>inputGuest</b> performs the filling of an object's fields with
	 * input information written by customer.
	 * 
	 * @return a new Object
	 */
	public static HotelGuest inputGuest() {
		System.out.println("Введите Ф.И.О.");
		String guestNameSurname = inputString();
		System.out.println("Введите дату рождения");
		String guestBith = inputString();
		System.out.println("Введите странуу и город");
		String guestMotherland = inputString();
		System.out.println("Введите номер паспорта");
		String guestPassport = inputString();
		System.out.println("Введите дату заселения");
		String dateOfArrival = inputString();
		System.out.println("Введите дату выселения");
		String dateOfEviction = inputString();
		System.out.println("Введите номер комнаты");
		String roomNum = inputString();
		System.out.println("Введите класс номера");
		String roomClass = inputString();
		System.out.println("Введите количество мест в номере");
		String roomPlaces = inputString();
		System.out.println("Введите причину заселения");
		String reasonOfArrival = inputString();

		HotelGuest newEl = new HotelGuest(guestNameSurname, guestBith, guestMotherland, guestPassport, dateOfArrival,
				dateOfEviction, roomNum, roomClass, roomPlaces, reasonOfArrival);

		return newEl;
	}

	/**
	 * Method <b>askToModifyHotelGuest</b> returns an element from the container,
	 * that will be modified
	 * 
	 * @param index
	 *            is an index of a specific element from the container
	 * @param containerOfStrings
	 *            is a container of elements
	 * @return element to modify
	 */
	public static HotelGuest askToModifyHotelGuest(int index, NewContainerOfStrings containerOfStrings) {
		System.out.println("Введите индекс элемента для изменения и новое значение.");
		index = inputInteger();
		if (index >= containerOfStrings.size())
			index = 0;
		HotelGuest toModify = ModifyUtil.getObject(index, containerOfStrings);
		return toModify;
	}

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
