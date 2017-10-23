package ua.khpi.oop.malokhvii05.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

import ua.khpi.oop.malokhvii05.util.algorithms.search.SearchAlgorithm;

/**
 * Призначений, для збереження однотипних елементів у вигляді звичайного
 * динамічного масиву об'єктів. Для обробки елементів див. детальнше пакет з
 * типовими алгоритмами.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 * @see ua.khpi.oop.malokhvii05.util.algorithms
 * @see ua.khpi.oop.malokhvii05.util.algorithms.sort
 * @see ua.khpi.oop.malokhvii05.util.algorithms.search
 * @param <E>
 *            Тип елементів розташованих у масиві
 */
public final class Array<E> implements Collection<E>, Serializable {

    /**
     * Призначений, для зручнішого проходження через елементи масиву. реалізує
     * двохстороннє ітерування, тобто в обидва напрямки, та видалення поточного
     * елементу.
     *
     * @author malokhvii-eduard (malokhvii.ee@gmail.com)
     * @version 1.0.0
     */
    public final class ArrayIterator implements Iterator<E> {

        /**
         * Наступний індекс, за яким необзідно отримати значення.
         *
         * @since 1.0.0
         */
        private int nextIndex;

        /**
         * Призначений, для ініціалізації ітератора індексом початкового
         * елементу.
         *
         * @param nextIndex
         *            індекст початкового елементу для проходження
         * @since 1.0.0
         */
        private ArrayIterator(final int nextIndex) {
            this.nextIndex = nextIndex;
        }

        @Override
        public boolean hasNext() {
            return this.nextIndex != Array.this.size;
        }

        /**
         * Призначений, для перевірки чи є наступний попередній елемент.
         *
         * @return результат перевірки
         * @since 1.0.0
         */
        public boolean hasPrevious() {
            return this.nextIndex != 0;
        }

        @SuppressWarnings("unchecked")
        @Override
        public E next() {
            if (this.nextIndex == Array.this.size) {
                throw new NoSuchElementException();
            }

            final int currentIndex = this.nextIndex;
            this.nextIndex++;
            return (E) Array.this.data[currentIndex];
        }

        /**
         * Призначений для отримання значення попереднього елементу.
         *
         * @return попередній елемент з масиву
         * @since 1.0.0
         */
        @SuppressWarnings("unchecked")
        public E previous() {
            final int currentIndex = this.nextIndex - 1;
            if (currentIndex < 0) {
                throw new NoSuchElementException();
            }

            this.nextIndex = currentIndex;
            return (E) Array.this.data[currentIndex];
        }

        @Override
        public void remove() {
            if (Array.this.size == 0) {
                return;
            }

            Array.this.removeElement(this.nextIndex);
        }
    }

    /**
     *
     */
    private static final long serialVersionUID = -2682051498433821439L;

    /**
     * Початковий розмір внутрінього буфера елементів.
     *
     * @since 1.0.0
     */
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    /**
     * Внутрішній буфер елементів масиву.
     *
     * @since 1.0.0
     */
    private transient Object[] data;

    /**
     * Кількість елементів розташованих у внутрішньому буфері.
     *
     * @since 1.0.0
     */
    private int size;

    /**
     * Призначений, для ініціалізації масиву з ємкостю внутрішнього буфера за
     * змовчуванням.
     *
     * @since 1.0.0
     */
    public Array() {
        this(Array.DEFAULT_INITIAL_CAPACITY);
    }

    /**
     * Призначений, для ініціалізації масиву елементами з отриманої колекції.
     *
     * @param collection
     *            колекція з якої будуть скопійовані елементи
     * @since 1.0.0
     */
    public Array(final Collection<? extends E> collection) {
        this();
        this.addAll(collection);
    }

    /**
     * Призначений, для ініціалізації масиву з задовільним розміром ємкості
     * внутрішьного буфера.
     *
     * @param capacity
     *            задовільна ємкість внутрішнього буфера
     * @since 1.0.0
     */
    public Array(final int capacity) {
        this.data = new Object[capacity];
    }

    @Override
    public boolean add(final E element) {
        this.addLast(element);
        return true;
    }

