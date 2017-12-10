package ua.khpi.oop.malokhvii05.common.collect;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.NoSuchElementException;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.errorprone.annotations.CanIgnoreReturnValue;

import ua.khpi.oop.malokhvii05.common.algorithms.search.SearchAlgorithm;
import ua.khpi.oop.malokhvii05.common.collect.EmptyList.EmptyListIterator;

/**
 * Призначений, для збереження однотипних елементів у вигляді звичайного
 * динамічного масиву. Для обробки елементів див. детальнше пакет з типовими
 * алгоритмами.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.1
 * @see ua.khpi.oop.malokhvii05.common.algorithms
 * @see ua.khpi.oop.malokhvii05.common.algorithms.sort
 * @see ua.khpi.oop.malokhvii05.common.algorithms.search
 * @param <E>
 *            Тип елементів розташованих у масиві
 */
public final class ArrayList<E> extends AbstractList<E> {

    /**
     * Призначений, для проходження через елементи масиву. Реалізує двохстороннє
     * ітерування, тобто в обидва напрямки, та видалення поточного елементу.
     *
     * @author malokhvii-eduard (malokhvii.ee@gmail.com)
     * @version 1.0.0
     */
    private final class ArrayListIterator implements ListIterator<E> {

        /**
         * Наступний індекс, за яким необхідно отримати значення.
         *
         * @since 1.0.0
         */
        private int nextIndex;

        /**
         * Призначений, для ініціалізації ітератора індексом початкового
         * елементу.
         *
         * @param index
         *            індекст початкового елементу для проходження
         * @since 1.0.0
         */
        private ArrayListIterator(@Nonnegative final int index) {
            this.nextIndex = index;
        }

        @Override
        public boolean hasNext() {
            return this.nextIndex != ArrayList.this.size;
        }

        @Override
        public boolean hasPrevious() {
            return this.nextIndex != 0;
        }

        @SuppressWarnings("unchecked")
        @Override
        public @Nullable E next() {
            if (this.nextIndex == ArrayList.this.size) {
                throw new NoSuchElementException();
            }

            final int currentIndex = this.nextIndex;
            this.nextIndex++;
            return (E) ArrayList.this.data[currentIndex];
        }

        @SuppressWarnings("unchecked")
        @Override
        public @Nullable E previous() {
            final int currentIndex = this.nextIndex - 1;
            if (currentIndex < 0) {
                throw new NoSuchElementException();
            }

            this.nextIndex = currentIndex;
            return (E) ArrayList.this.data[currentIndex];
        }

        @Override
        public void remove() {
            if (ArrayList.this.size == 0) {
                return;
            }

            ArrayList.this.removeElement(this.nextIndex);
        }
    }

    /**
     * Початковий розмір внутрішнього буфера елементів.
     *
     * @since 1.0.0
     */
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    private static final long serialVersionUID = -2682051498433821439L;

    /**
     * Внутрішній буфер елементів масиву.
     *
     * @since 1.0.0
     */
    transient Object[] data;

    /**
     * Призначений, для ініціалізації масиву з ємкостю внутрішнього буфера за
     * змовчуванням.
     *
     * @since 1.0.0
     */
    public ArrayList() {
        this(ArrayList.DEFAULT_INITIAL_CAPACITY);
    }

    /**
     * Призначений, для ініціалізації масиву елементами з отриманої колекції.
     *
     * @param elements
     *            колекція з якої будуть скопійовані елементи
     * @since 1.0.0
     */
    public ArrayList(@Nullable final Collection<? extends E> elements) {
        this();
        addAll(elements);
    }

    /**
     * Призначений, для ініціалізації масиву елементами з отриманого масиву.
     *
     * @param elements
     *            масив з якого будуть скопійовані елементи
     * @since 1.0.0
     */
    public ArrayList(@Nullable final E[] elements) {
        this();
        addAll(elements);
    }

    /**
     * Призначений, для ініціалізації масиву з задовільним розміром ємкості
     * внутрішьного буфера.
     *
     * @param capacity
     *            задовільна ємкість внутрішнього буфера
     * @since 1.0.0
     */
    public ArrayList(@Nonnegative final int capacity) {
        checkArgument(capacity > 0);
        data = new Object[capacity];
    }

    @Override
    public void acceptVisitor(final ListVisitor<E> listVisitor) {
        checkNotNull(listVisitor);
        listVisitor.visit(this);
    }

