package ua.khpi.oop.lytvyn.util;

/**
 * Забезпечує підтримку консольних команд
 * 
 * @author student Lytvyn I.I. KIT-26A
 */
public class Console {
	/**
	 * Завантаження бібліотеки
	 */
	static {
		System.loadLibrary("SystemUtil");
	}

	/**
	 * Очищення консолі
	 */
	public static void clean() {
		system("cls");
	}

	/**
	 * Призупинення виводу консолі
	 */
	public static void pause() {
		system("pause");
	}

	/**
	 * Виконує консольні команди
	 * 
	 * @param str
	 *            команда
	 */
	public static native void system(String str);
}
