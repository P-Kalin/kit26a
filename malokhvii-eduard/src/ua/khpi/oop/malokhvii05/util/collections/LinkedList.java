package ua.khpi.oop.malokhvii05.util.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public final class LinkedList<E> extends AbstractList<E> {

    private class LinkedListIterator implements ListIterator<E> {

        private Node<E> lastReturnedNode;
        private Node<E> nextNode;
        private int nextIndex;

        private LinkedListIterator(final int index) {
            nextNode = LinkedList.this.getNodeByIndex(index);
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
        public E next() {
            if (nextIndex == size) {
                throw new NoSuchElementException();
            }

            lastReturnedNode = nextNode;
            nextNode = nextNode.next;
            nextIndex++;
            return lastReturnedNode.element;
        }

        @Override
        public E previous() {
            if (nextIndex == 0) {
                throw new NoSuchElementException();
            }

            nextNode = nextNode.previous;
            lastReturnedNode = nextNode;
            nextIndex--;
            return lastReturnedNode.element;
        }

        @Override
        public void remove() {
            Node<E> lastNextNode = lastReturnedNode.next;
            LinkedList.this.remove(lastReturnedNode);

            if (nextNode == lastReturnedNode) {
                nextNode = lastNextNode;
            } else {
                nextIndex--;
            }
            lastReturnedNode = cursor;
        }
    }

    static class Node<E> {
        E element;
        Node<E> next;
        Node<E> previous;

        Node(E element, Node<E> next, Node<E> previous) {
            this.element = element;
            this.next = next;
            this.previous = previous;
        }
    }

    private Node<E> cursor;

    public LinkedList() {
        cursor = new Node<E>(null, null, null);
        cursor.next = cursor.previous;
        cursor.previous = cursor;
    }

    @Override
    public boolean add(final E element) {
        this.addBefore(element, cursor);
        return true;
    }

    @Override
    public void add(final int index, final E element) {
        addBefore(element, getNodeByIndex(index));
    }

    private void addAfter(final E element, final Node<E> node) {
        Node<E> newNode = new Node<E>(element, node.next, node);

        newNode.previous.next = newNode;
        newNode.next.previous = newNode;

        size++;
    }

    @Override
    public void addAfter(final int index, final E element) {
        addAfter(element, getNodeByIndex(index));
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean addAll(final Collection<? extends E> collection) {
        Object[] objects = collection.toArray();

        int index;
        for (index = 0; index < objects.length; index++) {
            addLast((E) objects[index]);
        }

        return true;
    }

    private void addBefore(final E element, final Node<E> node) {
        Node<E> newNode = new Node<E>(element, node, node.previous);

        newNode.previous.next = newNode;
        newNode.next.previous = newNode;

        size++;
    }

    @Override
    public void addBefore(final int index, final E element) {
        addBefore(element, getNodeByIndex(index));
    }

    @Override
    public void addFirst(final E element) {
        addBefore(element, cursor.next);
    }

    @Override
    public void addLast(final E element) {
        addBefore(element, cursor);
    }

    @Override
    public void clear() {
        Node<E> currentNode = cursor.next;
        Node<E> nextNode;
        while (currentNode != cursor) {
            nextNode = currentNode.next;
            currentNode.next = currentNode.previous;
            currentNode.previous = null;
            currentNode.element = null;
            currentNode = nextNode;
        }

        cursor.next = cursor.previous;
        cursor.previous = cursor;
        size = 0;
    }

    @Override
    public ListIterator<E> firstIterator() {
        return listIterator(0);
    }

    @Override
    public E get(final int index) {
        return getNodeByIndex(index).element;
    }

    @Override
    public E getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return cursor.next.element;
    }

    @Override
    public E getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return cursor.previous.element;
    }

    private Node<E> getNodeByIndex(final int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> currentNode = cursor;
        if (index < (size >> 2)) {
            for (int i = 0; i <= index; i++) {
                currentNode = currentNode.next;
            }
        } else {
            for (int i = size; i > index; i--) {
                currentNode = currentNode.previous;
            }
        }
        return currentNode;
    }

    @Override
    public int indexOf(final Object object) {
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
        return -1;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator(0);
    }

    @Override
    public ListIterator<E> lastIterator() {
        return listIterator(size - 1);
    }

    @Override
    public ListIterator<E> listIterator(final int index) {
        return new LinkedListIterator(index);
    }

    @Override
    public void remove(final int index) {
        remove(getNodeByIndex(index));
    }

    private E remove(final Node<E> node) {
        if (node == cursor) {
            throw new NoSuchElementException();
        }

        E element = node.element;
        node.previous.next = node.next;
        node.next.previous = node.previous;

        node.next = node.previous;
        node.previous = null;
        node.element = null;

        size--;
        return element;
    }

    @Override
    public boolean remove(final Object object) {
        int index = indexOf(object);
        if (index != -1) {
            remove(getNodeByIndex(index));
            return true;
        }

        return false;
    }

    @Override
    public void removeFirst() {
        remove(cursor.next);
    }

    @Override
    public void removeLast() {
        remove(cursor.previous);
    }

    @Override
    public E set(final int index, final E element) {
        Node<E> currentNode = getNodeByIndex(index);
        E oldElement = currentNode.element;
        currentNode.element = element;
        return oldElement;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];

        int index = 0;
        Node<E> currentNode;
        for (currentNode = cursor.next; currentNode != cursor;
                currentNode = currentNode.next) {
            array[index++] = currentNode.element;
        }

        return array;
    }
}
