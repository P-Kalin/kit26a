package ua.khpi.oop.malokhvii05.util.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public final class ArrayList<E> extends AbstractList<E> {

    private class ArrayListIterator implements ListIterator<E> {

        private int nextIndex;

        private ArrayListIterator(final int nextIndex) {
            this.nextIndex = nextIndex;
        }

        @Override
        public boolean hasNext() {
            return nextIndex != size;
        }

        @Override
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
            return (E) ArrayList.this.data[currentIndex];
        }

        @SuppressWarnings("unchecked")
        @Override
        public E previous() {
            int currentIndex = nextIndex - 1;
            if (currentIndex < 0) {
                throw new NoSuchElementException();
            }

            nextIndex = currentIndex;
            return (E) ArrayList.this.data[currentIndex];
        }

        @Override
        public void remove() {
            if (size == 0) {
                return;
            }

            ArrayList.this.removeElement(nextIndex);
        }
    }

    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    private Object[] data;

    public ArrayList() {
        data = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    @Override
    public boolean add(final E element) {
        addLast(element);
        return true;
    }

    @Override
    public void add(final int index, final E element) {
        isNewElementIndexInRange(index);

        ensureCapacity(size + 1);
        System.arraycopy(data, index, data, index + 1, size - index);

        data[index] = element;
        size++;
    }

    @Override
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

    @Override
    public void addBefore(final int index, final E element) {
        add(index - 1, element);
    }

    @Override
    public void addFirst(final E element) {
        add(0, element);
    }

    @Override
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

    @Override
    public ListIterator<E> firstIterator() {
        return listIterator(0);
    }

    @SuppressWarnings("unchecked")
    @Override
    public E get(final int index) {
        return (E) data[index];
    }

    @SuppressWarnings("unchecked")
    @Override
    public E getFirst() {
        if (isEmpty()) {
            return null;
        }

        return (E) data[0];
    }

    @SuppressWarnings("unchecked")
    @Override
    public E getLast() {
        if (isEmpty()) {
            return null;
        }

        return (E) data[size - 1];
    }

    @Override
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

    @Override
    public ListIterator<E> lastIterator() {
        return listIterator(size - 1);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        isIndexInRange(index);
        return new ArrayListIterator(index);
    }

    @Override
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

    @Override
    public void removeFirst() {
        removeElement(0);
    }

    @Override
    public void removeLast() {
        removeElement(size - 1);
    }

    @SuppressWarnings("unchecked")
    @Override
    public E set(final int index, final E element) {
        isIndexInRange(index);

        E oldElement = (E) data[index];
        data[index] = element;

        return oldElement;
    }

    @Override
    public Object[] toArray() {
        return data;
    }
}
