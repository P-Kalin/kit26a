package ua.khpi.oop.lytvyn14;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Містить методи створення та виведення клієнтів.
 * 
 * @author student Lytvyn I.I. KIT-26A
 */
public class ClientUtil {

	enum REGEX {
		date, height, string, name, gender
	}

	static String[] filtrs = {
	        "((0[1-9]|[12]\\d)\\.(0[1-9]|1[012])|30\\.(0[13-9]|1[012])|31\\.(0[13578]|1[02]))\\.(19|20)\\d\\d",
	        "2[0-3]\\d|1[5-9]\\d",
	        "[а-яіїА-ЯІЇ][а-яіїА-ЯІЇ -']{1,50}",
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

	/**
	 * @param array
	 * @return
	 */
	public static boolean checkArray(String[] array) {
		boolean safe = false;
		for (int i = 0; i < array.length; i++) {
			if (check(REGEX.string, array[i])) {
				safe = true;
			} else {
				safe = false;
			}
		}
		return safe;
	}

	/**
	 * @param client
	 * @return
	 */
	public static String clientToData(Client client) {
		String data = "";
		data += client.getGender() + "#";
		data += client.getRegNum() + "#";
		data += client.getRegDate() + "#";
		data += client.getName() + "#";
		data += client.getHeight() + "#";
		data += client.getEyes() + "#";
		data += client.getBirthday() + "#";

		final int hobbCount = client.getHobbies().length;
		if (hobbCount > 1) {
			for (int i = 0; i < hobbCount - 1; i++) {
				data += client.getHobbies()[i] + ",";
			}
		}
		data += client.getHobbies()[hobbCount - 1] + "#";

		final int reqsCount = client.getRequirements().length;
		if (reqsCount > 1) {
			for (int i = 0; i < reqsCount - 1; i++) {
				data += client.getRequirements()[i] + ",";
			}
		}
		data += client.getRequirements()[reqsCount - 1];

		return data;
	}

	/**
	 * стать; реєстраційний номер; дата реєстрації; відомості про себе
	 * (довільний набір властивостей: ім’я, зріст, колір очей, дата народження,
	 * хобі тощо); вимоги до партнера (довільний набір властивостей).
	 * 
	 * @param client
	 *            клієнт бюро знайомств
	 * 
	 * @return info
	 */
	public static String info(Client client) {
		String hobbies = "";
		for (int i = 0; i < client.getHobbies().length; i++) {
			hobbies += "" + (i + 1) + ". " + client.getHobbies()[i] + "\n";
		}
		String requirements = "";
		for (int i = 0; i < client.getRequirements().length; i++) {
			requirements += "" + (i + 1) + ". "
			        + client.getRequirements()[i] + "\n";
		}
		final String info = "-----------Client-----------\n" +
		        "Стать: " + client.getGender() + "\n" +
		        "Реєстраційний номер: " + client.getRegNum() + "\n" +
		        "Дата реєстрації: " + client.getRegDate() + "\n" +
		        "------------Info------------\n" +
		        "Ім’я: " + client.getName() + "\n" +
		        "Зріст: " + client.getHeight() + "\n" +
		        "Колір очей: " + client.getEyes() + "\n" +
		        "Дата народження: " + client.getBirthday() + "\n" +
		        "Хобі:\n" + hobbies +
		        "-----------Partner-----------\n" +
		        "Вимоги до партнера:\n" + requirements +
		        "_____________________________\n";
		return info;
	}

	/**
	 * @param list
	 * @return
	 */
	public static Client[] listToArray(LinkedList<Client> list) {
		final Client[] array = new Client[list.size()];
		int i = 0;
		for (final Client client : list) {
			array[i++] = client;
		}
		return array;
	}

	/**
	 * @param data
	 * @return
	 */
	public static Client parse(String data) {
		final String[] temp = data.split("#");
		final Client client = new Client();
		final String[] hobby = temp[7].split(",");
		final String[] reqs = temp[8].split(",");

		boolean safe = check(REGEX.gender, temp[0]);
		if (safe) {
			safe = check(REGEX.date, temp[2]);
			if (safe) {
				safe = check(REGEX.name, temp[3]);
				if (safe) {
					safe = check(REGEX.height, temp[4]);
					if (safe) {
						safe = check(REGEX.string, temp[5]);
						if (safe) {
							safe = check(REGEX.date, temp[6]);
							if (safe) {
								safe = checkArray(hobby);
								if (safe) {
									safe = checkArray(reqs);
								}
							}
						}
					}
				}
			}
		}

		if (safe) {
			client.setGender(temp[0]);
			client.setRegNum(Integer.parseInt(temp[1]));
			client.setRegDate(temp[2]);
			client.setName(temp[3]);
			client.setHeight(Integer.parseInt(temp[4]));
			client.setEyes(temp[5]);
			client.setBirthday(temp[6]);
			client.setHobbies(hobby);
			client.setRequirements(reqs);
		} else {
			return null;
		}
		return client;
	}

	/*
	 * Знайти всі комбінації пар із різностатевих партнерів з різницею у віці не
	 * більше 5 років для ранкових пробіжок в парках Київського району (може
	 * бути вказано в довільній формі у вимогах до партнера).
	 */
	public static LinkedList<Client> search(LinkedList<Client> list) {
		final LinkedList<Client> result = new LinkedList<>();
		for (int i = 0; i < list.size(); i++) {
			for (int j = i + 1; j < list.size() - 1 - i; j++) {
				final Client first = list.get(i);
				final Client second = list.get(j);
				if (condition(first, second)) {
					result.addLast(first);
					result.addLast(second);
				}
			}
		}
		return result;
	}

	/**
	 * Сортування контейнеру за допомогою компаратора
	 * 
	 * @param list
	 * 
	 * @param clientComparator
	 *            компаратор для сортування
	 */
	public static void sort(LinkedList<Client> list,
	        ClientComparator clientComparator) {
		final Client[] temp = listToArray(list);
		MergeSort.sort(temp, clientComparator);
		list.clear();
		for (int i = 0; i < temp.length; i++) {
			list.addLast(temp[i]);
		}
	}

	/**
	 * @param client
	 * @return
	 */
	@Deprecated
	public static boolean validate(Client client) {
		boolean safe = false;
		safe = check(REGEX.name, client.getName());
		safe = check(REGEX.date, client.getBirthday());
		safe = check(REGEX.string, client.getEyes());
		safe = check(REGEX.string, client.getGender());
		safe = check(REGEX.height, Integer.toString(client.getHeight()));
		safe = checkArray(client.getHobbies());
		safe = checkArray(client.getRequirements());
		safe = check(REGEX.date, client.getRegDate());

		return safe;
	}

	/**
	 * @param first
	 * @param second
	 * @return
	 */
	private static boolean condition(Client first, Client second) {
		boolean isGood = false;

		final String check = "ранкові пробіжки в парках Київського району";

		if (first.getGender().equals(second.getGender())) {
			return false;
		}

		String temp = first.getBirthday();
		final int left = Integer
		        .parseInt(temp.substring(temp.lastIndexOf('.') + 1));
		temp = second.getBirthday();
		final int right = Integer
		        .parseInt(temp.substring(temp.lastIndexOf('.') + 1));

		if (left - right > 5 || left - right < 0) {
			return false;
		}

		for (final String string : first.getRequirements()) {
			if (string.equals(check)) {
				isGood = true;
			}
		}
		if (isGood) {
			isGood = false;
			for (final String string : second.getRequirements()) {
				if (string.equals(check)) {
					isGood = true;
				}
			}
		}
		return isGood;
	}
}
