package ua.khpi.oop.pavlova05;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

/**
 * Class <b>ContainerOfStrings</b> contains a container for solving the task
 * with a string array
 * 
 * @param size
 *            means the size of the container
 * @param DEFAULT_CAPACITY
 *            means that a new container will have this capacity in a default
 *            setup
 * @param EMPTY_ELEMENT_DATA
 *            means that in a default setup this container includes such a type
 *            of data
 * @param MAX_ARRAY_SIZE
 *            means that`s the largest value of the array`s size
 * @param elementData
 *            is an input data
 * @author pavlova-mv
 *
 */
public class ContainerOfStrings implements Iterable<String> {

	private int size;
	private static final int DEFAULT_CAPACITY = 10;
	private static final String[] EMPTY_ELEMENT_DATA = {};
	private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
	transient String[] elementData;

	/**
	 * Default constructor for <b>ContainerOfStrings</b>. Creates an object by
	 * <i>EMPTY_ELEMENT_DATA</i> parameter.
	 */
	public ContainerOfStrings() {
		this.elementData = EMPTY_ELEMENT_DATA;
	}

	/**
	 * Constructor with parameters for <b>ContainerOfStrings</b>. Creates an object
	 * with input <i>initialCapacity</i>. Data field is filled with
	 * <i>EMPTY_ELEMENT_DATA</i> if <i>initialCapacity</i> has a null value.
	 * 
	 * @param initialCapacity
	 *            is an input value for creating a new object of ContainerOfStrings.
	 */
	public ContainerOfStrings(int initialCapacity) {
		if (initialCapacity > 0) {
			this.elementData = new String[initialCapacity];
		} else if (initialCapacity == 0) {
			this.elementData = EMPTY_ELEMENT_DATA;
		} else {
			throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
		}
	}

	/**
	 * Method <b>ensureCapacity</b> ensures the exsisting value of capacity of the
	 * object. Ensures capacity to the input value <i>minCapacity</i>.
	 * <i>MinCapacity</i> cannot be smaller than <i>DEFAULT_CAPACITY</i> when
	 * container is empty. In order if <i>minCapacity</i> is bigger than container's
	 * data length, new capacity assigns value of <i>minCapacity</i>. Else if
	 * <i>minCapacity</i> is larger than <i>MAX_ARRAY_SIZE</i>, method ensures
	 * capacity to the <i>MAX_ARRAY_SIZE</i> value.
	 * 
	 * 
	 * @param minCapacity
	 *            is an input value for ensuring the container's capacity to this
	 *            value
	 */
	private void ensureCapacity(int minCapacity) {
		if (elementData == EMPTY_ELEMENT_DATA) {
			minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
		}
		if (minCapacity - elementData.length > 0) {
			int newCapacity = minCapacity;
			if (newCapacity - MAX_ARRAY_SIZE > 0) {
				if (minCapacity < 0)
					throw new OutOfMemoryError();
				newCapacity = MAX_ARRAY_SIZE;
			}
			String[] newData = new String[minCapacity];
			for (int i = 0; i < size - 1; i++) {
				newData[i] = elementData[i];
			}
			// System.arraycopy(elementData, 0, newData, 0, size);
			elementData = newData;
		}

	}

	/**
	 * Method <b>toString</b> converts an object of <b>ContainerOfStrings</b> to a
	 * string.
	 */
	public String toString() {
		String containerContent = new String();
		for (String string : elementData)
			containerContent += string;
		return containerContent;
	}

	/**
	 * Method <b>add</b> writes a new one string to the container. The capacity
	 * increases while adding a new string.
	 * 
	 * @param string
	 *            is a new one line in a container
	 */
	public void add(String string) {
		ensureCapacity(size++);
		elementData[size++] = string;
	}

	/**
	 * Method <b>clear</b> performs the removal of all the elements in the
	 * container. Capacity is saved, but the size decreases to null value.
	 */
	public void clear() {
		for (int i = 0; i < size; i++)
			elementData[i] = null;

		size = 0;
	}

	/**
	 * Method <b>remove</b> performs the removal of the concreate string in the
	 * container. If this string is absent the false value returns.
	 * 
	 * @param string
	 *            is a string that has to be removed from the container
	 * @return true/false value
	 */
	public boolean remove(String string) {
		for (int i = 0; i < elementData.length; i++) {
			if (string.equals(elementData[i])) {
				int removed = elementData.length - i - 1;
				if (removed > 0) {
					System.arraycopy(elementData, i + 1, elementData, i, removed);
				}
				elementData[size - 1] = null;
				return true;
			}
		}
		return false;
	}

	/**
	 * Method <b>toArray</b> creates an array of <b>Object</b> type from the
	 * container.
	 * 
	 * @return an array of objects
	 */
	public Object[] toArray() {
		return copy();
	}

	/**
	 * Method <b>copy</b> is an accessory one. Creates a copy of an array of strings
	 * in the object format.
	 * 
	 * @return a copy of an array
	 */
	public Object[] copy() {
		Object[] copy = (String[]) new Object[size];
		System.arraycopy(elementData, 0, copy, 0, Math.min(elementData.length, size));
		return copy;
	}

	/**
	 * Method <b>size</b> gets the value of container's size
	 * 
	 * @return size of the container
	 */
	public int size() {
		return size;
	}

	/**
	 * Method <b>contains</b> performs the check-up of availability of a string in
	 * the container. If it exists - returns true, else - false.
	 * 
	 * @param string
	 *            a line for the check-up
	 * @return true/false value
	 */
	public boolean contains(String string) {
		int temp = 0;
		if (string == null) {
			for (int i = 0; i < size; i++)
				if (elementData[i] == null)
					temp = i;
		} else {
			for (int i = 0; i < size; i++)
				if (string.equals(elementData[i]))
					temp = i;
		}
		temp = -1;
		return temp >= 0;
	}

	/**
	 * Method <b>containsAll</b> performs the check-up of availability of an object
	 * in the container. It checks if an another container exists in this container.
	 * 
	 * @param container
	 *            a container for check-up
	 * @return true/false value
	 */
	public boolean containsAll(ContainerOfStrings container) {
		return true;
	}

	/**
	 * Method <b>get</b> returns data by the index
	 * 
	 * @param index
	 *            the number of the string to return
	 * @return string by index
	 */
	public String get(int index) {
		return elementData[index];
	}

	/**
	 * Method <b>iterator</b> returns a new iterator for the container
	 */
	public IteratorForContainer<String> iterator() {
		return new IteratorForContainer<String>(elementData);
	}

	/**
	 * <b>IteratorForContainer</b>
	 * 
	 * @author pavlova-mv
	 *
	 * @param <String>
	 */
	@SuppressWarnings("hiding")
	class IteratorForContainer<String> implements java.util.Iterator<String> {

		private int cursor;
		private int end;

		public IteratorForContainer(String[] array) {
			this.cursor = 0;
			this.end = array.length - 1;
		}

		@Override
		public boolean hasNext() {
			return this.cursor < end;
		}

		@SuppressWarnings("unchecked")
		@Override
		public String next() {
			if (!this.hasNext()) {
				throw new NoSuchElementException();
			}
			cursor++;
			return (String) elementData[cursor];
		}

		public void remove() {
			if (end == -1)
				throw new IllegalStateException();
			try {
				ContainerOfStrings.this.remove(elementData[end]);
				if (end < cursor)
					cursor--;
				end = -1;
			} catch (IndexOutOfBoundsException one) {
				throw new ConcurrentModificationException();
			}
		}

	}
}