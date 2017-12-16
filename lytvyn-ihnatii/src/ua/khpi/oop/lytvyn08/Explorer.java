/**
 * 
 */
package ua.khpi.oop.lytvyn08;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import ua.khpi.oop.lytvyn.util.Console;

/**
 * 
 * 
 * @author student Lytvyn I.I. KIT-26A
 */
public class Explorer {

	/**
	 * Перелік команд
	 * 
	 * @author student Lytvyn I.I. KIT-26A
	 *
	 */
	public enum COMANDS {
		up, enter, choose
	}

	/**
	 * Сканування вводу
	 */
	public static Scanner sc = new Scanner(System.in);

	/**
	 * Успішне виконання збереження/відкриття файлу
	 */
	public static boolean isDone = false;

	/**
	 * Шлях
	 */
	public static String path = "";

	/**
	 * Шлях до файлу
	 */
	public static String filePath = "";

	/**
	 * Вибір каталогу
	 */
	public static void chooseDir() {
		try {
			System.out.format("\nОберіть каталог: ");
			final BufferedReader br = new BufferedReader(
			        new InputStreamReader(System.in));
			final String answer = br.readLine(); // Запис тексту до буферу
			setPath(answer);
		} catch (final IOException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * @param name
	 *            ім'я файлу
	 * @return <tt>true</tt> якщо створено файл
	 */
	public static boolean createFile(String name) {
		final File file = new File(name);
		return file.exists();
	}

	/**
	 * @return шлях за замовчуванням
	 */
	public static String defaultPath() {
		final File path = new File("default");
		String temp = path.getAbsolutePath();
		path.delete();
		temp = temp.substring(0, temp.lastIndexOf('\\'));
		return temp;
	}

	/**
	 * Перехід до підкаталогу
	 */
	public static void enter() {
		if (path.equals("")) {
			setRoot();
		} else {
			chooseDir();
		}
	}

	/**
	 * Виводить список директорій
	 */
	public static void getDirs() {
		final File dir = new File(path);
		if (dir.isDirectory()) {
			System.out.println(path + "\n");
			for (final File item : dir.listFiles()) {
				if (item.isDirectory()) {
					System.out.println("|" + item.getName());
				}
			}
		}
	}

	/**
	 * Виводить список файлів
	 */
	public static void getFiles() {
		final File file = new File(path);
		System.out.println(path + "\n");
		for (final File item : file.listFiles()) {
			if (item.isFile()) {
				System.out.println("|" + item.getName());
			}
		}
	}

	/**
	 * Виконує обробку введеної команди
	 * 
	 * @param comands
	 *            введена команда
	 * @throws Exception
	 *             будь-яка виникаюча виняткова ситуація
	 */
	public static void handleAction(COMANDS comands) throws Exception {
		switch (comands) {
		case up:
			up();
			getDirs();
			Console.pause();
			break;
		case enter:
			getDirs();
			enter();
			break;
		case choose:
			getDirs();
			chooseDir();
			isDone = true;
			break;
		}
	}

	/**
	 * 
	 * @return <tt>true</tt> якщо знайдено файл
	 */
	public static boolean open() {
		Console.clean();
		getFiles();
		System.out.format("\nВведіть назву файлу: ");
		String file = "";
		final BufferedReader br = new BufferedReader(
		        new InputStreamReader(System.in));
		try {
			file = br.readLine();
		} catch (final IOException e) {
			System.out.println(e.getMessage());
		}
		filePath = path + file;
		return true;
	}

	/**
	 * Виконує пошук шляху до файлу
	 * 
	 * @return шлях до файл
	 */
	public static String openFile() {
		if (!filePath.equals("")) {
			filePath = "";
		}
		try {
			do {
				Console.clean();
				comands();
				System.out.format("\nВведіть команду: ");
				sc = new Scanner(System.in);
				final COMANDS comand = COMANDS.valueOf(sc.next());
				handleAction(comand);
			} while (!isDone);
			isDone = false;
			if (!open()) {
				throw new FileNotFoundException();
			}
		} catch (final Exception exception) {
			exception.printStackTrace();
		}
		return filePath;
	}

	/**
	 * @param name
	 *            назва файлу
	 * @return <tt>true</tt> якщо створено файл
	 */
	public static boolean save(String name) {
		filePath = path + name + ".xml";
		return true;
	}

	/**
	 * Виконує збереження шляху до файлу
	 * 
	 * @return шлях до файлу
	 */
	public static String saveFile() {
		if (!filePath.equals("")) {
			filePath = "";
		}
		Console.clean();
		System.out.format("Введіть назву файлу: ");
		String file = "";
		final BufferedReader br = new BufferedReader(
		        new InputStreamReader(System.in));
		try {
			file = br.readLine();
			Console.pause();
			do {
				Console.clean();
				comands();
				System.out.format("\nВведіть команду: ");
				sc = new Scanner(System.in);
				final COMANDS comand = COMANDS.valueOf(sc.next());
				handleAction(comand);
			} while (!isDone);
			isDone = false;
		} catch (final Exception exception) {
			exception.printStackTrace();
		}
		save(file);
		return filePath;
	}

	/**
	 * @param newPath
	 *            the path to set
	 */
	public static void setPath(String newPath) {
		path = path + newPath + "\\";
	}

	/**
	 * Вибирає потрібний диск
	 */
	public static void setRoot() {
		final File[] roots = File.listRoots();
		System.out.println("Перелік дисків:");
		for (final File file : roots) {
			if (file.canWrite()) {
				String path = file.getAbsolutePath();
				path = path.substring(0, path.length() - 1);
				System.out.println("# " + path);
			}
		}
		System.out.format("\nОберіть диск: ");
		final BufferedReader br = new BufferedReader(
		        new InputStreamReader(System.in));
		try {
			final String path = br.readLine();
			setPath(path);
		} catch (final IOException e) {
			System.out.println(e.getMessage());
		} // Запис тексту до буферу
	}

	/**
	 * Список команд
	 */
	private static void comands() {
		System.out.format("Список доступних команд:\n\n");
		System.out.format("up - назад\n");
		System.out.format("enter - увійти\n");
		System.out.format("choose - вибрати\n");
	}

	/**
	 * Підняття вверх по ієрархії каталогів
	 */
	private static void up() {
		// определяем объект для каталога
		final File dir = new File(path);
		// если объект представляет каталог
		if (dir.isDirectory()) {
			path = dir.getParent() + "\\";
			System.out.println(path + "\n");
		}
	}
}
