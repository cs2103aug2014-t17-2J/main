package userInterface;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import dataStorage.DataHandler;
import logic.CommandHandler;

import javax.swing.JScrollBar;

import java.awt.Dimension;
import java.awt.ScrollPane;
import java.awt.Scrollbar;

import javax.swing.JTable;

public class UserIntSwing {

	private JFrame frame;
	private JTextField textField;
	private JTable table;

	/**
	 * @wbp.nonvisual location=-28,119
	 */
	//private JTextArea textArea_1;

	/**
	 * Launch the application.
	 */
	public void execute() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserIntSwing window = new UserIntSwing();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserIntSwing() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 460, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblDisplay = new JLabel("My Label");
		lblDisplay.setBounds(10, 42, 157, 20);
		frame.getContentPane().add(lblDisplay);

		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(256, 132, 178, 96);
		textArea_1.setWrapStyleWord(true);
		frame.getContentPane().add(textArea_1);

		ArrayList<String> taskList = new ArrayList<String>();

		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String textInput = "";
				textInput += textField.getText();
				lblDisplay.setText(textField.getText());

				taskList.add(textInput);

				// textArea_1.append(taskList+"\n"); //displays the entire
				// arrayList in [a,b,c] format

				textField.setText("");
			}
		});
		textField.setBounds(10, 11, 294, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnEnter = new JButton("ENTER");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// lblDisplay.setText(textField.getText());
				// textArea.setText(textField.getText());
				// textField.setText("");

				for (int i = 0; i < taskList.size(); i++) {
					textArea_1.append((String) taskList.get(i) + "\n");
				}
			}
		});
		btnEnter.setBounds(331, 10, 89, 23);
		frame.getContentPane().add(btnEnter);

		String[] columnNames = { "task", "description", "start date",
				"end date", "start time", "end time", "priority" };
		Object[][] data = {
				{ "meeting", "about cs2101", "4/10/2014", "", "2pm", "4pm" },
				{ "cycling", "at east coast", "5/10/2014", "", "5pm", "8pm" } };
		table = new JTable(data, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(50, 10));
		table.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(table);
		//frame.getContentPane().add(scrollPane);
		//frame.getContentPane().add(table);

	}
}
