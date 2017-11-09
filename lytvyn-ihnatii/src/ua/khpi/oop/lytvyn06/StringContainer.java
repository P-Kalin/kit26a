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
public class StringContainer implements Iterable<String>, Serializable {
	/**
	 * Ітератор для контейнеру StringСontainer
	 * 
	 * @author student Lytvyn I.I. KIT-26A
	 */
	class ContainerIterator<E> implements Iterator<E> {
		/**
		 * Індекс елементу, що повернеться
		 */
		int current;
		/**
		 * Індекс останього елементу, що повертався; -1 якщо такий відсутній
		 */
		int lastRet = -1;

		@SuppressWarnings("unchecked")
		@Override
		public void forEachRemaining(Consumer<? super E> consumer) {
			Objects.requireNonNull(consumer);
			final int size = StringContainer.this.size;
			int i = current;
			if (i >= size) {
				return;
			}
			final Object[] elementData = StringContainer.this.stringData;
			if (i >= elementData.length) {
				throw new ConcurrentModificationException();
			}
			while (i != size) {
				consumer.accept((E) elementData[i++]);
			}
			current = i;
			lastRet = i - 1;
		}

		@Override
		public boolean hasNext() {
			return current != size;
		}

		@SuppressWarnings("unchecked")
		@Override
		public E next() {
			final int i = current;
			if (i >= size) {
				throw new NoSuchElementException();
			}
			final Object[] elementData = StringContainer.this.stringData;
			current = i + 1;
			return (E) elementData[lastRet = i];
		}

		@Override
		public void remove() {
			if (lastRet < 0) {
				throw new IllegalStateException();
			}
			try {
				StringContainer.this.fastRemove(lastRet);
				current = lastRet;
				lastRet = -1;
			} catch (final IndexOutOfBoundsException ex) {
				System.out.println(this);
				throw new RuntimeException(ex.toString());
			}
		}
	}
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
	 * Компаратор для порівніння елементів контейнеру
	 */
	public static final Comparator<String> COMPARATOR = new StringComparator();
	/**
	 * Конвертує копію масиву данних до масиву елементів типу <tt>Object</tt>
	 * 
	 * @param original
	 *            масив данних
	 * @param newLength
	 *            розмір масиву
	 * @return копія масиву данних
	 */
	public static Object[] copyOf(String[] original, int newLength) {
		final Object[] copy = new Object[newLength];
		System.arraycopy(original, 0, copy, 0,
		        Math.min(original.length, newLength));
		return copy;
	}

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
	 * Створює порожній контейнер з початковою ємністю.
	 */
	public StringContainer() {
		this.stringData = DEFAULTCAPACITY_EMPTY_DATA;
	}

