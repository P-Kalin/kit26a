package ua.khpi.oop.malokhvii04.shell.command;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import ua.khpi.oop.malokhvii04.shell.Shell;
import ua.khpi.oop.malokhvii04.shell.ShellData;
import ua.khpi.oop.malokhvii05.util.Array;

/**
 * Призначений, для інкапсуляції введення шляху до файлу для подальшої обробки
 * даних під виглядом команди.
 *
 * @author malokhvii-eduard
 * @version 1.0.1
 * @see Shell
 * @see CommandFactory
 * @see AbstractCommand
 * @since 1.0.0
 */
public final class InputCommand extends AbstractCommand {

    /**
     * Регулярний вираз для перевірки шляху до файлу.
     *
     * @since 1.0.0
     */
    private static final String FILE_PATH_PATTERN = "([A-Z|a-z]:\\\\[^"
            + "*|\"<>?\\n]*)|(\\\\\\\\.*?\\\\.*)";

    /**
     * Ключи, для виклику команди із інтерактивної оболонки.
     *
     * @since 1.0.0
     */
    private static List<String> keys = Arrays.asList("-input", "-i");

    static {
        CommandFactory.registerCommand(InputCommand.keys, InputCommand.class);
        Shell.registerCommandDescription(String.join(" ", InputCommand.keys),
                "Input of input data in the form of text files");
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
    public InputCommand(final String id, final ShellData shellData) {
        super(id, shellData);
    }

    @Override
    public void execute() {
        Collection<CharSequence> lines = null;
        String filePath = this.getFilePath();

        if (filePath == null) {
            return;
        }

        try {
            lines = this.getInputText(filePath);
        } catch (IOException exception) {
            System.out.format(
                    "\nThe system cannot find the file with specified path"
                            + ":\n%s %s\n\n",
                    this.getShellData().getTabCharacter(), filePath);
            return;
        }

        this.getShellData().setTextLines((Array<CharSequence>) lines);
        System.out.println();
    }

    /**
     * Призначений, для отримання шляху до файлу шляхом введення, та перевірки
     * його на коректність за допомгою регулярного виразу
     * {@value #FILE_PATH_PATTERN}.
     *
     * @return шлях до файлу
     * @since 1.0.0
     */
    public String getFilePath() {
        Scanner shellScanner = this.getShellData().getShellScanner();

        String filePath = "";
        boolean isCorrectFilePath = false;
        System.out.format("Please, enter file path with input text:\n%s ",
                this.getShellData().getTabCharacter());

        filePath = shellScanner.next();
        isCorrectFilePath = filePath.matches(InputCommand.FILE_PATH_PATTERN);
        if (!isCorrectFilePath) {
            System.out.format(
                    "\nInput file path mismatch with input pattern:\n%1$s"
                            + " Pattern: %2$s\n\n",
                    this.getShellData().getTabCharacter(),
                    InputCommand.FILE_PATH_PATTERN);
            return null;
        }
        shellScanner.nextLine();

        return filePath;
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
    public Collection<CharSequence> getInputText(final String filePath)
            throws IOException {
        Array<CharSequence> lines = new Array<CharSequence>();

        FileInputStream fileStream = null;
        BufferedReader bufferReader = null;
        try {
            fileStream = new FileInputStream(filePath);
            bufferReader = new BufferedReader(
                    new InputStreamReader(fileStream));

            String strLine;
            while ((strLine = bufferReader.readLine()) != null) {
                lines.add(strLine);
            }
        } catch (IOException exception) {
            throw exception;
        } finally {
            try {
                if (bufferReader != null) {
                    bufferReader.close();
                }
            } catch (IOException exception) {
            }
        }

        return lines;
    }
}
