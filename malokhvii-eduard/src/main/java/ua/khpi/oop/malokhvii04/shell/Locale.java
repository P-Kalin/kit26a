package ua.khpi.oop.malokhvii04.shell;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Призначений, для збереження рядків, необхідних для виведення під час
 * використання інтерактивної оболонки.
 *
 * @author malokhvii-eduard
 * @version 1.0.0
 * @see LocaleDictionary
 * @since 1.1.0
 */
public final class Locale implements Serializable {

    /**
     * Призначений, для створення об'єктів локалізації для інтерактивної
     * оболонки.
     *
     * @author malokhvii-eduard
     * @version 1.0.0
     * @since 1.0.0
     */
    public final class Builder {

        /**
         * Приватний конструктор, для заборони створення об'єктів будівника
         * локалізацій.
         *
         * @since 1.0.0
         */
        private Builder() {

        }

        /**
         * Призначений, для створення нового об'єкту локалізації.
         *
         * @return створений об'єкт локалізації
         * @since 1.0.0
         */
        public Locale build() {
            return Locale.this;
        }

        /**
         * Призначений, для встановлення назви локалізації.
         *
         * @param name
         *            назва локалізації
         * @return об'єкт будівника
         * @since 1.0.0
         */
        public Builder setName(final String name) {
            Locale.this.name = name;
            return this;
        }

        /**
         * Призначений, для додавання нового рядку в локалізацію.
         *
         * @param key
         *            ключ рядку в локалізації
         * @param string
         *            новий рядок в локалізації
         * @return об'єкт будівника
         * @since 1.0.0
         */
        public Builder setString(final String key, final String string) {
            Locale.this.strings.put(key, string);
            return this;
        }
    }

    /**
     * Номер версії класу, для серіалізації / десеріалізації.
     *
     * @since 1.0.0
     */
    private static final long serialVersionUID = 2688434768141604084L;

    /**
     * Призначений, для отримання нового об'єкту будівника для локалізації.
     *
     * @return новий об'єкт будівника локалізацій
     * @since 1.0.0
     */
    public static Builder newBuilder() {
        return new Locale().new Builder();
    }

    /**
     * Назва локалізації.
     *
     * @since 1.0.0
     */
    private String name;

    /**
     * Колекція рядків. А саме пари із ключа рядку, та його змісту. тобто
     * ("0x000" - "Рядок").
     *
     * @since 1.0.0
     */
    private HashMap<String, String> strings;

    /**
     * Приватний конструктор, для заборони створення об'єктів локалізації.
     *
     * @since 1.0.0
     */
    private Locale() {
        strings = new HashMap<String, String>();
    }

    /**
     * Призначений, для отримання назви локалізації.
     *
     * @return назва локалізації
     * @since 1.0.0
     */
    public String getName() {
        return name;
    }

    /**
     * Призначений, для отримання рядку згідно його індексу у локалізації.
     *
     * @param key
     *            ключ рядку в локалізації
     * @return рядок із локалізації
     * @since 1.0.0
     */
    public String getString(final String key) {
        return strings.get(key);
    }
}
