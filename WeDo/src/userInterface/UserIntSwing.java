package userInterface;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;


public class UserIntSwing {

	private JFrame frame;
	private JTextField textField;
	/**
	 * @wbp.nonvisual location=-28,119
	 */
	protected JTextArea textArea;
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
		lblDisplay.setBounds(26, 114, 157, 20);
		frame.getContentPane().add(lblDisplay);
	
		JTextArea textArea = new JTextArea(22, 80);
		frame.getContentPane().add(textArea);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(26, 145, 264, 90);
		frame.getContentPane().add(textArea_1);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblDisplay.setText(textField.getText());
				textArea.setText(textField.getText());
				textArea_1.setText(textField.getText());
				textField.setText("");
			}
		});
		textField.setBounds(26, 72, 294, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnEnter = new JButton("ENTER");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblDisplay.setText(textField.getText());
				textArea.setText(textField.getText());
				textField.setText("");
			}
		});
		btnEnter.setBounds(345, 71, 89, 23);
		frame.getContentPane().add(btnEnter);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(216, 160, -184, 37);
		frame.getContentPane().add(textPane);
		
		
		
		
	}
}
