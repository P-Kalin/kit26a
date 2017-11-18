package ua.khpi.oop.malokhvii05.common.eventbus.subscribe;

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.Method;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import ua.khpi.oop.malokhvii05.common.eventbus.publish.EventBus;

/**
 * Призначений, для збереження додаткових відомостей для
 * {@link SubscriberExceptionHandler обробника виключних ситуацій}.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 */
@Immutable
public final class SubscriberExceptionContext {

    /**
     * Об'єкт, який відповідний за оповіщення усіх підписчиків.
     *
     * @since 1.0.0
     */
    private final EventBus eventBus;

    /**
     * Подія, якою повинен був бути оповіщен підписчик.
     *
     * @since 1.0.0
     */
    private final Object event;

    /**
     * Підписчик, який повинен був отримати подію.
     *
     * @since 1.0.0
     */
    private final Object subscriber;

    /**
     * Метод підписчика, який повинен був обробити подію.
     *
     * @since 1.0.0
     */
    private final Method subscriberMethod;

    /**
     * Призначений, для ініціалізації контексту виключної ситуації.
     *
     * @param eventBus
     *            об'єкт, який відповідний за оповіщення усіх підписчиків
     * @param event
     *            подія, якою повинен був бути оповіщен підписчик
     * @param subscriber
     *            підписчик, який повинен був отримати подію
     * @param subscriberMethod
     *            метод підписчика, який повинен був обробити подію
     * @since 1.0.0
     */
    SubscriberExceptionContext(@Nonnull EventBus eventBus,
            @Nonnull Object event, @Nonnull Object subscriber,
            @Nonnull Method subscriberMethod) {
        this.eventBus = checkNotNull(eventBus);
        this.event = checkNotNull(event);
        this.subscriber = checkNotNull(subscriber);
        this.subscriberMethod = checkNotNull(subscriberMethod);
    }

    public @Nonnull EventBus getEventBus() {
        return eventBus;
    }

    /**
     * Призначений, для отримання події, якою повинен був бути оповіщен
     * підписчик.
     *
     * @return подія, якою повинен був бути оповіщен підписчик
     * @since 1.0.0
     */
    public @Nonnull Object getEvent() {
        return event;
    }

    /**
     * Призначений, для отримання підписчика, який повинен був отримати подію.
     *
     * @return підписчик, який повинен був отримати подію
     * @since 1.0.0
     */
    public @Nonnull Object getSubscriber() {
        return subscriber;
    }

    /**
     * Призначений, для отримання методу підписчика, який повинен був обробити
     * подію.
     *
     * @return метод підписчика, який повинен був обробити подію
     * @since 1.0.0
     */
    public @Nonnull Method getSubscriberMethod() {
        return subscriberMethod;
    }
}
