import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblDisplay = new JLabel("My Label");
		lblDisplay.setBounds(37, 133, 157, 20);
		frame.getContentPane().add(lblDisplay);
	
		JTextArea textArea = new JTextArea(22, 80);
		frame.getContentPane().add(textArea);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblDisplay.setText(textField.getText());
				textArea.setText(textField.getText());
				textField.setText("");
			}
		});
		textField.setBounds(22, 102, 140, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnEnter = new JButton("ENTER");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblDisplay.setText(textField.getText());
			}
		});
		btnEnter.setBounds(174, 101, 89, 23);
		frame.getContentPane().add(btnEnter);
		
		
		
	}
}
