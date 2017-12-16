package ua.khpi.oop.lytvyn10;

/**
 * Відповідає за запуск роботи застосунку.
 * 
 * @author student Lytvyn I.I. KIT-26A
 */
public class Application {

	static boolean isAuto = false;

	static int size = 0;

	/**
	 * @param args
	 *            параметри запуску
	 */
	public static void main(String[] args) {
		for (final String comand : args) {
			if (comand.equals("-auto")) {
				isAuto = true;
			} else {
				size = Integer.parseInt(comand);
			}
		}
		DialogHelper.start(isAuto, size);
	}
}