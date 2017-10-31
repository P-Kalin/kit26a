package ua.khpi.oop.pavlova06.util.sortAlgorithms;

import ua.khpi.oop.pavlova06.NewContainerOfStrings;

/**
 * Class <b>ShellSort</b> contains the realization of Shell sorting algorithm.
 * Shellsort, also known as Shell sort or Shell's method, is an in-place
 * comparison sort. It can be seen as either a generalization of sorting by
 * exchange (bubble sort) or sorting by insertion (insertion sort). The method
 * starts by sorting pairs of elements far apart from each other, then
 * progressively reducing the gap between elements to be compared. Starting with
 * far apart elements, it can move some out-of-place elements into position
 * faster than a simple nearest neighbor exchange. Donald Shell published the
 * first version of this sort in 1959. The running time of Shellsort is heavily
 * dependent on the gap sequence it uses. For many practical variants,
 * determining their time complexity remains an open problem.
 * 
 * @author pavlova-mv
 *
 */
public class ShellSort extends ForSort implements SortAbstract {

	@Override
	public void sort(NewContainerOfStrings array, boolean parameter) {
		int i, j, step;
		String temp;
		for (step = array.size() / 2; step > 0; step /= 2) {
			for (i = step; i < array.size(); i++) {
				temp = array.get(i);
				for (j = i; j >= step; j -= step) {
					if (parameter) {
						if (temp.length() < array.get(j - step).length())
							array.set(j, array.get(j - step));
						else
							break;
					} else if (!parameter) {
						if (temp.length() > array.get(j - step).length())
							array.set(j, array.get(j - step));
						else
							break;
					}
					array.set(j, temp);
				}
			}
		}

	}

}
