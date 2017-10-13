package ua.khpi.oop.malokhvii04.shell.command;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ua.khpi.oop.malokhvii04.Application;
import ua.khpi.oop.malokhvii04.shell.Shell;
import ua.khpi.oop.malokhvii04.shell.ShellData;

/**
 * Призначений, для інкапсуляції отримання довідки під виглядом команди.
 *
 * @author malokhvii-eduard
 * @version 1.0.0
 * @see Shell
 * @see CommandFactory
 * @see AbstractCommand
 * @since 1.0.0
 */
public final class HelpCommand extends AbstractCommand {

    /**
     * Ключи, для виклику команди із інтерактивної оболонки.
     *
     * @since 1.0.0
     */
    private static List<String> keys = Arrays.asList("-help", "-h");

    static {
        CommandFactory.registerCommand(HelpCommand.keys, HelpCommand.class);
        Shell.registerCommandDescription(String.join(" ", HelpCommand.keys),
                "Get help on all the interactive console commands");
    }

    /**
     * Призначений, для ініціалізації команди унікальним індентифікатором, та
     * посилання на дані інтерактивної оболонки.
     *
     * @param id
     *            унікальний індентифікатор команди
     * @param shellData
     *            посилання на дані інтерактивної оболнки
     * @since 1.0.0
     */
    public HelpCommand(final String id, final ShellData shellData) {
        super(id, shellData);
    }

    /**
     * Призначений, для виведення опису команди, отриманого від інтерактивної
     * оболочки.
     *
     * @param commandDescriptionEntry
     *            опис команди
     * @since 1.0.0
     */
    public void printCommandDescription(
            final Map.Entry<String, String> commandDescriptionEntry) {
        System.out.format("%s %-20s %-80s\n",
                this.getShellData().getTabCharacter(),
                commandDescriptionEntry.getKey(),
                commandDescriptionEntry.getValue());
    }

    @Override
    public void execute() {
        System.out.format("Usage: %s\n", Application.getApplicationName());

        Map<String, String> commandsDescription = Shell
                .getCommandsDescription();
        Set<Map.Entry<String, String>> entrySet = commandsDescription
                .entrySet();
        for (Map.Entry<String, String> commandDescriptionEntry : entrySet) {
            this.printCommandDescription(commandDescriptionEntry);
        }

        System.out.println();
    }
}
