package ua.khpi.oop.lytvyn06;

import java.util.Comparator;

/**
 * Забезпечує сортування масивів.
 * 
 * @author student Lytvyn I.I. KIT-26A
 *
 */
public class MergeSort {
	/**
	 * Масив для сортування
	 */
	private static String[] strings;
	/**
	 * Тимчасовий масив для сортування
	 */
	private static String[] temp;
	/**
	 * Довжина масиву
	 */
	private static int length;

	/**
	 * Виконує сортування отриманного масиву
	 * 
	 * @param array
	 *            масив, що буде відсортовано
	 * @param comparator
	 *            компоратор, що буде використовуватися при порівнянні
	 */
	public static void sort(String[] array,
	        Comparator<? super String> comparator) {
		strings = array;
		length = array.length;
		temp = new String[length];
		doMergeSort(0, length - 1, comparator);
	}

	/**
	 * Відсортовує масив елементів типу String за алгоритмом MergeSort
	 * 
	 * @param low
	 *            початковий індекс елементів масиву
	 * @param high
	 *            кінцевий індекс елементів масиву
	 * 
	 * @param comparator
	 *            компоратор, що буде використовуватися при порівнянні
	 */
	private static void doMergeSort(int low, int high,
	        Comparator<? super String> comparator) {
		/*
		 * Перевірка, чи початок менший, ніж кінець, якщо так, тоді масив
		 * сортується
		 */
		if (low < high) {
			/* Отримуємо середній індекс */
			int middle = low + (high - low) / 2;
			/* Сортуємо ліву частину */
			doMergeSort(low, middle, comparator);
			/* Сортуємо праву частину */
			doMergeSort(middle + 1, high, comparator);
			/* Сортуємо обидві частини */
			mergeParts(low, middle, high, comparator);
		}
	}

	/**
	 * Виконує сортування частин
	 * 
	 * @param low
	 *            початковий індекс елементів масиву
	 * @param middle
	 *            середній індекс елементів масиву
	 * @param high
	 *            кінцевий індекс елементів масиву
	 * @param comparator
	 *            компаратор для порівняння елементів
	 */
	private static void mergeParts(int low, int middle, int high,
	        Comparator<? super String> comparator) {
		/* Копіювання обох частин до допоміжного масиву */
		for (int i = low; i <= high; i++) {
			temp[i] = strings[i];
		}
		int i = low;
		int j = middle + 1;
		int k = low;
		/*
		 * Копіювання найменших значень зліва або праворуч назад до початкового
		 * масиву
		 */
		while (i <= middle && j <= high) {
			if (comparator.compare(temp[i], temp[j]) <= 0) {
				strings[k] = temp[i];
				i++;
			} else {
				strings[k] = temp[j];
				j++;
			}
			k++;
		}
		/*
		 * Копіювання решти частин лівої частини масиву в цільовий масив.
		 * Оскільки ми сортуємо на місці, будь-які залишкові елементи з правого
		 * боку вже знаходяться в правильному положенні.
		 */
		while (i <= middle) {
			strings[k] = temp[i];
			k++;
			i++;
		}
	}
}
