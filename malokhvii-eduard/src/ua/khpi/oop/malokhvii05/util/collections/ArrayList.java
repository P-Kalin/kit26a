package ua.khpi.oop.malokhvii05.util.collections;

import java.util.Collection;
import java.util.Iterator;

public final class ArrayList<E> extends AbstractList<E> {

    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private Object[] data;

    public ArrayList() {
        data = new Object[DEFAULT_INITIAL_CAPACITY];
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

    @SuppressWarnings("unchecked")
    @Override
    public E get(final int index) {
        return (E) data[index];
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
    public void add(final int index, final E element) {
        isNewElementIndexInRange(index);

        ensureCapacity(size + 1);
        System.arraycopy(data, index, data, index + 1, size - index);

        data[index] = element;
        size++;
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
    public void addBefore(final int index, final E element) {
        add(index - 1, element);
    }

    @Override
    public void addAfter(final int index, final E element) {
        add(index + 1, element);
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

    @Override
    public void remove(final int index) {
        isIndexInRange(index);
        removeElement(index);
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

    @Override
    public Iterator<E> iterator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object[] toArray() {
        return data;
    }

    @Override
    public boolean add(final E element) {
        addLast(element);
        return true;
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
    public void clear() {
        int index;
        for (index = 0; index < size; index++) {
            data[index] = null;
        }
        size = 0;
    }
}
