package ua.khpi.oop.malokhvii05.util.algorithms.sort;

import java.util.Comparator;

import ua.khpi.oop.malokhvii05.util.Array;

/**
 * <p>
 * Призначений, для реалізації алгоритму сортування вхідного масиву. Ключ у
 * фабриці алгоритмів - "simple-quick-sort".
 * </p>
 * <p>
 * Швидке сортування (англ. Quick Sort) — алгоритм сортування, добре відомий, як
 * алгоритм розроблений Тоні Гоаром (C. A. R. Hoare), який не потребує
 * додаткової пам'яті і виконує у середньому O(n log n) операцій. Однак, у
 * найгіршому випадку робить O(n^2) порівнянь.
 * </p>
 * <p>
 * Оскільки алгоритм використовує дуже прості цикли і операції, він працює
 * швидше інших алгоритмів, що мають таку ж асимптотичну оцінку складності.
 * Наприклад, зазвичай більш ніж удвічі швидший порівняно з сортуванням злиттям.
 * </p>
 * <p>
 * Ідея алгоритму полягає в переставлянні елементів масиву таким чином, щоб його
 * можна було розділити на дві частини і кожний елемент з першої частини був не
 * більший за будь-який елемент з другої. Впорядкування кожної з частин
 * відбувається рекурсивно. Алгоритм швидкого сортування може бути реалізований
 * як у масиві, так і в двозв'язному списку.
 * </p>
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
 * @author malokhvii-eduard
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
     */
    public SimpleQuickSort(final Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    public void sort(final Array<T> array) {
        int low = 0;
        int high = array.size() - 1;

        int[] stack = new int[high - low + 1];
        int top = -1;

        stack[++top] = low;
        stack[++top] = high;

        while (top >= 0) {
            high = stack[top--];
            low = stack[top--];

            int partition = partition(array, low, high);

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
     */
    private int partition(final Array<T> array, final int left,
            final int right) {
        T highValue = array.get(right);

        int i = (left - 1);
        int j;
        for (j = left; j <= right - 1; j++) {
            if (comparator.compare(array.get(j), highValue) <= -1) {
                swap(array, ++i, j);
            }
        }

        swap(array, ++i, right);
        return i;
    }
}
