# №3 Тема: Утилітарні класи. Обробка масивів і рядків.<br/>Мета: Розробка власних утилітарних класів. Набуття навичок вирішення прикладних задач з використанням масивів і рядків.

# 1 Індивідуальне завдання

## 1.1 Розробник

Студент Малохвій Едуард Едуардович, КІТ-26А, Варіант 8 (Завдання №8).

## 1.2 Вимоги

1. Розробити та продемонструвати консольну програму мовою Java в середовищі Eclipse для вирішення прикладної задачі за номером, що відповідає збільшеному на одиницю залишку від ділення на 15 зменшеного на одиницю номера студента в журналі групи (Class String, Manipulating Characters in a String, Comparing Strings and Portions of Strings).
2. При вирішенні прикладних задач використовувати латинку.
3. Продемонструвати використання об'єктів класу StringBuilder або StringBuffer.
4. Для обробки даних використовувати класи-утиліти (особливий випадок допоміжного класу, див. Helper Class).
5. Забороняється використовувати засоби обробки регулярних виразів.

## 1.3 Завдання

Ввести текст. У тексті знайти всі пари слів, з яких одне є обігом (словом навпаки) іншого (наприклад: "abc"-"cba", "def"-"fed"). Результат вивести у вигляді таблиці.

# 2 Розробка програми

## 2.1 Засоби ООП

Під час вирішенн поставленної задачі було використано ітератори, для отримання слів із колекції слів, таким чином змінюючи ітератор колекції можливо вдосконалювати алгоритм. Тобто, наприклад отримати анаграми лише для слів не більших певного розміру, або певной літери на початку.

## 2.2 Ієрархія та структура класів

Проект містить наступні пакети:
- text - містить у собі необхідні класи, для обробки тексту на наявнсть анаграм.

<center>
  <img src="http://i.piccy.info/i9/19e02ddf7198b616f169f504626c8f5e/1506437439/49933/1182944/ua_khpi_oop_malokhvii03_text.png" alt="Діарграма класів із пакету text">
  <p>Рис. 1 - Діарграма класів із пакету text</p>
</center>

<center>
  <img src="http://i.piccy.info/i9/b1ad602048f654ae20bee5311aafdfa5/1506437497/55294/1182944/ua_khpi_oop_malokhvii03.png" alt="Загальна діаграма класів">
  <p>Рис. 2 - Загальна діаграма класів</p>
</center>

## 2.3 Опис програми

Для надання інтерактивної оболнки використано рішення з пакету ua.khpi.oop.malokhvii04. Для індексування вхідних слів було використано декілька хеш-таблиць та хеш-множину для збереження слів.

<center>
  <img src="http://i.piccy.info/i9/8f7a49efb072e262ffa5c9f18fcfb299/1506437189/30392/1182944/words_collection.png" alt="Приклад стуктури колекції слів">
  <p>Рис. 3 - Приклад стуктури колекції слів</p>
</center>

## 2.4 Важливі фрагменти програми

Нижче наведено фрагмент утилітарного класу для пошуку ананимів. Інші фрагменти детальніше див за посиланням (<https://sourceforge.net/p/kit26a-cpp/code/HEAD/tree/malokhvii_eduard/src/ua/khpi/oop/malokhvii03/>).

```
package ua.khpi.oop.malokhvii03.text;

import java.util.Iterator;

public final class Anagrams {

    private Anagrams() {

    }

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

    public static AnanymsCollection findAllAnanyms(
            final WordsCollection words) {
        return findAllAnanyms(words, words.getWordIterator());
    }

    public static AnanymsCollection findAllAnanyms(final WordsCollection words,
            final char letter) {
        return findAllAnanyms(words, words.getWordFixedLetterIterator(letter));
    }

    public static AnanymsCollection findAllAnanyms(final WordsCollection words,
            final char letter, final int size) {
        return findAllAnanyms(words,
                words.getWordFixedSizeAndLetterIterator(letter, size));
    }

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

```

# 3 Результати роботи

Нижче наведено виведення обчислень у вигляді інтерактивної консолі.

<center>
  <img src="http://i.piccy.info/i9/e51373d10d5bf4a2511412a440e15d27/1506438623/40885/1182944/application_1.png" alt="Фрагмент демонстраційної програми">
  <p>Рис. 4 - Фрагмент демонстраційної програми</p>

  <img src="http://i.piccy.info/i9/dddc588590c122c386b912ed81815c04/1506438823/45450/1182944/application_2.png" alt="Фрагмент демонстраційної програми">
  <p>Рис. 5 - Фрагмент демонстраційної програми</p>

  <img src="http://i.piccy.info/i9/91a6c5b14357d78f73a6631ad38401b7/1506438848/30043/1182944/application_3.png" alt="Фрагмент демонстраційної програми">
  <p>Рис. 6 - Фрагмент демонстраційної програми</p>

  <img src="http://i.piccy.info/i9/08c3f25d6e0531452a6f277fdd90303f/1506438986/50995/1182944/application_4.png" alt="Фрагмент демонстраційної програми">
  <p>Рис. 7 - Фрагмент демонстраційної програми</p>
</center>

# Висновки

У ході виконання лабораторної роботи були покращені навички використання масивів, досліджено поведінку рядкового типу в мові Java. Вивчено принцип написання утилітарних класів.