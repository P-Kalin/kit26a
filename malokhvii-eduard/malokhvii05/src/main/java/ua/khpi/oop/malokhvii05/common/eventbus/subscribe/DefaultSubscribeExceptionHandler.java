package ua.khpi.oop.malokhvii05.common.eventbus.subscribe;

/**
 * Призначений, для обробки виключних ситуацій під час надсилання повії до
 * підписчика, або виконання методу для обробки події. Використовується за
 * змовчуванням, тобто нічого не робить.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 */
public enum DefaultSubscribeExceptionHandler
        implements SubscriberExceptionHandler {

    /**
     * Єдиний екземпляр обробника виключних ситуацій за змовчуванням.
     *
     * @since 1.0.0
     */
    INSTANCE;

    @Override
    public void handleException(Throwable exception,
            SubscriberExceptionContext context) {

    }
}