    /**
     * Призначений, для додавання нового елементу в задовільну позиції. Під час
     * додавання виконується перевірка на входження у допустимий діапазон.
     *
     * @param index
     *            індекс елементу для додавання
     * @param element
     *            елемент для додавання
     * @since 1.0.0
     */
    public void add(final int index, final E element) {
        this.isNewElementIndexInRange(index);

        this.ensureCapacity(this.size + 1);
        System.arraycopy(this.data, index, this.data, index + 1,
                this.size - index);

        this.data[index] = element;
        this.size++;
    }

    /**
     * Призначений, для додавання елементу після отриманого індекса.
     *
     * @param index
     *            індекс для додавання
     * @param element
     *            елемент для додавання у масив
     * @since 1.0.0
     */
    public void addAfter(final int index, final E element) {
        this.add(index + 1, element);
    }

    @Override
    public boolean addAll(final Collection<? extends E> collection) {
        final Object[] collectionData = collection.toArray();
        final int amountOfNewElements = collectionData.length;

        this.ensureCapacity(this.size + amountOfNewElements);
        System.arraycopy(collectionData, 0, this.data, this.size,
                amountOfNewElements);
        this.size += amountOfNewElements;

        return amountOfNewElements != 0;
    }

    /**
     * Призначений, для додавання елементу перед отриманим індексом.
     *
     * @param index
     *            індекс для додавання
     * @param element
     *            елемент для додавання у масив
     * @since 1.0.0
     */
    public void addBefore(final int index, final E element) {
        this.add(index - 1, element);
    }

    /**
     * Призначений, для додавання нового елементу на початок масиву.
     *
     * @param element
     *            елемент для додавання у масив
     * @since 1.0.0
     */
    public void addFirst(final E element) {
        this.add(0, element);
    }

    /**
     * Призначений, для додавання нового елементу у кінець масиву.
     *
     * @param element
     *            елемент для додавання у масив
     * @since 1.0.0
     */
    public void addLast(final E element) {
        this.ensureCapacity(this.size + 1);
        this.data[this.size++] = element;
    }

    @Override
    public void clear() {
        int index;
        for (index = 0; index < this.size; index++) {
            this.data[index] = null;
        }
        this.size = 0;
    }

    @Override
    public boolean contains(final Object object) {
        return this.indexOf(object) != -1;
    }

    @Override
    public boolean containsAll(final Collection<?> collection) {
        final Iterator<?> iterator = collection.iterator();
        while (iterator.hasNext()) {
            if (!this.contains(iterator.next())) {
                return false;
            }
        }

        return true;
    }

    /**
     * Призначений, для збільшення ємкості буфера для розташування нових
     * елементів. Коефіцієнт збільшення 1,3.
     *
     * @param minimumCapacity
     *            мінімальна ємкість масиву
     * @since 1.0.0
     */
    private void ensureCapacity(final int minimumCapacity) {
        final int oldCapacity = this.data.length;
        if (minimumCapacity > oldCapacity) {
            int newCapacity = (oldCapacity * 3) / 2 + 1;
            if (newCapacity < minimumCapacity) {
                newCapacity = minimumCapacity;
            }

            final Object[] copy = new Object[newCapacity];
            System.arraycopy(this.data, 0, copy, 0,
                    Math.min(this.data.length, newCapacity));
            this.data = copy;
        }
    }

    /**
     * Призначений, для отримання ітератора на початковий елемент масиву.
     *
     * @return ітератор на початковий елемент масиву
     * @since 1.0.0
     */
    public ArrayIterator firstIterator() {
        return this.iterator(0);
    }

    /**
     * Призначений, для отримання задовільного елементу масиву за індексом.
     * Перевірка на вихід за границі масиву не виконується.
     *
     * @param index
     *            індекс елементу
     * @return значення елементу в масиві
     * @since 1.0.0
     */
    @SuppressWarnings("unchecked")
    public E get(final int index) {
        return (E) this.data[index];
    }

