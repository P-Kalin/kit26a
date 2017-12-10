package ua.khpi.oop.malokhvii05.common.collect;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.NoSuchElementException;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.errorprone.annotations.CanIgnoreReturnValue;

import ua.khpi.oop.malokhvii05.common.algorithms.search.SearchAlgorithm;
import ua.khpi.oop.malokhvii05.common.collect.EmptyList.EmptyListIterator;

/**
 * Призначений, для збереження однотипних елементів у вигляді двозв'язного
 * списку. Для обробки елементів див. детальнше пакет з типовими алгоритмами.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.1
 * @see ua.khpi.oop.malokhvii05.common.algorithms
 * @see ua.khpi.oop.malokhvii05.common.algorithms.sort
 * @see ua.khpi.oop.malokhvii05.common.algorithms.search
 * @param <E>
 *            Тип елементів розташованих у вузлах
 */
public final class LinkedList<E> extends AbstractList<E> {

    /**
     * Призначений, для проходження через елементи списку. Реалізує двохстороннє
     * ітерування, тобто в обидва напрямки, та видалення поточного елементу.
     *
     * @author malokhvii-eduard (malokhvii.ee@gmail.com)
     * @version 1.0.0
     */
    private final class LinkedListIterator implements ListIterator<E> {

        /**
         * Останній повернутий вузел списку.
         *
         * @since 1.0.0
         */
        private Node<E> lastReturnedNode = cursor;

        /**
         * Індекс поточного вузла.
         *
         * @since 1.0.0
         */
        private int nextIndex;

        /**
         * Поточний вузел списку, елемент якого необхідно повернути.
         *
         * @since 1.0.0
         */
        private Node<E> nextNode;

        /**
         * Призначений, для ініціалізації ітератора індексом початкового вузлу.
         *
         * @param index
         *            індекст початкового елементу для проходження
         * @since 1.0.0
         */
        private LinkedListIterator(@Nonnegative final int index) {
            if (!isNewElementIndexInRange(index)) {
                throw new IndexOutOfBoundsException();
            }

            if (index < size >> 1) {
                nextNode = cursor.next;
                for (nextIndex = 0; nextIndex < index; nextIndex++) {
                    nextNode = nextNode.next;
                }
            } else {
                nextNode = cursor;
                for (nextIndex = size; nextIndex > index; nextIndex--) {
                    nextNode = nextNode.previous;
                }
            }
        }

        @Override
        public boolean hasNext() {
            return nextIndex != size;
        }

        @Override
        public boolean hasPrevious() {
            return nextIndex != 0;
        }

        @Override
        public @Nullable E next() {
            if (nextIndex == size) {
                throw new NoSuchElementException();
            }

            lastReturnedNode = nextNode;
            nextNode = nextNode.next;
            nextIndex++;
            return lastReturnedNode.element;
        }

        @Override
        public @Nullable E previous() {
            if (nextIndex == 0) {
                throw new NoSuchElementException();
            }

            lastReturnedNode = nextNode = nextNode.previous;
            nextIndex--;
            return lastReturnedNode.element;
        }

        @Override
        public void remove() {
            Node<E> lastNext = lastReturnedNode.next;
            try {
                LinkedList.this.remove(lastReturnedNode);
            } catch (Exception exception) {
                throw new IllegalStateException();
            }

            if (nextNode == lastReturnedNode) {
                nextNode = lastNext;
            } else {
                nextIndex--;
            }
            lastReturnedNode = cursor;
        }
    }

    /**
     * Призначений, для оглошення вузлу двозв'язного списку.
     *
     * @author malokhvii-eduard (malokhvii.ee@gmail.com)
     * @version 1.0.0
     * @param <E>
     *            тип елементу, для розміщення у вузлі
     */
    static class Node<E> {

        /**
         * Елемент вузлу.
         *
         * @since 1.0.0
         */
        E element;

        /**
         * Посилання на наступний вузел.
         *
         * @since 1.0.0
         */
        Node<E> next;

        /**
         * Посилання на попередній вузел.
         *
         * @since 1.0.0
         */
        Node<E> previous;

        /**
         * Призначений, для ініціалізації вузлу наступним та попереднім
         * посиланнями на вузли та елементом.
         *
         * @param element
         *            елемент вузлу
         * @param next
         *            посилання на наступний вузел
         * @param previous
         *            посилання на попердній вузел
         * @since 1.0.0
         */
        Node(@Nullable final E element, @Nullable final Node<E> next,
                @Nullable final Node<E> previous) {
            this.element = element;
            this.next = next;
            this.previous = previous;
        }
    }

    private static final long serialVersionUID = 1795198624949169541L;

    /**
     * Кореневий вузел списку. Де {@code next} голова списку, {@code previous}
     * хвіст списку.
     *
     * @since 1.0.0
     */
    transient Node<E> cursor;

