/**
 * 
 */
package ua.khpi.oop.lytvyn08;

import java.util.ArrayList;
import java.util.List;

/**
 * Бюро знайомств
 * 
 * @author student Lytvyn I.I. KIT-26A
 */
public class Bureau {

	/**
	 * Список клієнтів
	 */
	private List<Client> clients = new ArrayList<>();

	/**
	 * Конструктор за замовчуванням
	 */
	public Bureau() {
	}

	/**
	 * @return the clients
	 */
	public List<Client> getClients() {
		return clients;
	}

	/**
	 * @param clients
	 *            the clients to set
	 */
	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	/**
	 * Представлення бюро у вигляді інформації про всіх кієнтів
	 */
	@Override
	public String toString() {
		String clients = "";
		for (final Client client : this.clients) {
			clients += ClientUtil.info(client);
		}
		return clients;
	}

}
