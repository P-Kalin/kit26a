package ua.khpi.oop.lytvyn16.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import ua.khpi.oop.lytvyn16.util.LocalDateAdapter;

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
	private StringProperty gender;

	/**
	 * Реєстраційний номер
	 */
	private IntegerProperty regNum;

	/**
	 * Дата реєстрації
	 */
	private ObjectProperty<LocalDate> regDate;

	/**
	 * Ім'я
	 */
	private StringProperty name;

	/**
	 * Зріст
	 */
	private IntegerProperty height;

	/**
	 * Колір очей
	 */
	private StringProperty eyes;

	/**
	 * День народження
	 */
	private ObjectProperty<LocalDate> birthday;

	/**
	 * Хобі
	 */
	private StringProperty hobby;

	/**
	 * Вимоги до партнера
	 */
	private StringProperty requirements;

	/**
	 * Конструктор за замовчуванням
	 */
	public Client() {
	}

	/**
	 * @param regNum
	 */
	public Client(int regNum) {
		this.regNum = new SimpleIntegerProperty(regNum);

		this.name = new SimpleStringProperty("Хтось");
		this.gender = new SimpleStringProperty("Хтось");
		this.eyes = new SimpleStringProperty("Якісь");
		this.height = new SimpleIntegerProperty(180);
		this.hobby = new SimpleStringProperty("Щось");
		this.requirements = new SimpleStringProperty("Щось");
		this.regDate = new SimpleObjectProperty<>(
		        LocalDate.of(2017, 12, 31));
		this.birthday = new SimpleObjectProperty<>(
		        LocalDate.of(1999, 1, 1));
	}

	public ObjectProperty<LocalDate> birthday() {
		return birthday;
	}

	public StringProperty eyes() {
		return eyes;
	}

	public StringProperty gender() {
		return gender;
	}

	/**
	 * @return the birthday
	 */
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	public LocalDate getBirthday() {
		return birthday.get();
	}

	/**
	 * @return the eyes
	 */
	public String getEyes() {
		return eyes.get();
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender.get();
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height.get();
	}

	/**
	 * @return the hobby
	 */
	public String getHobby() {
		return hobby.get();
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name.get();
	}

	/**
	 * @return the regDate
	 */
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	public LocalDate getRegDate() {
		return regDate.get();
	}

	/**
	 * @return the regNum
	 */
	public int getRegNum() {
		return regNum.get();
	}

	/**
	 * @return the requirements
	 */
	public String getRequirements() {
		return requirements.get();
	}

	public IntegerProperty height() {
		return height;
	}

	public StringProperty hobby() {
		return hobby;
	}

	public StringProperty name() {
		return name;
	}

	public ObjectProperty<LocalDate> regDate() {
		return regDate;
	}

	public IntegerProperty regNum() {
		return regNum;
	}

	public StringProperty requirements() {
		return requirements;
	}

	/**
	 * @param birthday
	 *            the birthday to set
	 */
	public void setBirthday(LocalDate birthday) {
		this.birthday.set(birthday);
	}

	/**
	 * @param eyes
	 *            the eyes to set
	 */
	public void setEyes(String eyes) {
		this.eyes.set(eyes);
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(String gender) {
		this.gender.set(gender);
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(int height) {
		this.height.set(height);
	}

	/**
	 * @param hobby
	 *            the hobby to set
	 */
	public void setHobby(String hobby) {
		this.hobby.set(hobby);
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name.set(name);
	}

	/**
	 * @param regDate
	 *            the regDate to set
	 */
	public void setRegDate(LocalDate regDate) {
		this.regDate.set(regDate);
	}

	/**
	 * @param regNum
	 *            the regNum to set
	 */
	public void setRegNum(int regNum) {
		this.regNum.set(regNum);
	}

	/**
	 * @param requirements
	 *            the requirements to set
	 */
	public void setRequirements(String requirements) {
		this.requirements.set(requirements);
	}

}
