package ua.khpi.oop.malokhvii05.common.algorithms.search;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Фабрика, алгоритмів пошуку, призначена для отримання об'єкту алгоритму. Якщо,
 * вказано не вірну назву алгоритму повертається заглушка, тобто не дійсний
 * алгоритм для пошуку.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 * @see SearchAlgorithm
 */
@SuppressWarnings("rawtypes")
public final class SearchAlgorithmFactory {

    /**
     * Хеш-карта об'єктів обгорток над класами алгоритмів пошуку.
     *
     * @since 1.0.0
     */
    private static Map<String, Class<? extends SearchAlgorithm>> classMapping;

    /**
     * Клас алгоритму за змовчування.
     *
     * @since 1.0.0
     */
    private static Class<? extends SearchAlgorithm> defaultAlgorithm;

    static {
        SearchAlgorithmFactory.classMapping = new HashMap<String, Class<? extends SearchAlgorithm>>();
        SearchAlgorithmFactory.loadBasicAlgorithms();
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
     * @since 1.0.0
     */
    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> SearchAlgorithm<T> getAlgorithm(
            final String name) {
        SearchAlgorithm<T> searchAlgorithm = NullSearchAlgorithm.INSTANCE;
        final Class<? extends SearchAlgorithm> sortAlgorithmClass = SearchAlgorithmFactory.classMapping
                .get(name);
        if (sortAlgorithmClass != null) {
            try {
                final Constructor<? extends SearchAlgorithm> searchAlgorithmConstructor = sortAlgorithmClass
                        .getConstructor(Comparator.class);
                searchAlgorithm = searchAlgorithmConstructor
                        .newInstance(Comparator.naturalOrder());
            } catch (final Exception exception) {

            }
        }
        return searchAlgorithm;
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
     * @since 1.0.0
     */
    @SuppressWarnings("unchecked")
    public static <T> SearchAlgorithm<T> getAlgorithm(final String name,
            final Comparator<T> comparator) {
        SearchAlgorithm<T> searchtAlgorithm = NullSearchAlgorithm.INSTANCE;
        final Class<? extends SearchAlgorithm> searchAlgorithmClass = SearchAlgorithmFactory.classMapping
                .get(name);
        if (searchAlgorithmClass != null) {
            try {
                final Constructor<? extends SearchAlgorithm> searchAlgorithmConstructor = searchAlgorithmClass
                        .getConstructor(Comparator.class);
                searchtAlgorithm = searchAlgorithmConstructor
                        .newInstance(comparator);
            } catch (final Exception exception) {

            }
        }
        return searchtAlgorithm;
    }

    /**
     * Призначений, для отримання алгоритму за змовчуванням. Для типів які
     * реалізують інтерфейс {@link Comparable}. В якості компаратора
     * використовується {@link Comparator#naturalOrder}.
     *
     * @param <T>
     *            Тип, об'єктів для яких використовується компаратор
     * @return алгоритм за змовчуванням, якщо алгоритм за змовчуванням не
     *         визначено, повертає заглушку на недійсний алгоритм.
     * @since 1.0.0
     */
    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> SearchAlgorithm<T> getDefaultAlgorithm() {
        if (SearchAlgorithmFactory.defaultAlgorithm != null) {
            try {
                return SearchAlgorithmFactory.defaultAlgorithm
                        .getConstructor(Comparator.class)
                        .newInstance(Comparator.naturalOrder());
            } catch (final Exception exception) {

            }
        }
        return NullSearchAlgorithm.INSTANCE;
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
     * @since 1.0.0
     */
    @SuppressWarnings("unchecked")
    public static <T> SearchAlgorithm<T> getDefaultAlgorithm(
            final Comparator<T> comparator) {
        if (SearchAlgorithmFactory.defaultAlgorithm != null) {
            try {
                return SearchAlgorithmFactory.defaultAlgorithm
                        .getConstructor(Comparator.class)
                        .newInstance(comparator);
            } catch (final Exception exception) {

            }
        }
        return NullSearchAlgorithm.INSTANCE;
    }

    /**
     * Пирзначений, для отримання множини зарегестрованих назв алгоритмів.
     *
     * @return множина зарегестрованих назв алгоритмів
     * @since 1.0.0
     */
    public static Set<String> getRegisteredAlgorithms() {
        return SearchAlgorithmFactory.classMapping.keySet();
    }

    /**
     * Призначений, для завантаження типових алгоритмів.
     *
     * @since 1.0.0
     */
    private static void loadBasicAlgorithms() {
        final String basicPackage = "ua.khpi.oop.malokhvii05.util.algorithms"
                + ".search";
        final List<String> basicSortAlgorithms = Arrays.asList("BinarySearch",
                "ExponentialSearch", "LinearSearch", "LinearWithBarrierSearch",
                "GallopSearch", "GallopWithExternalSearch", "FibonacciSearch");

        for (final String basicSortAlgorithm : basicSortAlgorithms) {
            try {
                Class.forName(basicPackage + '.' + basicSortAlgorithm);
            } catch (final ClassNotFoundException exception) {

            }
        }
    }

    /**
     * Призначений, для регістрації нового алгоритму пошуку.
     *
     * @param name
     *            назва алгоритму
     * @param algorithmClass
     *            клас нового алгоритму пошуку
     * @since 1.0.0
     */
    public static void registerAlgorithm(final String name,
            final Class<? extends SearchAlgorithm> algorithmClass) {
        SearchAlgorithmFactory.classMapping.put(name, algorithmClass);
    }

    /**
     * Призначений, для встановлення алгоритму за змовчуванням.
     *
     * @param algorithmClass
     *            клас алгоритму за змовчуванням
     * @since 1.0.0
     */
    public static void setDefaultAlgorithm(
            final Class<? extends SearchAlgorithm> algorithmClass) {
        SearchAlgorithmFactory.defaultAlgorithm = algorithmClass;
    }

    /**
     * Призначений, для видалення запису про зарегестрований алгоритм пошуку.
     *
     * @param name
     *            зарегестрована назва алгоритму
     * @since 1.0.0
     */
    static void unregisterAlgorithm(final String name) {
        SearchAlgorithmFactory.classMapping.remove(name);
    }

    /**
     * Приватний конструктор, для заборони створення фабрик алгоритмів.
     *
     * @since 1.0.0
     */
    private SearchAlgorithmFactory() {

    }
}
