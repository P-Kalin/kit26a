package ua.khpi.oop.lytvyn15;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * 
 * @author student Lytvyn I.I. KIT-26A
 */
public class DateClientComparator implements ClientComparator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see ua.khpi.oop.lytvyn09.ClientComparator#compare(ua.khpi.oop.lytvyn09.
	 * Client, ua.khpi.oop.lytvyn09.Client)
	 */
	@Override
	public int compare(Client first, Client second) {
		final SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy");
		Date left = new Date();
		Date right = new Date();
		try {
			left = ft.parse(first.getRegDate());
			right = ft.parse(second.getRegDate());

		} catch (final ParseException e) {
			e.printStackTrace();
		}
		return left.before(right) ? 1 : -1;
	}
}
