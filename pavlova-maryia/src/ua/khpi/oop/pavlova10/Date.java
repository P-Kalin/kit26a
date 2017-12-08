package ua.khpi.oop.pavlova10;

import java.util.ArrayList;

public class Date {
	private int day;
	private int month;
	private int year;

	private static final int DEFAULT_DAY = 1;
	private static final int DEFAULT_MONTH = 1;
	private static final int DEFAULT_YEAR = 2010;

	public Date() {
		day = DEFAULT_DAY;
		month = DEFAULT_MONTH;
		year = DEFAULT_YEAR;
	}

	public Date(int inDay, int inMonth, int inYear) {
		day = inDay;
		month = inMonth;
		year = inYear;
	}

	public Date(Date date) {
		day = date.getDay();
		month = date.getMonth();
		year = date.getMonth();
	}

	public void setDay(int inDay) {
		day = inDay;
	}

	public void setMonth(int inMonth) {
		month = inMonth;
	}

	public void setYear(int inYear) {
		year = inYear;
	}

	public int getDay() {
		return day;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}

	public String toString() {
		String date = new String();
		date += getDay();
		date += "-";
		date += convertMonth();
		date += "-";
		date += getYear();
		return date;
	}

	private String convertMonth() {
		if (month == 1)
			return "Январь";
		else if (month == 2)
			return "Февраль";
		else if (month == 3)
			return "Март";
		else if (month == 4)
			return "Апрель";
		else if (month == 5)
			return "Май";
		else if (month == 6)
			return "Июнь";
		else if (month == 7)
			return "Июль";
		else if (month == 8)
			return "Август";
		else if (month == 9)
			return "Сентябрь";
		else if (month == 10)
			return "Октябрь";
		else if (month == 11)
			return "Ноябрь";
		else if (month == 12)
			return "Декабрь";
		else
			return "Nope";
	}

	public static Date getDifference(Date date1, Date date2) {
		return new Date(Math.abs(date1.getDay() - date2.getDay()), Math.abs(date1.getMonth() - date2.getMonth()),
				Math.abs(date1.getYear() - date2.getYear()));
	}

	public static int countDifference(Date date1, Date date2) {
		int counter = 0;
		ArrayList<ArrayList<Integer>> info = getInfo(date1, date2);
		int temp = info.get(2).size();
		if (temp != 0) {
			for (int i = 0; i < temp; i++) {
				counter += convertYearToDays(info.get(2).get(i));
			}
		}
		Date tempDate1 = new Date(date1);
		int tempCounter = tempDate1.getDay();
		tempDate1.setMonth(tempDate1.getMonth() - 1);
		while (tempDate1.getMonth() > 0) {
			tempCounter += convertMonthToDays(tempDate1);
			tempDate1.setMonth(tempDate1.getMonth() - 1);
		}
		counter = counter - tempCounter;

		Date tempDate2 = new Date(date2);
		tempCounter = tempDate2.getDay();
		tempDate2.setMonth(tempDate2.getMonth() - 1);
		while (tempDate2.getMonth() > 0) {
			tempCounter += convertMonthToDays(tempDate2);
			tempDate2.setMonth(tempDate2.getMonth() - 1);
		}
		counter = counter + tempCounter;
		return counter;
	}

	public static ArrayList<ArrayList<Integer>> getInfo(Date date1, Date date2) {
		ArrayList<Integer> days = countDays(date1, date2);
		ArrayList<Integer> months = countMonths(date1, date2);
		ArrayList<Integer> years = countYears(date1, date2);
		ArrayList<ArrayList<Integer>> info = new ArrayList<>();
		info.add(days);
		info.add(months);
		info.add(years);

		return info;
	}

	private static int convertYearToDays(int year) {
		if ((year % 4 == 0) || ((year % 4 == 0) && (year % 100 == 0) && (year % 400 == 0)))
			return 366;
		else
			return 365;
	}

	private static int convertMonthToDays(Date date) {
		if (date.getMonth() == 1 || date.getMonth() == 3 || date.getMonth() == 5 || date.getMonth() == 7
				|| date.getMonth() == 8 || date.getMonth() == 10 || date.getMonth() == 12)
			return 31;
		else if (date.getMonth() == 4 || date.getMonth() == 6 || date.getMonth() == 9 || date.getMonth() == 11)
			return 30;
		else if (date.getMonth() == 2 && convertYearToDays(date.getYear()) == 365)
			return 28;
		else if (date.getMonth() == 2 && convertYearToDays(date.getYear()) == 366)
			return 29;
		else
			return 30;
	}

	private static ArrayList<Integer> countYears(Date date1, Date date2) {
		ArrayList<Integer> years = new ArrayList<>();
		if (date2.getYear() - date1.getYear() == 0) {
			return years;
		} else if (date2.getYear() - date1.getYear() == 1) {
			years.add(date1.getYear());
		} else {
			int temp = date1.getYear();
			while (date2.getYear() - temp != 0) {
				years.add(temp);
				temp++;
			}
		}
		return years;
	}

	private static ArrayList<Integer> countDays(Date date1, Date date2) {
		ArrayList<Integer> days = new ArrayList<>();
		if (date1.getDay() < date2.getDay()) {
			int temp = date1.getDay();
			while (temp != date2.getDay()) {
				days.add(temp);
				temp++;
			}
		} else if (date1.getDay() > date2.getDay()) {
			int temp = date2.getDay();
			while (temp != 0) {
				days.add(temp);
				temp--;
			}
			temp = convertMonthToDays(date1);
			while (temp != date1.getDay()) {
				days.add(temp);
				temp--;
			}
		}
		return days;
	}

	private static ArrayList<Integer> countMonths(Date date1, Date date2) {
		ArrayList<Integer> months = new ArrayList<>();
		if (date1.getMonth() < date2.getMonth()) {
			int temp = date1.getMonth();
			while (temp != date2.getMonth()) {
				months.add(temp);
				temp++;
			}
		} else if (date1.getMonth() > date2.getMonth()) {
			int temp = date2.getMonth();
			while (temp != 0) {
				months.add(temp);
				temp--;
			}
			temp = 12;
			while (temp != date1.getMonth()) {
				months.add(temp);
				temp--;
			}
		}
		return months;
	}
}
