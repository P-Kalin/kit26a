package ua.khpi.oop.malokhvii04.shell;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Observable;
import java.util.ResourceBundle;

/**
 * Призначений, для збереження усіх об'єктів локалізації, та оповіщення усіх
 * команд про зміну поточної локалізації. Оновлення об'єктів ресурсів, для усіх
 * слухачів.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 * @see Observable
 */
public final class ShellResources extends Observable {

    /**
     * Призначений, для збереження єдиного екземпляру колекції локалізацій.
     *
     * @author malokhvii-eduard (malokhvii.ee@gmail.com)
     * @version 1.0.0
     */
    private static class SingletonHolder {

        /**
         * Єдиний екземпляр колекції локалізацій.
         *
         * @since 1.0.0
         */
        private static final ShellResources INSTANCE = new ShellResources();
    }

    /**
     * Перелік допустимих локалізацій для інтерактивної оболонки.
     *
     * @since 1.0.0
     */
    private static Map<String, Locale> locales = new HashMap<String, Locale>();

    /**
     * Призначений, для отримання єдиного екземпляру колекції локалізацій.
     *
     * @return єдиний екземпляр колекції локалізацій
     * @since 1.1.0
     */
    public static ShellResources getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * Локалізація, за змовучанням, тобто поточно локалізація для усієї
     * інтерактивної оболонки.
     *
     * @since 1.0.0
     */
    private Locale defaultLocale;

    /**
     * Приватний конструктор, для заборони створення декількох об'єктів сховища
     * локалізацій.
     *
     * @since 1.0.0
     */
    private ShellResources() {

    }

    /**
     * Призначений, для отримання об'єкту ресурсів, з використання локалізації
     * за змовчуванням.
     *
     * @param baseName
     *            шлях, до властивостей які будуть використані як ресур
     * @return новий об'єкт ресурів
     * @since 1.0.0
     */
    public ResourceBundle getResourceBundle(final String baseName) {
        return ResourceBundle.getBundle(baseName, this.defaultLocale);
    }

    /**
     * Призначений, для додавання нової локалізації за змовчуванням до сховища
     * локалізацій.
     *
     * @param name
     *            назва локалізації
     * @param locale
     *            об'єкт нової локалізації за змовчуванням
     */
    public void setDefaultLocale(final String name, final Locale locale) {
        this.defaultLocale = locale;
        this.setLocale(name, locale);
        this.notifyObservers();
    }

    /**
     * Призначений, для додавання нової локалізації до сховища локалізацій.
     *
     * @param name
     *            наза локалізації
     * @param locale
     *            об'єкт нової локалізації
     * @since 1.0.0
     */
    public void setLocale(final String name, final Locale locale) {
        ShellResources.locales.put(name, locale);
    }
}
