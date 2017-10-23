package ua.khpi.oop.malokhvii04.shell.commands.text;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import ua.khpi.oop.malokhvii04.shell.Shell;
import ua.khpi.oop.malokhvii04.shell.ShellCommandsPool;
import ua.khpi.oop.malokhvii04.shell.ShellResources;
import ua.khpi.oop.malokhvii04.shell.commands.HandleTextFileCommand;
import ua.khpi.oop.malokhvii05.util.Array;

/**
 * Призначений, для інкапсуляції введення шляху до файлу для подальшої обробки
 * даних під виглядом команди.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.2.0
 * @see Shell
 * @see ShellCommandsPool
 */
public final class InputTextCommand extends HandleTextFileCommand {

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
    private static List<String> keys = Arrays.asList("-input", "-i");

    /**
     * Розташування текстового файлу, з властивостями, а саме текстовими
     * рядками, для подальшого виведення в інтерактивній оболонці.
     *
     * @since 1.2.0
     */
    private static final String RESOURCE_BUNDLE_NAME = "shell.commands."
            + "text.InputTextCommandBundle";

    /**
     * Загальний об'єкт ресурсів для локалізації усіх команд.
     *
     * @since 1.2.0
     */
    private static ResourceBundle resourceBundle;

    static {
        ShellCommandsPool.registerCommand(InputTextCommand.keys,
                InputTextCommand.class);
        InputTextCommand.updateResourceBundle();
    }

    /**
     * Призначений, для зчитування тексту із вхідного файлу.
     *
     * @param filePath
     *            шлях до файлу з текстом
     * @return текст з вхідного файлу у вигляді масиву рядків
     * @throws IOException
     *             Помилка, під час обробки вхідного файлу
     * @since 1.0.0
     */
    private static Array<String> getInputText(final String filePath)
            throws IOException {
        final Array<String> lines = new Array<String>();

        try (final BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(new FileInputStream(filePath)))) {
            String strLine;
            while ((strLine = bufferedReader.readLine()) != null) {
                lines.add(strLine);
            }
        } catch (final IOException exception) {
            throw exception;
        }

        return lines;
    }

    /**
     * Призначений, для оновлення усіх статичних полів, які залежать від
     * поточної локалізації.
     *
     * @since 1.2.0
     */
    protected static void updateResourceBundle() {
        InputTextCommand.resourceBundle = ShellResources.getInstance()
                .getResourceBundle(InputTextCommand.RESOURCE_BUNDLE_NAME);
        InputTextCommand.description = InputTextCommand.resourceBundle
                .getString("description");
    }

    @Override
    public void execute() {
        Collection<String> lines = null;
        final String filePath = HandleTextFileCommand.getFilePath();

        if (filePath == null) {
            return;
        }

        try {
            lines = InputTextCommand.getInputText(filePath);
        } catch (final IOException exception) {
            System.out.format(
                    InputTextCommand.resourceBundle
                            .getString("not-exist-file-message"),
                    Shell.getInstance().getSettings().getTabCharacter(),
                    filePath);
            return;
        }

        Shell.getInstance().getData().setTextLines((Array<String>) lines);
        System.out.println();
    }

    @Override
    public String getDescription() {
        return InputTextCommand.description;
    }

    @Override
    public List<String> getKeys() {
        return InputTextCommand.keys;
    }

    @Override
    public void update(final Observable observable, final Object object) {
        HandleTextFileCommand.updateResourceBundle();
        InputTextCommand.updateResourceBundle();
    }
}
