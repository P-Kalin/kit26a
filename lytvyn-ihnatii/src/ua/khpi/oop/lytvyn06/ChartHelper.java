package ua.khpi.oop.lytvyn06;

import java.util.ArrayList;

/**
 * class ChartHelper Утилітарний клас, що виконує виведення результатів.
 * 
 * @author student Lytvyn I.I. KIT-26A
 */
class ChartHelper {
	/**
	 * Виводить результат опрацювання тексту у вигляді таблиці
	 * 
	 * @param text
	 *            результати підрахунку голосних і приголосних
	 */
	public static void printChart(ArrayList<Integer> data) {
		int counter = 0; // Лічильник номеру речень
		System.out.println("---------------------------------------------"
		        + "---------------------------\n");
		/* Кількість голосних */
		int vowels;
		/* Кількість приголосних */
		int consonants;
		System.out.format("	Реченя №	Голосних	Приголосних\n\n");
		for (int i = 0; i < data.size(); i += 2) {
			counter++;
			vowels = data.get(i);
			consonants = data.get(i + 1);
			System.out.format("	%d" + "		%d" + "		%d\n", counter, vowels,
			        consonants);
		}
		System.out.println("\n--------------------------------------------"
		        + "---------------------------\n");
	}
}