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

/**
 * Призначений, для демонстрації виконання отриманного завдання.
 *
 * @author malokhvii-ee
 * @version 1.0.1
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

    /**
     * Призначений, для отримання ім'я файлу програми.
     *
     * @return ім'я файлу програми
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
