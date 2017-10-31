package ua.khpi.oop.pavlova06.util.sortAlgorithms;

import ua.khpi.oop.pavlova06.NewContainerOfStrings;

/**
 * Class <b>InsertionSort</b> contains the realization of the sorting algorithm
 * with insertions. Insertion sort is a simple sorting algorithm that builds the
 * final sorted array (or list) one item at a time. It is much less efficient on
 * large lists than more advanced algorithms such as quicksort, heapsort.
 * However, insertion sort provides several advantages.
 * 
 * @author pavlova-mv
 * @see QuickSort
 * @see HeapSort
 *
 */
public class InsertionSort extends ForSort implements SortAbstract {

	@Override
	public void sort(NewContainerOfStrings array, boolean parameter) {
		NewContainerOfStrings temp = new NewContainerOfStrings(array);
		for (int i = 0; i < array.size(); i++) {
			int j = 0;
			while ((j < array.size()) && (temp.get(j).length() < array.get(i).length()))
				j++;
			for (int k = 0; k < j; k++)
				temp.set(k, temp.get(k + 1));
			temp.set(j, array.get(i));
		}
		array = new NewContainerOfStrings(temp);
	}

}
