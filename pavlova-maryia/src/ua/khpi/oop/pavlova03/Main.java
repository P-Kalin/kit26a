package ua.khpi.oop.pavlova03;

import java.util.ArrayList;

import ua.khpi.oop.pavlova04.TextUtil;

public class Main {
	private Main() {
	}

	public static void main(final String[] args) {
		System.out.println("Лабораторна робота №3");
		System.out.print("Номер прикладної задачі: ");
		int number = (12 % 15) + 1;
		System.out.println(number);
		String text = "When I do count the clocks that tells the time." + "And see the brave day sunk in hideous night."
				+ "When I behold the violet past prime. " + "And sable curls all silver'd o'er with white."
				+ "When lofty trees I see barren of leaves." + " Which erst from heat did canopy the herd."
				+ "And summer's green all girded up in sheaves." + " Borne on the bier with white and bristly beard."
				+ "Then of thy beauty do I question make. " + "That thou among the wastes of time must go."
				+ "Since sweets and beauties do themselves forsake. " + "And die as fast as they see others grow."
				+ "And nothing 'gainst Time's scythe can make defence. "
				+ "Save breed, to brave him when he takes thee hence.";
		System.out.println("Sent# \tThe shortest word \tThe longest word");
		ArrayList<String> sentences = (ArrayList<String>) TextUtil.extractAllSentences(text);
		ArrayList<String> longest = (ArrayList<String>) TextUtil.findLongestWordsInLines(sentences);
		ArrayList<String> shortest = (ArrayList<String>) TextUtil.findShortestWordsInLines(sentences);
		for (int i = 0; i < sentences.size(); i++)
			System.out.println(i + "\t" + shortest.get(i) + "\t\t\t" + longest.get(i));

	}
}
