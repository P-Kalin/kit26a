package ua.khpi.oop.pavlova15.util;

import java.util.ArrayList;

import ua.khpi.oop.pavlova10.HotelGuest;

public class PrintUtil {
	public static void printByDays(ArrayList<HotelGuest> list) {
		int i = 1;
		for (HotelGuest element : list) {
			System.out.println(i + ". " + element.getDays() + " (" + element.getGuestNameSurname() + ")");
			i++;
		}
	}

	public static void printByClass(ArrayList<HotelGuest> list) {
		int i = 1;
		for (HotelGuest element : list) {
			System.out.println(i + ". " + element.getRoomClass() + " (" + element.getGuestNameSurname() + ")");
			i++;
		}
	}

	public static void printByName(ArrayList<HotelGuest> list) {
		int i = 1;
		for (HotelGuest element : list) {
			System.out.println(i + ". " + element.getGuestNameSurname());
			i++;
		}
	}

	public static void printByDateOfArrival(ArrayList<HotelGuest> list) {
		int i = 1;
		for (HotelGuest element : list) {
			System.out.println(i + ". " + element.getDateOfArrival() + " (" + element.getGuestNameSurname() + ")");
			i++;
		}
	}

	public static void printByDateOfEviction(ArrayList<HotelGuest> list) {
		int i = 1;
		for (HotelGuest element : list) {
			System.out.println(i + ". " + element.getDateOfEviction() + " (" + element.getGuestNameSurname() + ")");
			i++;
		}
	}

	public static void printByMotherland(ArrayList<HotelGuest> list) {
		int i = 1;
		for (HotelGuest element : list) {
			System.out.println(i + ". " + element.getGuestMotherland() + " (" + element.getGuestNameSurname() + ")");
			i++;
		}
	}

	public static void printByRoomNumber(ArrayList<HotelGuest> list) {
		int i = 1;
		for (HotelGuest element : list) {
			System.out.println(i + ". " + element.getRoomNum() + " (" + element.getGuestNameSurname() + ")");
			i++;
		}
	}

	public static void printByRoomPlaces(ArrayList<HotelGuest> list) {
		int i = 1;
		for (HotelGuest element : list) {
			System.out.println(i + ". " + element.getRoomPlaces() + " (" + element.getGuestNameSurname() + ")");
			i++;
		}
	}

	public static void printByRoomClass(ArrayList<HotelGuest> list) {
		int i = 1;
		for (HotelGuest element : list) {
			System.out.println(i + ". " + element.getRoomClass() + " (" + element.getGuestNameSurname() + ")");
			i++;
		}
	}

	public static void printByReason(ArrayList<HotelGuest> list) {
		int i = 1;
		for (HotelGuest element : list) {
			System.out.println(i + ". " + element.getReasonOfArrival() + " (" + element.getGuestNameSurname() + ")");
			i++;
		}
	}
}
