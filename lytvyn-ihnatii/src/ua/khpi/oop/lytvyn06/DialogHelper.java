package ua.khpi.oop.lytvyn06;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * class DialogHelper - Утилітарний клас, що забезпечує обробку команд
 * користувача у вигляді інтерактивного діалогового меню.
 * 
 * @author student Lytvyn I.I. KIT-26A
 *
 */
public class DialogHelper {

	public static String LINE = "----------------------------------------------"
	        + "--------------------------\n";
	public static Scanner sc = new Scanner(System.in);
	public static boolean exit = false;
	static ArrayList<Integer> data; // Зберігає результат обчислень
	static StringСontainer sentences = new StringСontainer(); // Зберігає
	                                                          // речення

	/**
	 * Перелік команд
	 * 
	 * @author student Lytvyn I.I. KIT-26A
	 *
	 */
	public enum ACTION {
		input, calc, exit, read, result, save, search, sort, show, view
	}

	/**
	 * Реалізує інтерактивне діалогове меню для забезпечення отримання команд
	 * від користувача
	 */
	public static void start() {
		do {
			System.out.format("\n	Введіть команду: ");
			ACTION cur = ACTION.valueOf(sc.next());
			try {
				handleAction(cur);
			} catch (Exception ex) {
				System.out.println(ex.toString());
			}
		} while (!exit);
	}

	/**
	 * Виконує обробку введеної команди
	 * 
	 * @param action
	 *            введена команда
	 * @throws Exception
	 */
	public static void handleAction(ACTION action) throws Exception {
		switch (action) {
			case input:
				TextHelper.setText(InputHelper.getInput());
				/* Результат ризбиття тексту на окремі речення */
				sentences = new StringСontainer(TextHelper.getSentences());
				break;
			case read:
				/* Зчитування екземпляру контейнеру */
				sentences = new StringСontainer(deserialize());
				StringBuffer buffer = new StringBuffer();
				for (String string : sentences) {
					buffer.append(string);
				}
				TextHelper.setText(buffer.toString());
				break;
			case calc:
				if (sentences.isEmpty()) {
					System.out.println("	Контейнер порожній!");
				} else {
					/* Результат опрацювання речень */
					data = CountHelper.Count(sentences);
				}
				break;
			case sort:
				if (sentences.isEmpty()) {
					System.out.println(
					        "	Сортування неможливе контейнер порожній!");
				} else {
					/* Сортування контейнеру */
					sentences.sort(StringСontainer.COMPARATOR);
				}
				break;
			case search:
				if (sentences.isEmpty()) {
					System.out
					        .println("	Пошук неможливий контейнер порожній!");
				} else {
					/* Пошук елементу в контейнері */
					String find = InputHelper.getInput();
					int result = sentences.indexOf(find);
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
				ChartHelper.printChart(data);
				break;
			case save:
				/* Запис контейнеру */
				serialize(sentences);
				break;
			case show:
				if (sentences.isEmpty()) {
					System.out.println("	Контейнер порожній!");
				} else {
					/* Виведення речень */
					StringСontainer.ContainerIterator<String> iterator = sentences
					        .iterator();
					System.out.println("\n	Поточний вміст контейнеру:\n");
					while (iterator.hasNext()) {
						System.out.println("### " + iterator.next());
					}
				}
				break;
			case view:
				String text = TextHelper.getText();
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
	 * Виконує серіалізацію (збереження до файлу) отриманого контейнеру типу
	 * <tt>StringСontainer</tt>.
	 * 
	 * @param sentences
	 *            контейнер, що буде серіалізовано
	 */
	public static void serialize(StringСontainer sentences) {
		ObjectOutputStream out = null;
		try {
			/* Відкриваємо потік для запису */
			out = new ObjectOutputStream(
			        new BufferedOutputStream(new FileOutputStream("Data.ser")));
			/* Записуємо контейнер */
			out.writeObject(sentences);
			System.out.println("	Записано: " + sentences);
		} catch (IOException ex) {
			ex.printStackTrace();
			/* Обов'язково зачиняємо потік */
		} finally {
			if (out != null)
				try {
					out.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
		}
	}

	/**
	 * Виконує десеріалізацію (відновлення з файлу) контейнеру типу
	 * <tt>StringСontainer</tt>.
	 * 
	 * @return <tt>sentences</tt> контейнер, що було відновлено з файлу
	 */
	public static StringСontainer deserialize() {
		StringСontainer sentences = new StringСontainer();
		ObjectInputStream in = null;
		try {
			/* Відкриваємо потік для зчитування */
			in = new ObjectInputStream(
			        new BufferedInputStream(new FileInputStream("Data.ser")));
			/* Відновлюємо контейнер */
			sentences = new StringСontainer((StringСontainer) in.readObject());
			System.out.println("	Зчитано: " + sentences);
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
			/* Обов'язково зачиняємо потік */
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
		}
		return sentences;
	}
}