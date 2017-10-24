package ua.khpi.oop.malokhvii04.shell.commands;

import java.util.List;

/**
 * Реалізація, абстрактного декоратору команд ітерактивної оболонки.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.1.0
 * @since 1.0.0
 */
public abstract class AbstractCommandDecorator implements Command {

    /**
     * Посилання на обгорнуту команду.
     *
     * @since 1.0.0
     */
    protected Command command;

    /**
     * Призначений, для ініціалізації полів абстрактного класу, спадкоємцями.
     *
     * @param command
     *            команда для декорування
     * @since 1.0.0
     */
    public AbstractCommandDecorator(final Command command) {
        this.command = command;
    }

    /**
     * Призначений, для отримання посилання на обгорнуту команду.
     *
     * @return посиланняна на обгорнуту команду
     * @since 1.0.0
     */
    public final Command getCommand() {
        return this.command;
    }

    @Override
    public final String getDescription() {
        return this.command.getDescription();
    }

    @Override
    public final List<String> getKeys() {
        return this.command.getKeys();
    }

    @Override
    public final String getName() {
        return this.command.getName();
    }

    /**
     * Призначений, для оновлення посилання на обгонуту команду.
     *
     * @param command
     *            посиланняна на обгорнуту команду
     * @since 1.0.0
     */
    public final void setCommand(final Command command) {
        this.command = command;
    }
}
