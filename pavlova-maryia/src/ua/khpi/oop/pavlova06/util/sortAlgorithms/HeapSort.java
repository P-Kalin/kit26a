package ua.khpi.oop.pavlova06.util.sortAlgorithms;

import ua.khpi.oop.pavlova06.NewContainerOfStrings;

/**
 * Class <b>HeapSort</b> contains the realization of the sorting algorithm with
 * a heap.Heapsort can be thought of as an improved selection sort: like that
 * algorithm, it divides its input into a sorted and an unsorted region, and it
 * iteratively shrinks the unsorted region by extracting the largest element and
 * moving that to the sorted region. The improvement consists of the use of a
 * heap data structure rather than a linear-time search to find the maximum.
 * 
 * @author pavlvova-mv
 *
 */
public class HeapSort extends ForSort implements SortAbstract {

	private static int heapSize;

	@Override
	public void sort(NewContainerOfStrings array, boolean parameter) {
		buildHeap(array);
		while (heapSize > 1) {
			swap(array, 0, heapSize - 1);
			heapSize--;
			heapify(array, 0);
		}
	}

	/**
	 * Method <b>buildHeap</b> creates the heap
	 * 
	 * @param array
	 *            is the container for sorting
	 */
	private static void buildHeap(NewContainerOfStrings array) {
		heapSize = array.size();
		for (int i = array.size() / 2; i >= 0; i--)
			heapify(array, i);
	}

	/**
	 * Method <b>heapify</b> rearranges the heap subtree from the node
	 * 
	 * @param array
	 *            is the container for sorting
	 * @param i
	 *            root of the subtree for which the reordering occurs
	 */
	private static void heapify(NewContainerOfStrings array, int i) {
		int l = left(i);
		int r = right(i);
		int largest = i;
		if ((l < heapSize) && (array.get(i).length() < array.get(l).length())) {
			largest = l;
		}
		if ((r < heapSize) && (array.get(i).length() < array.get(r).length())) {
			largest = r;
		}
		if (i != largest) {
			swap(array, i, largest);
			heapify(array, largest);
		}
	}

	/**
	 * Method <b>right</b> returns the index of the right child of the current node
	 * 
	 * @param i
	 *            root of the subtree for which the reordering occurs
	 * @return the index of the right child of the current node
	 */
	private static int right(int i) {
		return 2 * i + 1;
	}

	/**
	 * Method <b>left</b> returns the index of the left child of the current node
	 * 
	 * @param i
	 *            root of the subtree for which the reordering occurs
	 * @return the index of the left child of the current node
	 */
	private static int left(int i) {
		return 2 * i + 2;
	}
}
