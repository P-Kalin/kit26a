package ua.khpi.oop.malokhvii05.util.algorithms;

/**
 * Інтерфейс, призначений для оголошення заглушки для фабрики алгоритмів,
 * використовується для повернення замість null, об'єкту заглушки. Таким чином
 * захищаючи користувача від не передюачених помилок.
 *
 * @author Ed
 * @version 1.0.0
 * @see NullAlgorithmWithComparator
 * @param <T>
 *            Тип даних, які оброблює алгоритм
 * @since 1.0.0
 */
public abstract class NullAlgorithm<T> implements Algorithm<T> {

    @Override
    public final boolean isNull() {
        return true;
    }
}
