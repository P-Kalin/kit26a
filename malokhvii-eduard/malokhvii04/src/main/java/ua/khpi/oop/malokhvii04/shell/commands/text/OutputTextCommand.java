package ua.khpi.oop.malokhvii04.shell.commands.text;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import ua.khpi.oop.malokhvii04.shell.Shell;
import ua.khpi.oop.malokhvii04.shell.ShellCommandsPool;
import ua.khpi.oop.malokhvii04.shell.ShellResources;
import ua.khpi.oop.malokhvii04.shell.commands.AbstractCommand;

/**
 * Призначений, для інкапсуляції виведення колекції рядків, під виглядом
 * команди.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 * @see Shell
 * @see ShellCommandsPool
 * @see AbstractCommand
 */
public class OutputTextCommand extends AbstractCommand {

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
    private static List<String> keys = Arrays.asList("-output", "-o");

    /**
     * Розташування текстового файлу, з властивостями, а саме текстовими
     * рядками, для подальшого виведення в інтерактивній оболонці.
     *
     * @since 1.0.0
     */
    private static final String RESOURCE_BUNDLE_NAME = "shell.commands."
            + "text.OutputTextCommandBundle";

    /**
     * Загальний об'єкт ресурсів для локалізації усіх команд.
     *
     * @since 1.0.0
     */
    private static ResourceBundle resourceBundle;

    static {
        ShellCommandsPool.registerCommand(OutputTextCommand.keys,
                OutputTextCommand.class);
        OutputTextCommand.updateResourceBundle();
    }

    /**
     * Призначений, для виведенння колекції рядків.
     *
     * @param textLines
     *            колекція рядків
     */
    protected static void printTextLines(final Collection<String> textLines) {
        System.out.println(OutputTextCommand.resourceBundle
                .getString("text-lines-summary-message"));

        final String tabCharacter = Shell.getInstance().getSettings()
                .getTabCharacter();
        for (final CharSequence textLine : textLines) {
            System.out.format("%1$s %2$s\n", tabCharacter, textLine);
        }

        System.out.println();
    }

    /**
     * Призначений, для оновлення усіх статичних полів, які залежать від
     * поточної локалізації.
     *
     * @since 1.0.0
     */
    protected static void updateResourceBundle() {
        OutputTextCommand.resourceBundle = ShellResources.getInstance()
                .getResourceBundle(OutputTextCommand.RESOURCE_BUNDLE_NAME);
        OutputTextCommand.description = OutputTextCommand.resourceBundle
                .getString("description");
    }

    @Override
    public void execute() {
        final Collection<String> textLines = Shell.getInstance().getData()
                .getTextLines();
        if (!textLines.isEmpty()) {
            OutputTextCommand.printTextLines(textLines);
        } else {
            System.out.print(OutputTextCommand.resourceBundle
                    .getString("text-lines-not-found-message"));
        }
    }

    @Override
    public String getDescription() {
        return OutputTextCommand.description;
    }

    @Override
    public List<String> getKeys() {
        return OutputTextCommand.keys;
    }

    @Override
    public void update(final Observable observable, final Object object) {
        OutputTextCommand.updateResourceBundle();
    }
}
