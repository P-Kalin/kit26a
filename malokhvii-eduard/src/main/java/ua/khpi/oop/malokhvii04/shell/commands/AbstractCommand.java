package ua.khpi.oop.malokhvii04.shell.commands;

import java.util.Observer;

/**
 * Реалізація, абстрактної команди інтерактивної оболонки.
 *
 * @author malokhvii-eduard
 * @version 1.1.0
 * @see Command
 * @since 1.0.0
 */
public abstract class AbstractCommand implements Command, Observer {

    @Override
    public final String getName() {
        return getClass().getSimpleName();
    }
}
