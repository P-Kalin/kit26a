package ua.khpi.oop.malokhvii05.common.algorithms.sort;

import java.util.Comparator;

import ua.khpi.oop.malokhvii05.common.Array;

/**
 * Призначений, для реалізації алгоритму сортування вхідного масиву. Ключ у
 * фабриці алгоритмів - "j-sort".
 * <p>
 * J-сортування - алгоритм сортування розроблений Джесоном Морісоном. Алгоритм
 * базується на впорядкуванні початку масиву, та кінця масиву за допомгою
 * незпадаючої та незростаючої куп. Для подальшого використання сортування
 * вставкою на вхідному масиві з впорядкованим початком та кінцем.
 * <ul>
 * <li>Назва: J Sort</li>
 * <li>Автор: Jason Morrison</li>
 * <li>Метод: Selection + Insertion</li>
 * <li>Найкраща швидкодія: Ω(n)</li>
 * <li>Середня швидкодія: ?</li>
 * <li>Найгірша швидкодія: O(n^2)</li>
 * <li>Просторова складність: O(n)</li>
 * <li>Стабільний: Ні</li>
 * </ul>
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 * @see SortAlgorithmFactory
 * @param <T>
 *            Тип даних, елементів масиву для сортування, та компаратору для
 *            порівняння елементів
 */
public final class JSort<T> extends AbstractSortAlgorithm<T> {

    static {
        SortAlgorithmFactory.registerAlgorithm("j-sort", JSort.class);
    }

    /**
     * Призначений, для інійіалізації об'єкту компоратором для порівняння
     * вхідних даних.
     *
     * @param comparator
     *            компоратор для вхідних даних
     * @since 1.0.0
     */
    public JSort(final Comparator<T> comparator) {
        super(comparator);

    }

    /**
     * Призначений, для переміщення менших елементів ближче к початку масива, за
     * допомогою неповної незростаючої купи.
     *
     * @param array
     *            масив
     * @param index
     *            індекс елементу купи
     * @since 1.0.0
     */
    private void inverseReheap(final Array<T> array, final int index) {
        final int heapSize = array.size();

        boolean done = false;

        final T parentValue = array.get(heapSize - 1 - index);
        int parent = index;
        int child = 2 * (index + 1) - 1;

        while ((child < heapSize) && (!done)) {
            if (child < heapSize - 1) {
                if (this.comparator.compare(array.get(heapSize - 1 - child),
                        array.get(heapSize - 1 - (child + 1))) <= 0) {
                    child += 1;
                }

            }

            if (this.comparator.compare(parentValue,
                    array.get(heapSize - 1 - child)) == 1) {
                done = true;
            } else {
                array.set(heapSize - 1 - parent,
                        array.get(heapSize - 1 - child));
                parent = child;
                child = 2 * (parent + 1) - 1;

            }
        }
        array.set(heapSize - 1 - parent, parentValue);
    }

    /**
     * Призначений, для переміщення більших елементів ближче к кінцю масива, за
     * допомогою неповної неспадаючої купи.
     *
     * @param array
     *            масив
     * @param index
     *            індекс елементу купи
     * @since 1.0.0
     */
    private void reheap(final Array<T> array, final int index) {
        final int heapLength = array.size();

        boolean done = false;

        final T parentValue = array.get(index);
        int parent = index;
        int child = 2 * (index + 1) - 1;

        while ((child < heapLength) && (!done)) {
            if (child < heapLength - 1) {
                if (this.comparator.compare(array.get(child),
                        array.get(child + 1)) >= 0) {
                    child += 1;
                }
            }

            if (this.comparator.compare(parentValue, array.get(child)) == -1) {
                done = true;
            } else {
                array.set(parent, array.get(child));
                parent = child;
                child = 2 * (parent + 1) - 1;

            }
        }
        array.set(parent, parentValue);
    }

    @Override
    public void sort(final Array<T> array) {
        final int arraySize = array.size();

        int i;
        for (i = arraySize - 1; i >= 0; i--) {
            this.reheap(array, i);
        }

        for (i = arraySize - 1; i >= 0; i--) {
            this.inverseReheap(array, i);
        }

        T key;
        int j;
        for (i = 1; i < arraySize; i++) {
            key = array.get(i);
            j = i - 1;
            while (j >= 0 && this.comparator.compare(array.get(j), key) > 0) {
                array.set(j + 1, array.get(j));
                j = j - 1;
            }
            array.set(j + 1, key);
        }
    }
}
