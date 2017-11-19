package ua.khpi.oop.malokhvii05.common.algorithms.search;

import java.util.Comparator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.Signed;

/**
 * Призначений, для реалізації алгоритму пошуку в масиві. Ключ у фабриці
 * алгоритмів - "fibonacci-search".
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
 * <ul>
 * <li>Назва: Fibonacci Search</li>
 * <li>Автор: Jack Kiefer</li>
 * <li>Найкраща швидкодія: Ω(1)</li>
 * <li>Середня швидкодія: Θ(log(n))</li>
 * <li>Найгірша швидкодія: O(log(n))</li>
 * <li>Просторова складність: O(1)</li>
 * </ul>
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 * @see SearchAlgorithmFactory
 * @param <T>
 *            Тип даних, елементів масиву, та елемента для пошуку
 */
public final class FibonacciSearch<T> extends AbstractSearchAlgorithm<T> {

    static {
        SearchAlgorithmFactory.registerAlgorithm("fibonacci-search",
                FibonacciSearch.class);
    }

    /**
     * Число фібоначі m'th.
     *
     * @since 1.0.0
     */
    private int currentFibonacciNumber;

    /**
     * Число фібоначі (m-1)'th.
     *
     * @since 1.0.0
     */
    private int fibonacciNumberM1;

    /**
     * Число фібоначі (m-2)'th.
     *
     * @since 1.0.0
     */
    private int fibonacciNumberM2;

    /**
     * Призначений, для ініціалізації об'єкту компаратором для подільшого
     * обчислення.
     *
     * @param comparator
     *            компаратор для порівння під час сорутвання
     * @since 1.0.0
     */
    public FibonacciSearch(@Nonnull final Comparator<T> comparator) {
        super(comparator);
    }

    /**
     * Призначений, для обрізання діапазону пошуку, якщо значення для пошуку
     * меньше ніж поточне значення.
     *
     * @since 1.0.0
     */
    private void cutSubarrayAfterIndexPlusOne() {
        this.currentFibonacciNumber = this.fibonacciNumberM2;
        this.fibonacciNumberM1 -= this.fibonacciNumberM2;
        this.fibonacciNumberM2 = this.currentFibonacciNumber
                - this.fibonacciNumberM1;
    }

    /**
     * Призначений, для обрізання діапазону пошуку, якщо значення для пошуку
     * більше ніж поточне значення.
     *
     * @since 1.0.0
     */
    private void cutSubarrayFromOffsetToIndex() {
        this.currentFibonacciNumber = this.fibonacciNumberM1;
        this.fibonacciNumberM1 = this.fibonacciNumberM2;
        this.fibonacciNumberM2 = this.currentFibonacciNumber
                - this.fibonacciNumberM1;
    }

    /**
     * Призначений, для ініціалізації чисел фібоначі залежно від розміру масиву.
     *
     * @param arraySize
     *            розмір вхідного масиву
     * @since 1.0.0
     */
    private void prepareFibonacciNumbers(@Nonnegative final int arraySize) {
        this.fibonacciNumberM1 = 1;
        this.fibonacciNumberM2 = 0;
        this.currentFibonacciNumber = this.fibonacciNumberM2
                + this.fibonacciNumberM1;

        while (this.currentFibonacciNumber < arraySize) {
            this.fibonacciNumberM2 = this.fibonacciNumberM1;
            this.fibonacciNumberM1 = this.currentFibonacciNumber;
            this.currentFibonacciNumber = this.fibonacciNumberM2
                    + this.fibonacciNumberM1;
        }
    }

    @Override
    public @Signed int search(@Nonnull final T[] array,
            @Nullable final T value) {
        if (!isValidArray(array)) {
            return lastFoundIndex;
        }

        final int subarraySize = array.length - 1;
        this.prepareFibonacciNumbers(subarraySize);

        int offset = -1;
        T currentValue;
        while (this.currentFibonacciNumber > 1) {
            lastFoundIndex = Math.min(offset + this.fibonacciNumberM2,
                    subarraySize - 1);
            currentValue = array[lastFoundIndex];
            if (comparator.compare(currentValue, value) == -1) {
                this.cutSubarrayFromOffsetToIndex();
                offset = lastFoundIndex;
            } else if (comparator.compare(currentValue, value) == 1) {
                this.cutSubarrayAfterIndexPlusOne();
            } else {
                return lastFoundIndex;
            }
        }

        if (this.fibonacciNumberM1 != 0
                && comparator.compare(array[offset ^ 1], value) == 0) {
            return offset ^ 1;
        }

        return indexNotFound();
    }
}
