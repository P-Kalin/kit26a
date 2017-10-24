package ua.khpi.oop.malokhvii03.text;

/**
 * Призначений, для реалізації поняття - Ана́нім (грец. ανα- — «знову» όνομα —
 * «ім'я») — псевдонім чи анаграма з літер власного імені, прочитаного у
 * зворотному порядку.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 * @see Anagrams
 * @since 1.0.0
 */
public final class Ananym {

    /**
     * Початкове слово.
     *
     * @since 1.0.0
     */
    private String word;

    /**
     * Обернене початкове слово, тобто анаграма.
     *
     * @since 1.0.0
     */
    private String reversedWord;

    public Ananym(final CharSequence word, final CharSequence reversedWord) {
        this.word = word.toString();
        this.reversedWord = reversedWord.toString();
    }

    /**
     * Призначений для ініціалізації ананиму (анаграми).
     *
     * @param word
     *            слово
     * @param reversedWord
     *            обернене слово, тобто анаграма
     * @since 1.0.0
     */
    public Ananym(final String word, final String reversedWord) {
        this.word = word;
        this.reversedWord = reversedWord;
    }

    /**
     * Призначений, для отримання оберненого слова, тобто анаграми.
     *
     * @return обернене слово, тобто анаграма
     * @since 1.0.0
     */
    public String getReversedWord() {
        return this.reversedWord;
    }

    /**
     * Призначений, для отримання слова.
     *
     * @return слово
     * @since 1.0.0
     */
    public String getWord() {
        return this.word;
    }

    /**
     * Призначений, для оновлення поточного значення оберненого слова, тобто
     * анаграми.
     *
     * @param reversedWord
     *            обернене слово, тобто анаграма
     * @since 1.0.0
     */
    public void setReverseWord(final String reversedWord) {
        this.reversedWord = reversedWord;
    }

    /**
     * Призначений, для оновлення поточного значення слова.
     *
     * @param word
     *            поточне значення слова
     * @since 1.0.0
     */
    public void setWord(final String word) {
        this.word = word;
    }
}
