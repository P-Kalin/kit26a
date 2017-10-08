package ua.khpi.oop.malokhvii05.util.algorithms.search;

import java.util.Comparator;

import ua.khpi.oop.malokhvii05.util.Array;

/**
 * <p>
 * Призначений, для реалізації алгоритму пошуку в масиві. Ключ у фабриці
 * алгоритмів - "linear-with-barrier-search".
 * </p>
 * <p>
 * Модифікація алгоритму {@link LinearSearch лінійного пошуку}. Мета модифікації
 * полягає у тому що, в кінець масиву записується додатковий елемент зі
 * значенням шуканого елементу. Він називається бар'єром, тому що обмежує
 * перехід за межі масиву. Але тепер масив буде описаний як m[0..n + 1]. Після
 * пошуку необхідно зняти бар'єр.
 * </p>
 * <ul>
 * <li>Назва: Linear With Barrier Search</li>
 * <li>Автор: ?</li>
 * <li>Найкраща швидкодія: Ω(1)</li>
 * <li>Середня швидкодія: Θ(n)</li>
 * <li>Найгірша швидкодія: O(n)</li>
 * <li>Просторова складність: O(1)</li>
 * </ul>
 *
 * @author malokhvii-eduard
 * @version 1.0.0
 * @see SearchAlgorithmFactory
 * @param <T>
 *            Тип даних, елементів масиву, та елемента для пошуку
 */
public class LinearWithBarrierSearch<T> extends AbstractSearchAlgorithm<T> {

    static {
        SearchAlgorithmFactory.registerAlgorithm("linear-with-barrier-search",
                LinearWithBarrierSearch.class);
    }

    /**
     * Призначений, для ініціалізації об'єкту компаратором для подільшого
     * обчислення.
     *
     * @param comparator
     *            компаратор для порівння під час сорутвання
     */
    public LinearWithBarrierSearch(final Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    public final int search(final Array<T> array, final T value) {
        if (!isValidArray(array)) {
            return lastFoundIndex;
        }

        if (comparator.compare(array.getLast(), value) != 0) {
            array.setLast(value);
            for (lastFoundIndex = 0;
                    comparator.compare(array.get(lastFoundIndex), value) != 0;
                    lastFoundIndex++) {
                ;
            }
        } else {
            return array.size() - 1;
        }
        array.removeLast();

        if (lastFoundIndex < array.size()) {
            return lastFoundIndex;
        }

        return indexNotFound();
    }
}
