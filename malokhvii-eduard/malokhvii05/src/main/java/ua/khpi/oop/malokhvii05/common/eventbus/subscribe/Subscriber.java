package ua.khpi.oop.malokhvii05.common.eventbus.subscribe;

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.google.common.base.MoreObjects;
import com.google.errorprone.annotations.ForOverride;

import ua.khpi.oop.malokhvii05.common.eventbus.meta.SubscribeMetadata;
import ua.khpi.oop.malokhvii05.common.eventbus.meta.SubscriberMetadata;
import ua.khpi.oop.malokhvii05.common.eventbus.publish.EventBus;

/**
 * Призначений, для реалізації абстрактного запису про підписчика. А саме
 * збереження підписчика (об'єкту) а також виконавця, який повинен
 * використовуватися для диспетчеризації події до нього.
 * <p>
 * Два абонента еквівалентні, коли вони відносяться до одного і того ж методу на
 * одному і тому ж об'єкті (не клас) Ця властивість використовується для
 * забезпечення того, щоб не було зареєстровано жодного методу для
 * диспетчеризації більше одного разу.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 */
@Immutable
public abstract class Subscriber implements Comparable<Subscriber> {

    /**
     * Посилання на об'єкт, який відповідний за оповіщення усіх підписчиків.
     *
     * @since 1.0.0
     */
    private final WeakReference<EventBus> eventBus;

    /**
     * Виконавець, який використовуватиметься для відправлення подій для цього
     * підписчика.
     *
     * @since 1.0.0
     */
    private final Executor executor;

    /**
     * Метод підписчика, для обробки події.
     *
     * @sicne 1.0.0
     */
    private final Method method;

    /**
     * Об'єкте представлення вилучених даних із анотації {@link Subscribe}. Якою
     * був помічений метод, для обробки події.
     *
     * @since 1.0.0
     */
    private final SubscribeMetadata subscribeMetadata;

    /**
     * Об'єкте представлення вилучених даних із анотації {@link Subscriber}.
     * Якою був помічений підписчик.
     *
     * @since 1.0.0
     */
    private final SubscriberMetadata subscriberMetadata;

    /**
     * Призначений, для ініціалізації запису про підписчика необхідними даними,
     * для подальшого оповіщення та обробки.
     *
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
    protected Subscriber(@Nonnull final Method method,
            @Nonnull final EventBus eventBus,
            @Nonnull final SubscriberMetadata subscriberMetadata,
            @Nonnull final SubscribeMetadata subscribeMetadata) {
        this.eventBus = new WeakReference<EventBus>(checkNotNull(eventBus));
        this.method = checkNotNull(method);

        this.subscribeMetadata = checkNotNull(subscribeMetadata);
        this.subscriberMetadata = checkNotNull(subscriberMetadata);

        method.setAccessible(true);
        executor = eventBus.getExecutor();
    }

    /**
     * Відправляє подію підписчику за допомогою належного виконавця (методу).
     *
     * @param event
     *            подія, для подальшої відправки до виконавця (методу)
     * @see #invokeSubscriberMethod
     * @since 1.0.0
     */
    public final void dispatchEvent(@Nonnull final Object event) {
        executor.execute(() -> {
            try {
                invokeSubscriberMethod(event);
            } catch (final InvocationTargetException exception) {
                eventBus.get().handleSubscriberException(exception.getCause(),
                        createExceptionContext(event));
            }
        });
    }

    @Override
    public final boolean equals(@Nullable final Object object) {
        if (object == null) {
            return false;
        }

        if (object instanceof Subscriber) {
            final Subscriber subscriber = (Subscriber) object;
            return getSubscriber() == subscriber.getSubscriber()
                    && method.equals(subscriber.method);
        }
        return false;
    }

    /**
     * Призначений, для отримання об'єктного представлення вилучених даних із
     * анотації {@link Subscribe} про виконавця події (методу).
     *
     * @return об'єкте представлення вилучених даних із анотації
     *         {@link Subscribe}
     * @since 1.0.0
     */
    public @Nonnull SubscribeMetadata getSubscribeMetadata() {
        return subscribeMetadata;
    }

    /**
     * Призначений, для отримання об'єктного представлення вилучених даних із
     * анотації {@link Subscriber} про підписчика.
     *
     * @return об'єкте представлення вилучених даних із анотації
     *         {@link Subscriber}
     * @since 1.0.0
     */
    public @Nonnull SubscriberMetadata getSubscriberMetadata() {
        return subscriberMetadata;
    }

    @Override
    public final int hashCode() {
        return (31 + method.hashCode()) * 31
                + System.identityHashCode(getSubscriber());
    }

    @Override
    public @Nonnull String toString() {
        return MoreObjects.toStringHelper(this)
                .add("type",
                        subscriberMetadata.getSubscriberType().getSimpleName())
                .add("method", method.getName()).toString();
    }

    /**
     * Призначений, для отримання контексту для поточної виключної ситуації під
     * час оповіщення виконавця (методу).
     *
     * @param event
     *            подія
     * @return контекст виключної ситуації
     * @since 1.0.0
     */
    private @Nonnull SubscriberExceptionContext createExceptionContext(
            @Nonnull final Object event) {
        return new SubscriberExceptionContext(eventBus.get(), event,
                getSubscriber(), method);
    }

    /**
     * Призначений, для отримання підписчика.
     *
     * @return підписчик
     * @since 1.0.0
     */
    @ForOverride
    public abstract @Nonnull Object getSubscriber();

    /**
     * Призначений, для виклику виконавця (методу), для обробки події. Якщо,
     * посилання на підписчика дорівнює {@code null} тоді, виконується запит на
     * очистку сховища підписчиків, для подальшого вилучення не дійсних
     * підписчиків.
     *
     * @param event
     *            подія
     * @throws InvocationTargetException
     *             помилка під час виклику виконавця
     * @since 1.0.0
     */
    private final void invokeSubscriberMethod(@Nonnull final Object event)
            throws InvocationTargetException {
        try {
            final Object target = getSubscriber();
            if (target != null) {
                method.invoke(target, checkNotNull(event));
            } else {
                eventBus.get().requestCleanup();
            }
        } catch (final IllegalArgumentException exception) {

        } catch (final IllegalAccessException exception) {

        } catch (final InvocationTargetException exception) {
            if (exception.getCause() instanceof Error) {
                throw (Error) exception.getCause();
            }
            throw exception;
        }
    }

    @Override
    public int compareTo(@Nonnull Subscriber subscriber) {
        return Integer.compare(subscribeMetadata.getPriority(),
                subscriber.subscribeMetadata.getPriority());
    }
}
