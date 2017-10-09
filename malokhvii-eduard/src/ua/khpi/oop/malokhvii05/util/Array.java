package ua.khpi.oop.malokhvii05.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import ua.khpi.oop.malokhvii05.util.algorithms.search.SearchAlgorithm;

/**
 * Призначений, для збереження однотипних елементів у вигляді звичайного
 * динамічного масиву об'єктів. Для обробки елементів див. детальнше пакет з
 * типовими алгоритмами.
 *
 * @author malokhvii-ee
 * @version 1.0.0
 * @see ua.khpi.oop.malokhvii05.util.algorithms
 * @see ua.khpi.oop.malokhvii05.util.algorithms.sort
 * @see ua.khpi.oop.malokhvii05.util.algorithms.search
 * @param <E>
 *            Тип елементів розташованих у масиві
 */
// TODO реалізувати інтерфейс Serializable
public final class Array<E> implements Collection<E> {

    /**
     * Призначений, для зручнішого проходження через елементи масиву. реалізує
     * двохстороннє ітерування, тобто в обидва напрямки, та видалення поточного
     * елементу.
     *
     * @author malokhvii-ee
     * @version 1.0.1
     */
    public final class ArrayIterator implements Iterator<E> {

        /**
         * Наступний індекс, за яким необзідно отримати значення.
         */
        private int nextIndex;

        /**
         * Призначений, для ініціалізації ітератора індексом початкового
         * елементу.
         *
         * @param nextIndex
         *            індекст початкового елементу для проходження
         */
        private ArrayIterator(final int nextIndex) {
            this.nextIndex = nextIndex;
        }

        @Override
        public boolean hasNext() {
            return nextIndex != size;
        }

        /**
         * Призначений, для перевірки чи є наступний попередній елемент.
         *
         * @return результат перевірки
         */
        public boolean hasPrevious() {
            return nextIndex != 0;
        }

        @SuppressWarnings("unchecked")
        @Override
        public E next() {
            if (nextIndex == size) {
                throw new NoSuchElementException();
            }

            int currentIndex = nextIndex;
            nextIndex++;
            return (E) Array.this.data[currentIndex];
        }

        /**
         * Призначений для отримання значення попереднього елементу.
         *
         * @return попередній елемент з масиву
         */
        @SuppressWarnings("unchecked")
        public E previous() {
            int currentIndex = nextIndex - 1;
            if (currentIndex < 0) {
                throw new NoSuchElementException();
            }

            nextIndex = currentIndex;
            return (E) Array.this.data[currentIndex];
        }

        @Override
        public void remove() {
            if (size == 0) {
                return;
            }

            Array.this.removeElement(nextIndex);
        }
    }

    /**
     * Кількість елементів розташованих у внутрішньому буфері.
     */
    private int size;

    /**
     * Початковий розмір внутрінього буфера елементів.
     */
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    /**
     * Внутрішній буфер елементів масиву.
     */
    private Object[] data;

    /**
     * Призначений, для ініціалізації масиву з задовільним розміром ємкості
     * внутрішьного буфера.
     *
     * @param capacity
     *            задовільна ємкість внутрішнього буфера
     */
    public Array(final int capacity) {
        data = new Object[capacity];
    }

    /**
     * Призначений, для ініціалізації масиву з ємкостю внутрішнього буфера за
     * змовчуванням.
     */
    public Array() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    /**
     * Призначений, для ініціалізації масиву елементами з отриманої колекції.
     *
     * @param collection
     *            колекція з якої будуть скопійовані елементи
     */
    public Array(final Collection<? extends E> collection) {
        addAll(collection);
    }

