package userInterface;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dataStorage.ObservableList;
import logic.InvalidCommandException;
import logic.LogicManager;
import logic.utility.Task;
import ui.CommandGuide;
import ui.UserLogic;

@SuppressWarnings("serial")
public class UserIntSwing extends JPanel implements Observer {

	ArrayList<Task> taskList;	
	
	public static JFrame frame;
	public static JTextField textField;
	public static JLabel lblWarning;
	public static JLabel lblHelp;
	public static JButton btnHelp;
	
	private InteractiveForm interForm;
	private LogicManager logicManager;
    private ObservableList<Task> observableList;

	/**
	 * Launch the application.
	 */
	public void execute() {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
				//	UserIntSwing window = new UserIntSwing(commandHandler, observableList);
				    initialize();
					//observableList.addObserver(window);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
    
   

	public UserIntSwing()
	{
	    
	}
	
	/**
	 * Create the application.
	 */
	public UserIntSwing(LogicManager logicManager, ObservableList<Task> observableList) {
		this.logicManager = logicManager;
		this.observableList = observableList;
		taskList = observableList.getList();
        initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 600, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblDisplay = new JLabel("My Label");
		lblDisplay.setBounds(10, 42, 157, 20);
		frame.getContentPane().add(lblDisplay);

		//ArrayList<String> taskList = new ArrayList<String>();

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				UserLogic.processHotKeys(e);
			}
		});
		
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//String textInput = "";
			//	textInput += textField.getText();
				//lblDisplay.setText(textField.getText());
			    try {
                    logicManager.processCommand(textField.getText());
                } catch (InvalidCommandException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
				textField.setText("");
				//processor.executeCommand(textInput);
				
				//taskList.add(textInput);

			//	textField.setText("");
			}
		});
		textField.setBounds(10, 306, 386, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnEnter = new JButton("ENTER");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// lblDisplay.setText(textField.getText());
				// textArea.setText(textField.getText());
				// textField.setText("");
			    try {
                    logicManager.processCommand(textField.getText());
                } catch (InvalidCommandException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
				textField.setText("");
				// for (int i = 0; i < taskList.size(); i++) {
				// textArea_1.append((String) taskList.get(i) + "\n");
				// }
			}
		});
		btnEnter.setBounds(406, 305, 100, 23);
		frame.getContentPane().add(btnEnter);

		JLabel lblHelp = new JLabel("Label Help");
		lblHelp.setBounds(10, 330, 496, 56);
		frame.getContentPane().add(lblHelp);
		//UserInterface.processHotKeys();

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

				//lblHelp.setText(commandGuide.getGuideMessage("ADD"));

				// textArea_1.append(taskList+"\n"); //displays the entire
				// arrayList in [a,b,c] format

				//textField.setText("");
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

		interForm = new InteractiveForm();
		interForm.execute(frame);
		
		//Setup the Help label
		lblHelp.setText(CommandGuide.buildGeneralGuideString());
		
		//This operation puts the focus on the textField 
		//for the user to type immediately when the program runs 
		UserLogic.addFrameWindowFocusListener();
		
		UserLogic.processTextField();
		
		JButton btnDel = new JButton("F5 <Delete>");
		btnDel.setBounds(450, 11, 100, 23);
		frame.getContentPane().add(btnDel);
		
		JLabel lblWarning = new JLabel("");
		lblWarning.setBounds(10, 256, 496, 47);
		frame.getContentPane().add(lblWarning);
	}

	private void addFrameWindowFocusListener() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Observable arg0, Object arg1) 
	{
        taskList = observableList.getList();
		interForm.updateTable(taskList);		
	}
}