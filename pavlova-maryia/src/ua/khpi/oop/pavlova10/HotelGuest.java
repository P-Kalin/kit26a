package ua.khpi.oop.pavlova10;

import java.util.ArrayList;

import ua.khpi.oop.pavlova03.TextUtil;
import ua.khpi.oop.pavlova10.util.SortUtil;

/**
 * Class <b>HotelGuest</b> is used for creating domain-objects by the specific
 * variant. In this class information about one guest in a hotel is stored.
 * 
 * @param DEFAULT_GUEST_NAME_SURNAME
 *            is a value for name and surname of the guest in a default case.
 * @param DEFAULT_GUEST_BIRTH
 *            is a value for the date of birth of the guest in a default case.
 * @param DEFAULT_GUEST_COUNTRY_CITY
 *            is a value for the counrty and the city where the guestis from in
 *            a default case.
 * @param DEFAULT_GUEST_PASSPORT_NUM
 *            is a value of the guest's passport number in a default case.
 * @param DEFAULT_DATE_OF_ARRIVAL
 *            is a value for the guest's date of arrival in a default case.
 * @param DEFAULT_DATE_OF_EVICTION
 *            is a value for the guest's date of eviction in a default case.
 * @param DEFAULT_ROOM_NUM
 *            is a value for the guest's room number in a default case.
 * 
 * @param DEFAULT_ROOM_CLASS
 *            is a type of room
 * @param DEFAULT_ROOM_PLACES_NUM
 *            is a value of places in the specific room
 * @param DEFAULT_REASON_OF_ARRIVAL
 *            is a line where the reason of coming to the hotel is described
 * @param LINES_SEPARATOR
 *            is a separator for getting serialized data in lines
 * @param DATA_SEPARATOR
 *            is a separator for getting exactly useful data from a line (in one
 *            line the description of the data and data itself are written both)
 * @param guestNameSurname
 *            is data about guest's name and surname, that is written in the
 *            object
 * @param guestDateOfBirth
 *            is data about guest's date of birth, that is written in the object
 * @param guestMotherland
 *            is data about country and city where the guest is from, that is
 *            written ia the object
 * @param guestPassport
 *            is data about guest's passport number, that is wrritten in the
 *            object
 * @param dateOfArrival
 *            is data about the date when the guest arrived to the hotel, that
 *            is wriiten in the object
 * @param dateOfEviction
 *            is data about the date when the guest left the hotel, that is
 *            wriiten in the object
 * @param roomNum
 *            is data about the number of a room, where the guest lived, that is
 *            written in the object
 * @param roomClass
 *            is data about the state of the room where the guest lived, that is
 *            written in the object
 * @param roomPlaces
 *            is data about number of places in the room the guest lived, that
 *            is written in the object
 * @param reasonOfArrival
 *            is data with description of the reason of coming to the hotel,
 *            that is written in the object
 * @author pavlova-mv
 *
 */
public class HotelGuest {
	private static final String DEFAULT_GUEST_NAME_SURNAME = "Петренко А.Л.";
	private static final String DEFAULT_GUEST_BIRTH = "01-Сентябрь-1987";
	private static final String DEFAULT_GUEST_COUNTRY_CITY = "Украина, Львов";
	private static final String DEFAULT_GUEST_PASSPORT_NUM = "МТ-589647";

	private static final String DEFAULT_DATE_OF_ARRIVAL = "18-Март-2017";
	private static final String DEFAULT_DATE_OF_EVICTION = "18-Март-2017";

	private static final String DEFAULT_ROOM_NUM = "234";
	private static final String DEFAULT_ROOM_CLASS = "Эконом";
	private static final String DEFAULT_ROOM_PLACES_NUM = "1";

	private static final String DEFAULT_REASON_OF_ARRIVAL = "Туризм";

	private static final String LINES_SEPARATOR = "\n";
	private static final String DATA_SEPARATOR = ":";

