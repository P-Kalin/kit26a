package ua.khpi.oop.malokhvii04.shell.command;

import java.util.Arrays;
import java.util.List;

import ua.khpi.oop.malokhvii04.shell.Shell;
import ua.khpi.oop.malokhvii04.shell.ShellData;

/**
 * Призначений, для інкапсуляції вмикання виведення відладочної інформації під
 * виглядом команди.
 *
 * @author malokhvii-ee
 * @version 1.0.0
 * @see Shell
 * @see CommandFactory
 * @see AbstractCommand
 */
public final class DebugCommand extends AbstractCommand {

    /**
     * Ключи, для виклику команди із інтерактивної оболонки.
     */
    private static List<String> keys = Arrays.asList("-debug", "-d");

    static {
        CommandFactory.registerCommand(DebugCommand.keys, DebugCommand.class);
        Shell.registerCommandDescription(String.join(" ", DebugCommand.keys),
                "Switch on debug information");
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
    public DebugCommand(final String id, final ShellData shellData) {
        super(id, shellData);
    }

    @Override
    public void execute() {
        System.out.println("Switch on debug infomation\n");
        this.getShellData().setDebugCommandDecorator(CommandWithDebug.class);
    }
}
