package ua.khpi.oop.malokhvii05.common.eventbus.publish;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.concurrent.Executor;

import javax.annotation.Nonnull;

import ua.khpi.oop.malokhvii05.common.eventbus.dispatch.Dispatcher;

/**
 * Сервіс, призначений для реалізації оповіщення підписчиків подією.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 */
public class EventService {

    /**
     * Шина подій, призначена для обміну подіями між підписниками та видавцями.
     *
     * @since 1.0.0
     */
    private final EventBus eventBus;

    EventService(final EventServiceBuilder builder) {
        checkNotNull(builder);
        eventBus = new EventBus(builder.dispatcher, builder.executor,
                builder.exceptionHandler);
    }

    /**
     * Призначений, для отримання відправника подій до підписчиків.
     *
     * @return відправник подій до підписчиків
     * @since 1.0.0
     */
    public @Nonnull Dispatcher getDispatcher() {
        return eventBus.getDispatcher();
    }

    /**
     * Призначений, для отримання виконавця методів підписчика.
     *
     * @return виконавець методів підписчика
     * @since 1.0.0
     */
    public @Nonnull Executor getExecutor() {
        return eventBus.getExecutor();
    }

    /**
     * Призначений, для перевірки чи присутній підписчик у поточному сховищі.
     *
     * @param subscriber
     *            підписчик, для перевірки
     * @return результат перевірки
     * @since 1.0.0
     */
    public boolean isSubscriber(final Object subscriber) {
        return eventBus.contains(subscriber);
    }

    /**
     * Призначений, для публікації події, для усіх підписчиків на даний тип
     * події. Під час виникнення фатальних ситуацій надсилається подія
     * {@link DeadEvent}.
     *
     * @param event
     *            подія, для публікації
     * @since 1.0.0
     */
    public void publish(final Object event) {
        eventBus.post(event);
    }

    /**
     * Призначений, для оновлення відправника подій.
     *
     * @param dispatcher
     *            оновлений відправник подій
     * @since 1.0.0
     */
    public void setDispatcher(@Nonnull final Dispatcher dispatcher) {
        eventBus.setDispatcher(dispatcher);
    }

    /**
     * Призначений, для регестрації нового підписчика. Підписчик повинен бути
     * відмічений анотацією
     * {@link ua.khpi.oop.malokhvii05.common.eventbus.annotations.Subscriber
     * Subscriber}, а його методи для обробки подій відмічені анотацією
     * {@link ua.khpi.oop.malokhvii05.common.eventbus.annotations.Subscribe}.
     *
     * @param subscriber
     *            підписчик
     * @since 1.0.0
     */
    public void subscribe(final Object subscriber) {
        eventBus.register(subscriber);
    }

    /**
     * Призначений, для видалення підписчика. Тобто відписки підписчика від
     * подальших повідомлень.
     *
     * @param subscriber
     *            підписчик
     * @since 1.0.0
     */
    public void unsubscribe(final Object subscriber) {
        eventBus.unregister(subscriber);
    }
}
