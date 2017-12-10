package ua.khpi.oop.malokhvii05.common.eventbus.publish;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Iterator;
import java.util.concurrent.Executor;

import javax.annotation.Nonnull;

import com.google.common.util.concurrent.MoreExecutors;

import ua.khpi.oop.malokhvii05.common.eventbus.dispatch.Dispatcher;
import ua.khpi.oop.malokhvii05.common.eventbus.dispatch.Dispatchers;
import ua.khpi.oop.malokhvii05.common.eventbus.subscribe.DefaultSubscribeExceptionHandler;
import ua.khpi.oop.malokhvii05.common.eventbus.subscribe.Subscriber;
import ua.khpi.oop.malokhvii05.common.eventbus.subscribe.SubscriberExceptionContext;
import ua.khpi.oop.malokhvii05.common.eventbus.subscribe.SubscriberExceptionHandler;
import ua.khpi.oop.malokhvii05.common.eventbus.subscribe.SubscriberRegistry;

/**
 * Відправляє події підписчикам і дає змогу підписчикам зареєструвати себе.
 * {@link EventBus} дозволяє реалізувати стиль зв'язку публікація-підписка між
 * компонентами без необхідності компонентів явно взаємодіяти один з одним (і,
 * таким чином, бути в курсі один одного).
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.1
 */
public final class EventBus {

    /**
     * Флаг, очищення. Сигналізує чи було отримано запит на очищення сховища
     * записів про підписчиків.
     *
     * @since 1.0.0
     */
    private boolean cleanupAvailable;

    /**
     * Відправник, подій до підписчиків.
     *
     * @since 1.0.0
     */
    private Dispatcher dispatcher;

    /**
     * Обробник виключних ситуацій, під час відправлення та виконання
     * оброблюючого метода підписчика.
     *
     * @since 1.0.0
     */
    private final SubscriberExceptionHandler exceptionHandler;

    /**
     * Виконавець методу підписчика.
     *
     * @since 1.0.0
     */
    private final Executor executor;

    /**
     * Сховище підписчиків, містить у собі записи про підписчиків, включно з
     * метаданими вилученими із анотацій.
     *
     * @since 1.0.0
     */
    private final SubscriberRegistry subscribers;

    /**
     * Призначений, для ініціалізаці шини подій за змовчуванням.
     *
     * @since 1.0.0
     */
    public EventBus() {
        this(Dispatchers.newPerThreadQueuedDispatcher(),
                MoreExecutors.directExecutor(),
                DefaultSubscribeExceptionHandler.INSTANCE);
    }

    /**
     * Призначений, для ініціалізаці шини подій з задовільним відправником.
     *
     * @param dispatcher
     *            відправник подій
     * @since 1.0.0
     */
    public EventBus(@Nonnull final Dispatcher dispatcher) {
        this(dispatcher, MoreExecutors.directExecutor(),
                DefaultSubscribeExceptionHandler.INSTANCE);
    }

    /**
     * Призначений, для ініціалізаці шини подій з задовільним відправником та
     * виконавцем методів для обробки подій.
     *
     * @param dispatcher
     *            відправник подій
     * @param executor
     *            виконавець методу підписчика
     * @since 1.0.0
     */
    public EventBus(@Nonnull final Dispatcher dispatcher,
            @Nonnull final Executor executor) {
        this(dispatcher, executor, DefaultSubscribeExceptionHandler.INSTANCE);
    }

    /**
     * Призначений, для ініціалізаці шини подій з задовільним відправником та
     * виконавцем методів для обробки подій, а також обробником виключних
     * ситуацій під час обробки подій.
     *
     * @param dispatcher
     *            відправник подій
     * @param executor
     *            виконавець методу підписчика
     * @param exceptionHandler
     *            обробник виключних ситуацій
     * @since 1.0.0
     */
    public EventBus(@Nonnull final Dispatcher dispatcher,
            @Nonnull final Executor executor,
            @Nonnull final SubscriberExceptionHandler exceptionHandler) {
        this.executor = checkNotNull(executor);
        this.exceptionHandler = exceptionHandler;
        this.dispatcher = checkNotNull(dispatcher);
        subscribers = new SubscriberRegistry(this);
    }

    /**
     * Призначений, для відміни запиту на оптимізацію сховища підписчиків.
     *
     * @since 1.0.0
     */
    public void abortCleanup() {
        cleanupAvailable = false;
    }

    /**
     * Призначений, для перевірки чи присутній підписчик у поточному сховищі.
     *
     * @param subscriber
     *            підписчик, для перевірки
     * @return результат перевірки
     * @since 1.0.1
     */
    public boolean contains(final Object subscriber) {
        return subscribers.contains(subscriber);
    }

    /**
     * Призначений, для отримання відправника подій до підписчиків.
     *
     * @return відправник подій до підписчиків
     * @since 1.0.0
     */
    public @Nonnull Dispatcher getDispatcher() {
        return dispatcher;
    }

    /**
     * Призначений, для отримання виконавця методів підписчика.
     *
     * @return виконавець методів підписчика
     * @since 1.0.0
     */
    public @Nonnull Executor getExecutor() {
        return executor;
    }

    /**
     * Призначений, для обробки виключних ситуацій, які виникають під час
     * обробки події підписчиком.
     *
     * @param exception
     *            виключна ситуація
     * @param context
     *            контекст виклчної ситуації
     * @since 1.0.0
     */
    public void handleSubscriberException(@Nonnull final Throwable exception,
            @Nonnull final SubscriberExceptionContext context) {
        checkNotNull(exception);
        checkNotNull(context);
        try {
            exceptionHandler.handleException(exception, context);
        } catch (final Throwable handlerException) {

        }
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
    public void post(@Nonnull final Object event) {
        final Iterator<Subscriber> eventSubscribers = subscribers
                .getSubscribers(event);

        if (eventSubscribers.hasNext()) {
            dispatcher.dispatch(event, eventSubscribers);
        } else {
            if (!(event instanceof DeadEvent)) {
                post(new DeadEvent(this, event));
            }
        }

        if (cleanupAvailable) {
            subscribers.removeUnusedSubscribers();
            cleanupAvailable = false;
        }
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
    public void register(@Nonnull final Object subscriber) {
        subscribers.register(subscriber);
    }

    /**
     * Призначений, для запиту на оптимізацію сховища підписчиків.
     *
     * @since 1.0.0
     */
    public void requestCleanup() {
        cleanupAvailable = true;
    }

    /**
     * Призначений, для оновлення відправника подій.
     *
     * @param dispatcher
     *            оновлений відправник подій
     * @since 1.0.0
     */
    public void setDispatcher(@Nonnull final Dispatcher dispatcher) {
        this.dispatcher = checkNotNull(dispatcher);
    }

    /**
     * Призначений, для видалення підписчика із сховища записів про підписчиків.
     * Тобто відписки підписчика від подальших повідомлень.
     *
     * @param subscriber
     *            підписчик
     * @since 1.0.0
     */
    public void unregister(@Nonnull final Object subscriber) {
        subscribers.unregister(subscriber);
    }
}
