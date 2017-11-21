package ua.khpi.oop.malokhvii05.common.collect;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.errorprone.annotations.CanIgnoreReturnValue;

/**
 * Призначений, для оголошення загального інтерфейcу колекцій у вигляді списку,
 * переліку елементів.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.1
 * @see ua.khpi.oop.malokhvii05.common.algorithms
 * @see ua.khpi.oop.malokhvii05.common.algorithms.sort
 * @see ua.khpi.oop.malokhvii05.common.algorithms.search
 * @param <E>
 *            Тип елементів розташованих у колекції
 */
public interface List<E> extends Collection<E>, Serializable {

    /**
     * Призначений, для оголошення загального інтерфейсу для ітератора лінійних
     * колекцій.
     *
     * @author malokhvii-eduard (malokhvii.ee@gmail.com)
     * @version 1.0.0
     * @param <E>
     *            Тип елементів, які повертає ітератор
     */
    interface ListIterator<E> extends Iterator<E> {

        /**
         * Призначений, для перевірки чи є наступний елемент.
         *
         * @return результат перевірки
         * @since 1.0.0
         */
        @Override
        boolean hasNext();

        /**
         * Призначений, для перевірки чи є попередній елемент.
         *
         * @return результат перевірки
         * @since 1.0.0
         */
        boolean hasPrevious();

        /**
         * Призначений, для отримання значення наступного елементу.
         *
         * @return наступний елемент з колекції
         * @since 1.0.0
         */
        @Override
        @Nullable
        E next();

        /**
         * Призначений, для отримання значення попереднього елементу.
         *
         * @return попередній елемент з колекції
         * @since 1.0.0
         */
        @Nullable
        E previous();
    }

    /**
     * Призначений, для розширення функціоналу списку, за допомогою зовнішніх
     * компонентів.
     *
     * @param listVisitor
     *            компонет, розширюючий функціонал списку
     * @since 1.0.1
     */
    void acceptVisitor(@Nonnull ListVisitor<E> listVisitor);

    /**
     * Призначений, для додавання нового елементу у кінець колекції.
     *
     * @param element
     *            елемент для додавання у колекцію
     * @return результат додавання
     * @since 1.0.0
     */
    @Override
    @CanIgnoreReturnValue
    boolean add(@Nullable E element);

    /**
     * Призначений, для додавання елементу після отриманого індекса.
     *
     * @param index
     *            індекс для додавання
     * @param element
     *            елемент для додавання у колекцію
     * @return результат додавання
     * @since 1.0.0
     */
    @CanIgnoreReturnValue
    boolean add(@Nonnegative int index, @Nullable E element);

    /**
     * Призначений, для додавання елементу після отриманого індекса.
     *
     * @param index
     *            індекс для додавання
     * @param element
     *            елемент для додавання у колекцію
     * @return результат додавання
     * @since 1.0.0
     */
    @CanIgnoreReturnValue
    boolean addAfter(@Nonnegative int index, @Nullable E element);

    /**
     * Призначений, для додавання усіх елементів іншої колекції до колекції.
     *
     * @param elements
     *            масив елементів
     * @return результат додавання
     * @since 1.0.0
     */
    @Override
    @CanIgnoreReturnValue
    boolean addAll(@Nullable Collection<? extends E> elements);

    /**
     * Призначений, для додавання усіх елементів масиву до колекції.
     *
     * @param elements
     *            масив елементів
     * @return результат додавання
     * @since 1.0.0
     */
    @CanIgnoreReturnValue
    boolean addAll(@Nullable E[] elements);

    /**
     * Призначений, для додавання елементу перед отриманим індексом.
     *
     * @param index
     *            індекс для додавання
     * @param element
     *            елемент для додавання у колекцію
     * @return результат додавання
     * @since 1.0.0
     */
    @CanIgnoreReturnValue
    boolean addBefore(@Nonnegative int index, @Nullable E element);

