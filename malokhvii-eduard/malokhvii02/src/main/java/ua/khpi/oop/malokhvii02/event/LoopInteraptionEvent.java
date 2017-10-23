package ua.khpi.oop.malokhvii02.event;

import java.io.PrintStream;

import ua.khpi.oop.malokhvii02.data.DataContainer;

/**
 * Cлужбова подія, призначена для закінчення циклу подій. Делегує поведінку
 * об'єктів циклу подій, та контейнеру даних. Власноруч по закінченню програми
 * встановлює флаг припинення у об'єкті циклу подій.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 * @see EventLoop
 * @see DataContainer
 */
public class LoopInteraptionEvent implements Event {

    @Override
    public final void perform(final EventLoop eventLoop,
            final DataContainer dataContainer) {
        StreamHolder streamHolder = eventLoop.getStreamHolder();
        PrintStream out = streamHolder.getOut();

        out.printf("%s Computation time %d ms\n",
                streamHolder.getCurrentOutLabel(), eventLoop.getRuntime());
        out.printf("%s Tear down data container: %s\n",
                streamHolder.getCurrentOutLabel(),
                dataContainer.getContainerName());

        eventLoop.breakLoop();
    }

}
