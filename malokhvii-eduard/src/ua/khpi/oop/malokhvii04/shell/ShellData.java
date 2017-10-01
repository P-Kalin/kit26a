package ua.khpi.oop.malokhvii04.shell;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

import ua.khpi.oop.malokhvii03.text.AnanymsCollection;
import ua.khpi.oop.malokhvii03.text.WordsCollection;
import ua.khpi.oop.malokhvii04.shell.command.AbstractCommandDecorator;
import ua.khpi.oop.malokhvii04.shell.command.Command;

/**
 * Призначений, для збереження даних інтерактивної оболонки, для подальшої
 * передачі між командами.
 *
 * @author malokhvii-ee
 * @version 1.0.0
 */
public final class ShellData {

    /**
     * Клас-декоратор для відображення трасування команд.
     */
    private Class<? extends AbstractCommandDecorator> debugCommandDecorator;

    /**
     * Історія викликів команд.
     */
    private Deque<Command> commandHistory;

    /**
     * Розмір історії команд.
     */
    private int commandHistorySize;

    /**
     * Індекс поточної команди.
     */
    private int currentCommandIndex;

    /**
     * Стан роботи ынтерактивноъ оболонки.
     */
    private boolean isRunning;

    /**
     * Посилання, на колекцію слів.
     */
    private WordsCollection wordsCollection;

    /**
     * Посилання, на колекцію ананимів (анаграм).
     */
    private AnanymsCollection ananymsCollection;

    /**
     * Символ виділення команди.
     */
    private String commandCharacter;

    /**
     * Символ табуляції.
     */
    private String tabCharacter;

    /**
     * Сканер вхідних даних.
     */
    private Scanner shellScanner;

    /**
     * Приватний конструктор, задля створення об'єкт за допомогою Builder-а.
     */
    private ShellData() {
        this.commandHistory = new ArrayDeque<Command>();
        this.isRunning = false;
        this.currentCommandIndex = 0;
    }

    /**
     * Призначений, для отримання нового об'єкту будівника даних.
     *
     * @return об'єкт будівника даних
     */
    public static Builder getBuilder() {
        return new ShellData().new Builder();
    }

    /**
     * Призначений, для отримання символи виділення команди.
     *
     * @return символ виділення команди
     */
    public String getCommandCharacter() {
        return this.commandCharacter;
    }

    /**
     * Призначений, для отримання символу табуляції.
     *
     * @return символ табуляції
     */
    public String getTabCharacter() {
        return this.tabCharacter;
    }

    /**
     * Призначений, для отримання стану роботи інтерактивної оболонки.
     *
     * @return стан роботи інтерактивної оболонки
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * Призначений, для оновлення стану роботи.
     *
     * @param isRunning
     *            новий стан роботи
     */
    public void setRunning(final boolean isRunning) {
        this.isRunning = isRunning;
    }

    /**
     * Призначений, для отримання розміру історії викликів команд.
     *
     * @return розмір історії викликів команд
     */
    public int getCommandHistorySize() {
        return commandHistorySize;
    }

    /**
     * Призначений, для отримання колекції слів.
     *
     * @return колекція слів
     */
    public WordsCollection getWordsCollection() {
        return this.wordsCollection;
    }

    /**
     * Призначений, для оновлення колекції слів.
     *
     * @param wordsCollection
     *            оновлена колекція слів
     */
    public void setWordsCollection(final WordsCollection wordsCollection) {
        this.wordsCollection = wordsCollection;
    }

    /**
     * Призначений, для оновлення колекції ананимів (анаграм).
     *
     * @param ananymsCollection
     *            оновлена колекція ананимів (анаграм).
     */
    public void setAnanymsCollection(
            final AnanymsCollection ananymsCollection) {
        this.ananymsCollection = ananymsCollection;
    }

    /**
     * Призначений, для отримання колекції ананимів (анаграм).
     *
     * @return колекція ананимів (анаграм)
     */
    public AnanymsCollection getAnanymsCollection() {
        return this.ananymsCollection;
    }

