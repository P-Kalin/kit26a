package ua.khpi.oop.malokhvii05.common.eventbus.publish;

import javax.annotation.Nonnull;

/**
 * Інтерфейс, призначений для оголошення загальної поведінки відправника подій.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 */
public interface Publisher {

    /**
     * Призначений, для відправлення події.
     *
     * @param event
     *            подія, для оповіщення
     * @since 1.0.0
     */
    void publish(@Nonnull Object event);

    /**
     * Призначений, для відправлення переліку подій.
     *
     * @param events
     *            перелік подій
     * @since 1.0.0
     */
    void publishAll(@Nonnull Iterable<Object> events);

    /**
     * Призначений, для підписування об'єкту на повідомлення.
     *
     * @param subscriber
     *            об'єкт, підписчик
     * @since 1.0.0
     */
    void subscribe(@Nonnull Object subscriber);

    /**
     * Призначений, для підписування переліку об'єктів на повідомлення.
     *
     * @param subscribers
     *            перелік об'єктів, підписчиків
     * @since 1.0.0
     */
    void subscribeAll(@Nonnull Iterable<Object> subscribers);

    /**
     * Призначений, для відписування об'єкту від повідомлень.
     *
     * @param subscriber
     *            об'єкт, підписчик
     * @since 1.0.0
     */
    void unsubscribe(@Nonnull Object subscriber);

    /**
     * Призначений, для відписування переліку об'єктів від повідомленнь.
     *
     * @param subscribers
     *            перелік об'єктів, підписчиків
     * @since 1.0.0
     */
    void unsubscribeAll(@Nonnull Iterable<Object> subscribers);
}
