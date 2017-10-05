package ua.khpi.oop.pavlova01;

public final class Main {
	private Main() {
	}

	public static void main(final String[] args) {

		/**
		 * Завдання №1
		 */
		/**
		 * Число, що відповідає номеру залікової книжки за допомогою шістнадцяткового
		 * літералу
		 */
		int bookNum = 0x3BFD;
		/**
		 * Число, що відповідає номеру мобільного телефона (починаючи з 380...) за
		 * допомогою десяткового літералу.
		 */
		long phoneNum = 380508511449L;
		/**
		 * Число, яке складається з останніх двох ненульових цифр номера мобільного
		 * телефону за допомогою двійкового літералу.
		 */
		byte twoLast = 0B110001;
		/**
		 * Число, яке складається з останніх чотирьох ненульових цифр номера мобільного
		 * телефону за допомогою вісімкового літералу.
		 */
		short fourLast = 02651;
		/**
		 * Визначити збільшене на одиницю значення залишку від ділення на 26 зменшеного
		 * на одиницю номера студента в журналі групи.
		 */
		final byte MY_CONST_1 = 13;// порядковий номер у списку групи;
		final byte MY_CONST_2 = 26;// кількість літер;
		int code = (MY_CONST_2 - 1) % MY_CONST_1 + 1;
		/**
		 * Символ англійського алфавіту в верхньому регістрі, номер якого відповідає
		 * знайденому раніше значенню.
		 */
		char letter = (char) (code + 64);
		/**
		 * Переведення усіх значень у строки
		 */
		String str1 = Integer.toString(bookNum);
		String str2 = Long.toString(phoneNum);
		String str3 = Byte.toString(twoLast);
		String str4 = Short.toString(fourLast);
		String str5 = Integer.toString(code);
		taskTwo(str1, str2, str3, str4, str5);
		/**
		 * Переведення усіх значень у двійкову систему
		 */
		str1 = Integer.toBinaryString(bookNum);
		str2 = Long.toBinaryString(phoneNum);
		str3 = Integer.toBinaryString(twoLast);
		str4 = Integer.toBinaryString(fourLast);
		str5 = Integer.toBinaryString(code);
		taskThree(str1, str2, str3, str4, str5);
	}

	/**
	 * Функція для вирішення завдання №2
	 * 
	 * @param strInt
	 *            Строка зі значенням номеру залікової книжки типу Integer
	 * @param strLong
	 *            Строка зі значенням номеру телефона типу Long
	 * @param strByte
	 *            Строка зі значенням двох останніх ненульових цифр номеру телефона
	 *            типу Byte
	 * @param strShort
	 *            Строка зі значенням чотирьох останніх ненульових цифр номеру
	 *            телефона типу Short
	 * @param strCode
	 *            Строка зі значенням коду літери типу Integer
	 */
	private static void taskTwo(String strInt, String strLong, String strByte, String strShort, String strCode) {

		byte even = 0;// лічильник парних
		byte odd = 0;// лічильник непарних
		/**
		 * Обробка номеру залікової книжки
		 */
		countOddEven(odd, even, strInt);
		/**
		 * Обробка номеру телефона
		 */
		countOddEven(odd, even, strLong);
		/**
		 * Обробка останніх двох ненульових цифр номеру телефона
		 */
		countOddEven(odd, even, strByte);
		/**
		 * Обробка чотирьох останніх ненульових цифр номеру телефона
		 */
		countOddEven(odd, even, strShort);
		/**
		 * Обробка коду літери
		 */
		countOddEven(odd, even, strCode);
	}

	/**
	 * Функція для вирішення завдання №3
	 * 
	 * @param strInt
	 *            Строка зі значенням номеру залікової книжки у двійковій системі
	 *            числення
	 * @param strLong
	 *            Строка за зніченням номеру телефона у двійковій системі числення
	 * @param strByte
	 *            Строка зі значенням двох останніх ненульових цифр номеру телефона
	 *            у двійковій системі числення
	 * @param strShort
	 *            Строка за значенням чотирьох останніх ненульових цифр номеру
	 *            телефона у двійковій системі числення
	 * @param strCode
	 *            Строка зі значенням коду літери у двійковій системі числення
	 */
	private static void taskThree(String strInt, String strLong, String strByte, String strShort, String strCode) {
		/**
		 * Завдання №3
		 */
		byte count_0 = 0;// лічильник нулів
		byte count_1 = 0;// лічильник одиниць
		/**
		 * Обробка номеру залікової книжки
		 */
		countOneNull(count_0, count_1, strInt);
		/**
		 * Обробка номеру телефона
		 */
		countOneNull(count_0, count_1, strLong);
		/**
		 * Обробка останніх двох ненульових чисел номеру телефона
		 */
		countOneNull(count_0, count_1, strByte);
		/**
		 * Обробка останніх чотирьох ненульових чисел номеру телефона
		 */
		countOneNull(count_0, count_1, strShort);
		/**
		 * Обробка коду літери
		 */
		countOneNull(count_0, count_1, strCode);
	}

	/**
	 * Функція convertToString для переведення обраного елементу строки з типу char
	 * у string
	 * 
	 * @param temp
	 *            Строка, в якій проводиться пошук елементу за індексом
	 * @param index
	 *            Індекс
	 * @return Потрібний елемент строки
	 */
	private static String convertToString(String temp, int index) {
		char helper = temp.charAt(index);
		String pos = Character.toString(helper);
		return pos;
	}

	/**
	 * Функція countOddEven для підліку парних та непарних чисел
	 * 
	 * @param odd
	 *            Лічильник непарних
	 * @param even
	 *            Лічильник парних
	 * @param str
	 *            Строка зі значенням
	 */
	private static void countOddEven(byte odd, byte even, String str) {
		int numArr[] = new int[str.length()];
		for (int i = 0; i < str.length(); i++) {
			numArr[i] = Integer.parseInt(convertToString(str, i), 10);
			if ((numArr[i] % 2) == 0)// умова на парність
				even++;
			else {
				if ((numArr[i] % 1) == 0)
					odd++;
			}
		}
	}

	/**
	 * Функція countOneNull для підліку нулів та одиниць у значень в двійковій
	 * системі числення
	 * 
	 * @param count_0
	 *            Лічильник нулів
	 * @param count_1
	 *            Лічильник одиниць
	 * @param str
	 *            Строка зі значенням
	 */
	private static void countOneNull(byte count_0, byte count_1, String str) {
		int binary[] = new int[str.length()];
		for (int i = 0; i < str.length(); i++) {
			binary[i] = Integer.parseInt(convertToString(str, i), 10);
			if (binary[i] == 0)
				count_0++;
			else {
				if (binary[i] == 1)
					count_1++;
			}
		}
	}
}