package ua.khpi.oop.lytvyn06;

import java.io.Serializable;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Контейнер для зберігання елементів типу String
 * 
 * @author student Lytvyn I.I. KIT-26A
 */
public class StringСontainer implements Iterable<String>, Serializable {
	/**
	 * Унікальний ідентифікатор версії класу
	 */
	private static final long serialVersionUID = 1306986177301409325L;
	/**
	 * Початкова ємність за замовчуванням.
	 */
	private static final int DEFAULT_CAPACITY = 10;
	/**
	 * Пустий порожній екземпляр масиву, який використовується для пустих
	 * екземплярів.
	 */
	private static final String[] EMPTY_DATA = {};
	/**
	 * Пустий порожній екземпляр масиву, який використовується для пустих
	 * екземплярів ємністю за замовчуванням.
	 */
	private static final String[] DEFAULTCAPACITY_EMPTY_DATA = {};
	/**
	 * Максимальний розмір масиву.
	 */
	private static final int MAX_SIZE = Integer.MAX_VALUE - 8;
	/**
	 * Буферний масив, в якому зберігаються контейнерні елементи. Ємність
	 * контейнера - це довжина цього масиву.
	 */
	transient String[] stringData;
	/**
	 * Розмір контейнера (кількість елементів, що містяться в ньому).
	 */
	private int size;

	/**
	 * Створює порожній контейнер із вказаною початковою ємністю.
	 *
	 * @param initialCapacity
	 *            Початкова ємність контейнера
	 * @throws IllegalArgumentException
	 *             якщо вказана початкова ємність є негативною
	 */
	public StringСontainer(int initialCapacity) {
		if (initialCapacity > 0) {
			this.stringData = new String[initialCapacity];
		} else if (initialCapacity == 0) {
			this.stringData = EMPTY_DATA;
		} else {
			throw new IllegalArgumentException(
			        "Illegal Capacity: " + initialCapacity);
		}
	}

	/**
	 * Створює контейнер, що містить елементи вказаного контейнера.
	 * 
	 * @param container
	 *            колекція, елементи якої повинні бути включені в цей список
	 */
	public StringСontainer(StringСontainer container) { // TO-DO
		stringData = container.stringData;
		if ((size = container.size()) != 0) {
			String[] newData = new String[size];
			System.arraycopy(stringData, 0, newData, 0, size);
			stringData = newData;
		} else { // replace with empty array.
			this.stringData = EMPTY_DATA;
		}
	}

	/**
	 * Створює порожній контейнер з початковою ємністю.
	 */
	public StringСontainer() {
		this.stringData = DEFAULTCAPACITY_EMPTY_DATA;
	}

