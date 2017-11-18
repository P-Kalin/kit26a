package ua.khpi.oop.malokhvii05.common.eventbus.meta;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import ua.khpi.oop.malokhvii05.common.eventbus.annotations.Subscribe;

/**
 * Призначений, для забезпечення об'єктного представлення анотації
 * {@link ua.khpi.oop.malokhvii05.common.eventbus.annotations.Subscribe
 * Subscribe}. Для подальшого використанні у механізмі публікація-підписка.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 */
@Immutable
public final class SubscribeMetadata extends AnnotationMetadata {

    /**
     * Тип події, яку оброблює обробник.
     *
     * @since 1.0.0
     */
    private final Class<?> eventType;

    /**
     * Пріорітет обробник, тобто якщо вибрано пріорітетне оповіщення, тоді на
     * основі цього значення, буде упорядковано підписчиків.
     *
     * @since 1.0.0
     */
    private final int priority;

    /**
     * Призначений, для ініціалізації об'єкту початковими даними, а саме
     * анотацією
     * {@link ua.khpi.oop.malokhvii05.common.eventbus.annotations.Subscriber
     * Subscriber}, та типом події, для подальшої обробки.
     *
     * @param annotation
     *            анотація, з метаданими про обробника події
     * @param eventType
     *            тип події для обробки
     * @since 1.0.0
     */
    SubscribeMetadata(@Nonnull final Subscribe annotation,
            @Nonnull final Class<?> eventType) {
        super(annotation);
        priority = annotation.priority();
        this.eventType = checkNotNull(eventType);
    }

    /**
     * Призначений, для отримання типу події, яку оброблює обробник.
     *
     * @return тип події, яку оброблює обробник
     * @since 1.0.0
     */
    public @Nonnull Class<?> getEventType() {
        return eventType;
    }

    /**
     * Призначений, для отримання пріорітету обробника.
     *
     * @return пріорітет обробника
     * @since 1.0.0
     */
    public int getPriority() {
        return priority;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(priority);
    }

    @Override
    public @Nonnull String toString() {
        return MoreObjects.toStringHelper(this)
                .add("annotation", getAnnotationType().getSimpleName())
                .toString();
    }
}
