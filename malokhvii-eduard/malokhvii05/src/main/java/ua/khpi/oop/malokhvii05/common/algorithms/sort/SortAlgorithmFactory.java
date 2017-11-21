package ua.khpi.oop.malokhvii05.common.algorithms.sort;

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Фабрика, алгоритмів сортування, призначена для отримання об'єкту алгоритму.
 * Якщо, вказано не вірну назву алгоритму повертається заглушка, тобто не
 * дійсний алгоритм для сортування.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 * @see SortAlgorithm
 */
@SuppressWarnings("rawtypes")
public final class SortAlgorithmFactory {

    /**
     * Хеш-карта об'єктів обгорток над класами алгоритмів сортування.
     *
     * @since 1.0.0
     */
    private static Map<String, Class<? extends SortAlgorithm>> classMapping;

    /**
     * Клас алгоритму за змовчування.
     *
     * @since 1.0.0
     */
    private static Class<? extends SortAlgorithm> defaultAlgorithm;

    static {
        SortAlgorithmFactory.classMapping = new HashMap<>();
        SortAlgorithmFactory.loadBasicAlgorithms();
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
    public static @Nonnull <T extends Comparable<T>> SortAlgorithm<T> getAlgorithm(
            @Nullable final String name) {
        SortAlgorithm<T> sortAlgorithm = NullSortAlgorithm.INSTANCE;
        final Class<? extends SortAlgorithm> sortAlgorithmClass = SortAlgorithmFactory.classMapping
                .get(name);
        if (sortAlgorithmClass != null) {
            try {
                final Constructor<? extends SortAlgorithm> sortAlgorithmConstructor = sortAlgorithmClass
                        .getConstructor(Comparator.class);
                sortAlgorithm = sortAlgorithmConstructor
                        .newInstance(Comparator.naturalOrder());
            } catch (final Exception exception) {

            }
        }
        return sortAlgorithm;
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
    public static @Nonnull <T> SortAlgorithm<T> getAlgorithm(
            @Nullable final String name,
            @Nonnull final Comparator<? super T> comparator) {
        SortAlgorithm<T> sortAlgorithm = NullSortAlgorithm.INSTANCE;
        final Class<? extends SortAlgorithm> sortAlgorithmClass = SortAlgorithmFactory.classMapping
                .get(name);
        if (sortAlgorithmClass != null) {
            try {
                final Constructor<? extends SortAlgorithm> sortAlgorithmConstructor = sortAlgorithmClass
                        .getConstructor(Comparator.class);
                sortAlgorithm = sortAlgorithmConstructor
                        .newInstance(comparator);
            } catch (final Exception exception) {

            }
        }
        return sortAlgorithm;
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
    public static @Nonnull <T extends Comparable<T>> SortAlgorithm<T> getDefaultAlgorithm() {
        if (SortAlgorithmFactory.defaultAlgorithm != null) {
            try {
                return SortAlgorithmFactory.defaultAlgorithm
                        .getConstructor(Comparator.class)
                        .newInstance(Comparator.naturalOrder());
            } catch (final Exception exception) {

            }
        }
        return NullSortAlgorithm.INSTANCE;
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
    public static @Nonnull <T> SortAlgorithm<T> getDefaultAlgorithm(
            @Nonnull final Comparator<? super T> comparator) {
        if (SortAlgorithmFactory.defaultAlgorithm != null) {
            try {
                return SortAlgorithmFactory.defaultAlgorithm
                        .getConstructor(Comparator.class)
                        .newInstance(comparator);
            } catch (final Exception exception) {

            }
        }
        return NullSortAlgorithm.INSTANCE;
    }

    /**
     * Пирзначений, для отримання множини зарегестрованих назв алгоритмів.
     *
     * @return множина зарегестрованих назв алгоритмів
     * @since 1.0.0
     */
    public static @Nonnull Set<String> getRegisteredAlgorithms() {
        return SortAlgorithmFactory.classMapping.keySet();
    }

    /**
     * Призначений, для завантаження типових алгоритмів.
     *
     * @since 1.0.0
     */
    private static void loadBasicAlgorithms() {
        final String basicPackage = "ua.khpi.oop.malokhvii05.common.algorithms"
                + ".sort";
        final List<String> basicSortAlgorithms = Arrays.asList("GnomeSort",
                "BubbleSort", "ShellSort", "HeapSort", "TopDownMergeSort",
                "BottomUpMergeSort", "QuickSort", "InsertionSort",
                "SelectionSort", "JSort", "TimSort");

        for (final String basicSortAlgorithm : basicSortAlgorithms) {
            try {
                Class.forName(basicPackage + '.' + basicSortAlgorithm);
            } catch (final ClassNotFoundException exception) {

            }
        }
    }

    /**
     * Призначений, для регістрації нового алгоритму сортування.
     *
     * @param name
     *            назва алгоритму
     * @param algorithmClass
     *            клас нового алгоритму сортування
     * @since 1.0.0
     */
    public static void registerAlgorithm(@Nonnull final String name,
            @Nonnull final Class<? extends SortAlgorithm> algorithmClass) {
        SortAlgorithmFactory.classMapping.put(checkNotNull(name),
                checkNotNull(algorithmClass));
    }

    /**
     * Призначений, для встановлення алгоритму за змовчуванням.
     *
     * @param algorithmClass
     *            клас алгоритму за змовчуванням
     * @since 1.0.0
     */
    public static void setDefaultAlgorithm(
            @Nonnull final Class<? extends SortAlgorithm> algorithmClass) {
        SortAlgorithmFactory.defaultAlgorithm = checkNotNull(algorithmClass);
    }

    /**
     * Призначений, для видалення запису про зарегестрований алгоритм
     * сортування.
     *
     * @param name
     *            зарегестрована назва алгоритму
     * @since 1.0.0
     */
    public static void unregisterAlgorithm(@Nullable final String name) {
        SortAlgorithmFactory.classMapping.remove(name);
    }

    /**
     * Приватний конструктор, для заборони створення фабрик алгоритмів.
     *
     * @since 1.0.0
     */
    private SortAlgorithmFactory() {

    }
}
