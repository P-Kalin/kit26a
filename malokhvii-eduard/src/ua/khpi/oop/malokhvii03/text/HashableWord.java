package ua.khpi.oop.malokhvii03.text;

/**
 * Призначений, для збереження слова для подальшої обробки на наявність
 * оберненого йому слова.
 *
 * @author malokhvii-eduard
 * @version 1.0.0
 * @since 1.1.0
 */
public final class HashableWord {

    /**
     * Послідовність символів слова.
     *
     * @since 1.0.0
     */
    private CharSequence charSequence;

    /**
     * Поліноміальне значення хешу для слова.
     *
     * @since 1.0.0
     */
    private long polynomialHashCode;

    /**
     * Поліноміальне значення хешу для можливого оберненого слова.
     *
     * @since 1.0.0
     */
    private long reversedPolynomialHashCode;

    /**
     * Призначений, для ініціалізації об'єкту хешованого слова, початковою
     * послідовностю символів, та попередньо розрахованим поліноміальним хешом.
     *
     * @param charSequence
     *            початкова послідовність символів у слові
     * @param polynomialHashCode
     *            значення поліноміального хешу для поточної послідовності
     * @since 1.0.0
     */
    public HashableWord(CharSequence charSequence, long polynomialHashCode) {
        this.charSequence = charSequence;

        this.polynomialHashCode = polynomialHashCode;
        this.reversedPolynomialHashCode = PolynomialHash
                .reversedCharSequenceHash(charSequence);
    }

    /**
     * Призначений, для отримання поточної послідовності символів у слові.
     *
     * @return поточна послідовність символів у слові
     * @since 1.0.0
     */
    public CharSequence getCharSequence() {
        return charSequence;
    }

    /**
     * Призначений, для отримання значення поліноміального хешу для поточної
     * послідовності символів.
     *
     * @return поліноміальне значення хешу поточної послідовності символів у
     *         слові
     * @since 1.0.0
     */
    public long getPolynomialHashCode() {
        return polynomialHashCode;
    }

    /**
     * Призначений, для отримання значення поліноміального хешу для можливої
     * оберненої послідовності символів.
     *
     * @return поліноміальне значення хешу для можливої оберненої послідовності
     *         символів у слові
     * @since 1.0.0
     */
    public long getReversedPolynomialHashCode() {
        return reversedPolynomialHashCode;
    }

    /**
     * Призначений, для оновлення поточної послідовності символів у слові.
     *
     * @param charSequence
     *            поточна послідовність символів у слові
     * @since 1.0.0
     */
    public void setCharSequence(CharSequence charSequence) {
        this.charSequence = charSequence;
    }

    /**
     * Призначений, для оновлення значення поліноміального хешу для поточної
     * послідовності символів.
     *
     * @param polynomialHashCode
     *            значення оновленого поліноміального хешу для поточної
     *            послідовності символів
     * @since 1.0.0
     */
    public void setPolynomialHashCode(long polynomialHashCode) {
        this.polynomialHashCode = polynomialHashCode;
    }

    /**
     * Призначений, для оновлення значення поліноміального хешу для можливої
     * оберненої послідовності символів у слові.
     *
     * @param reversedPolynomialHashCode
     *            поліноміальне значення хешу для можливої оберненої
     *            послідовності символів у слові
     * @since 1.0.0
     */
    public void setReversedPolynomialHashCode(long reversedPolynomialHashCode) {
        this.reversedPolynomialHashCode = reversedPolynomialHashCode;
    }
}
