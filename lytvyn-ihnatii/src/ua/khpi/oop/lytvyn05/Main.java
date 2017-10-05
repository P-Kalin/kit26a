package ua.khpi.oop.lytvyn05;

import java.util.ArrayList;

/**
 * class Main Головний клас, відповідає за отримання тексту.
 * 
 * @author student Lytvyn I.I. KIT-26A
 */
public class Main {

	public static void main(String[] args) throws Exception {
		/* Результат введення тексту */
		String text = InputHelper.getInput();
		/* Результат ризбиття тексту на окремі речення */
		StringСontainer sentences = TextHelper.getSentences(text);
		/* Результат опрацювання речень */
		ArrayList<Integer> data = CountHelper.Count(sentences);
		/* Виведення результату */
		ChartHelper.printChart(data);
	}
}