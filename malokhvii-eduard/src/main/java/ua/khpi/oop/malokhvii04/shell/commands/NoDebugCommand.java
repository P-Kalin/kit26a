package ua.khpi.oop.malokhvii04.shell.commands;

import java.util.Arrays;
import java.util.List;
import java.util.Observable;

import ua.khpi.oop.malokhvii04.shell.Locale;
import ua.khpi.oop.malokhvii04.shell.LocaleDictionary;
import ua.khpi.oop.malokhvii04.shell.Shell;
import ua.khpi.oop.malokhvii04.shell.ShellCommandsPool;

/**
 * Призначений, для інкапсуляції вимкнення виведення відладочної інформації під
 * виглядом команди.
 *
 * @author malokhvii-eduard
 * @version 1.1.0
 * @see Shell
 * @see ShellCommandsPool
 * @see AbstractCommand
 * @since 1.0.0
 */
public final class NoDebugCommand extends AbstractCommand {

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
    private static List<String> keys = Arrays.asList("-no-debug", "-nd");

    static {
        ShellCommandsPool.registerCommand(keys, NoDebugCommand.class);
        Locale locale = LocaleDictionary.getInstance().getDefaultLocale();
        description = locale.getString("005x000");
    }

    @Override
    public void execute() {
        Locale locale = LocaleDictionary.getInstance().getDefaultLocale();
        System.out.println(locale.getString("005x001"));
        Shell.getInstance().setCommandDecorator(null);
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
        description = locale.getString("005x000");
    }
}
