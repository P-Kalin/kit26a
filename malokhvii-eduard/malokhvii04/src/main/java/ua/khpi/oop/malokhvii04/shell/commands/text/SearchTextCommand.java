package ua.khpi.oop.malokhvii04.shell.commands.text;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.Scanner;

import ua.khpi.oop.malokhvii04.shell.Shell;
import ua.khpi.oop.malokhvii04.shell.ShellCommandsPool;
import ua.khpi.oop.malokhvii04.shell.ShellResources;
import ua.khpi.oop.malokhvii04.shell.commands.AbstractCommand;

/**
 * Призначений, для інкапсуляції пошуку рядку у колекції рядків, під виглядом
 * команди.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 * @see Shell
 * @see ShellCommandsPool
 * @see AbstractCommand
 */
public final class SearchTextCommand extends AbstractCommand {

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
    private static List<String> keys = Arrays.asList("-search-text", "-srt");

    /**
     * Розташування текстового файлу, з властивостями, а саме текстовими
     * рядками, для подальшого виведення в інтерактивній оболонці.
     *
     * @since 1.0.0
     */
    private static final String RESOURCE_BUNDLE_NAME = "shell.commands."
            + "text.SearchTextCommandBundle";

    /**
     * Загальний об'єкт ресурсів для локалізації усіх команд.
     *
     * @since 1.0.0
     */
    private static ResourceBundle resourceBundle;

    static {
        ShellCommandsPool.registerCommand(SearchTextCommand.keys,
                SearchTextCommand.class);
        SearchTextCommand.updateResourceBundle();
    }

    /**
     * Призначений, для оновлення усіх статичних полів, які залежать від
     * поточної локалізації.
     *
     * @since 1.0.0
     */
    private static void updateResourceBundle() {
        SearchTextCommand.resourceBundle = ShellResources.getInstance()
                .getResourceBundle(SearchTextCommand.RESOURCE_BUNDLE_NAME);
        SearchTextCommand.description = SearchTextCommand.resourceBundle
                .getString("description");
    }

    @Override
    public void execute() {
        final Collection<String> textLines = Shell.getInstance().getData()
                .getTextLines();
        if (textLines.isEmpty()) {
            System.out.println(SearchTextCommand.resourceBundle
                    .getString("text-lines-not-found-message"));
            return;
        }

        System.out.format(
                SearchTextCommand.resourceBundle.getString("enter-message"),
                Shell.getInstance().getSettings().getTabCharacter());
        final Scanner scanner = Shell.getInstance().getScanner();
        final String wordToSearch = scanner.next();
        scanner.nextLine();
        System.out.println();

        boolean containsWord = false;
        for (final String line : textLines) {
            if (line.contains(wordToSearch)) {
                containsWord = true;
                break;
            }
        }

        if (containsWord) {
            System.out.println(SearchTextCommand.resourceBundle
                    .getString("contains-message"));
        } else {
            System.out.println(SearchTextCommand.resourceBundle
                    .getString("not-contains-message"));
        }
    }

    @Override
    public String getDescription() {
        return SearchTextCommand.description;
    }

    @Override
    public List<String> getKeys() {
        return SearchTextCommand.keys;
    }

    @Override
    public void update(final Observable observable, final Object object) {
        SearchTextCommand.updateResourceBundle();
    }
}
