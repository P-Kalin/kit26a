package ua.khpi.oop.malokhvii05.util.collections;

import java.util.Collection;
import java.util.Iterator;

public interface List<E> extends Collection<E> {

    public interface ListIterator<E> extends Iterator<E> {

        boolean hasPrevious();

        E previous();
    }

    void add(int index, E element);

    void addAfter(int index, E element);

    void addBefore(int index, E element);

    void addFirst(E element);

    void addLast(E element);

    ListIterator<E> firstIterator();

    E get(int index);

    E getFirst();

    E getLast();

    int indexOf(Object object);

    ListIterator<E> lastIterator();

    ListIterator<E> listIterator(int index);

    void remove(int index);

    void removeFirst();

    void removeLast();

    E set(int index, E element);
}
