package ua.khpi.oop.pavlova16;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Window extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton button = new JButton("Start");
	private JTextField input = new JTextField("", 5);
	private JLabel label = new JLabel("Введите команду");
	private JLabel label1 = new JLabel("1. Создание списка из 5 элементов");
	private JLabel label2 = new JLabel("2. Создание нового списка на основе предыдущего");
	private JLabel label3 = new JLabel("Введите команду");
	private JLabel label4 = new JLabel("Введите команду");
	private JLabel label5 = new JLabel("Введите команду");
	private JLabel label6 = new JLabel("Введите команду");
	private JRadioButton radio1 = new JRadioButton("-a");
	private JRadioButton radio2 = new JRadioButton("-c");
	private JCheckBox check = new JCheckBox("Check", false);

	public Window() {

		super("Laboratory work #16");
		this.setBounds(400, 400, 500, 150);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		button.setBounds(5, 10, 20, 15);
		Container container = this.getContentPane();
		container.setLayout(new GridLayout(3, 2, 2, 2));
		container.add(label);
		container.add(input);
		container.add(label1);
		container.add(label2);

		ButtonGroup group = new ButtonGroup();
		group.add(radio1);
		group.add(radio2);
		container.add(radio1);

		radio1.setSelected(true);
		container.add(radio2);
		container.add(check);
		button.addActionListener((ActionListener) new ButtonEventListener());
		container.add(button);
	}

	class ButtonEventListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String message = "";
			message += "Button was pressed\n";
			message += "Command is " + input.getText() + "\n";
			message += (radio1.isSelected() ? "Radio #1" : "Radio #2") + " is selected\n";
			message += "CheckBox is " + ((check.isSelected()) ? "checked" : "unchecked");
			JOptionPane.showMessageDialog(null, message, "Output", JOptionPane.PLAIN_MESSAGE);
		}
	}

	public static void main(String[] args) {
		Window app = new Window();
		app.setVisible(true);
	}
}
