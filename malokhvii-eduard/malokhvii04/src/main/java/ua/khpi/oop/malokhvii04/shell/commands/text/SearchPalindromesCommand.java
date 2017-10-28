package ua.khpi.oop.malokhvii04.shell.commands.text;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

import ua.khpi.oop.malokhvii03.text.Anagrams;
import ua.khpi.oop.malokhvii04.shell.Shell;
import ua.khpi.oop.malokhvii04.shell.ShellCommandsPool;
import ua.khpi.oop.malokhvii04.shell.ShellResources;

/**
 * Призначений, для інкапсуляції операції пошуку паліндромів, тобто наприклад
 * слів "civic" у вхідному наборі рядків, під виглядом команди.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 * @see Shell
 * @see ShellCommandsPool
 * @see Anagrams
 */
public final class SearchPalindromesCommand extends OutputTextCommand {

    /**
     * Детальний опис команди.
     *
     * @since 1.0.0
     */
    private static String description;

    private static final int AMOUNT_OF_PALINDROMES_IN_SINGLE_LINE = 10;

    /**
     * Ключи, для виклику команди із інтерактивної оболонки.
     *
     * @since 1.0.0
     */
    private static List<String> keys = Arrays.asList("-palindromes", "-t15",
            "-task15");

    /**
     * Розташування текстового файлу, з властивостями, а саме текстовими
     * рядками, для подальшого виведення в інтерактивній оболонці.
     *
     * @since 1.0.0
     */
    private static final String RESOURCE_BUNDLE_NAME = "shell.commands."
            + "text.SearchPalindromesCommandBundle";

    /**
     * Загальний об'єкт ресурсів для локалізації усіх команд.
     *
     * @since 1.0.0
     */
    private static ResourceBundle resourceBundle;

    static {
        ShellCommandsPool.registerCommand(SearchPalindromesCommand.keys,
                SearchPalindromesCommand.class);
        SearchPalindromesCommand.updateResourceBundle();
    }

    /**
     * Призначений, для виведення переліку паліндромів у вигляді списку.
     *
     * @param palindromes
     *            перелік паліндромів
     * @since 1.0.0
     */
    private static void printPalindromes(final Collection<String> palindromes) {
        System.out.print(SearchPalindromesCommand.resourceBundle
                .getString("search-result-message"));

        final String tabCharacter = Shell.getInstance().getSettings()
                .getTabCharacter();
        System.out.format(
                SearchPalindromesCommand.resourceBundle
                        .getString("palindromes-summary-message"),
                tabCharacter, palindromes.size());

        System.out.println(SearchPalindromesCommand.resourceBundle
                .getString("palindromes-list-message"));

        int palindromesCount = 0;
        System.out.format("%s ", tabCharacter);
        for (final String palindrome : palindromes) {
            if (palindromesCount == SearchPalindromesCommand.AMOUNT_OF_PALINDROMES_IN_SINGLE_LINE) {
                System.out.println();
                System.out.format("%s ", tabCharacter);
                palindromesCount = 0;
            }
            System.out.format("%s ", palindrome);
            palindromesCount++;
        }
        System.out.println("\n");
    }

    protected static void updateResourceBundle() {
        SearchPalindromesCommand.resourceBundle = ShellResources.getInstance()
                .getResourceBundle(
                        SearchPalindromesCommand.RESOURCE_BUNDLE_NAME);
        SearchPalindromesCommand.description = SearchPalindromesCommand.resourceBundle
                .getString("description");
    }

    @Override
    public void execute() {
        super.execute();
        final Collection<String> textLines = Shell.getInstance().getData()
                .getTextLines();
        if (!textLines.isEmpty()) {
            final Collection<String> palindromes = Anagrams
                    .findAllPalindromesInString(textLines,
                            Anagrams.DEFAUL_WORD_PATTERN);
            if (!palindromes.isEmpty()) {
                SearchPalindromesCommand.printPalindromes(palindromes);
            } else {
                System.out.print(SearchPalindromesCommand.resourceBundle
                        .getString("palindromes-not-found-message"));
            }
        }
    }

    @Override
    public String getDescription() {
        return SearchPalindromesCommand.description;
    }

    @Override
    public List<String> getKeys() {
        return SearchPalindromesCommand.keys;
    }

    @Override
    public void update(final Observable observable, final Object object) {
        OutputTextCommand.updateResourceBundle();
        SearchPalindromesCommand.updateResourceBundle();
    }
}
