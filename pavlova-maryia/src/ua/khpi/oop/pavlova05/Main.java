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
		NewContainerOfStrings containerOfStrings = new NewContainerOfStrings();
		Iterable<String> sentences = TextUtil.extractAllSentences(text);
		for (String sentence : sentences)
			containerOfStrings.add(sentence);
		NewContainerOfStrings longest = new NewContainerOfStrings(containerOfStrings.size());
		NewContainerOfStrings shortest = new NewContainerOfStrings(containerOfStrings.size());
		for (String string : containerOfStrings.elementData) {
			longest.add(TextUtil.findLongestWordtInText(string));
			shortest.add(TextUtil.findShortestWordInText(string));
		}
		NewContainerOfStrings.newIterator<String> iteratorForContainerLongest = longest.iterator();
		while (iteratorForContainerLongest.hasNext()) {
			System.out.println(iteratorForContainerLongest.next());
		}
		NewContainerOfStrings.newIterator<String> iteratorForContainerShortest = shortest.iterator();
		while (iteratorForContainerShortest.hasNext()) {
			System.out.println(iteratorForContainerShortest.next());
		}
	}
}