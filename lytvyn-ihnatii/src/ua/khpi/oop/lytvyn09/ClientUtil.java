package ua.khpi.oop.lytvyn09;

import java.io.IOException;
import java.text.SimpleDateFormat;
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
	public static LinkedList<Client> buildClients(int size) {
		final LinkedList<Client> clients = new LinkedList<>();
		for (int i = 0; i < size; i++) {
			final Client temp = ClientUtil.buildTest();
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

	/**
	 * Конвертує дані про клієнта у рядок для подальшого збереження
	 * 
	 * @param client
	 *            клієнт для конвертування
	 * @return дані про клієнта
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
	 * Створює клієнта з рядка
	 * 
	 * @param data
	 *            дані
	 * @return клієнт створений з рядка
	 */
	public static Client parse(String data) {
		final String[] temp = data.split("#");
		final Client client = new Client();
		client.setGender(temp[0]);
		client.setRegNum(Integer.parseInt(temp[1]));
		client.setRegDate(temp[2]);
		client.setName(temp[3]);
		client.setHeight(Integer.parseInt(temp[4]));
		client.setEyes(temp[5]);
		client.setBirthday(temp[6]);
		final String[] hobby = temp[7].split(",");
		client.setHobbies(hobby);
		final String[] reqs = temp[8].split(",");
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
	public static int randInt(int border) {
		if (border < 0) {
			throw new IllegalArgumentException();
		}
		final Random rand = new Random();
		final int randomNum = rand.nextInt(border);
		return randomNum;
	}
}
