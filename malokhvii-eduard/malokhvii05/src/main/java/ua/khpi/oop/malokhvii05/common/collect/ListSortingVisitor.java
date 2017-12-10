package ua.khpi.oop.malokhvii05.common.collect;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Comparator;

import javax.annotation.Nonnull;

import ua.khpi.oop.malokhvii05.common.algorithms.sort.SortAlgorithm;
import ua.khpi.oop.malokhvii05.common.algorithms.sort.SortAlgorithmFactory;
import ua.khpi.oop.malokhvii05.common.collect.LinkedList.Node;

/**
 * Призначений, для реалізації алгоритмів сортування колекцій (списків). Кожна
 * колекція містить спеціалізовану реалізацію алгоритму сортування.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @param <E>
 *            Тип елементів розташованих у колекції
 * @version 1.0.0
 */
class ListSortingVisitor<E> implements ListVisitor<E> {

    /**
     * Призначений, для створення екземпляру компоненту сортування колекцій.
     *
     * @param comparator
     *            компаратор вхідних даних
     * @return компонет сорутвання колекцій
     * @since 1.0.0
     */
    static @Nonnull <E> ListVisitor<E> create(
            @Nonnull final Comparator<E> comparator) {
        return new ListSortingVisitor<>(comparator);
    }

    /**
     * Компоратор вхідних даних.
     *
     * @since 1.0.0
     */
    private final Comparator<E> comparator;

    /**
     * Призначений, для ініціалізації компоненту компаратором вхідних даних.
     *
     * @param comparator
     *            компаратор вхідних даних
     * @since 1.0.0
     */
    private ListSortingVisitor(@Nonnull final Comparator<E> comparator) {
        this.comparator = checkNotNull(comparator);
    }

    /**
     * Призначений, для визначення шарнірного елементу, розміщує в останньому
     * елементі шарнірний елемент, для подальшого визначення правильного
     * положення у відсортованому масиві, тобто елементи меньші ніж шарнір
     * наліво від шарніру, більші елементи праворуч від шарніра.
     *
     * @param leftNode
     *            початковий вузел діапазону
     * @param rightNode
     *            кінцевий вузел діапазону
     * @return вузел для розбиття списку на частини, тобто шарнір
     * @since 1.0.0
     */
    private @Nonnull Node<E> linkedListPartition(
            @Nonnull final Node<E> leftNode, @Nonnull final Node<E> rightNode) {
        final E element = rightNode.element;
        E tempElement;

        Node<E> i = leftNode.previous;
        for (Node<E> j = leftNode; j != rightNode; j = j.next) {
            if (comparator.compare(j.element, element) <= -1) {
                i = i == null ? leftNode : i.next;
                tempElement = i.element;
                i.element = j.element;
                j.element = tempElement;
            }
        }

        i = i == null ? leftNode : i.next;
        tempElement = i.element;
        i.element = rightNode.element;
        rightNode.element = tempElement;

        return i;
    }

    /**
     * Призначений, для реалізації спеціалізованого алгоритму сортування
     * двозв'язних списків.
     *
     * @param leftNode
     *            початковий вузел діапазону, для сортування
     * @param rightNode
     *            кінцевий вузел діапазону, для сортування
     * @since 1.0.0
     */
    private void linkedListQuickSort(@Nonnull final Node<E> leftNode,
            @Nonnull final Node<E> rightNode) {
        if (rightNode != null && leftNode != rightNode
                && leftNode != rightNode.next) {
            final Node<E> partitionNode = linkedListPartition(leftNode,
                    rightNode);
            linkedListQuickSort(leftNode, partitionNode.previous);
            linkedListQuickSort(partitionNode.next, rightNode);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void visit(@Nonnull final ArrayList<E> arrayList) {
        checkNotNull(arrayList);
        if (arrayList.isEmpty()) {
            return;
        }

        final SortAlgorithm<E> sortAlgorithm = SortAlgorithmFactory
                .getDefaultAlgorithm(comparator);
        sortAlgorithm.sort((E[]) arrayList.data, arrayList.size);
    }

    @Override
    public void visit(@Nonnull final LinkedList<E> linkedList) {
        checkNotNull(linkedList);
        if (linkedList.isEmpty()) {
            return;
        }

        linkedListQuickSort(linkedList.cursor.next, linkedList.cursor.previous);
    }
}
