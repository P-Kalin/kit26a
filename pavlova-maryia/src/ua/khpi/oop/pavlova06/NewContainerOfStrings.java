package ua.khpi.oop.pavlova06;

import java.io.Serializable;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class includes the rrealization of a new container of strings for work with
 * the util from the laboratory work #3
 * 
 * @param MAX_ARRAY_SIZE
 *            is for capacity control of the container
 * @param DEFAULT_CAPACITY
 *            is for creating a container with a default capacity
 * @param EMPTY_ELEMENT_DATA
 *            is for creating a container with null value in a case of default
 *            usage
 * @param size
 *            is the size of the array of values
 * @param elementData
 *            is the array of strings that contains all the written in data
 * 
 * @author pavlova-mv
 *
 */
public class NewContainerOfStrings implements Iterable<String>, Serializable {

	private static final long serialVersionUID = 1L;
	private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
	private static final int DEFAULT_CAPACITY = 10;
	private static final String[] EMPTY_ELEMENT_DATA = {};
	private int size;
	transient String[] elementData;

	/**
	 * Default constructor for creating an empty container.
	 */
	public NewContainerOfStrings() {
		this.elementData = EMPTY_ELEMENT_DATA;
	}

	/**
	 * Constructor for creating a container with input capacity.
	 * 
	 * @param initialCapacity
	 *            is input capacity
	 */
	public NewContainerOfStrings(int initialCapacity) {
		if (initialCapacity < 0)
			throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
		else if (initialCapacity == 0) {
			this.elementData = EMPTY_ELEMENT_DATA;
		} else {
			this.elementData = new String[initialCapacity];
		}
	}

	/**
	 * Nethod for deleting a chosen element. Container is being refactored to shift
	 * all the element to right after the element that was removed.
	 * 
	 * @param index
	 *            is the number of the removing element
	 */
	private void removeAndRefactor(int index) {
		int numMoved = size - index - 1;
		if (numMoved > 0)
			System.arraycopy(elementData, index + 1, elementData, index, numMoved);
		elementData[--size] = null;
	}

	/**
	 * Method that controls index of the chosen element not to be out of the bound
	 * 
	 * @param index
	 *            is the number of the chosen element
	 */
	private void checkIndex(int index) {
		if (index >= size)
			throw new IndexOutOfBoundsException(indexExceptionMessage(index));
	}

	/**
	 * Method that creates a message for the exception view in a case index is out
	 * of bound.
	 * 
	 * @param index
	 *            is the number of the chosen element
	 * @return message
	 */
	private String indexExceptionMessage(int index) {
		return "Index: " + index + ", Size: " + size;
	}

	/**
	 * Method that checks the ensure of capacity
	 * 
	 * @param newCapacity
	 *            is a new value of capacity
	 */
	private void checkDefaultCapacityEnsuring(int newCapacity) {
		if (elementData == EMPTY_ELEMENT_DATA)
			newCapacity = Math.max(DEFAULT_CAPACITY, newCapacity);
		checkPermittedCapasityEnsuring(newCapacity);
	}

	/**
	 * Method for revising the value of a new capacity not to be smaller than the
	 * size of array of strings.
	 * 
	 * @param newCapacity
	 *            is a new value of capacity
	 */
	private void checkPermittedCapasityEnsuring(int newCapacity) {
		if (newCapacity - elementData.length > 0)
			expandCapacity(newCapacity);
	}

	/**
	 * Method that expands capacity to a chosen value.
	 * 
	 * @param newCapacity
	 *            is a new value of capacity
	 */
	private void expandCapacity(int newCapacity) {
		int currentCapacity = elementData.length;
		int expandedCapacity = currentCapacity + (currentCapacity >> 1);
		if (expandedCapacity - newCapacity < 0)
			expandedCapacity = newCapacity;
		if (expandedCapacity > MAX_ARRAY_SIZE)
			expandedCapacity = tooLargeCapacity(newCapacity);
		// throw new OutOfMemoryError();
		elementData = Arrays.copyOf(elementData, expandedCapacity);
	}

	/**
	 * Method for case if the value of new capacity is larger than the
	 * MAX_ARRAY_SIZE
	 * 
	 * @param newCapacity
	 *            is a new value of capacity
	 * @return value of new capacity
	 */
	private int tooLargeCapacity(int newCapacity) {
		if (newCapacity < 0)
			throw new OutOfMemoryError();
		return (newCapacity > MAX_ARRAY_SIZE) ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
	}

	/**
	 * Method for creating a string from the data of the container.
	 * 
	 * @return data in a string
	 */
	public String toString() {
		String containerContent = new String();
		for (String string : elementData)
			containerContent += string;
		return containerContent;
	}

