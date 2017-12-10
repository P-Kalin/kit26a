package ua.khpi.oop.pavlova15;

import java.util.ArrayList;

import ua.khpi.oop.pavlova10.HotelGuest;
import ua.khpi.oop.pavlova15.util.InPutUtil;

public class ExtraFunctions {

	static void showSetups() {
		System.out.println("-a or -auto: program will process in a default setup");
		System.out.println("-c or -customer: program will process with input values");
		// System.out.println("-h or -help: description of each method(function) will
		// appear");
	}

	static void showCommands() {
		System.out.println("1. Создание списка из 5 элементов");
		System.out.println("2. Создание нового списка на основе предыдущего");
		System.out.println("3. Сортировка списка по количеству дней пребывания в отеле");
		System.out.println("4. Сортировка списка по фамилии гостя");
		System.out.println("5. Сортировка списка по классу номера гостя");
		System.out.println("6. Сортировка списка по причине пребывания гостя в отеле");
		System.out.println("7. Сортировка списка по номеру комнаты");
		System.out.println("8. Сортировка списка по количеству мест в номере");
		System.out.println("9. Очистка списка");
		System.out.println("10. Стандартная сериализация/десериализация списка");
		System.out.println("11. Нестандартная сериализация/десериализация списка");
		System.out.println("12. Выход");
	}

	public static ArrayList<HotelGuest> createDefaultArrayList() {
		ArrayList<HotelGuest> arrayList = new ArrayList<>();
		HotelGuest first = new HotelGuest("Иванов А.Р.", "23-Октябрь-1980", "Львов, Украина", "МТ-452178",
				"12-Июнь-2016", "23-Июнь-2016", "123", "Бизнес", "2", "Командировка");
		HotelGuest second = new HotelGuest("Сидоров Л.Ф.", "11-Май-1991", "Ивано-Франковск, Украина", "МТ-123654",
				"15-Сентябрь-2017", "22-Сентябрь-2017", "220", "Эконом", "1", "Туризм");
		HotelGuest third = new HotelGuest("Петров В.В.", "14-Декабрь-1989", "Полтава, Украина", "МТ-546985",
				"28-Апрель-2017", "5-Май-2017", "51", "Бизнес", "3", "Командировка");
		HotelGuest fourth = new HotelGuest("Федоренко Н.Л.", "23-Февраль-1992", "Харьков, Украина", "МТ-965478",
				"23-Октябрь-2017", "2-Ноябрь-2017", "56", "Эконом", "1", "Туризм");
		HotelGuest fifth = new HotelGuest("Андреенко С.А.", "12-Январь-1975", "Одесса, Украина", "МТ-362598",
				"10-Декабрь-2016", "2-Январь-2017", "235", "Бизнес", "4", "Туризм");
		arrayList.add(first);
		arrayList.add(second);
		arrayList.add(third);
		arrayList.add(fourth);
		arrayList.add(fifth);

		return arrayList;

	}

	public static ArrayList<HotelGuest> createArrayList() {
		ArrayList<HotelGuest> arrayList = new ArrayList<>();
		HotelGuest first = InPutUtil.inputGuest();
		HotelGuest second = InPutUtil.inputGuest();
		HotelGuest third = InPutUtil.inputGuest();
		HotelGuest fourth = InPutUtil.inputGuest();
		HotelGuest fifth = InPutUtil.inputGuest();
		arrayList.add(first);
		arrayList.add(second);
		arrayList.add(third);
		arrayList.add(fourth);
		arrayList.add(fifth);

		return arrayList;
	}

	public static ArrayList<String> createStringArrayList(ArrayList<HotelGuest> array, String type) {
		ArrayList<String> strings = new ArrayList<>();
		if (type.equals("ROOM_CLASS")) {
			for (HotelGuest el : array)
				strings.add(el.getRoomClass());
			return strings;
		} else if (type.equals("SURNAME")) {
			for (HotelGuest el : array)
				strings.add(el.getGuestNameSurname());
			return strings;
		} else if (type.equals("MOTHERLAND")) {
			for (HotelGuest el : array)
				strings.add(el.getGuestMotherland());
			return strings;
		} else if (type.equals("REASON")) {
			for (HotelGuest el : array)
				strings.add(el.getReasonOfArrival());
			return strings;
		} else {
			for (HotelGuest el : array)
				strings.add(el.getRoomClass());
			return strings;
		}
	}

	public static ArrayList<HotelGuest> convertToHotelGuestArrayList(ArrayList<HotelGuest> arrayList,
			ArrayList<String> strings, String type) {
		ArrayList<HotelGuest> temp = new ArrayList<>(arrayList);
		arrayList.clear();
		if (type.equals("ROOM_CLASS")) {
			for (int i = 0; i < strings.size(); i++) {
				for (int j = 0; i < temp.size(); i++) {
					if (temp.get(j) != null) {
						if (temp.get(j).getRoomClass().equals(strings.get(i))) {
							arrayList.add(temp.get(j));
							temp.remove(j);
							break;
						}
					}

				}
			}
		} else if (type.equals("SURNAME")) {
			for (int i = 0; i < strings.size(); i++) {
				for (int j = 0; i < temp.size(); i++) {
					if (temp.get(j) != null) {
						if (temp.get(j).getGuestNameSurname().equals(strings.get(i))) {
							arrayList.add(temp.get(j));
							temp.remove(j);
							break;
						}
					}
				}
			}
		} else if (type.equals("MOTHERLAND")) {
			for (int i = 0; i < strings.size(); i++) {
				for (int j = 0; i < temp.size(); i++) {
					if (temp.get(j) != null) {
						if (temp.get(j).getGuestMotherland().equals(strings.get(i))) {
							arrayList.add(temp.get(j));
							temp.remove(j);
							break;
						}
					}
				}
			}
		} else if (type.equals("REASON")) {
			for (int i = 0; i < strings.size(); i++) {
				for (int j = 0; i < temp.size(); i++) {
					if (temp.get(j) != null) {
						if (temp.get(j).getReasonOfArrival().equals(strings.get(i))) {
							arrayList.add(temp.get(j));
							temp.remove(j);
							break;
						}
					}
				}
			}
		} else {
			for (int i = 0; i < strings.size(); i++) {
				for (int j = 0; i < temp.size(); i++) {
					if (temp.get(j) != null) {
						if (temp.get(j).getRoomClass().equals(strings.get(i))) {
							arrayList.add(temp.get(j));
							temp.remove(j);
							break;
						}
					}
				}
			}
		}
		return arrayList;
	}
}
