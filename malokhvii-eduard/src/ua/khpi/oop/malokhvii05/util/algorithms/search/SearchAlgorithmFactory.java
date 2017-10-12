package ua.khpi.oop.malokhvii05.util.algorithms.search;

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
 * @author malokhvii-eduard
 * @version 1.0.0
 * @see SearchAlgorithm
 */
@SuppressWarnings("rawtypes")
public final class SearchAlgorithmFactory {

    /**
     * Хеш-карта об'єктів обгорток над класами алгоритмів пошуку.
     */
    private static Map<String, Class<? extends SearchAlgorithm>> classMapping;

    /**
     * Клас алгоритму за змовчування.
     */
    private static Class<? extends SearchAlgorithm> defaultAlgorithm;

    static {
        classMapping = new HashMap<String, Class<? extends SearchAlgorithm>>();
        loadBasicAlgorithms();
    }

    /**
     * Приватний конструктор, для заборони створення фабрик алгоритмів.
     */
    private SearchAlgorithmFactory() {

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
            Class<? extends SearchAlgorithm> algorithmClass) {
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
    public static <T> SearchAlgorithm<T> getDefaultAlgorithm(
            Comparator<T> comparator) {
        if (defaultAlgorithm != null) {
            try {
                return defaultAlgorithm.getConstructor(Comparator.class)
                        .newInstance(comparator);
            } catch (Exception exception) {

            }
        }
        return NullSearchAlgorithm.INSTANCE;
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
    public static <T extends Comparable<T>> SearchAlgorithm<T> getDefaultAlgorithm() {
        if (defaultAlgorithm != null) {
            try {
                return defaultAlgorithm.getConstructor(Comparator.class)
                        .newInstance(Comparator.naturalOrder());
            } catch (Exception exception) {

            }
        }
        return NullSearchAlgorithm.INSTANCE;
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
    public static <T> SearchAlgorithm<T> getAlgorithm(String name,
            Comparator<T> comparator) {
        SearchAlgorithm<T> searchtAlgorithm = NullSearchAlgorithm.INSTANCE;
        Class<? extends SearchAlgorithm> searchAlgorithmClass = classMapping
                .get(name);
        if (searchAlgorithmClass != null) {
            try {
                Constructor<? extends SearchAlgorithm> searchAlgorithmConstructor = searchAlgorithmClass
                        .getConstructor(Comparator.class);
                searchtAlgorithm = searchAlgorithmConstructor
                        .newInstance(comparator);
            } catch (Exception exception) {

            }
        }
        return searchtAlgorithm;
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
    public static <T extends Comparable<T>> SearchAlgorithm<T> getAlgorithm(
            String name) {
        SearchAlgorithm<T> searchAlgorithm = NullSearchAlgorithm.INSTANCE;
        Class<? extends SearchAlgorithm> sortAlgorithmClass = classMapping
                .get(name);
        if (sortAlgorithmClass != null) {
            try {
                Constructor<? extends SearchAlgorithm> searchAlgorithmConstructor = sortAlgorithmClass
                        .getConstructor(Comparator.class);
                searchAlgorithm = searchAlgorithmConstructor
                        .newInstance(Comparator.naturalOrder());
            } catch (Exception exception) {

            }
        }
        return searchAlgorithm;
    }

    /**
     * Призначений, для видалення запису про зарегестрований алгоритм пошуку.
     *
     * @param name
     *            зарегестрована назва алгоритму
     */
    static void unregisterAlgorithm(String name) {
        classMapping.remove(name);
    }

    /**
     * Призначений, для регістрації нового алгоритму пошуку.
     *
     * @param name
     *            назва алгоритму
     * @param algorithmClass
     *            клас нового алгоритму пошуку
     */
    public static void registerAlgorithm(String name,
            Class<? extends SearchAlgorithm> algorithmClass) {
        classMapping.put(name, algorithmClass);
    }

    /**
     * Призначений, для завантаження типових алгоритмів.
     */
    private static void loadBasicAlgorithms() {
        final String basicPackage = "ua.khpi.oop.malokhvii05.util.algorithms"
                + ".search";
        final List<String> basicSortAlgorithms = Arrays.asList("BinarySearch",
                "ExponentialSearch", "LinearSearch", "LinearWithBarrierSearch",
                "GallopSearch", "GallopWithExternalSearch", "FibonacciSearch");

        for (String basicSortAlgorithm : basicSortAlgorithms) {
            try {
                Class.forName(basicPackage + '.' + basicSortAlgorithm);
            } catch (ClassNotFoundException exception) {

            }
        }
    }
}
