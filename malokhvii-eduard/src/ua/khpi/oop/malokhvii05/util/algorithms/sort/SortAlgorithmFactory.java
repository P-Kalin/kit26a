package ua.khpi.oop.malokhvii05.util.algorithms.sort;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Фабрика, алгоритмів сортування, призначена для отримання об'єкту алгоритму.
 * Якщо, вказано не вірну назву алгоритму повертається заглушка, тобто не
 * дійсний алгоритм для сортування.
 *
 * @author malokhvii-eduard
 * @version 1.0.0
 * @see SortAlgorithm
 */
@SuppressWarnings("rawtypes")
public final class SortAlgorithmFactory {

    /**
     * Хеш-карта об'єктів обгорток над класами алгоритмів сортування.
     */
    private static Map<String, Class<? extends SortAlgorithm>> classMapping;

    /**
     * Клас алгоритму за змовчування.
     */
    private static Class<? extends SortAlgorithm> defaultAlgorithm;

    static {
        classMapping = new HashMap<String, Class<? extends SortAlgorithm>>();
        loadBasicAlgorithms();
    }

    /**
     * Приватний конструктор, для заборони створення фабрик алгоритмів.
     */
    private SortAlgorithmFactory() {

    }

    /**
     * Пирзначений, для отримання множини зарегестрованих назв алгоритмів.
     *
     * @return множина зарегестрованих назв алгоритмів
     */
    public static Set<String> getRegisteredAlgorithms() {
        return classMapping.keySet();
    }

    /**
     * Призначений, для встановлення алгоритму за змовчуванням.
     *
     * @param algorithmClass
     *            клас алгоритму за змовчуванням
     */
    public static void setDefaultAlgorithm(
            final Class<? extends SortAlgorithm> algorithmClass) {
        defaultAlgorithm = algorithmClass;
    }

    /**
     * Призначений, для отримання алгоритму за змовчуванням.
     *
     * @param <T>
     *            Тип, об'єктів для яких використовується компаратор
     * @param comparator
     *            компаратор, для алгоритму
     * @return алгоритм за змовчуванням, якщо алгоритм за змовчуванням не
     *         визначено, повертає заглушку на недійсний алгоритм.
     */
    @SuppressWarnings("unchecked")
    public static <T> SortAlgorithm<T> getDefaultAlgorithm(
            final Comparator<? super T> comparator) {
        if (defaultAlgorithm != null) {
            try {
                return defaultAlgorithm.getConstructor(Comparator.class)
                        .newInstance(comparator);
            } catch (Exception exception) {

            }
        }
        return NullSortAlgorithm.INSTANCE;
    }

    /**
     * Призначений, для отримання алгоритму за змовчуванням. Для типів які
     * реалізують інтерфейс {@link Comparable}. В якості компаратора
     * використовується {@link Comparator#naturalOrder}. * @param <T> Тип,
     * об'єктів для яких використовується компаратор
     *
     * @return алгоритм за змовчуванням, якщо алгоритм за змовчуванням не
     *         визначено, повертає заглушку на недійсний алгоритм.
     */
    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> SortAlgorithm<T> getDefaultAlgorithm() {
        if (defaultAlgorithm != null) {
            try {
                return defaultAlgorithm.getConstructor(Comparator.class)
                        .newInstance(Comparator.naturalOrder());
            } catch (Exception exception) {

            }
        }
        return NullSortAlgorithm.INSTANCE;
    }

    /**
     * Призначений, для отримання алгоритму за його назвою.
     *
     * @param <T>
     *            Тип, об'єктів для яких використовується компаратор
     * @param name
     *            назва алгоритму
     * @param comparator
     *            компаратор, для алгоритму
     * @return алгоритм за назвою, якщо алгоритм за змовчуванням не визначено,
     *         повертає заглушку на недійсний алгоритм.
     */
    @SuppressWarnings("unchecked")
    public static <T> SortAlgorithm<T> getAlgorithm(final String name,
            final Comparator<? super T> comparator) {
        SortAlgorithm<T> sortAlgorithm = NullSortAlgorithm.INSTANCE;
        Class<? extends SortAlgorithm> sortAlgorithmClass = classMapping
                .get(name);
        if (sortAlgorithmClass != null) {
            try {
                Constructor<? extends SortAlgorithm> sortAlgorithmConstructor = sortAlgorithmClass
                        .getConstructor(Comparator.class);
                sortAlgorithm = sortAlgorithmConstructor
                        .newInstance(comparator);
            } catch (Exception exception) {

            }
        }
        return sortAlgorithm;
    }

    /**
     * Призначений, для отримання алгоритму за його назвою. Для типів які
     * реалізують інтерфейс {@link Comparable}. В якості компаратора
     * використовується {@link Comparator#naturalOrder}.
     *
     * @param <T>
     *            Тип, об'єктів для яких використовується компаратор
     * @param name
     *            назва алгоритму
     * @return алгоритм за назвою, якщо алгоритм за змовчуванням не визначено,
     *         повертає заглушку на недійсний алгоритм.
     */
    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> SortAlgorithm<T> getAlgorithm(
            final String name) {
        SortAlgorithm<T> sortAlgorithm = NullSortAlgorithm.INSTANCE;
        Class<? extends SortAlgorithm> sortAlgorithmClass = classMapping
                .get(name);
        if (sortAlgorithmClass != null) {
            try {
                Constructor<? extends SortAlgorithm> sortAlgorithmConstructor = sortAlgorithmClass
                        .getConstructor(Comparator.class);
                sortAlgorithm = sortAlgorithmConstructor
                        .newInstance(Comparator.naturalOrder());
            } catch (Exception exception) {

            }
        }
        return sortAlgorithm;
    }

    /**
     * Призначений, для видалення запису про зарегестрований алгоритм
     * сортування.
     *
     * @param name
     *            зарегестрована назва алгоритму
     */
    public static void unregisterAlgorithm(final String name) {
        classMapping.remove(name);
    }

    /**
     * Призначений, для регістрації нового алгоритму сортування.
     *
     * @param name
     *            назва алгоритму
     * @param algorithmClass
     *            клас нового алгоритму сортування
     */
    public static void registerAlgorithm(final String name,
            final Class<? extends SortAlgorithm> algorithmClass) {
        classMapping.put(name, algorithmClass);
    }

    /**
     * Призначений, для завантаження типових алгоритмів.
     */
    private static void loadBasicAlgorithms() {
        final String basicPackage = "ua.khpi.oop.malokhvii05.util.algorithms"
                + ".sort";
        final List<String> basicSortAlgorithms = Arrays.asList("GnomeSort",
                "BubbleSort", "ShellSort", "HeapSort", "TopDownMergeSort",
                "BottomUpMergeSort", "SimpleQuickSort", "InsertionSort",
                "SelectionSort", "JSort", "TimSort");

        for (String basicSortAlgorithm : basicSortAlgorithms) {
            try {
                Class.forName(basicPackage + '.' + basicSortAlgorithm);
            } catch (ClassNotFoundException exception) {

            }
        }
    }
}
