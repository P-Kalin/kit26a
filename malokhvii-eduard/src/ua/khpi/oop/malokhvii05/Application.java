package ua.khpi.oop.malokhvii05;

import java.io.IOException;

/**
 * Призначений, для демонстрації виконання отриманного завдання.
 *
 * @author malokhvii-ee
 * @version 1.0.2
 */
public final class Application {

    /**
     * Приватний конструктор, для заборони створення утилітарного класу.
     */
    private Application() {

    }

    /**
     * Призначений, для оголошення точки входу у програму. Можливі аргументи
     * (args):
     * <ul>
     * <li>-h -help Отримання детального опису команд</li>
     * <li>-d -debug Увімкнення відладочної інформації</li>
     * <li>-hs -history-size Налаштування розміру історії команд</li>
     * <li>-cc -command-character Налаштування символу виділення команд</li>
     * <li>-tc -tab-character Налаштування символу виступаючого у ролі
     * табуляції</li>
     * </ul>
     * Використовує точку входу з пакету {@link ua.khpi.oop.malokhvii04}, класу
     * {@link ua.khpi.oop.malokhvii04.Application}.
     *
     * @param args
     *            параметри, отримувані через командний рядок
     * @throws IOException
     *             Помилка під час введення, виведення
     */
    public static void main(final String[] args) throws IOException {
        ua.khpi.oop.malokhvii04.Application.main(args);
    }
}