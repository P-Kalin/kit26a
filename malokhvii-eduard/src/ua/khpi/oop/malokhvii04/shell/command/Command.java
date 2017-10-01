package ua.khpi.oop.malokhvii04.shell.command;

/**
 * Базовий інтерфейс, команди інтерактивної оболонки.
 *
 * @author malokhvii-ee
 * @version 1.0.0
 * @see Shell
 */
public interface Command {

    /**
     * Призначений, для виконання тіла команди.
     */
    void execute();

    /**
     * Призначений, для отримання назви команди.
     *
     * @return назва команди
     */
    String getName();

    /**
     * Призначений, для отримання унікального індентифікатора команди.
     *
     * @return унікальний індентифікатор команди
     */
    String getId();
}
