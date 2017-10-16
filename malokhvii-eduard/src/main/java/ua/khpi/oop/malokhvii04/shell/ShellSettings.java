package ua.khpi.oop.malokhvii04.shell;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;

import ua.khpi.oop.malokhvii05.util.Array;

/**
 * Призначений, для збереження загальних параметрів інтерактивної оболонки. для
 * подальшого повторного використання під час використання інтерактивної
 * оболонки.
 *
 * @author malokhvii-eduard
 * @version 1.0.0
 * @since 1.1.0
 */
public final class ShellSettings implements Serializable {

    /**
     * Номер версії класу, для серіалізації / десеріалізації.
     *
     * @since 1.0.0
     */
    private static final long serialVersionUID = -4336428240881604431L;

    /**
     * Призначений, для десеріалізації об'єкту налаштувань для інтерактивної
     * оболонки.
     *
     * @param filePath
     *            назва файлу серіалізації
     * @return десеріалізований об'єкт налаштувань для інтерактивної оболонки
     * @throws IOException
     *             помилка, під час зчитування файлу
     * @throws ClassNotFoundException
     *             помилка, під час десеріалізації об'єкту
     * @since 1.0.0
     */
    public static ShellSettings load(final String filePath)
            throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream(filePath);
            objectInputStream = new ObjectInputStream(fileInputStream);
            return (ShellSettings) objectInputStream.readObject();
        } finally {
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {

                }
            }
        }
    }

    public static ShellSettings getDefaultSettings() {
        ShellSettings defaultShellSettings = new ShellSettings();
        defaultShellSettings.setCommandCharacter(">>>");
        defaultShellSettings.setCommandHistorySize(10);
        defaultShellSettings.setDefaultLocale("en-En");
        defaultShellSettings.setTabCharacter("...");
        defaultShellSettings.setLocalesFolder("locale\\");

        defaultShellSettings.setCommandsNames(new Array<String>(Arrays.asList(
                "ua.khpi.oop.malokhvii04.shell.commands.InputCommand",
                "ua.khpi.oop.malokhvii04.shell.commands.ExitCommand",
                "ua.khpi.oop.malokhvii04.shell.commands.HelpCommand",
                "ua.khpi.oop.malokhvii04.shell.commands.HistoryCommand",
                "ua.khpi.oop.malokhvii04.shell.commands.DebugCommand",
                "ua.khpi.oop.malokhvii04.shell.commands.NoDebugCommand",
                "ua.khpi.oop.malokhvii06.commands.SearchAnanymsCommand")));

        return defaultShellSettings;
    }

    /**
     * Призначений, для серіалізації поточного стану об'єкту налаштувань для
     * інтерактивної оболонки.
     *
     * @param shellSettings
     *            налаштування інтерактивної оболонки
     * @param filePath
     *            назва файлу серіалізації
     * @throws IOException
     *             помилка, під час запису файлу
     * @since 1.0.0
     */
    public static void save(final ShellSettings shellSettings,
            final String filePath) throws IOException {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(filePath);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(shellSettings);
        } finally {
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {

                }
            }
        }
    }

    /**
     * Символ виділення команди.
     *
     * @since 1.0.0
     */
    private String commandCharacter;

    /**
     * Розмір історії команд, для відображення в інтерактивній оболонці.
     *
     * @since 1.0.0
     */
    private int commandHistorySize;

    /**
     * Перелік класів команд, для подальшого завантаження в інтерактивній
     * оболонці.
     *
     * @since 1.0.0
     */
    private Array<String> commandsNames;

    /**
     * Назва об'єкту локалізації за змовчуванням.
     *
     * @since 1.0.0
     */
    private String defaultLocale;

    /**
     * Розташування директорії з серіалізованими об'єктами різноманітних
     * перекладів інтерактивної оболонки.
     *
     * @since 1.0.0
     */
    private String localesFolder;

    /**
     * Символ табуляції.
     *
     * @since 1.0.0
     */
    private String tabCharacter;

    /**
     * Призначений, для отримання символу виділення команд.
     *
     * @return символ виділення команд
     * @since 1.0.0
     */
    public String getCommandCharacter() {
        return commandCharacter;
    }

    /**
     * Призначений, для отримання допустимого розміру історії команд в
     * інтерактивній оболонці.
     *
     * @return допустимий розмір історії команд в інтерактивній оболонці
     * @since 1.0.0
     */
    public int getCommandHistorySize() {
        return commandHistorySize;
    }

    /**
     * Призначений, для отримання назв класів команд, для подальшого
     * завантаження в інтерактивній оболонці.
     *
     * @return назви класів команд, для подальшого завантаження в інтерактивній
     *         оболонці
     * @since 1.0.0
     */
    public Array<String> getCommandsNames() {
        return commandsNames;
    }

    /**
     * Призначений, для отримання назви об'єкту локалізації за змовчуванням.
     *
     * @return назва об'єкту локалізації за змовчуванням
     * @since 1.0.0
     */
    public String getDefaultLocale() {
        return defaultLocale;
    }

    /**
     * Призначений, для отримання розташування директорії з серіалізованими
     * об'єктами різноманітних перекладів інтерактивної оболонки.
     *
     * @return розташування директорії з серіалізованими об'єктами різноманітних
     *         перекладів інтерактивної оболонки
     * @since 1.0.0
     */
    public String getLocalesFolder() {
        return localesFolder;
    }

    /**
     * Призначений, для отримання символу табуляції.
     *
     * @return символ табуляції
     * @since 1.0.0
     */
    public String getTabCharacter() {
        return tabCharacter;
    }

    /**
     * Призначений, для оновлення символу виділення команд.
     *
     * @param commandCharacter
     *            новий символ виділення команд
     * @since 1.0.0
     */
    public void setCommandCharacter(final String commandCharacter) {
        this.commandCharacter = commandCharacter;
    }

    /**
     * Призначений, для оновлення допустимого розміру історії команд в
     * інтерактивній оболонці.
     *
     * @param commandHistorySize
     *            новий допустимий розмір історії команд в інтерактивній
     *            оболонці
     * @since 1.0.0
     */
    public void setCommandHistorySize(final int commandHistorySize) {
        this.commandHistorySize = commandHistorySize;
    }

    /**
     * Призначений, для оновлення назв класів команд, для подальшого
     * завантаження в інтерактивній оболонці.
     *
     * @param commandsNames
     *            нові назви класів команд, для подальшого завантаження в
     *            інтерактивній оболонці
     * @since 1.0.0
     */
    public void setCommandsNames(final Array<String> commandsNames) {
        this.commandsNames = commandsNames;
    }

    /**
     * Призначений, для оновлення назви об'єкту локалізації за змовчуванням.
     *
     * @param defaultLocale
     *            нова назва об'єкту локалізації за змовчуванням
     * @since 1.0.0
     */
    public void setDefaultLocale(final String defaultLocale) {
        this.defaultLocale = defaultLocale;
    }

    /**
     * Призначений, для оновлення розташування директорії з серіалізованими
     * об'єктами різноманітних перекладів інтерактивної оболонки.
     *
     * @param localesFolder
     *            нове розташування директорії з серіалізованими об'єктами
     *            різноманітних перекладів інтерактивної оболонки
     * @since 1.0.0
     */
    public void setLocalesFolder(final String localesFolder) {
        this.localesFolder = localesFolder;
    }

    /**
     * Призначений, для оновлення символу табуляції.
     *
     * @param tabCharacter
     *            новий символ табуляції
     * @since 1.0.0
     */
    public void setTabCharacter(final String tabCharacter) {
        this.tabCharacter = tabCharacter;
    }
}
