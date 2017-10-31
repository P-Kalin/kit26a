package ua.khpi.oop.pavlova06.util.sortAlgorithms;

import ua.khpi.oop.pavlova06.NewContainerOfStrings;

/**
 * Class <b>SimpleSample</b> contains the realization of sorting algorithm with
 * the simple sample. Is one of the SampleSorting algorithms. Samplesort is a
 * sorting algorithm that is a divide and conquer algorithm often used in
 * parallel processing systems.
 * 
 * @author pavlova-mv
 *
 */
public class SimpleSample extends ForSort implements SortAbstract {

	@Override
	public void sort(NewContainerOfStrings array, boolean parameter) {
		boolean[] params = new boolean[array.size()];
		for (int k = 0; k < array.size(); k++)
			params[k] = true;
		for (int i = 0; i < array.size(); i++) {
			int m = i;
			for (int j = 0; j < array.size(); j++) {
				if (parameter) {
					if ((array.get(m).length() > array.get(j).length()) && params[j])
						m = j;
				} else if (!parameter) {
					if ((array.get(m).length() < array.get(j).length()) && params[j])
						m = j;
				}

				if (m != i) {
					array.set(m, array.get(i));
					params[m] = false;
				}
			}
		}

	}
}
