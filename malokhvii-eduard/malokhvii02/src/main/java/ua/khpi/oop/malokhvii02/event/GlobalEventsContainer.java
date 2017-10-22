package ua.khpi.oop.malokhvii02.event;

import java.util.HashMap;
import java.util.Map;

/**
 * Глобальний контейнер подій, призначений для ініціалізації під час виклику, та
 * збереження стану контейнеру до закінчення програми. У ролі основного сховища
 * подій використовує хеш-таблицю.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 * @see EventsContainer
 */
public final class GlobalEventsContainer implements EventsContainer {

    /**
     * Призначений для ініціалізації загального об'єкту контейнера подій.
     *
     * @author malokhvii-eduard (malokhvii.ee@gmail.com)
     *         (malokhvii.ee@gmail.com)
     * @version 1.0.0
     */
    private static class SingletonHolder {

        /**
         * Загальний об'єкт контейнеру подій.
         *
         * @since 1.0.0
         */
        private static final GlobalEventsContainer instance = new GlobalEventsContainer();
    }

    /**
     * Призначений для отримання посилання на єдиний об'єкт контейнеру подій.
     *
     * @return Посилання на єдиний екземпляр контейнеру подій
     * @since 1.0.0
     */
    public static GlobalEventsContainer getInstance() {
        return SingletonHolder.instance;
    }

    /**
     * Сховище подій.
     *
     * @since 1.0.0
     */
    private final Map<EventType, Event> events;

    /**
     * Призначений для заповнення сховища подій, загальними подіями для
     * подальшого використання у циклі подій.
     *
     * @since 1.0.0
     */
    private GlobalEventsContainer() {
        this.events = new HashMap<EventType, Event>();

        this.events.put(LoopEvent.INCEPTION, new LoopInceptionEvent());
        this.events.put(LoopEvent.CONTINUATION, new LoopContinuationEvent());
        this.events.put(LoopEvent.INTERAPTION, new LoopInteraptionEvent());

        this.events.put(DataEvent.COLLECTION, new DataCollectionEvent());
        this.events.put(DataEvent.COMPUTATION, new DataComputationEvent());
        this.events.put(DataEvent.VISUALIZATION, new DataVisualizationEvent());
    }

    @Override
    public Event getEvent(final EventType item) {
        return this.events.getOrDefault(item, null);
    }

    @Override
    public void putEvent(final EventType item, final Event event) {
        this.events.put(item, event);
    }
}