	private String guestNameSurname;
	private String guestDateOfBirth;
	private String guestMotherland;
	private String guestPassport;
	private String dateOfArrival;
	private String dateOfEviction;
	private String roomNum;
	private String roomClass;
	private String roomPlaces;
	private String reasonOfArrival;
	private int days;

	public HotelGuest() {
		setGuestNameSurname(DEFAULT_GUEST_NAME_SURNAME);
		setGuestDateOfBirth(DEFAULT_GUEST_BIRTH);
		setGuestMotherland(DEFAULT_GUEST_COUNTRY_CITY);
		setGuestPassport(DEFAULT_GUEST_PASSPORT_NUM);
		setDateOfArrival(DEFAULT_DATE_OF_ARRIVAL);
		setDateOfEviction(DEFAULT_DATE_OF_EVICTION);
		setRoomNum(DEFAULT_ROOM_NUM);
		setRoomClass(DEFAULT_ROOM_CLASS);
		setRoomPlaces(DEFAULT_ROOM_PLACES_NUM);
		setReasonOfArrival(DEFAULT_REASON_OF_ARRIVAL);
		days = SortUtil.countDays(this);
	}

	public HotelGuest(String guestNameSurname, String guestDateOfBirth, String guestMotherland, String guestPassport,

			String dateOfArrival, String dateOfEviction, String roomNum, String roomClass, String roomPlaces,
			String reasonOfArrival) {
		this.setGuestNameSurname(guestNameSurname);
		this.setGuestDateOfBirth(guestDateOfBirth);
		this.setGuestMotherland(guestMotherland);
		this.setGuestPassport(guestPassport);
		this.setDateOfArrival(dateOfArrival);
		this.setDateOfEviction(dateOfEviction);
		this.setRoomNum(roomNum);
		this.setRoomClass(roomClass);
		this.setRoomPlaces(roomPlaces);
		this.setReasonOfArrival(reasonOfArrival);
		days = SortUtil.countDays(this);
	}

	public HotelGuest(Object readObject) {
		// TODO Auto-generated constructor stub
	}

	public int getDays() {
		return days;
	}

	/**
	 * @return the guestNameSurname
	 */
	public String getGuestNameSurname() {
		return guestNameSurname;
	}

	/**
	 * @param guestNameSurname
	 *            the guestNameSurname to set
	 */
	public void setGuestNameSurname(String guestNameSurname) {
		this.guestNameSurname = new String(guestNameSurname);
	}

	/**
	 * @return the guestDateOfBirth
	 */
	public String getGuestDateOfBirth() {
		return guestDateOfBirth;
	}

	/**
	 * @param guestDateOfBirth
	 *            the guestDateOfBirth to set
	 */
	public void setGuestDateOfBirth(String guestDateOfBirth) {
		this.guestDateOfBirth = guestDateOfBirth;
	}

	/**
	 * @return the guestMotherland
	 */
	public String getGuestMotherland() {
		return guestMotherland;
	}

	/**
	 * @param guestMotherland
	 *            the guestMotherland to set
	 */
	public void setGuestMotherland(String guestMotherland) {
		this.guestMotherland = guestMotherland;
	}

	/**
	 * @return the guestPassport
	 */
	public String getGuestPassport() {
		return guestPassport;
	}

	/**
	 * @param guestPassport
	 *            the guestPassport to set
	 */
	public void setGuestPassport(String guestPassport) {
		this.guestPassport = guestPassport;
	}

	/**
	 * @return the dateOfArrival
	 */
	public String getDateOfArrival() {
		return dateOfArrival;
	}

	/**
	 * @param dateOfArrival
	 *            the dateOfArrival to set
	 */
	public void setDateOfArrival(String dateOfArrival) {
		this.dateOfArrival = dateOfArrival;
		if (dateOfArrival != DEFAULT_DATE_OF_ARRIVAL && dateOfEviction != null)
			this.days = SortUtil.countDays(this);
	}

