package ua.khpi.oop.malokhvii04.shell.command;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import ua.khpi.oop.malokhvii03.text.Ananym;
import ua.khpi.oop.malokhvii04.shell.Shell;
import ua.khpi.oop.malokhvii04.shell.ShellData;

/**
 * Призначений, для інкапсуляції виведення оброблених та вхідних даних під
 * виглядом команди.
 *
 * @author malokhvii-eduard
 * @version 1.0.1
 * @see Shell
 * @see CommandFactory
 * @see AbstractCommand
 * @since 1.0.0
 */
public final class OutputCommand extends AbstractCommand {

    /**
     * Верхня частина таблиці результатів.
     *
     * @since 1.0.0
     */
    private static String ananymsTableHeader = "%1$s |"
            + "--------------------------------"
            + "-----|-------------------------------------|\n"
            + "%1$s | %2$-35s | %3$-35s |\n"
            + "%1$s |-------------------------------------"
            + "|-------------------------------------|\n";

    /**
     * Нижня частина таблиці результатів.
     *
     * @since 1.0.0
     */
    private static String ananymsTableFooter = "%1$s |-------------------"
            + "------------------|-------------------------------------|\n\n";

    /**
     * Ключи, для виклику команди із інтерактивної оболонки.
     *
     * @since 1.0.0
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
     * @since 1.0.0
     */
    public OutputCommand(final String id, final ShellData shellData) {
        super(id, shellData);
    }

    /**
     * Призначений, для виведення колекції ананимів (анаграм). У вигляді
     * упорядкованної таблиці.
     *
     * @param ananyms
     *            колекція ананимів (анаграм)
     * @since 1.0.0
     */
    public void printAnanymsCollection(final Collection<Ananym> ananyms) {
        System.out.print("Result of searching ananyms in text:\n");
        System.out.format("%s Amount of ananyms: %d\n\n",
                this.getShellData().getTabCharacter(), ananyms.size());

        System.out.println("Table of ananyms from text:");

        System.out.format(OutputCommand.ananymsTableHeader,
                this.getShellData().getTabCharacter(), "Word", "Reversed word");

        for (Ananym ananym : ananyms) {
            System.out.format("%s | %-35s | %-35s |\n",
                    this.getShellData().getTabCharacter(), ananym.getWord(),
                    ananym.getReversedWord());
        }

        System.out.format(OutputCommand.ananymsTableFooter,
                this.getShellData().getTabCharacter());
    }

    /**
     * Призначений, для виведенння колекції рядків.
     *
     * @param textLines
     *            колекція рядків
     * @since 1.0.0
     */
    public void printTextLines(final Collection<CharSequence> textLines) {
        System.out.println("Text lines loaded from file:");

        for (CharSequence textLine : textLines) {
            System.out.format("%1$s %2$s\n",
                    this.getShellData().getTabCharacter(), textLine.toString());
        }

        System.out.println("\n");
    }

    @Override
    public void execute() {
        Collection<CharSequence> textLines = this.getShellData().getTextLines();
        Collection<Ananym> ananyms = this.getShellData().getAnanyms();

        if (!textLines.isEmpty()) {
            this.printTextLines(textLines);
        } else {
            System.out.print("Oops not found input lines. "
                    + "Maybe you didn't load text file\n\n");
        }

        if (!ananyms.isEmpty()) {
            this.printAnanymsCollection(ananyms);
        } else {
            System.out.print(
                    "Oops, ananyms is not found or you didn't call processing"
                            + " command for text\n\n");
        }
    }
}
