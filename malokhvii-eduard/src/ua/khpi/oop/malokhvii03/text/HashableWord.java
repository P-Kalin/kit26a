package ua.khpi.oop.malokhvii03.text;

/**
 * Призначений, для збереження слова для подальшої обробки на наявність
 * оберненого йому слова.
 *
 * @author malokhvii-eduard
 * @version 1.0.0
 */
public final class HashableWord {

    /**
     * Послідовність символів слова.
     */
    private CharSequence charSequence;

    /**
     * Поліноміальне значення хешу для слова.
     */
    private long polynomialHashCode;

    /**
     * Послідовність обернених символів слова.
     */
    private CharSequence reversedCharSequence = null;

    /**
     * Поліноміальне значення хешу для можливого оберненого слова.
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
     */
    public long getPolynomialHashCode() {
        return polynomialHashCode;
    }

    /**
     * Призначений, для отримання оберненої послідовності символів у слові, якщо
     * вона була знайдена у тексті та додана під час обробки.
     *
     * @return можлива обернена послідовність символів у слові
     */
    public CharSequence getReversedCharSequence() {
        return reversedCharSequence;
    }

    /**
     * Призначений, для отримання значення поліноміального хешу для можливої
     * оберненої послідовності символів.
     *
     * @return поліноміальне значення хешу для можливої оберненої послідовності
     *         символів у слові
     */
    public long getReversedPolіnomialHashCode() {
        return reversedPolynomialHashCode;
    }

    /**
     * Призначений, для перевірки чи знайдено обернену послідовність символів
     * для слова, або ні.
     *
     * @return результат перевірки
     */
    public boolean isExistReversedCharSequence() {
        return reversedCharSequence != null ? true : false;
    }

    /**
     * Призначений, для оновлення поточної послідовності символів у слові.
     *
     * @param charSequence
     *            поточна послідовність символів у слові
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
     */
    public void setPolynomialHashCode(long polynomialHashCode) {
        this.polynomialHashCode = polynomialHashCode;
    }

    /**
     * Призначений, для оновлення можливої оберненої послідовності символів у
     * слові.
     *
     * @param reversedCharSequence
     *            можлива обернена послідовність символів у слові
     */
    public void setReversedCharSequence(CharSequence reversedCharSequence) {
        this.reversedCharSequence = reversedCharSequence;
    }

    /**
     * Призначений, для оновлення значення поліноміального хешу для можливої
     * оберненої послідовності символів у слові.
     *
     * @param reversedPolynomialHashCode
     *            поліноміальне значення хешу для можливої оберненої
     *            послідовності символів у слові
     */
    public void setReversedPolynomialHashCode(long reversedPolynomialHashCode) {
        this.reversedPolynomialHashCode = reversedPolynomialHashCode;
    }
}