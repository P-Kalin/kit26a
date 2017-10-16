package ua.khpi.oop.malokhvii04.shell.commands;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Observable;
import java.util.Scanner;

import ua.khpi.oop.malokhvii04.shell.Locale;
import ua.khpi.oop.malokhvii04.shell.LocaleDictionary;
import ua.khpi.oop.malokhvii04.shell.Shell;
import ua.khpi.oop.malokhvii04.shell.ShellCommandsPool;
import ua.khpi.oop.malokhvii05.util.Array;

/**
 * Призначений, для інкапсуляції введення шляху до файлу для подальшої обробки
 * даних під виглядом команди.
 *
 * @author malokhvii-eduard
 * @version 1.1.0
 * @see Shell
 * @see ShellCommandsPool
 * @see AbstractCommand
 * @since 1.0.0
 */
public final class InputCommand extends AbstractCommand {

    /**
     * Детальний опис команди.
     *
     * @since 1.1.0
     */
    private static String description;

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
        ShellCommandsPool.registerCommand(keys, InputCommand.class);
        Locale locale = LocaleDictionary.getInstance().getDefaultLocale();
        description = locale.getString("001x000");
    }

    /**
     * Призначений, для отримання шляху до файлу шляхом введення, та перевірки
     * його на коректність за допомгою регулярного виразу
     * {@value #FILE_PATH_PATTERN}.
     *
     * @param locale
     *            об'єкт локалізації
     * @return шлях до файлу
     * @since 1.0.0
     */
    private static String getFilePath(final Locale locale) {
        Scanner shellScanner = Shell.getInstance().getScanner();

        String filePath = null;
        String tabCharacter = Shell.getInstance().getSettings()
                .getTabCharacter();

        boolean isCorrectFilePath = false;
        System.out.format(locale.getString("001x001"), tabCharacter);

        filePath = shellScanner.next();

        isCorrectFilePath = filePath.matches(InputCommand.FILE_PATH_PATTERN);
        if (!isCorrectFilePath) {
            System.out.format(locale.getString("001x002"), tabCharacter,
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
    private static Array<String> getInputText(final String filePath)
            throws IOException {
        Array<String> lines = new Array<String>();

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
        Locale locale = LocaleDictionary.getInstance().getDefaultLocale();

        Collection<String> lines = null;
        String filePath = getFilePath(locale);

        if (filePath == null) {
            return;
        }

        try {
            lines = getInputText(filePath);
        } catch (IOException exception) {
            System.out.format(locale.getString("001x003"),
                    Shell.getInstance().getSettings().getTabCharacter(),
                    filePath);
            return;
        }

        Shell.getInstance().getData().setTextLines((Array<String>) lines);
        System.out.println();
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public List<String> getKeys() {
        return keys;
    }

    @Override
    public void update(final Observable observable, final Object object) {
        Locale locale = (Locale) object;
        description = locale.getString("001x000");
    }
}
