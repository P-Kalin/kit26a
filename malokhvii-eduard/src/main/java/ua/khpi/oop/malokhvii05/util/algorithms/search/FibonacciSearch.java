package ua.khpi.oop.malokhvii05.util.algorithms.search;

import java.util.Comparator;

import ua.khpi.oop.malokhvii05.util.Array;

/**
 * <p>
 * Призначений, для реалізації алгоритму пошуку в масиві. Ключ у фабриці
 * алгоритмів - "fibonacci-search".
 * </p>
 * <p>
 * Метод пошуку Фібоначчі є метод пошуку у відсортованому масиві з використанням
 * алгоритму розподілу і спокою, який звужує можливі місця розташування за
 * допомогою чисел Фібоначчі. У порівнянні з бінарним пошуком, де відсортований
 * масив ділиться на дві частини рівного розміру, один з яких розглядається
 * додатково, пошук Фібоначчі ділить масив на дві частини з розмірами, які є
 * послідовними числами Фібоначчі. В середньому це призводить до виконання
 * порівняння на 4% більше, але має ту перевагу, що для обчислення індексів
 * елементів доступного масиву потрібно тільки додавання і віднімання, тоді як
 * класичний бінарний пошук вимагає бітові-зсуви, поділ або множення, операції,
 * які були менш поширені під час пошуку Фібоначчі.
 * </p>
 * <ul>
 * <li>Назва: Fibonacci Search</li>
 * <li>Автор: Jack Kiefer</li>
 * <li>Найкраща швидкодія: Ω(1)</li>
 * <li>Середня швидкодія: Θ(log(n))</li>
 * <li>Найгірша швидкодія: O(log(n))</li>
 * <li>Просторова складність: O(1)</li>
 * </ul>
 *
 * @author malokhvii-eduard
 * @version 1.0.0
 * @see SearchAlgorithmFactory
 * @param <T>
 *            Тип даних, елементів масиву, та елемента для пошуку
 * @since 1.0.0
 */
public final class FibonacciSearch<T> extends AbstractSearchAlgorithm<T> {

    /**
     * Число фібоначі (m-2)'th.
     *
     * @since 1.0.0
     */
    private int fibonacciNumberM2;

    /**
     * Число фібоначі (m-1)'th.
     *
     * @since 1.0.0
     */
    private int fibonacciNumberM1;

    /**
     * Число фібоначі m'th.
     *
     * @since 1.0.0
     */
    private int currentFibonacciNumber;

    static {
        SearchAlgorithmFactory.registerAlgorithm("fibonacci-search",
                FibonacciSearch.class);
    }

    /**
     * Призначений, для ініціалізації об'єкту компаратором для подільшого
     * обчислення.
     *
     * @param comparator
     *            компаратор для порівння під час сорутвання
     * @since 1.0.0
     */
    public FibonacciSearch(final Comparator<T> comparator) {
        super(comparator);
    }

    /**
     * Призначений, для ініціалізації чисел фібоначі залежно від розміру масиву.
     *
     * @param arraySize
     *            розмір вхідного масиву
     * @since 1.0.0
     */
    private void prepareFibonacciNumbers(final int arraySize) {
        fibonacciNumberM1 = 1;
        fibonacciNumberM2 = 0;
        currentFibonacciNumber = fibonacciNumberM2 + fibonacciNumberM1;

        while (currentFibonacciNumber < arraySize) {
            fibonacciNumberM2 = fibonacciNumberM1;
            fibonacciNumberM1 = currentFibonacciNumber;
            currentFibonacciNumber = fibonacciNumberM2 + fibonacciNumberM1;
        }
    }

    /**
     * Призначений, для обрізання діапазону пошуку, якщо значення для пошуку
     * більше ніж поточне значення.
     *
     * @since 1.0.0
     */
    private void cutSubarrayFromOffsetToIndex() {
        currentFibonacciNumber = fibonacciNumberM1;
        fibonacciNumberM1 = fibonacciNumberM2;
        fibonacciNumberM2 = currentFibonacciNumber - fibonacciNumberM1;
    }

    /**
     * Призначений, для обрізання діапазону пошуку, якщо значення для пошуку
     * меньше ніж поточне значення.
     * 
     * @since 1.0.0
     */
    private void cutSubarrayAfterIndexPlusOne() {
        currentFibonacciNumber = fibonacciNumberM2;
        fibonacciNumberM1 -= fibonacciNumberM2;
        fibonacciNumberM2 = currentFibonacciNumber - fibonacciNumberM1;
    }

    @Override
    public int search(final Array<T> array, final T value) {
        if (!isValidArray(array)) {
            return lastFoundIndex;
        }

        int subarraySize = array.size() - 1;
        prepareFibonacciNumbers(subarraySize);

        int offset = -1;
        T currentValue;
        while (currentFibonacciNumber > 1) {
            lastFoundIndex = Math.min(offset + fibonacciNumberM2,
                    subarraySize - 1);
            currentValue = array.get(lastFoundIndex);
            if (comparator.compare(currentValue, value) == -1) {
                cutSubarrayFromOffsetToIndex();
                offset = lastFoundIndex;
            } else if (comparator.compare(currentValue, value) == 1) {
                cutSubarrayAfterIndexPlusOne();
            } else {
                return lastFoundIndex;
            }
        }

        if (fibonacciNumberM1 != 0
                && comparator.compare(array.get(offset ^ 1), value) == 0) {
            return offset ^ 1;
        }

        return indexNotFound();
    }
}
