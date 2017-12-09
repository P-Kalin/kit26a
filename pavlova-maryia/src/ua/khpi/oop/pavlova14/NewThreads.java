package ua.khpi.oop.pavlova14;

import java.util.Random;

import ua.khpi.oop.pavlova10.HotelGuest;
import ua.khpi.oop.pavlova10.LinkedList;
import ua.khpi.oop.pavlova10.util.Comparators;
import ua.khpi.oop.pavlova10.util.SortUtil;

class FirstThread extends Thread {
	private Random random = new Random();
	private static boolean toFinish = false;

	public void run() {
		LinkedList<HotelGuest> list = ExtraFunctions.createDefaultList();

		for (int i = 0; i < 2; i++) {
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
					break;
				case 2:
					list = SortUtil.sort(list, "MOTHERLAND");
					break;
				case 3:
					list = SortUtil.sort(list, "SURNAME");
					break;
				case 4:
					list = SortUtil.sort(list, "REASON");
					break;
				case 5:
					list = SortUtil.sort(list, Comparators.sortToBigByRoomNum);
					break;
				default:
					list = SortUtil.sort(list, "ROOM_CLASS");
					break;
				}
			}
		}
	}

	public void finish() {
		toFinish = true;
	}
}

class SecondThread extends Thread {

	private static Random random = new Random();
	private static boolean toFinish = false;

	public void run() {
		LinkedList<HotelGuest> list = ExtraFunctions.createDefaultList();
		for (int i = 0; i < 2; i++) {
			if (!toFinish) {
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					interrupt();
				}
				int value = Integer.valueOf(random.nextInt(6));
				switch (value) {
				case 1:
					RegexSearch.search(list, "[А-Я][а-я]+(,\\sУкраина)");
					break;
				case 2:
					RegexSearch.search(list, "Петров((\\s[А-Я][а-я]?\\.)([А-Я][а-я]?\\.){1,})");
					break;
				case 3:
					RegexSearch.search(list, "[А-Я][а-я]+((\\s[А]\\.)([Р]\\.){1,})");
					break;
				case 4:
					RegexSearch.search(list, "[5][1]");
					break;
				case 5:
					RegexSearch.search(list, "МТ\\-[4][5][2][1-8]{3}");
					break;
				default:
					RegexSearch.search(list, "Эконом");
					break;
				}
			}
		}
	}

	public void finish() {
		toFinish = true;
	}
}
