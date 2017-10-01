package ua.khpi.oop.malokhvii02.event;

import java.io.PrintStream;
import java.util.Scanner;

import ua.khpi.oop.malokhvii02.data.DataContainer;

/**
 * Цикл подій, призначений для обробки подій. Реалізований на основі кінечного
 * автомату подій. Події власноруч оновлюють стан циклу, цикл лише делегує їх
 * звернення до контейнеру подій та обгортки потоків введення/виведення.
 *
 * @author malokhvii-ee
 * @version 1.0.0
 * @see Event
 * @see DataContainer
 * @see MillisTimer
 * @see StreamHolder
 */
public final class EventLoop {

    /**
     * Стан циклу подій, тобто чи активний цикл.
     */
    private boolean isRunning;

    /**
     * Контейнер вхідних даних.
     */
    private DataContainer dataContainer;

    /**
     * Контейнер подій.
     */
    private EventsContainer eventsContainer;

    /**
     * Поточна подія.
     */
    private Event currentEvent;

    /**
     * Обгортка над потоками введення/виведення.
     */
    private StreamHolder stream;

    /**
     * Таймер, для визначення затраченого часу на цикл подій.
     */
    private MillisTimer timer;

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
     * Призначений для зачинення потоку введення в обгортці.
     */
    public void closeStream() {
        this.stream.closeIn();
    }

    /**
     * Призначений для отримання посилання на обгортку до потоків введення та
     * виведення.
     *
     * @return Обгортка потоків введення та виведення
     */
    public StreamHolder getStreamHolder() {
        return this.stream;
    }

    /**
     * Призначений для отимання часу затраченого на виконання циклу.
     *
     * @return Час затрачений на виконання циклу
     */
    public long getRuntime() {
        return this.timer.getRuntime();
    }

    /**
     * Призначений для отримання поточного стану циклу подій.
     *
     * @return Поточний стан циклу подій.
     */
    public boolean isRunning() {
        return this.isRunning;
    }

    /**
     * Призначений для припинення циклу подій.
     */
    public void breakLoop() {
        this.isRunning = false;
    }

    /**
     * Призначений для встановлення у ролі поточної події - службової подіїї
     * завершення циклю подій.
     */
    public void terminateLoop() {
        this.currentEvent = this.eventsContainer
                .getEvent(LoopEvent.INTERAPTION);
        this.timer.terminateTimer();
    }

    /**
     * Призначений для встановлення у ролі поточної подіїї - службову подію
     * продовження циклу.
     */
    public void restoreLoop() {
        this.currentEvent = this.eventsContainer
                .getEvent(LoopEvent.CONTINUATION);
    }

    /**
     * Призначений для встановлення у ролі поточної події - службову подію
     * початку циклу. Встановлює істину у флаг стану циклу.
     */
    public void launchLoop() {
        this.timer.launchTimer();
        this.isRunning = true;
        this.currentEvent = this.eventsContainer.getEvent(LoopEvent.INCEPTION);
    }

    /**
     * Призначений для встановлення у ролі поточної події - подію обчислення
     * вхідних даних.
     */
    public void computeDataContainer() {
        this.currentEvent = this.eventsContainer
                .getEvent(DataEvent.COMPUTATION);
    }

    /**
     * Призначений для встановлення у ролі поточної подіїї - подію відображення
     * обчислених даних.
     */
    public void visualizeDataContainer() {
        this.currentEvent = this.eventsContainer
                .getEvent(DataEvent.VISUALIZATION);
    }

    /**
     * Призначений для встановлення у ролі поточної подіії - подію отримання
     * вхідних даних.
     */
    public void collectDataContainer() {
        this.currentEvent = this.eventsContainer.getEvent(DataEvent.COLLECTION);
    }

    /**
     * Призначений для надання загального інтерфейсу до оновлення поточної
     * події.
     *
     * @param event
     *            Нова поточна подія
     */
    public void updateCurrentEvent(final Event event) {
        this.currentEvent = event;
    }

    /**
     * Призначений для обробки поточної події в черзі подій.
     *
     * @return Стан виконання.
     */
    public boolean handleCurrentEvent() {
        if (this.currentEvent != null) {
            this.currentEvent.perform(this, this.dataContainer);
            return true;
        }
        return false;
    }
}
