package ua.khpi.oop.pavlova06.util.sortAlgorithms;

import ua.khpi.oop.pavlova06.NewContainerOfStrings;

/**
 * Class <b>BubbleClassicSort</b> contains the realization of classic algorithm
 * of bubble sort. </br>
 * The essence of bubble sort is in comparing two 'neighbours' in the array.
 * </br>
 * In each step one elements gets its place.
 * 
 * @author pavlova-mv
 * @see SortAbstract
 * @see ForSort
 *
 */
public class BubbleClassicSort extends ForSort implements SortAbstract {

	@Override
	public void sort(NewContainerOfStrings array, boolean parameter) {
		for (int i = 0; i < array.size(); i++)
			for (int j = 0; j < array.size(); j++)
				comparator(array, j, j + 1, parameter);
	}
}
