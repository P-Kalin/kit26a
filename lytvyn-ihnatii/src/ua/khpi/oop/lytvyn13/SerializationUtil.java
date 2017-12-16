package ua.khpi.oop.lytvyn13;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

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
	 * @param path
	 * 
	 * @return <tt>clients</tt> контейнер, що було відновлено з файлу
	 */
	@SuppressWarnings("unchecked")
	public static LinkedList<Client> deserialize(String path) {
		LinkedList<Client> clients = null;
		ObjectInputStream in = null;
		if (path == null) {
			path = Explorer.openFile();
		}
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
	 * @param path
	 * @return clients список клієнтів
	 */
	public static LinkedList<Client> read(String path) {
		if (path == null) {
			path = Explorer.openFile();
		}
		final LinkedList<Client> clients = new LinkedList<>();
		String line = null;
		BufferedReader in = null;
		try {
			/* Відкриваємо потік для зчитування */
			in = new BufferedReader(new InputStreamReader(
			        new FileInputStream(path), "UTF-8"));
			/* Відновлюємо контейнер */
			while ((line = in.readLine()) != null) {
				client = ClientUtil.parse(line);
				clients.addLast(client);
			}
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
	 * @param clients
	 *            бюро, що буде серіалізовано
	 * @param path
	 */
	public static void save(LinkedList<Client> clients, String path) {
		if (path == null) {
			path = Explorer.saveFile();
		}
		BufferedWriter out = null;
		try {
			/* Відкриваємо потік для запису */
			out = new BufferedWriter(new OutputStreamWriter(
			        new FileOutputStream(path), "UTF-8"));
			/* Записуємо контейнер */
			if (clients.size() > 1) {
				for (int i = 0; i < clients.size() - 1; i++) {
					out.append(ClientUtil
					        .clientToData(clients.get(i)));
					out.append("\n"); // переходим на новую строку
				}
			}
			out.append(ClientUtil
			        .clientToData(clients.get(clients.size() - 1)));

			out.flush();
			out.close();
		} catch (final Exception ex) {
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

	/**
	 * Виконує серіалізацію (збереження до файлу) <tt>LinkedList<Client></tt>.
	 * 
	 * @param clients
	 *            бюро, що буде серіалізовано
	 * @param path
	 */
	public static void serialize(LinkedList<Client> clients, String path) {
		ObjectOutputStream out = null;
		if (path == null) {
			path = Explorer.saveFile();
		}
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
