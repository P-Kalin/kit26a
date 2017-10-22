package ua.khpi.oop.malokhvii04;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Locale;
import java.util.Set;

import org.reflections.Reflections;

import ua.khpi.oop.malokhvii04.shell.Shell;
import ua.khpi.oop.malokhvii04.shell.ShellCommandsPool;
import ua.khpi.oop.malokhvii04.shell.ShellResources;
import ua.khpi.oop.malokhvii04.shell.commands.AbstractCommand;
import ua.khpi.oop.malokhvii04.shell.commands.Command;

/**
 * Призначений, для демонстрації виконання отриманного завдання.
 *
 * @author malokhvii-eduard (malokhvii.ee@gmail.com)
 * @version 1.2.0
 * @since 1.0.0
 */
public final class Application {

    /**
     * Назва локалізації за змовчуванням.
     *
     * @since 1.2.0
     */
    private static final String DEFAULT_LOCALE = "en";

    /**
     * Перелік усіх допустимих локалізацій.
     *
     * @since 1.2.0
     */
    private static final String[] LOCALES = { Application.DEFAULT_LOCALE };

    /**
     * Розташування усіх классів команд, для інтерактивної оболонки.
     *
     * @since 1.2.0
     */
    private static final String BASE_PACKAGE = "ua.khpi.oop.malokhvii04."
            + "shell.commands";

    /**
     * Призначений, для отримання ім'я файлу програми.
     *
     * @return ім'я файлу програми
     * @since 1.0.0
     */
    public static String getApplicationName() {
        File file = null;
        try {
            file = new File(Application.class.getProtectionDomain()
                    .getCodeSource().getLocation().toURI());
        } catch (final URISyntaxException exception) {

        }
        return file.getName();
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
     * <li>-o -output Виведення завантаженого текстового файлу</li>
     * <li>-st -sort-text Сортування масиву рядків із поточним текстом</li>
     * <li>-ser -serialize Серіалізація поточного стану рядків текстового
     * файлу</li>
     * <li>-deser -deserialize Десеріалізація текстового файлу із файлу у
     * вигляді масиву рядків</li>
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
    public static void main(final String[] args) throws IOException {
        Application.setLocales();

        final Shell shell = Shell.getInstance();
        shell.launch();

        final Reflections reflections = new Reflections(
                Application.BASE_PACKAGE);

        final Set<Class<? extends AbstractCommand>> subTypes = reflections
                .getSubTypesOf(AbstractCommand.class);
        for (final Class<? extends AbstractCommand> subTypeClass : subTypes) {
            try {
                Class.forName(subTypeClass.getCanonicalName());
            } catch (final ClassNotFoundException exception) {

            }
        }

        Command command = null;
        if (args.length != 0 && shell.isRunning()) {
            for (final String key : args) {
                command = ShellCommandsPool.getCommand(key);
                if (command != null) {
                    shell.putCommand(command);
                    shell.handleCommand();
                }
            }
        }

        while (shell.isRunning()) {
            command = ShellCommandsPool.getCommand(shell.getNextCommand());
            if (command != null) {
                shell.putCommand(command);
                shell.handleCommand();
            } else {
                shell.handleUnknownCommand();
            }
        }
    }

    /**
     * Встановлення допустимих локалізацій, в об'єкт відповідаючий за
     * контролювання ресурсів інтерактивної оболонки.
     *
     * @since 1.2.0
     */
    private static void setLocales() {
        final ShellResources shellResources = ShellResources.getInstance();
        for (final String locale : Application.LOCALES) {
            shellResources.setLocale(locale, new Locale(locale));
        }
        shellResources.setDefaultLocale(Application.DEFAULT_LOCALE,
                new Locale(Application.DEFAULT_LOCALE));
    }

    /**
     * Приватний конструктор, для заборони створення утилітарного класу.
     *
     * @since 1.0.0
     */
    private Application() {

    }
}
