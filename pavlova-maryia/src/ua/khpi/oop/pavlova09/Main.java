package ua.khpi.oop.pavlova09;

import java.io.FileNotFoundException;

import ua.khpi.oop.pavlova08.HotelGuest;
import ua.khpi.oop.pavlova09.util.SerializeUtil;
import ua.khpi.oop.pavlova09.util.XMLUtil;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		LinkedList<HotelGuest> cards = new LinkedList<>();
		CommandParser.doCommand(cards);
		SerializeUtil.serialize(cards);
		XMLUtil.write("Beanarchieve.xml", cards);
	}

}
