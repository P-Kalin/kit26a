package ua.khpi.oop.malokhvii03.text;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Призначений, для обходження колекції слів, з вилученням усіх слів фіксованої
 * довжини та з фіксованою початковою літерою.
 *
 * @author malokhvii-ee
 * @version 1.0.0
 * @see WordsCollection
 */
public final class WordFixedSizeAndLetterIterator implements Iterator<String> {

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
     * @param size
     *            фіксований початковий розмір для пошуку
     */
    public WordFixedSizeAndLetterIterator(
            final Map<Character, Map<Integer, Set<String>>> characters,
            final char letter, final int size) {
        if (characters.containsKey(letter)
                && characters.get(letter).containsKey(size)) {
            this.currentWord = characters.get(letter).get(size).iterator();
        } else {
            this.currentWord = Collections.emptyIterator();
        }
    }

    @Override
    public boolean hasNext() {
        return this.currentWord.hasNext();
    }

    @Override
    public String next() {
        return this.currentWord.next();
    }
}