    @Override
    @CanIgnoreReturnValue
    public boolean add(@Nullable final E element) {
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
     * @since 1.0.0
     */
    @Override
    @CanIgnoreReturnValue
    public boolean add(@Nonnegative final int index,
            @Nullable final E element) {
        if (!isNewElementIndexInRange(index)) {
            return false;
        }

        ensureCapacity(size + 1);
        System.arraycopy(data, index, data, index + 1, size - index);

        data[index] = element;
        size++;
        return true;
    }

    @Override
    @CanIgnoreReturnValue
    public boolean addAfter(@Nonnegative final int index,
            @Nullable final E element) {
        return add(index + 1, element);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean addAll(@Nullable final Collection<? extends E> collection) {
        if (collection == null || collection.isEmpty()) {
            return false;
        }
        return addAll((E[]) collection.toArray());
    }

    @Override
    @CanIgnoreReturnValue
    public boolean addAll(@Nullable final E[] elements) {
        if (elements == null || elements.length < 1) {
            return false;
        }
        final int amountOfNewElements = elements.length;

        this.ensureCapacity(size + amountOfNewElements);
        System.arraycopy(elements, 0, this.data, size, amountOfNewElements);
        size += amountOfNewElements;

        return amountOfNewElements != 0;
    }

    @Override
    @CanIgnoreReturnValue
    public boolean addBefore(@Nonnegative final int index,
            @Nullable final E element) {
        return add(index - 1, element);
    }

    @Override
    @CanIgnoreReturnValue
    public boolean addFirst(@Nullable final E element) {
        return add(0, element);
    }

    @Override
    @CanIgnoreReturnValue
    public boolean addLast(@Nullable final E element) {
        ensureCapacity(size + 1);
        data[size++] = element;
        return true;
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
     * @since 1.0.0
     */
    private void ensureCapacity(@Nonnegative final int minimumCapacity) {
        final int oldCapacity = data.length;
        if (minimumCapacity > oldCapacity) {
            int newCapacity = oldCapacity * 3 / 2 + 1;
            if (newCapacity < minimumCapacity) {
                newCapacity = minimumCapacity;
            }

            final Object[] copy = new Object[newCapacity];
            System.arraycopy(data, 0, copy, 0,
                    Math.min(data.length, newCapacity));
            data = copy;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public @Nullable E get(@Nonnegative final int index) {
        if (!isIndexInRange(index)) {
            return null;
        }

        return (E) data[index];
    }

    @Override
    @SuppressWarnings("unchecked")
    public @Nullable E getFirst() {
        if (isEmpty()) {
            return null;
        }

        return (E) data[0];
    }

    @Override
    @SuppressWarnings("unchecked")
    public @Nullable E getLast() {
        if (isEmpty()) {
            return null;
        }

        return (E) data[size - 1];
    }

    @Override
    public int indexOf(@Nullable final Object object) {
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

    @Override
    public @Nonnull ListIterator<E> iterator(@Nonnegative final int index) {
        if (isIndexInRange(index)) {
            return new ArrayListIterator(index);
        }

        return new EmptyListIterator<>();
    }

    @Override
    protected void readObject(
            @Nonnull final ObjectInputStream objectInputStream)
            throws ClassNotFoundException, IOException {
        checkNotNull(objectInputStream);
        objectInputStream.defaultReadObject();
        final int dataArraySize = objectInputStream.readInt();

        if (dataArraySize >= 0) {
            size = 0;
            data = new Object[dataArraySize];

            int index;
            for (index = 0; index < dataArraySize; index++) {
                data[index] = objectInputStream.readObject();
                size++;
            }
        }
    }

    @Override
    @CanIgnoreReturnValue
    public @Nullable E remove(@Nonnegative final int index) {
        if (isIndexInRange(index)) {
            final E element = get(index);
            removeElement(index);
            return element;
        }
        return null;
    }

    @Override
    @CanIgnoreReturnValue
    public boolean remove(@Nullable final Object object) {
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
     * @since 1.0.0
     */
    private void removeElement(@Nonnegative final int index) {
        final int amountOfMovedElements = size - index - 1;
        if (amountOfMovedElements > 0) {
            System.arraycopy(data, index + 1, data, index,
                    amountOfMovedElements);
        }

        data[--size] = null;
    }

    @Override
    @CanIgnoreReturnValue
    public @Nullable E removeFirst() {
        final E element = getFirst();
        removeElement(0);
        return element;
    }

    @Override
    @CanIgnoreReturnValue
    public @Nullable E removeLast() {
        final E element = getLast();
        removeElement(size - 1);
        return element;
    }

    @SuppressWarnings("unchecked")
    @Override
    @CanIgnoreReturnValue
    public @Nullable E set(@Nonnegative final int index,
            @Nullable final E element) {
        if (!isIndexInRange(index)) {
            return null;
        }

        final E oldElement = (E) data[index];
        data[index] = element;

        return oldElement;
    }

    @SuppressWarnings("unchecked")
    @Override
    @CanIgnoreReturnValue
    public @Nullable E setFirst(@Nullable final E element) {
        if (isEmpty()) {
            return null;
        }

        final Object oldElement = data[0];
        data[0] = element;
        return (E) oldElement;
    }

    @SuppressWarnings("unchecked")
    @Override
    @CanIgnoreReturnValue
    public @Nullable E setLast(@Nullable final E element) {
        if (isEmpty()) {
            return null;
        }

        final Object oldElement = data[size - 1];
        data[size - 1] = element;
        return (E) oldElement;
    }

    @Override
    @Nonnull
    public Object[] toArray() {
        final Object[] array = new Object[size];
        System.arraycopy(data, 0, array, 0, size);
        return array;
    }

    @SuppressWarnings("unchecked")
    @Override
    public @Nullable <T> T[] toArray(@Nullable T[] array) {
        if (array == null || array.length < size) {
            array = (T[]) new Object[array.length];
            System.arraycopy(this.data, 0, array, 1, size);
        }

        System.arraycopy(this.data, 0, array, 0, size);
        if (array.length > size) {
            array[size] = null;
        }

        return array;
    }

    @Override
    protected void writeObject(
            @Nonnull final ObjectOutputStream objectOutputStream)
            throws IOException {
        checkNotNull(objectOutputStream);
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(this.data.length);

        int index;
        for (index = 0; index < size; index++) {
            objectOutputStream.writeObject(this.data[index]);
        }
    }
}
