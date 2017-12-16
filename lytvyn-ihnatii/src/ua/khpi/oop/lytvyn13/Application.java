package ua.khpi.oop.lytvyn13;

/**
 * Відповідає за запуск роботи застосунку.
 * 
 * @author student Lytvyn I.I. KIT-26A
 */
public class Application {

	static int mode = 0;

	static int size = 0;

	/**
	 * @param args
	 *            параметри запуску
	 */
	public static void main(String[] args) {
		for (final String comand : args) {
			if (comand.equals("-auto")) {
				mode = 1;
			} else if (comand.equals("-generate")) {
				mode = 2;
			} else {
				size = Integer.parseInt(comand);
			}
		}
		DialogHelper.start(mode, size);
	}
}