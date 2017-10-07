package ua.khpi.oop.malokhvii05.util.algorithms.search;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("rawtypes")
public final class SearchAlgorithmFactory {

    private static Map<String, Class<? extends SearchAlgorithm>> classMapping;
    private static Class<? extends SearchAlgorithm> defaultAlgorithm;

    static {
        classMapping = new HashMap<String, Class<? extends SearchAlgorithm>>();
        loadBasicAlgorithms();
    }

    private SearchAlgorithmFactory() {

    }

    public static Set<String> getRegisteredAlgorithms() {
        return classMapping.keySet();
    }

    public static void setDefaultAlgorithm(
            Class<? extends SearchAlgorithm> algorithmClass) {
        defaultAlgorithm = algorithmClass;
    }

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

    public static void unregisterAlgorithm(String name) {
        classMapping.remove(name);
    }

    public static void registerAlgorithm(String name,
            Class<? extends SearchAlgorithm> algorithmClass) {
        classMapping.put(name, algorithmClass);
    }

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
