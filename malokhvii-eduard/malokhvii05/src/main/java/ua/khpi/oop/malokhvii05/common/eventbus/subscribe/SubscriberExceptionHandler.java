package ua.khpi.oop.malokhvii05.common.eventbus.subscribe;

import javax.annotation.Nonnull;

/**
 * Інтерфейс, призначений для оголошення обробнику виключних ситуацій під час
 * відправлення події до підписчу.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 */
public interface SubscriberExceptionHandler {

    /**
     * Призначений, для обробки виключної ситуацій, яка виникла під час
     * відправлення події до підписчику.
     *
     * @param exception
     *            виключна ситуація
     * @param context
     *            контекст виключної ситуації, містить додаткові відомості для
     *            обробника
     * @since 1.0.0
     */
    void handleException(@Nonnull Throwable exception,
            @Nonnull SubscriberExceptionContext context);
}
