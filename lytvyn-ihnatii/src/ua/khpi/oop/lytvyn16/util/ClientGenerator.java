package ua.khpi.oop.lytvyn16.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

import ua.khpi.oop.lytvyn16.model.Client;

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
	static String[] EYES = { "Зелені", "Блакитні", "Карі" };
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
	 * Генерує нового клієнта бюро знайомств.
	 * 
	 * @return client
	 */
	public static Client generate(int regNum) {
		final Client client = new Client(regNum);

		final Date today = new Date();
		final SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy");
		final String date = ft.format(today);

		final int border = Integer.parseInt(
		        date.substring(date.lastIndexOf('.') + 1)) - 18;

		final LocalDate birthday = DateUtil
		        .parse(BIRTHDAY[randNum(BIRTHDAY.length)]
		                + randNum(border - 1970, 1970));
		client.setBirthday(birthday);
		client.setEyes(EYES[randNum(EYES.length)]);
		client.setGender(GENDER[randNum(GENDER.length)]);
		if (client.getGender().equals("Чоловік")) {
			client.setName(MALE_NAMES[randNum(MALE_NAMES.length)]);
		} else {
			client.setName(FEMALE_NAMES[randNum(FEMALE_NAMES.length)]);
		}
		client.setHeight(randNum(MAX_HEIGHT - MIN_HEIGHT, MIN_HEIGHT));
		final String hobbies = HOBBIES[randNum(HOBBIES.length)];
		client.setHobby(hobbies);
		client.setRegDate(DateUtil.parse(date));
		final String reqs = REQUIREMENTS[randNum(REQUIREMENTS.length)];
		client.setRequirements(reqs);

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
