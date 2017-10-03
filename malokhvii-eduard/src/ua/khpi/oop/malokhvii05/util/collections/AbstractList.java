package ua.khpi.oop.malokhvii05.util.collections;

import java.util.Collection;
import java.util.Iterator;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public abstract class AbstractList<E> implements List<E> {

    protected int size;

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
