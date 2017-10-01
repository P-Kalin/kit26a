package ua.khpi.oop.malokhvii02.event;

/**
 * Призначений для отримання затраченого часу на певну подію, виступає у ролі
 * таймеру для циклу подій.
 *
 * @author malokhvii-ee
 * @version 1.0.0
 */
public final class MillisTimer {

    /**
     * Початкове значення часу.
     */
    private long beginTimeMillis;
    /**
     * Кінцеве значення часу.
     */
    private long endTimeMillis;

    /**
     * Призначений для ініціалізації полів часу, нулем.
     */
    public MillisTimer() {
        this.beginTimeMillis = 0;
        this.endTimeMillis = 0;
    }

    /**
     * Призначений для отримання затраченого часу.
     *
     * @return Затраччений час, різниця кінцевого та початкового значень
     */
    public long getRuntime() {
        return this.endTimeMillis - this.beginTimeMillis;
    }

    /**
     * Призначений для оновлення початкового значення часу.
     *
     * @return Початкове значення часу
     */
    public long launchTimer() {
        this.beginTimeMillis = System.currentTimeMillis();
        return this.beginTimeMillis;
    }

    /**
     * Призначений для оновлення кінцевого значення часу.
     *
     * @return Кінецеве значення часу.
     */
    public long terminateTimer() {
        this.endTimeMillis = System.currentTimeMillis();
        return this.endTimeMillis;
    }
}
