package ua.khpi.oop.lytvyn06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * class InputHelper Утилітарний клас, що отримує текст
 * 
 * @author student Lytvyn I.I. KIT-26A
 */
class InputHelper {

	/**
	 * Отримує текст для опрацювання
	 * 
	 * @return text введений текст латинкою
	 * @throws IOException
	 */
	public static String getInput() throws IOException {
		System.out.format("	Введіть будь-ласка текст(латинкою):\n\n");
		BufferedReader br = new BufferedReader(
		        new InputStreamReader(System.in));
		String text = br.readLine(); // Запис тексту до буферу
		// br.close();
		return text;
	}
}