package ua.khpi.oop.lytvyn16.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Helper class to wrap a list of persons. This is used for saving the list of
 * persons to XML.
 * 
 * @author student Lytvyn I.I. KIT-26A
 */
@XmlRootElement(
        name = "clients")
public class Bureau {

	private List<Client> clients;

	@XmlElement(
	        name = "client")
	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}
}
