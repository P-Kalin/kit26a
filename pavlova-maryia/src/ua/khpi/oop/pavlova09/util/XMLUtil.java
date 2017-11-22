package ua.khpi.oop.pavlova09.util;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import ua.khpi.oop.pavlova08.HotelGuest;
import ua.khpi.oop.pavlova09.LinkedList;

public class XMLUtil {

	public static void write(String filename, LinkedList<HotelGuest> list) throws FileNotFoundException {
		XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filename)));

		encoder.close();
		System.out.println("Object was written!");
	}

	public static LinkedList<HotelGuest> read(String filename) throws FileNotFoundException {
		XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(filename)));

		@SuppressWarnings("unchecked")
		LinkedList<HotelGuest> object = (LinkedList<HotelGuest>) decoder.readObject();
		System.out.println("Object was read!");
		decoder.close();
		return object;
	}
}
