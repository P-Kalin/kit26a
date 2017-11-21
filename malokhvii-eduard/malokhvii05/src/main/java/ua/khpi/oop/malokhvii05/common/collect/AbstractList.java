package ua.khpi.oop.malokhvii05.common.collect;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Iterator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.errorprone.annotations.CanIgnoreReturnValue;

/**
 * Призначений, для оголошення загальної реалізації колекцій у вигляді списку,
 * переліку елементів для подальшого розширення спеціалізованими реалізаціями.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.1
 * @param <E>
 *            Тип елементів розташованих у колекції
 */
public abstract class AbstractList<E> implements List<E> {

    private static final long serialVersionUID = -4738663996022907190L;

    /**
     * Кількість елементів в колекції.
     *
     * @since 1.0.0
     */
    protected transient int size;

    @Override
    public final boolean contains(@Nullable final Object object) {
        return indexOf(object) != -1;
    }

    @Override
    public final boolean containsAll(@Nullable final Collection<?> elements) {
        if (elements == null || elements.isEmpty()) {
            return false;
        }

        Iterator<?> iterator = elements.iterator();
        while (iterator.hasNext()) {
            if (!contains(iterator.next())) {
                return false;
            }
        }

        return true;
    }

    @Override
    @Nonnull
    public final ListIterator<E> firstIterator() {
        return iterator(0);
    }

    @Override
    public final boolean isEmpty() {
        return size == 0;
    }

    /**
     * Призначений, для перевірки індексу на входження в розмір колекції під час
     * задовільного доступу до елементів колекції.
     *
     * @param index
     *            індекс для перевірки
     * @return результат перевірки
     * @since 1.0.0
     */
    protected boolean isIndexInRange(@Nonnegative final int index) {
        if (index >= size) {
            return false;
        }
        return true;
    }

    /**
     * Призначений, для перевірки індексу на вихід за розмір колекції під час
     * додавання в задовільну позицію колекції.
     *
     * @param index
     *            індекс для перевірки
     * @return результат перевірки
     * @since 1.0.0
     */
    protected boolean isNewElementIndexInRange(@Nonnegative final int index) {
        if (index > this.size || index < 0) {
            return false;
        }
        return true;
    }

    @Override
    @Nonnull
    public final Iterator<E> iterator() {
        return iterator(0);
    }

    @Override
    @Nonnull
    public final ListIterator<E> lastIterator() {
        return iterator(size - 1);
    }

    /**
     * Призначений, для десеріалізації колекції, яка була на передодні записана
     * за допомогою {@link ObjectOutputStream}. Визначення задовільної
     * послідовності десеріаліації.
     *
     * @param objectInputStream
     *            об'єкт потоку зчитування
     * @throws ClassNotFoundException
     *             клас серіалізованого об'єкта не може бути знайденим
     * @throws IOException
     *             помилка під час операції введення
     * @since 1.0.0
     */
    protected abstract void readObject(
            @Nonnull ObjectInputStream objectInputStream)
            throws ClassNotFoundException, IOException;

    @Override
    @CanIgnoreReturnValue
    public final boolean removeAll(@Nullable final Collection<?> elements) {
        if (elements == null || elements.isEmpty()) {
            return false;
        }

        boolean isModified = false;

        Iterator<?> iterator = iterator();
        while (iterator.hasNext()) {
            if (!elements.contains(iterator.next())) {
                iterator.remove();
                isModified = true;
            }
        }

        return isModified;
    }

    @Override
    @CanIgnoreReturnValue
    public final boolean retainAll(@Nullable final Collection<?> elements) {
        if (elements == null || elements.isEmpty()) {
            return false;
        }

        boolean isModified = false;

        Iterator<E> iterator = iterator();
        while (iterator.hasNext()) {
            if (!elements.contains(iterator.next())) {
                iterator.remove();
                isModified = true;
            }
        }

        return isModified;
    }

    @Override
    public final @Nonnegative int size() {
        return size;
    }

    /**
     * Призначений, для серіалізації колекції, за допомогою. Визначення
     * задовільної послідовності серіаліації.
     *
     * @param objectOutputStream
     *            об'єкт потоку запису
     * @throws IOException
     *             помилка під час операції виведення
     * @since 1.0.0
     */
    protected abstract void writeObject(
            @Nonnull ObjectOutputStream objectOutputStream) throws IOException;
}