	/**
	 * @return the dateOfEviction
	 */
	public String getDateOfEviction() {
		return dateOfEviction;
	}

	/**
	 * @param dateOfEviction
	 *            the dateOfEviction to set
	 */
	public void setDateOfEviction(String dateOfEviction) {
		this.dateOfEviction = dateOfEviction;
		if (dateOfEviction != DEFAULT_DATE_OF_EVICTION)
			this.days = SortUtil.countDays(this);
	}

	/**
	 * @return the roomNum
	 */
	public String getRoomNum() {
		return roomNum;
	}

	/**
	 * @param roomNum
	 *            the roomNum to set
	 */
	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	/**
	 * @return the roomClass
	 */
	public String getRoomClass() {
		return roomClass;
	}

	/**
	 * @param roomClass
	 *            the roomClass to set
	 */
	public void setRoomClass(String roomClass) {
		this.roomClass = roomClass;
	}

	/**
	 * @return the roomPlaces
	 */
	public String getRoomPlaces() {
		return roomPlaces;
	}

	/**
	 * @param roomPlaces
	 *            the roomPlaces to set
	 */
	public void setRoomPlaces(String roomPlaces) {
		this.roomPlaces = roomPlaces;
	}

	/**
	 * @return the reasonOfArrival
	 */
	public String getReasonOfArrival() {
		return reasonOfArrival;
	}

	/**
	 * @param reasonOfArrival
	 *            the reasonOfArrival to set
	 */
	public void setReasonOfArrival(String reasonOfArrival) {
		this.reasonOfArrival = reasonOfArrival;
	}

	/**
	 * Method <b>toString</b> creates a line from an object
	 * 
	 * @return string with full data of an object
	 */
	public String toString() {
		StringBuilder temp = new StringBuilder();
		temp.append(this.guestNameSurname + "\n" + this.guestDateOfBirth + "\n" + this.guestMotherland + "\n"
				+ this.guestPassport + "\n\tДата заселения в номер: " + this.dateOfArrival + "\n\tДата выселения: "
				+ this.dateOfEviction + "\n\tНомер: " + this.roomNum + "\n\tКласс: " + this.roomClass
				+ "\n\tКоличество мест: " + this.roomPlaces + "\n\tПричина приезда: " + this.reasonOfArrival
				+ "\n\n\n");
		String info = new String(temp);
		return info;
	}

	public String toStringForRegex() {
		StringBuilder temp = new StringBuilder();
		temp.append(this.guestNameSurname + "|" + this.guestDateOfBirth + "|" + this.guestMotherland + "|"
				+ this.guestPassport + "|" + this.dateOfArrival + "|" + this.dateOfEviction + "|" + this.roomNum + "|"
				+ this.roomClass + "|" + this.roomPlaces + "|" + this.reasonOfArrival);
		String info = new String(temp);
		return info;
	}

	/**
	 * Method <b>toObject</b> creates an object from a line
	 * 
	 * @param info
	 *            is a line with full information about an object
	 * @return new object
	 * 
	 * @see TextUtil
	 */
	public static HotelGuest toObject(String info) {
		ArrayList<String> inLines = (ArrayList<String>) TextUtil.extractElementsFromText(info, LINES_SEPARATOR);
		String[] inArray = new String[10];
		int i;

		for (i = 0; i < 4; i++)
			inArray[i] = inLines.get(i);

		for (i = 4; i < 10; i++) {
			ArrayList<String> twoHalves = (ArrayList<String>) TextUtil.extractElementsFromText(inLines.get(i),
					DATA_SEPARATOR);
			inArray[i] = twoHalves.get(1);
		}
		HotelGuest newObject = new HotelGuest(inArray[0], inArray[1], inArray[2], inArray[3], inArray[4], inArray[5],
				inArray[6], inArray[7], inArray[8], inArray[9]);
		return newObject;
	}

	public static HotelGuest toObject(Object readObject) {
		HotelGuest newObject = new HotelGuest(readObject);
		return newObject;
	}
}
