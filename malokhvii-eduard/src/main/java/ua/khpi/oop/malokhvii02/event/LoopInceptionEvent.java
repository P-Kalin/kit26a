package ua.khpi.oop.malokhvii02.event;

import java.io.PrintStream;

import ua.khpi.oop.malokhvii02.data.DataContainer;

/**
 * Cлужбова подія, призначена для розпочатку циклу подій. Делегує поведінку
 * об'єктів циклу подій, та контейнеру даних. Власноруч по закінченню програми
 * оновлює наступну подію у циклі подій на подію отримання вхідних даних.
 *
 * @author malokhvii-eduard
 * @version 1.0.0
 * @see EventLoop
 * @see DataContainer
 * @since 1.0.0
 */
public class LoopInceptionEvent implements Event {

    @Override
    public final void perform(final EventLoop eventLoop,
            final DataContainer dataContainer) {
        String dataContainerDescription = dataContainer
                .prepareDataDescription();

        StreamHolder streamHolder = eventLoop.getStreamHolder();
        PrintStream out = streamHolder.getOut();

        out.printf("%s Set up data container: %s\n",
                streamHolder.getCurrentOutLabel(),
                dataContainer.getContainerName());
        out.printf("%s %s\n\n", streamHolder.getCurrentOutLabel(),
                dataContainerDescription);

        eventLoop.collectDataContainer();
    }
}
