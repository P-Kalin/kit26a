package ua.khpi.oop.pavlova05;

import java.io.Serializable;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class NewContainerOfStrings implements Iterable<String>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
	private static final int DEFAULT_CAPACITY = 10;
	private static final String[] EMPTY_ELEMENT_DATA = {};
	private int size;
	transient String[] elementData;

	public NewContainerOfStrings() {
		this.elementData = EMPTY_ELEMENT_DATA;
	}

	public NewContainerOfStrings(int initialCapacity) {
		if (initialCapacity < 0)
			throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
		else if (initialCapacity == 0) {
			this.elementData = EMPTY_ELEMENT_DATA;
		} else {
			this.elementData = new String[initialCapacity];
		}
	}

	private void removeAndRefactor(int index) {
		int numMoved = size - index - 1;
		if (numMoved > 0)
			System.arraycopy(elementData, index + 1, elementData, index, numMoved);
		elementData[--size] = null;
	}

	private void checkIndex(int index) {
		if (index >= size)
			throw new IndexOutOfBoundsException(indexExceptionMessage(index));
	}

	private String indexExceptionMessage(int index) {
		return "Index: " + index + ", Size: " + size;
	}

	private void checkDefaultCapacityEnsuring(int newCapacity) {
		if (elementData == EMPTY_ELEMENT_DATA)
			newCapacity = Math.max(DEFAULT_CAPACITY, newCapacity);
		checkPermittedCapasityEnsuring(newCapacity);
	}

	private void checkPermittedCapasityEnsuring(int newCapacity) {
		if (newCapacity - elementData.length > 0)
			expandCapacity(newCapacity);
	}

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

	private int tooLargeCapacity(int newCapacity) {
		if (newCapacity < 0)
			throw new OutOfMemoryError();
		return (newCapacity > MAX_ARRAY_SIZE) ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
	}

	public String toString() {
		String containerContent = new String();
		for (String string : elementData)
			containerContent += string;
		return containerContent;
	}

	public void add(String newElement) {
		if (size == elementData.length)
			ensureCapacity(size + 1);
		elementData[size] = newElement;
		size++;
	}

	public void clear() {
		for (int i = 0; i < elementData.length; i++) {
			elementData[i] = null;
		}
	}

	public void remove(int index) {
		checkIndex(index);
		elementData[index] = null;
		removeAndRefactor(index);
	}

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

	public Object[] toArray() {
		Object[] copy = new Object[size];
		for (int index = 0; index < size; index++)
			copy[index] = elementData[index];
		return copy;
	}

	public int size() {
		return size;
	}

	public boolean contains(String string) {
		return indexOf(string) >= 0;
	}

	public boolean containsAll(NewContainerOfStrings container) {
		for (String e : container)
			if (!contains(e))
				return false;
		return true;
	}

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

	public void ensureCapacity(int newCapacity) {
		checkDefaultCapacityEnsuring(newCapacity);
	}

	public String get(int index) {
		return elementData[index];
	}

	public newIterator<String> iterator() {
		return new newIterator<String>(elementData);
	}

	@SuppressWarnings("hiding")
	public class newIterator<String> implements Iterator<String> {
		private int cursor;
		private int end;

		public newIterator(String[] array) {
			this.cursor = -1;
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