    /**
     * Метод призначений, для отримання внутрішнього буферу. Використовується
     * для оптимізації алгоритмів пошуку та сортування.
     *
     * @return внутрішній буфер
     * @since 1.0.0
     */
    public Object[] getData() {
        return this.data;
    }

    /**
     * Призначений, для отримання першого елементу масиву.
     *
     * @return перший елемент масиву
     * @since 1.0.0
     */
    @SuppressWarnings("unchecked")
    public E getFirst() {
        if (this.isEmpty()) {
            return null;
        }

        return (E) this.data[0];
    }

    /**
     * Призначений, для отримання значення останнього елементу масиву.
     *
     * @return останній елемент масиву
     * @since 1.0.0
     */
    @SuppressWarnings("unchecked")
    public E getLast() {
        if (this.isEmpty()) {
            return null;
        }

        return (E) this.data[this.size - 1];
    }

    /**
     * Призначений, для отримання індексу об'єкту в масиві, якщо він присутній.
     * пошук об'єкту виконується за допомогою лінійного пошуку. Для швидкішого
     * пошуку див. пакет {@link ua.khpi.oop.malokhvii05.util.algorithms.search}.
     *
     * @param object
     *            об'єкт для пошуку
     * @return індекс елементу масиву, якщо не знайдено тоді повертає
     *         {@link SearchAlgorithm#INDEX_NOT_FOUND}
     * @since 1.0.0
     */
    public int indexOf(final Object object) {
        int index;
        if (object == null) {
            for (index = 0; index < this.size; index++) {
                if (this.data[index] == null) {
                    return index;
                }
            }
        } else {
            for (index = 0; index < this.size; index++) {
                if (this.data[index].equals(object)) {
                    return index;
                }
            }
        }
        return SearchAlgorithm.INDEX_NOT_FOUND;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Призначений, для перевірки індексу на входження в розмір масиву під час
     * задовільного доступу до елементів масиву.
     *
     * @param index
     *            індекс для перевірки
     * @return результат перевірки
     * @since 1.0.0
     */
    private boolean isIndexInRange(final int index) {
        if (index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        return true;
    }

    /**
     * Призначений, для перевірки індексу на вихід за розмір масиву під час
     * додавання в задовільну позицію масива.
     *
     * @param index
     *            індекс для перевірки
     * @return результат перевірки
     * @since 1.0.0
     */
    private boolean isNewElementIndexInRange(final int index) {
        if (index > this.size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return true;
    }

    @Override
    public Iterator<E> iterator() {
        return this.firstIterator();
    }

    /**
     * Призначений, для отримання об'єкту {@link ArrayIterator ітератора}.
     *
     * @param index
     *            початковий індекс для ітератора
     * @return об'єкт ітератора проініціалізований початковим індексом
     * @since 1.0.0
     */
    public ArrayIterator iterator(final int index) {
        this.isIndexInRange(index);
        return new ArrayIterator(index);
    }

    /**
     * Призначений, для отримання {@link ArrayIterator ітератора} на кінець
     * масиву.
     *
     * @return ітератор на кінець масиву
     * @since 1.0.0
     */
    public ArrayIterator lastIterator() {
        return this.iterator(this.size - 1);
    }

    private void readObject(final ObjectInputStream objectInputStream)
            throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();

        final int arraySize = objectInputStream.readInt();

        if (arraySize >= 0) {
            this.data = new Object[arraySize];
            int index;
            for (index = 0; index < this.size; index++) {
                this.data[index] = objectInputStream.readObject();
            }
        }
    }

    /**
     * Призначений, для видалення задовільного елементу масиву за індексом.
     *
     * @param index
     *            індекс елементу масиву для видалення
     * @since 1.0.0
     */
    public void remove(final int index) {
        this.isIndexInRange(index);
        this.removeElement(index);
    }

    @Override
    public boolean remove(final Object object) {
        int index;
        if (object == null) {
            for (index = 0; index < this.size; index++) {
                if (this.data[index] == null) {
                    this.removeElement(index);
                    return true;
                }
            }
        } else {
            for (index = 0; index < this.size; index++) {
                if (this.data[index].equals(object)) {
                    this.removeElement(index);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean removeAll(final Collection<?> collection) {
        boolean isModified = false;

        final Iterator<?> iterator = this.iterator();
        while (iterator.hasNext()) {
            if (!collection.contains(iterator.next())) {
                iterator.remove();
                isModified = true;
            }
        }

        return isModified;
    }

    /**
     * Внутрішня реалізація видалення елементу масиву, усі піблічні методи
     * видалення делегують викоритсання цього метода.
     *
     * @param index
     *            індекс елементу для видалення
     * @since 1.0.0
     */
    private void removeElement(final int index) {
        final int amountOfMovedElements = this.size - index - 1;
        if (amountOfMovedElements > 0) {
            System.arraycopy(this.data, index + 1, this.data, index,
                    amountOfMovedElements);
        }

        this.data[--this.size] = null;
    }

    /**
     * Призначений, для видалення першого елементу масиву.
     *
     * @since 1.0.0
     */
    public void removeFirst() {
        this.removeElement(0);
    }

    /**
     * Призначений, для видалення останнього елементу масиву.
     *
     * @since 1.0.0
     */
    public void removeLast() {
        this.removeElement(this.size - 1);
    }

    @Override
    public boolean retainAll(final Collection<?> collection) {
        boolean isModified = false;

        final Iterator<E> iterator = this.iterator();
        while (iterator.hasNext()) {
            if (!collection.contains(iterator.next())) {
                iterator.remove();
                isModified = true;
            }
        }

        return isModified;
    }

    /**
     * Призначений, для оновлення задовільного значення масиву за індексом.
     *
     * @param index
     *            індекс елементу для оновлення
     * @param element
     *            оновлене значення елементу
     * @return попереднє значення елементу
     * @since 1.0.0
     */
    @SuppressWarnings("unchecked")
    public E set(final int index, final E element) {
        this.isIndexInRange(index);

        final E oldElement = (E) this.data[index];
        this.data[index] = element;

        return oldElement;
    }

    /**
     * Призначений, для оновлення значення першого елементу масиву.
     *
     * @param element
     *            оновлене значення першого елементу масиву
     * @return попереднє знчення першого масиву
     * @since 1.0.0
     */
    @SuppressWarnings("unchecked")
    public E setFirst(final E element) {
        if (this.isEmpty()) {
            return null;
        }

        final Object oldElement = this.data[0];
        this.data[0] = element;
        return (E) oldElement;
    }

    /**
     * Призначений, для оновлення значення останнього елементу масиву.
     *
     * @param element
     *            оновлене значення останнього елементу масиву
     * @return попереднє значення останнього елементу масиву
     * @since 1.0.0
     */
    @SuppressWarnings("unchecked")
    public E setLast(final E element) {
        if (this.isEmpty()) {
            return null;
        }

        final Object oldElement = this.data[this.size - 1];
        this.data[this.size - 1] = element;
        return (E) oldElement;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Object[] toArray() {
        final Object[] array = new Object[this.size];
        System.arraycopy(this.data, 0, array, 0, this.size);
        return array;
    }

    @Override
    public <T> T[] toArray(final T[] array) {
        if (array.length < this.size) {
            return null;
        }

        System.arraycopy(this.data, 0, array, 0, this.size);
        if (array.length > this.size) {
            array[this.size] = null;
        }

        return array;
    }

    @Override
    public String toString() {
        return this.toString("\n");
    }

    /**
     * Призначений, для формування рядку із об'єктів масиву, з задовільним
     * роздільником для рядків.
     *
     * @param separator
     *            роздільник для рядків
     * @return елементу масиву у вигляді суцільного рядку
     * @since 1.0.0
     */
    public String toString(final String separator) {
        final StringBuilder stringBuilder = new StringBuilder();

        int index = 0;
        while (index < this.size) {
            stringBuilder.append(this.data[index].toString());
            stringBuilder.append(separator);
            index++;
        }

        return stringBuilder.toString();
    }

    private void writeObject(final ObjectOutputStream objectOutputStream)
            throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(this.data.length);

        int index;
        for (index = 0; index < this.size; index++) {
            objectOutputStream.writeObject(this.data[index]);
        }
    }
}
