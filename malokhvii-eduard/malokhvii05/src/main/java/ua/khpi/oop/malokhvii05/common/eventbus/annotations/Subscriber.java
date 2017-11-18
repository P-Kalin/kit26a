package ua.khpi.oop.malokhvii05.common.eventbus.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import ua.khpi.oop.malokhvii05.common.eventbus.publish.EventBus;
import ua.khpi.oop.malokhvii05.common.eventbus.publish.EventBusException;
import ua.khpi.oop.malokhvii05.common.eventbus.subscribe.SubscriberReferencePolicy;
import ua.khpi.oop.malokhvii05.common.eventbus.subscribe.SubscriberRegistry;

/**
 * Анотація призначена, для відмічення типу об'єкту, як підписчика для
 * подальшого оповіщення. Використовується для визначення загальних налаштувань
 * для окремого типу підписчиків. Якщо анотація не застосовується до типу, тоді
 * підчас регестрації підписчика до об'єкту {@link EventBus}, буде отримано
 * виключну ситуацію {@link EventBusException}.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 */
@Inherited
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = { ElementType.TYPE })
@Documented
public @interface Subscriber {

    /**
     * Властивість анотації, призначена для визначення політики посилання на
     * підписчика у об'єкті {@link SubscriberRegistry}. Впливає на термін
     * протягом, якого об'єкт буде присутній у сховищі підписчиків для
     * подальшого оповіщення.
     *
     * @return політика посилання, за змовчуванням
     *         {@link SubscriberReferencePolicy#STRONG}.
     * @since 1.0.0
     */
    SubscriberReferencePolicy referencePolicy() default SubscriberReferencePolicy.STRONG;
}
