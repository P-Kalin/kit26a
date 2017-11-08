package ua.khpi.oop.pavlova08.util;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import ua.khpi.oop.pavlova08.HotelGuest;

public class XMLUtil {

	public static void write(File filename, HotelGuest object) throws FileNotFoundException {
		XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filename)));

		encoder.writeObject(object);
		encoder.close();
	}

	public static HotelGuest read(File filename) throws FileNotFoundException {
		XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(filename)));

		HotelGuest object = HotelGuest.toObject(decoder.readObject());
		decoder.close();
		return object;
	}
}
