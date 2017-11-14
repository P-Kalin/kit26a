package ua.khpi.oop.pavlova09;

import java.util.Iterator;

public class LinkedList<H> implements Iterable<H> {

	private int size;
	private int capacity;
	private static final int DEFAULT_SIZE = 0;
	private static final int DEFAULT_CAPACITY = 10;

	private static final int MAX_LIST_CAPACITY = Integer.MAX_VALUE;
	private Element<H> head;

	public LinkedList() {
		this.head = null;

		this.size = DEFAULT_SIZE;
		this.capacity = DEFAULT_CAPACITY;
	}

	public LinkedList(int initialCapacity) {
		initialCapacity = checkInitialCapacity(initialCapacity);
		if (initialCapacity == 0) {
			this.head = null;
			this.head.prevElement = null;
			this.head.nextElement = null;
			this.size = initialCapacity;
		} else {
			head = null;
			this.head.prevElement = null;
			this.head.nextElement = null;
			size = initialCapacity;
		}
	}

	public LinkedList(Element<H> initial) {
		this.head = initial;
		this.size = 1;
	}

	public int getSize() {
		return size;
	}

	public void add(H newElement) {
		if (this.capacity - this.size < 1)
			ensureCapacity();
		this.size++;
		Element<H> toAdd = new Element<>();
		toAdd.setData(newElement);
		this.head.nextElement = toAdd;
		toAdd.prevElement = head;
	}

	public void add(int position, H newElement) {
		if (this.capacity - this.size < 1)
			ensureCapacity();
		this.size++;
		checkPosition(position);
		Element<H> toAdd = new Element<>();
		toAdd.prevElement = getElement(position - 1);
		toAdd.nextElement = getElement(position);
	}

	public void remove(int position) {
		checkPosition(position);
		Element<H> toDel = getElement(position);
		Element<H> next = toDel.nextElement;
		Element<H> prev = toDel.prevElement;
		prev.nextElement = next;
		next.prevElement = prev;
		toDel = null;
		this.size--;
	}

	public boolean remove(H extra) {
		Element<H> toDel = head;
		int i = 0;
		while (toDel.getData() != extra) {
			if (toDel.nextElement == null)
				return false;
			toDel = toDel.nextElement;
			i++;
		}
		remove(i);
		this.size--;
		return true;
	}

	public H get(int position) {
		checkPosition(position);
		return getElement(position).getData();

	}

	public boolean isEmpty() {
		if (head == null && size == 0)
			return true;
		else
			return false;
	}

	public void clear() {
		int i = this.size - 1;
		remove(i);
		while (!isEmpty()) {
			i--;
			remove(i);
		}

	}

	public Object[] toArray() {
		Object[] result = new Object[size];
		int i = 0;
		for (Element<H> x = head; x != null; x = x.nextElement)
			result[i++] = x.data;
		return result;
	}

	public String toString() {
		String dataInfo = new String();
		for (Element<H> x = head; x != null; x = x.nextElement)
			dataInfo += x.toString();

		return dataInfo;
	}

	public boolean contains(H toCheck) {
		Element<H> temp = head;

		while (temp.getData() != toCheck) {
			if (temp.nextElement == null)
				return false;
			temp = temp.nextElement;
		}
		return true;
	}

	private Element<H> getElement(int position) {
		Element<H> x = head;
		for (int i = 0; i < position; i++)
			x = x.nextElement;
		return x;

	}

	private int checkPosition(int position) {
		if (position < 0)
			return Math.abs(position);
		else if (position >= size)
			return size - 1;
		else
			return position;
	}

	private void ensureCapacity() {
		if (MAX_LIST_CAPACITY - this.capacity > 10)
			this.capacity = capacity + 10;
	}

	private static int checkInitialCapacity(int initialCapacity) {
		if (initialCapacity < 0)
			return Math.abs(initialCapacity);
		else if (initialCapacity > MAX_LIST_CAPACITY)
			return MAX_LIST_CAPACITY / 2;
		else if (initialCapacity < DEFAULT_CAPACITY)
			return DEFAULT_CAPACITY;
		else
			return initialCapacity;
	}

	static class Element<H> {
		private H data;
		Element<H> nextElement;
		Element<H> prevElement;

		/**
		 * @return the data
		 */
		public H getData() {
			return data;
		}

		/**
		 * @param data
		 *            the data to set
		 */
		public void setData(H data) {
			this.data = data;
		}

		/**
		 * @return the nextElement
		 */
		public Element<H> getNextElement() {
			return nextElement;
		}

		/**
		 * @param nextElement
		 *            the nextElement to set
		 */
		public void setNextElement(Element<H> nextElement) {
			this.nextElement = nextElement;
		}
	}

	@Override
	public Iterator<H> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
}
