package ua.khpi.oop.malokhvii03.text;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Призначений, для збереження та індексування вхідних слів за натупними
 * умовами: Перша літера вхідного слова ключ для хеш карти з ключем Character та
 * вкладенною хеш картою з множинами слів, розмір вхідного слова ключ для хеш
 * карти з підмножинами слів. Обхід колекції забезпечується за допомогою
 * ітераторів.
 *
 * @author malokhvii-ee
 * @version 1.0.1
 * @see WordIterator
 * @see WordFixedLetterIterator
 * @see WordFixedSizeAndLetterIterator
 */
public final class WordsCollection {

    /**
     * Корнева хеш карти з усіма словами.
     */
    private Map<Character, Map<Integer, Set<String>>> characters;

    /**
     * Призначений, для створення колекції слів.
     */
    public WordsCollection() {
        this.characters = new HashMap<Character, Map<Integer, Set<String>>>();
    }

    /**
     * Призначений, для додавання слова у колекцію.
     *
     * @param word
     *            вхідне слово для додавання
     * @return результат додавання вхідного слова
     */
    public boolean putWord(final String word) {
        if (word.length() < 1) {
            return false;
        }

        char capitalLetter = word.charAt(0);
        int wordLength = word.length();

        if (!this.characters.containsKey(capitalLetter)) {
            Map<Integer, Set<String>> words = new HashMap<Integer, Set<String>>();
            words.put(wordLength, new HashSet<String>());
            this.characters.put(capitalLetter, words);
        }

        if (!this.characters.get(capitalLetter).containsKey(wordLength)) {
            this.characters.get(capitalLetter).put(wordLength,
                    new HashSet<String>());
        }

        this.characters.get(capitalLetter).get(wordLength).add(word);
        return true;
    }

    /**
     * Призначений, для видалленя слова із колекції.
     *
     * @param word
     *            вхідне слово для видалення
     * @return результат видалення вхідного слова
     */
    public boolean removeWord(final String word) {
        char capitalLetter = word.charAt(0);
        int wordLength = word.length();

        if (this.characters.containsKey(capitalLetter)) {
            Map<Integer, Set<String>> words = this.characters
                    .get(capitalLetter);
            if (words.containsKey(wordLength)) {
                Set<String> fixedLengthWords = words.get(wordLength);
                fixedLengthWords.remove(word);
                return true;
            }
        }
        return false;
    }

    /**
     * Призначений, для очищення колекції.
     */
    public void clear() {
        this.characters.clear();
    }

    /**
     * Призначений, для отримання поточного стану колекції.
     *
     * @return поточний стан заповнення колекції
     */
    public boolean isEmpty() {
        return this.characters.isEmpty();
    }

    /**
     * Призначений для отримання ітератору, для отримання вмісту колекції.
     *
     * @see WordIterator
     * @return об'єкт ітератору
     */
    public Iterator<String> getWordIterator() {
        return new WordIterator(this.characters);
    }

    /**
     * Призначений для отримання ітератору, для отримання вмісту колекції.
     *
     * @param letter
     *            початкова літера для пошуку слів
     * @see WordFixedLetterIterator
     * @return об'єкт ітератору
     */
    public Iterator<String> getWordFixedLetterIterator(final char letter) {
        return new WordFixedLetterIterator(this.characters, letter);
    }

    /**
     * Призначений для отримання ітератору, для отримання вмісту колекції.
     *
     * @param letter
     *            початкова літера для пошуку слів
     * @param size
     *            розмір для пошуку слів
     * @return об'єкт ітератору
     */
    public Iterator<String> getWordFixedSizeAndLetterIterator(final char letter,
            final int size) {
        return new WordFixedSizeAndLetterIterator(this.characters, letter,
                size);
    }
}
