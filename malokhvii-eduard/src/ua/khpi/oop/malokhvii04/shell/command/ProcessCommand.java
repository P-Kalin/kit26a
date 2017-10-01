package ua.khpi.oop.malokhvii04.shell.command;

import java.util.Arrays;
import java.util.List;

import ua.khpi.oop.malokhvii03.text.Anagrams;
import ua.khpi.oop.malokhvii03.text.WordsCollection;
import ua.khpi.oop.malokhvii04.shell.Shell;
import ua.khpi.oop.malokhvii04.shell.ShellData;

/**
 * Призначений, для інкапсуляції обчислень вхідних даних під виглядом команди.
 *
 * @author malokhvii-ee
 * @version 1.0.0
 * @see Shell
 * @see CommandFactory
 * @see AbstractCommand
 */
public final class ProcessCommand extends AbstractCommand {

    /**
     * Ключи, для виклику команди із інтерактивної оболонки.
     */
    private static List<String> keys = Arrays.asList("-process", "-p");

    static {
        CommandFactory.registerCommand(ProcessCommand.keys,
                ProcessCommand.class);
        Shell.registerCommandDescription(String.join(" ", ProcessCommand.keys),
                "Search anagrams in the indexed input text");
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
    public ProcessCommand(final String id, final ShellData shellData) {
        super(id, shellData);
    }

    @Override
    public void execute() {
        WordsCollection wordsCollection = this.getShellData()
                .getWordsCollection();
        if (wordsCollection != null && !wordsCollection.isEmpty()) {
            this.getShellData().getAnanymsCollection().clear();
            this.getShellData().setAnanymsCollection(
                    Anagrams.findAllAnanyms(wordsCollection));
        } else {
            System.out.println("Oops not found words for searching ananyms."
                    + " Maybe you didn't load text file\n");
        }
    }
}
