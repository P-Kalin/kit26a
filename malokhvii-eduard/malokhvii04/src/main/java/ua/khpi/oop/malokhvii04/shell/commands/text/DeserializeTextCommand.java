package ua.khpi.oop.malokhvii04.shell.commands.text;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import ua.khpi.oop.malokhvii04.shell.Shell;
import ua.khpi.oop.malokhvii04.shell.ShellCommandsPool;
import ua.khpi.oop.malokhvii04.shell.ShellResources;
import ua.khpi.oop.malokhvii04.shell.commands.HandleTextFileCommand;
import ua.khpi.oop.malokhvii05.common.collect.Array;

/**
 * Призначений, для інкапсуляції десеріалізації текстового файлу у вигляді
 * колекції рядків, під виглядом команди.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 * @see Shell
 * @see ShellCommandsPool
 */
public final class DeserializeTextCommand extends HandleTextFileCommand {

    /**
     * Розташування текстового файлу, з властивостями, а саме текстовими
     * рядками, для подальшого виведення в інтерактивній оболонці.
     *
     * @since 1.0.0
     */
    private static final String RESOURCE_BUNDLE_NAME = "shell.commands."
            + "text.DeserializeTextCommandBundle";

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
    private static List<String> keys = Arrays.asList("-deserialize", "-deser");

    /**
     * Загальний об'єкт ресурсів для локалізації усіх команд.
     *
     * @since 1.2.0
     */
    private static ResourceBundle resourceBundle;

    static {
        ShellCommandsPool.registerCommand(DeserializeTextCommand.keys,
                DeserializeTextCommand.class);
        DeserializeTextCommand.updateResourceBundle();
    }

    /**
     * Призначений, для оновлення усіх статичних полів, які залежать від
     * поточної локалізації.
     *
     * @since 1.0.0
     */
    protected static void updateResourceBundle() {
        DeserializeTextCommand.resourceBundle = ShellResources.getInstance()
                .getResourceBundle(DeserializeTextCommand.RESOURCE_BUNDLE_NAME);
        DeserializeTextCommand.description = DeserializeTextCommand.resourceBundle
                .getString("description");
    }

    @SuppressWarnings("unchecked")
    @Override
    public void execute() {
        final String filePath = HandleTextFileCommand.getFilePath();

        if (filePath == null) {
            return;
        }

        try (ObjectInputStream objectInputStream = new ObjectInputStream(
                new FileInputStream(filePath))) {
            final Object textLines = objectInputStream.readObject();
            Shell.getInstance().getData()
                    .setTextLines((Array<String>) textLines);
        } catch (final Exception exception) {
            System.out.println(DeserializeTextCommand.resourceBundle
                    .getString("deserialization-fail-message"));
        }

        System.out.println();
    }

    @Override
    public String getDescription() {
        return DeserializeTextCommand.description;
    }

    @Override
    public List<String> getKeys() {
        return DeserializeTextCommand.keys;
    }

    @Override
    public void update(final Observable observable, final Object object) {
        HandleTextFileCommand.updateResourceBundle();
        DeserializeTextCommand.updateResourceBundle();
    }
}
