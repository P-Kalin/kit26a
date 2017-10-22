package ua.khpi.oop.malokhvii04.shell;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import ua.khpi.oop.malokhvii04.shell.commands.AbstractCommand;
import ua.khpi.oop.malokhvii04.shell.commands.Command;

/**
 * Призначений, для збереження об'єктів команд для інтерактивної оболонки.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 */
public final class ShellCommandsPool {

    /**
     * Пул, усіх створених команд.
     *
     * @since 1.0.0
     */
    private static HashMap<String, AbstractCommand> commands;

    static {
        ShellCommandsPool.commands = new HashMap<String, AbstractCommand>();
    }

    /**
     * Призначений, для отримання об'єкту команди за її ключем. Якщо команду не
     * знайдено тоді повертає null.
     *
     * @param key
     *            ключ необхідної команди
     * @return об'єкт команди, згідно запиту
     * @since 1.0.0
     */
    public static Command getCommand(final String key) {
        return ShellCommandsPool.commands.get(key);
    }

    /**
     * Призначений, для отримання переліку описів усіх команд у пулі.
     *
     * @return опис усіх команд у пулі
     * @since 1.0.0
     */
    public static Collection<String> getCommandsDescriptions() {
        final TreeMap<String, String> commandsDescription = new TreeMap<String, String>();
        for (final Command command : ShellCommandsPool.commands.values()) {
            commandsDescription.put(command.getName(),
                    command.getDescription());
        }
        return commandsDescription.values();
    }

    /**
     * Призначений, для отримання переліку усіх ключів команд у пулі.
     *
     * @return усі ключі команд у пулі
     * @since 1.0.0
     */
    public static Collection<String> getCommandsKeys() {
        final TreeMap<String, String> commandsKeys = new TreeMap<String, String>();
        for (final Command command : ShellCommandsPool.commands.values()) {
            commandsKeys.put(command.getName(),
                    String.join(" ", command.getKeys()));
        }
        return commandsKeys.values();
    }

    /**
     * Призначений, для регістрації нової команди у пулі. Під час регістрації
     * створюється новий об'єкт зарегестрованної команди, та додається як
     * спостерігач за колекцією локалізацій.
     *
     * @param keys
     *            ключи для виклику команди
     * @param commandClass
     *            клас нової команди
     * @return результат регестрації
     * @since 1.0.0
     */
    public static boolean registerCommand(final List<String> keys,
            final Class<? extends AbstractCommand> commandClass) {
        AbstractCommand command = null;
        try {
            command = commandClass.newInstance();
            ShellResources.getInstance().addObserver(command);
        } catch (InstantiationException | IllegalAccessException e) {
            return false;
        }

        for (final String key : keys) {
            ShellCommandsPool.commands.put(key, command);
        }

        return true;
    }

    /**
     * Приватний конструктор, для заборони створення об'єктів пулу команд.
     *
     * @since 1.0.0
     */
    private ShellCommandsPool() {

    }
}
