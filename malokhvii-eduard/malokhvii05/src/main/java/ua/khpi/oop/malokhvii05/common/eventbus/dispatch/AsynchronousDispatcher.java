package ua.khpi.oop.malokhvii05.common.eventbus.dispatch;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.annotation.Nonnull;

import ua.khpi.oop.malokhvii05.common.eventbus.subscribe.Subscriber;

/**
 * Призначений, для оповіщення подій почерзі, події розміщуються в одній черзі.
 * Ця поведінка використовується для асинхронної відправки, для інших випадків
 * необхідно використовувати {@link ImmediateDispatcher}.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 */
public final class AsynchronousDispatcher implements Dispatcher {

    /**
     * Глобальна черга подій.
     *
     * @since 1.0.0
     */
    private final ConcurrentLinkedQueue<EventWithSubscriber> queue;

    /**
     * Призначений, для ініціалізації за-змовчуванням.
     *
     * @since 1.0.0
     */
    AsynchronousDispatcher() {
        queue = new ConcurrentLinkedQueue<>();
    }

    @Override
    public void dispatch(@Nonnull final Object event,
            @Nonnull final Iterator<Subscriber> subscribers) {
        checkNotNull(event);
        checkNotNull(subscribers);

        while (subscribers.hasNext()) {
            queue.add(new EventWithSubscriber(event, subscribers.next()));
        }

        EventWithSubscriber eventWithSubscriber;
        while ((eventWithSubscriber = queue.poll()) != null) {
            eventWithSubscriber.subscriber
                    .dispatchEvent(eventWithSubscriber.event);
        }
    }

    /**
     * Призначений, для збереження подій у черзі.
     *
     * @author malokhvii-eduard (malokhvii.ee@gmail.com)
     * @version 1.0.0
     */
    private static final class EventWithSubscriber {

        /**
         * Подія для оповіщення.
         *
         * @since 1.0.0
         */
        private final Object event;

        /**
         * Підписчик для оповіщення.
         *
         * @since 1.0.0
         */
        private final Subscriber subscriber;

        /**
         * Призначений, для ініціалізації запису черги.
         *
         * @param event
         *            подія
         * @param subscriber
         *            підписчик для оповіщення
         * @since 1.0.0
         */
        private EventWithSubscriber(@Nonnull final Object event,
                @Nonnull final Subscriber subscriber) {
            this.event = event;
            this.subscriber = subscriber;
        }
    }
}