    /**
     * Призначений, для отримання історії викликів команд.
     *
     * @return історія викликів команд
     */
    public Deque<Command> getCommandHistory() {
        return this.commandHistory;
    }

    /**
     * Призначений, для отримання сканеру.
     *
     * @return сканер обробки вхідних даних
     */
    public Scanner getShellScanner() {
        return this.shellScanner;
    }

    /**
     * Призначений, для отримання поточного індексу команди.
     *
     * @return поточний індекс команди
     */
    public int getCurrentCommandIndex() {
        return this.currentCommandIndex;
    }

    /**
     * Призначений, для інкрементування індексу поточної команди.
     */
    public void incrementCurrentCommandIndex() {
        ++this.currentCommandIndex;
    }

    /**
     * Призначений, для отримання декоратору для відлагодження.
     *
     * @return декортаор для відлагодження
     */
    public Class<? extends AbstractCommandDecorator> getDebugCommandDecorator() {
        return debugCommandDecorator;
    }

    /**
     * Призначений, для оновлення декоратору для відлагодження.
     *
     * @param debugCommandDecorator
     *            декоратор для відлагодження
     */
    public void setDebugCommandDecorator(
            final Class<? extends AbstractCommandDecorator> debugCommandDecorator) {
        this.debugCommandDecorator = debugCommandDecorator;
    }

    /**
     * Призначений, створення декорованоъ команди.
     *
     * @param command
     *            звичайна команда
     * @return декорована команда
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

    /**
     * Призначений, для створення об'єкту даних інтерактивної оболонки.
     *
     * @author malokhvii-ee
     * @version 1.0.0
     */
    public final class Builder {

        /**
         * Приватний конструктор, за для створення об'єкту будівника лише через
         * сатичний метод {@link ShellData#getBuilder() }.
         */
        private Builder() {

        }

        /**
         * Призначений, для встановлення колекції слів.
         *
         * @param wordsCollection
         *            колекція слів
         * @return об'єкт будівника
         */
        public Builder setWordsCollection(
                final WordsCollection wordsCollection) {
            ShellData.this.wordsCollection = wordsCollection;
            return this;
        }

        /**
         * Призначений, для встановлення колекції ананимів (анаграм).
         *
         * @param ananymsCollection
         *            колекція ананимів (анаграм)
         * @return об'єкт будівника
         */
        public Builder setAnanymsCollection(
                final AnanymsCollection ananymsCollection) {
            ShellData.this.ananymsCollection = ananymsCollection;
            return this;
        }

        /**
         * Призначений, для встановлення значення розміру історії викликів
         * команд.
         *
         * @param commandHistorySize
         *            рормір історії викликів команд
         * @return об'єкт будівника
         */
        public Builder setCommandHistorySize(final int commandHistorySize) {
            ShellData.this.commandHistorySize = commandHistorySize;
            return this;
        }

        /**
         * Призначений, для встановлення символа виділення команди.
         *
         * @param commandCharacter
         *            символ виділення команди
         * @return об'єкт будівника
         */
        public Builder setCommandCharacter(final String commandCharacter) {
            ShellData.this.commandCharacter = commandCharacter;
            return this;
        }

        /**
         * Призначений, для встановлення символа табуляції.
         *
         * @param tabCharacter
         *            символ табуляції
         * @return об'єкт будівника
         */
        public Builder setTabCharacter(final String tabCharacter) {
            ShellData.this.tabCharacter = tabCharacter;
            return this;
        }

        /**
         * Призначений, для встановлення вхідного потоку, для сканера.
         *
         * @param inputStream
         *            вхідний потік
         * @return об'єкт будівника
         */
        public Builder setInputStream(final InputStream inputStream) {
            ShellData.this.shellScanner = new Scanner(inputStream);
            return this;
        }

        /**
         * Призначений, для побудування об'єкту даних інтерактивної оболонки.
         *
         * @return дані інтерактивної оболонки
         */
        public ShellData build() {
            return ShellData.this;
        }
    }
}
