package ua.khpi.oop.malokhvii05.common.collect;

import java.util.Collection;
import java.util.Iterator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.errorprone.annotations.CanIgnoreReturnValue;

import ua.khpi.oop.malokhvii05.common.algorithms.search.SearchAlgorithm;

/**
 * Призначений, для використання у якості заглушки на випдки необхідності
 * порожньої колекції.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 * @see ua.khpi.oop.malokhvii05.common.algorithms
 * @see ua.khpi.oop.malokhvii05.common.algorithms.sort
 * @see ua.khpi.oop.malokhvii05.common.algorithms.search
 * @param <E>
 *            Тип елементів розташованих у колекції
 */
class EmptyList<E> implements List<E> {

    /**
     * Призначений, для використання у якості заглушки на випдки необхідності
     * порожнього ітератора.
     *
     * @author malokhvii-eduard (malokhvii.ee@gmail.com)
     * @version 1.0.0
     * @param <E>
     *            Тип елементів, які повертає ітератор
     */
    static class EmptyListIterator<E> implements ListIterator<E> {

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public boolean hasPrevious() {
            return false;
        }

        @Override
        public @Nullable E next() {
            return null;
        }

        @Override
        public @Nullable E previous() {
            return null;
        }
    }

    private static final long serialVersionUID = -1743173312260000598L;

    @Override
    @CanIgnoreReturnValue
    public boolean add(@Nullable final E element) {
        return false;
    }

    @Override
    @CanIgnoreReturnValue
    public boolean add(@Nonnegative final int index,
            @Nullable final E element) {
        return false;
    }

    @Override
    @CanIgnoreReturnValue
    public boolean addAfter(@Nonnegative final int index,
            @Nullable final E element) {
        return false;
    }

    @Override
    @CanIgnoreReturnValue
    public boolean addAll(@Nullable final Collection<? extends E> elements) {
        return false;
    }

    @Override
    @CanIgnoreReturnValue
    public boolean addAll(@Nullable final E[] elements) {
        return false;
    }

    @Override
    @CanIgnoreReturnValue
    public boolean addBefore(@Nonnegative final int index,
            @Nullable final E element) {
        return false;
    }

    @Override
    @CanIgnoreReturnValue
    public boolean addFirst(@Nullable final E element) {
        return false;
    }

    @Override
    @CanIgnoreReturnValue
    public boolean addLast(@Nullable final E element) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean contains(@Nullable final Object object) {
        return false;
    }

    @Override
    public boolean containsAll(@Nullable final Collection<?> elements) {
        return false;
    }

    @Override
    public ListIterator<E> firstIterator() {
        return new EmptyListIterator<>();
    }

    @Override
    public @Nullable E get(@Nonnegative final int index) {
        return null;
    }

    @Override
    public @Nullable E getFirst() {
        return null;
    }

    @Override
    public @Nullable E getLast() {
        return null;
    }

    @Override
    public int indexOf(@Nullable final Object element) {
        return SearchAlgorithm.INDEX_NOT_FOUND;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public @Nonnull Iterator<E> iterator() {
        return new EmptyListIterator<>();
    }

    @Override
    public @Nonnull ListIterator<E> iterator(final int index) {
        return new EmptyListIterator<>();
    }

    @Override
    public @Nonnull ListIterator<E> lastIterator() {
        return new EmptyListIterator<>();
    }

    @Override
    @CanIgnoreReturnValue
    public @Nullable E remove(@Nonnegative final int index) {
        return null;
    }

    @Override
    @CanIgnoreReturnValue
    public boolean remove(@Nullable final Object object) {
        return false;
    }

    @Override
    @CanIgnoreReturnValue
    public boolean removeAll(@Nullable final Collection<?> elements) {
        return false;
    }

    @Override
    @CanIgnoreReturnValue
    public @Nullable E removeFirst() {
        return null;
    }

    @Override
    @CanIgnoreReturnValue
    public @Nullable E removeLast() {
        return null;
    }

    @Override
    @CanIgnoreReturnValue
    public boolean retainAll(@Nullable final Collection<?> elements) {
        return false;
    }

    @Override
    @CanIgnoreReturnValue
    public @Nullable E set(@Nonnegative final int index,
            @Nullable final E element) {
        return null;
    }

    @Override
    @CanIgnoreReturnValue
    public @Nullable E setFirst(@Nullable final E element) {
        return null;
    }

    @Override
    @CanIgnoreReturnValue
    public @Nullable E setLast(@Nullable final E element) {
        return null;
    }

    @Override
    public @Nonnegative int size() {
        return 0;
    }

    @Override
    public @Nullable Object[] toArray() {
        return null;
    }

    @Override
    public @Nullable <T> T[] toArray(@Nullable final T[] array) {
        return array;
    }
}
