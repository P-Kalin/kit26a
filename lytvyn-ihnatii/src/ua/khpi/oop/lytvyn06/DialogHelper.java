package ua.khpi.oop.lytvyn06;

import java.util.ArrayList;
import java.util.Scanner;

import ua.khpi.oop.lytvyn03.TextEditHelper;

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
		input, calc, calc_all, exit, read, result, save, search, sort, show, view
	}
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
	 * Режим виведення результату
	 */
	public static int workMode = 0;
	/**
	 * Кількість приголосних
	 */
	public static int consonants = 0;
	/**
	 * Кількість голосних
	 */
	public static int vowels = 0;
	/**
	 * Буфер, що зберігає текст
	 */
	private static String text = "";
	/**
	 * Буфер, що зберігає результат обробки тексту
	 */
	private static String result = "";
	/**
	 * Буфер, що зберігає результат обчислень
	 */
	static ArrayList<Integer> data;

	/**
	 * Буфер, що зберігає речення
	 */
	static StringContainer sentences = new StringContainer();

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
		case input:
			text = InputHelper.getInput();
			TextHelper.setText(text);
			/* Результат ризбиття тексту на окремі речення */
			sentences = new StringContainer(TextHelper.getSentences());
			break;
		case read:
			/* Зчитування екземпляру контейнеру */
			sentences = new StringContainer(SerializationUtil.deserialize());
			final StringBuffer buffer = new StringBuffer();
			for (final String string : sentences) {
				buffer.append(string);
			}
			text = buffer.toString();
			TextHelper.setText(text);
			break;
		case calc:
			System.out.println("\n\tВведіть номер речення.");
			final int number = Integer.parseInt(InputHelper.getAnswer());
			final String sentence = sentences.get(number);
			System.out.println("\n\tВаше речення:\n\n" + sentence);
			System.out.println("\n\n\tВиберіть один з варіантів опрацювання"
			        + " тексту:\n");
			System.out.println("\t1. Пошук та підрахунок кількості голосних та"
			        + " приголосних у речені.");
			System.out.println("\t2. Видалення з речення слів заданої довжини,"
			        + " що починаються з приголосної.");
			final int variant = Integer.parseInt(InputHelper.getAnswer());
			if (variant == 1) {
				/* Результат опрацювання речень */
				consonants = StringHelper
				        .countConsonants(sentence);
				vowels = StringHelper.countVowel(sentence);
				workMode = 1;
			} else if (variant == 2) {
				System.out.println("\n\tВведіть розмір слова.");
				final int size = Integer.parseInt(InputHelper.getAnswer());
				result = TextEditHelper.deleteWords(sentence, size);
				workMode = 3;
			} else {
				System.out.println("\tНевірний варіант!");
			}
			break;
		case calc_all:
			System.out.println("\n\tВиберіть один з варіантів опрацювання"
			        + " тексту:\n");
			System.out.println("\t1. Пошук та підрахунок кількості голосних та"
			        + " приголосних у кожному речені.");
			System.out.println("\t2. Видалення з тексту слів заданої довжини,"
			        + " що починаються з приголосної.");
			final int choice = Integer.parseInt(InputHelper.getAnswer());
			if (choice == 1) {
				if (sentences.isEmpty()) {
					System.out.println("	Контейнер порожній!");
				} else {
					/* Результат опрацювання речень */
					data = CountHelper.Count(sentences);
					workMode = 2;
				}
			} else if (choice == 2) {
				System.out.println("\n\tВведіть розмір слова.");
				final int size = Integer.parseInt(InputHelper.getAnswer());
				result = TextEditHelper.edit(text, size);
				workMode = 3;
			} else {
				System.out.println("\tНевірний варіант!");
			}
			break;
		case sort:
			if (sentences.isEmpty()) {
				System.out.println(
				        "	Сортування неможливе контейнер порожній!");
			} else {
				/* Сортування контейнеру */
				sentences.sort(StringContainer.COMPARATOR);
			}
			break;
		case search:
			if (sentences.isEmpty()) {
				System.out
				        .println("	Пошук неможливий контейнер порожній!");
			} else {
				/* Пошук елементу в контейнері */
				final String find = InputHelper.getInput();
				final int result = sentences.indexOf(find);
				if (result >= 0) {
					System.out.println("\n	Елемент:\n\n\"" + find
					        + "\"\n\n	Знайдено під індексом [" + result
					        + "].");
				} else {
					System.out.println("\n	Елемент не знайдено!");
				}
			}
			break;
		case result:
			/* Виведення результату */
			if (workMode == 1) {
				System.out.format("\n\tУ речені було знайдено:\n\n\tголосних: "
				        + "%d\n" + "\tприголосних: %d\n", vowels, consonants);
			} else if (workMode == 2) {
				ChartHelper.printChart(data);
			} else if (workMode == 3) {
				System.out.println("\n" + result);
			}
			break;
		case save:
			/* Запис контейнеру */
			SerializationUtil.serialize(sentences);
			break;
		case show:
			if (sentences.isEmpty()) {
				System.out.println("	Контейнер порожній!");
			} else {
				/* Виведення речень */
				final StringContainer.ContainerIterator<String> iterator = sentences
				        .iterator();
				System.out.println("\n	Поточний вміст контейнеру:\n");
				while (iterator.hasNext()) {
					System.out.println("### " + iterator.next());
				}
			}
			break;
		case view:
			final String text = TextHelper.getText();
			if (text != null) {
				System.out.format("\n%s\n", text);
			} else {
				System.out.println("	Текст порожній!");
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
			System.out.format("\n	Введіть команду: ");
			final ACTION cur = ACTION.valueOf(sc.next());
			try {
				handleAction(cur);
			} catch (final Exception ex) {
				System.out.println(ex.toString());
			}
		} while (!exit);
	}
}