package ua.khpi.oop.malokhvii05.common.eventbus.subscribe;

import java.lang.ref.SoftReference;
import java.lang.reflect.Method;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import ua.khpi.oop.malokhvii05.common.eventbus.meta.SubscribeMetadata;
import ua.khpi.oop.malokhvii05.common.eventbus.meta.SubscriberMetadata;
import ua.khpi.oop.malokhvii05.common.eventbus.publish.EventBus;

/**
 * Призначений, для реалізації запису про підписчика із м'яким посиланням.
 * Реалізує політику посилання {@link SubscriberReferencePolicy#SOFT}.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 * @see Subscriber
 */
@Immutable
final class SoftSubscriber extends Subscriber {

    /**
     * М'яке посилання на підписчика.
     *
     * @since 1.0.0
     */
    private SoftReference<Object> subscriber;

    /**
     * Призначений, для ініціалізації підписчика необхідними даними, для
     * подальшого оповіщення та обробки.
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
     * @since 1.0.0
     */
    SoftSubscriber(@Nonnull final Object subscriber,
            @Nonnull final Method method, @Nonnull final EventBus eventBus,
            @Nonnull final SubscriberMetadata subscriberMetadata,
            @Nonnull final SubscribeMetadata subscribeMetadata) {
        super(method, eventBus, subscriberMetadata, subscribeMetadata);
        this.subscriber = new SoftReference<>(subscriber);
    }

    @Override
    public @Nonnull Object getSubscriber() {
        return subscriber.get();
    }
}
