package ua.khpi.oop.pavlova06;

import java.io.IOException;
import java.util.Scanner;

import ua.khpi.oop.malokhvii05.util.Array;
import ua.khpi.oop.pavlova06.util.ChoiceUtil;
import ua.khpi.oop.pavlova06.util.InputUtil;
import ua.khpi.oop.pavlova06.util.sortAlgorithms.BubbleClassicSort;
import ua.khpi.oop.pavlova06.util.sortAlgorithms.BubbleModifiedSort;
import ua.khpi.oop.pavlova06.util.sortAlgorithms.ExchangeSampleSort;
import ua.khpi.oop.pavlova06.util.sortAlgorithms.InsertionSort;
import ua.khpi.oop.pavlova06.util.sortAlgorithms.PocketSort;
import ua.khpi.oop.pavlova06.util.sortAlgorithms.QuickSort;
import ua.khpi.oop.pavlova06.util.sortAlgorithms.ShakerSort;
import ua.khpi.oop.pavlova06.util.sortAlgorithms.ShellSort;
import ua.khpi.oop.pavlova06.util.sortAlgorithms.SimpleSample;

/**
 * Demonstration of usage of two containers: NewContainerOfStrings( author:
 * pavlova-mv), Array (author: malohvii-ee)
 * 
 * @author pavlova-mv
 *
 */
public class CommandParser {
	private static int choiceInParser;
	private static int value;
	public static Scanner scanner = new Scanner(System.in);

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

		switch (command) {

		case 1:
			System.out.println("1. Добавление элемента в конец контейнера.\n Какие входные данные использовать?");
			System.out.print("1.) По умолчанию. \n2.) Введеннные вручную.\n");
			choiceInParser = ChoiceUtil.chooseFirstOrSecond(choiceInParser);
			/**
			 * try { choiceInParser = Integer.valueOf(scanner.nextInt());
			 * scanner.nextLine(); } catch (InputMismatchException inputMismatchException) {
			 * scanner.nextLine(); System.out.println("Ошибка работы программы!"); } if
			 * (choiceInParser > 2 || choiceInParser < 1) { System.out.println("Неверно
			 * введена команда! Будет выбрана команда по умолчанию.");
			 * 
			 * }
			 */
			if (choiceInParser == 1) {
				demArray.add("ocean");
				System.out.println("added!");
				break;
			} else {
				String string = new String();
				demArray.add(InputUtil.inputString(string));
				System.out.println("added!");
				break;
			}

		case 2:
			System.out.println(
					"2. Добавление элемента на определенную позицию контейнера.\n Какие входные данные использовать? ");
			System.out.print("1.) По умолчанию. \n2.) Введеннные вручную.\n");
			choiceInParser = ChoiceUtil.chooseFirstOrSecond(choiceInParser);
			if (choiceInParser == 1) {
				demArray.add(0, "forest");
				System.out.println("added");
			} else {
				String string = new String();
				demArray.add(InputUtil.inputInteger(value), InputUtil.inputString(string));
				System.out.println("added!");
			}
			break;

		case 3:
			System.out.println("3. Удаление элемента по индексу.\n Какие входные данные использовать?");
			System.out.print("1.) По умолчанию. \n2.) Введеннные вручную.\n");
			choiceInParser = ChoiceUtil.chooseFirstOrSecond(choiceInParser);
			if (choiceInParser == 1) {
				demArray.remove(0);
				System.out.println("removed!");
			} else {
				demArray.remove(InputUtil.inputInteger(value));
				System.out.println("removed!");
			}
			break;

		case 4:
			System.out.println("4. Удаление элемента по определенному значению.\n Какие входные данные использовать?");
			System.out.print("1.) По умолчанию. \n2.) Введеннные вручную.\n");
			boolean ifRemoved;
			choiceInParser = ChoiceUtil.chooseFirstOrSecond(choiceInParser);
			if (choiceInParser == 1)
				ifRemoved = demArray.remove("mountain");
			else {
				String string = new String();
				ifRemoved = demArray.remove(InputUtil.inputString(string));
			}
			System.out.println("Элемент удален: " + ifRemoved);
			break;

		case 5:
			System.out.println(
					"5. Проверка на наличие в контейнере определноого элемента.\n Какие входные данные использовать?");
			System.out.print("1.) По умолчанию. \n2.) Введеннные вручную.\n");
			boolean ifExist;
			choiceInParser = ChoiceUtil.chooseFirstOrSecond(choiceInParser);
			if (choiceInParser == 1)
				ifExist = demArray.contains("rain");
			else {
				String string = new String();
				ifExist = demArray.contains(InputUtil.inputString(string));
			}
			System.out.println("Element exist" + ifExist);
			break;

		case 6:
			System.out.println("6. Проверка на наличие в контейнере всех элементов другого контейнера.");
			boolean ifContain;
			ifContain = demArray.containsAll(demArray);
			System.out.println("Elements exist" + ifContain);
			break;

		case 7:
			System.out.println("7. Получение элемента по индексу.\n Какие входные данные использовать?");
			System.out.print("1.) По умолчанию. \n2.) Введеннные вручную.\n");
			String element;
			choiceInParser = ChoiceUtil.chooseFirstOrSecond(choiceInParser);
			if (choiceInParser == 1) {
				element = new String(demArray.get(0));

			} else {
				element = new String(demArray.get(InputUtil.inputInteger(value)));

			}
			System.out.println("Got element:" + element);
			break;

		case 8:
			System.out.println("8. Сортировка элементов контейнера.");

		default:
			break;
		}

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

