package ua.khpi.oop.lytvyn06;

/**
 * Відповідає за виконання програми.
 * 
 * @author student Lytvyn I.I. KIT-26A
 */
public class Main {
	/**
	 * Розмежувач
	 */
	public static String LINE = "----------------------------------------------"
	        + "--------------------------\n";

	/**
	 * Головний метод запуску програми
	 * 
	 * @param args
	 *            параметри запуску
	 * @throws Exception
	 *             виняткова ситуація
	 */
	public static void main(String[] args) throws Exception {
		for (final String comand : args) {
			if (comand.equals("-h") || comand.equals("-help")) {
				System.out.println(LINE);
				System.out.format("Автор: студент КІТ-26А Литвин І.І.\n\n");
				System.out.format("Призначення програми: Визначення та"
				        + " виведення, яких літер (голосних чи приголосних)\n"
				        + "більше в кожному реченні введеного тексту.\n\n");
				System.out.format("	Опис пунктів меню та параметрів командного"
				        + " рядка:\n\n");
				System.out.format("	input - команда для вводу вхідних даних у"
				        + " вигляді тексту\n\n");
				System.out.format("	read - команда для зчитування вхідних даних"
				        + " у вигляді контейнеру з файлу\n\n");
				System.out.format("	show - команда для перегляду"
				        + " вмісту контейнеру\n\n");
				System.out.format("	view - команда для перегляду введених даних"
				        + "(відображає текст)\n\n");
				System.out.format("	search - команда для пошуку елементів"
				        + " у контейнері.\n\n");
				System.out.format("	sort - команда для сортування елементів"
				        + "у контейнері за алгоритмом MergeSort\n\n");
				System.out.format("	calc - команда, що виконує пошук та"
				        + " підрахунок голосних\n\t"
				        + "та приголосних у речені.\n\n");
				System.out.format("	calc_all - команда, що виконує пошук та"
				        + " підрахунок голосних\n	та приголосних у кожному"
				        + " речені тексту.\n\n");
				System.out.format("	result - відображення результату обчислень"
				        + " у вигляді таблиці\n\n");
				System.out.format("	save - команда для збереження даних у"
				        + " вигляді контейнеру до файлу\n\n");
				System.out.format("	exit - завершення програми, вихід\n\n");
				System.out.format("	-h або -help відображається інформація про"
				        + " автора програми,\n	призначення (індивідуальне"
				        + " завдання), детальний опис режимів роботи\n	"
				        + "(пунктів меню та параметрів командного рядка);\n\n");
				System.out.println(LINE);
			}
		}
		System.out.format("	Список доступних команд:\n\n");
		System.out.format("	input - введення даних\n");
		System.out.format("	read - зчитування даних\n");
		System.out.format("	show - перегляд контейнеру\n");
		System.out.format("	view - перегляд даних\n");
		System.out.format("	search - пошук даних\n");
		System.out.format("	sort - сортування даних\n");
		System.out.format("	calc - виконання обчислень для речення\n");
		System.out.format("	calc_all - виконання обчислень для тексту\n");
		System.out.format("	result - відображення результату\n");
		System.out.format("	save - збереження даних\n");
		System.out.format("	exit - завершення програми\n");

		DialogHelper.start();
	}
}