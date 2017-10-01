package ua.khpi.oop.malokhvii04.shell;

import java.util.ArrayList;
import java.util.List;

import ua.khpi.oop.malokhvii04.shell.command.Command;
import ua.khpi.oop.malokhvii04.shell.command.CommandFactory;

/**
 * Призначений, для обробки аргументів командного рядку.
 *
 * @author malokhvii-ee
 * @version 1.0.0
 */
public final class CommandLineParser {

    /**
     * Розмір історії команд за змовчуванням.
     */
    private static int defaultHistorySize = 10;

    /**
     * Символ табуляції за змовчуванням.
     */
    private static String defaultTabCharacter = "...";

    /**
     * Символ команди за змовчуванням.
     */
    private static String defaultCommandCharacter = ">>>";

    /**
     * Приватний конструктор, для заборони створення утилітарного класу.
     */
    private CommandLineParser() {

    }

    /**
     * Призначений, для обробки аргументів командної строки.
     *
     * @param args
     *            аргументи командної строки
     * @param shellData
     *            посилання на об'єкт даних інтерактивної оболонки
     * @return масив команд
     */
    public static List<Command> parseCommands(final String[] args,
            final ShellData shellData) {
        List<Command> commands = new ArrayList<Command>();
        for (String arg : args) {
            Command command = CommandFactory.getCommand(arg, shellData);
            if (command != null) {
                commands.add(command);
            }
        }

        return commands;
    }

    /**
     * Призначений, для обробки розміру історії команд.
     *
     * @param args
     *            аргументи командної строки
     * @return розмір історії команд
     */
    public static int parseCommandHistorySize(final String[] args) {
        for (String arg : args) {
            if (arg.matches("(-hs=[0-9]+)")) {
                arg = arg.replace("-hs=", "");
                CommandLineParser.setDefaultHistorySize(Integer.parseInt(arg));
                break;
            } else if (arg.matches("(-history-size=[0-9]+)")) {
                arg = arg.replace("-history-size=", "");
                CommandLineParser.setDefaultHistorySize(Integer.parseInt(arg));
                break;
            }
        }
        return CommandLineParser.getDefaultHistorySize();
    }

    /**
     * Призначений, для отримання символу таблуяції.
     *
     * @param args
     *            аргументи командної строки
     * @return символ табуляції
     */
    public static String parseTabCharacter(final String[] args) {
        for (String arg : args) {
            if (arg.matches("(-tc=[\\p{Punct}]+)")) {
                arg = arg.replace("-tc=", "");
                CommandLineParser.setDefaultTabCharacter(arg);
            } else if (arg.matches("(-tab-character=[\\p{Punct}]+)")) {
                arg = arg.replace("-tab-character=", "");
                CommandLineParser.setDefaultTabCharacter(arg);
            }
        }
        return CommandLineParser.getDefaultTabCharacter();
    }

    /**
     * Призначений, для отримання символу команди.
     *
     * @param args
     *            аргументи командної строки
     * @return символ команди
     */
    public static String parseCommandCharacter(final String[] args) {
        for (String arg : args) {
            if (arg.matches("(-cc=[\\p{Punct}]+)")) {
                arg = arg.replace("-cc=", "");
                CommandLineParser.setDefaultCommandCharacter(arg);
            } else if (arg.matches("(-command-character=[\\p{Punct}]+)")) {
                arg = arg.replace("-command-character=", "");
                CommandLineParser.setDefaultCommandCharacter(arg);
            }
        }
        return CommandLineParser.getDefaultCommandCharacter();
    }

    /**
     * Призначений, для отримання символу команд за змовчуванням.
     *
     * @return символ команди за змовчуванням
     */
    public static String getDefaultCommandCharacter() {
        return defaultCommandCharacter;
    }

    /**
     * Призначений, для оновлення символу команд за змовчуванням.
     *
     * @param defaultCommandCharacter
     *            символ команди за змовчуванням
     */
    public static void setDefaultCommandCharacter(
            final String defaultCommandCharacter) {
        CommandLineParser.defaultCommandCharacter = defaultCommandCharacter;
    }

    /**
     * Призначений, для отримання символу табуляції за змовчуванням.
     *
     * @return the defaultTabCharacter символ табуляції за змовчуванням
     */
    public static String getDefaultTabCharacter() {
        return defaultTabCharacter;
    }

    /**
     * Призначений, для оновлення символу табуляції за змовучанням.
     *
     * @param defaultTabCharacter
     *            символ табуляції за змовчуванням
     */
    public static void setDefaultTabCharacter(
            final String defaultTabCharacter) {
        CommandLineParser.defaultTabCharacter = defaultTabCharacter;
    }

    /**
     * Призначений, для отримання значення історії команд за змовчуванням.
     *
     * @return розмір історії команд за змовчуванням
     */
    public static int getDefaultHistorySize() {
        return defaultHistorySize;
    }

    /**
     * Призначений, для оновлення значення історії команд за змовчуванням.
     *
     * @param defaultHistorySize
     *            розмір історії команд за змовчуванням
     */
    public static void setDefaultHistorySize(final int defaultHistorySize) {
        CommandLineParser.defaultHistorySize = defaultHistorySize;
    }
}
