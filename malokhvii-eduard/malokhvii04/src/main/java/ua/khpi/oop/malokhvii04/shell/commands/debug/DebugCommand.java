package ua.khpi.oop.malokhvii04.shell.commands.debug;

import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import ua.khpi.oop.malokhvii04.shell.Shell;
import ua.khpi.oop.malokhvii04.shell.ShellCommandsPool;
import ua.khpi.oop.malokhvii04.shell.ShellResources;
import ua.khpi.oop.malokhvii04.shell.commands.AbstractCommand;

/**
 * Призначений, для інкапсуляції вмикання виведення відладочної інформації під
 * виглядом команди.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.2.0
 * @see Shell
 * @see ShellCommandsPool
 * @see AbstractCommand
 */
public final class DebugCommand extends AbstractCommand {

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
    private static List<String> keys = Arrays.asList("-debug", "-d");

    /**
     * Розташування текстового файлу, з властивостями, а саме текстовими
     * рядками, для подальшого виведення в інтерактивній оболонці.
     *
     * @since 1.2.0
     */
    private static final String RESOURCE_BUNDLE_NAME = "shell.commands."
            + "debug.DebugCommandBundle";

    /**
     * Загальний об'єкт ресурсів для локалізації усіх команд.
     *
     * @since 1.2.0
     */
    private static ResourceBundle resourceBundle;

    static {
        ShellCommandsPool.registerCommand(DebugCommand.keys,
                DebugCommand.class);
        DebugCommand.updateResourceBundle();
    }

    /**
     * Призначений, для оновлення усіх статичних полів, які залежать від
     * поточної локалізації.
     *
     * @since 1.2.0
     */
    private static void updateResourceBundle() {
        DebugCommand.resourceBundle = ShellResources.getInstance()
                .getResourceBundle(DebugCommand.RESOURCE_BUNDLE_NAME);
        DebugCommand.description = DebugCommand.resourceBundle
                .getString("description");
    }

    @Override
    public void execute() {
        System.out.println(
                DebugCommand.resourceBundle.getString("switch-on-message"));
        Shell.getInstance().setCommandDecorator(CommandWithDebug.class);
    }

    @Override
    public String getDescription() {
        return DebugCommand.description;
    }

    @Override
    public List<String> getKeys() {
        return DebugCommand.keys;
    }

    @Override
    public void update(final Observable observable, final Object object) {
        DebugCommand.updateResourceBundle();
    }
}
