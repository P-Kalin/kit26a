package ua.khpi.oop.malokhvii04.shell;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.ResourceBundle;
import java.util.Scanner;

import ua.khpi.oop.malokhvii04.shell.commands.AbstractCommandDecorator;
import ua.khpi.oop.malokhvii04.shell.commands.Command;

/**
 * Призначений, для обробки вхідних команд та контролювання даних інтерактивної
 * оболонки.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.2.0
 */
public final class Shell {

    /**
     * Призначений, для збереження єдиного екземпляру інтерактивної оболонки.
     *
     * @author malokhvii-eduard (malokhvii.ee@gmail.com)
     * @version 1.0.0
     */
    private static class SingletonHolder {

        /**
         * Єдиний екземпляр інтерактивної оболонки.
         *
         * @since 1.1.0
         */
        private static final Shell INSTANCE = new Shell();
    }

    /**
     * Розташування текстового файлу, з властивостями, а саме текстовими
     * рядками, для подальшого виведення в інтерактивній оболонці.
     *
     * @since 1.2.0
     */
    private static final String RESOURCE_BUNDLE_NAME = "shell.ShellBundle";

    /**
     * Призначений, для отримання єдиного екземпляру інтерактивної оболонки.
     *
     * @return єдиний екземпляр інтерактинвої оболнки
     * @since 1.1.0
     */
    public static Shell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * Обгортка над командами, для їх відлагодження.
     *
     * @since 1.1.0
     */
    private Class<? extends AbstractCommandDecorator> commandDecorator;

    /**
     * Історія виконаних команд.
     *
     * @since 1.1.0
     */
    private final Deque<Command> commandHistory;

    /**
     * Індекс поточної команди, тобто лічильник кількості виконаних команд.
     *
     * @since 1.1.0
     */
    private int currentCommandIndex;

    /**
     * Дані інтерактивної оболонки.
     *
     * @since 1.0.0
     */
    private final ShellData data;

    /**
     * Поточний стан інтерактивної оболонки.
     *
     * @since 1.1.0
     */
    private boolean isRunning;

    /**
     * Обгортка над потоком введення, для зручнішої обробки.
     *
     * @since 1.1.0
     */
    private final Scanner scanner;

    /**
     * Налаштування інтерактивної оболонки.
     *
     * @since 1.1.0
     */
    private ShellSettings settings;

    /**
     * Шлях розташування файлу серіалізації налаштувань інтерактивної оболонки.
     *
     * @since 1.1.0
     */
    private final String settingsPath = "settings.ser";

    /**
     * Приватний конструктор, для заборони створення декількох об'єктів
     * інтерактивної оболонки.
     *
     * @since 1.0.0
     */
    private Shell() {
        this.scanner = new Scanner(System.in);
        this.commandHistory = new ArrayDeque<Command>();
        this.data = new ShellData();
    }

    /**
     * Призначений, для отримання черги виконаних команд (тобто історії).
     *
     * @return черга виконаних команд
     * @since 1.1.0
     */
    public Deque<Command> getCommandHistory() {
        return this.commandHistory;
    }

    /**
     * Призначений, для отримання поточного індексу виконанної команди.
     *
     * @return індекс поточної виконанної команди
     * @since 1.1.0
     */
    public int getCurrentCommandIndex() {
        return this.currentCommandIndex;
    }

    /**
     * Призначений, для отримання посилання на об'єкт даних інтерактивної
     * оболонки.
     *
     * @return посилання на об'єкт даних інтерактивної оболонки
     * @since 1.0.0
     */
    public ShellData getData() {
        return this.data;
    }

    /**
     * Призначений, для отримання наступного ключа команди від користувача.
     *
     * @return наступний кдюч команди від користувача
     * @since 1.0.0
     */
    public String getNextCommand() {
        System.out.format("%s ", this.settings.getCommandCharacter());
        final String nextCommand = this.scanner.next();
        System.out.println();

        return nextCommand;
    }

    /**
     * Призначений, для отримання єдиного об'єкту сканера потоку введення.
     *
     * @return обгортка, для обробки потоку введення
     */
    public Scanner getScanner() {
        return this.scanner;
    }

