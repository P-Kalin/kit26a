package ua.khpi.oop.malokhvii05.util.collections;

import java.util.Collection;

public interface List<E> extends Collection<E> {

    E get(int index);

    E set(int index, E element);

    void add(int index, E element);

    E getFirst();

    E getLast();

    void addBefore(int index, E element);

    void addAfter(int index, E element);

    void addFirst(E element);

    void addLast(E element);

    void removeFirst();

    void removeLast();

    void remove(int index);

    int indexOf(Object object);
}
