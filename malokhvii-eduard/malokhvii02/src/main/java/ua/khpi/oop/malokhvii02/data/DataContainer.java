package ua.khpi.oop.malokhvii02.data;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Загальний інтерфейс контейнеру даних, призначений для збереження у собі усіх
 * необхідних даних для обчислення певної операції. Використовується під час
 * обробки подій програми.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 */
public interface DataContainer {

    /**
     * Призначений для отримання вхідних даних від користувача. Викликається під
     * час події. Повинен лише отримувати та розташовувати вхідні дані у
     * контейнері.
     *
     * @param out
     *            Поток виведення інформації
     * @param in
     *            Поток введення інформації
     * @return Статус завершення операції
     * @since 1.0.0
     */
    DataCollectionStatus collectData(PrintStream out, Scanner in);

    /**
     * Призначений для обчислення отриманих вхідних даних. Викликається під час
     * події DataComputationEvent. Повинен лише обчислювати отримані дані.
     *
     * @return Статус завершення операції
     * @since 1.0.0
     */
    DataComputationStatus computeData();

    /**
     * Призначений, для отримання назви контейнеру даних. Викликається під час
     * подій LoopInceptionEvent, LoopInteraptionEvent.
     *
     * @return Назва контейнеру даних
     * @since 1.0.0
     */
    String getContainerName();

    /**
     * Призначений для отримання детального описа конейнеру даних, його
     * призначення, тощо. Викликається під час події LoopInceptionEvent. Повинен
     * лише повертати детальний опий контейнеру.
     *
     * @return Детальний опис контейнеру даних
     * @since 1.0.0
     */
    String prepareDataDescription();

    /**
     * Призначений для отримання результатів обробки даних. Викликається під час
     * події DataVisualizationEvent. Повинен лише описувати стан обчислення та
     * його результат.
     *
     * @return Результат обробки даних.
     * @since 1.0.0
     */
    String prepareDataVisualization();
}
