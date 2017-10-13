package ua.khpi.oop.pavlova04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public final class CommandParser {
	public static boolean exit = false;
	public static String text;
	private static ArrayList<String> longest;
	private static ArrayList<String> shortest;

	private CommandParser() {

	}

	public static void doActionByCommand(int command) throws IOException {
		switch (command) {
		case 1:
			System.out.println("1. input");
			System.out.println("Введіть текст латинкою:");
			StringBuilder stringBuilder = new StringBuilder();

			BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
			while ((text = buf.readLine()) != null) {
				if (text.compareTo("end") == 0) {
					break;
				}
				stringBuilder.append(text);
			}
			text = stringBuilder.toString();
			if (ExtraOptions.debugParam == true)
				System.out.println("~dbg.Створено буфер.\n    Зчитано текст.");
			break;
		case 2:
			System.out.println("2. data");
			System.out.println(text);
			if (ExtraOptions.debugParam == true)
				System.out.println("~dbg.Виконано операцію виведення на екран.");
			break;
		case 3:
			System.out.println("3. calculate");
			assert text == null;
			ArrayList<String> sentences = (ArrayList<String>) TextUtil.extractAllSentences(text);
			longest = (ArrayList<String>) TextUtil.findLongestWordsInLines(sentences);
			shortest = (ArrayList<String>) TextUtil.findShortestWordsInLines(sentences);

			/**
			 * for (String element : TextUtil.extractAllSentences(text)) { if
			 * (ExtraOptions.debugParam == true) System.out.println("~dbg.Ведеться пошук
			 * найдовших слів у реченнях."); String temp =
			 * TextUtil.findLongestWordtInText(element); longest.add(temp); if
			 * (ExtraOptions.debugParam == true) System.out.println("~dbg.Ведеться пошук
			 * найкоротших слів у реченнях.");
			 * shortest.add(TextUtil.findShortestWordInText(element));
			 */
			break;
		case 4:
			System.out.println("4. result");
			if (ExtraOptions.debugParam == true)
				System.out.println("~dbg.Буде виведено усі результати обчислень.");
			for (int i = 0; i < longest.size(); i++) {
				System.out.format("%d\t%s\t%s\n", i, shortest.get(i), longest.get(i));
			}
			break;
		case 5:
			System.out.println("5. exit");
			if (ExtraOptions.debugParam == true)
				System.out.println("~dbg.Вихід з програми.");
			exit = true;
			break;
		default:
			System.out.println("Некоректне введення коду команди!");
			break;
		}

	}
}
