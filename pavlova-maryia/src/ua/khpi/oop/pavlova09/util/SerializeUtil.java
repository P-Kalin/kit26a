package ua.khpi.oop.pavlova09.util;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import ua.khpi.oop.pavlova08.HotelGuest;
import ua.khpi.oop.pavlova09.LinkedList;

public class SerializeUtil {
	/**
	 * Method <b>serialize is for serialization container with NewContainerOfStrings
	 * type.</br>
	 * It writes the information about an object in the special file.
	 * 
	 * @param sentences
	 *            container of sentences.
	 */
	public static void serialize(LinkedList<HotelGuest> list) {
		ObjectOutputStream out = null;
		try {

			out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("SerialCont")));

			System.out.println("	Записано: " + list);
		} catch (IOException ex) {
			ex.printStackTrace();

		} finally {
			if (out != null)
				try {
					out.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
		}
	}

}