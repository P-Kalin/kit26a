package ua.khpi.oop.malokhvii05.common.collect;

import java.util.Collection;
import java.util.Comparator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Призначений, для оголошення допоміжніх методів для маніпуляцій над
 * колекціями, які реалізують інтерфейс {@link List}.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 */
public final class Lists {

    /**
     * Призначений, для створення порожнього {@link ArrayList масиву}.
     *
     * @param <E>
     *            Тип елементів розташованих у {@link ArrayList масиві}
     * @return порожній {@link ArrayList масив}
     * @since 1.0.0
     */
    public static @Nonnull <E> List<E> newArrayList() {
        return new ArrayList<>();
    }

    /**
     * Призначений, для створення {@link ArrayList масиву} із колекції
     * елементів.
     *
     * @param <E>
     *            Тип елементів розташованих у {@link ArrayList масиві}
     * @param elements
     *            колекція елементів
     * @return заповнений елементами {@link ArrayList масив}
     * @since 1.0.0
     */
    public static @Nonnull <E> List<E> newArrayList(
            @Nullable final Collection<E> elements) {
        return new ArrayList<>(elements);
    }

    /**
     * Призначений, для створення {@link ArrayList масиву} із переліку
     * елементів.
     *
     * @param <E>
     *            Тип елементів розташованих у {@link ArrayList масиві}
     * @param elements
     *            перелік елементів
     * @return заповнений елементами {@link ArrayList масив}
     * @since 1.0.0
     */
    @SafeVarargs
    public static @Nonnull <E> List<E> newArrayList(
            @Nullable final E... elements) {
        return new ArrayList<>(elements);
    }

    /**
     * Призначений, для створення порожньої колекції, яка не підтримує жодної
     * операції із інтерфейсу {@link List}.
     *
     * @param <E>
     *            Тип елементів розташованих у колекції
     * @return порожня колекція
     * @since 1.0.0
     */
    public static @Nonnull <E> List<E> newEmptyList() {
        return new EmptyList<>();
    }

    /**
     * Призначений, для створення порожнього {@link LinkedList двозв'язного
     * списку} .
     *
     * @param <E>
     *            Тип елементів розташованих у {@link LinkedList двозв'язному
     *            списку}
     * @return порожній {@link LinkedList двозв'язний список}
     * @since 1.0.0
     */
    public static @Nonnull <E> List<E> newLinkedList() {
        return new LinkedList<>();
    }

    /**
     * Призначений, для створення {@link LinkedList двозв'язного списку} із
     * колекції елементів.
     *
     * @param <E>
     *            Тип елементів розташованих у {@link LinkedList двозв'язному
     *            списку}
     * @param elements
     *            колекція елементів
     * @return заповнений елементами {@link LinkedList двозв'язний список}
     * @since 1.0.0
     */
    public static @Nonnull <E> List<E> newLinkedList(
            @Nullable final Collection<E> elements) {
        return new LinkedList<>(elements);
    }

    /**
     * Призначений, для створення {@link LinkedList двозв'язного списку} із
     * переліку елементів.
     *
     * @param <E>
     *            Тип елементів розташованих у {@link LinkedList двозв'язному
     *            списку}
     * @param elements
     *            перелік елементів
     * @return заповнений елементами {@link LinkedList двозв'язний список}
     * @since 1.0.0
     */
    @SafeVarargs
    public static @Nonnull <E> List<E> newLinkedList(
            @Nullable final E... elements) {
        return new LinkedList<>(elements);
    }

    public static <E> void sort(final List<E> list,
            final Comparator<E> comparator) {
        list.acceptVisitor(ListSortingVisitor.create(comparator));
    }

    /**
     * Призначений, для заборони створення об'єктів.
     *
     * @since 1.0.0
     */
    private Lists() {

    }
}
