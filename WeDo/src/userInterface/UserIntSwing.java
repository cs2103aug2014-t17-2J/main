package userInterface;

import java.awt.Color;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dataStorage.ObservableList;
import logic.InvalidCommandException;
import logic.LogicManager;
import logic.utility.Task;
import ui.BalloonTipGuide;
import ui.CommandGuide;
import ui.UserLogic;

import javax.swing.SwingConstants;

import net.java.balloontip.BalloonTip;
import net.java.balloontip.BalloonTip.AttachLocation;
import net.java.balloontip.BalloonTip.Orientation;
import net.java.balloontip.styles.BalloonTipStyle;
import net.java.balloontip.styles.EdgedBalloonStyle;

import java.lang.Object;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

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
		frame = new JFrame("WeDo");
		frame.setBounds(100, 100, 600, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		UserLogic.setupFrameLocation();

		JLabel lblTodayDate = new JLabel("");
		lblTodayDate.setBounds(10, 0, 157, 20);
		frame.getContentPane().add(lblTodayDate);
		
		lblTodayDate.setText(UserLogic.setTodayDate());
		
		JLabel lblHelp = new JLabel("Label Help");
		lblHelp.setVerticalAlignment(SwingConstants.TOP);
		lblHelp.setBounds(10, 337, 496, 96);
		frame.getContentPane().add(lblHelp);
		
		//ArrayList<String> taskList = new ArrayList<String>();
		
		//Set the Help Label
		lblHelp.setText(CommandGuide.buildGeneralGuideString());
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg1) {
				UserLogic.processHotKeys(arg1);
			}
			@Override
			public void keyPressed(KeyEvent arg2) {
				try {
					String command = textField.getText();
					lblHelp.setText(CommandGuide.getGuideMessage(command + " "));
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
				}
				
				if(arg2.getKeyCode() == KeyEvent.VK_F1){
					JOptionPane.showMessageDialog(null, "The Help is not done!");
				}
			}
		});
		
		// Setup the Help label
		//CommandGuide.processGuide();
		
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
		
		JButton btnHelp = new JButton("F1 <Help>");
		BalloonTipStyle edgedLook = new EdgedBalloonStyle(Color.WHITE, Color.BLUE);
		BalloonTip helpBalloonTip = new BalloonTip(btnHelp, new JLabel("Press F1 for Help"), edgedLook, 
				Orientation.RIGHT_BELOW, AttachLocation.ALIGNED, 40, 20, false);
		btnHelp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				System.out.println("mouse entered!");
				helpBalloonTip.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent arg1) {
				System.out.println("mouse exited!");
				helpBalloonTip.setVisible(false);
			}
		});
		btnHelp.setBounds(10, 26, 100, 23);
		frame.getContentPane().add(btnHelp);

		JButton btnAdd = new JButton("F2 <Add>");
		BalloonTip AddBalloonTip = new BalloonTip(btnAdd, new JLabel("Press F2 to Add"), edgedLook, 
				Orientation.RIGHT_BELOW, AttachLocation.ALIGNED, 40, 20, false);
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				AddBalloonTip.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent arg1) {
				AddBalloonTip.setVisible(false);
			}
		});
		btnAdd.setBounds(120, 26, 100, 23);
		frame.getContentPane().add(btnAdd);

		JButton btnView = new JButton("F3 <View>");
		btnView.setBounds(230, 26, 100, 23);
		frame.getContentPane().add(btnView);

		JButton btnEdit = new JButton("F4 <Edit>");
		btnEdit.setBounds(340, 26, 100, 23);
		frame.getContentPane().add(btnEdit);

		interForm = new InteractiveForm();
		interForm.execute(frame);
		
		//Setup the Help label
		lblHelp.setText(CommandGuide.buildGeneralGuideString());
		
		//This operation puts the focus on the textField 
		//for the user to type immediately when the program runs 
		UserLogic.addFrameWindowFocusListener();
		
		//UserLogic.processTextField();
		
		JButton btnDel = new JButton("F5 <Delete>");
		btnDel.setBounds(450, 26, 100, 23);
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
        assert(taskList!=null);
		interForm.updateTable(taskList);		
	}
}
