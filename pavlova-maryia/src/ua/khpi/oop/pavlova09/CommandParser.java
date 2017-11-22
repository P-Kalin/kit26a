package ua.khpi.oop.pavlova09;

import ua.khpi.oop.pavlova08.HotelGuest;
import ua.khpi.oop.pavlova08.util.InPutUtil;
import ua.khpi.oop.pavlova09.util.ChoiceUtil;

public class CommandParser {
	public static void doCommand(LinkedList<HotelGuest> list) {
		int command = 0;
		int yN;
		while (command < 9) {
			command = ChoiceUtil.chooseCommand();
			switch (command) {
			case 1:
				yN = ChoiceUtil.chooseFirstOrSecond();
				if (yN == 1) {
					HotelGuest toAdd = new HotelGuest();
					list.add(toAdd);
					System.out.println("added!");
				} else {
					HotelGuest toAdd = ChoiceUtil.createNewHotelGuest();
					list.add(toAdd);
					System.out.println("added!");
				}
				break;
			case 2:
				yN = ChoiceUtil.chooseFirstOrSecond();
				System.out.println("Введите позицию");
				int pos = InPutUtil.inputInteger();
				if (yN == 1) {
					HotelGuest toAdd = new HotelGuest();
					list.add(pos, toAdd);
					System.out.println("added!");
				} else {
					HotelGuest toAdd = ChoiceUtil.createNewHotelGuest();
					list.add(pos, toAdd);
					System.out.println("added!");
				}
				break;
			case 3:
				list.remove(list.getSize() - 1);
				System.out.println("removed!");
				break;
			case 4:
				System.out.println("Введите позицию");
				int pos1 = InPutUtil.inputInteger();
				list.remove(pos1);
				System.out.println("removed!");
				break;
			case 5:
				if (list.isEmpty()) {
					System.out.println("List is already cleaned!");
					break;
				} else {
					System.out.println("Is cleaned: " + list.clear());
					break;
				}
			case 6:
				System.out.println("Is empty: " + list.isEmpty());
				break;
			case 7:
				String data = list.toString();
				System.out.println(data);
				break;
			case 8:
				HotelGuest toCheck = new HotelGuest();
				System.out.println("Contains deafault element: " + list.contains(toCheck));
				break;
			default:
				break;
			}
		}
	}
}
