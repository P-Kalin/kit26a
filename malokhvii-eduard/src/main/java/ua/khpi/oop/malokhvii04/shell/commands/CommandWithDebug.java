package ua.khpi.oop.malokhvii04.shell.commands;

import ua.khpi.oop.malokhvii04.shell.Locale;
import ua.khpi.oop.malokhvii04.shell.LocaleDictionary;
import ua.khpi.oop.malokhvii04.shell.Shell;

/**
 * Призначений, для декорування об'єктів команд відладочною інформацією.
 *
 * @author malokhvii-eduard
 * @version 1.1.0
 * @see Shell
 * @see AbstractCommand
 * @since 1.0.0
 */
public final class CommandWithDebug extends AbstractCommandDecorator {

    /**
     * Призначений, для виведення інформації про клас.
     *
     * @param command
     *            об'єкт команди
     * @param locale
     *            об'єкт локалізації
     * @param tabCharacter
     *            символ табуляції
     * @since 1.0.0
     */
    private static void printClassInfo(final Command command,
            final Locale locale, final String tabCharacter) {
        Class<? extends Command> commandClass = command.getClass();
        Class<? extends ClassLoader> commandClassLoader = commandClass
                .getClassLoader().getClass();

        System.out.format(locale.getString("12x004"), tabCharacter,
                commandClass.getSimpleName());
        System.out.format(locale.getString("12x005"), tabCharacter,
                commandClass.getCanonicalName());
        System.out.format(locale.getString("12x006"), tabCharacter,
                commandClassLoader.getName());
    }

    /**
     * Призначений, для виведення інформації про пакет, клас комани з якого
     * використано.
     *
     * @param command
     *            об'єкт команди
     * @param locale
     *            об'єкт локалізації
     * @param tabCharacter
     *            символ табуляції
     * @since 1.0.0
     */
    private static void printPackageInfo(final Command command,
            final Locale locale, final String tabCharacter) {
        Package commandPackage = command.getClass().getPackage();

        System.out.format(locale.getString("12x010"), tabCharacter,
                commandPackage.getName());
        System.out.format(locale.getString("12x011"), tabCharacter,
                commandPackage.getImplementationVendor());
        System.out.format(locale.getString("12x012"), tabCharacter,
                commandPackage.getSpecificationVersion());
    }

    /**
     * Поточний стан пам'яті після виконання команди.
     *
     * @since 1.0.0
     */
    private long memoryAfterExecution;

    /**
     * Поточний стан пам'яті до виконання команди.
     *
     * @since 1.0.0
     */
    private long memoryBeforeExecution;

    /**
     * Поточний час після виконання команди.
     *
     * @since 1.0.0
     */
    private long timeAfterExecution;

    /**
     * Поточний час до виконання команди.
     *
     * @since 1.0.0
     */
    private long timeBeforeExecution;

    /**
     * Призначений, для ініціалізації декоратору.
     *
     * @param command
     *            посилання на об'єкт команди, для декорування
     * @since 1.0.0
     */
    public CommandWithDebug(final Command command) {
        super(command);

        this.timeBeforeExecution = 0;
        this.timeAfterExecution = 0;

        this.memoryBeforeExecution = 0;
        this.memoryAfterExecution = 0;
    }

    @Override
    public void execute() {
        Locale locale = LocaleDictionary.getInstance().getDefaultLocale();
        String tabCharacter = Shell.getInstance().getSettings()
                .getTabCharacter();

        System.out.format(locale.getString("12x000"));

        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        int index;
        for (index = 1; index < elements.length; index++) {
            StackTraceElement stackTraceElement = elements[index];
            System.out.println(locale.getString("12x001")
                    + stackTraceElement.getClassName() + "."
                    + stackTraceElement.getMethodName() + "("
                    + stackTraceElement.getFileName() + ":"
                    + stackTraceElement.getLineNumber() + ")");
        }
        System.out.println();

        printPackageInfo(getCommand(), locale, tabCharacter);
        printClassInfo(getCommand(), locale, tabCharacter);

        System.out.format(locale.getString("12x002"), tabCharacter,
                getCommand().getName());

        memoryBeforeExecution = Runtime.getRuntime().totalMemory();
        timeBeforeExecution = System.nanoTime();

        command.execute();

        timeAfterExecution = System.nanoTime();
        memoryAfterExecution = Runtime.getRuntime().totalMemory();

        System.out.format(locale.getString("12x003"), tabCharacter,
                getCommand().getName());

        printMemoryUsage(locale, tabCharacter);
        printExecutionTime(locale, tabCharacter);

        System.out.println();
    }

    /**
     * Призначений, для виведення результатів вимірювання часу виконання
     * команди.
     *
     * @param locale
     *            об'єкт локалізації
     * @param tabCharacter
     *            символ табуляції
     * @since 1.0.0
     */
    private void printExecutionTime(final Locale locale,
            final String tabCharacter) {
        System.out.format(locale.getString("12x009"), tabCharacter,
                timeAfterExecution - timeBeforeExecution);
    }

    /**
     * Призначений, для виведення результатів вимірювання використання пам'яті.
     *
     * @param locale
     *            об'єкт локалізації
     * @param tabCharacter
     *            символ табуляції
     * @since 1.0.0
     */
    private void printMemoryUsage(final Locale locale,
            final String tabCharacter) {
        System.out.format(locale.getString("12x007"), tabCharacter,
                memoryBeforeExecution);
        System.out.format(locale.getString("12x008"), tabCharacter,
                memoryAfterExecution);
    }
}
