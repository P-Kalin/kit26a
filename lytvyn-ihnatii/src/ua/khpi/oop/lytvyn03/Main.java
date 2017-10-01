package ua.khpi.oop.lytvyn03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * class Main Головний клас, відповідає за отримання тексту.
 * 
 * @author student Lytvyn I.I. KIT-26A
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		System.out.format("	Введіть будь-ласка текст(латинкою):\n\n");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String text = br.readLine(); // Запис тексту до буферу
		ChartHelper.printChart(text); // Результат опрацювання тексту
	}
}

/**
 * class TextHelper Утилітарний клас, що опрацьовує текст (розбиває його на
 * речення).
 * 
 * @author student Lytvyn I.I. KIT-26A
 *
 */
class TextHelper {
	private static final char DOT = '.'; // Крапка
	private static final char EXCLAMATION = '!'; // Знак оклику
	private static final char QUESTION = '?'; // Знак питання

	/* Розбиває отриманний текст на речення */
	public static ArrayList<String> getSentences(String text) {

		/* Список, що зберігає результат */
		ArrayList<String> result = new ArrayList<String>();
		String temp = ""; // Буфер
		for (int i = 0; i < text.length(); ++i) {
			char sign = text.charAt(i);
			if (sign == DOT || sign == EXCLAMATION || sign == QUESTION) {
				result.add(temp);
				temp = "";
			} else {
				temp += text.charAt(i);
			}
		}
		return result;
	}
}

/**
 * class StringHelper Утилітарний клас, що виконує пошук та підрахунок голосних
 * та приголосних у речені.
 * 
 * @author student Lytvyn I.I. KIT-26A
 *
 */
class StringHelper {
	/* Перелік голосних */
	private static final String VOWELS = "aeiouyAEIOUY";
	/* Перелік приголосних */
	private static final String CONSONANTS = "bcdfghjklmnpqrstvwxz" + "BCDFGHJKLMNPQRSTVWXZ";

	/* Перевіряє чи є символ голосною буквою */
	public static boolean isVowel(char ch) {

		return VOWELS.indexOf(ch) >= 0;
	}

	/* Перевіряє чи є символ приголосною буквою */
	public static boolean isConsonants(char ch) {

		return CONSONANTS.indexOf(ch) >= 0;
	}

	/* Підраховує голосні */
	public static int countVowel(String sentence) {

		int counter = 0;
		for (int i = 0; i < sentence.length(); ++i) {
			if (isVowel(sentence.charAt(i))) {
				++counter;
			}
		}
		return counter;
	}

	/* Підраховує приголосні */
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

/**
 * class CountHelper Утилітарний клас, що заповнює список данними для подальшого
 * опрацювання.
 * 
 * @author student Lytvyn I.I. KIT-26A
 *
 */
class CountHelper {
	/* Заносить кількість голосних та приголосних до списку */
	public static ArrayList<Integer> Count(String text) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		ArrayList<String> sentences = TextHelper.getSentences(text);
		for (int i = 0; i < sentences.size(); i++) {
			/* Кількість голосних */
			int vowels = StringHelper.countVowel(sentences.get(i));
			/* Кількість приголосних */
			int consonants = StringHelper.countConsonants(sentences.get(i));
			result.add(vowels);
			result.add(consonants);
		}
		return result;
	}
}

/**
 * class ChartHelper Утилітарний клас, що виконує виведення результатів.
 * 
 * @author student Lytvyn I.I. KIT-26A
 *
 */
class ChartHelper {
	/* Виводить дані у вигляді таблиці */
	public static void printChart(String text) {
		ArrayList<Integer> data = CountHelper.Count(text);
		int counter = 0;
		System.out.println("---------------------------------------------" 
											+ "---------------------------\n");
		System.out.format("	Реченя №	Голосних	Приголосних\n\n");
		for (int i = 0; i < data.size(); i += 2) {
			counter++;
			System.out.format("	%d		%d		%d\n", counter, data.get(i), data.get(i + 1));
		}
		System.out.println("\n--------------------------------------------" 
											+ "---------------------------\n");
	}
}