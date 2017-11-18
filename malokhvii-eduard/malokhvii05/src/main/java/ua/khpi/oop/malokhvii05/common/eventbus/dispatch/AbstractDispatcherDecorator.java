package ua.khpi.oop.malokhvii05.common.eventbus.dispatch;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nonnull;

/**
 * Призначений, для розширення базового механізму оповіщення виконавців подією.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 */
public abstract class AbstractDispatcherDecorator implements Dispatcher {

    /**
     * Об'єкт, для оповіщення виконавців, реалізацію якого необхідно розширити.
     *
     * @since 1.0.0
     */
    private Dispatcher dispatcher;

    /**
     * Призначений, для ініціалізації декоратору, об'єктом для оповіщення
     * виконавців.
     *
     * @param dispatcher
     *            об'єкт, для оповіщення виконавців, реалізацію якого необхідно
     *            розширити
     * @since 1.0.0
     */
    protected AbstractDispatcherDecorator(
            @Nonnull final Dispatcher dispatcher) {
        this.dispatcher = checkNotNull(dispatcher);
    }

    /**
     * Призначений, для отримання обгорнутого об'єкту оповіщення.
     *
     * @return обгорнутий об'єкт оповіщення
     * @since 1.0.0
     */
    public @Nonnull final Dispatcher getDispatcher() {
        return dispatcher;
    }

    /**
     * Призначений, для оновлення обгорнутого об'єкту оповіщення.
     *
     * @param dispatcher
     *            оновленний обгорнутий об'єкт оповіщення
     * @since 1.0.0
     */
    public void setDispatcher(@Nonnull Dispatcher dispatcher) {
        this.dispatcher = checkNotNull(dispatcher);
    }
}
