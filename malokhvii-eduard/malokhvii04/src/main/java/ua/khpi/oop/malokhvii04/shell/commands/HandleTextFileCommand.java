package ua.khpi.oop.malokhvii04.shell.commands;

import java.util.ResourceBundle;
import java.util.Scanner;

import ua.khpi.oop.malokhvii04.shell.Shell;
import ua.khpi.oop.malokhvii04.shell.ShellResources;

/**
 * Призначений, для оголошення загальної реалізації, для усіх команд, які
 * потребують введення шляху до файлу.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 */
public abstract class HandleTextFileCommand extends AbstractCommand {

    /**
     * Регулярний вираз для перевірки шляху до файлу.
     *
     * @since 1.0.0
     */
    protected static final String FILE_PATH_PATTERN = "([A-Z|a-z]:\\\\[^"
            + "*|\"<>?\\n]*)|(\\\\\\\\.*?\\\\.*)";

    /**
     * Розташування текстового файлу, з властивостями, а саме текстовими
     * рядками, для подальшого виведення в інтерактивній оболонці.
     *
     * @since 1.2.0
     */
    private static final String RESOURCE_BUNDLE_NAME = "shell.commands."
            + "HandleTextFileCommandBundle";

    /**
     * Загальний об'єкт ресурсів для локалізації усіх команд.
     *
     * @since 1.2.0
     */
    private static ResourceBundle resourceBundle;

    static {
        HandleTextFileCommand.updateResourceBundle();
    }

    /**
     * Призначений, для отримання шляху до файлу шляхом введення, та перевірки
     * його на коректність за допомгою регулярного виразу
     * {@link #FILE_PATH_PATTERN}.
     *
     * @return шлях до файлу
     * @since 1.0.0
     */
    protected static String getFilePath() {
        final Scanner shellScanner = Shell.getInstance().getScanner();

        String filePath = null;
        final String tabCharacter = Shell.getInstance().getSettings()
                .getTabCharacter();

        boolean isCorrectFilePath = false;
        System.out.format(HandleTextFileCommand.resourceBundle
                .getString("enter-file-path-message"), tabCharacter);

        filePath = shellScanner.next();

        isCorrectFilePath = filePath
                .matches(HandleTextFileCommand.FILE_PATH_PATTERN);
        if (!isCorrectFilePath) {
            System.out.format(
                    HandleTextFileCommand.resourceBundle
                            .getString("mismatch-message"),
                    tabCharacter, HandleTextFileCommand.FILE_PATH_PATTERN);
            return null;
        }

        shellScanner.nextLine();
        return filePath;
    }

    /**
     * Призначений, для оновлення усіх статичних полів, які залежать від
     * поточної локалізації.
     *
     * @since 1.0.0
     */
    protected static void updateResourceBundle() {
        HandleTextFileCommand.resourceBundle = ShellResources.getInstance()
                .getResourceBundle(HandleTextFileCommand.RESOURCE_BUNDLE_NAME);
    }
}