    /**
     * Призначений, для додавання нового елементу на початок колекції.
     *
     * @param element
     *            елемент для додавання у колекцію
     * @return результат додавання
     * @since 1.0.0
     */
    @CanIgnoreReturnValue
    boolean addFirst(@Nullable E element);

    /**
     * Призначений, для додавання нового елементу у кінець колекції.
     *
     * @param element
     *            елемент для додавання у колекцію
     * @return результат додавання
     * @since 1.0.0
     */
    @CanIgnoreReturnValue
    boolean addLast(@Nullable E element);

    /**
     * Призначений, для очищення колекції від елементів.
     *
     * @since 1.0.0
     */
    @Override
    void clear();

    /**
     * Призначений, для перевірки на наявність елемента у колекції.
     *
     * @param object
     *            елемент для перевірки
     * @return результат перевірки
     * @since 1.0.0
     */
    @Override
    boolean contains(@Nullable Object object);

    /**
     * Призначений, для перевірки на наявність усіх елементів отриманої колекції
     * у поточній колекції.
     *
     * @param elements
     *            елементи, для перевірки на наявність
     * @return результат перевірки
     * @since 1.0.0
     */
    @Override
    boolean containsAll(@Nullable Collection<?> elements);

    /**
     * Призначений, для отримання об'єкту {@link ListIterator ітератора}.
     *
     * @return об'єкт ітератора проініціалізований початковим індексом, якщо
     *         колекція порожня тоді {@link EmptyList.EmptyListIterator порожній
     *         ітератор}, або не коректний індекс
     * @since 1.0.0
     */
    @Nonnull
    ListIterator<E> firstIterator();

    /**
     * Призначений, для отримання задовільного елементу колекції за індексом.
     *
     * @param index
     *            індекс елементу
     * @return значення елементу в колекції
     * @since 1.0.0
     */
    @Nullable
    E get(@Nonnegative int index);

    /**
     * Призначений, для отримання першого елементу колекції.
     *
     * @return перший елемент колекції
     * @since 1.0.0
     */
    @Nullable
    E getFirst();

    /**
     * Призначений, для отримання останнього елементу колекції.
     *
     * @return останній елемент колекції
     * @since 1.0.0
     */
    @Nullable
    E getLast();

    /**
     * Призначений, для отримання індексу об'єкту в колекції, якщо він
     * присутній. пошук об'єкту виконується за допомогою лінійного пошуку. Для
     * швидкішого пошуку див. пакет
     * {@link ua.khpi.oop.malokhvii05.common.algorithms.search}.
     *
     * @param element
     *            об'єкт для пошуку
     * @return індекс елементу колекції, якщо не знайдено тоді повертає
     *         {@link ua.khpi.oop.malokhvii05.common.algorithms.search.SearchAlgorithm#INDEX_NOT_FOUND}
     * @since 1.0.0
     */
    @Nonnegative
    int indexOf(@Nullable Object element);

    /**
     * Призначений, для отримання стану колекції, тобто перевірка на порожність.
     *
     * @return стан колекції
     * @since 1.0.0
     */
    @Override
    boolean isEmpty();

    /**
     * Призначений, для отримання об'єкту {@link ListIterator ітератора}.
     *
     * @return об'єкт ітератора проініціалізований індексом на перший елемент
     *         колекції, якщо колекція порожня тоді
     *         {@link EmptyList.EmptyListIterator порожній ітератор}
     */
    @Override
    @Nonnull
    Iterator<E> iterator();

    /**
     * Призначений, для отримання об'єкту {@link ListIterator ітератора}.
     *
     * @param index
     *            початковий індекс для ітератора
     * @return об'єкт ітератора проініціалізований початковим індексом, якщо
     *         колекція порожня тоді {@link EmptyList.EmptyListIterator порожній
     *         ітератор}, або не коректний індекс
     * @since 1.0.0
     */
    @Nonnull
    ListIterator<E> iterator(@Nonnegative int index);

