package ua.khpi.oop.malokhvii04.shell.command;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import ua.khpi.oop.malokhvii03.text.Anagrams;
import ua.khpi.oop.malokhvii03.text.Ananym;
import ua.khpi.oop.malokhvii04.shell.Shell;
import ua.khpi.oop.malokhvii04.shell.ShellData;
import ua.khpi.oop.malokhvii05.util.Array;

/**
 * Призначений, для інкапсуляції обчислень вхідних даних під виглядом команди.
 *
 * @author malokhvii-eduard
 * @version 1.0.1
 * @see Shell
 * @see CommandFactory
 * @see AbstractCommand
 * @since 1.0.0
 */
public final class ProcessCommand extends AbstractCommand {

    /**
     * Ключи, для виклику команди із інтерактивної оболонки.
     *
     * @since 1.0.0
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
     * @since 1.0.0
     */
    public ProcessCommand(final String id, final ShellData shellData) {
        super(id, shellData);
    }

    @Override
    public void execute() {
        Collection<CharSequence> textLines = this.getShellData().getTextLines();
        if (textLines != null && !textLines.isEmpty()) {
            this.getShellData().getAnanyms().clear();

            this.getShellData().setAnanyms((Array<Ananym>) Anagrams
                    .findAllAnanyms(textLines, Anagrams.DEFAUL_WORD_PATTERN));
        } else {
            System.out.println("Oops not found words for searching ananyms."
                    + " Maybe you didn't load text file\n");
        }
    }
}
