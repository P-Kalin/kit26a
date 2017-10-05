package ua.khpi.oop.pavlova04;

public final class ExtraOptions {
	public static boolean debugParam = false;

	private ExtraOptions() {

	}

	public static final void forHelperParam(String[] args) {
		for (String param : args) {
			if (param.equals("-h") || param.equals("helper")) {
				System.out.format("\t\tАвтор: Павлова М.В., КИТ-26а\n");
				System.out.println("\tІндивідуальне завдання: ");
				System.out.println("Вивести на екран найдовші та найкоротші слова кожного речення введенного тексту.");
				System.out.format("\t 1. input.\n");
				System.out.println("Команда запускає введення користувачем вхідного тексту. ");
				System.out.format("\t 2. data.\n");
				System.out.println("Команда запускає виведення на екран вхідного тексту.");
				System.out.format("\t 3. calculate.\n");
				System.out.println("Команда запускає функції пошуку найдовшого та найменшого слів у кожному реченні.");
				System.out.format("\t 4. result.\n");
				System.out.println("Команда запускає виведення на екран результату роботи.");
				System.out.format("\t 5. exit.\n");
				System.out.println("Команда завершує роботу програми.");
			}
		}
	}

	public static final void forDebugParam(String[] args) {
		for (String param : args) {
			if (param.equals("-d") || param.equalsIgnoreCase("-debug"))
				debugParam = true;
		}
	}
}
