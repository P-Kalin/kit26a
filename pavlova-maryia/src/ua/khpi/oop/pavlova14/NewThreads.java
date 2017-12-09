package ua.khpi.oop.pavlova14;

import java.util.Random;

import ua.khpi.oop.pavlova10.HotelGuest;
import ua.khpi.oop.pavlova10.LinkedList;
import ua.khpi.oop.pavlova10.util.Comparators;
import ua.khpi.oop.pavlova10.util.PrintUtil;
import ua.khpi.oop.pavlova10.util.SortUtil;
import ua.khpi.oop.pavlova12.RegexSearch;

class FirstThread extends Thread {
	private Random random = new Random();
	private static boolean toFinish = false;

	public void run() {
		LinkedList<HotelGuest> list = ExtraFunctions.createDefaultList();

		do {
			if (!toFinish) {
				try {
					sleep(1000); // Приостанавливает поток на 1 секунду
				} catch (InterruptedException e) {
					interrupt();
				}
				int value = Integer.valueOf(random.nextInt(6));
				switch (value) {
				case 1:
					list = SortUtil.sort(list, Comparators.sortToBigByDays);
					System.out.println("Sorted list by days");
					PrintUtil.printByDays(list);
					break;
				case 2:
					list = SortUtil.sort(list, "MOTHERLAND");
					System.out.println("Sorted list by motherland");
					PrintUtil.printByMotherland(list);
					break;
				case 3:
					list = SortUtil.sort(list, "SURNAME");
					System.out.println("Sorted list by surname");
					PrintUtil.printByName(list);
					break;
				case 4:
					list = SortUtil.sort(list, "REASON");
					System.out.println("Sorted list by reason");
					PrintUtil.printByReason(list);
					break;
				case 5:
					list = SortUtil.sort(list, Comparators.sortToBigByRoomNum);
					System.out.println("Sorted list by room number");
					PrintUtil.printByRoomNumber(list);
					break;
				default:
					list = SortUtil.sort(list, "ROOM_CLASS");
					System.out.println("Sorted list by room class");
					PrintUtil.printByRoomClass(list);
					break;
				}

				System.out.println("List was sorted in FirstThread!!\n");
			}
		} while (true);
		// System.out.println("FirstThread is finished..\n\n");
	}

	public void finish() {
		toFinish = true;
		System.out.println("FirstThread will be finished after one action...");
	}
}

class SecondThread extends Thread {

	private static Random random = new Random();
	private static boolean toFinish = false;

	public void run() {
		LinkedList<HotelGuest> list = ExtraFunctions.createDefaultList();
		do {
			if (!toFinish) {
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					interrupt();
				}
				int value = Integer.valueOf(random.nextInt(6));
				switch (value) {
				case 1:
					System.out.println("Поиск всех гостей из Украины");
					RegexSearch.search(list, "[А-Я][а-я]+(,\\sУкраина)");
					break;
				case 2:
					System.out.println("Поиск всех гостей с фамилией Петров");
					RegexSearch.search(list, "Петров((\\s[А-Я][а-я]?\\.)([А-Я][а-я]?\\.){1,})");
					break;
				case 3:
					System.out.println("Поиск всех гостей с инициалами А.Р.");
					RegexSearch.search(list, "[А-Я][а-я]+((\\s[А]\\.)([Р]\\.){1,})");
					break;
				case 4:
					System.out.println("Поиск всех гостей из 51го номера");
					RegexSearch.search(list, "[5][1]");
					break;
				case 5:
					System.out.println("Поиск всех гостей с паспортом МТ-452ххх");
					RegexSearch.search(list, "МТ\\-[4][5][2][1-8]{3}");
					break;
				default:
					System.out.println("Поиск всех гостей из эконом-класса");
					RegexSearch.search(list, "Эконом");
					break;
				}
				System.out.println("Search was activated in SecondThread!!\n");
			}
		} while (true);

		// System.out.println("SecondThread is finished...\n\n");
	}

	public void finish() {
		toFinish = true;
		System.out.println("SecondThread will be finished after one action...");
	}
}