    /**
     * Призначений, для отримання налаштувань інтерактивної оболонки.
     *
     * @return налаштування інтерактивної оболонки
     */
    public ShellSettings getSettings() {
        return this.settings;
    }

    /**
     * Призначений, для обробки останньої команди доданої до інтерактивної
     * оболонки.
     *
     * @since 1.0.0
     */
    public void handleCommand() {
        final Command command = this.commandHistory.peekFirst();
        if (command != null) {
            this.wrapCommand(command).execute();
        }
    }

    /**
     * Призначений, для обробки невідомої команди.
     *
     * @since 1.0.0
     */
    public void handleUnknownCommand() {
        final ResourceBundle resourceBundle = ShellResources.getInstance()
                .getResourceBundle(Shell.RESOURCE_BUNDLE_NAME);
        System.out.println(resourceBundle.getString("unknown-command-message"));
    }

    /**
     * Призначений, для отримання стану інтерактивної оболонки.
     *
     * @return стан роботи інтерактивної оболонки
     * @since 1.0.0
     */
    public boolean isRunning() {
        return this.isRunning;
    }

    /**
     * Призначений, для вмикання інтерактивної оболонки. Під час вмикання
     * десеріалізуються налаштування інтерактивної оболонки за змовчуванням.
     *
     * @since 1.0.0
     */
    public void launch() {
        this.launch(this.settingsPath);
    }

    /**
     * Призначений, для вмикання інтерактивної оболонки. Під час вмикання
     * десеріалізуються налаштування інтерактивної оболонки згідно отриманого
     * розташування файлу із серіалізованим об'єктом налаштувань.
     *
     * @param settingsPath
     *            розташування файлу із серіалізованим об'єктом налаштувань
     * @since 1.1.0
     */
    public void launch(final String settingsPath) {
        try {
            this.settings = ShellSettings.load(settingsPath);
        } catch (final Exception exception) {
            this.settings = ShellSettings.getDefaultInstance();
        }

        this.isRunning = true;
        final ResourceBundle resourceBundle = ShellResources.getInstance()
                .getResourceBundle(Shell.RESOURCE_BUNDLE_NAME);
        System.out.format(resourceBundle.getString("launch-message"),
                this.settings.getTabCharacter());
    }

    /**
     * Призначений, для додавання команди до історії команд для подальшого
     * виконання.
     *
     * @param command
     *            нова команда
     * @since 1.0.0
     */
    public void putCommand(final Command command) {
        if (this.commandHistory.size() >= this.settings
                .getCommandHistorySize()) {
            this.commandHistory.removeLast();
        }
        this.commandHistory.addFirst(command);
        this.currentCommandIndex++;
    }

    /**
     * Призначений, для встановлення нового декоратора для наступних команд.
     *
     * @param commandDecorator
     *            клас, декоратора для наступних команд
     * @since 1.1.0
     */
    public void setCommandDecorator(
            final Class<? extends AbstractCommandDecorator> commandDecorator) {
        this.commandDecorator = commandDecorator;
    }

    /**
     * Призначений, для вимикання інтерактивної оболонки.
     *
     * @since 1.0.0
     */
    public void terminate() {
        final ResourceBundle resourceBundle = ShellResources.getInstance()
                .getResourceBundle(Shell.RESOURCE_BUNDLE_NAME);
        System.out.println(resourceBundle.getString("terminate-message"));
        this.isRunning = false;

        try {
            ShellSettings.save(this.settings, this.settingsPath);
        } catch (final IOException exception) {
            System.out.println("Error: could not save settings");
        }
    }

    /**
     * Призначений, для обгорнення поточноъ команди декоратором для
     * відлагодження. Якщо декоратор не встановлено тоді об'єкт команди не
     * змінюється.
     *
     * @param command
     *            команда, для обгонення декоратором
     * @return команда обгорнена декоратором, для розширення її можливостей
     * @since 1.1.0
     */
    private Command wrapCommand(final Command command) {
        if (this.commandDecorator == null) {
            return command;
        }

        Constructor<? extends AbstractCommandDecorator> commandConstructor;
        try {
            commandConstructor = this.commandDecorator
                    .getConstructor(Command.class);
            return commandConstructor.newInstance(command);
        } catch (final Exception exception) {
            return command;
        }
    }
}
