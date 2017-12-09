package ua.khpi.oop.pavlova14;

import java.util.Random;

import ua.khpi.oop.pavlova10.HotelGuest;
import ua.khpi.oop.pavlova10.LinkedList;
import ua.khpi.oop.pavlova10.util.Comparators;
import ua.khpi.oop.pavlova10.util.PrintUtil;
import ua.khpi.oop.pavlova10.util.SortUtil;
import ua.khpi.oop.pavlova12.RegexSearch;

public class Demonstration {
	static FirstThread firstThread;
	static SecondThread secondThread;
	private static Random random = new Random();

	public static void demonstrateParallel() {
		firstThread = new FirstThread(); // Создание потока
		secondThread = new SecondThread();

		System.out.println("Demonstartion of threads");
		firstThread.start(); // Запуск потока
		secondThread.start();

		// int rand = Integer.valueOf(random.nextInt(5));
		for (int i = 0; i < 2; i++) {
			try {
				Thread.sleep(1000); // Приостанавливает поток на 1 секунду
			} catch (InterruptedException e) {

			}
		}
		firstThread.finish();
		secondThread.finish();
		try {
			Thread.sleep(1000); // Приостанавливает поток на 1 секунду
		} catch (InterruptedException e) {
		}
		System.out.println("\n\n\n Demonstration is over");
	}

	public static void demonstrateSequential() {
		LinkedList<HotelGuest> list = ExtraFunctions.createDefaultList();
		for (int i = 0; i < 3; i++) {
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

	}
}
