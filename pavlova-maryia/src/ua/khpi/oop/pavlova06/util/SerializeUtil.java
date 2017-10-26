package ua.khpi.oop.pavlova06.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import ua.khpi.oop.malokhvii05.util.Array;
import ua.khpi.oop.pavlova06.NewContainerOfStrings;

public class SerializeUtil {
	public static void serialize(NewContainerOfStrings sentences) {
		ObjectOutputStream out = null;
		try {

			out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("Serial")));

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

	public static void serialize(Array<String> sentences) {
		ObjectOutputStream out = null;
		try {

			out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("Data.ser")));

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

	public static NewContainerOfStrings deserializeCont() {
		NewContainerOfStrings sentences = null;
		ObjectInputStream in = null;
		try {

			in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("Serial")));

			sentences = (NewContainerOfStrings) in.readObject();
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

	@SuppressWarnings("unchecked")
	public static Array<String> deserialize() {
		Array<String> sentences = null;
		ObjectInputStream in = null;
		try {

			in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("Serial")));

			sentences = (Array<String>) in.readObject();
			System.out.println("	Зчитано: " + sentences);
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
