package ua.khpi.oop.malokhvii05.util.algorithms.sort;

import java.lang.reflect.Constructor;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("rawtypes")
public final class SortAlgorithmFactory {

    private static Map<String, Class<? extends SortAlgorithm>> classMapping;
    private static Class<? extends SortAlgorithm> defaultSortAlgorithm;

    static {
        classMapping = new HashMap<String, Class<? extends SortAlgorithm>>();
    }

    private SortAlgorithmFactory() {

    }

    public static void setDefaultSortAlgorithm(
            Class<? extends SortAlgorithm> algorithmClass) {
        defaultSortAlgorithm = algorithmClass;
    }

    @SuppressWarnings("unchecked")
    public static <T> SortAlgorithm<T> getDefaultSortAlgorithm(
            Comparator<T> comparator) {
        if (defaultSortAlgorithm != null) {
            try {
                return defaultSortAlgorithm.getConstructor(Comparator.class)
                        .newInstance(comparator);
            } catch (Exception exception) {

            }
        }
        return NullSortAlgorithm.INSTANCE;
    }

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> SortAlgorithm<T> getDefaultSortAlgorithm() {
        if (defaultSortAlgorithm != null) {
            try {
                return defaultSortAlgorithm.getConstructor(Comparator.class)
                        .newInstance(Comparator.naturalOrder());
            } catch (Exception exception) {

            }
        }
        return NullSortAlgorithm.INSTANCE;
    }

    @SuppressWarnings("unchecked")
    public static <T> SortAlgorithm<T> getSortAlgorithm(String name,
            Comparator<T> comparator) {
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

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> SortAlgorithm<T> getSortAlgorithm(
            String name) {
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

    public static void unregisterSortAlgorithm(String name) {
        classMapping.remove(name);
    }

    public static void registerSortAlgorithm(String name,
            Class<? extends SortAlgorithm> algorithmClass) {
        classMapping.put(name, algorithmClass);
    }
}
