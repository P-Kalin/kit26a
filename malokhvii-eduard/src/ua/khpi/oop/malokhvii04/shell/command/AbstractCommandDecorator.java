package ua.khpi.oop.malokhvii04.shell.command;

/**
 * Реалізація, абстрактного декоратору команд ітерактивної оболонки.
 *
 * @author malokhvii-eduard
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class AbstractCommandDecorator implements Command {

    /**
     * Посилання на обгорнуту команду.
     *
     * @since 1.0.0
     */
    private Command command;

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

    @Override
    public final String getName() {
        return this.command.getName();
    }

    @Override
    public final String getId() {
        return this.command.getId();
    }

    /**
     * Призначений, для отримання посилання на обгорнуту команду.
     *
     * @return посиланняна на обгорнуту команду
     * @since 1.0.0
     */
    public Command getCommand() {
        return command;
    }

    /**
     * Призначений, для оновлення посилання на обгонуту команду.
     *
     * @param command
     *            посиланняна на обгорнуту команду
     * @since 1.0.0
     */
    public void setCommand(final Command command) {
        this.command = command;
    }
}
