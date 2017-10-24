package ua.khpi.oop.malokhvii02.event;

import java.io.PrintStream;

import ua.khpi.oop.malokhvii02.data.DataComputationStatus;
import ua.khpi.oop.malokhvii02.data.DataContainer;

/**
 * Подія, призначена для виконання операції отримання вхідних даних для
 * контейнеру даних. Делегує поведінку об'єктів циклю подій, та контейнеру
 * даних. Власноруч по закінченню програми оновлює наступну подію у циклі подій
 * на подію обробки даних.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 * @see EventLoop
 * @see DataContainer
 */
public class DataComputationEvent implements Event {

    @Override
    public final void perform(final EventLoop eventLoop,
            final DataContainer dataContainer) {
        StreamHolder streamHolder = eventLoop.getStreamHolder();
        PrintStream out = streamHolder.getOut();

        out.printf("\n%s Starting data computation...\n",
                streamHolder.getCurrentOutLabel());
        DataComputationStatus dataComputationStatus = dataContainer
                .computeData();

        if (dataComputationStatus == DataComputationStatus.SUCCESS) {
            eventLoop.visualizeDataContainer();
            out.printf("%s Finishing data computation...\n",
                    streamHolder.getCurrentOutLabel());
        } else {
            eventLoop.terminateLoop();
            out.printf("%s Terminate data computation...\n",
                    streamHolder.getCurrentOutLabel());
        }
    }
}
