package ua.khpi.oop.malokhvii05.common.eventbus.publish;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.concurrent.Executor;

import javax.annotation.Nonnull;

import com.google.common.util.concurrent.MoreExecutors;

import ua.khpi.oop.malokhvii05.common.eventbus.dispatch.Dispatcher;
import ua.khpi.oop.malokhvii05.common.eventbus.dispatch.Dispatchers;
import ua.khpi.oop.malokhvii05.common.eventbus.subscribe.DefaultSubscribeExceptionHandler;
import ua.khpi.oop.malokhvii05.common.eventbus.subscribe.SubscriberExceptionHandler;

/**
 * Призначений, для побудови сервісу для обміну подіями. За змовчуванням
 * виконується побудова звичайного сервісу.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 */
public class EventServiceBuilder {

    /**
     * Призначений, для отримання нового єкземпляру будівника сервісу для обміну
     * подіями.
     *
     * @return будівник сервісу для обміну подіями
     * @since 1.0.0
     */
    public static @Nonnull EventServiceBuilder newBuilder() {
        return new EventServiceBuilder();
    }

    /**
     * Відправник, подій до підписчиків.
     *
     * @since 1.0.0
     */
    Dispatcher dispatcher;

    /**
     * Обробник виключних ситуацій, під час відправлення та виконання
     * оброблюючого метода підписчика.
     *
     * @since 1.0.0
     */
    SubscriberExceptionHandler exceptionHandler;

    /**
     * Виконавець методу підписчика.
     *
     * @since 1.0.0
     */
    Executor executor;

    /**
     * Призначений, для ініціалізації компонентів сервісу будівника за
     * змовчуванням, тобто для посслідовного оповіщення.
     *
     * @since 1.0.0
     */
    private EventServiceBuilder() {
        dispatcher = Dispatchers.newPerThreadQueuedDispatcher();
        exceptionHandler = DefaultSubscribeExceptionHandler.INSTANCE;
        executor = MoreExecutors.directExecutor();
    }

    /**
     * Призначений, для встановлення асинхроних компонентів для сервісу.
     *
     * @return будівник сервісу
     * @since 1.0.0
     */
    public @Nonnull EventServiceBuilder asynchronous() {
        dispatcher = Dispatchers.newAsynchronousDispatcher();
        executor = MoreExecutors.directExecutor();
        return this;
    }

    /**
     * Призначений, для побудови сервісу для обміну подіями.
     *
     * @return сервіс для обміну подіями
     * @since 1.0.0
     */
    public @Nonnull EventService build() {
        return new EventService(this);
    }

    /**
     * Призначений, для встановлення послідовних компонентів для сервісу.
     *
     * @return будівник сервісу
     * @since 1.0.0
     */
    public @Nonnull EventServiceBuilder imediate() {
        dispatcher = Dispatchers.newImmediateDispatcher();
        executor = MoreExecutors.directExecutor();
        return this;
    }

    /**
     * Призначений, для встановлення нового відправника події до методів
     * підписчика
     *
     * @param dispatcher
     *            відправник події до методів підписчика
     * @return будівник сервісу
     * @since 1.0.0
     */
    public @Nonnull EventServiceBuilder setDispatcher(
            @Nonnull final Dispatcher dispatcher) {
        this.dispatcher = checkNotNull(dispatcher);
        return this;
    }

    /**
     * Призначений, для встановлення нового обробника виключних ситуацій під час
     * виконання методів підписчика
     *
     * @param exceptionHandler
     *            обробник виключних ситуацій під час виконання методів
     *            підписчика
     * @return будівник сервісу
     * @since 1.0.0
     */
    public @Nonnull EventServiceBuilder setExceptionHandler(
            @Nonnull final SubscriberExceptionHandler exceptionHandler) {
        this.exceptionHandler = checkNotNull(exceptionHandler);
        return this;
    }

    /**
     * Призначений, для встановлення нового виконавця методів підписчика.
     *
     * @param executor
     *            виконавець методів підписчика
     * @return будівник сервісу
     * @since 1.0.0
     */
    public @Nonnull EventServiceBuilder setExecutor(
            @Nonnull final Executor executor) {
        this.executor = checkNotNull(executor);
        return this;
    }
}
