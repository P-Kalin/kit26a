package ua.khpi.oop.lytvyn06;

import java.util.Comparator;

/**
 * Компаратор, що впорядковує {@code String} об'єкти лексикографічно, ігноруючи
 * випадкові відмінності.
 *
 * @author student Lytvyn I.I. KIT-26A
 *
 */
class StringComparator implements Comparator<String> {
	/**
	 * Порівнює два аргументи для впорядкування. Повертає від'ємне ціле число,
	 * нульове значення або позитивне ціле число, якщо перший аргумент менше,
	 * дорівнює, або більше, ніж другий.
	 *
	 * @param first
	 *            перший рядок, що потрібно порівняти
	 * @param second
	 *            другий рядок, що потрібно порівняти
	 * @return від'ємне ціле число, нульове значення або позитивне ціле число,
	 *         якщо перший аргумент менше, дорівнює, або більше, ніж другий
	 */
	@Override
	public int compare(String first, String second) {
		final int firstLength = first.length();
		final int secondLength = second.length();
		final int min = Math.min(firstLength, secondLength);
		for (int i = 0; i < min; i++) {
			char firstChar = first.charAt(i);
			char secondChar = second.charAt(i);
			if (firstChar != secondChar) {
				firstChar = Character.toUpperCase(firstChar);
				secondChar = Character.toUpperCase(secondChar);
				if (firstChar != secondChar) {
					firstChar = Character.toLowerCase(firstChar);
					secondChar = Character.toLowerCase(secondChar);
					if (firstChar != secondChar) {
						return firstChar - secondChar;
					}
				}
			}
		}
		return firstLength - secondLength;
	}
}