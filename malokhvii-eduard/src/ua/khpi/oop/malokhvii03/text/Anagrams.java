package ua.khpi.oop.malokhvii03.text;

import java.util.Collection;
import java.util.HashMap;

/**
 * Утилітарний клас, призначения для обробки вхідних текстових наборів на
 * наявність анаграм.
 *
 * @author malokhvii-eduard
 * @version 1.1.0
 */
public final class Anagrams {

    /**
     * Регулярний вираз за змовчуванням, для розділення вхідного тексту на слова
     * ({@value}).
     *
     * @since 1.1.0
     */
    public static final String DEFAUL_LINE_SPLITTER = "[\\s\\d\\p{Punct}]";

    /**
     * Приватний конструктор, для заборони створення утилітарного класу.
     */
    private Anagrams() {

    }

    /**
     * Призначений, для пошуку слів ананимів. Наприклад: "def", "fed".
     * Порівняння рядків виконується за допомогою поліноміального хешу, таким
     * чином можливо зменьшити обсяг використаного часу на порівняння слів, їх
     * пошук, а також зменьшити просторову складність алгоритму пошуку,
     * порівняно з попереднім алгоритмом пошуку. Детальніше див. попередню
     * реалізацію. Регулярний вираз для розділення рядків
     * {@link Anagrams#DEFAUL_LINE_SPLITTER}.
     *
     * @param lines
     *            набір вхідних рядків
     * @see <a href=
     *      "https://sourceforge.net/p/kit26a-cpp/code/HEAD/tree/malokhvii_eduard/src/ua/khpi/oop/malokhvii03/text/">
     *      Версія - 1.0.0</a>
     * @see HashableWord
     * @see PolynomialHash
     * @since 1.1.0
     * @return множина хешованих слів, які були на передодні оброблені. Усі
     *         слова для яких було знайдено обернену послідовність символів
     *         повертають дісний результат за допомогою методів
     *         {@link HashableWord#isExistReversedCharSequence},
     *         {@link HashableWord#getReversedCharSequence}.
     */
    public static Collection<HashableWord> findAllAnanymsInText(
            final Iterable<String> lines) {
        return findAllAnanymsInText(lines, DEFAUL_LINE_SPLITTER);
    }

    /**
     * Призначений, для пошуку слів ананимів. Наприклад: "def", "fed".
     * Порівняння рядків виконується за допомогою поліноміального хешу, таким
     * чином можливо зменьшити обсяг використаного часу на порівняння слів, їх
     * пошук, а також зменьшити просторову складність алгоритму пошуку,
     * порівняно з попереднім алгоритмом пошуку. Детальніше див. попередню
     * реалізацію.
     *
     * @param lines
     *            набір вхідних рядків
     * @param regex
     *            регулярний вираз, для розділення рядків
     * @see <a href=
     *      "https://sourceforge.net/p/kit26a-cpp/code/HEAD/tree/malokhvii_eduard/src/ua/khpi/oop/malokhvii03/text/">
     *      Версія - 1.0.0</a>
     * @see HashableWord
     * @see PolynomialHash
     * @since 1.1.0
     * @return множина хешованих слів, які були на передодні оброблені. Усі
     *         слова для яких було знайдено обернену послідовність символів
     *         повертають дісний результат за допомогою методів
     *         {@link HashableWord#isExistReversedCharSequence},
     *         {@link HashableWord#getReversedCharSequence}.
     */
    public static Collection<HashableWord> findAllAnanymsInText(
            final Iterable<String> lines, final String regex) {
        assert lines == null;
        HashMap<Long, HashableWord> mappedWords = new HashMap<Long, HashableWord>();

        long polynomialHash;
        for (String line : lines) {
            for (String word : line.split(regex)) {
                if (word.length() > 1) {
                    polynomialHash = PolynomialHash.charSequenceHash(word);
                    if (!mappedWords.containsKey(polynomialHash)) {
                        mappedWords.put(polynomialHash,
                                new HashableWord(word, polynomialHash));
                    }
                }
            }
        }

        HashableWord currentMappedWord;
        for (HashableWord hashableWord : mappedWords.values()) {
            polynomialHash = hashableWord.getReversedPolіnomialHashCode();
            currentMappedWord = mappedWords.get(polynomialHash);

            if (currentMappedWord != null) {
                hashableWord.setReversedCharSequence(
                        currentMappedWord.getCharSequence());
                currentMappedWord.setReversedPolynomialHashCode(
                        PolynomialHash.ZERO_HASH);
            }
        }

        return mappedWords.values();
    }

    /**
     * Призначений, для пошуку слів ананимів. Наприклад: "def", "fed".
     * Порівняння рядків виконується за допомогою поліноміального хешу, таким
     * чином можливо зменьшити обсяг використаного часу на порівняння слів, їх
     * пошук, а також зменьшити просторову складність алгоритму пошуку,
     * порівняно з попереднім алгоритмом пошуку. Детальніше див. попередню
     * реалізацію.
     *
     * @param words
     *            набір вхідних слів, для обробки на наявність ананимів
     * @see <a href=
     *      "https://sourceforge.net/p/kit26a-cpp/code/HEAD/tree/malokhvii_eduard/src/ua/khpi/oop/malokhvii03/text/">
     *      Версія - 1.0.0</a>
     * @see HashableWord
     * @see PolynomialHash
     * @since 1.0.0
     * @return множина хешованих слів, які були на передодні оброблені. Усі
     *         слова для яких було знайдено обернену послідовність символів
     *         повертають дісний результат за допомогою методів
     *         {@link HashableWord#isExistReversedCharSequence},
     *         {@link HashableWord#getReversedCharSequence}.
     */
    public static Collection<HashableWord> findAllAnanymsInWords(
            Iterable<String> words) {
        assert words == null;
        HashMap<Long, HashableWord> mappedWords = new HashMap<Long, HashableWord>();

        long polynomialHash;
        for (String word : words) {
            if (word.length() > 1) {
                polynomialHash = PolynomialHash.charSequenceHash(word);
                if (!mappedWords.containsKey(polynomialHash)) {
                    mappedWords.put(polynomialHash,
                            new HashableWord(word, polynomialHash));
                }
            }
        }

        HashableWord currentMappedWord;
        for (HashableWord hashableWord : mappedWords.values()) {
            polynomialHash = hashableWord.getReversedPolіnomialHashCode();
            currentMappedWord = mappedWords.get(polynomialHash);

            if (currentMappedWord != null) {
                hashableWord.setReversedCharSequence(
                        currentMappedWord.getCharSequence());
                currentMappedWord.setReversedPolynomialHashCode(
                        PolynomialHash.ZERO_HASH);
            }
        }

        return mappedWords.values();
    }
}
