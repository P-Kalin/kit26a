package ua.khpi.oop.malokhvii05.common.eventbus.dispatch;

import java.util.Iterator;

import javax.annotation.Nonnull;

import ua.khpi.oop.malokhvii05.common.eventbus.subscribe.Subscriber;

/**
 * Інтерфейс, призначений для оголошення механізму оповіщення виконавців,
 * подією.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 */
public interface Dispatcher {

    /**
     * Призначений, для оповіщення виконавців подією.
     *
     * @param event
     *            подіє
     * @param subscribers
     *            перелік виконавців, у вигляді ітератора
     * @since 1.0.0
     */
    void dispatch(@Nonnull Object event,
            @Nonnull Iterator<Subscriber> subscribers);
}
