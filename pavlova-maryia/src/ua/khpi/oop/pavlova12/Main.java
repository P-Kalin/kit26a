package ua.khpi.oop.pavlova12;

import ua.khpi.oop.pavlova10.HotelGuest;
import ua.khpi.oop.pavlova10.LinkedList;

public class Main {

	public static void main(String[] args) {
		HotelGuest one = new HotelGuest();
		HotelGuest two = new HotelGuest();
		HotelGuest three = new HotelGuest();

		two.setReasonOfArrival("Командировка");
		one.setGuestNameSurname("Иванов А.Р.");
		two.setDateOfArrival("18-Июнь-2001");
		;
		LinkedList<HotelGuest> list = new LinkedList<>();
		list.add(one);
		list.add(two);
		list.add(three);

		CommandParser.doCommand(list);
	}

}
