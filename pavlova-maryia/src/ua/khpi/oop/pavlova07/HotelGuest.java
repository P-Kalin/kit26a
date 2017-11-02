package ua.khpi.oop.pavlova07;

/**
 * 
 * @author pavlova-mv
 *
 */
public class HotelGuest {
	private static final String DEFAULT_GUEST_NAME_SURNAME = "Петренко А.Л.";
	private static final String DEFAULT_GUEST_BIRTH = "01-Сент-1987";
	private static final String DEFAULT_GUEST_COUNTRY_CITY = "Украина, Львов";
	private static final String DEFAULT_GUEST_PASSPORT_NUM = "МТ-589647";

	private static final String DEFAULT_DATE_OF_ARRIVAL = "03-Март-2017";
	private static final String DEFAULT_DATE_OF_EVICTION = "18-Март-2017";

	private static final String DEFAULT_ROOM_NUM = "234";
	private static final String DEFAULT_ROOM_CLASS = "Эконом";
	private static final String DEFAULT_ROOM_PLACES_NUM = "1";

	private static final String DEFAULT_REASON_OF_ARRIVAL = "Турист";

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
		this.guestNameSurname = guestNameSurname;
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

	public String toString() {
		StringBuilder temp = new StringBuilder();
		temp.append(this.guestNameSurname + "\n" + this.guestDateOfBirth + "\n" + this.guestMotherland + "\n"
				+ this.guestPassport + "\n\nДата заселения в номер: " + this.dateOfArrival + "\nДата выселения: "
				+ this.dateOfEviction + "\n\nИнформация о номере" + "\nНомер: " + this.roomNum + "\nКласс: "
				+ this.roomClass + "\nКоличество мест: " + this.roomPlaces + "\n\nПричина приезда: "
				+ this.reasonOfArrival + "\n\n\n");
		String info = new String(temp);
		return info;
	}
}
