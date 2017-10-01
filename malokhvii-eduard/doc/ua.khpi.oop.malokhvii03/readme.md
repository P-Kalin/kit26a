# �3 ����: �������� �����. ������� ������ � �����.<br/>����: �������� ������� ���������� �����. ������� ������� �������� ���������� ����� � ������������� ������ � �����.

# 1 ������������ ��������

## 1.1 ���������

������� ������� ������ ����������, ʲ�-26�, ������ 8 (�������� �8).

## 1.2 ������

1. ��������� �� ���������������� ��������� �������� ����� Java � ���������� Eclipse ��� �������� ��������� ������ �� �������, �� ������� ���������� �� ������� ������� �� ������ �� 15 ���������� �� ������� ������ �������� � ������ ����� (Class String, Manipulating Characters in a String, Comparing Strings and Portions of Strings).
2. ��� ������� ���������� ����� ��������������� �������.
3. ���������������� ������������ ��'���� ����� StringBuilder ��� StringBuffer.
4. ��� ������� ����� ��������������� �����-������ (��������� ������� ���������� �����, ���. Helper Class).
5. ������������� ��������������� ������ ������� ���������� ������.

## 1.3 ��������

������ �����. � ����� ������ �� ���� ���, � ���� ���� � ����� (������ �������) ������ (���������: "abc"-"cba", "def"-"fed"). ��������� ������� � ������ �������.

# 2 �������� ��������

## 2.1 ������ ���

ϳ� ��� ������� ����������� ������ ���� ����������� ���������, ��� ��������� ��� �� �������� ���, ����� ����� ������� �������� �������� ������� �������������� ��������. �����, ��������� �������� �������� ���� ��� ��� �� ������ ������� ������, ��� ������ ����� �� �������.

## 2.2 �������� �� ��������� �����

������ ������ ������� ������:
- text - ������ � ��� �������� �����, ��� ������� ������ �� �������� �������.

<center>
  <img src="http://i.piccy.info/i9/19e02ddf7198b616f169f504626c8f5e/1506437439/49933/1182944/ua_khpi_oop_malokhvii03_text.png" alt="ĳ������� ����� �� ������ text">
  <p>���. 1 - ĳ������� ����� �� ������ text</p>
</center>

<center>
  <img src="http://i.piccy.info/i9/b1ad602048f654ae20bee5311aafdfa5/1506437497/55294/1182944/ua_khpi_oop_malokhvii03.png" alt="�������� ������� �����">
  <p>���. 2 - �������� ������� �����</p>
</center>

## 2.3 ���� ��������

��� ������� ������������ ������� ����������� ������ � ������ ua.khpi.oop.malokhvii04. ��� ������������ ������� ��� ���� ����������� ������� ���-������� �� ���-������� ��� ���������� ���.

<center>
  <img src="http://i.piccy.info/i9/8f7a49efb072e262ffa5c9f18fcfb299/1506437189/30392/1182944/words_collection.png" alt="������� �������� �������� ���">
  <p>���. 3 - ������� �������� �������� ���</p>
</center>

## 2.4 ������ ��������� ��������

����� �������� �������� ����������� ����� ��� ������ �������. ���� ��������� ��������� ��� �� ���������� (<https://sourceforge.net/p/kit26a-cpp/code/HEAD/tree/malokhvii_eduard/src/ua/khpi/oop/malokhvii03/>).

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

# 3 ���������� ������

����� �������� ��������� ��������� � ������ ������������ ������.

<center>
  <img src="http://i.piccy.info/i9/e51373d10d5bf4a2511412a440e15d27/1506438623/40885/1182944/application_1.png" alt="�������� �������������� ��������">
  <p>���. 4 - �������� �������������� ��������</p>

  <img src="http://i.piccy.info/i9/dddc588590c122c386b912ed81815c04/1506438823/45450/1182944/application_2.png" alt="�������� �������������� ��������">
  <p>���. 5 - �������� �������������� ��������</p>

  <img src="http://i.piccy.info/i9/91a6c5b14357d78f73a6631ad38401b7/1506438848/30043/1182944/application_3.png" alt="�������� �������������� ��������">
  <p>���. 6 - �������� �������������� ��������</p>

  <img src="http://i.piccy.info/i9/08c3f25d6e0531452a6f277fdd90303f/1506438986/50995/1182944/application_4.png" alt="�������� �������������� ��������">
  <p>���. 7 - �������� �������������� ��������</p>
</center>

# ��������

� ��� ��������� ����������� ������ ���� �������� ������� ������������ ������, ��������� �������� ��������� ���� � ��� Java. ������� ������� ��������� ���������� �����.