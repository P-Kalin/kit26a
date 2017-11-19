package ua.khpi.oop.malokhvii05.common.eventbus.subscribe;

/**
 * Призначений, для оголошення можливих політик посилань на підписчика під час
 * збереження у {@link SubscriberRegistry}.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 */
public enum SubscriberReferencePolicy {

    /**
     * Політика м'якого посилання, тобто м'яке посилання на об'єкт, не завадить
     * видаленню під час виклику збирача сміття, якщо пам'ять заповнена и
     * виникає можливість виникнення {@link OutOfMemoryError}. Коли буде
     * повернено {@code null} методом {@link Subscriber#getSubscriber}, у методі
     * {@link Subscriber#dispatchEvent(Object)}, буде надіслано запит на виклик
     * {@link SubscriberRegistry#removeUnusedSubscribers()}.
     *
     * @since 1.0.0
     */
    SOFT,
    /**
     * Політика сильного посилання, тобто звичайне посилання на об'єкт
     * підписчика.
     *
     * @since 1.0.0
     */
    STRONG,
    /**
     * Політика слабкого посилання, тобто слабке посилання на об'єкт підписчика,
     * не завадить видаленню під час виклику збирача сміття. Коли буде повернено
     * {@code null} методом {@link Subscriber#getSubscriber}, у методі
     * {@link Subscriber#dispatchEvent(Object)}, буде надіслано запит на виклик
     * {@link SubscriberRegistry#removeUnusedSubscribers()}.
     *
     * @since 1.0.0
     */
    WEAK
}
