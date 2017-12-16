package ua.khpi.oop.lytvyn12;

import java.io.Serializable;

/**
 * Реалізує клієнта бюро знайомств.
 * 
 * @author student Lytvyn I.I. KIT-26A
 */
public class Client implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1737890839005372828L;

	/**
	 * Стать
	 */
	private String gender = null;

	/**
	 * Реєстраційний номер
	 */
	private int regNum = 0;

	/**
	 * Дата реєстрації
	 */
	private String regDate = null;

	/**
	 * Ім'я
	 */
	private String name = null;

	/**
	 * Зріст
	 */
	private int height = 0;

	/**
	 * Колір очей
	 */
	private String eyes = null;

	/**
	 * День народження
	 */
	private String birthday = null;

	/**
	 * Хобі
	 */
	private String[] hobby = null;

	/**
	 * Вимоги до партнера
	 */
	private String[] requirements = null;

	/**
	 * Конструктор за замовчуванням
	 */
	public Client() {
	}

	/**
	 * Порівнює обєкти на відповідність
	 */
	@Override
	public boolean equals(Object client) {
		final int good = 7 + this.hobby.length + this.requirements.length;
		int mark = 0;
		if (this.gender.equals(((Client) client).getGender())) {
			mark++;
		}
		if (this.regNum == ((Client) client).getRegNum()) {
			mark++;
		}
		if (this.regDate.equals(((Client) client).getRegDate())) {
			mark++;
		}
		if (this.name.equals(((Client) client).getName())) {
			mark++;
		}
		if (this.height == ((Client) client).getHeight()) {
			mark++;
		}
		if (this.eyes.equals(((Client) client).getEyes())) {
			mark++;
		}
		if (this.birthday.equals(((Client) client).getBirthday())) {
			mark++;
		}
		for (int i = 0; i < hobby.length; i++) {
			if (this.hobby[i].equals(((Client) client).getHobbies()[i])) {
				mark++;
			}
		}
		for (int i = 0; i < requirements.length; i++) {
			if (this.requirements[i].equals(
			        ((Client) client).getRequirements()[i])) {
				mark++;
			}
		}
		return mark == good ? true : false;
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
		if (regNum < 0) {
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
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString();
	}
}
