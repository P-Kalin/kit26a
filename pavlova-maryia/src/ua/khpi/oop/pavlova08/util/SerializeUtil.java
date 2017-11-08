package ua.khpi.oop.pavlova08.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import ua.khpi.oop.pavlova06.NewContainerOfStrings;

/**
 * Class <b>SerializeUtil</b> is an util class for serialization and
 * deserialization.
 * 
 * @author pavlova-mv
 *
 */
public class SerializeUtil {
	/**
	 * Method <b>serialize is for serialization container with NewContainerOfStrings
	 * type.</br>
	 * It writes the information about an object in the special file.
	 * 
	 * @param sentences
	 *            container of sentences.
	 */
	public static void serialize(NewContainerOfStrings sentences) {
		ObjectOutputStream out = null;
		try {

			out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("SerialCont")));

			out.writeObject(sentences);
			System.out.println("	Записано: " + sentences);
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

	/**
	 * Method <b>deserialize</b> is for deserialization of an object with a type
	 * NewContainerOfStrings from the special file.
	 * 
	 * @return object
	 */
	public static NewContainerOfStrings deserializeCont() {
		NewContainerOfStrings sentences = null;
		ObjectInputStream in = null;
		try {

			in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("SerialCont")));

			sentences = (NewContainerOfStrings) in.readObject();
			System.out.println("	Cчитано: " + sentences);
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
		}
		return sentences;
	}
}