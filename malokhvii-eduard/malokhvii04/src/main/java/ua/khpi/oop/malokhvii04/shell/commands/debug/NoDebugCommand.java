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
 * Призначений, для інкапсуляції вимкнення виведення відладочної інформації під
 * виглядом команди.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.2.0
 * @see Shell
 * @see ShellCommandsPool
 * @see AbstractCommand
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

    /**
     * Розташування текстового файлу, з властивостями, а саме текстовими
     * рядками, для подальшого виведення в інтерактивній оболонці.
     *
     * @since 1.2.0
     */
    private static final String RESOURCE_BUNDLE_NAME = "shell.commands."
            + "debug.NoDebugCommandBundle";

    /**
     * Загальний об'єкт ресурсів для локалізації усіх команд.
     *
     * @since 1.2.0
     */
    private static ResourceBundle resourceBundle;

    static {
        ShellCommandsPool.registerCommand(NoDebugCommand.keys,
                NoDebugCommand.class);
        NoDebugCommand.updateResourceBundle();
    }

    /**
     * Призначений, для оновлення усіх статичних полів, які залежать від
     * поточної локалізації.
     *
     * @since 1.2.0
     */
    private static void updateResourceBundle() {
        NoDebugCommand.resourceBundle = ShellResources.getInstance()
                .getResourceBundle(NoDebugCommand.RESOURCE_BUNDLE_NAME);
        NoDebugCommand.description = NoDebugCommand.resourceBundle
                .getString("description");
    }

    @Override
    public void execute() {
        System.out.println(
                NoDebugCommand.resourceBundle.getString("switch-off-message"));
        Shell.getInstance().setCommandDecorator(null);
    }

    @Override
    public String getDescription() {
        return NoDebugCommand.description;
    }

    @Override
    public List<String> getKeys() {
        return NoDebugCommand.keys;
    }

    @Override
    public void update(final Observable observable, final Object object) {
        NoDebugCommand.updateResourceBundle();
    }
}
