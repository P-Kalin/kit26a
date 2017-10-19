package ua.khpi.oop.lytvyn06;

/**
 * Опрацьовує текст (розбиває його на речення).
 * 
 * @author student Lytvyn I.I. KIT-26A
 */
class TextHelper {
	/**
	 * Символ "Крапка"
	 */
	private static final char DOT = '.';
	/**
	 * Символ "Знак оклику"
	 */
	private static final char EXCLAMATION = '!';
	/**
	 * Символ "Знак питання"
	 */
	private static final char QUESTION = '?';
	/**
	 * Буфер, що зберігає текст
	 */
	private static String text;

	/**
	 * Розбиває отриманний текст на речення
	 * 
	 * @return result контейнер, що зберігає відокремлені речення
	 */
	public static StringContainer getSentences() {
		/* Список, що зберігає результат */
		StringContainer result = new StringContainer();
		String temp = ""; // Буфер
		int cnt = 0;
		for (int i = 0; i < text.length(); ++i) {
			char sign = text.charAt(i);
			cnt = i + 1;
			if (sign == DOT) {
				if (cnt < text.length() && text.charAt(i + 1) == ' ') {
					i++;
				}
				result.add(temp + DOT);
				temp = "";
			} else if (sign == EXCLAMATION) {
				if (cnt < text.length() && text.charAt(i + 1) == ' ') {
					i++;
				}
				result.add(temp + EXCLAMATION);
				temp = "";
			} else if (sign == QUESTION) {
				if (cnt < text.length() && text.charAt(i + 1) == ' ') {
					i++;
				}
				result.add(temp + QUESTION);
				temp = "";
			} else {
				temp += text.charAt(i);
			}
		}
		return result;
	}

	/**
	 * Гетер, що повертає значення змінної text
	 * 
	 * @return значення змінної text
	 */
	public static String getText() {
		return text;
	}

	/**
	 * Сетер, що встановлює значення змінної text
	 * 
	 * @param data
	 *            дані для задання значення змінної text
	 */
	public static void setText(String data) {
		text = data;
	}
}