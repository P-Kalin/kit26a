package ua.khpi.oop.malokhvii05.util.algorithms;

import java.util.Comparator;

/**
 * Інтерфейс, призначений для оголошення алгоритму, який потребує використання
 * компоратора для обчислення вхідних даних.
 *
 * @author malokhvii-eduard
 * @version 1.0.0
 * @see AbstractAlgorithmWithComparator
 * @param <T>
 *            Тип даних, які оброблює алгоритм, а також тип компоратору для
 *            даних
 */
public interface AlgorithmWithComparator<T> extends Algorithm<T> {

    /**
     * Призначений, для оновлення компаратору для обчислення даних.
     *
     * @param comparator
     *            новий об'єкт компоратора
     */
    void setComparator(Comparator<T> comparator);

    /**
     * Призначений, для отримання поточного компаратора.
     *
     * @return поточний компаратор
     */
    Comparator<T> getComparator();
}
