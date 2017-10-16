package ua.khpi.oop.malokhvii05;

import java.io.IOException;

/**
 * Призначений, для демонстрації виконання отриманного завдання.
 *
 * @author malokhvii-ee
 * @version 1.1.0
 * @since 1.0.0
 */
public final class Application {

    /**
     * Приватний конструктор, для заборони створення утилітарного класу.
     *
     * @since 1.0.0
     */
    private Application() {

    }

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
     * <li>-ananyms -task08 -t08 Пошук ананимів в тексті (див.
     * {@link ua.khpi.oop.malokhvii03.text.Anagrams алгоритм пошуку})</li>
     * </ul>
     * Використовує точку входу з пакету {@link ua.khpi.oop.malokhvii06}, класу
     * {@link ua.khpi.oop.malokhvii06.Application}.
     *
     * @param args
     *            параметри, отримувані через командний рядок
     * @throws IOException
     *             Помилка під час введення, виведення
     * @since 1.0.0
     */
    public static void main(final String[] args) throws IOException {
        ua.khpi.oop.malokhvii06.Application.main(args);
    }
}