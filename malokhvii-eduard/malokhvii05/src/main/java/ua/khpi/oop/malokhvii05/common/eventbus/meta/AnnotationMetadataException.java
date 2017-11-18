package ua.khpi.oop.malokhvii05.common.eventbus.meta;

import javax.annotation.Nullable;

/**
 * Призначений, для оголошення загальної виключної ситуації для пакету
 * {@link ua.khpi.oop.malokhvii05.common.eventbus.meta}. Виникає під час не
 * коректного створення метаданих.
 * 
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 */
public final class AnnotationMetadataException extends RuntimeException {

    private static final long serialVersionUID = -6280618400535275347L;

    /**
     * Приначений, для створення нового виключення з {@code null} як його
     * детальне повідомлення.
     *
     * @since 1.0.0
     */
    public AnnotationMetadataException() {
        super();
    }

    /**
     * Призначений, для створення нового виключення з вказаним деталізованим
     * повідомленням.
     *
     * @param message
     *            деталізоване повідомлення
     * @since 1.0.0
     */
    public AnnotationMetadataException(@Nullable final String message) {
        super(message);
    }

    /**
     * Призначений, для створення нового виключення з вказаним детальним
     * повідомленням та причиною.
     *
     * @param message
     *            деталізоване повідомлення
     * @param cause
     *            причина виключної ситуації
     * @since 1.0.0
     */
    public AnnotationMetadataException(@Nullable final String message,
            @Nullable final Throwable cause) {
        super(message, cause);
    }

    /**
     * Призначений, для створення нового виключення з зазначеною причиною.
     *
     * @param cause
     *            причина виключної ситуації
     * @since 1.0.0
     */
    public AnnotationMetadataException(final Throwable cause) {
        super(cause);
    }
}
