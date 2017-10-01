package ua.khpi.oop.malokhvii02.event;

/**
 * Перелік можливих служюових подій для контролювання стану циклу подій,
 * використовується у якості ключа для збереження подій
 * {@link LoopInceptionEvent}, {@link LoopInteraptionEvent},
 * {@link LoopContinuationEvent}.
 *
 * @author malokhvii-ee
 * @version 1.0.0
 * @see EventType
 */
public enum LoopEvent implements EventType {
    /**
     * Ключ, події для початку виконання циклу подій.
     */
    INCEPTION,
    /**
     * Ключ, події для обрання наступного стану циклу подій, тобто або
     * продовжувати обчислення або перейти до події закінчення циклу.
     */
    CONTINUATION,
    /**
     * Ключ, події для закінчення циклу подій.
     */
    INTERAPTION
}
