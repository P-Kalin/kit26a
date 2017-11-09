package ua.khpi.oop.pavlova08.util;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import ua.khpi.oop.pavlova08.HotelGuest;

public class XMLUtil {

	public static void write(String filename, String string) throws FileNotFoundException {
		XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filename)));

		encoder.writeObject(string);
		encoder.close();
		System.out.println("Object was written!");
	}

	public static HotelGuest read(String filename) throws FileNotFoundException {
		XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(filename)));

		String data = decoder.readObject().toString();
		HotelGuest object = HotelGuest.toObject(data);
		System.out.println("Object was read!");
		decoder.close();
		return object;
	}
}