    /**
     * Призначений, для ініціалізації порожнього списку.
     *
     * @since 1.0.0
     */
    public LinkedList() {
        cursor = new Node<>(null, null, null);
        cursor.next = cursor.previous = cursor;
    }

    /**
     * Призначений, для ініціалізації вузлів елементами з отриманої колекції.
     *
     * @param elements
     *            колекція з якої будуть скопійовані елементи
     * @since 1.0.0
     */
    public LinkedList(@Nullable final Collection<E> elements) {
        this();
        addAll(elements);
    }

    /**
     * Призначений, для ініціалізації вузлів елементами з отриманого масиву.
     *
     * @param elements
     *            масив з якого будуть скопійовані елементи
     * @since 1.0.0
     */
    public LinkedList(@Nullable final E[] elements) {
        this();
        addAll(elements);
    }

    @Override
    public void acceptVisitor(@Nonnull final ListVisitor<E> listVisitor) {
        checkNotNull(listVisitor);
        listVisitor.visit(this);
    }

    @Override
    @CanIgnoreReturnValue
    public boolean add(@Nullable final E element) {
        addNodeBefore(element, cursor);
        return true;
    }

    @Override
    @CanIgnoreReturnValue
    public boolean add(@Nonnegative final int index,
            @Nullable final E element) {
        addNodeBefore(element, getNodeByIndex(index));
        return true;
    }

    @Override
    @CanIgnoreReturnValue
    public boolean addAfter(@Nonnegative final int index,
            @Nullable final E element) {
        addNodeAfter(element, getNodeByIndex(index));
        return true;
    }

    @Override
    @CanIgnoreReturnValue
    public boolean addAll(@Nullable final Collection<? extends E> elements) {
        if (elements == null || elements.isEmpty()) {
            return false;
        }

        for (E element : elements) {
            addLast(element);
        }
        return true;
    }

    @Override
    @CanIgnoreReturnValue
    public boolean addAll(@Nullable final E[] elements) {
        if (elements == null || elements.length == 0) {
            return false;
        }

        for (E element : elements) {
            addLast(element);
        }
        return true;
    }

    @Override
    @CanIgnoreReturnValue
    public boolean addBefore(@Nonnegative final int index,
            @Nullable final E element) {
        addNodeBefore(element, getNodeByIndex(index));
        return true;
    }

    @Override
    @CanIgnoreReturnValue
    public boolean addFirst(@Nullable final E element) {
        addNodeBefore(element, cursor.next);
        return true;
    }

    @Override
    @CanIgnoreReturnValue
    public boolean addLast(@Nullable final E element) {
        addNodeBefore(element, cursor);
        return true;
    }

    /**
     * Призначений, для додавання нового вузла після отриманого вузла.
     *
     * @param element
     *            елемент нового вузла
     * @param node
     *            вузел, після якого необхідно додати новий вузел
     * @since 1.0.0
     */
    private void addNodeAfter(@Nullable final E element,
            @Nonnull final Node<E> node) {
        Node<E> newNode = new Node<>(element, node.next, node);

        newNode.previous.next = newNode;
        newNode.next.previous = newNode;

        size++;
    }

    /**
     * Призначений, для додавання нового вузла перед отриманим вузлом.
     *
     * @param element
     *            елемент нового вузла
     * @param node
     *            вузел, перед яким необхідно додати новий вузел
     * @since 1.0.0
     */
    private void addNodeBefore(@Nullable final E element,
            @Nonnull final Node<E> node) {
        Node<E> newNode = new Node<>(element, node, node.previous);

        newNode.previous.next = newNode;
        newNode.next.previous = newNode;

        size++;
    }

    @Override
    public void clear() {
        Node<E> currentNode = cursor.next;
        Node<E> nextNode;

        while (currentNode != cursor) {
            nextNode = currentNode.next;
            currentNode.next = currentNode.previous = null;
            currentNode.element = null;
            currentNode = nextNode;
        }

        cursor.next = cursor.previous = cursor;
        size = 0;
    }

    @Override
    public @Nullable E get(@Nonnegative final int index) {
        return getNodeByIndex(index).element;
    }

    @Override
    public @Nullable E getFirst() {
        if (isEmpty()) {
            return null;
        }

        return cursor.next.element;
    }

    @Override
    public @Nullable E getLast() {
        if (isEmpty()) {
            return null;
        }

        return cursor.previous.element;
    }

    /**
     * Призначений, для пошуку вузлу за індексом, виконується звичайний лінійний
     * пошук.
     *
     * @param index
     *            індекс вузлу
     * @return знайдений вузел, якщо не знайдено тоді {@code null}
     * @since 1.0.0
     */
    private @Nullable Node<E> getNodeByIndex(@Nonnegative final int index) {
        if (!isNewElementIndexInRange(index)) {
            return null;
        }

        Node<E> currentNode = cursor;
        int currentNodeIndex;
        if (index < size >> 2) {
            for (currentNodeIndex = 0; currentNodeIndex <= index;
                    currentNodeIndex++) {
                currentNode = currentNode.next;
            }
        } else {
            for (currentNodeIndex = size; currentNodeIndex > index;
                    currentNodeIndex--) {
                currentNode = currentNode.previous;
            }
        }

        return currentNode;
    }

