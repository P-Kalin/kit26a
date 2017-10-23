# №2 Тема: Консольні програми для платформи Java SE. Прості алгоритми обробки даних.<br/>Мета: Розробка простих консольних програм для платформи Java SE. 

# 1 Індивідуальне завдання

## 1.1 Розробник

Студент Малохвій Едуард Едуардович, КІТ-26А, Варіант 8 (Завдання №6).

## 1.2 Вимоги

1. Розробити та продемонструвати програму мовою Java в середовищі Eclipse для вирішення прикладної задачі за номером, що відповідає збільшеному на одиницю залишку від ділення на 10 зменшеного на одиницю номера студента в журналі групи.
2. Для визначення вхідних даних використовувати генератор псевдовипадкових чисел (java.util.Random) та забезпечити циклічне (принаймні десять ітерацій) знаходження результату рішення прикладної задачі.
3. Забезпечити виведення до консолі відповідних значень вхідних даних та результатів обчислень у вигляді таблиці.
4. Забороняється використання даних типу String та масивів при знаходженні рішення прикладної задачі.
5. Застосувати функціональну (процедурну) декомпозицію і забезпечити рішення прикладної задачі за допомогою відповідних статичних методів.

## 1.3 Завдання

Перевірити чи дорівнює сума перших трьох цифр сумі останніх трьох цифр в десятковому запису 6-значного цілого числа.

# 2 Розробка програми

## 2.1 Засоби ООП 

Під час вирішення поставленної задачі було акцентовано увагу на інкапсуляції процесу обробки та отримання вхідних даних. Тобто об'єкт циклу подій оброблює контейнер даних який забезпечує загальний інтерфейс для певного набору методів (отримання, обробку, відображення). Об'єкт циклу лише контролює послідовність подій під час виконання програми, за обчислення відповідає лише контейнер даних.

<p align="center">
    <img src="https://github.com/oop-khpi/kit26a/blob/master/malokhvii-eduard/doc/malokhvii02/images/events.png?raw=true">
    Рис. 1 - Діаграма взаємодії подій
</p>

## 2.2 Ієрархія та структура класів

Проект містить наступні пакети:
- data - містить у собі реалізацію логіки обробки даних
- event - містить у собі реалізацію логіки обробки подій, їх збереження то контролю.

Нижче наведено діаграму класів отриману за допомогою розширення для середи розробки Eclipse - ObjectAid UML Diagram.

<p align="center">
    <img src="https://github.com/oop-khpi/kit26a/blob/master/malokhvii-eduard/doc/malokhvii02/images/ua.khpi.oop.malokhvii02.data.png?raw=true"><br/>
    Рис. 2 - Діаграма класів із пакету data
</p>

<p align="center">
    <img src="https://github.com/oop-khpi/kit26a/blob/master/malokhvii-eduard/doc/malokhvii02/images/ua.khpi.oop.malokhvii02.event.png?raw=true">
    Рис. 3 - Діаграма класів із пакету event
</p>

<p align="center">
    <img src="https://github.com/oop-khpi/kit26a/blob/master/malokhvii-eduard/doc/malokhvii02/images/ua.khpi.oop.malokhvii02.png?raw=true">
    Рис. 4 - Загальна діаграма класів
</p>

## 2.3 Опис програми

За умовою лабораторної роботи було виведено отримані значення під час обчислення у вигляді таблиці, вхідні значення згенеровано за допомогою класу java.util.Random. Для форматування виведення було використано format та printf.

## 2.4 Важливі фрагменти програми

Нижче наведено фрагмент виконавця завдання для десятичних значень. Інші фрагменти детальніше див за посиланням (<https://sourceforge.net/p/kit26a-cpp/code/HEAD/tree/malokhvii_eduard/src/ua/khpi/oop/malokhvii02/>).

```
package ua.khpi.oop.malokhvii02;

import java.util.Scanner;

import ua.khpi.oop.malokhvii02.data.DataContainer;
import ua.khpi.oop.malokhvii02.data.NumberEqualityContainer;
import ua.khpi.oop.malokhvii02.event.EventLoop;
import ua.khpi.oop.malokhvii02.event.EventsContainer;
import ua.khpi.oop.malokhvii02.event.GlobalEventsContainer;

public final class Application {

    private Application() {

    }

    public static void main(final String[] args) {
        DataContainer dataContainer = new NumberEqualityContainer();
        EventsContainer loopEvents = GlobalEventsContainer.getInstance();
        EventLoop eventLoop = new EventLoop(dataContainer, loopEvents,
                System.out, new Scanner(System.in));

        eventLoop.launchLoop();
        while (eventLoop.isRunning()) {
            eventLoop.handleCurrentEvent();
        }
        eventLoop.closeStream();
    }
}
```

# 3 Результати роботи

Нижче наведено виведення обчислень у вигляді таблиці та інтерактивної консолі.

<p align="center">
    <img src="https://github.com/oop-khpi/kit26a/blob/master/malokhvii-eduard/doc/malokhvii02/images/application-1.png?raw=true"><br/>
    Рис. 5 - Результат виконання
</p>

<p align="center">
    <img src="https://github.com/oop-khpi/kit26a/blob/master/malokhvii-eduard/doc/malokhvii02/images/application-2.png?raw=true"><br/>
    Рис. 6 - Результат виконання
</p>

# Висновки

У ході виконання лабораторної роботи були покращені навички взаємодії з потоковим виведення та введення, колекціями. Досліджено склад пакету java.util.