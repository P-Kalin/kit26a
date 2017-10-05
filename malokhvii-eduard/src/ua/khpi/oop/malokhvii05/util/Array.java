package ua.khpi.oop.malokhvii05.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public final class Array<E> implements Collection<E> {

    public class ArrayIterator implements Iterator<E> {

        private int nextIndex;

        private ArrayIterator(final int nextIndex) {
            this.nextIndex = nextIndex;
        }

        @Override
        public boolean hasNext() {
            return nextIndex != size;
        }

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

    private int size;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    private Object[] data;

    public Array(int capacity) {
        data = new Object[capacity];
    }

    public Array() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    @Override
    public boolean add(final E element) {
        addLast(element);
        return true;
    }

    public void add(final int index, final E element) {
        isNewElementIndexInRange(index);

        ensureCapacity(size + 1);
        System.arraycopy(data, index, data, index + 1, size - index);

        data[index] = element;
        size++;
    }

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

    public void addBefore(final int index, final E element) {
        add(index - 1, element);
    }

    public void addFirst(final E element) {
        add(0, element);
    }

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

    private void ensureCapacity(int minimumCapacity) {
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

    public ArrayIterator firstIterator() {
        return iterator(0);
    }

    @SuppressWarnings("unchecked")
    public E get(final int index) {
        return (E) data[index];
    }

    @SuppressWarnings("unchecked")
    public E getFirst() {
        if (isEmpty()) {
            return null;
        }

        return (E) data[0];
    }

    @SuppressWarnings("unchecked")
    public E getLast() {
        if (isEmpty()) {
            return null;
        }

        return (E) data[size - 1];
    }

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
        return -1;
    }

    private boolean isIndexInRange(final int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return true;
    }

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

    public ArrayIterator lastIterator() {
        return iterator(size - 1);
    }

    public ArrayIterator iterator(int index) {
        isIndexInRange(index);
        return new ArrayIterator(index);
    }

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

    private void removeElement(final int index) {
        int amountOfMovedElements = size - index - 1;
        if (amountOfMovedElements > 0) {
            System.arraycopy(data, index + 1, data, index,
                    amountOfMovedElements);
        }

        data[--size] = null;
    }

    public void removeFirst() {
        removeElement(0);
    }

    public void removeLast() {
        removeElement(size - 1);
    }

    @SuppressWarnings("unchecked")
    public E set(final int index, final E element) {
        isIndexInRange(index);

        E oldElement = (E) data[index];
        data[index] = element;

        return oldElement;
    }

    @SuppressWarnings("unchecked")
    public E setFirst(E element) {
        if (isEmpty()) {
            return null;
        }

        Object oldElement = data[0];
        data[0] = element;
        return (E) oldElement;
    }

    @SuppressWarnings("unchecked")
    public E setLast(E element) {
        if (isEmpty()) {
            return null;
        }

        Object oldElement = data[size - 1];
        data[size - 1] = element;
        return (E) oldElement;
    }

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
    public final boolean contains(final Object object) {
        return indexOf(object) != -1;
    }

    @Override
    public final boolean containsAll(final Collection<?> collection) {
        Iterator<?> iterator = collection.iterator();
        while (iterator.hasNext()) {
            if (!contains(iterator.next())) {
                return false;
            }
        }

        return true;
    }

    @Override
    public final boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public final boolean removeAll(final Collection<?> collection) {
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
    public final boolean retainAll(final Collection<?> collection) {
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
    public final int size() {
        return this.size;
    }

    @Override
    public <T> T[] toArray(final T[] array) {
        throw new NotImplementedException();
    }
}
