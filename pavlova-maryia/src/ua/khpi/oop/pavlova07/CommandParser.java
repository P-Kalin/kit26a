package ua.khpi.oop.pavlova07;

import ua.khpi.oop.pavlova06.NewContainerOfStrings;
import ua.khpi.oop.pavlova07.util.InPutUtil;

public class CommandParser {
	public static void doCommand(int command, NewContainerOfStrings containerOfStrings) {
		switch (command) {
		case 1:
			HotelGuest newGuest = InPutUtil.inputGuest();
			containerOfStrings.add(newGuest.toString());
			System.out.println("added");
			break;

		case 2:
			HotelGuest newGuest2 = new HotelGuest();
			containerOfStrings.add(newGuest2.toString());
			System.out.println("added");
			break;

		case 3:
			for (String guest : containerOfStrings) {
				if (guest != null)
					System.out.println(guest);
			}

			break;
		case 4:
			break;
		}
	}
}
