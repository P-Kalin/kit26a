package ua.khpi.oop.malokhvii04.shell.commands.text;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.Scanner;

import ua.khpi.oop.malokhvii04.shell.Shell;
import ua.khpi.oop.malokhvii04.shell.ShellCommandsPool;
import ua.khpi.oop.malokhvii04.shell.ShellResources;
import ua.khpi.oop.malokhvii04.shell.commands.AbstractCommand;
import ua.khpi.oop.malokhvii05.util.Array;
import ua.khpi.oop.malokhvii05.util.algorithms.sort.SortAlgorithm;
import ua.khpi.oop.malokhvii05.util.algorithms.sort.SortAlgorithmFactory;

/**
 * Призначений, для інкапсуляції сортування колекції рядків, під виглядом
 * команди.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 * @see Shell
 * @see ShellCommandsPool
 * @see AbstractCommand
 */
public final class SortTextCommand extends AbstractCommand {

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
    private static List<String> keys = Arrays.asList("-sort-text", "-st");

    /**
     * Розташування текстового файлу, з властивостями, а саме текстовими
     * рядками, для подальшого виведення в інтерактивній оболонці.
     *
     * @since 1.0.0
     */
    private static final String RESOURCE_BUNDLE_NAME = "shell.commands."
            + "text.SortTextCommandBundle";

    /**
     * Загальний об'єкт ресурсів для локалізації усіх команд.
     *
     * @since 1.0.0
     */
    private static ResourceBundle resourceBundle;

    static {
        ShellCommandsPool.registerCommand(SortTextCommand.keys,
                SortTextCommand.class);
        SortTextCommand.updateResourceBundle();
    }

    /**
     * Призначений, для введення порядку сортування з інтерактивної оболонки.
     *
     * @return порядок сортування true - зростаючий, false - спадаючий
     * @throws IOException
     *             невірний ключ порядку сортування
     * @since 1.0.0
     */
    private static boolean getSortOrder() throws IOException {
        final Scanner scanner = Shell.getInstance().getScanner();
        System.out.format(
                SortTextCommand.resourceBundle
                        .getString("enter-sort-order-message"),
                SortTextCommand.resourceBundle.getString("ascending-order-key"),
                SortTextCommand.resourceBundle
                        .getString("descending-order-key"),
                Shell.getInstance().getSettings().getTabCharacter());
        final String orderAsString = scanner.next();
        scanner.nextLine();

        if (orderAsString.equals(SortTextCommand.resourceBundle
                .getString("ascending-order-key"))) {
            return false;
        } else if (orderAsString.equals(SortTextCommand.resourceBundle
                .getString("descending-order-key"))) {
            return true;
        }
        throw new IOException();
    }

    /**
     * Призначений, для оновлення усіх статичних полів, які залежать від
     * поточної локалізації.
     *
     * @since 1.0.0
     */
    private static void updateResourceBundle() {
        SortTextCommand.resourceBundle = ShellResources.getInstance()
                .getResourceBundle(SortTextCommand.RESOURCE_BUNDLE_NAME);
        SortTextCommand.description = SortTextCommand.resourceBundle
                .getString("description");
    }

    @Override
    public void execute() {

        final Array<String> textLines = Shell.getInstance().getData()
                .getTextLines();
        if (!textLines.isEmpty()) {
            final SortAlgorithm<String> sortAlgorithm = SortAlgorithmFactory
                    .getDefaultAlgorithm();
            try {
                sortAlgorithm.setReversedOrder(SortTextCommand.getSortOrder());
                System.out.println();
            } catch (final IOException exception) {
                System.out.println(SortTextCommand.resourceBundle
                        .getString("mismatch-message"));
            }
            sortAlgorithm.sort(textLines);
            Shell.getInstance().getData().setTextLines(textLines);
        } else {
            System.out.println(SortTextCommand.resourceBundle
                    .getString("text-lines-not-found-message"));
        }
    }

    @Override
    public String getDescription() {
        return SortTextCommand.description;
    }

    @Override
    public List<String> getKeys() {
        return SortTextCommand.keys;
    }

    @Override
    public void update(final Observable observable, final Object object) {
        SortTextCommand.updateResourceBundle();
    }
}
