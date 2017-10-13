package ua.khpi.oop.malokhvii04.shell;

import java.util.Deque;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import ua.khpi.oop.malokhvii04.shell.command.Command;

/**
 * Призначений, для обробки вхідних команд та контролювання даних інтерактивної
 * оболонки.
 *
 * @author malokhvii-eduard
 * @version 1.0.0
 * @since 1.0.0
 */
public final class Shell {

    /**
     * Описи зарегестрованих команд.
     *
     * @since 1.0.0
     */
    private static Map<String, String> commandsDescription;

    /**
     * Дані інтерактивної оболонки.
     *
     * @since 1.0.0
     */
    private ShellData shellData;

    static {
        commandsDescription = new TreeMap<String, String>();
    }

    /**
     * Призначений, для ініціалізації інтерактивної оболонки.
     *
     * @param shellData
     *            посилання на дані інтерактивної оболонки
     * @since 1.0.0
     */
    public Shell(final ShellData shellData) {
        this.shellData = shellData;
    }

    /**
     * Призначений, для виведення початкового повідомлення.
     *
     * @since 1.0.0
     */
    public void printLaunchMessage() {
        System.out.format("Shell was launched\n"
                + "%1$s Description: Used to find in the text ananyms,"
                + " such as \"def\" - \"fed\"\n"
                + "%1$s An ananym is a word whose spelling is derived by"
                + " reversing the spelling\n"
                + "%1$s of another word. It is therefore"
                + " a special type of anagram\n\n",
                this.shellData.getTabCharacter());
    }

    /**
     * Призначений, для виведення кінцевого повідомлення.
     *
     * @since 1.0.0
     */
    public void printTerminateMessage() {
        System.out.print("Shell was terminated");
    }

    /**
     * Призначений, для отримання стану інтерактивної оболонки.
     *
     * @return стан роботи інтерактивної оболонки
     * @since 1.0.0
     */
    public boolean isRunning() {

        return this.shellData.isRunning();
    }

    /**
     * Призначений, для вмикання інтерактивної оболонки.
     *
     * @since 1.0.0
     */
    public void launch() {
        this.printLaunchMessage();
        this.shellData.setRunning(true);
    }

    /**
     * Призначений, для вимикання інтерактивної оболонки.
     *
     * @since 1.0.0
     */
    public void terminate() {
        this.printTerminateMessage();
        this.shellData.setRunning(false);
    }

    /**
     * Призначений, для обробки останньої команди доданої до інтерактивної
     * оболонки.
     *
     * @since 1.0.0
     */
    public void handleCommand() {
        Deque<Command> commandHistory = this.shellData.getCommandHistory();
        Command command = commandHistory.peekFirst();
        if (command != null) {
            this.shellData.wrapCommandWithDebug(command).execute();
        }
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
        Deque<Command> commandHistory = this.shellData.getCommandHistory();
        if (commandHistory.size() >= this.shellData.getCommandHistorySize()) {
            commandHistory.removeLast();
        }
        commandHistory.addFirst(command);
        this.shellData.incrementCurrentCommandIndex();
    }

    /**
     * Призначений, для отримання посилання на об'єкт даних інтерактивної
     * оболонки.
     *
     * @return посилання на об'єкт даних інтерактивної оболонки
     * @since 1.0.0
     */
    public ShellData getShellData() {
        return this.shellData;
    }

    /**
     * Призначений, для оновлення посилання на об'єет даних інтерактивної
     * оболонки.
     *
     * @param shellData
     *            посилання на об'єкт даних інтерактивної оболонки
     * @since 1.0.0
     */
    public void setShellData(final ShellData shellData) {
        this.shellData = shellData;
    }

    /**
     * Призначений, для отримання наступного ключа команди від користувача.
     *
     * @return наступний кдюч команди від користувача
     * @since 1.0.0
     */
    public String getNextCommand() {
        Scanner shellScanner = this.shellData.getShellScanner();
        String commandCharacter = this.shellData.getCommandCharacter();

        System.out.format("%s ", commandCharacter);
        String nextCommand = shellScanner.next();
        System.out.println();

        return nextCommand;
    }

    /**
     * Призначений, для обробки невідомої команди.
     *
     * @since 1.0.0
     */
    public void handleUnknownCommand() {
        System.out.println(
                "Unknown command, use -help, -h to see command list\n");
    }

    /**
     * Призначений, для отримання посилання на колекцію довідок для команд.
     *
     * @return посилання на колекцію довідок для команд
     * @since 1.0.0
     */
    public static Map<String, String> getCommandsDescription() {
        return Shell.commandsDescription;
    }

    /**
     * Призначений, для регестрації довідки для команди.
     *
     * @param keys
     *            ключи для команди
     * @param description
     *            опис команди
     * @since 1.0.0
     */
    public static void registerCommandDescription(final String keys,
            final String description) {
        Shell.commandsDescription.put(keys, description);
    }
}
