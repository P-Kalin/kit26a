package ua.khpi.oop.malokhvii04.shell;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

import ua.khpi.oop.malokhvii03.text.Ananym;
import ua.khpi.oop.malokhvii04.shell.command.AbstractCommandDecorator;
import ua.khpi.oop.malokhvii04.shell.command.Command;
import ua.khpi.oop.malokhvii05.util.Array;

/**
 * Призначений, для збереження даних інтерактивної оболонки, для подальшої
 * передачі між командами.
 *
 * @author malokhvii-eduard
 * @version 1.0.0
 * @since 1.0.0
 */
public final class ShellData {

    /**
     * Призначений, для створення об'єкту даних інтерактивної оболонки.
     *
     * @author malokhvii-eduard
     * @version 1.0.0
     * @since 1.0.0
     */
    public final class Builder {

        /**
         * Приватний конструктор, за для створення об'єкту будівника лише через
         * сатичний метод {@link ShellData#getBuilder() }.
         *
         * @since 1.0.0
         */
        private Builder() {

        }

        /**
         * Призначений, для побудування об'єкту даних інтерактивної оболонки.
         *
         * @return дані інтерактивної оболонки
         * @since 1.0.0
         */
        public ShellData build() {
            return ShellData.this;
        }

        /**
         * Призначений, для встановлення колекції ананимів (анаграм).
         *
         * @param ananymsCollection
         *            колекція ананимів (анаграм)
         * @return об'єкт будівника
         * @since 1.0.0
         */
        public Builder setAnanyms(final Array<Ananym> ananyms) {
            ShellData.this.ananyms = ananyms;
            return this;
        }

        /**
         * Призначений, для встановлення символа виділення команди.
         *
         * @param commandCharacter
         *            символ виділення команди
         * @return об'єкт будівника
         * @since 1.0.0
         */
        public Builder setCommandCharacter(final String commandCharacter) {
            ShellData.this.commandCharacter = commandCharacter;
            return this;
        }

        /**
         * Призначений, для встановлення значення розміру історії викликів
         * команд.
         *
         * @param commandHistorySize
         *            рормір історії викликів команд
         * @return об'єкт будівника
         * @since 1.0.0
         */
        public Builder setCommandHistorySize(final int commandHistorySize) {
            ShellData.this.commandHistorySize = commandHistorySize;
            return this;
        }

        /**
         * Призначений, для встановлення вхідного потоку, для сканера.
         *
         * @param inputStream
         *            вхідний потік
         * @return об'єкт будівника
         * @since 1.0.0
         */
        public Builder setInputStream(final InputStream inputStream) {
            ShellData.this.shellScanner = new Scanner(inputStream);
            return this;
        }

        /**
         * Призначений, для встановлення символа табуляції.
         *
         * @param tabCharacter
         *            символ табуляції
         * @return об'єкт будівника
         * @since 1.0.0
         */
        public Builder setTabCharacter(final String tabCharacter) {
            ShellData.this.tabCharacter = tabCharacter;
            return this;
        }

        /**
         * Призначений, для встановлення буфера вхідних рядків, для подальшої
         * обробки.
         *
         * @param textLines
         *            буфер вхідних рядків
         * @return об'єкт будівника
         * @since 1.0.0
         */
        public Builder setTextLines(final Array<CharSequence> textLines) {
            ShellData.this.textLines = textLines;
            return this;
        }
    }

    /**
     * Призначений, для отримання нового об'єкту будівника даних.
     *
     * @return об'єкт будівника даних
     * @since 1.0.0
     */
    public static Builder getBuilder() {
        return new ShellData().new Builder();
    }

    /**
     * Посилання, на колекцію ананимів (анаграм).
     *
     * @since 1.0.0
     */
    private Array<Ananym> ananyms;

    /**
     * Символ виділення команди.
     *
     * @since 1.0.0
     */
    private String commandCharacter;

    /**
     * Історія викликів команд.
     *
     * @since 1.0.0
     */
    private Deque<Command> commandHistory;

    /**
     * Розмір історії команд.
     *
     * @since 1.0.0
     */
    private int commandHistorySize;

    /**
     * Індекс поточної команди.
     *
     * @since 1.0.0
     */
    private int currentCommandIndex;

    /**
     * Клас-декоратор для відображення трасування команд.
     *
     * @since 1.0.0
     */
    private Class<? extends AbstractCommandDecorator> debugCommandDecorator;

    /**
     * Стан роботи ынтерактивноъ оболонки.
     *
     * @since 1.0.0
     */
    private boolean isRunning;

