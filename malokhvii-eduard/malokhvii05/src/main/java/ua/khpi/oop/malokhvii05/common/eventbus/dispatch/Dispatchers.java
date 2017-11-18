package ua.khpi.oop.malokhvii05.common.eventbus.dispatch;

import java.util.Comparator;

import javax.annotation.Nonnull;

import ua.khpi.oop.malokhvii05.common.eventbus.subscribe.Subscriber;

/**
 * Призначений, для створення об'єктів оповіщення виконавців.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 */
public final class Dispatchers {

    /**
     * Призначений, для заборони створення об'єктів.
     *
     * @since 1.0.0
     */
    private Dispatchers() {

    }

    /**
     * Призначений, для створення асинхроного оповіщувача.
     *
     * @return асинхроний оповіщувач
     * @since 1.0.0
     */
    public static @Nonnull Dispatcher newAsynchronousDispatcher() {
        return new AsynchronousDispatcher();
    }

    /**
     * Призначений, для створення послідовного оповіщувача.
     *
     * @return послідовний оповіщувач
     * @since 1.0.0
     */
    public static @Nonnull Dispatcher newImmediateDispatcher() {
        return ImmediateDispatcher.INSTANCE;
    }

    /**
     * Призначений, для оповіщення всіх підписчиків, гарантуючи що події
     * опубліковані в одному потоці, будуть відправлені всім підписчикам у
     * порядку їх розміщення.
     *
     * @return послідовний оповіщувач
     * @since 1.0.0
     */
    public static @Nonnull Dispatcher newPerThreadQueuedDispatcher() {
        return new PerThreadQueuedDispatcher();
    }

    /**
     * Призначений, для розширення механізму оповіщення, за допомогою
     * впорядкування переліку підписчиків за пріорітетом.
     *
     * @param dispatcher
     *            підписчик
     * @param comparator
     *            компаратор для порівняння підписчиків
     *            ({@link PrioritizedDispatcher.Order})
     * @return оповіщувач
     */
    public static @Nonnull Dispatcher newPrioritizedDispatcher(
            @Nonnull final Dispatcher dispatcher,
            @Nonnull final Comparator<Subscriber> comparator) {
        return new PrioritizedDispatcher(dispatcher, comparator);
    }
}
