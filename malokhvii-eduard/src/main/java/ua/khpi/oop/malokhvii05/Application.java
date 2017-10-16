package ua.khpi.oop.malokhvii05;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import ua.khpi.oop.malokhvii04.shell.Shell;
import ua.khpi.oop.malokhvii04.shell.ShellCommandsPool;
import ua.khpi.oop.malokhvii04.shell.commands.Command;

/**
 * Призначений, для демонстрації виконання отриманного завдання.
 *
 * @author malokhvii-eduard
 * @version 1.0.0
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
     *
     * @param args
     *            параметри, отримувані через командний рядок
     * @throws IOException
     *             Помилка під час введення, виведення
     * @since 1.0.0
     */
    public static void main(final String[] args) throws IOException {
        Shell shell = Shell.getInstance();
        shell.launch();

        Command command = null;
        if (args.length != 0 && shell.isRunning()) {
            for (String key : args) {
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
        } catch (URISyntaxException exception) {

        }
        return file.getName();
    }
}