package ua.khpi.oop.pavlova14;

import javax.swing.JFrame;

public class Main {
	/** Кількість мілісекунд в одній наносекунді. */
	private static final double DIVIDER = 1_000_000;
	private static long start;

	public static void main(final String[] args) {
		double[] timeS = new double[3];
		double[] timeP = new double[3];

		start = System.nanoTime();
		Demonstration.demonstrateSequential(1);
		timeS[0] = countTime(start);
		start = System.nanoTime();
		Demonstration.demonstrateSequential(5);
		timeS[1] = countTime(start);
		start = System.nanoTime();
		Demonstration.demonstrateSequential(10);
		timeS[2] = countTime(start);

		start = System.nanoTime();
		Demonstration.demonstrateParallel(1);
		timeP[0] = countTime(start);
		start = System.nanoTime();
		Demonstration.demonstrateParallel(5);
		timeP[1] = countTime(start);
		start = System.nanoTime();
		Demonstration.demonstrateParallel(10);
		timeP[2] = countTime(start);

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame.setDefaultLookAndFeelDecorated(true);
				ForTable.createTable(timeS, timeP);
			}
		});
	}

	private static double countTime(long start) {
		return (System.nanoTime() - start) / DIVIDER;
	}
}
