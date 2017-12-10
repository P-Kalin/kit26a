package ua.khpi.oop.pavlova15;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import ua.khpi.oop.pavlova10.HotelGuest;

public class Main {
	public static void main(final String[] args) throws FileNotFoundException, ClassNotFoundException, IOException {
		ArrayList<HotelGuest> arrayList = new ArrayList<>();
		CommandParser.doCommand(arrayList);
	}
}
