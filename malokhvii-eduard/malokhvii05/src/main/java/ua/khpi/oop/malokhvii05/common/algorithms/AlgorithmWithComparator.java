package ua.khpi.oop.malokhvii05.common.algorithms;

import java.util.Comparator;

import javax.annotation.Nonnull;

/**
 * Інтерфейс, призначений для оголошення алгоритму, який потребує використання
 * компоратора для обчислення вхідних даних.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 * @see AbstractAlgorithmWithComparator
 * @param <T>
 *            Тип даних, які оброблює алгоритм, а також тип компоратору для
 *            даних
 */
public interface AlgorithmWithComparator<T> extends Algorithm<T> {

    /**
     * Призначений, для отримання поточного компаратора.
     *
     * @return поточний компаратор
     * @since 1.0.0
     */
    @Nonnull
    Comparator<T> getComparator();

    /**
     * Призначений, для оновлення компаратору для обчислення даних.
     *
     * @param comparator
     *            новий об'єкт компоратора
     * @since 1.0.0
     */
    void setComparator(@Nonnull Comparator<T> comparator);
}
