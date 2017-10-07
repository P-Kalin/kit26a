package ua.khpi.oop.malokhvii05.util.algorithms.sort;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("rawtypes")
public final class SortAlgorithmFactory {

    private static Map<String, Class<? extends SortAlgorithm>> classMapping;
    private static Class<? extends SortAlgorithm> defaultAlgorithm;

    static {
        classMapping = new HashMap<String, Class<? extends SortAlgorithm>>();
        loadBasicAlgorithms();
    }

    private SortAlgorithmFactory() {

    }

    public static Set<String> getRegisteredAlgorithms() {
        return classMapping.keySet();
    }

    public static void setDefaultAlgorithm(
            Class<? extends SortAlgorithm> algorithmClass) {
        defaultAlgorithm = algorithmClass;
    }

    @SuppressWarnings("unchecked")
    public static <T> SortAlgorithm<T> getDefaultAlgorithm(
            Comparator<T> comparator) {
        if (defaultAlgorithm != null) {
            try {
                return defaultAlgorithm.getConstructor(Comparator.class)
                        .newInstance(comparator);
            } catch (Exception exception) {

            }
        }
        return NullSortAlgorithm.INSTANCE;
    }

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

    @SuppressWarnings("unchecked")
    public static <T> SortAlgorithm<T> getAlgorithm(String name,
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
    public static <T extends Comparable<T>> SortAlgorithm<T> getAlgorithm(
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

    public static void unregisterAlgorithm(String name) {
        classMapping.remove(name);
    }

    public static void registerAlgorithm(String name,
            Class<? extends SortAlgorithm> algorithmClass) {
        classMapping.put(name, algorithmClass);
    }

    private static void loadBasicAlgorithms() {
        final String basicPackage = "ua.khpi.oop.malokhvii05.util.algorithms"
                + ".sort";
        final List<String> basicSortAlgorithms = Arrays.asList("GnomeSort",
                "BubbleSort", "ShellSort", "HeapSort", "TopDownMergeSort",
                "BottomUpMergeSort", "QuickSort", "InsertionSort",
                "SelectionSort");

        for (String basicSortAlgorithm : basicSortAlgorithms) {
            try {
                Class.forName(basicPackage + '.' + basicSortAlgorithm);
            } catch (ClassNotFoundException exception) {

            }
        }
    }
}
