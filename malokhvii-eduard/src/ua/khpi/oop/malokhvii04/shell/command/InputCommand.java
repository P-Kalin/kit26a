package ua.khpi.oop.malokhvii04.shell.command;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import ua.khpi.oop.malokhvii03.text.WordsCollection;
import ua.khpi.oop.malokhvii04.shell.Shell;
import ua.khpi.oop.malokhvii04.shell.ShellData;

/**
 * Призначений, для інкапсуляції введення шляху до файлу для подальшої обробки
 * даних під виглядом команди.
 *
 * @author malokhvii-ee
 * @version 1.0.0
 * @see Shell
 * @see CommandFactory
 * @see AbstractCommand
 */
public final class InputCommand extends AbstractCommand {

    /**
     * Регулярний вираз для виділення слова із тексту.
     */
    private static final String WORD_PATTERN = "[\\s\\d\\p{Punct}]";

    /**
     * Регулярний вираз для перевірки шляху до файлу.
     */
    private static final String FILE_PATH_PATTERN = "([A-Z|a-z]:\\\\[^"
            + "*|\"<>?\\n]*)|(\\\\\\\\.*?\\\\.*)";

    /**
     * Ключи, для виклику команди із інтерактивної оболонки.
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
     */
    public InputCommand(final String id, final ShellData shellData) {
        super(id, shellData);
    }

    /**
     * Призначений, для отримання шляху до файлу шляхом введення, та перевірки
     * його на коректність за допомгою регулярного виразу
     * {@value #FILE_PATH_PATTERN}.
     *
     * @return шлях до файлу
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
     */
    public List<String> getInputText(final String filePath) throws IOException {
        List<String> lines = new ArrayList<String>();

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

    @Override
    public void execute() {
        List<String> lines = null;
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

        WordsCollection wordsCollection = this.getShellData()
                .getWordsCollection();
        wordsCollection.clear();
        this.getShellData().getAnanymsCollection().clear();

        for (String line : lines) {
            String[] words = line.split(InputCommand.WORD_PATTERN);
            for (String word : words) {
                if (word.length() > 1) {
                    wordsCollection.putWord(word);
                }
            }
        }

        System.out.println();
    }
}
