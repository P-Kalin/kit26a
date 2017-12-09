package ua.khpi.oop.pavlova14;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ua.khpi.oop.pavlova10.HotelGuest;
import ua.khpi.oop.pavlova10.LinkedList;

public class RegexSearch {
	private static Pattern pattern;
	private static Matcher matcher;

	// private static String[] PARAMETERS = {"NAME", "BIRTHDAY", "MOTHERLAND",
	// "PASSPORT", "ARRIVAL", "EVICTION", "NUM", "CLASS", "PLACES", "REASON"};

	public static ArrayList<String> search(LinkedList<HotelGuest> list, String toFind) {
		ArrayList<String> found = new ArrayList<>();
		HotelGuest[] inArray = list.toArray();
		String[] inStringArray = toStringArray(inArray);
		pattern = Pattern.compile(toFind);
		for (int i = 0; i < inStringArray.length; i++) {
			matcher = pattern.matcher(inStringArray[i]);
			if (matcher.find())
				found.add(inStringArray[i]);
		}
		return found;
	}

	private static String[] toStringArray(HotelGuest[] array) {
		String[] inStringArray = new String[array.length];
		for (int i = 0; i < array.length; i++)
			inStringArray[i] = array[i].toStringForRegex();
		return inStringArray;
	}
}
