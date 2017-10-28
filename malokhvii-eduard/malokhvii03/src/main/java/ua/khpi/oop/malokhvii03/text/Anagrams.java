package ua.khpi.oop.malokhvii03.text;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ua.khpi.oop.malokhvii05.util.Array;

/**
 * Утилітарний клас, призначения для обробки вхідних текстових наборів на
 * наявність анаграм.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.2.0
 */
public final class Anagrams {

    /**
     * Регулярний вираз за змовчуванням, для розділення вхідного тексту на слова
     * ({@value}).
     *
     * @since 1.1.0
     */
    public static final String DEFAUL_WORD_PATTERN = "[a-zA-Z]{2,}";

    /**
     * Призначений, для пошуку слів ананимів. Наприклад: "def", "fed".
     * Порівняння рядків виконується за допомогою поліноміального хешу, таким
     * чином можливо зменьшити обсяг використаного часу на порівняння слів, їх
     * пошук, а також зменьшити просторову складність алгоритму пошуку,
     * порівняно з попереднім алгоритмом пошуку. Детальніше див. попередню
     * реалізацію.
     *
     * @param charSequence
     *            символьна послідовність
     * @param regex
     *            регулярний вираз, для виділення з послідовності певнх
     *            символьних послідовностей
     * @see <a href=
     *      "https://sourceforge.net/p/kit26a-cpp/code/HEAD/tree/malokhvii_eduard/src/ua/khpi/oop/malokhvii03/text/">
     *      Версія - 1.0.0</a>
     * @see HashableWord
     * @see PolynomialHash
     * @see Ananym
     * @return {@link Array перелік} ананимів.
     * @since 1.0.0
     */
    public static Collection<Ananym> findAllAnanyms(
            final CharSequence charSequence, final String regex) {
        assert charSequence == null;
        final Array<Ananym> ananyms = new Array<Ananym>();
        final HashMap<Long, HashableWord> mappedWords = new HashMap<Long, HashableWord>();
        final Pattern wordPattern = Pattern.compile(regex);

        long polynomialHash;
        CharSequence word;
        final Matcher wordMatcher = wordPattern.matcher(charSequence);
        while (wordMatcher.find()) {
            word = wordMatcher.group();
            polynomialHash = PolynomialHash.charSequenceHash(word);
            if (!mappedWords.containsKey(polynomialHash)) {
                mappedWords.put(polynomialHash,
                        new HashableWord(word, polynomialHash));
            }
        }

        HashableWord currentMappedWord;
        for (final HashableWord hashableWord : mappedWords.values()) {
            polynomialHash = hashableWord.getReversedPolynomialHashCode();
            currentMappedWord = mappedWords.get(polynomialHash);

            if (currentMappedWord != null) {
                ananyms.add(new Ananym(hashableWord.getCharSequence(),
                        currentMappedWord.getCharSequence()));
                currentMappedWord.setReversedPolynomialHashCode(
                        PolynomialHash.ZERO_HASH);
            }
        }

        return ananyms;
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
     * @see Ananym
     * @return {@link Array перелік} ананимів.
     * @since 1.0.0
     */
    public static Collection<Ananym> findAllAnanyms(
            final Iterable<CharSequence> words) {
        assert words == null;
        final Array<Ananym> ananyms = new Array<Ananym>();
        final HashMap<Long, HashableWord> mappedWords = new HashMap<Long, HashableWord>();

        long polynomialHash;
        for (final CharSequence word : words) {
            polynomialHash = PolynomialHash.charSequenceHash(word);
            if (!mappedWords.containsKey(polynomialHash)) {
                mappedWords.put(polynomialHash,
                        new HashableWord(word, polynomialHash));
            }
        }

        HashableWord currentMappedWord;
        for (final HashableWord hashableWord : mappedWords.values()) {
            polynomialHash = hashableWord.getReversedPolynomialHashCode();
            currentMappedWord = mappedWords.get(polynomialHash);
            if (currentMappedWord != null) {
                ananyms.add(new Ananym(hashableWord.getCharSequence(),
                        currentMappedWord.getCharSequence()));
                currentMappedWord.setReversedPolynomialHashCode(
                        PolynomialHash.ZERO_HASH);
            }
        }

        return ananyms;
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
     *            набір вхідних символьних послідовностей
     * @param regex
     *            регулярний вираз, для виділення з послідовності певних
     *            символьних послідовностей
     * @see <a href=
     *      "https://sourceforge.net/p/kit26a-cpp/code/HEAD/tree/malokhvii_eduard/src/ua/khpi/oop/malokhvii03/text/">
     *      Версія - 1.0.0</a>
     * @see HashableWord
     * @see PolynomialHash
     * @see Ananym
     * @return {@link Array перелік} ананимів.
     * @since 1.0.0
     */
    public static Collection<Ananym> findAllAnanyms(
            final Iterable<CharSequence> lines, final String regex) {
        assert lines == null;
        final Array<Ananym> ananyms = new Array<Ananym>();
        final HashMap<Long, HashableWord> mappedWords = new HashMap<Long, HashableWord>();
        final Pattern wordPattern = Pattern.compile(regex);

        long polynomialHash;
        CharSequence word;
        for (final CharSequence line : lines) {
            final Matcher wordMatcher = wordPattern.matcher(line);
            while (wordMatcher.find()) {
                word = wordMatcher.group();
                polynomialHash = PolynomialHash.charSequenceHash(word);
                if (!mappedWords.containsKey(polynomialHash)) {
                    mappedWords.put(polynomialHash,
                            new HashableWord(word, polynomialHash));
                }
            }
        }

        HashableWord currentMappedWord;
        for (final HashableWord hashableWord : mappedWords.values()) {
            polynomialHash = hashableWord.getReversedPolynomialHashCode();
            currentMappedWord = mappedWords.get(polynomialHash);

            if (currentMappedWord != null) {
                ananyms.add(new Ananym(hashableWord.getCharSequence(),
                        currentMappedWord.getCharSequence()));
                currentMappedWord.setReversedPolynomialHashCode(
                        PolynomialHash.ZERO_HASH);
            }
        }

        return ananyms;
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
     * @see Ananym
     * @return {@link Array перелік} ананимів.
     * @since 1.1.0
     */
    public static Collection<Ananym> findAllAnanymsInString(
            final Iterable<String> words) {
        assert words == null;
        final Array<Ananym> ananyms = new Array<Ananym>();
        final HashMap<Long, HashableWord> mappedWords = new HashMap<Long, HashableWord>();

        long polynomialHash;
        for (final CharSequence word : words) {
            polynomialHash = PolynomialHash.charSequenceHash(word);
            if (!mappedWords.containsKey(polynomialHash)) {
                mappedWords.put(polynomialHash,
                        new HashableWord(word, polynomialHash));
            }
        }

        HashableWord currentMappedWord;
        for (final HashableWord hashableWord : mappedWords.values()) {
            polynomialHash = hashableWord.getReversedPolynomialHashCode();
            currentMappedWord = mappedWords.get(polynomialHash);
            if (currentMappedWord != null) {
                ananyms.add(new Ananym(hashableWord.getCharSequence(),
                        currentMappedWord.getCharSequence()));
                currentMappedWord.setReversedPolynomialHashCode(
                        PolynomialHash.ZERO_HASH);
            }
        }

        return ananyms;
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
     *            набір вхідних символьних послідовностей
     * @param regex
     *            регулярний вираз, для виділення з послідовності певних
     *            символьних послідовностей
     * @see <a href=
     *      "https://sourceforge.net/p/kit26a-cpp/code/HEAD/tree/malokhvii_eduard/src/ua/khpi/oop/malokhvii03/text/">
     *      Версія - 1.0.0</a>
     * @see HashableWord
     * @see PolynomialHash
     * @see Ananym
     * @return {@link Array перелік} ананимів.
     * @since 1.1.0
     */
    public static Collection<Ananym> findAllAnanymsInString(
            final Iterable<String> lines, final String regex) {
        assert lines == null;
        final Array<Ananym> ananyms = new Array<Ananym>();
        final HashMap<Long, HashableWord> mappedWords = new HashMap<Long, HashableWord>();
        final Pattern wordPattern = Pattern.compile(regex);

        long polynomialHash;
        CharSequence word;
        for (final CharSequence line : lines) {
            final Matcher wordMatcher = wordPattern.matcher(line);
            while (wordMatcher.find()) {
                word = wordMatcher.group();
                polynomialHash = PolynomialHash.charSequenceHash(word);
                if (!mappedWords.containsKey(polynomialHash)) {
                    mappedWords.put(polynomialHash,
                            new HashableWord(word, polynomialHash));
                }
            }
        }

        HashableWord currentMappedWord;
        for (final HashableWord hashableWord : mappedWords.values()) {
            polynomialHash = hashableWord.getReversedPolynomialHashCode();
            currentMappedWord = mappedWords.get(polynomialHash);

            if (currentMappedWord != null) {
                ananyms.add(new Ananym(hashableWord.getCharSequence(),
                        currentMappedWord.getCharSequence()));
                currentMappedWord.setReversedPolynomialHashCode(
                        PolynomialHash.ZERO_HASH);
            }
        }

        return ananyms;
    }

    /**
     * Призначений, для пошуку слів паліндромів. Наприклад: "civic". Порівняння
     * рядків виконується за допомогою поліноміального хешу, таким чином можливо
     * зменьшити обсяг використаного часу на порівняння слів.
     *
     * @param words
     *            набір вхідних слів, для обробки на наявність паліндромів
     * @see PolynomialHash
     * @return перелік паліндромів
     * @since 1.2.0
     */
    public static Collection<CharSequence> findAllPalindromes(
            final Iterable<CharSequence> words) {
        assert words == null;
        final HashSet<CharSequence> palindromes = new HashSet<CharSequence>();

        for (final CharSequence word : words) {
            if (!palindromes.contains(word)) {
                if (PolynomialHash.charSequenceHash(word) == PolynomialHash
                        .reversedCharSequenceHash(word)) {
                    palindromes.add(word);
                }
            }
        }

        return palindromes;
    }

    /**
     * Призначений, для пошуку слів паліндромів. Наприклад: "civic". Порівняння
     * рядків виконується за допомогою поліноміального хешу, таким чином можливо
     * зменьшити обсяг використаного часу на порівняння слів.
     *
     * @param lines
     *            набір вхідних символьних послідовностей
     * @param regex
     *            регулярний вираз, для виділення з послідовності певних
     *            символьних послідовностей
     * @return перелік паліндромів
     * @since 1.2.0
     */
    public static Collection<CharSequence> findAllPalindromes(
            final Iterable<CharSequence> lines, final String regex) {
        assert lines == null;
        final Set<CharSequence> palindromes = new HashSet<CharSequence>();
        final Pattern wordPattern = Pattern.compile(regex);

        String word;
        for (final CharSequence line : lines) {
            final Matcher wordMatcher = wordPattern.matcher(line);
            while (wordMatcher.find()) {
                word = wordMatcher.group();
                if (!palindromes.contains(word)) {
                    if (PolynomialHash.charSequenceHash(word) == PolynomialHash
                            .reversedCharSequenceHash(word)) {
                        palindromes.add(word);
                    }
                }
            }
        }

        return palindromes;
    }

    /**
     * Призначений, для пошуку слів паліндромів. Наприклад: "civic". Порівняння
     * рядків виконується за допомогою поліноміального хешу, таким чином можливо
     * зменьшити обсяг використаного часу на порівняння слів.
     *
     * @param words
     *            набір вхідних слів, для обробки на наявність паліндромів
     * @see PolynomialHash
     * @return перелік паліндромів
     * @since 1.2.0
     */
    public static Collection<String> findAllPalindromesInString(
            final Iterable<String> words) {
        assert words == null;
        final HashSet<String> palindromes = new HashSet<String>();

        for (final String word : words) {
            if (!palindromes.contains(word)) {
                if (PolynomialHash.charSequenceHash(word) == PolynomialHash
                        .reversedCharSequenceHash(word)) {
                    palindromes.add(word);
                }
            }
        }

        return palindromes;
    }

    /**
     * Призначений, для пошуку слів паліндромів. Наприклад: "civic". Порівняння
     * рядків виконується за допомогою поліноміального хешу, таким чином можливо
     * зменьшити обсяг використаного часу на порівняння слів.
     *
     * @param lines
     *            набір вхідних символьних послідовностей
     * @param regex
     *            регулярний вираз, для виділення з послідовності певних
     *            символьних послідовностей
     * @return перелік паліндромів
     * @since 1.2.0
     */
    public static Collection<String> findAllPalindromesInString(
            final Iterable<String> lines, final String regex) {
        assert lines == null;
        final Set<String> palindromes = new HashSet<String>();
        final Pattern wordPattern = Pattern.compile(regex);

        String word;
        for (final String line : lines) {
            final Matcher wordMatcher = wordPattern.matcher(line);
            while (wordMatcher.find()) {
                word = wordMatcher.group();
                if (!palindromes.contains(word)) {
                    if (PolynomialHash.charSequenceHash(word) == PolynomialHash
                            .reversedCharSequenceHash(word)) {
                        palindromes.add(word);
                    }
                }
            }
        }

        return palindromes;
    }

    /**
     * Приватний конструктор, для заборони створення утилітарного класу.
     *
     * @since 1.0.0
     */
    private Anagrams() {

    }
}
