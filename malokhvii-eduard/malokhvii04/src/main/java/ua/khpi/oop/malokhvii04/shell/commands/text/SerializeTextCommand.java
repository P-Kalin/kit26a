package ua.khpi.oop.malokhvii04.shell.commands.text;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import ua.khpi.oop.malokhvii04.shell.Shell;
import ua.khpi.oop.malokhvii04.shell.ShellCommandsPool;
import ua.khpi.oop.malokhvii04.shell.ShellResources;
import ua.khpi.oop.malokhvii04.shell.commands.HandleTextFileCommand;
import ua.khpi.oop.malokhvii05.util.Array;

/**
 * Призначений, для інкапсуляції серіалізації поточного стану колекції рядків,
 * під виглядом команди.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 * @see Shell
 * @see ShellCommandsPool
 */
public final class SerializeTextCommand extends HandleTextFileCommand {

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
    private static List<String> keys = Arrays.asList("-serialize", "-ser");

    /**
     * Розташування текстового файлу, з властивостями, а саме текстовими
     * рядками, для подальшого виведення в інтерактивній оболонці.
     *
     * @since 1.0.0
     */
    private static final String RESOURCE_BUNDLE_NAME = "shell.commands."
            + "text.SerializeTextCommandBundle";

    /**
     * Загальний об'єкт ресурсів для локалізації усіх команд.
     *
     * @since 1.0.0
     */
    private static ResourceBundle resourceBundle;

    static {
        ShellCommandsPool.registerCommand(SerializeTextCommand.keys,
                SerializeTextCommand.class);
        SerializeTextCommand.updateResourceBundle();
    }

    /**
     * Призначений, для оновлення усіх статичних полів, які залежать від
     * поточної локалізації.
     *
     * @since 1.0.0
     */
    protected static void updateResourceBundle() {
        SerializeTextCommand.resourceBundle = ShellResources.getInstance()
                .getResourceBundle(SerializeTextCommand.RESOURCE_BUNDLE_NAME);
        SerializeTextCommand.description = SerializeTextCommand.resourceBundle
                .getString("description");
    }

    @Override
    public void execute() {
        final String filePath = HandleTextFileCommand.getFilePath();

        if (filePath == null) {
            return;
        }

        final Array<String> textLines = Shell.getInstance().getData()
                .getTextLines();

        if (textLines.isEmpty()) {
            System.out.println(SerializeTextCommand.resourceBundle
                    .getString("empty-text-lines-message"));
            System.out.println();
            return;
        }

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream(filePath))) {
            objectOutputStream.writeObject(textLines);
        } catch (final Exception exception) {
            System.out.println(SerializeTextCommand.resourceBundle
                    .getString("serialization-fail-message"));
            final File file = new File(filePath);
            file.delete();
        }

        System.out.println();
    }

    @Override
    public String getDescription() {
        return SerializeTextCommand.description;
    }

    @Override
    public List<String> getKeys() {
        return SerializeTextCommand.keys;
    }

    @Override
    public void update(final Observable observable, final Object object) {
        SerializeTextCommand.updateResourceBundle();
    }
}
