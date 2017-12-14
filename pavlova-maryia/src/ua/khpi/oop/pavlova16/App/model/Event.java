package ua.khpi.oop.pavlova16.App.model;

import java.time.LocalDate;
import java.util.Random;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import ua.khpi.oop.pavlova10.HotelGuest;
import ua.khpi.oop.pavlova16.App.util.LocalDateAdapter;

public class Event {

	private final StringProperty guest;
	private final IntegerProperty room;
	private final IntegerProperty numberOfPlaces;
	private final StringProperty motherland;
	private final ObjectProperty<LocalDate> dateOfArrival;

	public Event() {
		this(null);
	}

	public Event(String description) {
		HotelGuest guest = new HotelGuest();
		this.guest = new SimpleStringProperty(guest.getGuestNameSurname());

		this.room = new SimpleIntegerProperty(Integer.valueOf(guest.getRoomNum()));
		this.numberOfPlaces = new SimpleIntegerProperty(Integer.valueOf(guest.getRoomPlaces()));
		this.motherland = new SimpleStringProperty(guest.getGuestMotherland());
		this.dateOfArrival = new SimpleObjectProperty<LocalDate>(LocalDate.of(
				new Random(System.nanoTime()).nextInt(2018 - 2017) + 2017,
				new Random(System.nanoTime()).nextInt(12 - 1) + 1, new Random(System.nanoTime()).nextInt(30 - 1) + 1));
	}

	public String getDescription() {
		return guest.get();
	}

	public void setDescription(String firstName) {
		this.guest.set(firstName);
	}

	public StringProperty descriptionProperty() {
		return guest;
	}

	public int getTimeOfSpending() {
		return room.get();
	}

	public void setTimeOfSpending(int timeOfSpending) {
		this.room.set(timeOfSpending);
	}

	public IntegerProperty timeOfSpendingProperty() {
		return room;
	}

	public int getCountOfParticipant() {
		return numberOfPlaces.get();
	}

	public void setCountOfParticipant(int countOfParticipant) {
		this.numberOfPlaces.set(countOfParticipant);
	}

	public IntegerProperty countOfParticipantProperty() {
		return numberOfPlaces;
	}

	public String getMotherland() {
		return motherland.get();
	}

	public void setMotherland(String motherland) {
		this.motherland.set(motherland);
	}

	public StringProperty motherlandProperty() {
		return motherland;
	}

	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	public LocalDate getDate() {
		return dateOfArrival.get();
	}

	public void setDate(LocalDate date) {
		this.dateOfArrival.set(date);
	}

	public ObjectProperty<LocalDate> dateProperty() {
		return dateOfArrival;
	}

}
