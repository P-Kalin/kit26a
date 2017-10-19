package ua.khpi.oop.pavlova05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
	public static Scanner scanner = new Scanner(System.in);

	public static void main(final String[] args) throws IOException {

		System.out.println("Лабораторна робота №5");
		System.out.print("Номер прикладної задачі: ");
		int number = (12 % 10) + 1;
		System.out.println(number);

		ContainerOfStrings container = new ContainerOfStrings();
		String text;
		StringBuilder stringBuilder = new StringBuilder();

		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
		while ((text = buf.readLine()) != null) {
			if (text.compareTo("end") == 0) {
				break;
			}
			stringBuilder.append(text);
		}
		text = stringBuilder.toString();

		Iterable<String> sentences = TextUtil.extractAllSentences(text);
		for (String sentence : sentences)
			container.add(sentence);

		ContainerOfStrings longest = (ContainerOfStrings) TextUtil.findLongestWordsInLines(sentences);
		ContainerOfStrings shortest = (ContainerOfStrings) TextUtil.findShortestWordsInLines(sentences);

		System.out.println("Список найдовших слів");
		ContainerOfStrings.IteratorForContainer<String> iteratorForContainerLongest = longest.iterator();
		while (iteratorForContainerLongest.hasNext()) {
			System.out.println(iteratorForContainerLongest.next());
		}
		System.out.println("Список найкоротших слів");
		for (String shortWord : shortest) {
			System.out.println(shortWord);
		}

	}
}