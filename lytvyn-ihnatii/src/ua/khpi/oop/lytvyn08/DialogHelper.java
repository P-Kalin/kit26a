package ua.khpi.oop.lytvyn08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ua.khpi.oop.lytvyn.util.Console;

/**
 * Забезпечує обробку команд користувача у вигляді інтерактивного діалогового
 * меню.
 * 
 * @author student Lytvyn I.I. KIT-26A
 */
public class DialogHelper {

	/**
	 * Перелік команд
	 * 
	 * @author student Lytvyn I.I. KIT-26A
	 */
	public enum ACTION {
		add, generate, exit, read, remove, save, show
	}

	/**
	 * Бюро знайомств
	 */
	static Bureau bureau = new Bureau();

	/**
	 * Клієнти
	 */
	static List<Client> clients = new ArrayList<>();

	/**
	 * Розмежувач
	 */
	public static String LINE = "----------------------------------------------"
	        + "--------------------------\n";
	/**
	 * Сканування вводу
	 */
	public static Scanner sc = new Scanner(System.in);
	/**
	 * Підтвердження завершення роботи
	 */
	public static boolean exit = false;

	/**
	 * Реєстраційний номер
	 */
	private static int regNum = 0;

	/**
	 * Додавання нового клієнту
	 */
	public static void add() {
		final Client temp = ClientUtil.build();
		temp.setRegNum(regNum);
		clients.add(temp);
		bureau.setClients(clients);
		regNum++;
	}

	/**
	 * Генерування нових клієнтів
	 * 
	 * @param count
	 *            кількість клієнтів
	 */
	public static void generate(int count) {
		clients = new ArrayList<>(ClientUtil.buildClients(count));
		regNum = clients.size();
		bureau.setClients(clients);
	}

	/**
	 * Отримує відповідь від користувача
	 * 
	 * @return answer відповідь від користувача
	 * @throws IOException
	 *             виняткова ситуація при роботі з вводом
	 */
	public static String getInput() throws IOException {
		System.out.format("\nВаша відповідь: ");
		final BufferedReader br = new BufferedReader(
		        new InputStreamReader(System.in));
		final String answer = br.readLine(); // Запис тексту до буферу
		return answer;
	}

	/**
	 * Виконує обробку введеної команди
	 * 
	 * @param action
	 *            введена команда
	 * @throws Exception
	 *             будь-яка виникаюча виняткова ситуація
	 */
	public static void handleAction(ACTION action) throws Exception {
		switch (action) {
		case add:
			add();
			break;
		case generate:
			System.out.println("Введіть кількість клієнтів.");
			final String size = getInput();
			final int count = Integer.parseInt(size);
			generate(count);
			break;
		case read:
			/* Зчитування екземпляру контейнеру */
			bureau = LTPSUtil.deserialize();
			clients = new ArrayList<>(bureau.getClients());
			break;
		case remove:
			/* Видалення */
			System.out.println("Введіть реєстраційний номер");
			final String num = getInput();
			final int regNum = Integer.parseInt(num);
			final Client remove = ClientUtil.build();
			remove.setRegNum(regNum);
			remove(remove);
			break;
		case save:
			/* Запис контейнеру */
			LTPSUtil.serialize(bureau);
			break;
		case show:
			show();
			break;
		case exit:
			exit = true;
			break;
		default:
			System.err.println("Error data ^-(");
			break;
		}
	}

	/**
	 * Видалення клієнту
	 * 
	 * @param client
	 *            який потрібно видалити
	 */
	public static void remove(Client client) {
		clients.remove(clients.indexOf(client));
		bureau.setClients(clients);
	}

	/**
	 * Показ клієнтів
	 */
	public static void show() {
		if (bureau.getClients().isEmpty()) {
			System.out.println("Контейнер порожній!");
		} else {
			/* Виведення клієнтів */
			System.out.println("\nПоточний вміст контейнеру:\n");
			System.out.println(bureau);
		}
	}

	/**
	 * Реалізує інтерактивне діалогове меню для забезпечення отримання команд
	 * від користувача
	 */
	public static void start() {
		do {
			Console.clean();
			comands();
			System.out.format("\nВведіть команду: ");
			sc = new Scanner(System.in);
			final ACTION cur = ACTION.valueOf(sc.next());
			try {
				handleAction(cur);
				Console.pause();
			} catch (final Exception ex) {
				System.out.println(ex.toString());
			}
		} while (!exit);
	}

	/**
	 * Список команд
	 */
	private static void comands() {
		System.out.format("Список доступних команд:\n\n");
		System.out.format("add - додавання новогу клієнту\n");
		System.out.format("generate - додавання згенерованих клієнтів\n");
		System.out.format("read - зчитування бюро з файлу\n");
		System.out.format("remove - видалення клієнту\n");
		System.out.format("show - перегляд бюро\n");
		System.out.format("save - збереження бюро\n");
		System.out.format("exit - завершення програми\n");
	}
}