# №4 Тема: Інтерактивні консольні програми для платформи Java SE.<br/>Мета: Розробка інтерактивних консольних програм мовою Java. Реалізація діалогового режиму роботи з користувачем в консольних програмах.

# 1 Індивідуальне завдання

## 1.1 Розробник

Студент Малохвій Едуард Едуардович, КІТ-26А, Варіант 8 (Завдання №8).

## 1.2 Вимоги

1. Використовуючи програму рішення завдання лабораторної роботи №3, відповідно до прикладної задачі забезпечити обробку команд користувача у вигляді текстового меню:  
    - введення даних;
    - перегляд даних;
    - виконання обчислень;
    - відображення результату;
    - завершення програми і т.д.
    
2. Забезпечити обробку параметрів командного рядка для визначення режиму роботи програми: ?параметр "-h" чи "-help":
    - відображається інформація про автора програми, призначення (індивідуальне завдання), детальний опис режимів роботи (пунктів меню та параметрів командного рядка);
    - параметр "-d" чи "-debug": в процесі роботи програми відображаються додаткові дані, що полегшують налагодження та перевірку працездатності програми: діагностичні повідомлення, проміжні значення змінних, значення тимчасових змінних та ін.

## 1.3 Завдання

Ввести текст. У тексті знайти всі пари слів, з яких одне є обігом (словом навпаки) іншого (наприклад: "abc"-"cba", "def"-"fed"). Результат вивести у вигляді таблиці.

# 2 Розробка програми

## 2.1 Засоби ООП

Під час вирішенн поставленної задачі було використано паттерн Command для інкапсуляції операцій під виглядом команд та патер Decorator для розширення функціоналу команд для надання їм властивостей виведення детальної інформації для подальшої відладки. Для більш гнучкішої реалізації фабрики команд використано рефлексію.

## 2.2 Ієрархія та структура класів

Проект містить наступні пакети:
- shell - містить інтерактивну оболонку та її допоміжні класи.
- сommand - містить у собі перелік команд, та фабрику для їх створення.

<center>
  <img src="http://i.piccy.info/i9/26ff43650dcc9fb4db05ce9a46e9125a/1506440422/131021/1182944/ua_khpi_oop_malokhvii04_shell.png" alt="Діарграма класів із пакету shell">
  <p>Рис. 1 - Діарграма класів із пакету shell</p>
</center>

<center>
  <img src="http://i.piccy.info/i9/3ae492cdf72d77851489eb7d7527789c/1506440464/103808/1182944/ua_khpi_oop_malokhvii04_shell_command.png" alt="Діарграма класів із пакету command">
  <p>Рис. 2 - Діарграма класів із пакету command</p>
</center>

<center>
  <img src="http://i.piccy.info/i9/91acd094787974cecc0722a007120848/1506440496/133723/1182944/ua_khpi_oop_malokhvii04.png" alt="Загальна діаграма класів">
  <p>Рис. 3 - Загальна діаграма класів</p>
</center>

## 2.3 Опис програми

Згідно завдання реалізовано обробку параметрів отриманих через командний рядок, реалізовано довідку для усіх команд, реалізовано необхідний перелік команд.

## 2.4 Важливі фрагменти програми

Нижче наведено фрагмент точки входу програми (<https://sourceforge.net/p/kit26a-cpp/code/HEAD/tree/malokhvii_eduard/src/ua/khpi/oop/malokhvii04/>).

```
package ua.khpi.oop.malokhvii04;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import ua.khpi.oop.malokhvii03.text.AnanymsCollection;
import ua.khpi.oop.malokhvii03.text.WordsCollection;
import ua.khpi.oop.malokhvii04.shell.CommandLineParser;
import ua.khpi.oop.malokhvii04.shell.Shell;
import ua.khpi.oop.malokhvii04.shell.ShellData;
import ua.khpi.oop.malokhvii04.shell.command.Command;
import ua.khpi.oop.malokhvii04.shell.command.CommandFactory;

public final class Application {

    public static void main(final String[] args) throws IOException {
        WordsCollection wordsCollection = new WordsCollection();
        AnanymsCollection ananymsCollection = new AnanymsCollection();

        ShellData shellData = ShellData.getBuilder()
                .setWordsCollection(wordsCollection)
                .setAnanymsCollection(ananymsCollection)
                .setCommandHistorySize(
                        CommandLineParser.parseCommandHistorySize(args))
                .setCommandCharacter(
                        CommandLineParser.parseCommandCharacter(args))
                .setTabCharacter(CommandLineParser.parseTabCharacter(args))
                .setInputStream(System.in).build();

        Shell shell = new Shell(shellData);
        shell.launch();

        for (Command command : CommandLineParser.parseCommands(args,
                shellData)) {
            shell.putCommand(command);
            shell.handleCommand();
        }

        while (shell.isRunning()) {
            Command command = CommandFactory.getCommand(shell.getNextCommand(),
                    shell.getShellData());
            if (command != null) {
                shell.putCommand(command);
                shell.handleCommand();
            } else {
                shell.handleUnknownCommand();
            }
        }
        shell.terminate();
    }

    public static String getApplicationName() {
        File file = null;
        try {
            file = new File(Application.class.getProtectionDomain()
                    .getCodeSource().getLocation().toURI());
        } catch (URISyntaxException exception) {

        }
        return file.getName();
    }
}

```

# 3 Результати роботи

Нижче наведено виведення обчислень у вигляді інтерактивної консолі.

<center>
  <img src="http://i.piccy.info/i9/e51373d10d5bf4a2511412a440e15d27/1506438623/40885/1182944/application_1.png" alt="Фрагмент демонстраційної програми">
  <p>Рис. 3 - Фрагмент демонстраційної програми</p>

  <img src="http://i.piccy.info/i9/dddc588590c122c386b912ed81815c04/1506438823/45450/1182944/application_2.png" alt="Фрагмент демонстраційної програми">
  <p>Рис. 4 - Фрагмент демонстраційної програми</p>

  <img src="http://i.piccy.info/i9/91a6c5b14357d78f73a6631ad38401b7/1506438848/30043/1182944/application_3.png" alt="Фрагмент демонстраційної програми">
  <p>Рис. 5 - Фрагмент демонстраційної програми</p>

  <img src="http://i.piccy.info/i9/08c3f25d6e0531452a6f277fdd90303f/1506438986/50995/1182944/application_4.png" alt="Фрагмент демонстраційної програми">
  <p>Рис. 6 - Фрагмент демонстраційної програми</p>
  
  <img src="http://i.piccy.info/i9/e027831a9e13b040b9bbdb03c12f5ec2/1506440891/52164/1182944/application_5.png" alt="Фрагмент демонстраційної програми">
  <p>Рис. 7 - Фрагмент демонстраційної програми</p>
</center>

# Висновки

У ході виконання лабораторної роботи були покращені навички розробки інтерактивних консольних програм мовою Java. Реалізовано діалоговий режим роботи з користувачем в консольній програмі.