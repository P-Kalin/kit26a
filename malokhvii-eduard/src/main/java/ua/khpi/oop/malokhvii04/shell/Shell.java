package ua.khpi.oop.malokhvii04.shell;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

import ua.khpi.oop.malokhvii04.shell.commands.AbstractCommandDecorator;
import ua.khpi.oop.malokhvii04.shell.commands.Command;

/**
 * Призначений, для обробки вхідних команд та контролювання даних інтерактивної
 * оболонки.
 *
 * @author malokhvii-eduard
 * @version 1.1.0
 * @since 1.0.0
 */
public final class Shell {

    /**
     * Призначений, для збереження єдиного екземпляру інтерактивної оболонки.
     *
     * @author malokhvii-eduard
     * @version 1.0.0
     * @since 1.1.0
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
    private Deque<Command> commandHistory;

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
    private ShellData data;

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
    private Scanner scanner;

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
    private String settingsPath = "settings.ser";

    /**
     * Приватний конструктор, для заборони створення декількох об'єктів
     * інтерактивної оболонки.
     *
     * @since 1.0.0
     */
    private Shell() {
        scanner = new Scanner(System.in);
        commandHistory = new ArrayDeque<Command>();
        data = new ShellData();
    }

    /**
     * Призначений, для отримання черги виконаних команд (тобто історії).
     *
     * @return черга виконаних команд
     * @since 1.1.0
     */
    public Deque<Command> getCommandHistory() {
        return commandHistory;
    }

    /**
     * Призначений, для отримання поточного індексу виконанної команди.
     *
     * @return індекс поточної виконанної команди
     * @since 1.1.0
     */
    public int getCurrentCommandIndex() {
        return currentCommandIndex;
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
        System.out.format("%s ", settings.getCommandCharacter());
        String nextCommand = scanner.next();
        System.out.println();

        return nextCommand;
    }

    /**
     * Призначений, для отримання єдиного об'єкту сканера потоку введення.
     *
     * @return обгортка, для обробки потоку введення
     */
    public Scanner getScanner() {
        return scanner;
    }

    /**
     * Призначений, для отримання налаштувань інтерактивної оболонки.
     *
     * @return налаштування інтерактивної оболонки
     */
    public ShellSettings getSettings() {
        return settings;
    }

    /**
     * Призначений, для обробки останньої команди доданої до інтерактивної
     * оболонки.
     *
     * @since 1.0.0
     */
    public void handleCommand() {
        Command command = commandHistory.peekFirst();
        if (command != null) {
            wrapCommand(command).execute();
        }
    }

    /**
     * Призначений, для обробки невідомої команди.
     *
     * @since 1.0.0
     */
    public void handleUnknownCommand() {
        Locale locale = LocaleDictionary.getInstance().getDefaultLocale();
        System.out.println(locale.getString("000x001"));
    }

    /**
     * Призначений, для отримання стану інтерактивної оболонки.
     *
     * @return стан роботи інтерактивної оболонки
     * @since 1.0.0
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * Призначений, для вмикання інтерактивної оболонки. Під час вмикання
     * десеріалізуються налаштування інтерактивної оболонки за змовчуванням.
     *
     * @since 1.0.0
     */
    public void launch() {
        launch(settingsPath);
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
            loadSettings(settingsPath);
        } catch (Exception exception) {
            isRunning = false;
            return;
        }

        isRunning = true;
        Locale locale = LocaleDictionary.getInstance().getDefaultLocale();
        System.out.format(locale.getString("000x000"),
                settings.getTabCharacter());
    }

    /**
     * Призначений, для завантаження усіх назв класів команд отриманих із
     * поточного об'єкту налаштувань інтерактивної оболнки.
     *
     * @since 1.1.0
     */
    private void loadCommands() {
        for (String command : settings.getCommandsNames()) {
            try {
                Class.forName(command);
            } catch (ClassNotFoundException e) {

            }
        }
    }

    /**
     * Призначений, для десеріалізації об'єкту налаштувань інтерактивної
     * консолі.
     *
     * @param filePath
     *            шлях, до результату серіалізації об'єкту налаштувань
     *            інтерактивної оболонки
     * @since 1.1.0
     */
    private void loadSettings(final String filePath) {
        try {
            settings = ShellSettings.load(filePath);
        } catch (Exception exception) {
            settings = ShellSettings.getDefaultSettings();
        }
        LocaleDictionary.getInstance().loadLocales(settings);
        loadCommands();
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
        if (commandHistory.size() >= this.settings.getCommandHistorySize()) {
            commandHistory.removeLast();
        }
        commandHistory.addFirst(command);
        currentCommandIndex++;
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
        Locale locale = LocaleDictionary.getInstance().getDefaultLocale();
        System.out.println(locale.getString("000x002"));
        isRunning = false;

        try {
            ShellSettings.save(settings, settingsPath);
        } catch (IOException exception) {
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
        if (commandDecorator == null) {
            return command;
        }

        Constructor<? extends AbstractCommandDecorator> commandConstructor;
        try {
            commandConstructor = commandDecorator.getConstructor(Command.class);
            return commandConstructor.newInstance(command);
        } catch (Exception exception) {
            return command;
        }
    }
}
