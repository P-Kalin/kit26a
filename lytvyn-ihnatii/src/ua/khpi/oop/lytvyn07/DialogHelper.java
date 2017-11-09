package ua.khpi.oop.lytvyn07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Забезпечує обробку команд користувача у вигляді інтерактивного діалогового
 * меню.
 * 
 * @author student Lytvyn I.I. KIT-26A
 *
 */
public class DialogHelper {

	/**
	 * Перелік команд
	 * 
	 * @author student Lytvyn I.I. KIT-26A
	 *
	 */
	public enum ACTION {
		add, exit, generate, remove, show
	}

	/**
	 * 
	 */
	static ArrayList<Client> bureau = new ArrayList<>();

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
		// br.close();
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
			bureau.add(ClientUtil.newClient(bureau.size()));
			break;
		case generate:
			System.out.println("Введіть кількість клієнтів.");
			final String bureauSize = getInput();
			bureau = new ArrayList<>(
			        ClientUtil.generateBureau(Integer.parseInt(bureauSize)));
			break;
		case remove:
			System.out.println("Введіть реєстраційний номер клієнта.");
			/* Пошук елементу в контейнері */
			final String remove = getInput();
			bureau.remove(Integer.parseInt(remove));
			break;
		case show:
			if (bureau.isEmpty()) {
				System.out.println("Контейнер порожній!");
			} else {
				/* Виведення речень */
				final Iterator<Client> iterator = bureau.iterator();
				System.out.println("\nПоточний вміст контейнеру:\n");
				while (iterator.hasNext()) {
					System.out.println(ClientUtil.info(iterator.next()));
				}
			}
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
	 * Реалізує інтерактивне діалогове меню для забезпечення отримання команд
	 * від користувача
	 */
	public static void start() {
		do {
			clean();
			comands();
			System.out.format("\nВведіть команду: ");
			sc = new Scanner(System.in);
			final ACTION cur = ACTION.valueOf(sc.next());
			try {
				handleAction(cur);
				Application.system("pause");
			} catch (final Exception ex) {
				System.out.println(ex.toString());
			}
		} while (!exit);
	}

	/**
	 * Очищення консолі
	 */
	private static void clean() {
		Application.system("cls");
	}

	/**
	 * Список доступних команд
	 */
	private static void comands() {
		System.out.format("Список доступних команд:\n\n");
		System.out.format("add - додавання нового клієнта\n");
		System.out.format("generate - додавання згенерованих клієнтів\n");
		System.out.format("remove - видалення клієнта\n");
		System.out.format("show - перегляд клієнтів\n");
		System.out.format("exit - завершення програми\n");
	}
}