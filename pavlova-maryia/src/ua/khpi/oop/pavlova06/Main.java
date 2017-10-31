package ua.khpi.oop.pavlova06;

import java.io.IOException;
import java.util.Scanner;

import ua.khpi.oop.malokhvii05.util.Array;
import ua.khpi.oop.pavlova06.util.ChoiceUtil;
import ua.khpi.oop.pavlova06.util.SerializeUtil;

public class Main {
	public static Scanner scanner = new Scanner(System.in);

	public static void main(final String[] args) throws IOException {

		System.out.println("Лабораторна робота №6");
		NewContainerOfStrings containerOfStrings = new NewContainerOfStrings();
		Array<String> array = new Array<>();
		int choice = 0;
		int command = 0;
		System.out.println("С каким типом контейнера работать?");
		System.out.println("1.Array<String>\n2.NewContainerOfStrings");
		choice = ChoiceUtil.chooseFirstOrSecond(choice);
		if (choice == 1) {
			while (command < 9) {
				command = ChoiceUtil.listOfCommands(command);
				CommandParser.demonstrateArrayWork(command, array);

			}
			SerializeUtil.serialize(array);
			Array<String> forCheck = new Array<String>(SerializeUtil.deserialize());
			if (forCheck.containsAll(array) == true)
				System.out.println("Сериализация валидна");
		} else {
			while (command < 9) {
				command = ChoiceUtil.listOfCommands(command);
				CommandParser.demonstrateContainerWork(command, containerOfStrings);
			}
			SerializeUtil.serialize(containerOfStrings);
			NewContainerOfStrings forCheck = new NewContainerOfStrings(SerializeUtil.deserializeCont());
			if (forCheck.containsAll(containerOfStrings) == true)
				System.out.println("Сериализация валидна");
		}

	}

}