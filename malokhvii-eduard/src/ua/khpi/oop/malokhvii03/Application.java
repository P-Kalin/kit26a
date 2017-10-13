package ua.khpi.oop.malokhvii03;

import java.io.IOException;

/**
 * Призначений, для демонстрації виконання отриманного завдання.
 *
 * @author malokhvii-eduard
 * @version 1.0.1
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
     * @since 1.0.0
     */
    public static void main(final String[] args) throws IOException {
        ua.khpi.oop.malokhvii04.Application.main(args);
    }
}
