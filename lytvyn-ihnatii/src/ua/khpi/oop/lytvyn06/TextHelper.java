package ua.khpi.oop.lytvyn06;

/**
 * class TextHelper Утилітарний клас, що опрацьовує текст (розбиває його на
 * речення).
 * 
 * @author student Lytvyn I.I. KIT-26A
 */
class TextHelper {
	private static final char DOT = '.'; // Крапка
	private static final char EXCLAMATION = '!'; // Знак оклику
	private static final char QUESTION = '?'; // Знак питання
	private static String text;

	/**
	 * Розбиває отриманний текст на речення
	 * 
	 * @param text
	 *            текст для опрацювання
	 * @return result контейнер, що зберігає відокремлені речення
	 */
	public static StringСontainer getSentences() {
		/* Список, що зберігає результат */
		StringСontainer result = new StringСontainer();
		String temp = ""; // Буфер
		for (int i = 0; i < text.length(); ++i) {
			char sign = text.charAt(i);
			if (sign == DOT) {
				result.add(temp + DOT);
				temp = "";
			} else if (sign == EXCLAMATION) {
				result.add(temp + EXCLAMATION);
				temp = "";
			} else if (sign == QUESTION) {
				result.add(temp + QUESTION);
				temp = "";
			} else {
				temp += text.charAt(i);
			}
		}
		return result;
	}

	public static String getText() {
		return text;
	}

	public static void setText(String data) {
		text = data;
	}
}