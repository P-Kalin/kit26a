package ua.khpi.oop.malokhvii04.shell.commands;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

import ua.khpi.oop.malokhvii04.shell.Locale;
import ua.khpi.oop.malokhvii04.shell.LocaleDictionary;
import ua.khpi.oop.malokhvii04.shell.Shell;
import ua.khpi.oop.malokhvii04.shell.ShellCommandsPool;

/**
 * Призначений, для інкапсуляції отримання довідки під виглядом команди.
 *
 * @author malokhvii-eduard
 * @version 1.1.0
 * @see Shell
 * @see ShellCommandsPool
 * @see AbstractCommand
 * @since 1.0.0
 */
public final class HelpCommand extends AbstractCommand {

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
    private static List<String> keys = Arrays.asList("-help", "-h");

    static {
        ShellCommandsPool.registerCommand(keys, HelpCommand.class);
        Locale locale = LocaleDictionary.getInstance().getDefaultLocale();
        description = locale.getString("002x000");
    }

    /**
     * Призначений, для виведення опису команди, отриманого від інтерактивної
     * оболочки.
     *
     * @param keys
     *            ключи для виклику команди в інтерактивній оболонці
     * @param description
     *            опис команди
     * @since 1.0.0
     */
    private static void printCommandDescription(final String keys,
            final String description) {
        System.out.format("%s %-30s %-80s\n",
                Shell.getInstance().getSettings().getTabCharacter(), keys,
                description);
    }

    @Override
    public void execute() {
        Locale locale = LocaleDictionary.getInstance().getDefaultLocale();
        System.out.format(locale.getString("002x001"),
                ua.khpi.oop.malokhvii06.Application.getApplicationName());

        Iterator<String> commandsKeys = ShellCommandsPool.getCommandsKeys()
                .iterator();
        Iterator<String> commandsDescription = ShellCommandsPool
                .getCommandsDescription().iterator();

        while (commandsKeys.hasNext() && commandsDescription.hasNext()) {
            printCommandDescription(commandsKeys.next(),
                    commandsDescription.next());
        }

        System.out.println();
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
        description = locale.getString("002x000");
    }
}
