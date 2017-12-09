package ua.khpi.oop.pavlova13;

import ua.khpi.oop.pavlova10.HotelGuest;
import ua.khpi.oop.pavlova10.LinkedList;

public class ExtraFunctions {
	public static LinkedList<HotelGuest> createDefaultList() {
		LinkedList<HotelGuest> list = new LinkedList<HotelGuest>();
		HotelGuest one = new HotelGuest("Иванов А.Р.", "23-Октябрь-1980", "Львов, Украина", "МТ-452178", "12-Июнь-2016",
				"23-Июнь-2016", "123", "Бизнес", "2", "Командировка");
		HotelGuest two = new HotelGuest("Сидоров Л.Ф.", "11-Май-1991", "Ивано-Франковск, Украина", "МТ-123654",
				"15-Сентябрь-2017", "22-Сентябрь-2017", "220", "Эконом", "1", "Туризм");
		HotelGuest three = new HotelGuest("Петров В.В.", "14-Декабрь-1989", "Полтава, Украина", "МТ-546985",
				"28-Апрель-2017", "5-Май-2017", "51", "Бизнес", "3", "Командировка");
		list.add(one);
		list.add(two);
		list.add(three);

		return list;
	}
}
