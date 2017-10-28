package ua.khpi.oop.malokhvii06;

import java.io.IOException;

/**
 * Призначений, для демонстрації виконання отриманного завдання.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.0.0
 */
public final class Application {

    /**
     * Призначений, для оголошення точки входу у програму. Можливі аргументи
     * (args):
     * <ul>
     * <li>-h -help Отримання детального опису команд</li>
     * <li>-d -debug Увімкнення відладочної інформації</li>
     * <li>-h -history Виведення історії команд</li>
     * <li>-nd -no-debug Вимикання відладочної інформації (за змовчуванням
     * вимкнена)</li>
     * <li>-e -exit Завершення сеансу інтерактивної оболонки</li>
     * <li>-i -input Введення шляху до текстового файлу</li>
     * <li>-o -output Виведення завантаженого текстового файлу</li>
     * <li>-sot -sort-text Сортування масиву рядків із поточним текстом</li>
     * <li>-srt -search-text Пошук підстроки у вхідному масиві рядків</li>
     * <li>-ser -serialize Серіалізація поточного стану рядків текстового
     * файлу</li>
     * <li>-deser -deserialize Десеріалізація текстового файлу із файлу у
     * вигляді масиву рядків</li>
     * <li>-ss -settings Оновленння поточних налаштувань інтерактивної
     * консолі</li>
     * <li>-ananyms -task08 -t08 Пошук ананимів в тексті (див. рішення до 3-ї
     * лабораторної роботи)</li>
     * </ul>
     *
     * @param args
     *            параметри, отримувані через командний рядок
     * @throws IOException
     *             Помилка під час введення, виведення
     * @since 1.0.0
     */
    public static void main(String[] args) throws IOException {
        ua.khpi.oop.malokhvii04.Application.main(args);
    }

    /**
     * Приватний конструктор, для заборони створення утилітарного класу.
     *
     * @since 1.0.0
     */
    private Application() {

    }
}
