package ua.khpi.oop.malokhvii05.common.eventbus.meta;

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.Method;

import javax.annotation.Nonnull;

import ua.khpi.oop.malokhvii05.common.eventbus.annotations.Subscribe;
import ua.khpi.oop.malokhvii05.common.eventbus.annotations.Subscriber;

/**
 * Призначений, для вилучення метаданих із отриманих об'єктів, та збереження у
 * їх об'єктному відображенні.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 */
public final class AnnotationMetadatas {

    /**
     * Призначений, для вилучення метаданих із методу (обробника подій).
     *
     * @param method
     *            метод (обробник подій)
     * @return об'єктне відображення, анотації {@link Subscribe}
     * @throws AnnotationMetadataException
     *             отриманий метод не помічен анотацією {@link Subscribe}
     * @since 1.0.0
     */
    public static @Nonnull SubscribeMetadata newSubscribeMetadata(
            @Nonnull final Method method) throws AnnotationMetadataException {
        checkNotNull(method);
        final Subscribe subscribeAnnotation = method
                .getAnnotation(Subscribe.class);
        if (subscribeAnnotation != null) {
            return new SubscribeMetadata(subscribeAnnotation,
                    method.getParameterTypes()[0]);
        }
        throw new AnnotationMetadataException(
                "Method is not marked with annotation Subscribe");
    }

    /**
     * Призначений, для вилучення метаданих із типу (підписчика).
     *
     * @param subscriberType
     *            тип (підписчик)
     * @return об'єктне відображення, анотації {@link Subscriber}
     * @throws AnnotationMetadataException
     *             отриманий тип не помічен анотацією {@link Subscriber}
     * @since 1.0.0
     */
    public static @Nonnull SubscriberMetadata newSubscriberMetadata(
            @Nonnull final Class<?> subscriberType)
            throws AnnotationMetadataException {
        checkNotNull(subscriberType);
        final Subscriber subscriberAnnotation = subscriberType
                .getAnnotation(Subscriber.class);
        if (subscriberAnnotation != null) {
            return new SubscriberMetadata(subscriberAnnotation, subscriberType);
        }
        throw new AnnotationMetadataException(
                "Type is not marked with annotation Subscriber");
    }

    /**
     * Призначений, для заборони створення об'єктів.
     *
     * @since 1.0.0
     */
    private AnnotationMetadatas() {

    }
}
