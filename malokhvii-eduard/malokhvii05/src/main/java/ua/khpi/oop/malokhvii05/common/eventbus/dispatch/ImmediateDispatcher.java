package ua.khpi.oop.malokhvii05.common.eventbus.dispatch;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Iterator;

import javax.annotation.Nonnull;

import ua.khpi.oop.malokhvii05.common.eventbus.subscribe.Subscriber;

/**
 * Призначений, для оповіщення виконавців негайно, коли вони опубліковані без
 * використання проміжної черги для зміни порядку відправлення. Відправлення
 * відбувається без жодних сторонніх ефектів.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @since 1.0.0
 */
public final class ImmediateDispatcher implements Dispatcher {

    /**
     * Єдиний, об'єкт оповіщення виконавців.
     *
     * @since 1.0.0
     */
    static final ImmediateDispatcher INSTANCE = new ImmediateDispatcher();

    /**
     * Призначений, для заборони створення об'єктів.
     *
     * @since 1.0.0
     */
    private ImmediateDispatcher() {

    }

    @Override
    public void dispatch(@Nonnull final Object event,
            @Nonnull final Iterator<Subscriber> subscribers) {
        checkNotNull(event);
        while (subscribers.hasNext()) {
            subscribers.next().dispatchEvent(event);
        }
    }
}
