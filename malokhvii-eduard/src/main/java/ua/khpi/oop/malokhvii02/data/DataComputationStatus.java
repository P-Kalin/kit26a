package ua.khpi.oop.malokhvii02.data;

/**
 * Перелік можливих станів завершення операції обробки контейнеру даних.
 * Використовується для контролювання стану обробки отриманих даних для
 * контейнеру даних.
 *
 * @author malokhvii-eduard
 * @version 1.0.0
 * @see DataContainer
 * @see {@link DataContainer#computeData()}
 * @since 1.0.0
 */
public enum DataComputationStatus {

    /**
     * Стан, призначений для відмітки успішного завершення операції обробки
     * даних контейнеру. Використовувати, якщо операція обробки успішно
     * завершена.
     *
     * @since 1.0.0
     */
    SUCCESS,
    /**
     * Стан, призначений для відмітки невдалого завершення операції обробки
     * даних контейнеру. Використовувати, якщо операція обробки неуспішно
     * завершена.
     *
     * @since 1.0.0
     */
    FAILURE
}
