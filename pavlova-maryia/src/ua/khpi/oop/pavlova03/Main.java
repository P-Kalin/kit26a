package ua.khpi.oop.pavlova03;

import java.util.ArrayList;

public final class Main {
	private Main() {
	}

	public static void main(final String[] args) {
		System.out.println("Лабораторна робота №3");
		System.out.print("Номер прикладної задачі: ");
		int number = (12 % 10) + 1;
		System.out.println(number);

		String text = new String("When I do count the clock that tells the time,\r\n"
				+ "And see the brave day sunk in hideous night.\r\n" + "When I behold the violet past prime,\r\n"
				+ "And sable curls all silver'd o'er with white.\r\n" + "When lofty trees I see barren of leaves \r\n"
				+ "Which erst from heat did canopy the herd,\r\n" + "And summer's green all girded up in sheaves.\r\n"
				+ "Borne on the bier with white and bristly beard,\r\n" + "Then of thy beauty do I question make,\r\n"
				+ "That thou among the wastes of time must go,\r\n"
				+ "Since sweets and beauties do themselves forsake \r\n"
				+ "And die as fast as they see others grow.\r\n"
				+ "    And nothing 'gainst Time's scythe can make defence.");
		ArrayList<String> sentences = (ArrayList<String>) TextUtil.extractAllSentences(text);
		ArrayList<String> longest = (ArrayList<String>) TextUtil.findLongestWordsInLines(sentences);
		ArrayList<String> shortest = (ArrayList<String>) TextUtil.findShortestWordsInLines(sentences);
		for (int i = 0; i < longest.size(); i++) {
			System.out.format("%d\t%s\t%s\n", i, shortest.get(i), longest.get(i));
		}
	}
}