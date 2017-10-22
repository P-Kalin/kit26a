package ua.khpi.oop.malokhvii02.event;

import java.io.PrintStream;

import ua.khpi.oop.malokhvii02.data.DataContainer;

/**
 * Подія, призначена для виконання операції відображення обчислених даних із
 * контейнеру даних. Делегує поведінку об'єктів циклю подій, та контейнеру
 * даних. Власноруч по закінченню програми оновлює наступну подію у циклі подій
 * на службову подію продовження циклу подій.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 * @see EventLoop
 * @see DataContainer
 */
public class DataVisualizationEvent implements Event {

    @Override
    public final void perform(final EventLoop eventLoop,
            final DataContainer dataContainer) {
        StreamHolder streamHolder = eventLoop.getStreamHolder();
        PrintStream out = streamHolder.getOut();

        out.printf("\n%s Starting data visualization...\n",
                streamHolder.getCurrentOutLabel());
        String dataVisualization = dataContainer.prepareDataVisualization();
        out.println(dataVisualization);
        out.printf("%s Finishing data visualization...\n",
                streamHolder.getCurrentOutLabel());

        eventLoop.restoreLoop();
    }
}
