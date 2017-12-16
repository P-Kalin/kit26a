package ua.khpi.oop.lytvyn14;

import java.util.Comparator;

/**
 * 
 * 
 * @author student Lytvyn I.I. KIT-26A
 */
public interface ClientComparator extends Comparator<Client> {
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Client first, Client second);
}
