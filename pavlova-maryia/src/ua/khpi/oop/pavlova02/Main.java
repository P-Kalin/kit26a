package ua.khpi.oop.pavlova02;

import java.util.Random;

public final class Main {
	private Main() {
	}

	public static void main(final String[] args) {
		System.out.println("Лабораторна робота №2");
		System.out.print("Номер прикладної задачі: ");
		int number = (12 % 10) + 1;
		System.out.println(number);
		int value = 0;
		int size = 0;
		final Random random = new Random();
		System.out.println("№\tРандомне число\tНайбільша цифра числа\t  ");
		System.out.println("");
		for (int i = 1; i < 11; i++) {
			
			value = Integer.valueOf(Math.abs(random.nextInt()));
			size = getCountsOfDigits(value);
			System.out.println(i + "  \t" +   value + "  \t" +   findMax(value, size) + "  \t");
		}
	}
/**
 * Функція getCountsOfDigits необхідна для пошуку кількості розрядів в числі
 * @param number Число, в скому проводиться встановлення кількості розрядів
 * @return Кількість розрядів
 */
	public static int getCountsOfDigits(int number) {
		int count = (number == 0) ? 1 : 0;
		while (number != 0) {
			count++;
			number /= 10;
		}
		return count;
	}
/**
 * Функція findMax необхідна для пошуку максимального значення цифри у числі
 * @param value Власне число
 * @param size Кількість розрядів
 * @return Максимальне значення
 */
	public static int findMax(int value, int size) {
		int temp_1 = 0;
		int temp_2 = 0;
		for (int j = 0; j < size; j++) {
			temp_1 = value % 10;
			value /= 10;
			if (temp_2 < temp_1)
				temp_2 = temp_1;
		}
		return temp_2;
	}
}
