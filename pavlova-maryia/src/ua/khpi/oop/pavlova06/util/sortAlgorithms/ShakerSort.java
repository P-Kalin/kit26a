package ua.khpi.oop.pavlova06.util.sortAlgorithms;

import ua.khpi.oop.pavlova06.NewContainerOfStrings;

/**
 * Class <b>ShakerSort</b> contains the realization of Shaker sorting algorithm.
 * is a variation of bubble sort that is both a stable sorting algorithm and a
 * comparison sort. The algorithm differs from a bubble sort in that it sorts in
 * both directions on each pass through the list. This sorting algorithm is only
 * marginally more difficult to implement than a bubble sort, and solves the
 * problem of turtles in bubble sorts. It provides only marginal performance
 * improvements, and does not improve asymptotic performance; like the bubble
 * sort, it is not of practical interest (insertion sort is preferred for simple
 * sorts), though it finds some use in education.
 * 
 * @author pavlova-mv
 * 
 *
 */
public class ShakerSort extends ForSort implements SortAbstract {

	@Override
	public void sort(NewContainerOfStrings array, boolean parameter) {
		int left = 0;
		int right = array.size() - 1;
		int flag = -1;
		while ((left < right) && (flag > 0)) {
			flag = 0;
			for (int i = left; i < right; i++) {
				if (comparator(array, i, i + 1, parameter))
					flag = 1;
			}
			right--;
			for (int i = right; i > left; i--) {
				if (comparator(array, i - 1, i, parameter))
					flag = 1;
			}
			left++;
		}
	}

}
