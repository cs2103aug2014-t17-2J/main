package userInterface;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JDesktopPane;

import java.awt.TextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UserIntSwing extends JPanel {

	private JFrame frame;
	private JTextField textField;
	private JTable table;
	
	private static final String WHITESPACE_PATTERN = "\\s+";
	private static final int MIN_TOKENS_LENGTH = 1;
	private static final String GENERAL_GUIDE = buildGeneralGuideString();
	//private static final String ADD_GUIDE = buildAddGuideString();
	
	private static final String HTML_OPEN = "<html>";
	private static final String HTML_CLOSE = "</html>";
	private static final String HTML_BREAK = "<br>";
	//private static final String HTML_UNDERLINE_OPEN = "<u>";
	//private static final String HTML_UNDERLINE_CLOSE = "</u>";
	
	private static final String TAG_WRAP_STRING = "%s%s%s";
	private static final int ACTION_IDENTIFIER_INDEX = 0;
	//private static final String IDENTIFIER_PLACEHOLDER = "%1$s";
	//private static final String DATE_FORMAT = "dd/MM/yy";

	/**
	 * @wbp.nonvisual location=-28,119
	 */
	// private JTextArea textArea_1;

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
		frame.getContentPane().addKeyListener(new KeyAdapter() {
		
		});
		frame.setBounds(100, 100, 600, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblDisplay = new JLabel("My Label");
		lblDisplay.setBounds(10, 42, 157, 20);
		frame.getContentPane().add(lblDisplay);

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
		textField.setBounds(10, 11, 386, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnEnter = new JButton("ENTER");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// lblDisplay.setText(textField.getText());
				// textArea.setText(textField.getText());
				// textField.setText("");

				//for (int i = 0; i < taskList.size(); i++) {
				//	textArea_1.append((String) taskList.get(i) + "\n");
				//}
			}
		});
		btnEnter.setBounds(406, 10, 89, 23);
		frame.getContentPane().add(btnEnter);
		
		JLabel lblHelp = new JLabel("Label Help");
		lblHelp.setBounds(10, 302, 410, 42);
		frame.getContentPane().add(lblHelp);
		
		JButton btnF = new JButton("F1 <Help>");
		btnF.setBounds(14, 268, 100, 23);
		frame.getContentPane().add(btnF);
		
		JButton btnFAdd = new JButton("F2 <Add>");
		btnFAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnFAdd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if(keyCode == KeyEvent.VK_F2){
					textField.setText("-add");
				}

			}
		});
		
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				lblHelp.setText(getGuideMessage("ADD"));

				// textArea_1.append(taskList+"\n"); //displays the entire
				// arrayList in [a,b,c] format

				textField.setText("");
			}
		});
		
		btnFAdd.setBounds(128, 268, 100, 23);
		frame.getContentPane().add(btnFAdd);
		
		JButton btnF_1 = new JButton("F3 <View>");
		btnF_1.setBounds(242, 268, 100, 23);
		frame.getContentPane().add(btnF_1);
		
		JButton btnF_2 = new JButton("F4 <Edit>");
		btnF_2.setBounds(356, 268, 100, 23);
		frame.getContentPane().add(btnF_2);
		
		InteractiveForm interForm = new InteractiveForm();
		interForm.execute(frame);
		
		lblHelp.setText(buildGeneralGuideString());
		
		JButton btnF_3 = new JButton("F5 <Delete>");
		btnF_3.setBounds(470, 268, 100, 23);
		frame.getContentPane().add(btnF_3);

	}
	
	public String getGuideMessage(String commandString) {

		/* Check that there is at least 1 token */
		String[] tokens = commandString.split(WHITESPACE_PATTERN);
		boolean isValidLength = (tokens.length >= MIN_TOKENS_LENGTH);

		if (!isValidLength) {
			return GENERAL_GUIDE;
		}

		String identifier = tokens[ACTION_IDENTIFIER_INDEX];
		String message = buildGuideMessage(identifier);

		return message;
	}
	
	private String buildGuideMessage(String identifier) {

		identifier = identifier.toLowerCase();
		Action action = Keywords.resolveActionIdentifier(identifier);

		switch (action) {
			case ADD:
				//return String.format(ADD_GUIDE, identifier);
			default:
				return GENERAL_GUIDE;
		}
	}
	
	private static String buildGeneralGuideString() {

		StringBuilder str = new StringBuilder();

		str.append("Type any of the following to begin:");
		str.append(HTML_BREAK);
		str.append(Keywords.getAddTaskIdentifier() + " | ");
		str.append(Keywords.getUpdateTaskIdentifier() + " | ");
		str.append(Keywords.getDeleteTaskIdentifier() + " | ");
		str.append(Keywords.getListTaskIdentifier());

		return wrapWithHtmlTag(str.toString());
	}
	
	private static String wrapWithHtmlTag(String text) {
		return String.format(TAG_WRAP_STRING, HTML_OPEN, text, HTML_CLOSE);
	}
}
