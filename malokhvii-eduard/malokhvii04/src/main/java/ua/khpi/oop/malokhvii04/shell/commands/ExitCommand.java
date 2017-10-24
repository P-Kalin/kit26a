package ua.khpi.oop.malokhvii04.shell.commands;

import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import ua.khpi.oop.malokhvii04.shell.Shell;
import ua.khpi.oop.malokhvii04.shell.ShellCommandsPool;
import ua.khpi.oop.malokhvii04.shell.ShellResources;

/**
 * Призначений, для інкапсуляції завершення сеансу інтерактивної оболонки під
 * виглядом команди.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.2.0
 * @see Shell
 * @see ShellCommandsPool
 * @see AbstractCommand
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

    /**
     * Розташування текстового файлу, з властивостями, а саме текстовими
     * рядками, для подальшого виведення в інтерактивній оболонці.
     *
     * @since 1.2.0
     */
    private static final String RESOURCE_BUNDLE_NAME = "shell.commands."
            + "ExitCommandBundle";

    static {
        ShellCommandsPool.registerCommand(ExitCommand.keys, ExitCommand.class);
        ExitCommand.updateResourceBundle();
    }

    /**
     * Призначений, для оновлення усіх статичних полів, які залежать від
     * поточної локалізації.
     *
     * @since 1.2.0
     */
    private static void updateResourceBundle() {
        final ResourceBundle resourceBundle = ShellResources.getInstance()
                .getResourceBundle(ExitCommand.RESOURCE_BUNDLE_NAME);
        ExitCommand.description = resourceBundle.getString("description");
    }

    @Override
    public void execute() {
        Shell.getInstance().terminate();
    }

    @Override
    public String getDescription() {
        return ExitCommand.description;
    }

    @Override
    public List<String> getKeys() {
        return ExitCommand.keys;
    }

    @Override
    public void update(final Observable observable, final Object object) {
        ExitCommand.updateResourceBundle();
    }
}
