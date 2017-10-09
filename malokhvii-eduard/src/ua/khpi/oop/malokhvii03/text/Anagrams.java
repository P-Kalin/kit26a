package ua.khpi.oop.malokhvii03.text;

import java.util.Iterator;

/**
 * Утилітарний клас, призначений для обробки вхідних даних на наявність анаграм.
 *
 * @author malokhvii-ee
 * @version 1.0.0
 */
public final class Anagrams {

    /**
     * Приватний конструктор, для заборони створення утилітарного класу.
     */
    private Anagrams() {

    }

    /**
     * Призначений, для перевірки чи є вхідне слово ананимом до очікуваного
     * слова. Наприклад: isAnanym("def", "fed") == true.
     *
     * @param word
     *            вхідне слово
     * @param reversedWord
     *            можливий ананим вхідного слова
     * @return результат перевірки
     */
    public static boolean isAnanym(final String word,
            final String reversedWord) {
        if (word.length() != reversedWord.length()) {
            return false;
        }

        char[] wordCharacterSequence = word.toCharArray();
        char[] reversedWordCharacterSequence = reversedWord.toCharArray();

        int charactersDifference = 0;
        for (int i = 0; i < wordCharacterSequence.length; i++) {
            charactersDifference += wordCharacterSequence[i]
                    - reversedWordCharacterSequence[wordCharacterSequence.length
                            - 1 - i];
            if (charactersDifference != 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Призначений, для пошуку усіх ананимів у колекції слів.
     *
     * @param words
     *            вхідна колекція слів
     * @see WordsCollection
     * @see WordIterator
     * @return колекція знайдених ананимів
     */
    public static AnanymsCollection findAllAnanyms(
            final WordsCollection words) {
        return findAllAnanyms(words, words.getWordIterator());
    }

    /**
     * Призначений, для пошуку ананимів починаючихся на певну літеру у колекції
     * слів.
     *
     * @param words
     *            вхідна колекція слів
     * @param letter
     *            фіксована літера, для пошуку ананимів
     * @see WordsCollection
     * @see WordFixedLetterIterator
     * @return колекція знайдених ананимів
     */
    public static AnanymsCollection findAllAnanyms(final WordsCollection words,
            final char letter) {
        return findAllAnanyms(words, words.getWordFixedLetterIterator(letter));
    }

    /**
     * Призначений, для пошуку ананимів починаючихся на певну літеру та
     * фіксованого розміру у колекції слів.
     *
     * @param words
     *            вхідна колекція слів
     * @param letter
     *            фіксована літера, для пошуку ананимів
     * @param size
     *            фіксований розмір слова, для пошуку ананимів
     * @see WordsCollection
     * @see WordFixedSizeAndLetterIterator
     * @return колекція знайдених ананимів
     */
    public static AnanymsCollection findAllAnanyms(final WordsCollection words,
            final char letter, final int size) {
        return findAllAnanyms(words,
                words.getWordFixedSizeAndLetterIterator(letter, size));
    }

    /**
     * Призначений, для проходу через колекцію слів, та обробки кожного
     * отриманого слова.
     *
     * @param words
     *            колекція слів
     * @param wordIterator
     *            ітератор для проходу через колекцію слів
     * @see WordsCollection
     * @see Iterator
     * @return колекція знайдених ананимів
     */
    private static AnanymsCollection findAllAnanyms(final WordsCollection words,
            final Iterator<String> wordIterator) {
        AnanymsCollection ananyms = new AnanymsCollection();

        while (wordIterator.hasNext()) {
            Ananym ananym = findAnanym(words, wordIterator.next());
            if (ananym != null) {
                ananyms.putAnanym(ananym);
            }
        }

        return ananyms;
    }

    /**
     * Призначений, для пошуку оберненого слова для отриманного вхідного слова.
     * Наприклад: findAnanym(..., "def") буде виконано пошук слова "fed" в
     * колекції слів.
     *
     * @param words
     *            колекція слів
     * @param word
     *            вхідне слово
     * @see Ananym
     * @return знайдений Ананим або null
     */
    private static Ananym findAnanym(final WordsCollection words,
            final String word) {
        int wordLength = word.length();
        char lastLetter = word.charAt(wordLength - 1);

        Iterator<String> reversedWordIterator = words
                .getWordFixedSizeAndLetterIterator(lastLetter, wordLength);
        while (reversedWordIterator.hasNext()) {
            String reversedWord = reversedWordIterator.next();
            if (isAnanym(word, reversedWord)) {
                return new Ananym(word, reversedWord);
            }
        }

        return null;
    }
}
