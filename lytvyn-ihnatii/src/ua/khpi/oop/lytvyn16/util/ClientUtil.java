package ua.khpi.oop.lytvyn16.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ua.khpi.oop.lytvyn16.model.Client;

/**
 * 
 * 
 * @author student Lytvyn I.I. KIT-26A
 */
public class ClientUtil {
	public enum REGEX {
		height, string, name, gender
	}

	static String[] filtrs = {
	        "2[0-3]\\d|1[5-9]\\d",
	        "[а-яіїА-ЯІЇ][а-яіїА-ЯІЇ -';,]{1,50}",
	        "[А-ЯІЇ][а-яіїА-ЯІЇ -']{1,20}",
	        "(Чоловік)|(Жінка)" };

	/**
	 * @param key
	 * @param data
	 * @return
	 */
	public static boolean check(REGEX key, String data) {
		final Pattern pattern = Pattern.compile(filtrs[key.ordinal()]);
		final Matcher matcher = pattern.matcher(data);
		return matcher.matches();
	}

	/*
	 * Знайти всі комбінації пар із різностатевих партнерів з різницею у віці не
	 * більше 5 років для ранкових пробіжок в парках Київського району (може
	 * бути вказано в довільній формі у вимогах до партнера).
	 */
	public static void search(ObservableList<Client> list) {
		final ObservableList<Client> result = FXCollections
		        .observableArrayList();
		for (int i = 0; i < list.size(); i++) {
			for (int j = i + 1; j < list.size() - 1 - i; j++) {
				final Client first = list.get(i);
				final Client second = list.get(j);
				if (condition(first, second)) {
					result.add(first);
					result.add(second);
				}
			}
		}
		// return result;
	}

	private static boolean condition(Client first, Client second) {
		boolean isGood = false;

		final String check = "ранкові пробіжки в парках Київського району";

		if (first.getGender().equals(second.getGender())) {
			return false;
		}

		String temp = first.getBirthday().toString();
		final int left = Integer
		        .parseInt(temp.substring(0, temp.indexOf("-")));
		temp = second.getBirthday().toString();
		final int right = Integer
		        .parseInt(temp.substring(0, temp.indexOf("-")));

		if (left - right > 5 || left - right < 0) {
			return false;
		}

		String[] reqs = first.getRequirements().split(",");

		for (final String string : reqs) {
			if (string.equals(check)) {
				isGood = true;
			}
		}
		if (isGood) {
			isGood = false;
			reqs = second.getRequirements().split(",");
			for (final String string : reqs) {
				if (string.equals(check)) {
					isGood = true;
				}
			}
		}
		return isGood;
	}
}
