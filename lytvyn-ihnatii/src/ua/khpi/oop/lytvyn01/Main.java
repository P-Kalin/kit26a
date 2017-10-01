/**
* @file Main.java
*
* Файл, що містить у собі рішення поставлених завдань.
*
* @author student Lytvyn I.I. KIT-26A
* @date 06-Sep-2017
*/
package ua.khpi.oop.lytvyn01;

public class Main {

	/**
	 * Метод, що виконує поставлене завдання
	 */
	public static void main(String[] args) {
		/* Номер залікової книжки у вигляді шістнадцяткового літералу */
		String firstNumber = "3BF7";
		/* Номер мобільного телефона у вигляді десяткового літералу */
		long secondNumber = 380689978088L;
		/*
		 * Останні дві ненульові цифри номера мобільного телефону у вигляді
		 * двійкового літералу
		 */
		int thirdNumber = 1011000;
		/*
		 * Останні чотири ненульові цифри номера мобільного телефону у вигляді
		 * вісімкового літералу
		 */
		short fourthNumber = 17630;
		/*
		 * Збільшене на одиницю значення залишку від ділення на 26 зменшеного на
		 * одиницю номера студента в журналі групи
		 */
		byte fifthNumber = 3;
		/*
		 * Символ англійського алфавіту в верхньому регістрі, номер якого
		 * відповідає знайденому раніше значенню
		 */
		@SuppressWarnings("unused")
		final char symbol = 'C';

		/* Конвертація третього числа у строку */
		String thirdTempNumber = Integer.toString(thirdNumber);
		/* Конвертація четвертого числа у строку */
		String fourthTempNumber = Short.toString(fourthNumber);

		/*
		 * Підрахунок кількості парних і непарних цифр
		 */
		countDigits(Integer.parseInt(firstNumber, 16));
		countDigits(secondNumber);
		countDigits(Integer.parseInt(thirdTempNumber, 2));
		countDigits(Integer.parseInt(fourthTempNumber, 8));
		countDigits(fifthNumber);

		/*
		 * Підрахунок кількості одиниць
		 */
		countOnes(Integer.toBinaryString(Integer.parseInt(firstNumber, 16)));
		countOnes(Long.toBinaryString(secondNumber));
		countOnes(thirdTempNumber);
		countOnes(
		        Integer.toBinaryString(Integer.parseInt(fourthTempNumber, 8)));
		countOnes(Integer.toBinaryString(fifthNumber));
	}

	/**
	 * Метод, що виконує підрахунок кількості парних і непарних цифр
	 * 
	 * @param number
	 *            десятковий запис цілочисельного значення кожної змінної
	 */
	@SuppressWarnings("unused")
	static void countDigits(long number) {
		long rest = 0; // Для збереження залишку
		long buffer = number; // Зберігає поточне значення числа
		int evenCounter = 0; // Лічильник парних цифр
		int oddCounter = 0; // Лічильник непарних цифр
		while (buffer != 0) {
			rest = buffer % 10;
			buffer = buffer / 10;
			if (rest % 2 == 0) { // Якщо парне
				evenCounter++;
			} else { // Якщо непарне
				oddCounter++;
			}
		}
	}

	/**
	 * Метод, що виконує підрахунок кількості одиниць
	 * 
	 * @param str
	 *            двійковий запис цілочисельного значення кожної змінної
	 */
	@SuppressWarnings("unused")
	static void countOnes(String str) {
		int result = 0;
		/*
		 * Підрахунок кількості одиниць
		 */
		for (char element : str.toCharArray()) {
			if (element == '1') {
				result++;
			}
		}
	}
}