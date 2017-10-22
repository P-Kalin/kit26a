package ua.khpi.oop.malokhvii04.shell.commands;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import ua.khpi.oop.malokhvii04.Application;
import ua.khpi.oop.malokhvii04.shell.Shell;
import ua.khpi.oop.malokhvii04.shell.ShellCommandsPool;
import ua.khpi.oop.malokhvii04.shell.ShellResources;

/**
 * Призначений, для інкапсуляції отримання довідки під виглядом команди.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.2.0
 * @see Shell
 * @see ShellCommandsPool
 * @see AbstractCommand
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

    /**
     * Розташування текстового файлу, з властивостями, а саме текстовими
     * рядками, для подальшого виведення в інтерактивній оболонці.
     *
     * @since 1.2.0
     */
    private static final String RESOURCE_BUNDLE_NAME = "shell.commands."
            + "HelpCommandBundle";

    /**
     * Загальний об'єкт ресурсів для локалізації усіх команд.
     *
     * @since 1.2.0
     */
    private static ResourceBundle resourceBundle;

    static {
        ShellCommandsPool.registerCommand(HelpCommand.keys, HelpCommand.class);
        HelpCommand.updateResourceBundle();
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

    /**
     * Призначений, для оновлення усіх статичних полів, які залежать від
     * поточної локалізації.
     *
     * @since 1.ц.0
     */
    private static void updateResourceBundle() {
        HelpCommand.resourceBundle = ShellResources.getInstance()
                .getResourceBundle(HelpCommand.RESOURCE_BUNDLE_NAME);
        HelpCommand.description = HelpCommand.resourceBundle
                .getString("description");
    }

    @Override
    public void execute() {
        System.out.format(HelpCommand.resourceBundle.getString("usage-message"),
                Application.getApplicationName());

        final Iterator<String> commandsKeys = ShellCommandsPool
                .getCommandsKeys().iterator();
        final Iterator<String> commandsDescription = ShellCommandsPool
                .getCommandsDescriptions().iterator();

        while (commandsKeys.hasNext() && commandsDescription.hasNext()) {
            HelpCommand.printCommandDescription(commandsKeys.next(),
                    commandsDescription.next());
        }

        System.out.println();
    }

    @Override
    public String getDescription() {
        return HelpCommand.description;
    }

    @Override
    public List<String> getKeys() {
        return HelpCommand.keys;
    }

    @Override
    public void update(final Observable observable, final Object object) {
        HelpCommand.updateResourceBundle();
    }
}
