package ua.khpi.oop.malokhvii04.shell.commands;

import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import ua.khpi.oop.malokhvii04.shell.Shell;
import ua.khpi.oop.malokhvii04.shell.ShellCommandsPool;
import ua.khpi.oop.malokhvii04.shell.ShellResources;

/**
 * Призначений, для інкапсуляції виведення історії виклів команд під виглядом
 * команди.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.2.0
 * @see Shell
 * @see ShellCommandsPool
 * @see AbstractCommand
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

    /**
     * Розташування текстового файлу, з властивостями, а саме текстовими
     * рядками, для подальшого виведення в інтерактивній оболонці.
     *
     * @since 1.2.0
     */
    private static final String RESOURCE_BUNDLE_NAME = "shell.commands."
            + "HistoryCommandBundle";

    /**
     * Загальний об'єкт ресурсів для локалізації усіх команд.
     *
     * @since 1.2.0
     */
    private static ResourceBundle resourceBundle;

    static {
        ShellCommandsPool.registerCommand(HistoryCommand.keys,
                HistoryCommand.class);
        HistoryCommand.updateResourceBundle();
    }

    /**
     * Призначений, для виведення відомостей про команду.
     *
     * @param commandIndex
     *            індекс команди з історії
     * @param command
     *            об'єкт команди
     * @since 1.0.0
     */
    private static void printCommandInfo(final int commandIndex,
            final Command command) {
        System.out.format(
                HistoryCommand.resourceBundle
                        .getString("history-message-template"),
                commandIndex,
                Shell.getInstance().getSettings().getTabCharacter(),
                command.getName());
    }

    /**
     * Призначений, для оновлення усіх статичних полів, які залежать від
     * поточної локалізації.
     *
     * @since 1.2.0
     */
    private static void updateResourceBundle() {
        HistoryCommand.resourceBundle = ShellResources.getInstance()
                .getResourceBundle(HistoryCommand.RESOURCE_BUNDLE_NAME);
        HistoryCommand.description = HistoryCommand.resourceBundle
                .getString("description");
    }

    @Override
    public void execute() {
        final Deque<Command> commandHistory = Shell.getInstance()
                .getCommandHistory();
        if (!commandHistory.isEmpty()) {
            final Iterator<Command> commandHistoryIterator = commandHistory
                    .iterator();

            int currentCommandIndex = Shell.getInstance()
                    .getCurrentCommandIndex();
            while (commandHistoryIterator.hasNext()) {
                HistoryCommand.printCommandInfo(currentCommandIndex,
                        commandHistoryIterator.next());
                --currentCommandIndex;
            }
        } else {
            System.out.println(HistoryCommand.resourceBundle
                    .getString("empty-history-message"));
        }
    }

    @Override
    public String getDescription() {
        return HistoryCommand.description;
    }

    @Override
    public List<String> getKeys() {
        return HistoryCommand.keys;
    }

    @Override
    public void update(final Observable observable, final Object object) {
        HistoryCommand.updateResourceBundle();
    }
}
