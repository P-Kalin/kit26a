package ua.khpi.oop.malokhvii04.shell.command;

/**
 * Базовий інтерфейс, команди інтерактивної оболонки.
 *
 * @author malokhvii-eduard
 * @version 1.0.0
 * @see Shell
 * @since 1.0.0
 */
public interface Command {

    /**
     * Призначений, для виконання тіла команди.
     *
     * @since 1.0.0
     */
    void execute();

    /**
     * Призначений, для отримання назви команди.
     *
     * @return назва команди
     * @since 1.0.0
     */
    String getName();

    /**
     * Призначений, для отримання унікального індентифікатора команди.
     *
     * @return унікальний індентифікатор команди
     * @since 1.0.0
     */
    String getId();
}
