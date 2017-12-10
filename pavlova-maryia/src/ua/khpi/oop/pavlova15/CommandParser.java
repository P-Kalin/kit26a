package ua.khpi.oop.pavlova15;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import ua.khpi.oop.pavlova10.HotelGuest;
import ua.khpi.oop.pavlova15.util.Comparators;
import ua.khpi.oop.pavlova15.util.InPutUtil;
import ua.khpi.oop.pavlova15.util.PrintUtil;
import ua.khpi.oop.pavlova15.util.SerializeUtil;
import ua.khpi.oop.pavlova15.util.SortUtil;

public class CommandParser {
	private static boolean auto = false;

	private static void setSetup() {
		String setup = InPutUtil.inputString();
		if (setup.equals("-a") || setup.equals("-auto"))
			auto = true;
		else if (setup.equals("-c") || setup.equals("-customer"))
			auto = false;
		else
			auto = false;
	}

	private static boolean ifAuto() {
		return auto;
	}

	public static void doCommand(ArrayList<HotelGuest> array) throws FileNotFoundException {
		ExtraFunctions.showSetups();
		setSetup();
		ExtraFunctions.showCommands();
		int command = 0;
		int sorting = 0;
		while (command < 12) {
			command = InPutUtil.makeChoice();
			switch (command) {
			case 1:
				if (ifAuto()) {
					array = ExtraFunctions.createDefaultArrayList();
					System.out.println("-new arrayList was created-");
					break;
				} else {
					array = ExtraFunctions.createArrayList();
					System.out.println("-new arrayList was created-");
					break;
				}
			case 2:
				ArrayList<HotelGuest> newOne = new ArrayList<>(array);
				System.out.println("- old array and a new one are equal: " + array.equals(newOne) + " -");
				break;
			case 3:
				if (ifAuto()) {
					array = SortUtil.sort(array, Comparators.sortToBigByDays);
					PrintUtil.printByDays(array);
					break;
				} else {
					sorting = InPutUtil.chooseSortingType();
					if (sorting == 1) {
						array = SortUtil.sort(array, Comparators.sortToBigByDays);
						PrintUtil.printByDays(array);
						break;
					} else {
						array = SortUtil.sort(array, Comparators.sortToSmallByDays);
						PrintUtil.printByDays(array);
						break;
					}
				}
			case 4:
				array = SortUtil.sort(array, "SURNAME");
				PrintUtil.printByName(array);
				;
				break;
			case 5:
				array = SortUtil.sort(array, "ROOM_CLASS");
				PrintUtil.printByClass(array);
				break;
			case 6:
				array = SortUtil.sort(array, "REASON");
				PrintUtil.printByReason(array);
				break;
			case 7:
				if (ifAuto()) {
					array = SortUtil.sort(array, Comparators.sortToBigByRoomNum);
					PrintUtil.printByRoomNumber(array);
					break;
				} else {
					sorting = InPutUtil.chooseSortingType();
					if (sorting == 1) {
						array = SortUtil.sort(array, Comparators.sortToBigByRoomNum);
						PrintUtil.printByRoomNumber(array);
						break;
					} else {
						array = SortUtil.sort(array, Comparators.sortToSmallByRoomNum);
						PrintUtil.printByRoomNumber(array);
						break;
					}
				}
			case 8:
				if (ifAuto()) {
					array = SortUtil.sort(array, Comparators.sortToBigByRoomPlaces);
					PrintUtil.printByRoomPlaces(array);
					;
					break;
				} else {
					sorting = InPutUtil.chooseSortingType();
					if (sorting == 1) {
						array = SortUtil.sort(array, Comparators.sortToBigByRoomPlaces);
						PrintUtil.printByRoomPlaces(array);
						break;
					} else {
						array = SortUtil.sort(array, Comparators.sortToSmallByRoomPlaces);
						PrintUtil.printByRoomPlaces(array);
						break;
					}
				}
			case 9:
				array.clear();
				System.out.println("-array is clear-");
				break;
			case 10:
				SerializeUtil.standartSerialization(array);
				array.clear();
				array = SerializeUtil.standartDeserialization();
				System.out.println("-result of serialization/deserialization-");
				System.out.println(array);
				break;
			case 11:
				SerializeUtil.longTermPersistanceSerialization(array);
				array.clear();
				array = SerializeUtil.longTermPersistanceDeserialization();
				System.out.println("-result of serialization/deserialization-");
				System.out.println(array);
				break;
			case 12:
				System.out.println("-exit-");
			default:
				break;
			}
		}
	}
}
