package ua.khpi.oop.malokhvii03.text;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Призначений, для обходження колекції слів, з вилученням усіх слів з
 * фіксованою початковою літерою.
 *
 * @author malokhvii-ee
 * @version 1.0.0
 * @see WordsCollection
 */
public final class WordFixedLetterIterator implements Iterator<String> {

    /**
     * Поточне значення ітератору, для обходження розмірів слів.
     */
    private Iterator<Entry<Integer, Set<String>>> currentWords;

    /**
     * Поточне значення ітератор, для обходження множин слів.
     */
    private Iterator<String> currentWord;

    /**
     * Призначений, для ініціалізації початкового стану ітератору.
     *
     * @param characters
     *            посилання на кореневий елемент колекції
     * @param letter
     *            фіксована початкова літера для пошуку
     */
    public WordFixedLetterIterator(
            final Map<Character, Map<Integer, Set<String>>> characters,
            final char letter) {
        if (characters.containsKey(letter)) {
            this.currentWords = characters.get(letter).entrySet().iterator();
            this.currentWord = this.currentWords.next().getValue().iterator();
        } else {
            this.currentWords = Collections.emptyIterator();
            this.currentWord = Collections.emptyIterator();
        }
    }

    @Override
    public boolean hasNext() {
        if (this.currentWord.hasNext()) {
            return true;
        } else if (this.currentWords.hasNext()) {
            return true;
        }
        return false;
    }

    @Override
    public String next() {
        if (this.currentWord.hasNext()) {
            return this.currentWord.next();
        } else if (this.currentWords.hasNext()) {
            this.currentWord = this.currentWords.next().getValue().iterator();
            return this.currentWord.next();
        }
        return null;
    }
}
