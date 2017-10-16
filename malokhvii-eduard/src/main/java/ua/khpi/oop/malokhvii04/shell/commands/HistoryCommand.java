package ua.khpi.oop.malokhvii04.shell.commands;

import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

import ua.khpi.oop.malokhvii04.shell.Locale;
import ua.khpi.oop.malokhvii04.shell.LocaleDictionary;
import ua.khpi.oop.malokhvii04.shell.Shell;
import ua.khpi.oop.malokhvii04.shell.ShellCommandsPool;

/**
 * Призначений, для інкапсуляції виведення історії виклів команд під виглядом
 * команди.
 *
 * @author malokhvii-eduard
 * @version 1.1.0
 * @see Shell
 * @see ShellCommandsPool
 * @see AbstractCommand
 * @since 1.0.0
 */
public final class HistoryCommand extends AbstractCommand {

    /**
     * Детальний опис команди.
     *
     * @since 1.1.0
     */
    private static String description;

    /**
     * Ключи, для виклику команди із інтерактивної оболонки.
     *
     * @since 1.0.0
     */
    private static List<String> keys = Arrays.asList("-history", "-hs");

    static {
        ShellCommandsPool.registerCommand(keys, HistoryCommand.class);
        Locale locale = LocaleDictionary.getInstance().getDefaultLocale();
        description = locale.getString("003x000");
    }

    /**
     * Призначений, для виведення відомостей про команду.
     *
     * @param locale
     *            об'єкт локалізації
     * @param commandIndex
     *            індекс команди з історії
     * @param command
     *            об'єкт команди
     * @since 1.0.0
     */
    private static void printCommandInfo(final int commandIndex,
            final Command command, final Locale locale) {
        System.out.format(locale.getString("003x001"), commandIndex,
                Shell.getInstance().getSettings().getTabCharacter(),
                command.getName());
    }

    @Override
    public void execute() {
        Locale locale = LocaleDictionary.getInstance().getDefaultLocale();

        Deque<Command> commandHistory = Shell.getInstance().getCommandHistory();
        if (!commandHistory.isEmpty()) {
            Iterator<Command> commandHistoryIterator = commandHistory
                    .iterator();

            int currentCommandIndex = Shell.getInstance()
                    .getCurrentCommandIndex();
            while (commandHistoryIterator.hasNext()) {
                printCommandInfo(currentCommandIndex,
                        commandHistoryIterator.next(), locale);
                --currentCommandIndex;
            }
        } else {
            System.out.println(locale.getString("003x002"));
        }
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public List<String> getKeys() {
        return keys;
    }

    @Override
    public void update(final Observable observable, final Object object) {
        Locale locale = (Locale) object;
        description = locale.getString("003x000");
    }
}
