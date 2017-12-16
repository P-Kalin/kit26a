/**
 * 
 */
package ua.khpi.oop.lytvyn08;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Виконує серіалізацію/десеріалізацію
 * 
 * @author student Lytvyn I.I. KIT-26A
 */
public class LTPSUtil {

	/**
	 * Виконує десеріалізацію (відновлення з файлу) контейнеру типу
	 * <tt>Bureau</tt>.
	 * 
	 * @return <tt>bureau</tt> контейнер, що було відновлено з файлу
	 */
	public static Bureau deserialize() {
		final String path = Explorer.openFile();
		XMLDecoder decoder = null;
		try {
			decoder = new XMLDecoder(new BufferedInputStream(
			        new FileInputStream(path)));
		} catch (final Exception exception) {
			exception.printStackTrace();
		}
		final Bureau bureau = (Bureau) decoder.readObject();
		System.out.println(bureau);

		return bureau;
	}

	/**
	 * Виконує серіалізацію (збереження до файлу) отриманого контейнеру типу
	 * <tt>Bureau</tt>.
	 * 
	 * @param bureau
	 *            контейнер, що буде серіалізовано
	 */
	public static void serialize(Bureau bureau) {
		final String path = Explorer.saveFile();
		XMLEncoder encoder = null;
		try {
			encoder = new XMLEncoder(new BufferedOutputStream(
			        new FileOutputStream(path)));
		} catch (final Exception exception) {
			exception.printStackTrace();
		}
		encoder.writeObject(bureau);
		encoder.close();
	}
}
