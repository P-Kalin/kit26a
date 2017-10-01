package ua.khpi.oop.malokhvii02.data;

import java.io.PrintStream;
import java.util.Scanner;

import ua.khpi.oop.malokhvii02.event.DataCollectionEvent;
import ua.khpi.oop.malokhvii02.event.DataComputationEvent;
import ua.khpi.oop.malokhvii02.event.DataVisualizationEvent;
import ua.khpi.oop.malokhvii02.event.LoopInceptionEvent;
import ua.khpi.oop.malokhvii02.event.LoopInteraptionEvent;

/**
 * Загальний інтерфейс контейнеру даних, призначений для збереження у собі усіх
 * необхідних даних для обчислення певної операції. Використовується під час
 * обробки подій програми.
 *
 * @author malokhvii-ee
 * @version 1.0.0
 * @see EventLoop
 */
public interface DataContainer {

    /**
     * Призначений, для отримання назви контейнеру даних. Викликається під час
     * подій {@link LoopInceptionEvent}, {@link LoopInteraptionEvent}.
     *
     * @return Назва контейнеру даних
     */
    String getContainerName();

    /**
     * Призначений для отримання детального описа конейнеру даних, його
     * призначення, тощо. Викликається під час події {@link LoopInceptionEvent}.
     * Повинен лише повертати детальний опий контейнеру.
     *
     * @return Детальний опис контейнеру даних
     */
    String prepareDataDescription();

    /**
     * Призначений для отримання результатів обробки даних. Викликається під час
     * події {@link DataVisualizationEvent}. Повинен лише описувати стан
     * обчислення та його результат.
     *
     * @return Результат обробки даних.
     */
    String prepareDataVisualization();

    /**
     * Призначений для отримання вхідних даних від користувача. Викликається під
     * час події {@link DataCollectionEvent}. Повинен лише отримувати та
     * розташовувати вхідні дані у контейнері.
     *
     * @param out
     *            Поток виведення інформації
     * @param in
     *            Поток введення інформації
     * @return Статус завершення операції
     */
    DataCollectionStatus collectData(PrintStream out, Scanner in);

    /**
     * Призначений для обчислення отриманих вхідних даних. Викликається під час
     * події {@link DataComputationEvent}. Повинен лише обчислювати отримані
     * дані.
     *
     * @return Статус завершення операції
     */
    DataComputationStatus computeData();
}
