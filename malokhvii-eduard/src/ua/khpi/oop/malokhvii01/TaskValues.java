package ua.khpi.oop.malokhvii01;

/**
 * Зберігає початкові дані, для подальшого обчислення згідно поставленого
 * завдання, та зручнішої передачі у обрабники завдань.
 *
 * @author malokhvii-eduard
 * @version 1.0.0
 * @see TaskPerformerForDecimalValues
 * @see TaskPerformerForBinaryValues
 * @since 1.0.0
 */
public final class TaskValues {

    /**
     * Призначений для більш наглядної ініціалізації об'єктів типу
     * {@link TaskValues}, та гнучкої, водночас зберігає імутабільність
     * (immutability) стану самого об'єкту. Використано механізм настатичних
     * внутрішніх класів. Тобто об'єкт даного класу можливо створити лише через
     * об'єкт "батківського-класу". Важливою особливостю є те, що таким чином
     * об'єкт отримує до приватних полів свого "батьківського-класу". Розраховує
     * більшу частину літералів самостійно, без необхідності встановлючвати
     * окремо значення кожного. На відміну від об'єктів типу
     * {@link LiteralBuilder}.
     *
     * @author malokhvii-eduard
     * @version 1.0.0
     * @see TaskValues
     * @see LiteralBuilder
     * @since 1.0.0
     */
    public final class Builder {

        /**
         * Порожній конструктор, але є приватним для захисту від створення
         * об'єкту не через спецільний метод {@link TaskValues#builder()}.
         *
         * @since 1.0.0
         */
        private Builder() {

        }

        /**
         * Призначений для остаточного конструювання об'єкту типу
         * {@link TaskValues}.
         *
         * @return Посилання на сконструйований об'єкт типу {@link TaskValues}
         * @since 1.0.0
         */
        public TaskValues build() {
            return TaskValues.this;
        }

        /**
         * Призначений для оновлення значення символу англійського алфавіту в
         * верхньому регістрі, номер якого відповідає збільшеному на одиницю
         * значенню залишку від ділення на 26 зменшеного на одиницю номера
         * студента в журналі групи.
         *
         * @param englishUpperCaseLetter
         *            Номер студента згідно списку групи, юнікод літери
         *            англійського алфавіту
         * @return Посилання на об'єкт будівельника початкових даних
         * @since 1.0.0
         */
        public Builder setEnglishUpperCaseLetter(
                final int englishUpperCaseLetter) {
            char letter = (char) ((englishUpperCaseLetter - 1)
                    % TaskValues.STUDENT_NUMBER_DIVIDER + 1 + 'A');
            TaskValues.this.englishUpperCaseLetter = letter;
            return this;
        }

        /**
         * Призначений для оновлення значення номеру мобільного телефону, та
         * дотакових полів, які безпосередньо пов'язані з обчисленням номеру
         * телефону, тобто дві останніх цифри з кінця номеру телефону, та чотири
         * останні цифри з кінця номеру телефону.
         *
         * @param mobilePhoneNumber
         *            Номер мобільного телефону
         * @return Посилання на об'єкт будівельника початкових даних
         * @since 1.0.0
         */
        public Builder setMobilePhoneNumber(final long mobilePhoneNumber) {
            TaskValues.this.mobilePhoneNumber = mobilePhoneNumber;
            int lastTwoDigits = (int) (mobilePhoneNumber % 100L);
            int lastFourDigits = (int) (mobilePhoneNumber % 10000L);
            TaskValues.this.lastTwoDigitsOfMobilePhoneNumber = lastTwoDigits;
            TaskValues.this.lastFourDigitsOfMobilePhoneNumber = lastFourDigits;
            return this;
        }

        /**
         * Призначений для оновлення значення номеру залікової книжки студента.
         *
         * @param recordBookNumber
         *            Номер залікової книжки студента
         * @return Посилання на об'єкт будівельника початкових даних
         * @since 1.0.0
         */
        public Builder setRecordBookNumber(final int recordBookNumber) {
            TaskValues.this.recordBookNumber = recordBookNumber;
            return this;
        }
    }

