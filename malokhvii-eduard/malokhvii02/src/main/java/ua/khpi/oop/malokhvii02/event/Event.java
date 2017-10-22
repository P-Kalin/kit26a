package ua.khpi.oop.malokhvii02.event;

import ua.khpi.oop.malokhvii02.data.DataContainer;

/**
 * Загальний інтерфейс події. Використовується для опису складових програми які
 * потім будуть використані у скінченному автоматі подій. Кожна подія повинна
 * вміти власноруч оброблювати об'єкт головного циклу подій, та вмикате наступну
 * подію під час завершення (якщо це необхідно). А також взаємодіяти з
 * контейнером даних.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 * @see EventLoop
 * @see DataContainer
 */
public interface Event {

    /**
     * Призначений для виконання логіки події.
     *
     * @param eventLoop
     *            Об'єкт циклу подій
     * @param dataContainer
     *            Контейнер даних, для обробки
     * @since 1.0.0
     */
    void perform(EventLoop eventLoop, DataContainer dataContainer);
}
