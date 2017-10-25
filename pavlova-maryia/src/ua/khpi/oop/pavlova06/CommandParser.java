package ua.khpi.oop.pavlova06;

import ua.khpi.oop.malokhvii05.util.Array;
import ua.khpi.oop.pavlova06.util.ChoiceUtil;
import ua.khpi.oop.pavlova06.util.InputUtil;

/**
 * Demonstration of usage of two containers: NewContainerOfStrings( author:
 * pavlova-mv), Array (author: malohvii-ee)
 * 
 * @author pavlova-mv
 *
 */
public class CommandParser {
	private static int choice;
	private static int value;

	/**
	 * Method for demonstration of Array usage.
	 * 
	 * @param command
	 *            is the chosen by customer command
	 * @param demArray
	 *            container for modification
	 */
	public static void demonstrateArrayWork(int command, Array<String> demArray) {

		switch (command) {
		case 1:
			System.out.println("");
			System.out.print("\n\n");
			choice = ChoiceUtil.chooseFirstOrSecond(choice);
			if (choice == 1)
				demArray.add("ocean");
			else {
				String string = new String();
				string = InputUtil.inputString(string);
				demArray.add(string);
			}
		case 2:
			System.out.println("");
			System.out.print("\n\n");
			choice = ChoiceUtil.chooseFirstOrSecond(choice);
			if (choice == 1)
				demArray.add(0, "forest");
			else {
				String string = new String();
				string = InputUtil.inputString(string);
				value = InputUtil.inputInteger(value);
				demArray.add(value, string);
			}
		case 3:

		}
	}

	/**
	 * Method for demonstration of NewContainerOfStrings usage.
	 * 
	 * @param command
	 *            is the chosen by customer coomand
	 * @param demContainer
	 *            container for modification
	 */
	public static void demonstrateContainerWork(int command, NewContainerOfStrings demContainer) {

	}
}
