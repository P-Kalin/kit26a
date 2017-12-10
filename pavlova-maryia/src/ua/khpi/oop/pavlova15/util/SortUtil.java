package ua.khpi.oop.pavlova15.util;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

import ua.khpi.oop.pavlova10.HotelGuest;
import ua.khpi.oop.pavlova15.ExtraFunctions;

public class SortUtil {
	private static final Locale ALPHABET = new Locale("ru");

	public static ArrayList<HotelGuest> sort(ArrayList<HotelGuest> array, Comparator<HotelGuest> comparator) {
		Collections.sort(array, comparator);
		return array;
	}

	public static ArrayList<HotelGuest> sort(ArrayList<HotelGuest> array, String type) {
		ArrayList<String> inStrings = ExtraFunctions.createStringArrayList(array, type);
		inStrings = collatorSort(inStrings, Strength.Tertiary);
		return ExtraFunctions.convertToHotelGuestArrayList(array, inStrings, type);
	}

	private static ArrayList<String> collatorSort(ArrayList<String> array, Strength strength) {
		Collator collator = Collator.getInstance(ALPHABET);
		collator.setStrength(strength.getStrength());
		Collections.sort(array, collator);
		return array;
	}
}
