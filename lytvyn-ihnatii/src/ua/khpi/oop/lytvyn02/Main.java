/**
* @file Main.java
*
* Файл, що містить у собі рішення поставлених завдань.
*
* @author student Lytvyn I.I. KIT-26A
* @date 09-Sep-2017
*/
package ua.khpi.oop.lytvyn02;

import java.util.Random;

public class Main {

	/**
	 * Виконує поставлене завдання
	 */
	public static void main(String[] args) {
		System.out.format("  Число		Чи просте?%n%n");
		for (int i = 0; i < 21; i++) {
			findPrimeNumber(randInt());
		}
	}

	/**
	 * Повертає псевдо-рандомне число.
	 *
	 * @return Число типу Integer.
	 * @see java.util.Random#nextInt(int)
	 */
	public static int randInt() {
		Random rand = new Random();
		int randomNum = rand.nextInt(Integer.MAX_VALUE);
		return randomNum;
	}

	/**
	 * Перевіряє чи є число простим за допомогою наївного методу.
	 * принцип роботи: коли задане число n, перевірити чи якесь ціле m від 2 до n-1 ділить n.
	 * Якщо n ділиться на певне m, то n складене, в іншому разі воно просте.
	 * Замість перевірки всіх m до n-1, досить лише перевірити m до sqrt(n):
	 * якщо n складене, то його можна розкласти на два множники, принаймні
	 * один з яких не перевищує sqrt(n).
	 * Ефективність покращена, шлюхом пропуску всіх парних m , за винятком 2,
	 * бо коли якесь парне число ділить n, то 2 також ділить. 
	 *
	 * @param number Число для перевірки
	 * @return true - число просте | false - ні.
	 */
	public static boolean isPrimeNumber(int number) {
		if (number <= 1) {
			return false;
		}
		if (number == 2) {
			return true;
		}
		if (number % 2 != 0) {
			return true;
		}
		final double ceiledNumberSqrt = Math.ceil(Math.sqrt(number));
		for (long i = 3; i <= ceiledNumberSqrt; i += 2) {
			if (number % i == 0) {
				return false;
			}
		}
		return false;
	}

	/**
	 * Шукає прості числа.
	 *
	 * @param number
	 *            Число для перевірки.
	 */
	public static void findPrimeNumber(int number) {
		if (isPrimeNumber(number)) {
			System.out.format("%d	Так%n", number);
		} else {
			System.out.format("%d	Ні%n", number);
		}
	}
}
