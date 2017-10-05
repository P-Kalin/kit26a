package ua.khpi.oop.lytvyn05;

import java.util.ArrayList;

/**
 * class CountHelper Утилітарний клас, що заповнює список данними для подальшого
 * опрацювання.
 * 
 * @author student Lytvyn I.I. KIT-26A
 */
class CountHelper {
	/**
	 * Виконує підрахунок кількості голосних та приголосних у кожному речені з
	 * отриманного тексту.
	 * 
	 * @param text
	 *            текст для опрацювання
	 * @return result результат обчислень кількості голосних та приголосних у
	 *         кожному речені.
	 */
	public static ArrayList<Integer> Count(StringСontainer sentences) {
		/* Для збереження результату підрахунку */
		ArrayList<Integer> result = new ArrayList<Integer>();
		/* Виведення знайдених речень */
		StringСontainer.ContainerIterator<String> iterator = sentences
		        .iterator();
		System.out.println("\nБуло знайдено наступні речення:\n");
		while (iterator.hasNext()) {
			System.out.println("### " + iterator.next());
		}
		System.out.println("\n");
		for (String string : sentences) {
			/* Кількість голосних */
			int vowels = StringHelper.countVowel(string);
			/* Кількість приголосних */
			int consonants = StringHelper.countConsonants(string);
			result.add(vowels);
			result.add(consonants);
		}
		return result;
	}
}