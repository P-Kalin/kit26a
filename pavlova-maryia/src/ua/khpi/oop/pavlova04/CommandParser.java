package ua.khpi.oop.pavlova04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public final class CommandParser {
	public static boolean exit = false;
	public static String text;
	private static List<String> longest;
	private static List<String> shortest;

	private CommandParser() {

	}

	public static void doActionByCommand(int command) throws IOException {
		switch (command) {
		case 1:
			System.out.println("1. input");
			System.out.println("Введіть текст латинкою:");

			BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
			text = buf.readLine();
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
			for (String element : TextUtil.extractAllSentences(text)) {
				if (ExtraOptions.debugParam == true)
					System.out.println("~dbg.Ведеться пошук найдовших слів у реченнях.");
				longest.add(TextUtil.findLongestWordtInText(element));
				if (ExtraOptions.debugParam == true)
					System.out.println("~dbg.Ведеться пошук найкоротших слів у реченнях.");
				shortest.add(TextUtil.findShortestWordInText(element));
			}
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