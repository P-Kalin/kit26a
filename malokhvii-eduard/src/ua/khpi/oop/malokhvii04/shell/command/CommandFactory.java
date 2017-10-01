package ua.khpi.oop.malokhvii04.shell.command;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import ua.khpi.oop.malokhvii04.shell.ShellData;

/**
 * Призначений, для створення об'єктів команд, за отриманним ключем команди.
 *
 * @author malokhvii-ee
 * @version 1.0.0
 */
public final class CommandFactory {

    /**
     * Колекція, класів команд.
     */
    private static HashMap<String, Class<? extends AbstractCommand>> classMapping;

    /**
     * Пакет із базовими командовми, для інтерактивної оболонки.
     */
    private static final String BASIC_COMMAND_PACKAGE = "ua.khpi.oop."
            + "malokhvii04.shell.command";

    static {
        CommandFactory.classMapping = new HashMap<String, Class<? extends AbstractCommand>>();
        try {

            List<String> basicCommands = Arrays.asList("InputCommand",
                    "OutputCommand", "ProcessCommand", "HistoryCommand",
                    "HelpCommand", "ExitCommand", "DebugCommand",
                    "NoDebugCommand");

            for (String command : basicCommands) {
                Class.forName(String.format("%s.%s",
                        CommandFactory.BASIC_COMMAND_PACKAGE, command));
            }
        } catch (ClassNotFoundException exception) {

        }
    }

    /**
     * Приватний конструктор, для заборони створення утилітарного класу.
     */
    private CommandFactory() {

    }

    /**
     * Призначений, для створення об'єкту команди згідно отриманного ключа.
     *
     * @param key
     *            ключ команди
     * @param shellData
     *            посилання на об'єкт даних інтерактивної оболонки
     * @return створенний об'єкт команди
     */
    public static Command getCommand(final String key,
            final ShellData shellData) {
        Class<? extends AbstractCommand> commandClass = CommandFactory.classMapping
                .get(key);
        if (commandClass != null) {
            try {
                Constructor<? extends AbstractCommand> commandConstructor = commandClass
                        .getConstructor(String.class, ShellData.class);
                return commandConstructor
                        .newInstance(UUID.randomUUID().toString(), shellData);
            } catch (Exception exception) {
                return null;
            }
        }
        return null;
    }

    /**
     * Призначений, для регістрації нового класу команд.
     *
     * @param keys
     *            список ключів для команди
     * @param commandClass
     *            клас команди
     */
    public static void registerCommand(final List<String> keys,
            final Class<? extends AbstractCommand> commandClass) {
        for (String name : keys) {
            CommandFactory.classMapping.put(name, commandClass);
        }
    }

    /**
     * Призначений, для регістрації нового класу команд.
     *
     * @param key
     *            ключ для команди
     * @param commandClass
     *            клас команди
     */
    public static void registerCommand(final String key,
            final Class<? extends AbstractCommand> commandClass) {
        CommandFactory.classMapping.put(key, commandClass);
    }
}
