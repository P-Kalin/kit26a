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
	public static void standartSerialization(ArrayList<HotelGuest> toSerialize) {
		try {
			FileOutputStream fos = new FileOutputStream("SerialArray");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(toSerialize);
			oos.close();
			fos.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<HotelGuest> standartDeserialization() {
		ArrayList<HotelGuest> arraylist = null;
		try {
			FileInputStream fis = new FileInputStream("SerialArray");
			ObjectInputStream ois = new ObjectInputStream(fis);
			arraylist = (ArrayList<HotelGuest>) ois.readObject();
			ois.close();
			fis.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return arraylist;
		} catch (ClassNotFoundException c) {
			System.out.println("Class not found");
			c.printStackTrace();
			return arraylist;
		}
		return arraylist;
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
