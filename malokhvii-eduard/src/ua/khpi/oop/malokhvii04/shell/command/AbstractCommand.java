package ua.khpi.oop.malokhvii04.shell.command;

import ua.khpi.oop.malokhvii04.shell.ShellData;

/**
 * Реалізація, абстрактної команди інтерактивної оболонки.
 *
 * @author malokhvii-ee
 * @version 1.0.0
 * @see Command
 */
public abstract class AbstractCommand implements Command {

    /**
     * Унікальний індентифікатор команди.
     */
    private String id;

    /**
     * Посилання на об'єкт даних інтерактивної оболонки.
     */
    private ShellData shellData;

    /**
     * Призначений, для ініціалізації полів абстрактного класу, спадкоємцями.
     *
     * @param id
     *            унікальний індентифікатор команди
     * @param shellData
     *            посилання на об'єкт даних інтерактивної оболонки
     */
    public AbstractCommand(final String id, final ShellData shellData) {
        this.id = id;
        this.shellData = shellData;
    }

    @Override
    public final String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public final String getId() {
        return this.id;
    }

    /**
     * Призначений, для отримання посилання на об'єкт даних інтерактивної
     * оболонки.
     *
     * @return посилання на об'єкт даних інтерактивної оболонки
     */
    public ShellData getShellData() {
        return shellData;
    }

    /**
     * Призначений, для оновлення посилання на об'єкт даних інтерактивної
     * оболонки.
     *
     * @param shellData
     *            посилання на об'єкт даних інтерактивної оболонки
     */
    public void setShellData(final ShellData shellData) {
        this.shellData = shellData;
    }

    /**
     * Призначений, для оновлення унікального індентифікатора команди.
     *
     * @param id
     *            унікальний індентифікатор команди
     */
    public void setId(final String id) {
        this.id = id;
    }
}
