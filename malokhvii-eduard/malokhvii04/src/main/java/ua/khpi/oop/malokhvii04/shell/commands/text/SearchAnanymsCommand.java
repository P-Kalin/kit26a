package ua.khpi.oop.malokhvii04.shell.commands.text;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import ua.khpi.oop.malokhvii03.text.Anagrams;
import ua.khpi.oop.malokhvii03.text.Ananym;
import ua.khpi.oop.malokhvii04.shell.Shell;
import ua.khpi.oop.malokhvii04.shell.ShellCommandsPool;
import ua.khpi.oop.malokhvii04.shell.ShellResources;

/**
 * Призначений, для інкапсуляції операції пошуку ананимів, тобто наприклад пар
 * слів("def" - "fed") у вхідному наборі рядків, під виглядом команди.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.2.0
 * @see Shell
 * @see ShellCommandsPool
 * @see Anagrams
 */
public final class SearchAnanymsCommand extends OutputTextCommand {

    /**
     * Нижня частина таблиці результатів.
     */
    private static final String ANANYMS_TABLE_FOOTER = "%1$s |"
            + "-------------------------------------|"
            + "-------------------------------------|\n\n";

    /**
     * Верхня частина таблиці результатів.
     */
    private static final String ANANYMS_TABLE_HEADER = "%1$s |"
            + "--------------------------------"
            + "-----|-------------------------------------|\n"
            + "%1$s | %2$-35s | %3$-35s |\n"
            + "%1$s |-------------------------------------"
            + "|-------------------------------------|\n";

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
    private static List<String> keys = Arrays.asList("-ananyms", "-t08",
            "-task08");

    /**
     * Розташування текстового файлу, з властивостями, а саме текстовими
     * рядками, для подальшого виведення в інтерактивній оболонці.
     *
     * @since 1.2.0
     */
    private static final String RESOURCE_BUNDLE_NAME = "shell.commands."
            + "text.SearchAnanymsCommandBundle";

    /**
     * Загальний об'єкт ресурсів для локалізації усіх команд.
     *
     * @since 1.2.0
     */
    private static ResourceBundle resourceBundle;

    static {
        ShellCommandsPool.registerCommand(SearchAnanymsCommand.keys,
                SearchAnanymsCommand.class);
        SearchAnanymsCommand.updateResourceBundle();
    }

    /**
     * Призначений, для виведення колекції ананимів (анаграм). У вигляді
     * упорядкованної таблиці.
     *
     * @param ananyms
     *            колекція ананимів (анаграм)
     */
    private static void printAnanymsCollection(
            final Collection<Ananym> ananyms) {
        System.out.print(SearchAnanymsCommand.resourceBundle
                .getString("search-result-message"));

        final String tabCharacter = Shell.getInstance().getSettings()
                .getTabCharacter();
        System.out.format(
                SearchAnanymsCommand.resourceBundle
                        .getString("ananyms-summary-message"),
                tabCharacter, ananyms.size());

        System.out.println(SearchAnanymsCommand.resourceBundle
                .getString("ananyms-table-message"));

        System.out.format(SearchAnanymsCommand.ANANYMS_TABLE_HEADER,
                tabCharacter,
                SearchAnanymsCommand.resourceBundle
                        .getString("ananyms-table-column-1"),
                SearchAnanymsCommand.resourceBundle
                        .getString("ananyms-table-column-2"));

        for (final Ananym ananym : ananyms) {
            System.out.format("%s | %-35s | %-35s |\n", tabCharacter,
                    ananym.getWord(), ananym.getReversedWord());
        }

        System.out.format(SearchAnanymsCommand.ANANYMS_TABLE_FOOTER,
                tabCharacter);
    }

    /**
     * Призначений, для оновлення усіх статичних полів, які залежать від
     * поточної локалізації.
     *
     * @since 1.2.0
     */
    protected static void updateResourceBundle() {
        SearchAnanymsCommand.resourceBundle = ShellResources.getInstance()
                .getResourceBundle(SearchAnanymsCommand.RESOURCE_BUNDLE_NAME);
        SearchAnanymsCommand.description = SearchAnanymsCommand.resourceBundle
                .getString("description");
    }

    @Override
    public void execute() {
        super.execute();
        final Collection<String> textLines = Shell.getInstance().getData()
                .getTextLines();
        if (!textLines.isEmpty()) {
            final Collection<Ananym> ananyms = Anagrams.findAllAnanymsInString(
                    textLines, Anagrams.DEFAUL_WORD_PATTERN);

            if (!ananyms.isEmpty()) {
                SearchAnanymsCommand.printAnanymsCollection(ananyms);
            } else {
                System.out.print(SearchAnanymsCommand.resourceBundle
                        .getString("ananyms-not-found-message"));
            }
        }
    }

    @Override
    public String getDescription() {
        return SearchAnanymsCommand.description;
    }

    @Override
    public List<String> getKeys() {
        return SearchAnanymsCommand.keys;
    }

    @Override
    public void update(final Observable observable, final Object object) {
        OutputTextCommand.updateResourceBundle();
        SearchAnanymsCommand.updateResourceBundle();
    }
}