	/**
	 * Конвертує контейнер до String.
	 */
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < size; i++) {
			buffer.append(stringData[i]);
		}
		return buffer.toString();
	}

	/**
	 * Додає вказаний елемент до кінця цього контейнера.
	 * 
	 * @param string
	 *            елемент, що слід додати
	 */
	void add(String string) {
		resize(size + 1);
		stringData[size++] = string;
	}

	/**
	 * Видаляє всі елементи з цього контейнера.
	 */
	void clear() {
		for (int i = 0; i < size; i++) {
			stringData[i] = null;
		}
		size = 0;
	}

	/**
	 * Видаляє перше входження вказаного елемента з цього контейнер, якщо є.
	 * Якщо контейнер не містить елемент, він не змінився.
	 *
	 * @param string
	 *            елемент, який слід видалити з цього контейнера, якщо він
	 *            присутній
	 * @return <tt>true</tt> якщо цей контейнер містить вказаний елемент
	 */
	public boolean remove(String string) {
		if (string == null) {
			for (int index = 0; index < size; index++)
				if (stringData[index] == null) {
					fastRemove(index);
					return true;
				}
		} else {
			for (int index = 0; index < size; index++)
				if (string.equals(stringData[index])) {
					fastRemove(index);
					return true;
				}
		}
		return false;
	}

	/*
	 * Приватний метод видалення, який пропускає перевірку межі та не повертає
	 * видаленого значення
	 */
	private void fastRemove(int index) {
		int numMoved = size - index - 1;
		if (numMoved > 0)
			System.arraycopy(stringData, index + 1, stringData, index,
			        numMoved);
		stringData[--size] = null;
	}

	/**
	 * Повертає масив, що містить всі елементи у цьому контейнері в правильній
	 * послідовності (від першого до останнього елемента).
	 *
	 * @return масив, що містить всі елементи у цьому контейнері в правильній
	 *         послідовності
	 */
	public Object[] toArray() {
		return copyOf(stringData, size);
	}

	/**
	 * Повертає кількість елементів у цьому контейнері.
	 *
	 * @return кількість елементів у цьому контейнері.
	 */
	public int size() {
		return size;
	}

	/**
	 * Повертає <tt>true</tt> якщо цей контейнер містить вказаний елемент.
	 * 
	 * @param string
	 *            елемент, наявність якого в цьому контейнері має бути
	 *            перевірено
	 * @return <tt>true</tt> if this list contains the specified element
	 */
	public boolean contains(String string) {
		return indexOf(string) >= 0;
	}

	/**
	 * Повертає <tt>true</tt> якщо цей контейнер містить вказані елементи
	 * отриманий контейнер
	 *
	 * @param conteiner
	 *            контейнер, наявність яких елементів буде перевірено
	 * @return <tt>true</tt> якщо цей контейнер містить вказані елементи
	 */
	public boolean containsAll(StringСontainer container) {
		for (int i = 0; i < container.size(); i++)
			if (!contains(container.get(i)))
				return false;
		return true;
	}

	@Override
	public ContainerIterator<String> iterator() {
		return new ContainerIterator<String>();
	}

	/**
	 * Повертає індекс першого входження вказаного елемента в цей контейнер, або
	 * -1, якщо цей контейнер не містить елемента.
	 */
	public int indexOf(String string) {
		if (string == null) {
			for (int i = 0; i < size; i++)
				if (stringData[i] == null)
					return i;
		} else {
			for (int i = 0; i < size; i++)
				if (string.equals(stringData[i]))
					return i;
		}
		return -1;
	}

	public static Object[] copyOf(String[] original, int newLength) {
		Object[] copy = (String[]) new Object[newLength];
		System.arraycopy(original, 0, copy, 0,
		        Math.min(original.length, newLength));
		return copy;
	}

	/**
	 * Повертає елемент у зазначеній позиції в цьому контейнері.
	 *
	 * @param index
	 *            індекс повернутого елементу
	 * @return елемент у зазначеній позиції в цьому контейнері
	 * @throws IndexOutOfBoundsException
	 */
	public String get(int index) {
		rangeCheck(index);

		return stringData[index];
	}

	/**
	 * Замінює елемент у вказаному положенні в цьому контейнері з вказаний
	 * елемент.
	 *
	 * @param index
	 *            індекс елемента, який потрібно замінити
	 * @param string
	 *            елемент, який зберігатиметься у вказаній позиції
	 * @return елемент, що був раніше в зазначеній позиції
	 * @throws IndexOutOfBoundsException
	 */
	public String set(int index, String string) {
		rangeCheck(index);

		String oldValue = stringData[index];
		stringData[index] = string;
		return oldValue;
	}

	/**
	 * Перевіряє, чи вказаний індекс знаходиться в діапазоні. Якщо ні, кидає
	 * відповідний runtime exception. Цей метод "не" перевіряє, чи є індекс
	 * негативним.
	 */
	private void rangeCheck(int index) {
		if (index >= size)
			throw new IndexOutOfBoundsException(
			        "Index: " + index + ", Size: " + size);
	}

	/**
	 * Повертає <tt> true </ tt> якщо цей контейнер не містить елементів.
	 *
	 * @return <tt>true</tt> якщо цей контейнер не містить елементів
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Збільшує ємніть, щоб забезпечити щонайменше число елементів, вказані
	 * аргументом мінімальної ємності.
	 *
	 * @param minCapacity
	 *            бажана мінімальна ємність
	 */
	private void resize(int minCapacity) {
		if (stringData == DEFAULTCAPACITY_EMPTY_DATA) {
			minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
		}

		if (minCapacity - stringData.length > 0) {
			int oldCapacity = stringData.length;
			int newCapacity = oldCapacity + (oldCapacity >> 1);
			if (newCapacity - minCapacity < 0) {
				newCapacity = minCapacity;
			}
			if (newCapacity - MAX_SIZE > 0) {
				if (minCapacity < 0) { // Переповнення
					throw new OutOfMemoryError();
				}
				newCapacity = (minCapacity > MAX_SIZE) ? Integer.MAX_VALUE
				        : MAX_SIZE;
			}

			String[] newData = new String[newCapacity];
			System.arraycopy(stringData, 0, newData, 0,
			        Math.min(stringData.length, newCapacity));
			stringData = newData;
		}
	}

	public void sort(Comparator<? super String> comparator) {
		// MergeSort.sort(stringData, comparator);
	}

	/**
	 * Зберігає екземпляр <tt>StringContainer</tt> в потоці (тобто серіалізує
	 * його).
	 * 
	 * @param outStream
	 *            потік, в який записується екземпляр <tt>StringContainer</tt>
	 * @throws java.io.IOException
	 */
	private void writeObject(java.io.ObjectOutputStream outStream)
	        throws java.io.IOException {
		/* Запис кількості елементів та інших прихованих речей */
		outStream.defaultWriteObject();
		/* Запис довжини масиву */
		outStream.writeInt(size);
		/* Запис всіх елементів у належному порядку. */
		for (int i = 0; i < size; i++) {
			outStream.writeObject(stringData[i]);
		}
	}

	/**
	 * Відновлює екземпляр <tt>StringContainer</tt> з потоку (тобто десеріалізує
	 * його).
	 * 
	 * @param inStream
	 *            потік, з якого відновлюється екземпляр
	 *            <tt>StringContainer</tt>
	 * @throws java.io.IOException
	 * @throws ClassNotFoundException
	 */
	private void readObject(java.io.ObjectInputStream inStream)
	        throws java.io.IOException, ClassNotFoundException {
		/* Зчитування розміру та інших прихованих речей */
		inStream.defaultReadObject();
		/* Зчитування довжинb масиву */
		inStream.readInt();

		if (size > 0) {
			/* Виділення масиву для зчитування */
			Object[] a = stringData = new String[size];
			/* Зчитування всіх елементів у належному порядку */
			for (int i = 0; i < size; i++) {
				a[i] = inStream.readObject();
			}
		}
	}

	/**
	 * Ітератор для контейнеру StringСontainer
	 * 
	 * @author student Lytvyn I.I. KIT-26A
	 */
	class ContainerIterator<E> implements Iterator<E> {
		int current; // Індекс елементу, що повернеться
		int lastRet = -1; // Індекс останього елементу, що повертався; -1 якщо
		                  // такий відсутній

		@Override
		public boolean hasNext() {
			return current != size;
		}

		@SuppressWarnings("unchecked")
		@Override
		public E next() {
			int i = current;
			if (i >= size)
				throw new NoSuchElementException();
			Object[] elementData = StringСontainer.this.stringData;
			current = i + 1;
			return (E) elementData[lastRet = i];
		}

		@Override
		public void remove() {
			if (lastRet < 0)
				throw new IllegalStateException();
			try {
				StringСontainer.this.fastRemove(lastRet);
				current = lastRet;
				lastRet = -1;
			} catch (IndexOutOfBoundsException ex) {
				throw new RuntimeException(ex.toString());
			}
		}

		@SuppressWarnings("unchecked")
		@Override
		public void forEachRemaining(Consumer<? super E> consumer) {
			Objects.requireNonNull(consumer);
			final int size = StringСontainer.this.size;
			int i = current;
			if (i >= size) {
				return;
			}
			final Object[] elementData = StringСontainer.this.stringData;
			if (i >= elementData.length) {
				throw new ConcurrentModificationException();
			}
			while (i != size) {
				consumer.accept((E) elementData[i++]);
			}
			current = i;
			lastRet = i - 1;
		}
	}
}