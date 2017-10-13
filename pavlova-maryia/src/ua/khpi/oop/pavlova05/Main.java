package ua.khpi.oop.pavlova05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
	public static Scanner scanner = new Scanner(System.in);

	public static void main(final String[] args) throws IOException {
		int i;
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
		ContainerOfStrings longest = new ContainerOfStrings();
		ContainerOfStrings shortest = new ContainerOfStrings();

		for (String sentence : sentences) {
			longest.add(TextUtil.findLongestWordtInText(sentence));
			shortest.add(TextUtil.findShortestWordInText(sentence));
		}
		for (i = 0; i < longest.size(); i++) {
			System.out.println(shortest.get(i) + "\t\t" + longest.get(i));
		}

	}
}