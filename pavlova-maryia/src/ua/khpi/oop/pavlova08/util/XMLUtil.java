package ua.khpi.oop.pavlova08.util;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import ua.khpi.oop.pavlova06.NewContainerOfStrings;

public class XMLUtil {

	public static void write(String filename, NewContainerOfStrings object) throws FileNotFoundException {
		XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filename)));

		encoder.writeObject(object);
		encoder.close();
		System.out.println("Object was written!");
	}

	public static NewContainerOfStrings read(String filename) throws FileNotFoundException {
		XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(filename)));

		NewContainerOfStrings object = (NewContainerOfStrings) decoder.readObject();
		System.out.println("Object was read!");
		decoder.close();
		return object;
	}
}