    /**
     * Призначений для більш наглядної ініціалізації об'єктів типу
     * {@link TaskValues}, та гнучкої, водночас зберігає імутабільність
     * (immutability) стану самого об'єкту. Використано механізм настатичних
     * внутрішніх класів. Тобто об'єкт даного класу можливо створити лише через
     * об'єкт "батківського-класу". Важливою особливостю є те, що таким чином
     * об'єкт отримує до приватних полів свого "батьківського-класу". Потребує
     * встановлення усіх літералів власноруч, для демонстрації поставленого
     * завдання. На відміну від об'єктів типу{@link Builder}
     *
     * @author malokhvii-eduard
     * @version 1.0.0
     * @see TaskValues
     * @see Builder
     * @since 1.0.0
     */
    public final class LiteralBuilder {

        /**
         * Порожній конструктор, але є приватним для захисту від створення
         * об'єкту не через спецільний метод
         * {@link TaskValues#literalBuilder()}.
         *
         * @since 1.0.0
         */
        private LiteralBuilder() {

        }

        /**
         * Призначений для остаточного конструювання об'єкту типу
         * {@link TaskValues}.
         *
         * @return Посилання на сконструйований об'єкт типу {@link TaskValues}
         * @since 1.0.0
         */
        public TaskValues build() {
            return TaskValues.this;
        }

        /**
         * Призначений для оновлення значення символу англійського алфавіту в
         * верхньому регістрі, номер якого відповідає збільшеному на одиницю
         * значенню залишку від ділення на 26 зменшеного на одиницю номера
         * студента в журналі групи.
         *
         * @param englishUpperCaseLetter
         *            Символ англійського алфавіту в верхньому регістрі
         * @return Посилання на об'єкт будівельника початкових даних
         * @since 1.0.0
         */
        public LiteralBuilder setEnglishUpperCaseLetter(
                final char englishUpperCaseLetter) {
            TaskValues.this.englishUpperCaseLetter = englishUpperCaseLetter;
            return this;
        }

        /**
         * Призначений для оновлення значення останніх двох цифр від номеру
         * мобільного телефону.
         *
         * @param lastFourDigitsOfMobilePhoneNumber
         *            Останні дві цифри від номеру мобільного телефону
         * @return Посилання на об'єкт будівельника початкових даних
         * @since 1.0.0
         */
        public LiteralBuilder setLastFourDigitsOfMobilePhoneNumber(
                final int lastFourDigitsOfMobilePhoneNumber) {
            TaskValues.this.lastFourDigitsOfMobilePhoneNumber = lastFourDigitsOfMobilePhoneNumber;
            return this;
        }

        /**
         * Призначений для оновлення значення останніх чотирьох цифр від номеру
         * мобільного телефону.
         *
         * @param lastTwoDigitsOfMobilePhoneNumber
         *            Останні чотири цифри від номеру мобільного телефону
         * @return Посилання на об'єкт будівельника початкових даних
         * @since 1.0.0
         */
        public LiteralBuilder setLastTwoDigitsOfMobilePhoneNumber(
                final int lastTwoDigitsOfMobilePhoneNumber) {
            TaskValues.this.lastTwoDigitsOfMobilePhoneNumber = lastTwoDigitsOfMobilePhoneNumber;
            return this;
        }

        /**
         * Призначений для оновлення значення номеру мобільного телефону.
         *
         * @param mobilePhoneNumber
         *            Номер мобільного телефону
         * @return Посилання на об'єкт будівельника початкових даних
         * @since 1.0.0
         */
        public LiteralBuilder setMobilePhoneNumber(
                final long mobilePhoneNumber) {
            TaskValues.this.mobilePhoneNumber = mobilePhoneNumber;
            return this;
        }

        /**
         * Призначений для оновлення значення номеру залікової книжки студента.
         *
         * @param recordBookNumber
         *            Номер залікової книжки студента
         * @return Посилання на об'єкт будівельника початкових даних
         * @since 1.0.0
         */
        public LiteralBuilder setRecordBookNumber(final int recordBookNumber) {
            TaskValues.this.recordBookNumber = recordBookNumber;
            return this;
        }
    }

