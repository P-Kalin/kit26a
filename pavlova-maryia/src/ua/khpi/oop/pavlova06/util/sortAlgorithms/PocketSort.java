package ua.khpi.oop.pavlova06.util.sortAlgorithms;

import ua.khpi.oop.pavlova06.NewContainerOfStrings;

/**
 * Class <b>PocketSort</b> contains the realization of the pocket sorting
 * algorithm.
 * 
 * @author Lenovo
 *
 */
public class PocketSort extends ForSort implements SortAbstract {

	@Override
	public void sort(NewContainerOfStrings array, boolean parameter) {
		for (int i = 0; i < array.size(); i++)
			while (array.get(i).length() != i)
				comparator(array, i, array.getIndex(array.get(i)), parameter);
	}

}
