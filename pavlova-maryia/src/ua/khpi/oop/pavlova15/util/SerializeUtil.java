package ua.khpi.oop.pavlova15.util;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import ua.khpi.oop.pavlova10.HotelGuest;

public class SerializeUtil {
	public static void standartSerialization(ArrayList<HotelGuest> toSerialize)
			throws FileNotFoundException, IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("SerialArr"));
		out.writeObject(toSerialize);
		out.close();

	}

	@SuppressWarnings("unchecked")
	public static ArrayList<HotelGuest> standartDeserialization()
			throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("SerialArr"));
		ArrayList<HotelGuest> array = (ArrayList<HotelGuest>) in.readObject();
		in.close();
		return array;
	}

	public static void longTermPersistanceSerialization(ArrayList<HotelGuest> arrayList) throws FileNotFoundException {
		XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("Beanarchieve.xml")));

		encoder.writeObject(arrayList);
		encoder.close();
		System.out.println("Object was written!");
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<HotelGuest> longTermPersistanceDeserialization() throws FileNotFoundException {
		XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("Beanarchieve.xml")));

		ArrayList<HotelGuest> array = (ArrayList<HotelGuest>) decoder.readObject();
		System.out.println("Object was read!");
		decoder.close();
		return array;
	}
}
