package ua.khpi.oop.pavlova05;

import java.util.Iterator;

public class ContainerOfStrings implements Iterable<String> {
	private int size;
	private static final int DEFAULT_CAPACITY = 10;
	private static final String[] EMPTY_ELEMENTDATA = {};
	private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
	transient String[] elementData;

	public ContainerOfStrings() {
		this.elementData = EMPTY_ELEMENTDATA;
	}

	public ContainerOfStrings(int initialCapacity) {
		if (initialCapacity > 0) {
			this.elementData = new String[initialCapacity];
		} else if (initialCapacity == 0) {
			this.elementData = EMPTY_ELEMENTDATA;
		} else {
			throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
		}
	}

	private void ensureCapacityInternal(int minCapacity) {
		if (elementData == EMPTY_ELEMENTDATA) {
			minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
		}
		ensureExplicitCapacity(minCapacity);
	}

	private void ensureExplicitCapacity(int minCapacity) {
		if (minCapacity - elementData.length > 0) {
			int oldCapacity = elementData.length;
			int newCapacity = oldCapacity + (oldCapacity >> 1);
			if (newCapacity - minCapacity < 0)
				newCapacity = minCapacity;
			if (newCapacity - MAX_ARRAY_SIZE > 0) {
				if (minCapacity < 0) // overflow
					throw new OutOfMemoryError();
				newCapacity = (minCapacity > MAX_ARRAY_SIZE) ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
			}
		}
	}

	public String toString() {
		String containerContent = new String();
		for (String string : elementData)
			containerContent += string;
		return containerContent;
	}

	public void add(String string) {
		ensureCapacityInternal(size++);
		elementData[size++] = string;
	}

	public void clear() {
		for (int i = 0; i < size; i++)
			elementData[i] = null;

		size = 0;
	}

	public static boolean remove(String string) {
		return true;
	}

	public Object[] toArray() {
		return copy(elementData, size);
	}

	public static Object[] copy(Object[] elementData, int size) {
		Object[] copy = (String[]) new Object[size];
		System.arraycopy(elementData, 0, copy, 0, Math.min(elementData.length, size));
		return copy;
	}

	public int size() {
		return size;
	}

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

	public boolean containsAll(ContainerOfStrings container) {
		return true;
	}

	public Iterator<String> iterator() {
		return new Iterator<String>(elementData);
	}

}
