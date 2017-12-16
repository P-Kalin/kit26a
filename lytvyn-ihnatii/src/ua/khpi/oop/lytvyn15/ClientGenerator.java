package ua.khpi.oop.lytvyn15;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Random;

/**
 * 
 * 
 * @author student Lytvyn I.I. KIT-26A
 */
public class ClientGenerator {
	private static final int MIN_HEIGHT = 150;
	private static final int MAX_HEIGHT = 210;
	static String[] MALE_NAMES = { "АНДРІЙ", "АРСЕН", "АРСЕНІЙ", "АРТЕМ",
	        "АРТУР", "БОГДАН", "ВАДИМ", "ВАСИЛЬ", "ВІКТОР", "ВІТАЛІЙ",
	        "ВЛАДИСЛАВ", "ВОЛОДИМИР", "ДАВИД", "ДАНИЇЛ", "ДАНИЛО", "ДЕМ'ЯН",
	        "ДЕНИС", "ДМИТРО", "ЗАХАР", "ІВАН", "ІГОР", "ІЛЛЯ", "ЛУК'ЯН",
	        "ЛЮБОМИР", "МАКСИМ", "МАРК", "МАРКІЯН", "МАРКО", "МАР'ЯН", "МАТВІЙ",
	        "МИКОЛА", "МИХАЙЛО", "НАЗАР", "НАЗАРІЙ", "ОЛЕГ", "ОЛЕКСАНДР",
	        "ОЛЕКСІЙ", "ОСТАП", "ПАВЛО", "РОМАН", "РОСТИСЛАВ", "РУСЛАН",
	        "СВЯТОСЛАВ", "СЕРГІЙ", "СТАНІСЛАВ", "СТЕПАН", "ТАРАС", "ТИМОФІЙ",
	        "ЮРІЙ", "ЯРОСЛАВ" };
	static String[] FEMALE_NAMES = { "АЛІНА", "АНАСТАСІЯ", "АНГЕЛІНА",
	        "АНДРІАНА", "АННА", "БОЖЕНА", "ВАЛЕРІЯ", "ВАСИЛИНА", "ВЕРОНІКА",
	        "ВІКТОРІЯ", "ВІРА", "ДАРИНА", "ДАРІЯ", "ДІАНА", "ЕВЕЛІНА", "ЕМІЛІЯ",
	        "ЄВА", "ЄЛИЗАВЕТА", "ЗЛАТА", "ІВАННА", "ІЛОНА", "ІРИНА", "КАРІНА",
	        "КАРОЛІНА", "КАТЕРИНА", "ЛІЛІЯ", "МАРГАРИТА", "МАРІЯ", "МАРТА",
	        "МАР'ЯНА", "МІЛАНА", "НАДІЯ", "НАТАЛІЯ", "ОКСАНА", "ОЛЕКСАНДРА",
	        "ОЛЕНА", "ОЛЕСЯ", "ОЛЬГА", "ПОЛІНА", "РОКСОЛАНА", "СОЛОМІЯ",
	        "СОФІЯ", "ТЕТЯНА", "УЛЯНА", "ХРИСТИНА", "ЮЛІАНА", "ЮЛІЯ", "ЮСТИНА",
	        "ЯНА", "ЯРИНА" };
	static String[] BIRTHDAY = { "01.01." };
	static String[] EYES = { "Зелені" };
	static String[] GENDER = { "Чоловік", "Жінка" };
	static String[] HOBBIES = { "Спів", "Щось", "Щось", "Щось", "Щось",
	        "Щось", "Щось", "Щось", "Щось", "Щось", "Щось", "Щось", "Щось",
	        "Щось", "Щось", "Щось", "Щось", "Щось", "Щось", "Щось", "Щось",
	        "Щось", "Щось", "Щось", "Щось", "Щось", "Щось", "Щось", "Щось" };
	static String[] REQUIREMENTS = { "Щось", "Щось", "Щось", "Щось", "Щось",
	        "Щось", "Щось", "Щось", "Щось", "Щось", "Щось", "Щось", "Щось",
	        "Щось", "Щось", "Щось", "Щось", "Щось", "Щось", "Щось", "Щось",
	        "Щось", "Щось", "Щось", "Щось", "Щось", "Щось", "Щось", "Щось",
	        "ранкові пробіжки в парках Київського району" };

