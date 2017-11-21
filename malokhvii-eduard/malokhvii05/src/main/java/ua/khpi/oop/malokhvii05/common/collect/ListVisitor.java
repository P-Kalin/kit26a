package ua.khpi.oop.malokhvii05.common.collect;

import javax.annotation.Nonnull;

/**
 * Призначений, для оголошення загального інтерфейсу зовнішніх компонентів для
 * розширення колекцій (списків) додатковим функціоналом.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @param <E>
 *            Тип елементів розташованих у колекції
 * @version 1.0.0
 */
public interface ListVisitor<E> {

    /**
     * Призначений, для обробки {@link ArrayList масивів} елементів.
     *
     * @param arrayList
     *            {@link ArrayList масив} елементів
     * @since 1.0.0
     */
    void visit(@Nonnull ArrayList<E> arrayList);

    /**
     * Призначений, для обробки порожньої колекції ({@link EmptyList заглушки}).
     *
     * @param emptyList
     *            порожня колекція (заглушка)
     * @since 1.0.0
     */
    default void visit(@Nonnull final EmptyList<E> emptyList) {

    }

    /**
     * Призначений, для обробки {@link LinkedList двозв'язних списків}
     * елементів.
     *
     * @param linkedList
     *            {@link LinkedList двозв'язний список} елементів
     * @since 1.0.0
     */
    void visit(@Nonnull LinkedList<E> linkedList);
}
