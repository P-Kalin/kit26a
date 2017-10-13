package ua.khpi.oop.malokhvii04.shell.command;

import ua.khpi.oop.malokhvii04.shell.CommandLineParser;

/**
 * Призначений, для декорування об'єктів команд відладочною інформацією.
 *
 * @author malokhvii-eduard
 * @version 1.0.0
 * @see Shell
 * @see CommandFactory
 * @see AbstractCommand
 * @since 1.0.0
 */
public final class CommandWithDebug extends AbstractCommandDecorator {

    /**
     * Поточний час до виконання команди.
     *
     * @since 1.0.0
     */
    private long commandExecutionTimeBegin;

    /**
     * Поточний час після виконання команди.
     *
     * @since 1.0.0
     */
    private long commandExecutionTimeEnd;

    /**
     * Поточний стан пам'яті до виконання команди.
     *
     * @since 1.0.0
     */
    private long commandExecutionMemoryBefore;

    /**
     * Поточний стан пам'яті після виконання команди.
     *
     * @since 1.0.0
     */
    private long commandExecutionMemoryAfter;

    /**
     * Символ відладочною табуляції.
     *
     * @since 1.0.0
     */
    private static String debugTabCharacter;

    static {
        CommandWithDebug.debugTabCharacter = CommandLineParser
                .getDefaultTabCharacter();
    }

    /**
     * Призначений, для ініціалізації декоратору.
     *
     * @param command
     *            посилання на об'єкт команди, для декорування
     * @since 1.0.0
     */
    public CommandWithDebug(final Command command) {
        super(command);

        this.commandExecutionTimeBegin = 0;
        this.commandExecutionTimeEnd = 0;

        this.commandExecutionMemoryBefore = 0;
        this.commandExecutionMemoryAfter = 0;
    }

    /**
     * Призначений, для виведення інформації про пакет, клас комани з якого
     * використано.
     *
     * @param command
     *            об'єкт команди
     * @since 1.0.0
     */
    public void printPackageInfo(final Command command) {
        Package commandPackage = command.getClass().getPackage();

        System.out.format("%1$s Package name: %2$s\n",
                CommandWithDebug.debugTabCharacter, commandPackage.getName());
        System.out.format("%1$s Package vendor: %2$s\n",
                CommandWithDebug.debugTabCharacter,
                commandPackage.getImplementationVendor());
        System.out.format("%1$s Package version: %2$s\n\n",
                CommandWithDebug.debugTabCharacter,
                commandPackage.getSpecificationVersion());
    }

    /**
     * Призначений, для виведення інформації про клас.
     *
     * @param command
     *            об'єкт команди
     * @since 1.0.0
     */
    public void printClassInfo(final Command command) {
        Class<? extends Command> commandClass = command.getClass();
        Class<? extends ClassLoader> commandClassLoader = commandClass
                .getClassLoader().getClass();

        System.out.format("%1$s Simple name: %2$s\n",
                CommandWithDebug.debugTabCharacter,
                commandClass.getSimpleName());
        System.out.format("%1$s Canonical name: %2$s\n",
                CommandWithDebug.debugTabCharacter,
                commandClass.getCanonicalName());
        System.out.format("%1$s Class loader: %2$s\n\n",
                CommandWithDebug.debugTabCharacter,
                commandClassLoader.getName());
    }

    /**
     * Призначений, для виведення результатів вимірювання використання пам'яті.
     *
     * @since 1.0.0
     */
    public void printMemoryUsage() {
        System.out.format("%1$s Total memory before execution: %2$d bytes\n",
                CommandWithDebug.debugTabCharacter,
                this.commandExecutionMemoryBefore);
        System.out.format("%1$s Total memory after execution:  %2$d bytes\n",
                CommandWithDebug.debugTabCharacter,
                this.commandExecutionMemoryAfter);
    }

    /**
     * Призначений, для виведення результатів вимірювання часу виконання
     * команди.
     * 
     * @since 1.0.0
     */
    public void printExecutionTime() {
        System.out.format("%1$s Command execution took: %2$d ns\n",
                CommandWithDebug.debugTabCharacter,
                this.commandExecutionTimeEnd - this.commandExecutionTimeBegin);
    }

    @Override
    public void execute() {
        System.out.format("[Debug]: %s\n", this.getCommand().getId());
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        for (int i = 1; i < elements.length; i++) {
            StackTraceElement s = elements[i];
            System.out.println(
                    "\tat " + s.getClassName() + "." + s.getMethodName() + "("
                            + s.getFileName() + ":" + s.getLineNumber() + ")");
        }
        System.out.println();

        this.printPackageInfo(getCommand());
        this.printClassInfo(getCommand());

        System.out.format("%1$s Start execution %2$s\n\n",
                CommandWithDebug.debugTabCharacter, getCommand().getName());

        this.commandExecutionMemoryBefore = Runtime.getRuntime().totalMemory();
        this.commandExecutionTimeBegin = System.nanoTime();

        this.getCommand().execute();

        this.commandExecutionTimeEnd = System.nanoTime();
        this.commandExecutionMemoryAfter = Runtime.getRuntime().totalMemory();

        System.out.format("%1$s Finish execution %2$s\n\n",
                CommandWithDebug.debugTabCharacter, getCommand().getName());

        this.printMemoryUsage();
        this.printExecutionTime();

        System.out.println();
    }
}
