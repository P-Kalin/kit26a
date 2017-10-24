package ua.khpi.oop.malokhvii05.util.algorithms.search;

import java.util.Comparator;

import ua.khpi.oop.malokhvii05.util.Array;

/**
 * Призначений, для реалізації алгоритму пошуку в масиві. Ключ у фабриці
 * алгоритмів - "linear-with-barrier-search".
 * <p>
 * Модифікація алгоритму {@link LinearSearch лінійного пошуку}. Мета модифікації
 * полягає у тому що, в кінець масиву записується додатковий елемент зі
 * значенням шуканого елементу. Він називається бар'єром, тому що обмежує
 * перехід за межі масиву. Але тепер масив буде описаний як m[0..n + 1]. Після
 * пошуку необхідно зняти бар'єр.
 * <ul>
 * <li>Назва: Linear With Barrier Search</li>
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
     * @since 1.0.0
     */
    public LinearWithBarrierSearch(final Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    public final int search(final Array<T> array, final T value) {
        if (!this.isValidArray(array)) {
            return this.lastFoundIndex;
        }

        if (this.comparator.compare(array.getLast(), value) != 0) {
            array.addLast(value);
            for (this.lastFoundIndex = 0; this.comparator
                    .compare(array.get(this.lastFoundIndex), value) != 0;
                    this.lastFoundIndex++) {
                ;
            }
            array.removeLast();
        } else {
            return array.size() - 1;
        }

        if (this.lastFoundIndex < array.size()) {
            return this.lastFoundIndex;
        }

        return this.indexNotFound();
    }
}
