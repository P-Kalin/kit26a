package ua.khpi.oop.malokhvii02.event;

/**
 * Призначений для отримання затраченого часу на певну подію, виступає у ролі
 * таймеру для циклу подій.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 */
public final class MillisTimer {

    /**
     * Початкове значення часу.
     *
     * @since 1.0.0
     */
    private long beginTimeMillis;
    /**
     * Кінцеве значення часу.
     *
     * @since 1.0.0
     */
    private long endTimeMillis;

    /**
     * Призначений для ініціалізації полів часу, нулем.
     *
     * @since 1.0.0
     */
    public MillisTimer() {
        this.beginTimeMillis = 0;
        this.endTimeMillis = 0;
    }

    /**
     * Призначений для отримання затраченого часу.
     *
     * @return Затраччений час, різниця кінцевого та початкового значень
     * @since 1.0.0
     */
    public long getRuntime() {
        return this.endTimeMillis - this.beginTimeMillis;
    }

    /**
     * Призначений для оновлення початкового значення часу.
     *
     * @return Початкове значення часу
     * @since 1.0.0
     */
    public long launchTimer() {
        this.beginTimeMillis = System.currentTimeMillis();
        return this.beginTimeMillis;
    }

    /**
     * Призначений для оновлення кінцевого значення часу.
     *
     * @return Кінецеве значення часу
     * @since 1.0.0
     */
    public long terminateTimer() {
        this.endTimeMillis = System.currentTimeMillis();
        return this.endTimeMillis;
    }
}
