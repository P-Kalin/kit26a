package ua.khpi.oop.malokhvii05.common.eventbus.publish;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.MoreObjects;

/**
 * Призначений, для збереження важливої інформації під час виникнення не
 * передбачених ситуацій, під час відправлення події до підписчика.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 */
public final class DeadEvent {

    /**
     * Об'єкт який надіслав подію.
     *
     * @since 1.0.0
     */
    private final Object eventBus;

    /**
     * Подія, під час якої виникла не передбачена ситуація.
     *
     * @since 1.0.0
     */
    private final Object event;

    /**
     * Призначений, для ініціалізації фатальної події.
     *
     * @param eventBus
     *            об'єкт який надіслав подію
     * @param event
     *            подія, під час якої виникла не передбачена ситуація
     * @since 1.0.0
     */
    public DeadEvent(Object eventBus, Object event) {
        this.eventBus = checkNotNull(eventBus);
        this.event = checkNotNull(event);
    }

    /**
     * Призначений, для отримання об'єкту який відправив подію.
     *
     * @return об'єкт який відправив подію
     * @since 1.0.0
     */
    public Object getEventBus() {
        return eventBus;
    }

    /**
     * Призначений, для отримання події яку не було надіслано до підписчиків.
     *
     * @return подія, яку не було надіслано до підписчиків
     * @since 1.0.0
     */
    public Object getEvent() {
        return event;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("source", eventBus)
                .add("event", event).toString();
    }
}
