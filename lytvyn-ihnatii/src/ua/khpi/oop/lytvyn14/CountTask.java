package ua.khpi.oop.lytvyn14;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

/**
 * 
 * 
 * @author student Lytvyn I.I. KIT-26A
 */
public class CountTask {
	/**
	 * обчислення середнього значення
	 */
	public static void countAvrAge(LinkedList<Client> list) {
		final ArrayList<Integer> result = new ArrayList<>();

		final Date today = new Date();
		final SimpleDateFormat ft = new SimpleDateFormat("yyyy");
		String temp = ft.format(today);
		final int current = Integer.parseInt(temp);

		for (final Client client : list) {
			temp = client.getBirthday();
			final int year = Integer
			        .parseInt(temp.substring(temp.lastIndexOf('.') + 1));
			result.add(current - year);
		}

		long sum = 0;

		for (final Integer integer : result) {
			sum += integer;
		}
		System.out.format("Середній вік серед клієнтів: %d\n",
		        sum / result.size());
	}

	/**
	 * пошук максимуму
	 * 
	 * @param list
	 * @return
	 */

	public static void countMaxAge(LinkedList<Client> list) {
		final Date today = new Date();
		final SimpleDateFormat ft = new SimpleDateFormat("yyyy");
		String temp = ft.format(today);
		final int current = Integer.parseInt(temp);

		final String first = list.getFirst().getBirthday();
		int result = Integer
		        .parseInt(first.substring(first.lastIndexOf('.') + 1));

		for (final Client client : list) {
			temp = client.getBirthday();
			final int year = Integer
			        .parseInt(temp.substring(temp.lastIndexOf('.') + 1));
			if (result > year) {
				result = year;
			}
		}
		System.out.format("Максимальний вік серед клієнтів: %d\n",
		        current - result);
	}

	/**
	 * пошук мінімуму;
	 * 
	 * @param list
	 * @return
	 */

	public static void countMinAge(LinkedList<Client> list) {
		final Date today = new Date();
		final SimpleDateFormat ft = new SimpleDateFormat("yyyy");
		String temp = ft.format(today);
		final int current = Integer.parseInt(temp);

		final String first = list.getFirst().getBirthday();
		int result = Integer
		        .parseInt(first.substring(first.lastIndexOf('.') + 1));

		for (final Client client : list) {
			temp = client.getBirthday();
			final int year = Integer
			        .parseInt(temp.substring(temp.lastIndexOf('.') + 1));
			if (result < year) {
				result = year;
			}
		}
		System.out.format("Мінімальний вік серед клієнтів: %d\n",
		        current - result);
	}

}
