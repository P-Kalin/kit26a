package ua.khpi.oop.lytvyn09;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Виконує серіалізацію/десеріалізацію
 * 
 * @author student Lytvyn I.I. KIT-26A
 */
public class SerializationUtil {

	/**
	 * Екземпляр клієнту
	 */
	static Client client = null;

	/**
	 * Виконує десеріалізацію (відновлення з файлу) контейнеру типу
	 * <tt>LinkedList<Client></tt>.
	 * 
	 * @return <tt>clients</tt> контейнер, що було відновлено з файлу
	 */
	@SuppressWarnings("unchecked")
	public static LinkedList<Client> deserialize() {
		LinkedList<Client> clients = null;
		ObjectInputStream in = null;
		final String path = Explorer.openFile();
		try {
			/* Відкриваємо потік для зчитування */
			in = new ObjectInputStream(
			        new BufferedInputStream(new FileInputStream(path)));
			/* Відновлюємо контейнер */
			clients = (LinkedList<Client>) in.readObject();
		} catch (final IOException ex) {
			ex.printStackTrace();
		} catch (final Exception ex) {
			ex.printStackTrace();
			/* Обов'язково зачиняємо потік */
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (final IOException ex) {
					ex.printStackTrace();
				}
			}
		}
		return clients;
	}

	/**
	 * @return clients список клієнтів
	 */
	public static LinkedList<Client> read() {
		final String path = Explorer.openFile();
		final LinkedList<Client> clients = new LinkedList<>();
		String line = null;
		BufferedReader br = null;
		try {
			/* Відкриваємо потік для зчитування */
			br = new BufferedReader(new FileReader(path));
			/* Відновлюємо контейнер */
			while ((line = br.readLine()) != null) {
				client = ClientUtil.parse(line);
				clients.addLast(client);
			}
		} catch (final IOException ex) {
			ex.printStackTrace();
		} catch (final Exception ex) {
			ex.printStackTrace();
			/* Обов'язково зачиняємо потік */
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (final IOException ex) {
					ex.printStackTrace();
				}
			}
		}
		return clients;
	}

	/**
	 * @param clients
	 *            бюро, що буде серіалізовано
	 */
	public static void save(LinkedList<Client> clients) {
		final String path = Explorer.saveFile();
		FileWriter fw = null;
		try {
			/* Відкриваємо потік для запису */
			fw = new FileWriter(path);
			/* Записуємо контейнер */
			if (clients.size() > 1) {
				for (int i = 0; i < clients.size() - 1; i++) {
					fw.append(ClientUtil
					        .clientToData(clients.get(i)));
					fw.append("\n"); // переходим на новую строку
				}
			}
			fw.append(ClientUtil
			        .clientToData(clients.get(clients.size() - 1)));

			fw.flush();
			fw.close();
		} catch (final Exception ex) {
			ex.printStackTrace();
			/* Обов'язково зачиняємо потік */
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (final IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	/**
	 * Виконує серіалізацію (збереження до файлу) <tt>LinkedList<Client></tt>.
	 * 
	 * @param clients
	 *            бюро, що буде серіалізовано
	 */
	public static void serialize(LinkedList<Client> clients) {
		ObjectOutputStream out = null;
		final String path = Explorer.saveFile();
		try {
			/* Відкриваємо потік для запису */
			out = new ObjectOutputStream(
			        new BufferedOutputStream(new FileOutputStream(path)));
			/* Записуємо контейнер */
			out.writeObject(clients);
		} catch (final IOException ex) {
			ex.printStackTrace();
			/* Обов'язково зачиняємо потік */
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (final IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}
}
