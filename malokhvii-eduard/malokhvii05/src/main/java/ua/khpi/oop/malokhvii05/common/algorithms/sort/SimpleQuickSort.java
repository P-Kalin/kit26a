package ua.khpi.oop.malokhvii05.common.algorithms.sort;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Comparator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/**
 * Призначений, для реалізації алгоритму сортування вхідного масиву. Ключ у
 * фабриці алгоритмів - "simple-quick-sort".
 * <p>
 * Швидке сортування — алгоритм сортування, добре відомий, як алгоритм
 * розроблений Тоні Гоаром (C. A. R. Hoare), який не потребує додаткової пам'яті
 * і виконує у середньому O(n log(n)) операцій. Однак, у найгіршому випадку
 * робить O(n^2) порівнянь.
 * <p>
 * Оскільки алгоритм використовує дуже прості цикли і операції, він працює
 * швидше інших алгоритмів, що мають таку ж асимптотичну оцінку складності.
 * Наприклад, зазвичай більш ніж удвічі швидший порівняно з сортуванням злиттям.
 * <p>
 * Ідея алгоритму полягає в переставлянні елементів масиву таким чином, щоб його
 * можна було розділити на дві частини і кожний елемент з першої частини був не
 * більший за будь-який елемент з другої. Впорядкування кожної з частин
 * відбувається рекурсивно. Алгоритм швидкого сортування може бути реалізований
 * як у масиві, так і в двозв'язному списку.
 * <ul>
 * <li>Назва: Quick Sort (simple partition)</li>
 * <li>Автор: C. A. R. Hoare</li>
 * <li>Метод: Partitioning</li>
 * <li>Найкраща швидкодія: Ω(n log(n))</li>
 * <li>Середня швидкодія: Θ(n log(n))</li>
 * <li>Найгірша швидкодія: O(n^2)</li>
 * <li>Просторова складність: O(log(n))</li>
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
public final class SimpleQuickSort<T> extends AbstractSortAlgorithm<T> {

    static {
        SortAlgorithmFactory.registerAlgorithm("simple-quick-sort",
                SimpleQuickSort.class);
    }

    /**
     * Призначений, для інійіалізації об'єкту компоратором для порівняння
     * вхідних даних.
     *
     * @param comparator
     *            компоратор для вхідних даних
     * @since 1.0.0
     */
    public SimpleQuickSort(@Nonnull final Comparator<T> comparator) {
        super(comparator);
    }

    /**
     * Призначений, для вибірки останнього елементу як шарніру, розміщення
     * стрижневого елементу у своєму правильному положенні у відсортованому
     * масиві та розміщення всіх менших (менших за шарнір) у лівій частині від
     * шарніра і всіх більших у правій частині.
     *
     * @param array
     *            вхідний масив
     * @param left
     *            початок діапазону
     * @param right
     *            кінець діпазону
     * @return індекс для розбиття масиву на частини
     * @since 1.0.0
     */
    private int partition(@Nonnull final T[] array, @Nonnegative final int left,
            @Nonnegative final int right) {
        final T highValue = array[right];

        int i = left - 1;
        int j;
        for (j = left; j <= right - 1; j++) {
            if (comparator.compare(array[j], highValue) <= -1) {
                swap(array, ++i, j);
            }
        }

        swap(array, ++i, right);
        return i;
    }

    @Override
    public void sort(@Nonnull final T[] array) {
        checkNotNull(array);
        int low = 0;
        int high = array.length - 1;

        final int[] stack = new int[high - low + 1];
        int top = -1;

        stack[++top] = low;
        stack[++top] = high;

        while (top >= 0) {
            high = stack[top--];
            low = stack[top--];

            final int partition = this.partition(array, low, high);

            if (partition - 1 > low) {
                stack[++top] = low;
                stack[++top] = partition - 1;
            }

            if (partition + 1 < high) {
                stack[++top] = partition + 1;
                stack[++top] = high;
            }
        }
    }
}
