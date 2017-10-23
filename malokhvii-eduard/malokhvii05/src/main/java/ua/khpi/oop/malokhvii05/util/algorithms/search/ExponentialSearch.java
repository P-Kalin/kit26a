package ua.khpi.oop.malokhvii05.util.algorithms.search;

import java.util.Comparator;

import ua.khpi.oop.malokhvii05.util.Array;

/**
 * Призначений, для реалізації алгоритму пошуку в масиві. Ключ у фабриці
 * алгоритмів - "exponential-search".
 * <p>
 * Експонентний пошук (також званий пошуком з подвоєнням пошуку або пошук
 * Струзіка) - це алгоритм, створений Джоном Бентлі і Ендрю Чі-Чі Яо в ​​1976
 * році для пошуку відсортованих необмежених / нескінченних списків. Існує
 * безліч способів реалізувати це, причому найбільш поширеним є визначення
 * діапазону, в якому знаходиться ключ пошуку, і виконання довічного пошуку в
 * цьому діапазоні. Це займає O (log i), де i - позиція ключа пошуку в списку,
 * якщо ключ пошуку знаходиться в списку або позиції, в якій повинен знаходитися
 * ключ пошуку.
 * <ul>
 * <li>Назва: Exponential Search</li>
 * <li>Автор: Jon Bentley + Andrew Chi-Chih Yao</li>
 * <li>Найкраща швидкодія: Ω(1)</li>
 * <li>Середня швидкодія: Θ(log(i))</li>
 * <li>Найгірша швидкодія: O(log(i))</li>
 * <li>Просторова складність: O(1)</li>
 * </ul>
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 * @see SearchAlgorithmFactory
 * @param <T>
 *            Тип даних, елементів масиву, та елемента для пошуку
 */
public class ExponentialSearch<T> extends AbstractSearchAlgorithm<T> {

    static {
        SearchAlgorithmFactory.registerAlgorithm("exponential-search",
                ExponentialSearch.class);
    }

    /**
     * Внутрішній алгоритм пошуку в діапазоні.
     *
     * @since 1.0.0
     */
    private SearchInRangeAlgorithm<T> searchInRangeAlgorithm;

    /**
     * Призначений, для ініціалізації об'єкту довільним внутрішнім алгоритм для
     * пошуку в діапазоні.
     *
     * @param searchInRangeAlgorithm
     *            внутрішній алгоритм для пошуку в діапазоні
     * @since 1.0.0
     */
    public ExponentialSearch(
            final AbstractSearchInRangeAlgorithm<T> searchInRangeAlgorithm) {
        super(searchInRangeAlgorithm.getComparator());
        this.searchInRangeAlgorithm = searchInRangeAlgorithm;
    }

    /**
     * Призначений, для ініціалізації об'єкту компаратором для подільшого
     * обчислення.
     *
     * @param comparator
     *            компаратор для порівння під час сорутвання
     * @since 1.0.0
     */
    public ExponentialSearch(final Comparator<T> comparator) {
        super(comparator);
        this.searchInRangeAlgorithm = (AbstractSearchInRangeAlgorithm<T>) SearchAlgorithmFactory
                .getAlgorithm("binary-search", comparator);
    }

    /**
     * Призначений, для отримання внутрішшнього алгоритму пошуку в діапазоні.
     *
     * @return внутрішній алгоритм пошуку в діапазоні
     * @since 1.0.0
     */
    public final SearchInRangeAlgorithm<T> getSearchInRangeAlgorithm() {
        return this.searchInRangeAlgorithm;
    }

    @Override
    public final int search(final Array<T> array, final T value) {
        if (!this.isValidArray(array)) {
            return this.lastFoundIndex;
        }

        int index = 1;
        final int arraySize = array.size();
        while (index < arraySize
                && this.comparator.compare(array.get(index), value) <= -1) {
            index = index << 1;
        }

        return this.searchInRangeAlgorithm.search(array, value, index >> 1,
                Math.min(index, arraySize));
    }

    /**
     * Призначений, для оновлення внутрішнього алгоритму пошуку в діпазоні.
     *
     * @param searchInRangeAlgorithm
     *            новий внітршній алгоритм для пошуку в діапазоні
     * @since 1.0.0
     */
    public final void setSearchInRangeAlgorithm(
            final SearchInRangeAlgorithm<T> searchInRangeAlgorithm) {
        this.searchInRangeAlgorithm = searchInRangeAlgorithm;
    }
}
