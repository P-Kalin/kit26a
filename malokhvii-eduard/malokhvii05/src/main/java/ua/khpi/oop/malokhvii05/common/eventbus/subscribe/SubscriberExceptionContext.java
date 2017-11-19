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
     * Подія, якою повинен був бути оповіщен підписчик.
     *
     * @since 1.0.0
     */
    private final Object event;

    /**
     * Об'єкт, який відповідний за оповіщення усіх підписчиків.
     *
     * @since 1.0.0
     */
    private final EventBus eventBus;

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
    SubscriberExceptionContext(@Nonnull final EventBus eventBus,
            @Nonnull final Object event, @Nonnull final Object subscriber,
            @Nonnull final Method subscriberMethod) {
        this.eventBus = checkNotNull(eventBus);
        this.event = checkNotNull(event);
        this.subscriber = checkNotNull(subscriber);
        this.subscriberMethod = checkNotNull(subscriberMethod);
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

    public @Nonnull EventBus getEventBus() {
        return eventBus;
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
