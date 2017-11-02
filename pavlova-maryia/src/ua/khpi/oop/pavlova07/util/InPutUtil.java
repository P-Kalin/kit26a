package ua.khpi.oop.pavlova07.util;

import java.util.Scanner;

import ua.khpi.oop.pavlova06.util.InputUtil;
import ua.khpi.oop.pavlova07.HotelGuest;

public class InPutUtil {
	public Scanner scanner = new Scanner(System.in);

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
}
