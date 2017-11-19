package ua.khpi.oop.malokhvii05.common.algorithms.search;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Comparator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.Signed;

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
            @Nonnull final AbstractSearchInRangeAlgorithm<T> searchInRangeAlgorithm) {
        super(checkNotNull(searchInRangeAlgorithm).getComparator());
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
    public ExponentialSearch(@Nonnull final Comparator<T> comparator) {
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
    public final @Nonnull SearchInRangeAlgorithm<T> getSearchInRangeAlgorithm() {
        return this.searchInRangeAlgorithm;
    }

    @Override
    public final @Signed int search(@Nonnull final T[] array,
            @Nullable final T value) {
        if (!isValidArray(array)) {
            return lastFoundIndex;
        }

        int index = 1;
        while (index < array.length
                && comparator.compare(array[index], value) <= -1) {
            index = index << 1;
        }

        return this.searchInRangeAlgorithm.search(array, value, index >> 1,
                Math.min(index, array.length));
    }

    /**
     * Призначений, для оновлення внутрішнього алгоритму пошуку в діпазоні.
     *
     * @param searchInRangeAlgorithm
     *            новий внітршній алгоритм для пошуку в діапазоні
     * @since 1.0.0
     */
    public final void setSearchInRangeAlgorithm(
            @Nonnull final SearchInRangeAlgorithm<T> searchInRangeAlgorithm) {
        this.searchInRangeAlgorithm = checkNotNull(searchInRangeAlgorithm);
    }
}
