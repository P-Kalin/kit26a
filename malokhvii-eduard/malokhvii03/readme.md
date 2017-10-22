# №3 Тема: Утилітарні класи. Обробка масивів і рядків.<br>Мета: Розробка власних утилітарних класів. Набуття навичок вирішення прикладних задач з використанням масивів і рядків.

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

<p align="center">
    <img src="https://github.com/oop-khpi/kit26a/blob/master/malokhvii-eduard/doc/malokhvii03/images/ua.khpi.oop.malokhvii03.text.png?raw=true">
    Рис. 1 - Діаграма класів із пакету text
</p>

<p align="center">
    <img    src="https://github.com/oop-khpi/kit26a/blob/master/malokhvii-eduard/doc/malokhvii03/images/ua.khpi.oop.malokhvii03.png?raw=true"> 
    Рис. 2 - Загальна діаграма класів
</p>

## 2.3 Опис програми

Для надання інтерактивної оболнки використано рішення з пакету ua.khpi.oop.malokhvii04\. Для індексування вхідних слів було використано декілька хеш-таблиць та хеш-множину для збереження слів.

<p align="center">
    <img src="https://github.com/oop-khpi/kit26a/blob/master/malokhvii-eduard/doc/malokhvii03/images/words-collection.png?raw=true">
    Рис. 3 - Приклад стуктури колекції слів
</p>

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

<p align="center">
    <img src="https://github.com/oop-khpi/kit26a/blob/master/malokhvii-eduard/doc/malokhvii03/images/application-1.png?raw=true">
    Рис. 3 - Приклад стуктури колекції слів 
</p>

<p align="center">
    <img src="https://github.com/oop-khpi/kit26a/blob/master/malokhvii-eduard/doc/malokhvii03/images/application-2.png?raw=true"> 
    Рис. 5 - Фрагмент демонстраційної програми
</p> 
   
<p align="center">
    <img src="https://github.com/oop-khpi/kit26a/blob/master/malokhvii-eduard/doc/malokhvii03/images/application-3.png?raw=true">
    Рис. 6 - Фрагмент демонстраційної програми
</p>
    
<p align="center">
    <img src="https://github.com/oop-khpi/kit26a/blob/master/malokhvii-eduard/doc/malokhvii03/images/application-4.png?raw=true">
    Рис. 7 - Фрагмент демонстраційної програми
</p>

# Висновки

У ході виконання лабораторної роботи були покращені навички використання масивів, досліджено поведінку рядкового типу в мові Java. Вивчено принцип написання утилітарних класів.