	/**
	 * Створює нового клієнта бюро знайомств.
	 * 
	 * @return client
	 */
	public static Client build() {
		final Client client = new Client();

		final Date today = new Date();
		final SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy");

		try {
			System.out.format("\nВведіть стать.");
			final String gender = DialogHelper.getInput();
			if (ClientUtil.check(ClientUtil.REGEX.gender, gender)) {
				client.setGender(gender);
			} else {
				return null;
			}

			System.out.format("\nВведіть ім'я.");
			final String name = DialogHelper.getInput();
			if (ClientUtil.check(ClientUtil.REGEX.name, name)) {
				client.setName(name);
			} else {
				return null;
			}

			System.out.format("\nВведіть зріст.");
			final String height = DialogHelper.getInput();
			if (ClientUtil.check(ClientUtil.REGEX.height, height)) {
				client.setHeight(Integer.parseInt(height));
			} else {
				return null;
			}

			System.out.format("\nВведіть колір очей.");
			final String eyes = DialogHelper.getInput();
			if (ClientUtil.check(ClientUtil.REGEX.string, eyes)) {
				client.setEyes(eyes);
			} else {
				return null;
			}

			System.out.format("\nВведіть дату народження"
			        + " у форматі dd.MM.yyyy.");
			final String birthday = DialogHelper.getInput();
			if (ClientUtil.check(ClientUtil.REGEX.date, birthday)) {
				client.setBirthday(birthday);
			} else {
				return null;
			}

			System.out.format("\nВведіть хобі через \";\"");
			final String hobby = DialogHelper.getInput();
			final String[] hobbies = hobby.split(";");
			if (ClientUtil.checkArray(hobbies)) {
				client.setHobbies(hobbies);
			} else {
				return null;
			}

			System.out.format("\nВведіть вимоги до партнера через \";\"");
			final String reqs = DialogHelper.getInput();
			final String[] requirements = reqs.split(";");
			if (ClientUtil.checkArray(requirements)) {
				client.setRequirements(requirements);
			} else {
				return null;
			}

			final String regDate = ft.format(today);
			if (ClientUtil.check(ClientUtil.REGEX.date, regDate)) {
				client.setRegDate(regDate);
			} else {
				return null;
			}

		} catch (final Exception e) {
			System.out.println(e.getMessage());
		}
		return client;
	}

	/**
	 * Генерує нове бюро знайомств.
	 * 
	 * @param size
	 *            кількість клієнтів
	 * @return bureau бюро знайомств
	 */
	public static LinkedList<Client> buildClients(int size) {
		final LinkedList<Client> clients = new LinkedList<>();
		for (int i = 0; i < size; i++) {
			final Client temp = buildTest();
			temp.setRegNum(i);
			clients.addLast(temp);
		}
		return clients;
	}

	/**
	 * Генерує нового клієнта бюро знайомств.
	 * 
	 * @return client
	 */
	public static Client buildTest() {
		final Client client = new Client();

		final Date today = new Date();
		final SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy");
		final String date = ft.format(today);

		final int border = Integer.parseInt(
		        date.substring(date.lastIndexOf('.') + 1)) - 18;

		client.setBirthday(BIRTHDAY[randNum(BIRTHDAY.length)]
		        + randNum(border - 1970, 1970));
		client.setEyes(EYES[randNum(EYES.length)]);
		client.setGender(GENDER[randNum(GENDER.length)]);
		if (client.getGender().equals("Чоловік")) {
			client.setName(MALE_NAMES[randNum(MALE_NAMES.length)]);
		} else {
			client.setName(FEMALE_NAMES[randNum(FEMALE_NAMES.length)]);
		}
		client.setHeight(randNum(MAX_HEIGHT - MIN_HEIGHT, MIN_HEIGHT));
		final String hobbies = HOBBIES[randNum(HOBBIES.length)];
		final String[] hobby = hobbies.split(";");
		client.setHobbies(hobby);
		client.setRegDate(date);
		final String reqs = REQUIREMENTS[randNum(REQUIREMENTS.length)];
		final String[] requirements = reqs.split(";");
		client.setRequirements(requirements);

		return client;
	}

	/**
	 * Повертає псевдо-рандомне число.
	 * 
	 * @param border
	 *            допустима границя чисел
	 *
	 * @return число типу Integer
	 * @see java.util.Random#nextInt(int)
	 */
	public static int randNum(int border) {
		if (border < 0) {
			throw new IllegalArgumentException();
		}
		final Random rand = new Random();
		final int randomNum = rand.nextInt(border);
		return randomNum;
	}

	/**
	 * Повертає псевдо-рандомне число.
	 * 
	 * @param border
	 *            допустима границя чисел
	 *
	 * @return число типу Integer
	 * @see java.util.Random#nextInt(int)
	 */
	public static int randNum(int border, int bottom) {
		if (border < 0) {
			throw new IllegalArgumentException();
		}
		final Random rand = new Random();
		final int randomNum = rand.nextInt(border) + bottom;
		return randomNum;
	}
}
