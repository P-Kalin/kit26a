package ua.khpi.oop.pavlova03;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author pavlova-mv
 * 
 *         TextUtil is an util class, that contains methods for input text
 *         transformations and solving the task of the laboratory work#3
 */
public final class TextUtil {
	/**
	 * @param WORDS_SEPARATORS
	 *            contains 'stop-symbols' for segmentation into words
	 * @param SENTENCES_SEPARATORS
	 *            contains 'stop-symbols' for segmentation into sentences
	 */
	public static final String WORDS_SEPARATORS = "`1234567890?/,. ";
	public static final String SENTENCES_SEPARATORS = ".?!";

	private TextUtil() {

	}

	/**
	 * Method for extracting all sentences from the text
	 * 
	 * @param text
	 *            - input data
	 * @return list of sentences
	 */
	public static Iterable<String> extractAllSentences(String text) {
		return extractElementsFromText(text, SENTENCES_SEPARATORS);
	}

	/**
	 * Method for searching the longest word in each line
	 * 
	 * @param lines
	 *            - list of sentences
	 * @return list of the longest words
	 */
	public static Iterable<String> findLongestWordsInLines(Iterable<String> lines) {
		List<String> longestWords = new ArrayList<String>();
		for (String sentence : lines) {
			longestWords.add(findLongestWordtInText(sentence));
		}
		return longestWords;
	}

	/**
	 * Method for searching the shortest word in each line
	 * 
	 * @param lines
	 *            - list of sentences
	 * @return list of the shortest words
	 */
	public static Iterable<String> findShortestWordsInLines(Iterable<String> lines) {
		List<String> shortestWords = new ArrayList<String>();
		for (String sentence : lines) {
			shortestWords.add(findShortestWordInText(sentence));
		}
		return shortestWords;
	}

	/**
	 * Method for seaching an element by a special token
	 * 
	 * @param text
	 *            - input data
	 * @param comparator
	 *            - token
	 * @return found word
	 */
	private static String findWordInText(String text, Comparator<String> comparator) {
		List<String> words = (List<String>) extractWordsFromText(text);
		Collections.sort(words, comparator);
		return words.get(0);
	}

	/**
	 * Method for searching the longest word in the text
	 * 
	 * @param text
	 *            - input data
	 * @return found word
	 */
	public static String findLongestWordtInText(String text) {
		return findWordInText(text, new Comparator<String>() {
			@Override
			public int compare(String leftWord, String rightWord) {
				if (leftWord.length() > rightWord.length()) {
					return -1;
				} else if (leftWord.length() < rightWord.length()) {
					return 1;
				}
				return 0;
			}
		});
	}

	/**
	 * Method for searching the shortest word in text
	 * 
	 * @param text
	 *            - input data
	 * @return found word
	 */
	public static String findShortestWordInText(String text) {
		return findWordInText(text, new Comparator<String>() {

			@Override
			public int compare(String leftWord, String rightWord) {
				if (leftWord.length() < rightWord.length()) {
					return -1;
				} else if (leftWord.length() > rightWord.length()) {
					return 1;
				}
				return 0;
			}
		});
	}

	/**
	 * Method for extracting elements from text
	 * 
	 * @param text
	 *            - input data
	 * @param separators
	 *            - special 'stop-symbols'
	 * @return extracted elements
	 */
	public static Iterable<String> extractElementsFromText(String text, String separators) {
		List<String> elements = new ArrayList<String>();

		StringTokenizer elementExtractor = new StringTokenizer(text, separators);
		while (elementExtractor.hasMoreTokens()) {
			elements.add(((String) elementExtractor.nextElement()).trim());
		}

		return elements;
	}

	/**
	 * Method for extracting words from text
	 * 
	 * @param text
	 *            - input data
	 * @return extracted words
	 */
	public static Iterable<String> extractWordsFromText(String text) {
		return extractElementsFromText(text, WORDS_SEPARATORS);
	}

	public static void print() {

	}
}