package ua.khpi.oop.malokhvii05.common.eventbus.meta;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import ua.khpi.oop.malokhvii05.common.eventbus.annotations.Subscriber;
import ua.khpi.oop.malokhvii05.common.eventbus.subscribe.SubscriberReferencePolicy;
import ua.khpi.oop.malokhvii05.common.eventbus.subscribe.SubscriberRegistry;

/**
 * Призначений, для забезпечення об'єктного представлення анотації
 * {@link Subscriber}. Для подальшого використанні у механізмі
 * публікація-підписка.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 */
@Immutable
public final class SubscriberMetadata extends AnnotationMetadata {

    /**
     * Тип підписчика.
     *
     * @since 1.0.0
     */
    private final Class<?> subscriberType;

    /**
     * Політика посилання на підписчика у {@link SubscriberRegistry}.
     *
     * @since 1.0.0
     */
    private final SubscriberReferencePolicy referencePolicy;

    /**
     * Призначений, для ініціалізації об'єкту початковими даними, а саме
     * анотацією {@link Subscriber}, та типом підписчика, для подальшої обробки.
     *
     * @param annotation
     *            анотація, з метаданими про обробника події
     * @param subscriberType
     *            тип підписчика
     * @since 1.0.0
     */
    SubscriberMetadata(@Nonnull final Subscriber annotation,
            @Nonnull final Class<?> subscriberType) {
        super(annotation);
        this.subscriberType = checkNotNull(subscriberType);
        referencePolicy = annotation.referencePolicy();
    }

    /**
     * Призначений, для отримання політики посилання на об'єкт підписчика.
     *
     * @return політика посилання на об'єкт підписчика
     * @since 1.0.0
     */
    public @Nonnull SubscriberReferencePolicy getReferencePolicy() {
        return referencePolicy;
    }

    /**
     * Призначений, для отримання типу підписчика.
     *
     * @return тип підписчика
     * @since 1.0.0
     */
    public @Nonnull Class<?> getSubscriberType() {
        return subscriberType;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(subscriberType, referencePolicy);
    }

    @Override
    public @Nonnull String toString() {
        return MoreObjects.toStringHelper(this)
                .add("annotation", getAnnotationType().getName())
                .add("subscriber", subscriberType.getName())
                .add("referencePolicy", referencePolicy).toString();
    }
}
