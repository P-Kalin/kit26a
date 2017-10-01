package ua.khpi.oop.malokhvii03.text;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Призначений, для обходження колекції слів, з вилученням усіх слів.
 *
 * @author malokhvii-ee
 * @version 1.0.0
 * @see WordsCollection
 */
public final class WordIterator implements Iterator<String> {

    /**
     * Поточне значення ітератору, для обходження усієї колекції.
     */
    private Iterator<Entry<Character, Map<Integer, Set<String>>>> currentCharacter;

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
     */
    public WordIterator(
            final Map<Character, Map<Integer, Set<String>>> characters) {
        this.currentCharacter = characters.entrySet().iterator();
        this.currentWords = this.currentCharacter.next().getValue().entrySet()
                .iterator();
        this.currentWord = this.currentWords.next().getValue().iterator();
    }

    @Override
    public boolean hasNext() {
        if (this.currentWord.hasNext()) {
            return true;
        } else if (this.currentWords.hasNext()) {
            return true;
        } else if (this.currentCharacter.hasNext()) {
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
        } else if (this.currentCharacter.hasNext()) {
            this.currentWords = this.currentCharacter.next().getValue()
                    .entrySet().iterator();
            this.currentWord = this.currentWords.next().getValue().iterator();
            return this.currentWord.next();
        }
        return null;
    }
}
