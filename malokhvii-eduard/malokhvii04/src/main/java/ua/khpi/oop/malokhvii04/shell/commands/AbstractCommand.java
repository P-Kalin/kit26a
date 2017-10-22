package ua.khpi.oop.malokhvii04.shell.commands;

import java.util.Observer;

/**
 * Реалізація, абстрактної команди інтерактивної оболонки.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.1.0
 * @see Command
 */
public abstract class AbstractCommand implements Command, Observer {

    @Override
    public final String getName() {
        return this.getClass().getSimpleName();
    }
}
