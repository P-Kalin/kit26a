package ua.khpi.oop.malokhvii05.common.eventbus.subscribe;

import java.lang.reflect.Method;

import javax.annotation.Nonnull;

import ua.khpi.oop.malokhvii05.common.eventbus.meta.SubscribeMetadata;
import ua.khpi.oop.malokhvii05.common.eventbus.meta.SubscriberMetadata;
import ua.khpi.oop.malokhvii05.common.eventbus.publish.EventBus;

/**
 * Призначений, для створення записів про підписчика, для збереження в
 * {@link SubscriberRegistry}.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 */
public final class Subscribers {

    /**
     * Призначений, для заборони створення об'єктів.
     *
     * @since 1.0.0
     */
    private Subscribers() {

    }

    /**
     * Призначений, для створення запису про підписчика, згідно політики
     * посилання {@link SubscriberReferencePolicy#SOFT}.
     *
     * @param subscriber
     *            підписчик
     * @param method
     *            обробник події
     * @param eventBus
     *            посилання на об'єкт відповідальний за оповіщення
     * @param subscriberMetadata
     *            об'єкте представлення вилучених даних із анотації
     *            {@link Subscribe}
     * @param subscribeMetadata
     *            об'єкте представлення вилучених даних із анотації
     *            {@link Subscriber}
     * @return запис про підписчика
     * @since 1.0.0
     */
    public static @Nonnull Subscriber newSoftSubscriber(
            @Nonnull final Object subscriber, @Nonnull final Method method,
            @Nonnull final EventBus eventBus,
            @Nonnull final SubscriberMetadata subscriberMetadata,
            @Nonnull final SubscribeMetadata subscribeMetadata) {
        return new SoftSubscriber(subscriber, method, eventBus, subscriberMetadata,
                subscribeMetadata);
    }

    /**
     * Призначений, для створення запису про підписчика, згідно політики
     * посилання {@link SubscriberReferencePolicy#STRONG}.
     *
     * @param subscriber
     *            підписчик
     * @param method
     *            обробник події
     * @param eventBus
     *            посилання на об'єкт відповідальний за оповіщення
     * @param subscriberMetadata
     *            об'єкте представлення вилучених даних із анотації
     *            {@link Subscribe}
     * @param subscribeMetadata
     *            об'єкте представлення вилучених даних із анотації
     *            {@link Subscriber}
     * @return запис про підписчика
     * @since 1.0.0
     */
    public static @Nonnull Subscriber newStrongSubscriber(
            @Nonnull final Object subscriber, @Nonnull final Method method,
            @Nonnull final EventBus eventBus,
            @Nonnull final SubscriberMetadata subscriberMetadata,
            @Nonnull final SubscribeMetadata subscribeMetadata) {
        return new StrongSubcriber(subscriber, method, eventBus, subscriberMetadata,
                subscribeMetadata);
    }

    /**
     * Призначений, для створення запису про підписчика, згідно політики
     * посилання {@link SubscriberReferencePolicy#WEAK}.
     *
     * @param subscriber
     *            підписчик
     * @param method
     *            обробник події
     * @param eventBus
     *            посилання на об'єкт відповідальний за оповіщення
     * @param subscriberMetadata
     *            об'єкте представлення вилучених даних із анотації
     *            {@link Subscribe}
     * @param subscribeMetadata
     *            об'єкте представлення вилучених даних із анотації
     *            {@link Subscriber}
     * @return запис про підписчика
     * @since 1.0.0
     */
    public static @Nonnull Subscriber newWeakSubscriber(
            @Nonnull final Object subscriber, @Nonnull final Method method,
            @Nonnull final EventBus eventBus,
            @Nonnull final SubscriberMetadata subscriberMetadata,
            @Nonnull final SubscribeMetadata subscribeMetadata) {
        return new WeakSubscriber(subscriber, method, eventBus, subscriberMetadata,
                subscribeMetadata);
    }
}