	/**
	 * Створює порожній контейнер із вказаною початковою ємністю.
	 *
	 * @param initialCapacity
	 *            Початкова ємність контейнера
	 * @throws IllegalArgumentException
	 *             якщо вказана початкова ємність є негативною
	 */
	public StringContainer(int initialCapacity) {
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
	public StringContainer(StringContainer container) { // TO-DO
		stringData = container.stringData;
		if ((size = container.size()) != 0) {
			final String[] newData = new String[size];
			System.arraycopy(stringData, 0, newData, 0, size);
			stringData = newData;
		} else { // replace with empty array.
			this.stringData = EMPTY_DATA;
		}
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
	 * @param container
	 *            контейнер, наявність яких елементів буде перевірено
	 * @return <tt>true</tt> якщо цей контейнер містить вказані елементи
	 */
	public boolean containsAll(StringContainer container) {
		for (int i = 0; i < container.size(); i++) {
			if (!contains(container.get(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Повертає елемент у зазначеній позиції в цьому контейнері.
	 *
	 * @param index
	 *            індекс повернутого елементу
	 * @return елемент у зазначеній позиції в цьому контейнері
	 * @throws IndexOutOfBoundsException
	 *             вихід за межі масиву
	 */
	public String get(int index) {
		rangeCheck(index);

		return stringData[index];
	}

	/**
	 * Повертає індекс першого входження вказаного елемента в цей контейнер, або
	 * -1, якщо цей контейнер не містить елемента.
	 * 
	 * @param string
	 *            об'єкт для пошуку
	 * 
	 * @return індекс прешого входження вказаного елементу, або -1 якщо такого
	 *         немає
	 */
	public int indexOf(String string) {
		if (string == null) {
			for (int i = 0; i < stringData.length; i++) {
				if (stringData[i] == null) {
					return i;
				}
			}
		} else {
			for (int i = 0; i < stringData.length; i++) {
				if (string.equals(stringData[i])) {
					return i;
				}
			}
		}
		return -1;
	}

	/**
	 * Повертає <tt> true </tt> якщо цей контейнер не містить елементів.
	 *
	 * @return <tt>true</tt> якщо цей контейнер не містить елементів
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public ContainerIterator<String> iterator() {
		return new ContainerIterator<>();
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
			for (int index = 0; index < size; index++) {
				if (stringData[index] == null) {
					fastRemove(index);
					return true;
				}
			}
		} else {
			for (int index = 0; index < size; index++) {
				if (string.equals(stringData[index])) {
					fastRemove(index);
					return true;
				}
			}
		}
		return false;
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
	 *             вихід за межі масиву
	 */
	public String set(int index, String string) {
		rangeCheck(index);

		final String oldValue = stringData[index];
		stringData[index] = string;
		return oldValue;
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
	 * Сортування контейнеру за допомогою компаратора
	 * 
	 * @param comparator
	 *            компаратор для сортування
	 */
	public void sort(Comparator<? super String> comparator) {
		MergeSort.sort(stringData, comparator);
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
	 * Конвертує екземпляр StringСontainer до String.
	 */
	@Override
	public String toString() {
		return getClass().getName() + "@" + Integer.toHexString(hashCode())
		        + "{size=" + size + "}";
	}

	/**
	 * Приватний метод видалення, який пропускає перевірку межі та не повертає
	 * видаленого значення
	 *
	 * @param index
	 *            індекс елементу, що видаляється
	 */
	private void fastRemove(int index) {
		final int numMoved = size - index - 1;
		if (numMoved > 0) {
			System.arraycopy(stringData, index + 1, stringData, index,
			        numMoved);
		}
		stringData[--size] = null;
	}

	/**
	 * Перевіряє, чи вказаний індекс знаходиться в діапазоні. Якщо ні, кидає
	 * відповідний runtime exception. Цей метод "не" перевіряє, чи є індекс
	 * негативним.
	 * 
	 * @param index
	 *            індекс розташування в контейнері
	 */
	private void rangeCheck(int index) {
		if (index >= size) {
			throw new IndexOutOfBoundsException(
			        "Index: " + index + ", Size: " + size);
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
	 *             виключна ситуація при введені/виведені
	 * @throws ClassNotFoundException
	 *             виключна ситуація при відсутності необхідного класу
	 */
	private void readObject(java.io.ObjectInputStream inStream)
	        throws java.io.IOException, ClassNotFoundException {
		/* Зчитування розміру та інших прихованих речей */
		inStream.defaultReadObject();
		/* Зчитування довжинb масиву */
		inStream.readInt();

		if (size > 0) {
			/* Виділення масиву для зчитування */
			final Object[] a = stringData = new String[size];
			/* Зчитування всіх елементів у належному порядку */
			for (int i = 0; i < size; i++) {
				a[i] = inStream.readObject();
			}
		}
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
			final int oldCapacity = stringData.length;
			int newCapacity = oldCapacity + (oldCapacity >> 1);
			if (newCapacity - minCapacity < 0) {
				newCapacity = minCapacity;
			}
			if (newCapacity - MAX_SIZE > 0) {
				if (minCapacity < 0) { // Переповнення
					throw new OutOfMemoryError();
				}
				newCapacity = minCapacity > MAX_SIZE ? Integer.MAX_VALUE
				        : MAX_SIZE;
			}

			final String[] newData = new String[newCapacity];
			System.arraycopy(stringData, 0, newData, 0,
			        Math.min(stringData.length, newCapacity));
			stringData = newData;
		}
	}

	/**
	 * Зберігає екземпляр <tt>StringContainer</tt> в потоці (тобто серіалізує
	 * його).
	 * 
	 * @param outStream
	 *            потік, в який записується екземпляр <tt>StringContainer</tt>
	 * @throws java.io.IOException
	 *             виключна ситуація при введені/виведені
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
}