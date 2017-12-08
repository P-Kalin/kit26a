package ua.khpi.oop.pavlova10.util;

import java.util.Comparator;

import ua.khpi.oop.pavlova10.HotelGuest;

public class Comparators {

	public static final Comparator<HotelGuest> sortToBigByDays = new Comparator<HotelGuest>() {

		@Override
		public int compare(HotelGuest o1, HotelGuest o2) {
			if (o1.getDays() < o2.getDays())
				return -1;
			else if (o1.getDays() > o2.getDays())
				return 1;
			return 0;
		}
	};

	public static final Comparator<HotelGuest> sortToSmallByDays = new Comparator<HotelGuest>() {

		@Override
		public int compare(HotelGuest o1, HotelGuest o2) {
			if (o1.getDays() > o2.getDays())
				return -1;
			else if (o1.getDays() < o2.getDays())
				return 1;
			return 0;
		}
	};

	public static final Comparator<HotelGuest> sortToBigByRoomNum = new Comparator<HotelGuest>() {

		@Override
		public int compare(HotelGuest o1, HotelGuest o2) {
			if (Integer.valueOf(o1.getRoomNum()) < Integer.valueOf(o2.getRoomNum()))
				return -1;
			else if (Integer.valueOf(o1.getRoomNum()) > Integer.valueOf(o2.getRoomNum()))
				return 1;
			return 0;
		}
	};

	public static final Comparator<HotelGuest> sortToSmallByRoomNum = new Comparator<HotelGuest>() {
		@Override
		public int compare(HotelGuest o1, HotelGuest o2) {
			if (Integer.valueOf(o1.getRoomNum()) > Integer.valueOf(o2.getRoomNum()))
				return -1;
			else if (Integer.valueOf(o1.getRoomNum()) < Integer.valueOf(o2.getRoomNum()))
				return 1;
			return 0;
		}
	};

	public static final Comparator<HotelGuest> sortToBigByRoomClass = new Comparator<HotelGuest>() {
		@Override
		public int compare(HotelGuest o1, HotelGuest o2) {
			if (o1.getRoomClass().length() < o2.getRoomClass().length())
				return -1;
			else if (o1.getRoomClass().length() > o2.getRoomClass().length())
				return 1;
			return 0;
		}
	};

	public static final Comparator<HotelGuest> sortToSmallByRoomClass = new Comparator<HotelGuest>() {
		@Override
		public int compare(HotelGuest o1, HotelGuest o2) {
			if (o1.getRoomClass().length() > o2.getRoomClass().length())
				return -1;
			else if (o1.getRoomClass().length() < o2.getRoomClass().length())
				return 1;
			return 0;
		}
	};

	public static final Comparator<HotelGuest> sortToBigByRoomPlaces = new Comparator<HotelGuest>() {
		@Override
		public int compare(HotelGuest o1, HotelGuest o2) {
			if (Integer.valueOf(o1.getRoomPlaces()) < Integer.valueOf(o2.getRoomPlaces()))
				return -1;
			else if (Integer.valueOf(o1.getRoomPlaces()) > Integer.valueOf(o2.getRoomPlaces()))
				return 1;
			return 0;
		}
	};

	public static final Comparator<HotelGuest> sortToSmallByRoomPlaces = new Comparator<HotelGuest>() {
		@Override
		public int compare(HotelGuest o1, HotelGuest o2) {
			if (Integer.valueOf(o1.getRoomPlaces()) > Integer.valueOf(o2.getRoomPlaces()))
				return -1;
			else if (Integer.valueOf(o1.getRoomPlaces()) < Integer.valueOf(o2.getRoomPlaces()))
				return 1;
			return 0;
		}
	};

}