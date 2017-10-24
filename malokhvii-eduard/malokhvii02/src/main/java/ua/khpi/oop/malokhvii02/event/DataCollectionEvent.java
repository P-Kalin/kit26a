package ua.khpi.oop.malokhvii02.event;

import java.io.PrintStream;

import ua.khpi.oop.malokhvii02.data.DataCollectionStatus;
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
public class DataCollectionEvent implements Event {

    @Override
    public final void perform(final EventLoop eventLoop,
            final DataContainer dataContainer) {
        StreamHolder streamHolder = eventLoop.getStreamHolder();
        PrintStream out = streamHolder.getOut();

        DataCollectionStatus dataCollectionStatus;
        out.printf("%s Starting data collection...\n",
                streamHolder.getCurrentOutLabel());

        dataCollectionLoop: while (true) {
            dataCollectionStatus = dataContainer
                    .collectData(streamHolder.getOut(), streamHolder.getIn());

            switch (dataCollectionStatus) {
            case CONTINUE:
                eventLoop.computeDataContainer();
                out.printf("%s Finishing data collection...\n",
                        streamHolder.getCurrentOutLabel());
                break dataCollectionLoop;
            case FINISH:
                eventLoop.terminateLoop();
                break dataCollectionLoop;
            case REPEAT:
                out.printf("%s Repeating data collection\n",
                        streamHolder.getCurrentOutLabel());
            default:
                break;
            }
        }
    }
}
