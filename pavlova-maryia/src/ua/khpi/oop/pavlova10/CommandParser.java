package ua.khpi.oop.pavlova10;

import java.util.Scanner;

import ua.khpi.oop.pavlova08.util.InPutUtil;
import ua.khpi.oop.pavlova10.util.Comparators;
import ua.khpi.oop.pavlova10.util.PrintUtil;
import ua.khpi.oop.pavlova10.util.SortUtil;

public class CommandParser {
	private static String[] TYPES = { "ROOM_CLASS", "SURNAME", "MOTHERLAND", "REASON" };
	public static Scanner scanner = new Scanner(System.in);

	public static void makeChoice(LinkedList<HotelGuest> list) {
		String choice = InPutUtil.inputString();
		int command = 0;
		boolean param = false;
		if (choice.equals("-a") || choice.equals("-auto")) {
			param = true;
			doCommand(command, param, list);
		} else {
			doCommand(command, param, list);
		}
	}

	private static int makeChoice(int choice) {
		System.out.println("Введите команду:");
		System.out.println("1. Сортировка элементов по периоду проживания (min->max)");
		System.out.println("2. Сортировка элементов по периоду проживания (max->min)");
		System.out.println("3. Сортировка элементов по номеру (min->max)");
		System.out.println("4. Сортировка элементов по номеру (max->min)");
		System.out.println("5. Сортировка элементов по классу номера (alpabet)");
		System.out.println("6. Сортировка элементов по фамилии гостя (alpabet)");
		System.out.println("7. Сортировка элементов по стране гостя (alpabet)");
		System.out.println("8. Сортировка элементов по причине приезда гостя гостя (alpabet)");
		System.out.println("9. Выход.");

		choice = InPutUtil.inputInteger();
		return choice;
	}

	public static void doCommand(int command, boolean param, LinkedList<HotelGuest> list) {
		if (param) {
			HotelGuest firstAuto = new HotelGuest();
			HotelGuest secondAuto = new HotelGuest();
			HotelGuest thirdAuto = new HotelGuest();
			secondAuto.setDateOfEviction("25-Апрель-2017");
			secondAuto.setRoomNum("325");
			secondAuto.setRoomClass("Бизнес");
			secondAuto.setGuestNameSurname("Иванов И.И.");
			secondAuto.setReasonOfArrival("Командировка");
			secondAuto.setGuestMotherland("Англия, Лондон");
			thirdAuto.setGuestNameSurname("Василенко А.В.");
			thirdAuto.setGuestMotherland("Франция, Париж");
			list.add(firstAuto);
			list.add(secondAuto);
			list.add(thirdAuto);

		} else {
			System.out.println("Введите первого посетителя");
			String info1 = InPutUtil.inputString();
			HotelGuest first = HotelGuest.toObject(info1);
			System.out.println("Введите второго посетителя");
			String info2 = InPutUtil.inputString();
			HotelGuest second = HotelGuest.toObject(info2);
			list.add(first);
			list.add(second);
		}
		while (command < 9) {
			command = makeChoice(command);

			switch (command) {
			case 1:
				list = SortUtil.sort(list, Comparators.sortToBigByDays);
				PrintUtil.printByDays(list);
				break;
			case 2:
				list = SortUtil.sort(list, Comparators.sortToSmallByDays);
				PrintUtil.printByDays(list);
				break;
			case 3:
				list = SortUtil.sort(list, Comparators.sortToBigByRoomNum);
				PrintUtil.printByRoomNumber(list);
				break;
			case 4:
				list = SortUtil.sort(list, Comparators.sortToSmallByRoomNum);
				PrintUtil.printByRoomNumber(list);
				break;
			case 5:
				list = SortUtil.sort(list, TYPES[0]);
				PrintUtil.printByRoomClass(list);
				break;
			case 6:
				list = SortUtil.sort(list, TYPES[1]);
				PrintUtil.printByName(list);
				break;
			case 7:
				list = SortUtil.sort(list, TYPES[2]);
				PrintUtil.printByMotherland(list);
				break;
			case 8:
				list = SortUtil.sort(list, TYPES[3]);
				PrintUtil.printByReason(list);
				break;
			}
		}
	}
}