		switch (command) {

		case 1:
			System.out.println("1. Добавление элемента в конец контейнера.");
			System.out.print("1.) По умолчанию. \n2.) Введеннные вручную.\n");
			choiceInParser = ChoiceUtil.chooseFirstOrSecond(choiceInParser);
			if (choiceInParser == 1) {
				demContainer.add("ocean ");
				System.out.println("added!");
			} else {
				String string = new String();
				string = InputUtil.inputString(string);
				demContainer.add(string);
				System.out.println("added!");
			}
			break;

		case 2:
			System.out.println(" Добавление элемента по индексу.");
			System.out.print("1.) По умолчанию. \n2.) Введеннные вручную.\n");
			choiceInParser = ChoiceUtil.chooseFirstOrSecond(choiceInParser);
			if (choiceInParser == 1) {
				demContainer.add(0, "forest");
				System.out.println("added!");
			} else {
				String string = new String();
				string = InputUtil.inputString(string);
				value = InputUtil.inputInteger(value);
				demContainer.add(value, string);
				System.out.println("added!");
			}
			break;

		case 3:
			System.out.println("Удаление элемента по индексу.");
			System.out.print("1.) По умолчанию.\n2.) Введеннные вручную.\n");
			choiceInParser = ChoiceUtil.chooseFirstOrSecond(choiceInParser);
			if (choiceInParser == 1) {

				demContainer.remove(0);
				System.out.println("removed!");
			} else {
				demContainer.remove(InputUtil.inputInteger(value));
				System.out.println("removed!");
			}
			break;

		case 4:
			System.out.println("Удаление конкретного элемента");
			System.out.print("1.) По умолчанию. \n2.) Введеннные вручную.\n");
			boolean ifRemoved;
			choiceInParser = ChoiceUtil.chooseFirstOrSecond(choiceInParser);
			if (choiceInParser == 1)
				ifRemoved = demContainer.remove("rain");
			else {
				String string = new String();
				ifRemoved = demContainer.remove(InputUtil.inputString(string));
			}
			System.out.println("Элемент удален: " + ifRemoved);
			break;

		case 5:

			System.out.println("Проверка на наличие элемента.");
			System.out.print("1.) По умолчанию. \n2.) Введеннные вручную.\n");
			boolean ifExist;
			choiceInParser = ChoiceUtil.chooseFirstOrSecond(choiceInParser);
			if (choiceInParser == 1)
				ifExist = demContainer.contains("rain");
			else {
				String string = new String();
				ifExist = demContainer.contains(InputUtil.inputString(string));
			}
			System.out.println("Element exists" + ifExist);
			break;

		case 6:
			System.out.println("Проверка на наличие всех элементов другого контейнера");
			System.out.print("1.) По умолчанию.\n2.) Введеннные вручную.\n");
			boolean ifContain;
			ifContain = demContainer.containsAll(demContainer);
			System.out.println("Elements exist" + ifContain);
			break;

		case 7:
			System.out.println("Выведение по индексу");
			System.out.print("1.) По умолчанию. \n2.) Введеннные вручную.\n");
			String element;
			choiceInParser = ChoiceUtil.chooseFirstOrSecond(choiceInParser);
			if (choiceInParser == 1)
				element = new String(demContainer.get(0));
			else {
				element = new String(demContainer.get(InputUtil.inputInteger(value)));
			}
			System.out.println(":" + element);
			break;

		case 8:
			System.out.println("Сортировка");
			demostrateSortAlgorithmsCont(ChoiceUtil.listOfSorts(command), demContainer);
			break;

		default:
			ChoiceUtil.listOfCommands(command);
		}
	}

	/**
	 * Method for demonstration of different sort algorithms
	 * 
	 * @param command
	 *            is the number of chosen algorithm
	 * @param array
	 *            is the container, that would be sorted
	 */
	public static void demostrateSortAlgorithmsCont(int command, NewContainerOfStrings containerOfStrings) {
		switch ((command)) {
		case 1:
			BubbleClassicSort sort = new BubbleClassicSort();
			sort.sort(containerOfStrings, false);
			break;
		case 2:
			BubbleModifiedSort sort2 = new BubbleModifiedSort();
			sort2.sort(containerOfStrings, false);
			break;
		case 3:
			ExchangeSampleSort sort3 = new ExchangeSampleSort();
			sort3.sort(containerOfStrings, false);
			break;
		case 4:
			ua.khpi.oop.pavlova06.util.sortAlgorithms.HeapSort sort4 = new ua.khpi.oop.pavlova06.util.sortAlgorithms.HeapSort();
			sort4.sort(containerOfStrings, false);
			break;
		case 5:
			InsertionSort sort5 = new InsertionSort();
			sort5.sort(containerOfStrings, false);
			break;
		case 6:
			PocketSort sort6 = new PocketSort();
			sort6.sort(containerOfStrings, false);
			break;
		case 7:
			QuickSort sort7 = new QuickSort();
			sort7.sort(containerOfStrings, false);
			break;
		case 8:
			ShakerSort sort8 = new ShakerSort();
			sort8.sort(containerOfStrings, false);
			break;
		case 9:
			ShellSort sort9 = new ShellSort();
			sort9.sort(containerOfStrings, false);
			break;
		case 10:
			SimpleSample sort10 = new SimpleSample();
			sort10.sort(containerOfStrings, false);
			break;
		default:
			break;
		}
	}
}
