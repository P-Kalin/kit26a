package ua.khpi.oop.malokhvii04.shell;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ResourceBundle;

/**
 * Призначений, для збереження загальних параметрів інтерактивної оболонки. для
 * подальшого повторного використання під час використання інтерактивної
 * оболонки.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.1
 */
public final class ShellSettings implements Serializable {

    /**
     * Номер версії класу, для серіалізації / десеріалізації.
     *
     * @since 1.0.0
     */
    private static final long serialVersionUID = -4336428240881604431L;

    /**
     * Розташування текстового файлу, з властивостями, а саме текстовими
     * рядками, для ініціалізації об'єкту налаштувань параметрами за
     * змовчуванням.
     *
     * @since 1.0.0
     */
    private static final String RESOURCE_BUNDLE_NAME = "shell."
            + "ShellSettingsBundle";

    /**
     * Максимально допустимий розмір історії команд.
     *
     * @since 1.0.1
     */
    private static final int MAX_COMMAND_HISTORY_SIZE = 99;

    /**
     * Максимально допустима довжина рядків для виділення відступів та команд.
     *
     * @since 1.0.1
     */
    private static final int MAX_CHARACTER_SIZE = 5;

    /**
     * Призначений, для отримання об'єкту налаштувань з параметрами за
     * змовчуванням. Використовується у випадках коли неможливо завантажити
     * попередні налаштування інтерактивної оболонки.
     *
     * @return об'єкт налаштувань за змовчуванням
     * @since 1.0.0
     */
    public static ShellSettings getDefaultInstance() {
        final ResourceBundle resourceBundle = ShellResources.getInstance()
                .getResourceBundle(ShellSettings.RESOURCE_BUNDLE_NAME);

        final ShellSettings defaultShellSettings = new ShellSettings();
        defaultShellSettings.setCommandCharacter(
                resourceBundle.getString("default-command-character"));
        defaultShellSettings.setCommandHistorySize(Integer.parseInt(
                resourceBundle.getString("default-command-history-size")));
        defaultShellSettings
                .setDefaultLocale(resourceBundle.getString("default-locale"));
        defaultShellSettings.setTabCharacter(
                resourceBundle.getString("default-tab-character"));

        return defaultShellSettings;
    }

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
        try (ObjectInputStream objectInputStream = new ObjectInputStream(
                new FileInputStream(filePath))) {
            return (ShellSettings) objectInputStream.readObject();
        } catch (final Exception exception) {
            throw exception;
        }
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
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream(filePath))) {
            objectOutputStream.writeObject(shellSettings);
        } catch (final Exception exception) {
            throw exception;
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
     * Назва об'єкту локалізації за змовчуванням.
     *
     * @since 1.0.0
     */
    private String defaultLocale;

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
        return this.commandCharacter;
    }

    /**
     * Призначений, для отримання допустимого розміру історії команд в
     * інтерактивній оболонці.
     *
     * @return допустимий розмір історії команд в інтерактивній оболонці
     * @since 1.0.0
     */
    public int getCommandHistorySize() {
        return this.commandHistorySize;
    }

    /**
     * Призначений, для отримання назви об'єкту локалізації за змовчуванням.
     *
     * @return назва об'єкту локалізації за змовчуванням
     * @since 1.0.0
     */
    public String getDefaultLocale() {
        return this.defaultLocale;
    }

    /**
     * Призначений, для отримання символу табуляції.
     *
     * @return символ табуляції
     * @since 1.0.0
     */
    public String getTabCharacter() {
        return this.tabCharacter;
    }

    /**
     * Призначений, для оновлення символу виділення команд.
     *
     * @param commandCharacter
     *            новий символ виділення команд
     * @throws IllegalArgumentException
     *             розмір рядку для виділення команд не відповідає допустимому
     *             розміру
     * @since 1.0.0
     */
    public void setCommandCharacter(final String commandCharacter)
            throws IllegalArgumentException {
        if (!commandCharacter.isEmpty() && commandCharacter
                .length() <= ShellSettings.MAX_CHARACTER_SIZE) {
            this.commandCharacter = commandCharacter;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Призначений, для оновлення допустимого розміру історії команд в
     * інтерактивній оболонці.
     *
     * @param commandHistorySize
     *            новий допустимий розмір історії команд в інтерактивній
     *            оболонці
     * @throws IllegalArgumentException
     *             розмір історії команд не відповідає допустимому значенню
     * @since 1.0.0
     */
    public void setCommandHistorySize(final int commandHistorySize)
            throws IllegalArgumentException {
        if (commandHistorySize > 0
                && commandHistorySize <= ShellSettings.MAX_COMMAND_HISTORY_SIZE) {
            this.commandHistorySize = commandHistorySize;
        } else {
            throw new IllegalArgumentException();
        }
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
     * Призначений, для оновлення символу табуляції.
     *
     * @param tabCharacter
     *            новий символ табуляції
     * @throws IllegalArgumentException
     *             розмір рядку для текстових відступів не відповідає
     *             допустимому розміру
     * @since 1.0.0
     */
    public void setTabCharacter(final String tabCharacter)
            throws IllegalArgumentException {
        if (!tabCharacter.isEmpty()
                && tabCharacter.length() <= ShellSettings.MAX_CHARACTER_SIZE) {
            this.tabCharacter = tabCharacter;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
