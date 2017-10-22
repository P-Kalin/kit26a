package ua.khpi.oop.malokhvii04.shell.commands.debug;

import java.util.ResourceBundle;

import ua.khpi.oop.malokhvii04.shell.Shell;
import ua.khpi.oop.malokhvii04.shell.ShellResources;
import ua.khpi.oop.malokhvii04.shell.commands.AbstractCommandDecorator;
import ua.khpi.oop.malokhvii04.shell.commands.Command;

/**
 * Призначений, для декорування об'єктів команд відладочною інформацією.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.1.0
 * @see Shell
 */
public final class CommandWithDebug extends AbstractCommandDecorator {

    /**
     * Розташування текстового файлу, з властивостями, а саме текстовими
     * рядками, для подальшого виведення в інтерактивній оболонці.
     *
     * @since 1.1.0
     */
    private static final String RESOURCE_BUNDLE_NAME = "shell.commands."
            + "debug.CommandWithDebugBundle";

    /**
     * Об'єкт ресурсів для локалізації трасування виконання команд, під час
     * відлагодження.
     *
     * @since 1.1.0
     */
    private static ResourceBundle resourceBundle;

    /**
     * Призначений, для виведення інформації про клас.
     *
     * @param command
     *            об'єкт команди
     * @param tabCharacter
     *            символ табуляції
     * @since 1.0.0
     */
    private static void printClassInfo(final Command command,
            final String tabCharacter) {
        final Class<? extends Command> commandClass = command.getClass();
        final Class<? extends ClassLoader> commandClassLoader = commandClass
                .getClassLoader().getClass();

        System.out.format(
                CommandWithDebug.resourceBundle
                        .getString("class-simple-name-message"),
                tabCharacter, commandClass.getSimpleName());
        System.out.format(
                CommandWithDebug.resourceBundle
                        .getString("class-canonical-name-message"),
                tabCharacter, commandClass.getCanonicalName());
        System.out.format(
                CommandWithDebug.resourceBundle
                        .getString("class-loader-message"),
                tabCharacter, commandClassLoader.getName());
    }

    /**
     * Призначений, для виведення інформації про пакет, клас комани з якого
     * використано.
     *
     * @param command
     *            об'єкт команди
     * @param tabCharacter
     *            символ табуляції
     * @since 1.0.0
     */
    private static void printPackageInfo(final Command command,
            final String tabCharacter) {
        final Package commandPackage = command.getClass().getPackage();

        System.out.format(
                CommandWithDebug.resourceBundle
                        .getString("package-name-message"),
                tabCharacter, commandPackage.getName());
        System.out.format(
                CommandWithDebug.resourceBundle
                        .getString("package-vendor-message"),
                tabCharacter, commandPackage.getImplementationVendor());
        System.out.format(
                CommandWithDebug.resourceBundle
                        .getString("package-version-message"),
                tabCharacter, commandPackage.getSpecificationVersion());
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
        CommandWithDebug.resourceBundle = ShellResources.getInstance()
                .getResourceBundle(CommandWithDebug.RESOURCE_BUNDLE_NAME);
        final String tabCharacter = Shell.getInstance().getSettings()
                .getTabCharacter();

        System.out.format(
                CommandWithDebug.resourceBundle.getString("debug-token"));

        final StackTraceElement[] elements = Thread.currentThread()
                .getStackTrace();
        int index;
        for (index = 1; index < elements.length; index++) {
            final StackTraceElement stackTraceElement = elements[index];
            System.out.println(
                    CommandWithDebug.resourceBundle.getString("location-token")
                            + stackTraceElement.getClassName() + "."
                            + stackTraceElement.getMethodName() + "("
                            + stackTraceElement.getFileName() + ":"
                            + stackTraceElement.getLineNumber() + ")");
        }
        System.out.println();

        CommandWithDebug.printPackageInfo(this.getCommand(), tabCharacter);
        CommandWithDebug.printClassInfo(this.getCommand(), tabCharacter);

        System.out.format(
                CommandWithDebug.resourceBundle
                        .getString("start-execution-message"),
                tabCharacter, this.getCommand().getName());

        this.memoryBeforeExecution = Runtime.getRuntime().totalMemory();
        this.timeBeforeExecution = System.nanoTime();

        this.command.execute();

        this.timeAfterExecution = System.nanoTime();
        this.memoryAfterExecution = Runtime.getRuntime().totalMemory();

        System.out.format(
                CommandWithDebug.resourceBundle
                        .getString("finish-execution-message"),
                tabCharacter, this.getCommand().getName());

        this.printMemoryUsage(tabCharacter);
        this.printExecutionTime(tabCharacter);

        System.out.println();
    }

    /**
     * Призначений, для виведення результатів вимірювання часу виконання
     * команди.
     *
     * @param tabCharacter
     *            символ табуляції
     * @since 1.0.0
     */
    private void printExecutionTime(final String tabCharacter) {
        System.out.format(
                CommandWithDebug.resourceBundle
                        .getString("execution-time-message"),
                tabCharacter,
                this.timeAfterExecution - this.timeBeforeExecution);
    }

    /**
     * Призначений, для виведення результатів вимірювання використання пам'яті.
     *
     * @param tabCharacter
     *            символ табуляції
     * @since 1.0.0
     */
    private void printMemoryUsage(final String tabCharacter) {
        System.out.format(
                CommandWithDebug.resourceBundle
                        .getString("total-memory-before-message"),
                tabCharacter, this.memoryBeforeExecution);
        System.out.format(
                CommandWithDebug.resourceBundle
                        .getString("total-memory-after-message"),
                tabCharacter, this.memoryAfterExecution);
    }
}
