package ua.khpi.oop.pavlova07.util;

import java.util.Scanner;

import ua.khpi.oop.pavlova06.NewContainerOfStrings;
import ua.khpi.oop.pavlova06.util.InputUtil;
import ua.khpi.oop.pavlova07.HotelGuest;

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
	public Scanner scanner = new Scanner(System.in);

	/**
	 * Method <b>inputGuest</b> performs the filling of an object's fields with
	 * input information written by customer.
	 * 
	 * @return a new Object
	 */
	public static HotelGuest inputGuest() {
		System.out.println("Введите Ф.И.О.");
		String guestNameSurname = InputUtil.inputString();
		System.out.println("Введите дату рождения");
		String guestBith = InputUtil.inputString();
		System.out.println("Введите странуу и город");
		String guestMotherland = InputUtil.inputString();
		System.out.println("Введите номер паспорта");
		String guestPassport = InputUtil.inputString();
		System.out.println("Введите дату заселения");
		String dateOfArrival = InputUtil.inputString();
		System.out.println("Введите дату выселения");
		String dateOfEviction = InputUtil.inputString();
		System.out.println("Введите номер комнаты");
		String roomNum = InputUtil.inputString();
		System.out.println("Введите класс номера");
		String roomClass = InputUtil.inputString();
		System.out.println("Введите количество мест в номере");
		String roomPlaces = InputUtil.inputString();
		System.out.println("Введите причину заселения");
		String reasonOfArrival = InputUtil.inputString();

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
		index = InputUtil.inputInteger();
		if (index >= containerOfStrings.size())
			index = 0;
		HotelGuest toModify = ModifyUtil.getObject(index, containerOfStrings);
		return toModify;
	}
}
