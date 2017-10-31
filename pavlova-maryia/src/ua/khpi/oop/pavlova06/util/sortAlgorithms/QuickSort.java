package ua.khpi.oop.pavlova06.util.sortAlgorithms;

import ua.khpi.oop.pavlova06.NewContainerOfStrings;

/**
 * Class <b>QuickSort</b> contains the realization of the quick sort algorithm.
 * Quicksort is a comparison sort, meaning that it can sort items of any type
 * for which a "less-than" relation (formally, a total order) is defined. In
 * efficient implementations it is not a stable sort, meaning that the relative
 * order of equal sort items is not preserved. Quicksort can operate in-place on
 * an array, requiring small additional amounts of memory to perform the
 * sorting.
 * 
 * @author pavlova-mv
 *
 */
public class QuickSort extends ForSort implements SortAbstract {
	@Override
	public void sort(NewContainerOfStrings array, boolean parameter) {
		int startIndex = 0;
		int endIndex = array.size() - 1;
		doSort(array, startIndex, endIndex, parameter);
	}

	private static void doSort(NewContainerOfStrings array, int start, int end, boolean parameter) {
		if (start >= end)
			return;
		int i = start, j = end;
		int cur = i - (i - j) / 2;
		if (parameter) {
			while (i < j) {
				while (i < cur && (array.get(i).length() <= array.get(cur).length())) {
					i++;
				}
				while (j > cur && (array.get(cur).length() <= array.get(j).length())) {
					j--;
				}
				if (i < j) {
					if (i == cur)
						cur = j;
					else if (j == cur)
						cur = i;
				}
			}
		} else if (!parameter) {
			while (i < j) {
				while (i < cur && (array.get(i).length() >= array.get(cur).length())) {
					i++;
				}
				while (j > cur && (array.get(cur).length() >= array.get(j).length())) {
					j--;
				}
				if (i < j) {
					if (i == cur)
						cur = j;
					else if (j == cur)
						cur = i;
				}
			}
		}
		doSort(array, start, cur, parameter);
		doSort(array, cur + 1, end, parameter);
	}
}
