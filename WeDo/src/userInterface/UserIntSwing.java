package userInterface;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import brain.Processor;
import ui.CommandGuide;
import ui.Keywords;
import ui.Action;

@SuppressWarnings("serial")
public class UserIntSwing extends JPanel implements Observer {

	private JFrame frame;
	private JTextField textField;
	private JTable table;
	private CommandGuide commandGuide;
	private Processor processor;
	// private static final String IDENTIFIER_PLACEHOLDER = "%1$s";
	// private static final String DATE_FORMAT = "dd/MM/yy";

	/**
	 * Launch the application.
	 */
	public void execute() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserIntSwing window = new UserIntSwing(processor);
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
	public UserIntSwing(Processor processor) {
		this.processor = processor;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if (keyCode == KeyEvent.VK_F2) {
					textField.setText("-add");
				}
			}
		});
		frame.getContentPane().addKeyListener(new KeyAdapter() {

		});
		frame.setBounds(100, 100, 600, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblDisplay = new JLabel("My Label");
		lblDisplay.setBounds(10, 42, 157, 20);
		frame.getContentPane().add(lblDisplay);

		//ArrayList<String> taskList = new ArrayList<String>();

		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String textInput = "";
				textInput += textField.getText();
				lblDisplay.setText(textField.getText());
				
				//processor.executeCommand(textInput);
				
				//taskList.add(textInput);

				textField.setText("");
			}
		});
		textField.setBounds(10, 270, 386, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnEnter = new JButton("ENTER");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// lblDisplay.setText(textField.getText());
				// textArea.setText(textField.getText());
				// textField.setText("");
				processor.executeCommand(textField.toString());
				// for (int i = 0; i < taskList.size(); i++) {
				// textArea_1.append((String) taskList.get(i) + "\n");
				// }
			}
		});
		btnEnter.setBounds(406, 269, 100, 23);
		frame.getContentPane().add(btnEnter);

		JLabel lblHelp = new JLabel("Label Help");
		lblHelp.setBounds(10, 293, 496, 56);
		frame.getContentPane().add(lblHelp);

		JButton btnHelp = new JButton("F1 <Help>");
		btnHelp.setBounds(10, 11, 100, 23);
		frame.getContentPane().add(btnHelp);

		JButton btnAdd = new JButton("F2 <Add>");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("-add");
			}
		});
		// btnAdd.addKeyListener(new KeyAdapter() {
		// @Override
		// public void keyPressed(KeyEvent e) {
		// int keyCode = e.getKeyCode();
		// if(keyCode == KeyEvent.VK_F2){
		// textField.setText("-add");
		// }

		// }
		// });

		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				lblHelp.setText(commandGuide.getGuideMessage("ADD"));

				// textArea_1.append(taskList+"\n"); //displays the entire
				// arrayList in [a,b,c] format

				textField.setText("");
			}
		});

		btnAdd.setBounds(120, 11, 100, 23);
		frame.getContentPane().add(btnAdd);

		JButton btnView = new JButton("F3 <View>");
		btnView.setBounds(230, 11, 100, 23);
		frame.getContentPane().add(btnView);

		JButton btnEdit = new JButton("F4 <Edit>");
		btnEdit.setBounds(340, 11, 100, 23);
		frame.getContentPane().add(btnEdit);

		InteractiveForm interForm = new InteractiveForm();
		interForm.execute(frame);

		lblHelp.setText(commandGuide.buildGeneralGuideString());

		JButton btnDel = new JButton("F5 <Delete>");
		btnDel.setBounds(450, 11, 100, 23);
		frame.getContentPane().add(btnDel);

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
}
