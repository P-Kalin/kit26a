package ua.khpi.oop.malokhvii03.text;

import java.util.Collection;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ua.khpi.oop.malokhvii05.util.Array;

/**
 * Утилітарний клас, призначения для обробки вхідних текстових наборів на
 * наявність анаграм.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.1.0
 * @see Ananym
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
        Array<Ananym> ananyms = new Array<Ananym>();
        HashMap<Long, HashableWord> mappedWords = new HashMap<Long, HashableWord>();
        Pattern wordPattern = Pattern.compile(regex);

        long polynomialHash;
        CharSequence word;
        Matcher wordMatcher = wordPattern.matcher(charSequence);
        while (wordMatcher.find()) {
            word = wordMatcher.group();
            polynomialHash = PolynomialHash.charSequenceHash(word);
            if (!mappedWords.containsKey(polynomialHash)) {
                mappedWords.put(polynomialHash,
                        new HashableWord(word, polynomialHash));
            }
        }

        HashableWord currentMappedWord;
        for (HashableWord hashableWord : mappedWords.values()) {
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
        Array<Ananym> ananyms = new Array<Ananym>();
        HashMap<Long, HashableWord> mappedWords = new HashMap<Long, HashableWord>();

        long polynomialHash;
        for (CharSequence word : words) {
            polynomialHash = PolynomialHash.charSequenceHash(word);
            if (!mappedWords.containsKey(polynomialHash)) {
                mappedWords.put(polynomialHash,
                        new HashableWord(word, polynomialHash));
            }
        }

        HashableWord currentMappedWord;
        for (HashableWord hashableWord : mappedWords.values()) {
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
            final Iterable<CharSequence> lines, final String regex) {
        assert lines == null;
        Array<Ananym> ananyms = new Array<Ananym>();
        HashMap<Long, HashableWord> mappedWords = new HashMap<Long, HashableWord>();
        Pattern wordPattern = Pattern.compile(regex);

        long polynomialHash;
        CharSequence word;
        for (CharSequence line : lines) {
            Matcher wordMatcher = wordPattern.matcher(line);
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
        for (HashableWord hashableWord : mappedWords.values()) {
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
        Array<Ananym> ananyms = new Array<Ananym>();
        HashMap<Long, HashableWord> mappedWords = new HashMap<Long, HashableWord>();

        long polynomialHash;
        for (CharSequence word : words) {
            polynomialHash = PolynomialHash.charSequenceHash(word);
            if (!mappedWords.containsKey(polynomialHash)) {
                mappedWords.put(polynomialHash,
                        new HashableWord(word, polynomialHash));
            }
        }

        HashableWord currentMappedWord;
        for (HashableWord hashableWord : mappedWords.values()) {
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
     *            регулярний вираз, для виділення з послідовності певнх
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
        Array<Ananym> ananyms = new Array<Ananym>();
        HashMap<Long, HashableWord> mappedWords = new HashMap<Long, HashableWord>();
        Pattern wordPattern = Pattern.compile(regex);

        long polynomialHash;
        CharSequence word;
        for (CharSequence line : lines) {
            Matcher wordMatcher = wordPattern.matcher(line);
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
        for (HashableWord hashableWord : mappedWords.values()) {
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
     * Приватний конструктор, для заборони створення утилітарного класу.
     *
     * @since 1.0.0
     */
    private Anagrams() {

    }
}
