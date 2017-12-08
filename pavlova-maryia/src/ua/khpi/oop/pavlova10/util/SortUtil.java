package ua.khpi.oop.pavlova10.util;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;

import ua.khpi.oop.pavlova03.TextUtil;
import ua.khpi.oop.pavlova10.Date;
import ua.khpi.oop.pavlova10.HotelGuest;
import ua.khpi.oop.pavlova10.LinkedList;

public class SortUtil {
	private static final Locale ALPHABET = new Locale("ru");
	private static final String DATE_SEPARATOR = "-";

	public static int countDays(HotelGuest guest) {
		String arr = guest.getDateOfArrival();
		Date arrival = new Date(getDay(arr), getMonth(arr), getYear(arr));

		String ev = guest.getDateOfEviction();
		Date eviction = new Date(getDay(ev), getMonth(ev), getYear(ev));

		return Date.countDifference(arrival, eviction);
	}

	private static String extractYearMonthDay(String data, int pos) {
		ArrayList<String> inArray = (ArrayList<String>) TextUtil.extractElementsFromText(data, DATE_SEPARATOR);
		return inArray.get(pos);
	}

	private static int getDay(String date) {
		return Integer.valueOf(extractYearMonthDay(date, 0));
	}

	private static int getMonth(String date) {
		return convertMonthToInteger(extractYearMonthDay(date, 1));
	}

	private static int getYear(String date) {
		return Integer.valueOf(extractYearMonthDay(date, 2));
	}

	private static int convertMonthToInteger(String month) {
		if (month.equals("Январь"))
			return 1;
		else if (month.equals("Февраль"))
			return 2;
		else if (month.equals("Март"))
			return 3;
		else if (month.equals("Апрель"))
			return 4;
		else if (month.equals("Май"))
			return 5;
		else if (month.equals("Июнь"))
			return 6;
		else if (month.equals("Июль"))
			return 7;
		else if (month.equals("Август"))
			return 8;
		else if (month.equals("Сентябрь"))
			return 9;
		else if (month.equals("Октябрь"))
			return 10;
		else if (month.equals("Ноябрь"))
			return 11;
		else if (month.equals("Декабрь"))
			return 12;
		else
			return 1;
	}

	public static LinkedList<HotelGuest> sort(LinkedList<HotelGuest> list, Comparator<HotelGuest> comparator) {
		HotelGuest[] a = list.toArray();
		Arrays.sort((HotelGuest[]) a, 0, a.length, comparator);

		// Iterator<H> i = this.iterator();
		list = new LinkedList<>();
		for (int i = 0; i < a.length; i++) {
			list.add((HotelGuest) a[i]);
		}
		return list;
	}

	public static LinkedList<HotelGuest> sort(LinkedList<HotelGuest> list, String type) {
		String[] strings = toStringArray(list, type);
		collatorSort(strings, Strength.Tertiary);
		HotelGuest[] guests = createHotelGuestFromString(list, strings, type);
		list = new LinkedList<>();
		for (int i = 0; i < strings.length; i++) {
			list.add(guests[i]);
		}
		return list;
	}

	private static String[] collatorSort(String[] words, Strength strength) {
		Collator collator = Collator.getInstance(ALPHABET);
		collator.setStrength(strength.getStrength());
		Arrays.sort(words, collator);

		return words;
	}

	private static String[] toStringArray(LinkedList<HotelGuest> list, String type) {
		String[] strings = new String[list.getSize()];
		if (type.equals("ROOM_CLASS"))
			for (int i = 0; i < strings.length; i++)
				strings[i] = list.get(i).getRoomClass();
		if (type.equals("SURNAME"))
			for (int i = 0; i < strings.length; i++)
				strings[i] = list.get(i).getGuestNameSurname();
		if (type.equals("MOTHERLAND"))
			for (int i = 0; i < strings.length; i++)
				strings[i] = list.get(i).getGuestMotherland();
		if (type.equals("REASON"))
			for (int i = 0; i < strings.length; i++)
				strings[i] = list.get(i).getReasonOfArrival();
		return strings;
	}

	private static HotelGuest[] createHotelGuestFromString(LinkedList<HotelGuest> list, String[] strings, String type) {
		HotelGuest[] guests = new HotelGuest[strings.length];
		HotelGuest[] temp = list.toArray();
		for (int i = 0; i < strings.length; i++) {
			if (type.equals("ROOM_CLASS")) {
				for (int j = 0; j < strings.length; j++) {
					if (temp[j] != null) {
						if (temp[j].getRoomClass().equals(strings[i])) {
							guests[i] = temp[j];
							temp[j] = null;
							break;
						}
					}
				}
			} else if (type.equals("SURNAME")) {
				for (int j = 0; j < strings.length; j++) {
					if (temp[j] != null) {
						if (temp[j].getGuestNameSurname().equals(strings[i])) {
							guests[i] = temp[j];
							temp[j] = null;
							break;
						}
					}
				}
			} else if (type.equals("MOTHERLAND")) {
				for (int j = 0; j < strings.length; j++) {
					if (temp[j] != null) {
						if (temp[j].getGuestMotherland().equals(strings[i])) {
							guests[i] = temp[j];
							temp[j] = null;
							break;
						}
					}
				}
			} else if (type.equals("REASON")) {
				for (int j = 0; j < strings.length; j++) {
					if (temp[j] != null) {
						if (temp[j].getReasonOfArrival().equals(strings[i])) {
							guests[i] = temp[j];
							temp[j] = null;
							break;
						}
					}
				}
			}
		}
		return guests;
	}
}
