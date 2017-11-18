package ua.khpi.oop.malokhvii05.common.eventbus.dispatch;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Iterator;
import java.util.Queue;

import javax.annotation.Nonnull;

import com.google.common.collect.Queues;

import ua.khpi.oop.malokhvii05.common.eventbus.subscribe.Subscriber;

/**
 * Призначений, для оповіщення усіх підписчиків однієї події А для виклику перед
 * будь-якими підписчиками на будь-які події В і С, які публікуються на
 * {@link EventBus шину} подій підписчика А.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 */
public final class PerThreadQueuedDispatcher implements Dispatcher {

    /**
     * Поточна черга подій для відправлення.
     *
     * @since 1.0.0
     */
    private final ThreadLocal<Queue<Event>> queue;

    /**
     * Статус відправлення для кожного потоку, що використовується, щоб уникнути
     * повторної події.
     *
     * @since 1.0.0
     */
    private final ThreadLocal<Boolean> dispatching;

    /**
     * Призначений, для ініціалізації за-змовчуванням.
     *
     * @since 1.0.0
     */
    PerThreadQueuedDispatcher() {
        dispatching = new ThreadLocal<Boolean>() {
            @Override
            protected Boolean initialValue() {
                return false;
            }
        };

        queue = new ThreadLocal<Queue<Event>>() {
            @Override
            protected Queue<Event> initialValue() {
                return Queues.newArrayDeque();
            }
        };
    }

    @Override
    public void dispatch(@Nonnull final Object event,
            @Nonnull final Iterator<Subscriber> subscribers) {
        checkNotNull(event);
        checkNotNull(subscribers);

        final Queue<Event> queueForThread = queue.get();
        queueForThread.offer(new Event(event, subscribers));

        if (!dispatching.get()) {
            dispatching.set(true);
            try {
                Event nextEvent;
                while ((nextEvent = queueForThread.poll()) != null) {
                    while (nextEvent.subscribers.hasNext()) {
                        nextEvent.subscribers.next()
                                .dispatchEvent(nextEvent.event);
                    }
                }
            } finally {
                dispatching.remove();
                queue.remove();
            }
        }
    }

    /**
     * Призначений, для збереження подій у черзі.
     *
     * @author malokhvii-eduard (malokhvii.ee@gmail.com)
     * @version 1.0.0
     */
    private static final class Event {

        /**
         * Подія для оповіщення.
         *
         * @since 1.0.0
         */
        private final Object event;

        /**
         * Перелік підписчиків, для оповіщення.
         *
         * @since 1.0.0
         */
        private final Iterator<Subscriber> subscribers;

        /**
         * Призначений, для ініціалізації запису черги.
         *
         * @param event
         *            подія
         * @param subscribers
         *            перелік підписчиків
         * @since 1.0.0
         */
        private Event(@Nonnull final Object event,
                @Nonnull final Iterator<Subscriber> subscribers) {
            this.event = event;
            this.subscribers = subscribers;
        }
    }
}
