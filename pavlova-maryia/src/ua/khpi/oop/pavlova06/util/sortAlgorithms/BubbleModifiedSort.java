package ua.khpi.oop.pavlova06.util.sortAlgorithms;

import ua.khpi.oop.pavlova06.NewContainerOfStrings;

/**
 * Class <b>BubbleModifiedSort</b> contains the realization of modified bubble
 * sorting algorithm. </br>
 * The essence of bubble sort is in comparing two 'neighbours' in the array. The
 * modification's essence is in stop parameter, that enables to compare elements
 * only if its value is <i>true</i>
 * 
 * @author pavlova-mv
 *
 */
public class BubbleModifiedSort extends ForSort implements SortAbstract {

	@Override
	public void sort(NewContainerOfStrings array, boolean parameter) {
		boolean pr = true;
		do {
			pr = false;
			for (int j = 0; j < array.size(); j++) {
				if (comparator(array, j, j + 1, parameter))
					pr = true;
			}

		} while (pr);

	}

}
