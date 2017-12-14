package ua.khpi.oop.pavlova16.App.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "events")
public class EventListWrapper {
	@SuppressWarnings("rawtypes")
	private List events;

	@SuppressWarnings("rawtypes")
	@XmlElement(name = "event")
	public List getEvents() {
		return events;
	}

	@SuppressWarnings("rawtypes")
	public void setEvents(List events) {
		this.events = events;
	}
}
