package ua.khpi.oop.lytvyn05;

/**
 * class StringHelper Утилітарний клас, що виконує пошук та підрахунок голосних
 * та приголосних у речені.
 * 
 * @author student Lytvyn I.I. KIT-26A
 */
class StringHelper {
	/**
	 * Перелік голосних
	 */
	private static final String VOWELS = "aeiouyAEIOUY";
	/**
	 * Перелік приголосних
	 */
	private static final String CONSONANTS = "bcdfghjklmnpqrstvwxz"
	        + "BCDFGHJKLMNPQRSTVWXZ";

	/**
	 * Перевіряє чи є символ голосною
	 * 
	 * @param ch
	 *            символ для перевірки
	 * @return <tt>true</tt> якщо цей символ є голосною
	 */
	public static boolean isVowel(char ch) {

		return VOWELS.indexOf(ch) >= 0;
	}

	/**
	 * Перевіряє чи є символ приголосною
	 * 
	 * @param ch
	 *            символ для перевірки
	 * @return <tt>true</tt> якщо цей символ є приголосною
	 */
	public static boolean isConsonants(char ch) {

		return CONSONANTS.indexOf(ch) >= 0;
	}

	/**
	 * Підраховує голосні у речені
	 * 
	 * @param sentence
	 *            речення для опрацювання
	 * @return counter кількість голосних у речені
	 */
	public static int countVowel(String sentence) {

		int counter = 0;
		for (int i = 0; i < sentence.length(); ++i) {
			if (isVowel(sentence.charAt(i))) {
				++counter;
			}
		}
		return counter;
	}

	/**
	 * Підраховує приголосні у речені
	 * 
	 * @param sentence
	 *            речення для опрацювання
	 * @return counter кількість приголосних у речені
	 */
	public static int countConsonants(String sentence) {

		int counter = 0;
		for (int i = 0; i < sentence.length(); ++i) {
			if (isConsonants(sentence.charAt(i))) {
				++counter;
			}
		}
		return counter;
	}
}