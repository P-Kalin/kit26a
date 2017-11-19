package ua.khpi.oop.malokhvii05.common.eventbus.dispatch;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Comparator;
import java.util.Iterator;

import javax.annotation.Nonnull;

import com.google.common.collect.TreeMultiset;

import ua.khpi.oop.malokhvii05.common.eventbus.subscribe.Subscriber;

/**
 * Призначений, для надання базовому механізму оповіщення виконавців, можливості
 * оповіщувати з урахуванням пріорітету оповіщення.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 */
public final class PrioritizedDispatcher extends AbstractDispatcherDecorator {

    /**
     * Призначений, для оголошення компараторів, для упорядкування виконавців.
     *
     * @author malokhvii-eduard (malokhvii.ee@gmail.com)
     * @version 1.0.0
     */
    public static enum Order implements Comparator<Subscriber> {

        /**
         * Упорядкування виконавців, від меньшого приорітету до більшого
         * пріорітету.
         *
         * @since 1.0.0
         */
        ASCENDING {

            @Override
            public int compare(@Nonnull final Subscriber subscriber1,
                    @Nonnull final Subscriber subscriber2) {
                checkNotNull(subscriber1);
                checkNotNull(subscriber2);

                return Integer.compare(
                        subscriber1.getSubscribeMetadata().getPriority(),
                        subscriber2.getSubscribeMetadata().getPriority());
            }
        },
        /**
         * Упорядкування виконавців, від більшого приорітету до меньшого
         * пріорітету.
         *
         * @since 1.0.0
         */
        DESCENDING {

            @Override
            public int compare(@Nonnull final Subscriber subscriber1,
                    @Nonnull final Subscriber subscriber2) {
                checkNotNull(subscriber1);
                checkNotNull(subscriber2);

                return -Integer.compare(
                        subscriber1.getSubscribeMetadata().getPriority(),
                        subscriber2.getSubscribeMetadata().getPriority());
            }
        };
    }

    /**
     * Компаратор, для визначення порядку постановлення підписчиків за
     * пріорітетом.
     *
     * @since 1.0.0
     */
    private Comparator<Subscriber> comparator;

    /**
     * Призначений, для ініціалізації декоратору, об'єктом для оповіщення
     * виконавців, та компаратором.
     *
     * @param dispatcher
     *            об'єкт, для оповіщення виконавців, реалізацію якого необхідно
     *            розширити
     * @param comparator
     *            компаратор, для визначення порядку постановлення підписчиків
     *            за пріорітетом
     * @since 1.0.0
     */
    PrioritizedDispatcher(@Nonnull final Dispatcher dispatcher,
            @Nonnull final Comparator<Subscriber> comparator) {
        super(dispatcher);
        this.comparator = checkNotNull(comparator);
    }

    @Override
    public void dispatch(@Nonnull final Object event,
            @Nonnull final Iterator<Subscriber> subscribers) {
        checkNotNull(event);
        checkNotNull(subscribers);

        TreeMultiset<Subscriber> subscribersSortedByPriority = TreeMultiset
                .create(comparator);
        while (subscribers.hasNext()) {
            subscribersSortedByPriority.add(subscribers.next());
        }
        getDispatcher().dispatch(event, subscribersSortedByPriority.iterator());
    }

    /**
     * Призначений, для отримання компаратору для порівняння записів про
     * підписчиків.
     *
     * @return комапаратор для порівняння записів про підписчиків
     * @since 1.0.0
     */
    public @Nonnull Comparator<Subscriber> getComparator() {
        return comparator;
    }

    /**
     * Призначений, для оновлення компаратору для порівняння записів про
     * підписчика.
     *
     * @param comparator
     *            комапаратор для порівняння записів про підписчиків
     * @since 1.0.0
     */
    public void setComparator(
            @Nonnull final Comparator<Subscriber> comparator) {
        this.comparator = checkNotNull(comparator);
    }
}
