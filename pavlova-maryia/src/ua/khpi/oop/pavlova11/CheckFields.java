package ua.khpi.oop.pavlova11;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckFields {

	private static Pattern pattern;
	private static Matcher matcher;
	private static final String INFO_SEPARATOR = "|";

	private static boolean checkNameSurname(String test) {
		pattern = Pattern.compile("^[А-Я][а-я]+((\\s[А-Я][а-я]?\\.)([А-Я][а-я]?\\.){1,})$");
		matcher = pattern.matcher(test);
		return matcher.matches();
	}

	private static boolean checkDate(String test) {
		pattern = Pattern.compile(
				"^[0-3]?[0-9]\\-(Январь|Февраль|Март|Апрель|Май|Июнь|Июль|Август|Сентябрь|Октябрь|Ноябрь|Декабрь){1}\\-[1-2][9|0][0-9][0-9]$");
		matcher = pattern.matcher(test);
		return matcher.matches();
	}

	private static boolean checkPassport(String test) {
		pattern = Pattern.compile("^МТ\\-([0-9]{6})$");
		matcher = pattern.matcher(test);
		return matcher.matches();
	}

	private static boolean checkMotherLand(String test) {
		pattern = Pattern.compile("^[А-Я][а-я]+(,\\s[А-Я][а-я]+)$");
		matcher = pattern.matcher(test);
		return matcher.matches();
	}

	private static boolean checkReasonOfArrival(String test) {
		pattern = Pattern.compile("Командировка|Туризм");
		matcher = pattern.matcher(test);
		return matcher.matches();
	}

	private static boolean checkRoomNumber(String test) {
		pattern = Pattern.compile("[1-3]?[0-9][0-9]?$");
		matcher = pattern.matcher(test);
		return matcher.matches();
	}

	private static boolean checkRoomClass(String test) {
		pattern = Pattern.compile("Эконом|Бизнес");
		matcher = pattern.matcher(test);
		return matcher.matches();
	}

	private static boolean checkRoomPlaces(String test) {
		pattern = Pattern.compile("^[1-4]$");
		matcher = pattern.matcher(test);
		return matcher.matches();
	}

	public static boolean generalTest(String info) {
		String[] x = createStringArray(info);
		int counter = 0;
		if (!checkNameSurname(x[0])) {
			System.out.println("Неверная запись Ф.И.О. гостя (требуемый формат: Иванов И.И.)");
			counter++;
		}
		if (!checkDate(x[1])) {
			System.out.println("Неверная запись даты рождения гостя (требуемый формат: 01-Январь-1990)");
			counter++;
		}
		if (!checkMotherLand(x[2])) {
			System.out.println("Неверная запись страны и города гостя (требуемый формат: Украина, Харьков)");
			counter++;
		}

		if (!checkPassport(x[3])) {
			System.out.println("Неверная запись паспорта гостя (требуемый формат: МТ-111111)");
			counter++;
		}

		if (!checkDate(x[4])) {
			System.out.println("Неверная запись даты заселения гостя (требуемый формат: 01-Январь-1990)");
			counter++;
		}

		if (!checkDate(x[5])) {
			System.out.println("Неверная запись даты выселения гостя (требуемый формат: 01-Январь-1990)");
			counter++;
		}

		if (!checkRoomNumber(x[6])) {
			System.out.println("Неверная запись номера гостя (диапазон номеров: 1-399)");
			counter++;
		}
		if (!checkRoomClass(x[7])) {
			System.out.println("Неверная запись класса номера гостя (возможные варианты: Эконом, Бизнес)");
			counter++;
		}

		if (!checkRoomPlaces(x[8])) {
			System.out.println("Неверная запись количества мест в номере гостя (диапазон мест: 1-4)");
			counter++;
		}

		if (!checkReasonOfArrival(x[9])) {
			System.out.println("Неверная запись причины заселения гостя (возможные варианты: Туризм, Командировка)");
			counter++;
		}

		if (counter > 0) {
			System.out.println("Количество ошибок: " + counter);
			return false;
		} else
			return true;

	}

	private static String[] createStringArray(String info) {
		String[] elementInArray = new String[10];
		StringTokenizer elementExtractor = new StringTokenizer(info, INFO_SEPARATOR);
		int i = 0;
		while (elementExtractor.hasMoreTokens()) {
			elementInArray[i] = ((String) elementExtractor.nextElement()).trim();
			i++;
		}
		return elementInArray;
	}

}
// [1-31]\\.
//