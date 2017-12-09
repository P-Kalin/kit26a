package ua.khpi.oop.pavlova14;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ForTable {
	private static final String[] columnNames = { "Number of iterations", "Sequential Processing",
			"Parallel Processing" };

	public static void createTable(double[] timeS, double[] timeP) {
		JFrame frame = new JFrame("Results");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		String[][] data = setData(timeS, timeP);
		JTable table = new JTable(data, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);

		frame.getContentPane().add(scrollPane);
		frame.setPreferredSize(new Dimension(450, 200));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private static String[][] setData(double[] timeS, double[] timeP) {
		String[][] data = { { "1", String.valueOf(timeS[0]), String.valueOf(timeP[0]) },

				{ "5", String.valueOf(timeS[1]), String.valueOf(timeP[1]) },

				{ "10", String.valueOf(timeS[2]), String.valueOf(timeP[2]) }, };

		return data;
	}
}
