package ua.khpi.oop.pavlova06.util.sortAlgorithms;

import ua.khpi.oop.pavlova06.NewContainerOfStrings;

/**
 * Class <b>ExchangeSampleSort</b> contains the realization of the sortinf
 * algorithm with the exchange simple sample. Is one of the Sample sorting
 * algorithms.
 * 
 * @author pavlova-mv
 *
 */
public class ExchangeSampleSort extends ForSort implements SortAbstract {

	@Override
	public void sort(NewContainerOfStrings array, boolean parameter) {
		for (int i = 0; i < array.size(); i++) {
			int m = i;
			for (int j = 0; j < array.size(); j++) {
				if (parameter) {
					if (array.get(m).length() > array.get(j).length())
						m = j;
				} else if (!parameter) {
					if (array.get(m).length() < array.get(j).length())
						m = j;
				}

				if (m != i)
					swap(array, m, i);
			}
		}

	}

}
