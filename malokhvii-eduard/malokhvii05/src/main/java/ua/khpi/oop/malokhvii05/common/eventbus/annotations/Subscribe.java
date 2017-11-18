package ua.khpi.oop.malokhvii05.common.eventbus.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Comparator;

import ua.khpi.oop.malokhvii05.common.eventbus.dispatch.Dispatcher;
import ua.khpi.oop.malokhvii05.common.eventbus.dispatch.Dispatchers;
import ua.khpi.oop.malokhvii05.common.eventbus.dispatch.PrioritizedDispatcher;
import ua.khpi.oop.malokhvii05.common.eventbus.publish.EventBus;
import ua.khpi.oop.malokhvii05.common.eventbus.publish.EventBusException;

/**
 * Анотація призначена, для відмічення усіх методів, які здатні обробити подію.
 * Тип події буде позначений першим (і тільки) параметром методу. Якщо анотація
 * застосовується до методів з відсутнім списком параметрів або більш ніж одним
 * параметром, тоді під час регестрації підписчика до об'єкту {@link EventBus},
 * буде отримано виключну ситуацію {@link EventBusException}.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 */
@Inherited
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = { ElementType.METHOD })
@Documented
public @interface Subscribe {

    /**
     * Властивість анотації, призначена для визначення пріорітету оповіщення
     * обробника події. Впливає на порядок оповіщення, якщо у конструкторі
     * об'єкту {@link EventBus} використовувати наступний відправник подій
     * {@link PrioritizedDispatcher},
     * {@link Dispatchers#newPrioritizedDispatcher(Dispatcher, Comparator)}
     *
     * @return пріорітет оповіщення, за змовчуванням 0
     * @since 1.0.0
     */
    int priority() default 0;
}
