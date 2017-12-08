package ua.khpi.oop.pavlova10;

import java.util.Iterator;

public class LinkedList<H> implements Iterable<H> {

	private int size;
	private int capacity;
	private static final int DEFAULT_SIZE = 0;
	private static final int DEFAULT_CAPACITY = 10;

	private static final int MAX_LIST_CAPACITY = Integer.MAX_VALUE;
	private Element<H> head;
	private Element<H> tail;

	public LinkedList() {
		this.head = null;

		this.size = DEFAULT_SIZE;
		this.capacity = DEFAULT_CAPACITY;
	}

	public Element<H> getHead() {
		return head;
	}

	public void setHead(Element<H> el) {
		head = el;
	}

	public Element<H> getTail() {
		return tail;
	}

	public LinkedList(int initialCapacity) {
		initialCapacity = checkInitialCapacity(initialCapacity);
		if (initialCapacity == 0) {
			this.head = null;
			this.head.setPrevElement(null);
			;
			this.head.setNextElement(null);
			this.size = initialCapacity;
		} else {
			this.head = null;
			this.head.setPrevElement(null);
			;
			this.head.setNextElement(null);
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
		if (capacity - size < 2)
			ensureCapacity();
		// if (isEmpty())
		// linkFirst(newElement);
		// else
		linkLast(newElement);

	}

	private void linkLast(H newElement) {
		final Element<H> l = tail;
		final Element<H> newNode = new Element<H>(l, newElement, null);
		tail = newNode;
		if (l == null && head == null)
			head = newNode;
		else if (l == null && head != null)
			head.nextElement = newNode;
		else
			l.nextElement = newNode;
		size++;
	}

	private void link(int position, H newElement) {
		if (getElement(position) == head) {

			final Element<H> oldHead = head;
			final Element<H> newH = new Element<>(null, newElement, oldHead);
			if (oldHead == null) {
				head = newH;
				head.nextElement = null;
				tail = newH;
				tail.prevElement = head;
			} else {
				head = newH;
				head.nextElement = oldHead;
				oldHead.prevElement = head;
			}
			size++;
		} else if (getElement(position) == tail) {
			final Element<H> oldTail = tail;
			final Element<H> newH = new Element<>(oldTail, newElement, null);
			tail = newH;
			tail.prevElement = oldTail;
			oldTail.nextElement = tail;
			size++;
		} else {
			final Element<H> next = getElement(position);
			final Element<H> prev = getElement(position - 1);
			final Element<H> newH = new Element<>(prev, newElement, next);
			prev.nextElement = newH;
			newH.nextElement = next;
			newH.prevElement = prev;
			next.prevElement = newH;
			size++;
		}
	}

	public void add(int position, H newElement) {
		checkPosition(position);
		if (capacity - size < 2)
			ensureCapacity();
		link(position, newElement);
	}

	public void remove(int position) {
		checkPosition(position);
		Element<H> toDel = getElement(position);
		Element<H> next = toDel.getNextElement();
		Element<H> prev = toDel.getPrevElement();
		if (next != null && prev != null) {
			prev.setNextElement(next);
			next.setPrevElement(prev);
		} else if (prev == null && next == null) {
			head = null;
		} else if (prev != null && next == null) {
			prev.nextElement = null;
			tail = prev;
		} else if (prev == null && next != null) {
			next.prevElement = null;
			head = next;
		}
		toDel = null;
		this.size--;
	}

	public boolean remove(H extra) {
		Element<H> toDel = head;
		int i = 0;
		while (toDel.getData() != extra) {
			if (toDel.getNextElement() == null)
				return false;
			toDel = toDel.getNextElement();
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

	public int get(H element) {
		if (contains(element)) {
			int i = 0;
			for (Element<H> x = head; x != null; x = x.nextElement) {
				if (x.data == element)
					return i;
				else
					i++;
			}
		}
		return -1;
	}

	public boolean isEmpty() {
		if (head == null && size == 0)
			return true;
		else
			return false;
	}

	public boolean clear() {
		int i = this.size - 1;
		remove(i);
		while (!isEmpty()) {
			i--;
			remove(i);
		}
		if (isEmpty())
			return true;
		return false;
	}

	@SuppressWarnings("unchecked")
	public HotelGuest[] toArray() {
		HotelGuest[] result = new HotelGuest[size];
		int i = 0;
		for (Element<HotelGuest> x = (Element<HotelGuest>) head; x != null; x = x.nextElement)
			result[i++] = x.data;
		return result;
	}

	public String toString() {
		String dataInfo = new String();
		if (isEmpty())
			return "Is clear!";
		for (Element<H> x = head; x != null; x = x.nextElement)
			dataInfo += x.getData();

		return dataInfo;
	}

	public boolean contains(H toCheck) {
		Element<H> temp = head;
		while (temp.getData() != toCheck) {

			if (temp.getNextElement() == null)
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

	public static class Element<H> {
		private H data;
		Element<H> nextElement;
		Element<H> prevElement;

		/**
		 * @return the data
		 */
		Element(Element<H> prev, H element, Element<H> next) {
			this.data = element;
			this.nextElement = next;
			this.prevElement = prev;
		}

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

		public void setPrevElement(Element<H> prevElement) {
			this.prevElement = prevElement;
		}

		public Element<H> getPrevElement() {
			return prevElement;
		}

	}

	@Override
	public Iterator<H> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
