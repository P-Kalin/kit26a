package ua.khpi.oop.malokhvii02.event;

import java.io.PrintStream;
import java.util.Scanner;

import ua.khpi.oop.malokhvii02.data.DataContainer;

/**
 * Цикл подій, призначений для обробки подій. Реалізований на основі кінечного
 * автомату подій. Події власноруч оновлюють стан циклу, цикл лише делегує їх
 * звернення до контейнеру подій та обгортки потоків введення/виведення.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 * @see Event
 * @see DataContainer
 * @see MillisTimer
 * @see StreamHolder
 */
public final class EventLoop {

    /**
     * Стан циклу подій, тобто чи активний цикл.
     *
     * @since 1.0.0
     */
    private boolean isRunning;

    /**
     * Контейнер вхідних даних.
     *
     * @since 1.0.0
     */
    private final DataContainer dataContainer;

    /**
     * Контейнер подій.
     *
     * @since 1.0.0
     */
    private final EventsContainer eventsContainer;

    /**
     * Поточна подія.
     *
     * @since 1.0.0
     */
    private Event currentEvent;

    /**
     * Обгортка над потоками введення/виведення.
     *
     * @since 1.0.0
     */
    private final StreamHolder stream;

    /**
     * Таймер, для визначення затраченого часу на цикл подій.
     *
     * @since 1.0.0
     */
    private final MillisTimer timer;

    /**
     * Призначений для ініціалізації циклу подій, контейнером даних, контейнером
     * подій.
     *
     * @param dataContainer
     *            Контейнер даних
     * @param eventsContainer
     *            Контейнер подій
     * @param outputStream
     *            Поток виведення
     * @param inputScanner
     *            Поток введення
     * @since 1.0.0
     */
    public EventLoop(final DataContainer dataContainer,
            final EventsContainer eventsContainer,
            final PrintStream outputStream, final Scanner inputScanner) {
        this.stream = new StreamHolder(outputStream, inputScanner);
        this.timer = new MillisTimer();

        this.dataContainer = dataContainer;
        this.eventsContainer = eventsContainer;
        this.currentEvent = null;
    }

    /**
     * Призначений для припинення циклу подій.
     *
     * @since 1.0.0
     */
    public void breakLoop() {
        this.isRunning = false;
    }

    /**
     * Призначений для зачинення потоку введення в обгортці.
     *
     * @since 1.0.0
     */
    public void closeStream() {
        this.stream.closeIn();
    }

    /**
     * Призначений для встановлення у ролі поточної подіії - подію отримання
     * вхідних даних.
     *
     * @since 1.0.0
     */
    public void collectDataContainer() {
        this.currentEvent = this.eventsContainer.getEvent(DataEvent.COLLECTION);
    }

    /**
     * Призначений для встановлення у ролі поточної події - подію обчислення
     * вхідних даних.
     *
     * @since 1.0.0
     */
    public void computeDataContainer() {
        this.currentEvent = this.eventsContainer
                .getEvent(DataEvent.COMPUTATION);
    }

    /**
     * Призначений для отимання часу затраченого на виконання циклу.
     *
     * @return Час затрачений на виконання циклу
     * @since 1.0.0
     */
    public long getRuntime() {
        return this.timer.getRuntime();
    }

    /**
     * Призначений для отримання посилання на обгортку до потоків введення та
     * виведення.
     *
     * @return Обгортка потоків введення та виведення
     * @since 1.0.0
     */
    public StreamHolder getStreamHolder() {
        return this.stream;
    }

    /**
     * Призначений для обробки поточної події в черзі подій.
     *
     * @return Стан виконання.
     * @since 1.0.0
     */
    public boolean handleCurrentEvent() {
        if (this.currentEvent != null) {
            this.currentEvent.perform(this, this.dataContainer);
            return true;
        }
        return false;
    }

    /**
     * Призначений для отримання поточного стану циклу подій.
     *
     * @return Поточний стан циклу подій.
     * @since 1.0.0
     */
    public boolean isRunning() {
        return this.isRunning;
    }

    /**
     * Призначений для встановлення у ролі поточної події - службову подію
     * початку циклу. Встановлює істину у флаг стану циклу.
     *
     * @since 1.0.0
     */
    public void launchLoop() {
        this.timer.launchTimer();
        this.isRunning = true;
        this.currentEvent = this.eventsContainer.getEvent(LoopEvent.INCEPTION);
    }

    /**
     * Призначений для встановлення у ролі поточної подіїї - службову подію
     * продовження циклу.
     *
     * @since 1.0.0
     */
    public void restoreLoop() {
        this.currentEvent = this.eventsContainer
                .getEvent(LoopEvent.CONTINUATION);
    }

    /**
     * Призначений для встановлення у ролі поточної події - службової подіїї
     * завершення циклю подій.
     *
     * @since 1.0.0
     */
    public void terminateLoop() {
        this.currentEvent = this.eventsContainer
                .getEvent(LoopEvent.INTERAPTION);
        this.timer.terminateTimer();
    }

    /**
     * Призначений для надання загального інтерфейсу до оновлення поточної
     * події.
     *
     * @param event
     *            Нова поточна подія
     * @since 1.0.0
     */
    public void updateCurrentEvent(final Event event) {
        this.currentEvent = event;
    }

    /**
     * Призначений для встановлення у ролі поточної подіїї - подію відображення
     * обчислених даних.
     *
     * @since 1.0.0
     */
    public void visualizeDataContainer() {
        this.currentEvent = this.eventsContainer
                .getEvent(DataEvent.VISUALIZATION);
    }
}
