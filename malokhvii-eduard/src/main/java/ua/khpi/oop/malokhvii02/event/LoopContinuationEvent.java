package ua.khpi.oop.malokhvii02.event;

import java.io.PrintStream;
import java.util.Scanner;

import ua.khpi.oop.malokhvii02.data.DataContainer;

/**
 * Cлужбова подія, призначена для виконання операції продовження циклу подій.
 * Делегує поведінку об'єктів циклю подій, та контейнеру даних. Власноруч по
 * закінченню програми оновлює наступну подію у циклі подій на службову подію
 * припинення циклу подій, або на подію отримання вхідних даних залежно від
 * обраного варіанту.
 *
 * @author malokhvii-eduard
 * @version 1.0.0
 * @see EventLoop
 * @see DataContainer
 * @since 1.0.0
 */
public class LoopContinuationEvent implements Event {

    /**
     * Символ підтвердження продовження циклу подій ({@value}).
     * 
     * @since 1.0.0
     */
    private static final String CONTINUE_LOOP_CHARACTER = "Y";

    @Override
    public final void perform(final EventLoop eventLoop,
            final DataContainer dataContainer) {
        StreamHolder streamHolder = eventLoop.getStreamHolder();
        PrintStream out = streamHolder.getOut();
        Scanner in = streamHolder.getIn();

        out.printf(
                "\n%s Are you want to repeat data container "
                        + "computation (Y/N): ",
                streamHolder.getCurrentOutLabel());
        String repeatReply = in.next();
        out.println();

        if (repeatReply.equals(CONTINUE_LOOP_CHARACTER)) {
            eventLoop.collectDataContainer();
        } else {
            eventLoop.terminateLoop();
        }
    }
}