    @Override
    public boolean add(final E element) {
        addLast(element);
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
     */
    public void add(final int index, final E element) {
        isNewElementIndexInRange(index);

        ensureCapacity(size + 1);
        System.arraycopy(data, index, data, index + 1, size - index);

        data[index] = element;
        size++;
    }

    /**
     * Призначений, для додавання елементу після отриманого індекса.
     *
     * @param index
     *            індекс для додавання
     * @param element
     *            елемент для додавання у масив
     */
    public void addAfter(final int index, final E element) {
        add(index + 1, element);
    }

    @Override
    public boolean addAll(final Collection<? extends E> collection) {
        Object[] collectionData = collection.toArray();
        int amountOfNewElements = collectionData.length;

        ensureCapacity(size + amountOfNewElements);
        System.arraycopy(collectionData, 0, data, size, amountOfNewElements);
        size += amountOfNewElements;

        return amountOfNewElements != 0;
    }

    /**
     * Призначений, для додавання елементу перед отриманим індексом.
     *
     * @param index
     *            індекс для додавання
     * @param element
     *            елемент для додавання у масив
     */
    public void addBefore(final int index, final E element) {
        add(index - 1, element);
    }

    /**
     * Призначений, для додавання нового елементу на початок масиву.
     *
     * @param element
     *            елемент для додавання у масив
     */
    public void addFirst(final E element) {
        add(0, element);
    }

    /**
     * Призначений, для додавання нового елементу у кінець масиву.
     *
     * @param element
     *            елемент для додавання у масив
     */
    public void addLast(final E element) {
        ensureCapacity(size + 1);
        data[size++] = element;
    }

    @Override
    public void clear() {
        int index;
        for (index = 0; index < size; index++) {
            data[index] = null;
        }
        size = 0;
    }

    /**
     * Призначений, для збільшення ємкості буфера для розташування нових
     * елементів. Коефіцієнт збільшення 1,3.
     *
     * @param minimumCapacity
     *            мінімальна ємкість масиву
     */
    private void ensureCapacity(final int minimumCapacity) {
        int oldCapacity = data.length;
        if (minimumCapacity > oldCapacity) {
            int newCapacity = (oldCapacity * 3) / 2 + 1;
            if (newCapacity < minimumCapacity) {
                newCapacity = minimumCapacity;
            }

            Object[] copy = new Object[newCapacity];
            System.arraycopy(data, 0, copy, 0,
                    Math.min(data.length, newCapacity));
            data = copy;
        }
    }

    /**
     * Призначений, для отримання ітератора на початковий елемент масиву.
     *
     * @return ітератор на початковий елемент масиву
     */
    public ArrayIterator firstIterator() {
        return iterator(0);
    }

    /**
     * Призначений, для отримання задовільного елементу масиву за індексом.
     * Перевірка на вихід за границі масиву не виконується.
     *
     * @param index
     *            індекс елементу
     * @return значення елементу в масиві
     */
    @SuppressWarnings("unchecked")
    public E get(final int index) {
        return (E) data[index];
    }

    /**
     * Призначений, для отримання першого елементу масиву.
     *
     * @return перший елемент масиву
     */
    @SuppressWarnings("unchecked")
    public E getFirst() {
        if (isEmpty()) {
            return null;
        }

        return (E) data[0];
    }

    /**
     * Призначений, для отримання значення останнього елементу масиву.
     *
     * @return останній елемент масиву
     */
    @SuppressWarnings("unchecked")
    public E getLast() {
        if (isEmpty()) {
            return null;
        }

        return (E) data[size - 1];
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
     */
    public int indexOf(final Object object) {
        int index;
        if (object == null) {
            for (index = 0; index < size; index++) {
                if (data[index] == null) {
                    return index;
                }
            }
        } else {
            for (index = 0; index < size; index++) {
                if (data[index].equals(object)) {
                    return index;
                }
            }
        }
        return SearchAlgorithm.INDEX_NOT_FOUND;
    }

    /**
     * Призначений, для перевірки індексу на входження в розмір масиву під час
     * задовільного доступу до елементів масиву.
     *
     * @param index
     *            індекс для перевірки
     * @return результат перевірки
     */
    private boolean isIndexInRange(final int index) {
        if (index >= size) {
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
     */
    private boolean isNewElementIndexInRange(final int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return true;
    }

    @Override
    public Iterator<E> iterator() {
        return firstIterator();
    }

    /**
     * Призначений, для отримання {@link ArrayIterator ітератора} на кінець
     * масиву.
     *
     * @return ітератор на кінець масиву
     */
    public ArrayIterator lastIterator() {
        return iterator(size - 1);
    }

    /**
     * Призначений, для отримання об'єкту {@link ArrayIterator ітератора}.
     *
     * @param index
     *            початковий індекс для ітератора
     * @return об'єкт ітератора проініціалізований початковим індексом
     */
    public ArrayIterator iterator(final int index) {
        isIndexInRange(index);
        return new ArrayIterator(index);
    }

    /**
     * Призначений, для видалення задовільного елементу масиву за індексом.
     *
     * @param index
     *            індекс елементу масиву для видалення
     */
    public void remove(final int index) {
        isIndexInRange(index);
        removeElement(index);
    }

    @Override
    public boolean remove(final Object object) {
        int index;
        if (object == null) {
            for (index = 0; index < size; index++) {
                if (data[index] == null) {
                    removeElement(index);
                    return true;
                }
            }
        } else {
            for (index = 0; index < size; index++) {
                if (data[index].equals(object)) {
                    removeElement(index);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Внутрішня реалізація видалення елементу масиву, усі піблічні методи
     * видалення делегують викоритсання цього метода.
     *
     * @param index
     *            індекс елементу для видалення
     */
    private void removeElement(final int index) {
        int amountOfMovedElements = size - index - 1;
        if (amountOfMovedElements > 0) {
            System.arraycopy(data, index + 1, data, index,
                    amountOfMovedElements);
        }

        data[--size] = null;
    }

    /**
     * Призначений, для видалення першого елементу масиву.
     */
    public void removeFirst() {
        removeElement(0);
    }

    /**
     * Призначений, для видалення останнього елементу масиву.
     */
    public void removeLast() {
        removeElement(size - 1);
    }

    /**
     * Призначений, для оновлення задовільного значення масиву за індексом.
     *
     * @param index
     *            індекс елементу для оновлення
     * @param element
     *            оновлене значення елементу
     * @return попереднє значення елементу
     */
    @SuppressWarnings("unchecked")
    public E set(final int index, final E element) {
        isIndexInRange(index);

        E oldElement = (E) data[index];
        data[index] = element;

        return oldElement;
    }

    /**
     * Призначений, для оновлення значення першого елементу масиву.
     *
     * @param element
     *            оновлене значення першого елементу масиву
     * @return попереднє знчення першого масиву
     */
    @SuppressWarnings("unchecked")
    public E setFirst(final E element) {
        if (isEmpty()) {
            return null;
        }

        Object oldElement = data[0];
        data[0] = element;
        return (E) oldElement;
    }

    /**
     * Призначений, для оновлення значення останнього елементу масиву.
     *
     * @param element
     *            оновлене значення останнього елементу масиву
     * @return попереднє значення останнього елементу масиву
     */
    @SuppressWarnings("unchecked")
    public E setLast(final E element) {
        if (isEmpty()) {
            return null;
        }

        Object oldElement = data[size - 1];
        data[size - 1] = element;
        return (E) oldElement;
    }

    /**
     * Метод призначений, для отримання внутрішнього буферу. Викоритсовується
     * для оптимізації алгоритмів пошуку та сортування.
     *
     * @return внутрішній буфер
     */
    public Object[] getData() {
        return data;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        System.arraycopy(data, 0, array, 0, size);
        return array;
    }

    @Override
    public boolean contains(final Object object) {
        return indexOf(object) != -1;
    }

    @Override
    public boolean containsAll(final Collection<?> collection) {
        Iterator<?> iterator = collection.iterator();
        while (iterator.hasNext()) {
            if (!contains(iterator.next())) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean removeAll(final Collection<?> collection) {
        boolean isModified = false;

        Iterator<?> iterator = iterator();
        while (iterator.hasNext()) {
            if (!collection.contains(iterator.next())) {
                iterator.remove();
                isModified = true;
            }
        }

        return isModified;
    }

    @Override
    public boolean retainAll(final Collection<?> collection) {
        boolean isModified = false;

        Iterator<E> iterator = iterator();
        while (iterator.hasNext()) {
            if (!collection.contains(iterator.next())) {
                iterator.remove();
                isModified = true;
            }
        }

        return isModified;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public <T> T[] toArray(final T[] array) throws NotImplementedException {
        if (array.length < size) {
            return null;
        }

        System.arraycopy(data, 0, array, 0, size);
        if (array.length > size) {
            array[size] = null;
        }

        return array;
    }
}
