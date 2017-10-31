package ua.khpi.oop.pavlova06.util.sortAlgorithms;

import ua.khpi.oop.pavlova06.NewContainerOfStrings;

/**
 * Class ForSort is an util class for help functions for sorting. It contains:
 * </br>
 * <i>swap</i> is for elements exchange;</br>
 * <i>comparator</i> for the choosing the way of sorting;</br>
 * <i>compareToBig</i> for sorting from the smallest element to the
 * biggest;</br>
 * <i>compareToSmall</i> for sorting from the biggest element to the
 * smallest.</br>
 * 
 * @author pavlova-mv
 *
 */
public class ForSort {
	/**
	 * Method <b>swap</b> exchanges two elements in array.
	 * 
	 * @param array
	 *            is the container for sorting
	 * @param left
	 *            index of the left element
	 * @param right
	 *            index of the right element
	 */
	public final static void swap(NewContainerOfStrings array, int left, int right) {
		final String temp = array.get(left);
		array.set(left, array.get(right));
		array.set(right, temp);
	}

	/**
	 * Method <b>compareToBig</b> is the comparator to sort elements from the
	 * smallest element to the biggest.
	 * 
	 * @param array
	 *            is the container for sorting
	 * @param left
	 *            is index of the left element
	 * @param right
	 *            index of the right element
	 */
	private final boolean compareToBig(NewContainerOfStrings array, int left, int right) {
		if (array.get(left).length() > array.get(left).length()) {
			swap(array, left, right);
			return true;
		}
		return false;
	}

	/**
	 * Method <b>compareToSmall</b> is the comparator to sort elements from the
	 * biggest element to the smallest.
	 * 
	 * @param array
	 *            is the container for sorting
	 * @param left
	 *            is index of the left element
	 * @param right
	 *            index of the right element
	 */
	private final boolean compareToSmall(NewContainerOfStrings array, int left, int right) {
		if (array.get(left).length() < array.get(left).length()) {
			swap(array, left, right);
			return true;
		}
		return false;
	}

	/**
	 * Method <b>comparator</b> is the method to sort elements in the array in one
	 * of two ways:</br>
	 * ~ from the smallest element to the biggest; </br>
	 * ~ from the biggest element to the smallest; </br>
	 * 
	 * @param array
	 *            is the container for sorting
	 * @param left
	 *            is index of the left element
	 * @param right
	 *            index of the right element
	 * @param param
	 *            is for choosing the way of sorting
	 */
	public final boolean comparator(NewContainerOfStrings array, int left, int right, boolean param) {
		if (param == true)
			return compareToBig(array, left, right);
		if (param == false)
			return compareToSmall(array, left, right);
		return false;
	}
}
