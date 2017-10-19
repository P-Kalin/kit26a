package ua.khpi.oop.lytvyn06;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Виконує серіалізацію/десеріалізацію контейнеру типу <tt>StringContainer</tt>.
 * 
 * @author student Lytvyn I.I. KIT-26A
 */
public class SerializationUtil {
	/**
	 * Виконує серіалізацію (збереження до файлу) отриманого контейнеру типу
	 * <tt>StringContainer</tt>.
	 * 
	 * @param sentences
	 *            контейнер, що буде серіалізовано
	 */
	public static void serialize(StringContainer sentences) {
		ObjectOutputStream out = null;
		try {
			/* Відкриваємо потік для запису */
			out = new ObjectOutputStream(
			        new BufferedOutputStream(new FileOutputStream("Data.ser")));
			/* Записуємо контейнер */
			out.writeObject(sentences);
			System.out.println("	Записано: " + sentences);
		} catch (IOException ex) {
			ex.printStackTrace();
			/* Обов'язково зачиняємо потік */
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
	 * Виконує десеріалізацію (відновлення з файлу) контейнеру типу
	 * <tt>StringContainer</tt>.
	 * 
	 * @return <tt>sentences</tt> контейнер, що було відновлено з файлу
	 */
	public static StringContainer deserialize() {
		StringContainer sentences = null;
		ObjectInputStream in = null;
		try {
			/* Відкриваємо потік для зчитування */
			in = new ObjectInputStream(
			        new BufferedInputStream(new FileInputStream("Data.ser")));
			/* Відновлюємо контейнер */
			sentences = (StringContainer) in.readObject();
			System.out.println("	Зчитано: " + sentences);
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
			/* Обов'язково зачиняємо потік */
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
