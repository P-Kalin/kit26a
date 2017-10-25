package ua.khpi.oop.pavlova06;

import java.io.IOException;

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
	 * @throws IOException
	 */
	public static void demonstrateArrayWork(int command, Array<String> demArray) throws IOException {
		do {
			switch (command) {

			case 1:
				System.out.println("1. Добавление элемента в конец контейнера.\n Какие входные данные использовать?");
				System.out.print("1.) По умолчанию. \n 2.) Введеннные вручную.\n");
				choice = ChoiceUtil.chooseFirstOrSecond(choice);
				if (choice == 1)
					demArray.add("ocean");
				else {
					String string = new String();
					demArray.add(InputUtil.inputString(string));
				}

			case 2:
				System.out.println(
						"2. Добавление элемента на определенную позицию контейнера.\\n Какие входные данные использовать? ");
				System.out.print("1.) По умолчанию. \\n 2.) Введеннные вручную.\\n");
				choice = ChoiceUtil.chooseFirstOrSecond(choice);
				if (choice == 1)
					demArray.add(0, "forest");
				else {
					String string = new String();
					demArray.add(InputUtil.inputInteger(value), InputUtil.inputString(string));
				}

			case 3:
				System.out.println("3. Удаление элемента по индексу.\\n Какие входные данные использовать?");
				System.out.print("1.) По умолчанию. \\n 2.) Введеннные вручную.\\n");
				choice = ChoiceUtil.chooseFirstOrSecond(choice);
				if (choice == 1)
					demArray.remove(0);
				else {
					demArray.remove(InputUtil.inputInteger(value));
				}

			case 4:
				System.out.println(
						"4. Удаление элемента по определенному значению.\\n Какие входные данные использовать?");
				System.out.print("1.) По умолчанию. \\n 2.) Введеннные вручную.\\n");
				boolean ifRemoved;
				choice = ChoiceUtil.chooseFirstOrSecond(choice);
				if (choice == 1)
					ifRemoved = demArray.remove("mountain");
				else {
					String string = new String();
					ifRemoved = demArray.remove(InputUtil.inputString(string));
				}
				System.out.println("Элемент удален: " + ifRemoved);

			case 5:
				System.out.println(
						"5. Проверка на наличие в контейнере определноого элемента.\\n Какие входные данные использовать?");
				System.out.print("1.) По умолчанию. \\n 2.) Введеннные вручную.\\n");
				boolean ifExist;
				choice = ChoiceUtil.chooseFirstOrSecond(choice);
				if (choice == 1)
					ifExist = demArray.contains("rain");
				else {
					String string = new String();
					ifExist = demArray.contains(InputUtil.inputString(string));
				}
				System.out.println("" + ifExist);

			case 6:
				System.out.println(
						"6. Проверка на наличие в контейнере всех элементов другого контейнера.\\n Какие входные данные использовать?");
				boolean ifContain;
				ifContain = demArray.containsAll(demArray);
				System.out.println("" + ifContain);

			case 7:
				System.out.println("7. Получение элемента по индексу.\\n Какие входные данные использовать?");
				System.out.print("1.) По умолчанию. \\n 2.) Введеннные вручную.\\n");
				String element;
				choice = ChoiceUtil.chooseFirstOrSecond(choice);
				if (choice == 1)
					element = new String(demArray.get(0));
				else {
					element = new String(demArray.get(InputUtil.inputInteger(value)));
				}
				System.out.println(":" + element);
			case 8:
				System.out.println("8. Сортировка элементов контейнера.");
				System.out.print("1.) По умолчанию. \\n 2.) Введеннные вручную.\\n");
				demonstrateSortAlgorithms(ChoiceUtil.listOfSorts(choice), demArray);
			default:
				ChoiceUtil.listOfCommands(command);
			}
		} while (command < 9);
	}

	/**
	 * Method for demonstration of NewContainerOfStrings usage.
	 * 
	 * @param command
	 *            is the chosen by customer coomand
	 * @param demContainer
	 *            container for modification
	 * @throws IOException
	 */
	public static void demonstrateContainerWork(int command, NewContainerOfStrings demContainer) throws IOException {
		do {
			switch (command) {

			case 1:
				System.out.println("");
				System.out.print("1.) По умолчанию. \\n 2.) Введеннные вручную.\\n");
				choice = ChoiceUtil.chooseFirstOrSecond(choice);
				if (choice == 1)
					demContainer.add("ocean");
				else {
					String string = new String();
					string = InputUtil.inputString(string);
					demContainer.add(string);
				}

			case 2:
				System.out.println("");
				System.out.print("1.) По умолчанию. \\n 2.) Введеннные вручную.\\n");
				choice = ChoiceUtil.chooseFirstOrSecond(choice);
				if (choice == 1)
					demContainer.add(0, "forest");
				else {
					String string = new String();
					string = InputUtil.inputString(string);
					value = InputUtil.inputInteger(value);
					demContainer.add(value, string);
				}

			case 3:
				System.out.println("");
				System.out.print("1.) По умолчанию. \\n 2.) Введеннные вручную.\\n");
				choice = ChoiceUtil.chooseFirstOrSecond(choice);
				if (choice == 1)
					demContainer.remove(0);
				else {
					demContainer.remove(InputUtil.inputInteger(value));
				}

			case 4:
				System.out.println("");
				System.out.print("1.) По умолчанию. \\n 2.) Введеннные вручную.\\n");
				boolean ifRemoved;
				choice = ChoiceUtil.chooseFirstOrSecond(choice);
				if (choice == 1)
					ifRemoved = demContainer.remove("rain");
				else {
					String string = new String();
					ifRemoved = demContainer.remove(InputUtil.inputString(string));
				}
				System.out.println("Элемент удален: " + ifRemoved);
			case 5:

				System.out.println("");
				System.out.print("1.) По умолчанию. \\n 2.) Введеннные вручную.\\n");
				boolean ifExist;
				choice = ChoiceUtil.chooseFirstOrSecond(choice);
				if (choice == 1)
					ifExist = demContainer.contains("rain");
				else {
					String string = new String();
					ifExist = demContainer.contains(InputUtil.inputString(string));
				}
				System.out.println("" + ifExist);

			case 6:
				System.out.println("");
				System.out.print("1.) По умолчанию. \\n 2.) Введеннные вручную.\\n");
				boolean ifContain;
				ifContain = demContainer.containsAll(demContainer);
				System.out.println("" + ifContain);

			case 7:
				System.out.println("");
				System.out.print("1.) По умолчанию. \\\\n 2.) Введеннные вручную.\\\\n");
				String element;
				choice = ChoiceUtil.chooseFirstOrSecond(choice);
				if (choice == 1)
					element = new String(demContainer.get(0));
				else {
					element = new String(demContainer.get(InputUtil.inputInteger(value)));
				}
				System.out.println(":" + element);

			case 8:
				System.out.println("");
				System.out.print("1.) По умолчанию. \\\\n 2.) Введеннные вручную.\\\\n");
				demonstrateSortAlgorithms(ChoiceUtil.listOfSorts(choice), demContainer);
			default:
				ChoiceUtil.listOfCommands(command);
			}
		} while (command < 9);
	}

	/**
	 * Method for demonstration of different sort algorithms
	 * 
	 * @param command
	 *            is the number of chosen algorithm
	 * @param array
	 *            is the container, that would be sorted
	 */
	public static void demonstrateSortAlgorithms(int command, Iterable<String> array) {

	}
}
