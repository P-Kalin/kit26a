package ua.khpi.oop.malokhvii05.common.eventbus.meta;

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.annotation.Annotation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Абстрактний клас, призначений для оголошення загальної поведінки усіх
 * об'єетків метаданих.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 */
public abstract class AnnotationMetadata {

    /**
     * Тип, анотації для якої зберігаються метадані.
     *
     * @since 1.0.0
     */
    private final Class<?> annotationType;

    /**
     * Призначений, для ініціалізації об'єкту анотацією, для збереження
     * загальної інформації.
     *
     * @param annotation
     *            анотації для якої зберігаються метадані
     * @since 1.0.0
     */
    protected AnnotationMetadata(@Nonnull final Annotation annotation) {
        checkNotNull(annotation);
        annotationType = annotation.annotationType();
    }

    @Override
    public final boolean equals(@Nullable final Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        return hashCode() == object.hashCode();
    }

    /**
     * Призначений, для отримання типу анотації, для якої зберігаються метадані.
     *
     * @return тип, анотації для якої зберігаються метадані
     * @since 1.0.0
     */
    public final @Nonnull Class<?> getAnnotationType() {
        return annotationType;
    }
}
