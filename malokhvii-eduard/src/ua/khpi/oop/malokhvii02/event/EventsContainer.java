package ua.khpi.oop.malokhvii02.event;

/**
 * Загальний інтерфейс контейнеру подій, використовується для збереження
 * об'єктів подій для подальшого використання у циклі подій. У ролі ключа до
 * події використовуються об'єкти які реалізують інтерфейс {@link EventType}.
 *
 * @author malokhvii-ee
 * @version 1.0.0
 * @see EventType
 * @see Event
 */
public interface EventsContainer {

    /**
     * Призначений для додавання нової події, до контейнеру.
     *
     * @param item
     *            Ключ нової події
     * @param event
     *            Нова подія
     */
    void putEvent(EventType item, Event event);

    /**
     * Призначений для отримання події за ключем.
     *
     * @param item
     *            Ключ події
     * @return Посилання на подію
     */
    Event getEvent(EventType item);
}
