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
 * class DialogHelper - Утилітарний клас, забезпечує обробку команд користувача
 * у вигляді інтерактивного текстового меню.
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
		input, read, view, sort, calc, result, save, exit
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
			case sort:
				/*
				 * if (sentences.isEmpty()) { System.out.println(
				 * "	Сортування неможливе контейнер порожній!"); } else {
				 * sentences.sort(null); }
				 */
				break;
			case read:
				/* Зчитування екземпляру контейнеру */
				sentences = new StringСontainer(deserialize());
				TextHelper.setText(sentences.toString());
				break;
			case view:
				System.out.format("\n%s\n", TextHelper.getText());
				break;
			case calc:
				/* Результат опрацювання речень */
				data = CountHelper.Count(sentences);
				break;
			case result:
				/* Виведення результату */
				ChartHelper.printChart(data);
				break;
			case save:
				/* Запис контейнеру */
				serialize(sentences);
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