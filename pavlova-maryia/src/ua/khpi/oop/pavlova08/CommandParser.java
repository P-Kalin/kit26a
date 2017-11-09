package ua.khpi.oop.pavlova08;

import java.io.IOException;
import java.util.Scanner;

import ua.khpi.oop.pavlova06.NewContainerOfStrings;
import ua.khpi.oop.pavlova06.util.ChoiceUtil;
import ua.khpi.oop.pavlova08.util.InPutUtil;
import ua.khpi.oop.pavlova08.util.ModifyUtil;
import ua.khpi.oop.pavlova08.util.XMLUtil;
import ua.khpi.oop.pavlova08.util.forFiles.DirectorySearch;

/**
 * Class <b>CommandParser</b> contains methods for specific manipulations with a
 * container and its elements by customer's choice. In this class
 * <i>NewContainerOfStrings</i> is used as a container and converted in strings
 * objects of <i>HotelGuest</i> are used as its elements. </br>
 * Command is set in <b><i>ChoiceUtil</i></b>.
 * 
 * @author pavlova-mv
 * @see NewContainerOfStrings
 * @see HotelGuest
 * @see ChoiceUtil
 */
public class CommandParser {
	private static int index;

	public static Scanner scanner = new Scanner(System.in);

	/**
	 * Method <b>doCommand</b> does specific manipulations with a container and its
	 * elements by the code of each command.
	 * 
	 * @param command
	 *            code of each command
	 * @param containerOfStrings
	 *            container for manipulations
	 * @throws IOException
	 */
	public static void doCommand(int command, NewContainerOfStrings containerOfStrings) throws IOException {
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
			HotelGuest toModify = InPutUtil.askToModifyHotelGuest(index, containerOfStrings);
			toModify.setGuestNameSurname(InPutUtil.inputString());

			ModifyUtil.returnToContainer(toModify, containerOfStrings);
			System.out.println("modified");
			break;

		case 4:
			HotelGuest toModify1 = InPutUtil.askToModifyHotelGuest(index, containerOfStrings);
			toModify1.setGuestDateOfBirth(InPutUtil.inputString());
			ModifyUtil.returnToContainer(toModify1, containerOfStrings);
			System.out.println("modified");
			break;

		case 5:
			HotelGuest toModify2 = InPutUtil.askToModifyHotelGuest(index, containerOfStrings);
			toModify2.setGuestMotherland(InPutUtil.inputString());
			ModifyUtil.returnToContainer(toModify2, containerOfStrings);
			System.out.println("modified");
			break;

		case 6:
			HotelGuest toModify3 = InPutUtil.askToModifyHotelGuest(index, containerOfStrings);
			toModify3.setGuestPassport(InPutUtil.inputString());
			ModifyUtil.returnToContainer(toModify3, containerOfStrings);
			System.out.println("modified");
			break;

		case 7:
			HotelGuest toModify4 = InPutUtil.askToModifyHotelGuest(index, containerOfStrings);
			toModify4.setDateOfArrival(InPutUtil.inputString());
			ModifyUtil.returnToContainer(toModify4, containerOfStrings);
			System.out.println("modified");
			break;

		case 8:
			HotelGuest toModify5 = InPutUtil.askToModifyHotelGuest(index, containerOfStrings);
			toModify5.setDateOfEviction(InPutUtil.inputString());
			ModifyUtil.returnToContainer(toModify5, containerOfStrings);
			System.out.println("modified");
			break;

		case 9:
			HotelGuest toModify6 = InPutUtil.askToModifyHotelGuest(index, containerOfStrings);
			toModify6.setRoomNum(InPutUtil.inputString());
			ModifyUtil.returnToContainer(toModify6, containerOfStrings);
			System.out.println("modified");
			break;

		case 10:
			HotelGuest toModify7 = InPutUtil.askToModifyHotelGuest(index, containerOfStrings);
			toModify7.setRoomClass(InPutUtil.inputString());
			ModifyUtil.returnToContainer(toModify7, containerOfStrings);
			System.out.println("modified");
			break;

		case 11:
			HotelGuest toModify8 = InPutUtil.askToModifyHotelGuest(index, containerOfStrings);
			toModify8.setRoomPlaces(InPutUtil.inputString());
			ModifyUtil.returnToContainer(toModify8, containerOfStrings);
			System.out.println("modified");
			break;

		case 12:
			HotelGuest toModify9 = InPutUtil.askToModifyHotelGuest(index, containerOfStrings);
			toModify9.setReasonOfArrival(InPutUtil.inputString());
			ModifyUtil.returnToContainer(toModify9, containerOfStrings);
			System.out.println("modified");
			break;

		case 13:
			for (String guest : containerOfStrings) {
				if (guest != null)
					System.out.println(guest);
			}

			break;

		case 14:
			String toOpen = DirectorySearch.fileSearch();
			XMLUtil.write(toOpen, containerOfStrings.get(0));

			HotelGuest newOne = XMLUtil.read(toOpen);
			System.out.println(newOne.toString());
			break;

		case 15:
			break;
		}
	}
}
