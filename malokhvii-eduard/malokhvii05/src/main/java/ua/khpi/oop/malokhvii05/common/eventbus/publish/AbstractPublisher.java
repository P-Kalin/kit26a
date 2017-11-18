package ua.khpi.oop.malokhvii05.common.eventbus.publish;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nonnull;

/**
 * Призначений, для делегування звернень до об'єкту {@link EventBus}, та
 * адаптування його до інтерфейсу {@link Publisher}.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 */
public abstract class AbstractPublisher implements Publisher {

    /**
     * Шина подій, для забезпечення механізму публікація-підписка.
     *
     * @since 1.0.0
     */
    private final EventBus eventBus;

    /**
     * Призначений, для ініціалізації публікатора, шиною подій, для її
     * подальшого делегування.
     *
     * @param eventBus
     *            шина подій
     * @since 1.0.0
     */
    protected AbstractPublisher(@Nonnull final EventBus eventBus) {
        this.eventBus = checkNotNull(eventBus);
    }

    @Override
    public final void publish(@Nonnull final Object event) {
        eventBus.post(event);
    }

    @Override
    public final void publishAll(@Nonnull final Iterable<Object> events) {
        for (final Object event : events) {
            eventBus.post(event);
        }
    }

    @Override
    public final void subscribe(@Nonnull final Object subscriber) {
        eventBus.register(subscriber);
    }

    @Override
    public final void subscribeAll(
            @Nonnull final Iterable<Object> subscribers) {
        for (final Object subscriber : subscribers) {
            eventBus.register(subscriber);
        }
    }

    @Override
    public final void unsubscribe(@Nonnull final Object subscriber) {
        eventBus.unregister(subscriber);
    }

    @Override
    public final void unsubscribeAll(
            @Nonnull final Iterable<Object> subscribers) {
        for (final Object subscriber : subscribers) {
            eventBus.unregister(subscriber);
        }
    }
}
