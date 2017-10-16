package ua.khpi.oop.malokhvii04.shell.commands;

import java.util.Arrays;
import java.util.List;
import java.util.Observable;

import ua.khpi.oop.malokhvii04.shell.Locale;
import ua.khpi.oop.malokhvii04.shell.LocaleDictionary;
import ua.khpi.oop.malokhvii04.shell.Shell;
import ua.khpi.oop.malokhvii04.shell.ShellCommandsPool;

/**
 * Призначений, для інкапсуляції завершення сеансу інтерактивної оболонки під
 * виглядом команди.
 *
 * @author malokhvii-eduard
 * @version 1.1.0
 * @see Shell
 * @see ShellCommandsPool
 * @see AbstractCommand
 * @since 1.0.0
 */
public final class ExitCommand extends AbstractCommand {

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
    private static List<String> keys = Arrays.asList("-exit", "-e");

    static {
        ShellCommandsPool.registerCommand(keys, ExitCommand.class);
        Locale locale = LocaleDictionary.getInstance().getDefaultLocale();
        description = locale.getString("011x000");
    }

    @Override
    public void execute() {
        Shell.getInstance().terminate();
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
        description = locale.getString("011x000");
    }
}
