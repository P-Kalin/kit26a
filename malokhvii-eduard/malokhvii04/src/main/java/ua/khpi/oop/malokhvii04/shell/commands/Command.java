package ua.khpi.oop.malokhvii04.shell.commands;

import java.util.List;

/**
 * Базовий інтерфейс, команди інтерактивної оболонки.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.1.0
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
     * Призначений, для отримання опису команди.
     *
     * @return опис команди
     * @since 1.1.0
     */
    String getDescription();

    /**
     * Призначений, для отримання переліку ключів команди в інтерактивній
     * оболонці.
     *
     * @return перелік команд в інтерактивній оболонці
     * @since 1.1.0
     */
    List<String> getKeys();

    /**
     * Призначений, для отримання назви команди.
     *
     * @return назва команди
     * @since 1.0.0
     */
    String getName();
}
