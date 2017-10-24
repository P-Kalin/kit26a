package ua.khpi.oop.malokhvii04.shell.commands;

import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.Scanner;

import ua.khpi.oop.malokhvii04.shell.Shell;
import ua.khpi.oop.malokhvii04.shell.ShellCommandsPool;
import ua.khpi.oop.malokhvii04.shell.ShellResources;
import ua.khpi.oop.malokhvii04.shell.ShellSettings;

/**
 * Призначений, для інкапсуляції оновлення налаштувань інтерактивної оболонки,
 * під виглядом команди.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 * @see Shell
 * @see ShellCommandsPool
 * @see AbstractCommand
 */
public final class UpdateSettingsCommand extends AbstractCommand {

    /**
     * Детальний опис команди.
     *
     * @since 1.0.0
     */
    private static String description;

    /**
     * Ключи, для виклику команди із інтерактивної оболонки.
     *
     * @since 1.0.0
     */
    private static List<String> keys = Arrays.asList("-settings", "-ss");

    /**
     * Розташування текстового файлу, з властивостями, а саме текстовими
     * рядками, для подальшого виведення в інтерактивній оболонці.
     *
     * @since 1.0.0
     */
    private static final String RESOURCE_BUNDLE_NAME = "shell.commands."
            + "UpdateSettingsCommandBundle";

    /**
     * Загальний об'єкт ресурсів для локалізації усіх команд.
     *
     * @since 1.0.0
     */
    private static ResourceBundle resourceBundle;

    static {
        ShellCommandsPool.registerCommand(UpdateSettingsCommand.keys,
                UpdateSettingsCommand.class);
        UpdateSettingsCommand.updateResourceBundle();
    }

    /**
     * Призначений, для оновлення усіх статичних полів, які залежать від
     * поточної локалізації.
     *
     * @since 1.0.0
     */
    private static void updateResourceBundle() {
        UpdateSettingsCommand.resourceBundle = ShellResources.getInstance()
                .getResourceBundle(UpdateSettingsCommand.RESOURCE_BUNDLE_NAME);
        UpdateSettingsCommand.description = UpdateSettingsCommand.resourceBundle
                .getString("description");
    }

    @Override
    public void execute() {
        final Scanner scanner = Shell.getInstance().getScanner();
        final ShellSettings shellSettings = Shell.getInstance().getSettings();
        final String tabCharacter = shellSettings.getTabCharacter();

        try {
            System.out.format(UpdateSettingsCommand.resourceBundle
                    .getString("entry-tab-character-message"), tabCharacter);
            shellSettings.setTabCharacter(scanner.next());
            scanner.nextLine();
            System.out.println();
        } catch (final Exception exception) {
            System.out.println(UpdateSettingsCommand.resourceBundle
                    .getString("illegal-tab-character-message"));
        }

        try {
            System.out.format(UpdateSettingsCommand.resourceBundle.getString(
                    "entry-command-character-message"), tabCharacter);
            shellSettings.setCommandCharacter(scanner.next());
            scanner.nextLine();
            System.out.println();
        } catch (final Exception exception) {
            System.out.println(UpdateSettingsCommand.resourceBundle
                    .getString("illegal-command-character-message"));
        }

        try {
            System.out.format(UpdateSettingsCommand.resourceBundle
                    .getString("entry-command-history-size"), tabCharacter);
            shellSettings.setCommandHistorySize(scanner.nextInt());
            scanner.nextLine();
            System.out.println();
        } catch (final Exception exception) {
            System.out.println(UpdateSettingsCommand.resourceBundle
                    .getString("illegal-command-history-size-message"));
        }
    }

    @Override
    public String getDescription() {
        return UpdateSettingsCommand.description;
    }

    @Override
    public List<String> getKeys() {
        return UpdateSettingsCommand.keys;
    }

    @Override
    public void update(final Observable observable, final Object object) {
        UpdateSettingsCommand.updateResourceBundle();
    }
}
