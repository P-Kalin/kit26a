package ua.khpi.oop.lytvyn08;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * Містить методи створення та виведення клієнтів.
 * 
 * @author student Lytvyn I.I. KIT-26A
 */
public class ClientUtil {

	/**
	 * Зріст за замовчуванням
	 */
	private final static int DEFAULT_HIGHT = 160;

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
	 * Повертає псевдо-рандомне число.
	 * 
	 * @param border
	 *            допустима границя чисел
	 *
	 * @return число типу Integer
	 * @see java.util.Random#nextInt(int)
	 */
	public static int randInt(int border) {
		if (border < 0) {
			throw new IllegalArgumentException();
		}
		final Random rand = new Random();
		final int randomNum = rand.nextInt(border);
		return randomNum;
	}

	/**
	 * Створює нового клієнта бюро знайомств.
	 * 
	 * @return client
	 */
	static Client build() {
		final Client client = new Client();

		final Date today = new Date();
		final SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy");

		try {
			System.out.format("\nВведіть стать.");
			final String gender = DialogHelper.getInput();
			System.out.format("\nВведіть ім'я.");
			final String name = DialogHelper.getInput();
			System.out.format("\nВведіть зріст.");
			final int height = Integer.parseInt(DialogHelper.getInput());
			System.out.format("\nВведіть колір очей.");
			final String eyes = DialogHelper.getInput();
			System.out.format("\nВведіть дату народження"
			        + " у форматі dd.MM.yyyy.");
			final String birthday = DialogHelper.getInput();
			System.out.format("\nВведіть хобі через \";\".");
			final String hobby = DialogHelper.getInput();
			System.out.format("\nВведіть вимоги до партнера через \";\".");
			final String reqs = DialogHelper.getInput();

			final String[] hobbies = hobby.split(";");
			final String[] requirements = reqs.split(";");

			client.setName(name);
			client.setBirthday(birthday);
			client.setEyes(eyes);
			client.setGender(gender);
			client.setHeight(height);
			client.setHobbies(hobbies);
			client.setRegDate(ft.format(today));
			client.setRequirements(requirements);
		} catch (final IOException e) {
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
	static ArrayList<Client> buildClients(int size) {
		final ArrayList<Client> bureau = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			final Client temp = ClientUtil.buildTest();
			temp.setRegNum(i);
			bureau.add(temp);
		}
		return bureau;
	}

	/**
	 * Генерує нового клієнта бюро знайомств.
	 * 
	 * @return client
	 */
	static Client buildTest() {
		final Client client = new Client();

		final Date today = new Date();
		final SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy");

		client.setName("Some_Name");
		client.setBirthday(ft.format(today));
		client.setEyes("Some_Eyes");
		client.setGender("Some_Gender");
		client.setHeight(DEFAULT_HIGHT);
		final String hobbies = "Some_Hobby;Some_Hobby;Some_Hobby";
		final String[] hobby = hobbies.split(";");
		client.setHobbies(hobby);
		client.setRegDate(ft.format(today));
		final String reqs = "Some_Requirement;Some_Requirement;"
		        + "Some_Requirement";
		final String[] requirements = reqs.split(";");
		client.setRequirements(requirements);
		return client;
	}
}
