package ua.khpi.oop.pavlova12;

import ua.khpi.oop.pavlova08.util.InPutUtil;
import ua.khpi.oop.pavlova10.HotelGuest;
import ua.khpi.oop.pavlova10.LinkedList;

public class CommandParser {
	private static int makeChoice(int choice) {
		System.out.println("Введите команду:");
		System.out.println("1. Поиск всех гостей-туристов");
		System.out.println("2. Поиск всех гостей в командировке");
		System.out.println("3. Поиск всех гостей из Украины");
		System.out.println("4. Поиск всех гостей бизнес класса");
		System.out.println("5. Поиск всех гостей эконом класса");
		System.out.println("6. Поиск всех гостейс фамилией 'Петренко'");
		System.out.println("7. Поиск всех гостей с инициалами 'А.Р.'");
		System.out.println("8. Поиск всех гостей, заселившихся 18 числа любого месяца 21го века");
		System.out.println("9. Выход.");

		choice = InPutUtil.inputInteger();
		return choice;
	}

	public static void doCommand(LinkedList<HotelGuest> list) {
		int command = 0;
		while (command < 9) {
			command = makeChoice(command);
			switch (command) {
			case 1:
				RegexSearch.search(list, "Туризм");
				break;
			case 2:
				RegexSearch.search(list, "Командировка");
				break;
			case 3:
				RegexSearch.search(list, "Украина+(,\\s[А-Я][а-я]+)");
				break;
			case 4:
				RegexSearch.search(list, "Бизнес");
				break;
			case 5:
				RegexSearch.search(list, "Эконом");
				break;
			case 6:
				RegexSearch.search(list, "Петренко((\\s[А-Я][а-я]?\\.)([А-Я][а-я]?\\.){1,})");
				break;
			case 7:
				RegexSearch.search(list, "[А-Я][а-я]+((\\s[А]\\.)([Р]\\.){1,})");
				break;
			case 8:
				RegexSearch.search(list,
						"[1][8]\\-(Январь|Февраль|Март|Апрель|Май|Июнь|Июль|Август|Сентябрь|Октябрь|Ноябрь|Декабрь){1}\\-[2][0][0-9][0-9]");
				break;
			default:
				break;
			}
		}
	}
}
