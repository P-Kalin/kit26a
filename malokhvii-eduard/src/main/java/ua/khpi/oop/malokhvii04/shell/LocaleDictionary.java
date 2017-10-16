package ua.khpi.oop.malokhvii04.shell;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Observable;

import ua.khpi.oop.malokhvii05.util.Array;

/**
 * Призначений, для збереження усіх об'єктів локалізації, та оповіщення усіх
 * команд про зміну поточної локалізації.
 *
 * @author malokhvii-eduard
 * @version 1.0.0
 * @since 1.1.0
 * @see Observable
 * @see ua.khpi.oop.malokhvii04.shell.commands.AbstractCommand AbstractCommand
 */
public final class LocaleDictionary extends Observable {

    /**
     * Призначений, для збереження єдиного екземпляру колекції локалізацій.
     *
     * @author malokhvii-eduard
     * @version 1.0.0
     * @since 1.1.0
     */
    private static class SingletonHolder {

        /**
         * Єдиний екземпляр колекції локалізацій.
         *
         * @since 1.1.0
         */
        private static final LocaleDictionary INSTANCE = new LocaleDictionary();
    }

    /**
     * Призначений, для отримання єдиного екземпляру колекції локалізацій.
     *
     * @return єдиний екземпляр колекції локалізацій
     * @since 1.1.0
     */
    public static LocaleDictionary getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * Назва локалізації за змовчуванням.
     *
     * @since 1.0.0
     */
    private String defaultLocale;

    /**
     * Перелік, усіх об'єктів локалізацій.
     *
     * @since 1.0.0
     */
    private HashMap<String, Locale> locales;

    /**
     * Приватний конструктор, для заборони створення декількох об'єктів колекції
     * локалізацій.
     *
     * @since 1.0.0
     */
    private LocaleDictionary() {
        locales = new HashMap<String, Locale>();
    }

    /**
     * Призначений, для отримання локалізації за змовчуванням.
     *
     * @return локалізація за змовчуванням
     * @since 1.0.0
     */
    public Locale getDefaultLocale() {
        return locales.get(defaultLocale);
    }

    /**
     * Призначений, для отримання переліку файлів серіалізації із директорії
     * розташування локалізацій.
     *
     * @param fileNames
     *            масив, для збереження шляхів до файлів
     * @param folder
     *            назва директорії розташування локалізацій
     * @since 1.0.0
     */
    private void getFileNames(final Array<String> fileNames,
            final Path folder) {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(folder)) {
            for (Path path : stream) {
                if (path.toFile().isDirectory()) {
                    getFileNames(fileNames, path);
                } else {
                    fileNames.add(path.toAbsolutePath().toString());
                }
            }
        } catch (IOException e) {

        }
    }

    /**
     * Призначений, для отримання об'єкту локаліації за її назвою.
     *
     * @param name
     *            назва локалізації
     * @return об'єкт локалізації
     * @since 1.0.0
     */
    public Locale getLocale(final String name) {
        return locales.get(name);
    }

    /**
     * Призначений, для десеріалізації об'єкту локалізації.
     *
     * @param fileName
     *            назва файлу серіалізації
     * @since 1.0.0
     */
    private void loadLocale(final String fileName) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream(fileName);
            objectInputStream = new ObjectInputStream(fileInputStream);
            Locale locale = (Locale) objectInputStream.readObject();
            locales.put(locale.getName(), locale);
        } catch (Exception exception) {

        } finally {
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {

                }
            }
        }
    }

    /**
     * Призначений, для десеріалізації усіх об'єктів локалізації.
     *
     * @param shellSettings
     *            налаштуванням інтерактивної оболонки, містять шлях до
     *            розташуванням об'єктів локалізацій
     * @since 1.0.0
     */
    public void loadLocales(final ShellSettings shellSettings) {
        defaultLocale = shellSettings.getDefaultLocale();

        Array<String> fileNames = new Array<String>();
        getFileNames(fileNames, Paths.get(shellSettings.getLocalesFolder()));

        for (String fileName : fileNames) {
            loadLocale(fileName);
        }
    }

    /**
     * Призначений, для оновлення назви локлізації за змовчуванням.
     *
     * @param name
     *            нова назва локалізації за змовчуванням
     * @since 1.0.0
     */
    public void setDefaultLocale(final String name) {
        if (locales.containsKey(name)) {
            defaultLocale = name;
            notifyObservers(locales.get(defaultLocale));
        }
    }
}