    @Override
    public int indexOf(@Nullable final Object object) {
        int index = 0;
        Node<E> currentNode;
        if (object == null) {
            for (currentNode = cursor.next; currentNode != cursor;
                    currentNode = currentNode.next) {
                if (currentNode.element == null) {
                    return index;
                }
                index++;
            }
        } else {
            for (currentNode = cursor.next; currentNode != cursor;
                    currentNode = currentNode.next) {
                if (object.equals(currentNode.element)) {
                    return index;
                }
                index++;
            }
        }
        return SearchAlgorithm.INDEX_NOT_FOUND;
    }

    @Override
    public @Nonnull ListIterator<E> iterator(@Nonnegative final int index) {
        if (isIndexInRange(index)) {
            return new LinkedListIterator(index);
        }

        return new EmptyListIterator<>();
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void readObject(
            @Nonnull final ObjectInputStream objectInputStream)
            throws ClassNotFoundException, IOException {
        checkNotNull(objectInputStream);
        objectInputStream.defaultReadObject();
        int size = objectInputStream.readInt();

        cursor = new Node<>(null, null, null);
        cursor.next = cursor.previous = cursor;

        int index;
        for (index = 0; index < size; index++) {
            addNodeBefore((E) objectInputStream.readObject(), cursor);
        }
    }

    @Override
    @CanIgnoreReturnValue
    public @Nullable E remove(@Nonnegative final int index) {
        return removeNode(getNodeByIndex(index));
    }

    @Override
    @CanIgnoreReturnValue
    public boolean remove(@Nullable final Object object) {
        int objectIndex = indexOf(object);
        if (objectIndex != SearchAlgorithm.INDEX_NOT_FOUND) {
            removeNode(getNodeByIndex(objectIndex));
            return true;
        }
        return false;
    }

    @Override
    @CanIgnoreReturnValue
    public @Nullable E removeFirst() {
        return removeNode(cursor.next);
    }

    @Override
    @CanIgnoreReturnValue
    public @Nullable E removeLast() {
        return removeNode(cursor.previous);
    }

    /**
     * Призначений, для видалення зв'язків для отриманого вузлу.
     *
     * @param node
     *            вузел для видалення
     * @return елемент з видаленного вузлу
     * @since 1.0.0
     */
    @CanIgnoreReturnValue
    private @Nullable E removeNode(@Nonnull final Node<E> node) {
        if (node == cursor || node == null) {
            return null;
        }

        E element = node.element;
        node.previous.next = node.next;
        node.next.previous = node.previous;

        node.next = node.previous = null;
        node.element = null;

        size--;
        return element;
    }

    @Override
    @CanIgnoreReturnValue
    public @Nullable E set(@Nonnegative final int index,
            @Nullable final E element) {
        Node<E> currentNode = getNodeByIndex(index);
        E oldElement = currentNode.element;
        currentNode.element = element;
        return oldElement;
    }

    @Override
    @CanIgnoreReturnValue
    public @Nullable E setFirst(@Nullable final E element) {
        E oldElement = cursor.next.element;
        cursor.next.element = element;
        return oldElement;
    }

    @Override
    @CanIgnoreReturnValue
    public @Nullable E setLast(@Nullable final E element) {
        E oldElement = cursor.previous.element;
        cursor.previous.element = element;
        return oldElement;
    }

    @Override
    public @Nonnull Object[] toArray() {
        Object[] array = new Object[size];

        int index = 0;
        Node<E> currentNode;
        for (currentNode = cursor.next; currentNode != cursor;
                currentNode = currentNode.next) {
            array[index++] = currentNode.element;
        }

        return array;
    }

    @SuppressWarnings("unchecked")
    @Override
    public @Nonnull <T> T[] toArray(@Nullable T[] array) {
        if (array.length < size) {
            array = (T[]) Array.newInstance(array.getClass().getComponentType(),
                    size);
        }

        int index = 0;
        for (Node<E> node = cursor.next; node != cursor; node = node.next) {
            array[index++] = (T) node.element;
        }

        if (array.length > size) {
            array[size] = null;
        }

        return array;
    }

    @Override
    protected void writeObject(
            @Nonnull final ObjectOutputStream objectOutputStream)
            throws IOException {
        checkNotNull(objectOutputStream);
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(size);

        for (Node<E> node = cursor.next; node != cursor; node = node.next) {
            objectOutputStream.writeObject(node.element);
        }
    }
}
