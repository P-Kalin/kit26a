package ua.khpi.oop.malokhvii04.shell.command;

import java.util.Arrays;
import java.util.List;

import ua.khpi.oop.malokhvii04.shell.Shell;
import ua.khpi.oop.malokhvii04.shell.ShellData;

/**
 * Призначений, для інкапсуляції завершення сеансу інтерактивної оболонки під
 * виглядом команди.
 *
 * @author malokhvii-ee
 * @version 1.0.0
 * @see Shell
 * @see CommandFactory
 * @see AbstractCommand
 */
public final class ExitCommand extends AbstractCommand {

    /**
     * Ключи, для виклику команди із інтерактивної оболонки.
     */
    private static List<String> keys = Arrays.asList("-exit", "-e");

    static {
        CommandFactory.registerCommand(ExitCommand.keys, ExitCommand.class);
        Shell.registerCommandDescription(String.join(" ", ExitCommand.keys),
                "End interactive console session");
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
    public ExitCommand(final String id, final ShellData shellData) {
        super(id, shellData);
    }

    @Override
    public void execute() {
        this.getShellData().setRunning(false);
    }
}
