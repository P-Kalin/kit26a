package ua.khpi.oop.lytvyn06;

import java.util.ArrayList;

/**
 * Заповнює список данними для подальшого опрацювання.
 * 
 * @author student Lytvyn I.I. KIT-26A
 */
class CountHelper {
	/**
	 * Виконує підрахунок кількості голосних та приголосних у кожному речені з
	 * отриманного тексту.
	 * 
	 * @param sentences
	 *            реення для опрацювання
	 * @return result результат обчислень кількості голосних та приголосних у
	 *         кожному речені.
	 */
	public static ArrayList<Integer> Count(StringContainer sentences) {
		/* Для збереження результату підрахунку */
		ArrayList<Integer> result = new ArrayList<Integer>();
		/* Виведення знайдених речень */
		StringContainer.ContainerIterator<String> iterator = sentences
		        .iterator();
		System.out.println("\nБуло знайдено і опрацьовано наступні речення:\n");
		while (iterator.hasNext()) {
			System.out.println("### " + iterator.next());
		}
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