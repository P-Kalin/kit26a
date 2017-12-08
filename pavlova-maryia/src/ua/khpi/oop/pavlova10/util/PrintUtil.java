package ua.khpi.oop.pavlova10.util;

import ua.khpi.oop.pavlova10.HotelGuest;
import ua.khpi.oop.pavlova10.LinkedList;
import ua.khpi.oop.pavlova10.LinkedList.Element;

public class PrintUtil {
	public static void printByDays(LinkedList<HotelGuest> list) {
		int i = 1;
		for (Element<HotelGuest> xElement = list.getHead(); xElement != null; xElement = xElement.getNextElement()) {
			System.out.println(i + ". " + xElement.getData().getDays());
			i++;
		}
	}

	public static void printByClass(LinkedList<HotelGuest> list) {
		int i = 1;
		for (Element<HotelGuest> xElement = list.getHead(); xElement != null; xElement = xElement.getNextElement()) {
			System.out.println(i + ". " + xElement.getData().getRoomClass());
			i++;
		}
	}

	public static void printByName(LinkedList<HotelGuest> list) {
		int i = 1;
		for (Element<HotelGuest> xElement = list.getHead(); xElement != null; xElement = xElement.getNextElement()) {
			System.out.println(i + ". " + xElement.getData().getGuestNameSurname());
			i++;
		}
	}

	public static void printByDateOfArrival(LinkedList<HotelGuest> list) {
		int i = 1;
		for (Element<HotelGuest> xElement = list.getHead(); xElement != null; xElement = xElement.getNextElement()) {
			System.out.println(i + ". " + xElement.getData().getDateOfArrival());
			i++;
		}
	}

	public static void printByDateOfEviction(LinkedList<HotelGuest> list) {
		int i = 1;
		for (Element<HotelGuest> xElement = list.getHead(); xElement != null; xElement = xElement.getNextElement()) {
			System.out.println(i + ". " + xElement.getData().getDateOfEviction());
			i++;
		}
	}

	public static void printByMotherland(LinkedList<HotelGuest> list) {
		int i = 1;
		for (Element<HotelGuest> xElement = list.getHead(); xElement != null; xElement = xElement.getNextElement()) {
			System.out.println(i + ". " + xElement.getData().getGuestMotherland());
			i++;
		}
	}

	public static void printByRoomNumber(LinkedList<HotelGuest> list) {
		int i = 1;
		for (Element<HotelGuest> xElement = list.getHead(); xElement != null; xElement = xElement.getNextElement()) {
			System.out.println(i + ". " + xElement.getData().getRoomNum());
			i++;
		}

	}

	public static void printByRoomPlaces(LinkedList<HotelGuest> list) {
		int i = 1;
		for (Element<HotelGuest> xElement = list.getHead(); xElement != null; xElement = xElement.getNextElement()) {
			System.out.println(i + ". " + xElement.getData().getRoomPlaces());
			i++;
		}
	}

	public static void printByRoomClass(LinkedList<HotelGuest> list) {
		int i = 1;
		for (Element<HotelGuest> xElement = list.getHead(); xElement != null; xElement = xElement.getNextElement()) {
			System.out.println(i + ". " + xElement.getData().getRoomClass());
			i++;
		}
	}

	public static void printByReason(LinkedList<HotelGuest> list) {
		int i = 1;
		for (Element<HotelGuest> xElement = list.getHead(); xElement != null; xElement = xElement.getNextElement()) {
			System.out.println(i + ". " + xElement.getData().getReasonOfArrival());
			i++;
		}
	}
}