    /**
     * Призначений, для отримання об'єкту {@link ListIterator ітератора}.
     *
     * @return об'єкт ітератора проініціалізований останнім індексом, якщо
     *         колекція порожня тоді {@link EmptyList.EmptyListIterator порожній
     *         ітератор}, або не коректний індекс
     * @since 1.0.0
     */
    @Nonnull
    ListIterator<E> lastIterator();

    /**
     * Призначений, для видалення задовільного елементу колекції за індексом.
     *
     * @param index
     *            індекс елементу колекції для видалення
     * @return видалене значення
     * @since 1.0.0
     */
    @CanIgnoreReturnValue
    @Nullable
    E remove(@Nonnegative int index);

    /**
     * Призначений, для видалення задовільного елементу колекції за посиланням.
     *
     * @param object
     *            елемент колекції для видалення
     * @return результат видалення
     * @since 1.0.0
     */
    @Override
    boolean remove(@Nullable Object object);

    /**
     * Призначений, для видалення усіх елементів отриманої колекції з поточної
     * колекції.
     *
     * @param elements
     *            елементи, для видалення
     * @return результат видалення
     * @since 1.0.0
     */
    @Override
    @CanIgnoreReturnValue
    boolean removeAll(@Nullable Collection<?> elements);

    /**
     * Призначений, для видалення першого елементу колекції.
     *
     * @return видалене значення
     * @since 1.0.0
     */
    @CanIgnoreReturnValue
    @Nullable
    E removeFirst();

    /**
     * Призначений, для видалення останнього елементу колекції.
     *
     * @return видалене значення
     * @since 1.0.0
     */
    @CanIgnoreReturnValue
    @Nullable
    E removeLast();

    /**
     * Призначений, для видалення усіх елементів отриманої колекції з поточної
     * колекції, які не присутні в отриманій колекції.
     *
     * @param elements
     *            елементи, для видалення
     * @return результат видалення
     * @since 1.0.0
     */
    @Override
    @CanIgnoreReturnValue
    boolean retainAll(@Nullable Collection<?> elements);

    /**
     * Призначений, для оновлення задовільного значення колекції за індексом.
     *
     * @param index
     *            індекс елементу для оновлення
     * @param element
     *            оновлене значення елементу
     * @return попереднє значення елементу
     * @since 1.0.0
     */
    @CanIgnoreReturnValue
    @Nullable
    E set(@Nonnegative int index, @Nullable E element);

    /**
     * Призначений, для оновлення значення першого елементу колекції.
     *
     * @param element
     *            оновлене значення першого елементу колекції
     * @return попереднє значення першого елементу колекції
     * @since 1.0.0
     */
    @CanIgnoreReturnValue
    @Nullable
    E setFirst(@Nullable E element);

    /**
     * Призначений, для оновлення значення останнього елементу колекції.
     *
     * @param element
     *            оновлене значення останнього елементу колекції
     * @return попереднє значення останнього елементу колекції
     * @since 1.0.0
     */
    @CanIgnoreReturnValue
    @Nullable
    E setLast(@Nullable E element);

    /**
     * Призначений, для отримання кількості елементів розміщених у колекції.
     *
     * @return кількість елементів розміщених у колекції
     * @since 1.0.0
     */
    @Override
    @Nonnegative
    int size();

    /**
     * Призначений, для отримання масиву із усіх елементів в колекції.
     *
     * @return масив із усіх елементів в колекції
     * @since 1.0.0
     */
    @Override
    @Nonnull
    Object[] toArray();

    /**
     * Призначений, для отримання масиву із усіх елементів колекції, але тип
     * масиву відповідає типу елементів розміщених у колекції.
     *
     * @return масив із усіх елементів в колекції
     * @since 1.0.0
     */
    @Override
    @Nonnull
    <T> T[] toArray(@Nullable T[] array);
}
