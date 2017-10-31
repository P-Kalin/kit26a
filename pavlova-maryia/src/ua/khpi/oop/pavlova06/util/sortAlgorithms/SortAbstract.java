package ua.khpi.oop.pavlova06.util.sortAlgorithms;

import ua.khpi.oop.pavlova06.NewContainerOfStrings;

/**
 * Interface SortAbstract contains two methods for each type of sorting.</br>
 * <i>sort</i> is the main method for each sort algorithm realization. </br>
 *
 * 
 * @author pavlova-mv
 * @see ForSort
 */
public interface SortAbstract {
	/**
	 * Method <b>sort</b> is the abstract method for each sort algorithm realization
	 * 
	 * @param array
	 *            is the container for sorting
	 * @param parameter
	 *            is for choosing the way of sorting
	 */
	public void sort(NewContainerOfStrings array, boolean parameter);

}
