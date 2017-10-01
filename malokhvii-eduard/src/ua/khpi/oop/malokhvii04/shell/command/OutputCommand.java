package ua.khpi.oop.malokhvii04.shell.command;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import ua.khpi.oop.malokhvii03.text.Ananym;
import ua.khpi.oop.malokhvii03.text.AnanymsCollection;
import ua.khpi.oop.malokhvii03.text.WordsCollection;
import ua.khpi.oop.malokhvii04.shell.Shell;
import ua.khpi.oop.malokhvii04.shell.ShellData;

/**
 * Призначений, для інкапсуляції виведення оброблених та вхідних даних під
 * виглядом команди.
 *
 * @author malokhvii-ee
 * @version 1.0.0
 * @see Shell
 * @see CommandFactory
 * @see AbstractCommand
 */
public final class OutputCommand extends AbstractCommand {

    /**
     * Максимальна кількість слів в один рядок.
     */
    private static final int MAX_WORDS_IN_ONE_LINE = 10;

    /**
     * Верхня частина таблиці результатів.
     */
    private static String ananymsTableHeader = "%1$s |"
            + "--------------------------------"
            + "-----|-------------------------------------|\n"
            + "%1$s | %2$-35s | %3$-35s |\n"
            + "%1$s |-------------------------------------"
            + "|-------------------------------------|\n";

    /**
     * Нижня частина таблиці результатів.
     */
    private static String ananymsTableFooter = "%1$s |-------------------"
            + "------------------|-------------------------------------|\n\n";

    /**
     * Ключи, для виклику команди із інтерактивної оболонки.
     */
    private static List<String> keys = Arrays.asList("-output", "-o");

    static {
        CommandFactory.registerCommand(OutputCommand.keys, OutputCommand.class);
        Shell.registerCommandDescription(String.join(" ", OutputCommand.keys),
                "Output of an anagram search result in the input text");
    }

    /**
     * Призначений, для ініціалізації команди унікальним індентифікатором, та
     * посилання на дані інтерактивної оболонки.
     *
     * @param id
     *            унікальний індентифікатор команди
     * @param shellData
     *            посилання на дані інтерактивної оболнки
     */
    public OutputCommand(final String id, final ShellData shellData) {
        super(id, shellData);
    }

    /**
     * Призначений, для виведення колекції ананимів (анаграм). У вигляді
     * упорядкованної таблиці.
     *
     * @param ananymsCollection
     *            колекція ананимів (анаграм)
     */
    public void printAnanymsCollection(
            final AnanymsCollection ananymsCollection) {
        System.out.print("Result of searching ananyms in text:\n");
        System.out.format("%s Amount of ananyms: %d\n\n",
                this.getShellData().getTabCharacter(),
                ananymsCollection.size());

        System.out.println("Table of ananyms from text:");

        System.out.format(OutputCommand.ananymsTableHeader,
                this.getShellData().getTabCharacter(), "Word", "Reversed word");

        Iterator<Ananym> ananymIterator = ananymsCollection.getIterator();
        while (ananymIterator.hasNext()) {
            Ananym ananym = ananymIterator.next();
            System.out.format("%s | %-35s | %-35s |\n",
                    this.getShellData().getTabCharacter(), ananym.getWord(),
                    ananym.getReversedWord());
        }

        System.out.format(OutputCommand.ananymsTableFooter,
                this.getShellData().getTabCharacter());
    }

    /**
     * Призначений, для виведенння колекції слів. У вигляді упорядкованого
     * стовбця.
     *
     * @param wordsCollection
     *            колекція слів
     */
    public void printWordsCollection(final WordsCollection wordsCollection) {
        System.out.println("List of words loaded from text file:");

        Iterator<String> wordIterator = wordsCollection.getWordIterator();

        char capitalLetter = '-';
        boolean isFirstIteration = true;
        int wordsCount = 0;
        while (wordIterator.hasNext()) {
            String word = wordIterator.next();
            char currentCapitalLetter = word.charAt(0);

            if (wordsCount == OutputCommand.MAX_WORDS_IN_ONE_LINE) {
                System.out.format("\n%s\t ",
                        this.getShellData().getTabCharacter());
                wordsCount = 0;
            }
            ++wordsCount;

            if (capitalLetter != currentCapitalLetter) {
                capitalLetter = currentCapitalLetter;
                wordsCount = 0;
                if (!isFirstIteration) {
                    System.out.println();
                }
                System.out.format("%1$s [%2$c]: ",
                        this.getShellData().getTabCharacter(), capitalLetter);
                isFirstIteration = false;
            }

            System.out.format("%s  ", word);
        }

        System.out.println("\n");
    }

    @Override
    public void execute() {
        WordsCollection wordsCollection = this.getShellData()
                .getWordsCollection();
        AnanymsCollection ananymsColelction = this.getShellData()
                .getAnanymsCollection();

        if (!wordsCollection.isEmpty()) {
            this.printWordsCollection(wordsCollection);
        } else {
            System.out.print("Oops not found input words. "
                    + "Maybe you didn't load text file\n\n");
        }

        if (!ananymsColelction.isEmpty()) {
            this.printAnanymsCollection(
                    this.getShellData().getAnanymsCollection());
        } else {
            System.out.print(
                    "Oops, ananyms is not found or you didn't call processing"
                            + " command for text\n\n");
        }
    }
}
