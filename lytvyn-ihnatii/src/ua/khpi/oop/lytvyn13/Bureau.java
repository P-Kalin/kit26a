package ua.khpi.oop.lytvyn13;

import java.util.LinkedList;

/**
 * Бюро знайомств
 * 
 * @author student Lytvyn I.I. KIT-26A
 */
public class Bureau {

	/**
	 * Список клієнтів
	 */
	private LinkedList<Client> clients = new LinkedList<>();

	/**
	 * Конструктор за замовчуванням
	 */
	public Bureau() {
	}

	/**
	 * @return the clients
	 */
	public LinkedList<Client> getClients() {
		return clients;
	}

	/**
	 * @param clients
	 *            the clients to set
	 */
	public void setClients(LinkedList<Client> clients) {
		this.clients = clients;
	}

	/**
	 * Представлення бюро у вигляді інформації про всіх кієнтів
	 */
	@Override
	public String toString() {
		final StringBuffer result = new StringBuffer();
		for (final Client client : this.clients) {
			result.append(ClientUtil.info(client));
		}
		return result.toString();
	}

}
