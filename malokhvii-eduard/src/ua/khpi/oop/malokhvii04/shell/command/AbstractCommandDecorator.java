package ua.khpi.oop.malokhvii04.shell.command;

/**
 * Реалізація, абстрактного декоратору команд ітерактивної оболонки.
 *
 * @author malokhvii-ee
 * @version 1.0.0
 */
public abstract class AbstractCommandDecorator implements Command {

    /**
     * Посилання на обгорнуту команду.
     */
    private Command command;

    /**
     * Призначений, для ініціалізації полів абстрактного класу, спадкоємцями.
     *
     * @param command
     *            команда для декорування
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
     */
    public Command getCommand() {
        return command;
    }

    /**
     * Призначений, для оновлення посилання на обгонуту команду.
     *
     * @param command
     *            посиланняна на обгорнуту команду
     */
    public void setCommand(final Command command) {
        this.command = command;
    }
}