	/**
	 * Method for adding a new element in the end of the array of strings.
	 * 
	 * @param newElement
	 *            is a new string
	 */
	public void add(String newElement) {
		if (size == elementData.length)
			ensureCapacity(size + 1);
		elementData[size] = newElement;
		size++;
	}

	/**
	 * Method for adding a new element by the specific index
	 * 
	 * @param index
	 *            is a position for adding
	 * @param newElement
	 */
	public void add(int index, String newElement) {
		checkIndex(index);
		ensureCapacity(size + 1);
		System.arraycopy(elementData, index, elementData, index + 1, size - index);

		elementData[index] = newElement;
		size++;
	}

	/**
	 * Method for removing all the data from the array of strings.
	 */
	public void clear() {
		for (int i = 0; i < elementData.length; i++) {
			elementData[i] = null;
		}
	}

	/**
	 * Method for removing an element by a specific index.
	 * 
	 * @param index
	 *            is the number of a chosen element
	 */
	public void remove(int index) {
		checkIndex(index);
		elementData[index] = null;
		removeAndRefactor(index);
	}

	/**
	 * Method for removing a specific element.
	 * 
	 * @param string
	 *            is a string for removing
	 * @return is removed?
	 */
	public boolean remove(String string) {
		if (string == null) {
			for (int index = 0; index < size; index++) {
				if (elementData[index] == null) {
					removeAndRefactor(index);
					return true;
				}
			}
		} else {
			for (int index = 0; index < size; index++)
				if (string.equals(elementData[index])) {
					removeAndRefactor(index);
					return true;
				}
		}
		return false;
	}

	/**
	 * Method for creating an array of objects by the array of strings.
	 * 
	 * @return array of objects
	 */
	public Object[] toArray() {
		Object[] copy = new Object[size];
		for (int index = 0; index < size; index++)
			copy[index] = elementData[index];
		return copy;
	}

	/**
	 * Method for getting the size of the array
	 * 
	 * @return the value of size
	 */
	public int size() {
		return size;
	}

	/**
	 * Method for revising if the specific element exists in this container.
	 * 
	 * @param string
	 *            is the element for revising
	 * @return is in the container?
	 */
	public boolean contains(String string) {
		return indexOf(string) >= 0;
	}

	/**
	 * Method for checking if all the elements of another container exist in this
	 * container.
	 * 
	 * @param container
	 *            is another container for revising
	 * @return is in the container?
	 */
	public boolean containsAll(NewContainerOfStrings container) {
		for (String e : container)
			if (!contains(e))
				return false;
		return true;
	}

	/**
	 * Method for getting the index of the specific element.
	 * 
	 * @param string
	 *            is the chosen element
	 * @return the number of the element
	 */
	public int indexOf(String string) {
		if (string == null) {
			for (int i = 0; i < size; i++)
				if (elementData[i] == null)
					return i;
		} else {
			for (int i = 0; i < size; i++)
				if (string.equals(elementData[i]))
					return i;
		}
		return -1;
	}

	/**
	 * Method for capacity ensuring by the customer.
	 * 
	 * @param newCapacity
	 *            the value of a new capacity
	 */
	public void ensureCapacity(int newCapacity) {
		checkDefaultCapacityEnsuring(newCapacity);
	}

	/**
	 * Method for getting the element by its' index
	 * 
	 * @param index
	 *            the number of the chosen element
	 * @return element by the index
	 */
	public String get(int index) {
		checkIndex(index);
		return elementData[index];
	}

	/**
	 * Method for creating the iterator
	 */
	public newIterator<String> iterator() {
		return new newIterator<String>(elementData);
	}

	/**
	 * New iterator for work with the NewContainerOfStrings
	 * 
	 * @author pavlova-mv
	 *
	 * @param <String>
	 */
	@SuppressWarnings("hiding")
	public class newIterator<String> implements Iterator<String> {
		private int cursor;
		private int end;

		/**
		 * Default constructor
		 * 
		 * @param array
		 */
		public newIterator(String[] array) {
			this.cursor = -1;
			this.end = array.length - 1;
		}

		/**
		 * Method for revising does the next element exist.
		 */
		@Override
		public boolean hasNext() {
			return this.cursor < end;
		}

		/**
		 * Method for getting the next element of the iterable array.
		 */
		@SuppressWarnings("unchecked")
		@Override
		public String next() {
			if (!this.hasNext()) {
				throw new NoSuchElementException();
			}
			cursor++;
			return (String) elementData[cursor];
		}

		/**
		 * Method for removing the last element of te array.
		 */
		public void remove() {
			if (end == -1)
				throw new IllegalStateException();
			try {
				NewContainerOfStrings.this.remove(elementData[end]);
				if (end < cursor)
					cursor--;
				end = -1;
			} catch (IndexOutOfBoundsException one) {
				throw new ConcurrentModificationException();
			}
		}
	}
}
