package ua.khpi.oop.malokhvii03.text;

/**
 * Призначений, для реалізації поняття - Ана́нім (грец. ανα- — «знову» όνομα —
 * «ім'я») — псевдонім чи анаграма з літер власного імені, прочитаного у
 * зворотному порядку.
 *
 * @author malokhvii-ee
 * @version 1.0.0
 * @see Anagrams
 * @see AnanymsCollection
 */
public final class Ananym {

    /**
     * Початкове слово.
     */
    private String word;

    /**
     * Обернене початкове слово, тобто анаграма.
     */
    private String reversedWord;

    /**
     * Призначений для ініціалізації ананиму (анаграми).
     *
     * @param word
     *            слово
     * @param reversedWord
     *            обернене слово, тобто анаграма
     */
    public Ananym(final String word, final String reversedWord) {
        this.word = word;
        this.reversedWord = reversedWord;
    }

    /**
     * Призначений, для отримання слова.
     *
     * @return слово
     */
    public String getWord() {
        return this.word;
    }

    /**
     * Призначений, для отримання оберненого слова, тобто анаграми.
     *
     * @return обернене слово, тобто анаграма
     */
    public String getReversedWord() {
        return this.reversedWord;
    }

    /**
     * Призначений, для оновлення поточного значення слова.
     *
     * @param word
     *            поточне значення слова
     */
    public void setWord(final String word) {
        this.word = word;
    }

    /**
     * Призначений, для оновлення поточного значення оберненого слова, тобто
     * анаграми.
     *
     * @param reversedWord
     *            обернене слово, тобто анаграма
     */
    public void setReverseWord(final String reversedWord) {
        this.reversedWord = reversedWord;
    }
}
