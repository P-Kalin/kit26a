package ua.khpi.oop.lytvyn07;

/**
 * Відповідає за запуск роботи застосунку.
 * 
 * @author student Lytvyn I.I. KIT-26A
 */
public class Application {

	/**
	 * Завантаження бібліотеки
	 */
	static {
		System.loadLibrary("SystemUtil");
	}

	/**
	 * @param args
	 *            параметри запуску
	 */
	public static void main(String[] args) {
		DialogHelper.start();
	}

	/**
	 * Виконує консольні команди
	 * 
	 * @param str
	 *            команда
	 */
	public static native void system(String str);

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
}