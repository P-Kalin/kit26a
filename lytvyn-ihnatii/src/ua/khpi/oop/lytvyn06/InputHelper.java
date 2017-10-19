package ua.khpi.oop.lytvyn06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Отримує текст та введені дані від користувача
 * 
 * @author student Lytvyn I.I. KIT-26A
 */
class InputHelper {

	/**
	 * Отримує текст для опрацювання
	 * 
	 * @return text введений текст латинкою
	 * @throws IOException
	 *             виняткова ситуація при роботі з вводом
	 */
	public static String getInput() throws IOException {
		System.out.format("	Введіть будь-ласка текст(латинкою):\n\n");
		BufferedReader br = new BufferedReader(
		        new InputStreamReader(System.in));
		String text = br.readLine(); // Запис тексту до буферу
		// br.close();
		return text;
	}

	/**
	 * Отримує відповідь від користувача
	 * 
	 * @return answer відповідь від користувача
	 * @throws IOException
	 *             виняткова ситуація при роботі з вводом
	 */
	public static String getAnswer() throws IOException {
		System.out.format("\n\tВаша відповідь: ");
		BufferedReader br = new BufferedReader(
		        new InputStreamReader(System.in));
		String answer = br.readLine(); // Запис тексту до буферу
		return answer;
	}
}