package ua.khpi.oop.pavlova09;

import ua.khpi.oop.pavlova08.HotelGuest;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList<HotelGuest> cards = new LinkedList<>();
		HotelGuest def = new HotelGuest();
		cards.add(def);
		String check = cards.toString();
		System.out.println(check);
	}

}
