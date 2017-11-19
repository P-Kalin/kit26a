package ua.khpi.oop.malokhvii05.common.algorithms.sort;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Comparator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

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
    public JSort(@Nonnull final Comparator<T> comparator) {
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
    private void inverseReheap(@Nonnull final T[] array,
            @Nonnegative final int index) {
        boolean done = false;

        final T parentValue = array[array.length - 1 - index];
        int parent = index;
        int child = 2 * (index + 1) - 1;

        while (child < array.length && !done) {
            if (child < array.length - 1) {
                if (comparator.compare(array[array.length - 1 - child],
                        array[array.length - 1 - (child + 1)]) <= 0) {
                    child += 1;
                }

            }

            if (comparator.compare(parentValue,
                    array[array.length - 1 - child]) == 1) {
                done = true;
            } else {
                array[array.length - 1 - parent] = array[array.length - 1
                        - child];
                parent = child;
                child = 2 * (parent + 1) - 1;

            }
        }
        array[array.length - 1 - parent] = parentValue;
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
    private void reheap(@Nonnull final T[] array,
            @Nonnegative final int index) {
        boolean done = false;

        final T parentValue = array[index];
        int parent = index;
        int child = 2 * (index + 1) - 1;

        while (child < array.length && !done) {
            if (child < array.length - 1) {
                if (comparator.compare(array[child], array[child + 1]) >= 0) {
                    child += 1;
                }
            }

            if (comparator.compare(parentValue, array[child]) == -1) {
                done = true;
            } else {
                array[parent] = array[child];
                parent = child;
                child = 2 * (parent + 1) - 1;

            }
        }
        array[parent] = parentValue;
    }

    @Override
    public void sort(@Nonnull final T[] array) {
        checkNotNull(array);
        int i;
        for (i = array.length - 1; i >= 0; i--) {
            this.reheap(array, i);
        }

        for (i = array.length - 1; i >= 0; i--) {
            this.inverseReheap(array, i);
        }

        T key;
        int j;
        for (i = 1; i < array.length; i++) {
            key = array[i];
            j = i - 1;
            while (j >= 0 && comparator.compare(array[j], key) > 0) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
    }
}
