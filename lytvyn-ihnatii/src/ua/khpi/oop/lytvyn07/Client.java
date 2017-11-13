package ua.khpi.oop.lytvyn07;

/**
 * Реалізує клієнта бюро знайомств.
 * 
 * @author student Lytvyn I.I. KIT-26A
 */
public class Client {

	/**
	 * Стать
	 */
	private String gender;

	/**
	 * Реєстраційний номер
	 */
	private int regNum;

	/**
	 * Дата реєстрації
	 */
	private String regDate;

	/**
	 * Ім'я
	 */
	private String name;

	/**
	 * Зріст
	 */
	private int height;

	/**
	 * Колір очей
	 */
	private String eyes;

	/**
	 * День народження
	 */
	private String birthday;

	/**
	 * Хобі
	 */
	private String[] hobby;

	/**
	 * Вимоги до партнера
	 */
	private String[] requirements;

	/**
	 * Конструктор за замовчуванням
	 */
	Client() {
		gender = null;
		regNum = 0;
		regDate = null;
		name = null;
		height = 0;
		eyes = null;
		birthday = null;
		hobby = null;
		requirements = null;
	}

	/**
	 * @return the birthday
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * @return the eyes
	 */
	public String getEyes() {
		return eyes;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @return the hobbies
	 */
	public String[] getHobbies() {
		return hobby;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the regDate
	 */
	public String getRegDate() {
		return regDate;
	}

	/**
	 * @return the regNum
	 */
	public int getRegNum() {
		return regNum;
	}

	/**
	 * @return the requirements
	 */
	public String[] getRequirements() {
		return requirements;
	}

	/**
	 * @param birthday
	 *            the birthday to set
	 */
	public void setBirthday(String birthday) {
		if (birthday == null || birthday.equals("")) {
			throw new IllegalArgumentException(birthday);
		}
		this.birthday = birthday;
	}

	/**
	 * @param eyes
	 *            the eyes to set
	 */
	public void setEyes(String eyes) {
		if (eyes == null || eyes.equals("")) {
			throw new IllegalArgumentException(eyes);
		}
		this.eyes = eyes;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(String gender) {
		if (gender == null || gender.equals("")) {
			throw new IllegalArgumentException(eyes);
		}
		this.gender = gender;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(int height) {
		if (height <= 0) {
			throw new IllegalArgumentException("" + height);
		}
		this.height = height;
	}

	/**
	 * @param hobby
	 *            the hobbies to set
	 */
	public void setHobbies(String[] hobby) {
		if (hobby.length == 0) {
			throw new IllegalArgumentException(hobby.toString());
		}
		this.hobby = hobby;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		if (name == null || name.equals("")) {
			throw new IllegalArgumentException(name);
		}
		this.name = name;
	}

	/**
	 * @param regDate
	 *            the regDate to set
	 */
	public void setRegDate(String regDate) {
		if (regDate == null || regDate.equals("")) {
			throw new IllegalArgumentException(regDate);
		}
		this.regDate = regDate;
	}

	/**
	 * @param regNum
	 *            the regNum to set
	 */
	public void setRegNum(int regNum) {
		if (regNum <= 0) {
			throw new IllegalArgumentException("" + regNum);
		}
		this.regNum = regNum;
	}

	/**
	 * @param requirements
	 *            the requirements to set
	 */
	public void setRequirements(String[] requirements) {
		if (requirements.length == 0) {
			throw new IllegalArgumentException(requirements.toString());
		}
		this.requirements = requirements;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object client) {
		return this.regNum == ((Client) client).getRegNum();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString();
	}
}