    /**
     * Константа, необхідна для розрахунку числа, для подальшого його
     * переведення у літеру англійського алфавіту. Розрахунок за наступним
     * чином: (номер студента у писку - 1) % {@value} - 1
     *
     * @since 1.0.0
     */
    private static final int STUDENT_NUMBER_DIVIDER = 26;

    /**
     * Призначений для побудови об'єкту типу {@link Builder}, для подальшого
     * створення об'єкту власного типу.
     *
     * @see Builder
     * @return Сконструйований екземпляр об'єкту будівельника почтакових даних
     * @since 1.0.0
     */
    public static Builder builder() {
        return new TaskValues().new Builder();
    }

    /**
     * Призначений для побудови об'єкту типу {@link LiteralBuilder}, для
     * подальшого створення об'єкту власного типу.
     *
     * @see LiteralBuilder
     * @return Сконструйований екземпляр об'єкту будівельника почтакових даних
     * @since 1.0.0
     */
    public static LiteralBuilder literalBuilder() {
        return new TaskValues().new LiteralBuilder();
    }

    /**
     * Літера у верхньому регістрі, англійського алфавіту.
     *
     * @since 1.0.0
     */
    private char englishUpperCaseLetter;

    /**
     * Останні чотири цифри від номеру мобільного телефону.
     *
     * @since 1.0.0
     */
    private int lastFourDigitsOfMobilePhoneNumber;

    /**
     * Останні дві цифри від номеру мобільного телефону.
     *
     * @since 1.0.0
     */
    private int lastTwoDigitsOfMobilePhoneNumber;

    /**
     * Номер мобільного телефону.
     *
     * @since 1.0.0
     */
    private long mobilePhoneNumber;

    /**
     * Номер залікової книжки студента.
     *
     * @since 1.0.0
     */
    private int recordBookNumber;

    /**
     * Порожній конструктор, але є приватним для захисту від створення об'єкту
     * не через спецільний "об'єкт будівник" типу {@link LiteralBuilder} або
     * {@link Builder}.
     *
     * @since 1.0.0
     */
    private TaskValues() {

    }

    /**
     * Призначений для отримання значення поля
     * {@link TaskValues#englishUpperCaseLetter}.
     *
     * @return Значення поля {@link TaskValues#englishUpperCaseLetter}
     * @since 1.0.0
     */
    public char getEnglishUpperCaseLetter() {
        return this.englishUpperCaseLetter;
    }

    /**
     * Призначений для отримання значення поля
     * {@link TaskValues#lastFourDigitsOfMobilePhoneNumber}.
     *
     * @return Значення поля
     *         {@link TaskValues#lastFourDigitsOfMobilePhoneNumber}
     * @since 1.0.0
     */
    public int getLastFourDigitsOfMobilePhoneNumber() {
        return this.lastFourDigitsOfMobilePhoneNumber;
    }

    /**
     * Призначений для отримання значення поля
     * {@link TaskValues#lastTwoDigitsOfMobilePhoneNumber}.
     *
     * @return Значення поля {@link TaskValues#lastTwoDigitsOfMobilePhoneNumber}
     * @since 1.0.0
     */
    public int getLastTwoDigitsOfMobilePhoneNumber() {
        return this.lastTwoDigitsOfMobilePhoneNumber;
    }

    /**
     * Призначений для отримання значення поля
     * {@link TaskValues#mobilePhoneNumber}.
     *
     * @return Значення поля {@link TaskValues#mobilePhoneNumber}
     * @since 1.0.0
     */
    public long getMobilePhoneNumber() {
        return this.mobilePhoneNumber;
    }

    /**
     * Призначений для отримання значення поля
     * {@link TaskValues#recordBookNumber}.
     *
     * @return Значення поля {@link TaskValues#recordBookNumber}
     * @since 1.0.0
     */
    public int getRecordBookNumber() {
        return this.recordBookNumber;
    }
}
