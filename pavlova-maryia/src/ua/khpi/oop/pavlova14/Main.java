package ua.khpi.oop.pavlova14;

public class Main {
	/** Кількість мілісекунд в одній наносекунді. */
	private static final double DIVIDER = 1_000_000;
	private static long start;

	public static void main(final String[] args) {

		System.out.println("Sequential processing");
		start = System.nanoTime();
		Demonstration.demonstrateSequential();
		;
		countTime(start);

		System.out.println("Parallel processing");
		start = System.nanoTime();
		Demonstration.demonstrateParallel();
		countTime(start);
	}

	private static void countTime(long start) {
		System.out.println("Время обработки: " + (System.nanoTime() - start) / DIVIDER);
	}
}