    /**
     * Сканер вхідних даних.
     *
     * @since 1.0.0
     */
    private Scanner shellScanner;

    /**
     * Символ табуляції.
     * 
     * @since 1.0.0
     */
    private String tabCharacter;

    /**
     * Посилання, на буффер для вхідного тексту.
     *
     * @since 1.0.0
     */
    private Array<CharSequence> textLines;

    /**
     * Приватний конструктор, задля створення об'єкт за допомогою Builder-а.
     *
     * @since 1.0.0
     */
    private ShellData() {
        this.commandHistory = new ArrayDeque<Command>();
        this.isRunning = false;
        this.currentCommandIndex = 0;
    }

    /**
     * Призначений, для отримання колекції ананимів.
     *
     * @return колекція ананимів
     * @since 1.0.0
     */
    public Array<Ananym> getAnanyms() {
        return ananyms;
    }

    /**
     * Призначений, для отримання символи виділення команди.
     *
     * @return символ виділення команди
     * @since 1.0.0
     */
    public String getCommandCharacter() {
        return this.commandCharacter;
    }

    /**
     * Призначений, для отримання історії викликів команд.
     *
     * @return історія викликів команд
     * @since 1.0.0
     */
    public Deque<Command> getCommandHistory() {
        return this.commandHistory;
    }

    /**
     * Призначений, для отримання розміру історії викликів команд.
     *
     * @return розмір історії викликів команд
     * @since 1.0.0
     */
    public int getCommandHistorySize() {
        return commandHistorySize;
    }

    /**
     * Призначений, для отримання поточного індексу команди.
     *
     * @return поточний індекс команди
     * @since 1.0.0
     */
    public int getCurrentCommandIndex() {
        return this.currentCommandIndex;
    }

    /**
     * Призначений, для отримання декоратору для відлагодження.
     *
     * @return декортаор для відлагодження
     * @since 1.0.0
     */
    public Class<? extends AbstractCommandDecorator> getDebugCommandDecorator() {
        return debugCommandDecorator;
    }

    /**
     * Призначений, для отримання сканеру.
     *
     * @return сканер обробки вхідних даних
     * @since 1.0.0
     */
    public Scanner getShellScanner() {
        return this.shellScanner;
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
     * Призначений, для отримання буферу рядків вхідного тексту.
     *
     * @return буфер рядків вхідного тексту
     * @since 1.0.0
     */
    public Array<CharSequence> getTextLines() {
        return textLines;
    }

    /**
     * Призначений, для інкрементування індексу поточної команди.
     *
     * @since 1.0.0
     */
    public void incrementCurrentCommandIndex() {
        ++this.currentCommandIndex;
    }

    /**
     * Призначений, для отримання стану роботи інтерактивної оболонки.
     *
     * @return стан роботи інтерактивної оболонки
     * @since 1.0.0
     */
    public boolean isRunning() {
        return isRunning;
    }

    public void setAnanyms(Array<Ananym> ananyms) {
        this.ananyms = ananyms;
    }

    /**
     * Призначений, для оновлення декоратору для відлагодження.
     *
     * @param debugCommandDecorator
     *            декоратор для відлагодження
     * @since 1.0.0
     */
    public void setDebugCommandDecorator(
            final Class<? extends AbstractCommandDecorator> debugCommandDecorator) {
        this.debugCommandDecorator = debugCommandDecorator;
    }

    /**
     * Призначений, для оновлення стану роботи.
     *
     * @param isRunning
     *            новий стан роботи
     * @since 1.0.0
     */
    public void setRunning(final boolean isRunning) {
        this.isRunning = isRunning;
    }

    public void setTextLines(Array<CharSequence> textLines) {
        this.textLines = textLines;
    }

    /**
     * Призначений, створення декорованоъ команди.
     *
     * @param command
     *            звичайна команда
     * @return декорована команда
     * @since 1.0.0
     */
    public Command wrapCommandWithDebug(final Command command) {
        if (this.debugCommandDecorator == null) {
            return command;
        }

        Constructor<? extends AbstractCommandDecorator> commandConstructor;
        try {
            commandConstructor = this.debugCommandDecorator
                    .getConstructor(Command.class);
        } catch (NoSuchMethodException | SecurityException exception) {
            return command;
        }

        try {
            return commandConstructor.newInstance(command);
        } catch (InstantiationException | IllegalAccessException
                | IllegalArgumentException
                | InvocationTargetException exception) {
            return command;
        }
    }
}
