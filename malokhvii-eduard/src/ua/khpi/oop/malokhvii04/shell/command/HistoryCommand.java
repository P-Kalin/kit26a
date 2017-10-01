package ua.khpi.oop.malokhvii04.shell.command;

import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

import ua.khpi.oop.malokhvii04.shell.Shell;
import ua.khpi.oop.malokhvii04.shell.ShellData;

/**
 * Призначений, для інкапсуляції виведення історії виклів команд під виглядом
 * команди.
 *
 * @author malokhvii-ee
 * @version 1.0.0
 * @see Shell
 * @see CommandFactory
 * @see AbstractCommand
 */
public final class HistoryCommand extends AbstractCommand {

    /**
     * Ключи, для виклику команди із інтерактивної оболонки.
     */
    private static List<String> keys = Arrays.asList("-history", "-hs");

    static {
        CommandFactory.registerCommand(HistoryCommand.keys,
                HistoryCommand.class);
        Shell.registerCommandDescription(String.join(" ", HistoryCommand.keys),
                "Show commands call history");
    }

    /**
     * Призначений, для ініціалізації команди унікальним індентифікатором, та
     * посилання на дані інтерактивної оболонки.
     *
     * @param id
     *            унікальний індентифікатор команди
     * @param shellData
     *            посилання на дані інтерактивної оболнки
     */
    public HistoryCommand(final String id, final ShellData shellData) {
        super(id, shellData);
    }

    /**
     * Призначений, для виведення відомостей про команду.
     *
     * @param commandIndex
     *            індекс команди з історії
     * @param command
     *            об'єкт команди
     */
    public void printCommandInfo(final int commandIndex,
            final Command command) {
        System.out.format(
                "%2$s Index: %1$d\n" + "%2$s Name: %3$s\n%2$S Id: %4$s\n\n",
                commandIndex, this.getShellData().getTabCharacter(),
                command.getName(), command.getId());
    }

    @Override
    public void execute() {
        Deque<Command> commandHistory = this.getShellData().getCommandHistory();
        if (!commandHistory.isEmpty()) {
            Iterator<Command> commandHistoryIterator = commandHistory
                    .iterator();

            int currentCommandIndex = getShellData().getCurrentCommandIndex();
            while (commandHistoryIterator.hasNext()) {
                this.printCommandInfo(currentCommandIndex,
                        commandHistoryIterator.next());
                --currentCommandIndex;
            }
        } else {
            System.out.println(
                    "Oops, command history is empty, try to invoke some"
                            + " commands\n");
        }
    }
}
