package ua.khpi.oop.malokhvii05.common.algorithms.search;

import java.util.Comparator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Signed;

/**
 * Призначений, для реалізації алгоритму пошуку в масиві або діапазоні. Ключ у
 * фабриці алгоритмів - "linear-search".
 * <p>
 * Лінійний пошук - алгоритм послідовного пошуку знаходження заданого значення
 * довільної функції на деякому її відрізку. Цей алгоритм є найпростішим
 * алгоритмом пошуку і на відміну, наприклад, від двійкового пошуку, не накладає
 * жодних обмежень на функцію і має просту реалізацію. Пошук значення функції
 * здійснюється простим порівнянням чергового розглянутого значення (як правило
 * пошук відбувається зліва направо, тобто від менших значень аргументу до
 * більших) і, якщо значення збігаються (з тією або іншою точністю), то пошук
 * вважається завершеним.
 * <ul>
 * <li>Назва: Linear Search</li>
 * <li>Автор: ?</li>
 * <li>Найкраща швидкодія: Ω(1)</li>
 * <li>Середня швидкодія: Θ(n)</li>
 * <li>Найгірша швидкодія: O(n)</li>
 * <li>Просторова складність: O(1)</li>
 * </ul>
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 * @see SearchAlgorithmFactory
 * @param <T>
 *            Тип даних, елементів масиву, діапазону, та елемента для пошуку
 */
public class LinearSearch<T> extends AbstractSearchInRangeAlgorithm<T> {

    static {
        SearchAlgorithmFactory.registerAlgorithm("linear-search",
                LinearSearch.class);
    }

    /**
     * Призначений, для ініціалізації об'єкту компаратором для подільшого
     * обчислення.
     *
     * @param comparator
     *            компаратор для порівння під час сорутвання
     * @since 1.0.0
     */
    public LinearSearch(@Nonnull final Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    public final @Signed int search(@Nonnull final T[] array, final T value,
            @Nonnegative final int left, @Nonnegative final int right) {
        if (!isValidRange(array, left, right)) {
            return lastFoundIndex;
        }

        for (lastFoundIndex = left; lastFoundIndex < right; lastFoundIndex++) {
            if (comparator.compare(array[lastFoundIndex], value) == 0) {
                return lastFoundIndex;
            }
        }

        return indexNotFound();
    }
}
