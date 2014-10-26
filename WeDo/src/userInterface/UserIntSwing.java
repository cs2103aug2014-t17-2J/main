package userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import logic.LogicManager;
import logic.exception.InvalidCommandException;
import logic.parser.ParseResult;
import logic.utility.Task;
import net.java.balloontip.BalloonTip;
import net.java.balloontip.BalloonTip.AttachLocation;
import net.java.balloontip.BalloonTip.Orientation;
import net.java.balloontip.styles.BalloonTipStyle;
import net.java.balloontip.styles.EdgedBalloonStyle;
import ui.UserInterfaceMain;
import ui.guide.CommandGuide;
import ui.guide.FeedbackGuide;
import dataStorage.ObservableList;

import java.awt.Toolkit;

@SuppressWarnings("serial")
public class UserIntSwing extends JPanel implements Observer {

	ArrayList<Task> taskList;

	public static JFrame frame;
	public static JTextField textField;
	public static JLabel lblHelp;
	public static JButton btnHelp;
	public static final JLabel lblFeedback = new JLabel("");
	public static final JLabel lblQuickHelp = new JLabel("Quick Help");

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
					// UserIntSwing window = new UserIntSwing(commandHandler,
					// observableList);
					// initialize(); //reduce the initialize count
					// observableList.addObserver(window);
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
	public UserIntSwing(LogicManager logicManager,
			ObservableList<Task> observableList) {
		this.logicManager = logicManager;
		this.observableList = observableList;
		taskList = observableList.getList();
		initialize(); // reduce the initialize count
		interForm.updateTable(taskList);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("WeDo");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(UserIntSwing.class.getResource("/ui/icon/WeDo.png")));
		frame.getContentPane().setEnabled(false);
		frame.setForeground(Color.WHITE);
		frame.getContentPane().setBackground(new Color(255, 204, 255));
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 12));
		frame.setBounds(100, 100, 755, 510); // windowSize
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/**
		 *@author Andy - Set the frame location to the right corner of
		 * the screen when the programs starts
		 */
		UserInterfaceMain.setupFrameLocation();

		BalloonTipStyle edgedLook = new EdgedBalloonStyle(Color.WHITE,
				Color.BLUE);
		
		/**
		 *@author Andy - Set the label to show today's date
		 */
		JLabel lblTodayDate = new JLabel("");
		lblTodayDate.setText(UserInterfaceMain.setTodayDate());

		JButton btnHelp_1 = new JButton("F1 <Help>");
		btnHelp_1.setForeground(new Color(0, 0, 0));
		btnHelp_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
			}
		});
		btnHelp_1.setBackground(new Color(255, 153, 255));
		BalloonTip helpBalloonTip = new BalloonTip(btnHelp_1, new JLabel(
				"Press F1 for Help"), edgedLook, Orientation.RIGHT_BELOW,
				AttachLocation.ALIGNED, 40, 20, false);
		btnHelp_1.addMouseListener(new MouseAdapter() {
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

		JButton btnAdd = new JButton("F2 <Add>");
		btnAdd.setForeground(new Color(0, 0, 0));
		btnAdd.setToolTipText("");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("add");
			}
		});
		btnAdd.setBackground(new Color(255, 153, 255));
		BalloonTip AddBalloonTip = new BalloonTip(btnAdd, new JLabel(
				"Press F2 to Add"), edgedLook, Orientation.RIGHT_BELOW,
				AttachLocation.ALIGNED, 40, 20, false);
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

		JButton btnView = new JButton("F3 <View>");
		btnView.setForeground(new Color(0, 0, 0));
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("view");
			}
		});
		btnView.setToolTipText("Click for Viewing Tasks!");
		btnView.setBackground(new Color(255, 153, 255));

		JButton btnEdit = new JButton("F4 <Edit>");
		btnEdit.setForeground(new Color(0, 0, 0));
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("edit");
			}
		});
		btnEdit.setBackground(new Color(255, 153, 255));

		JButton btnDel = new JButton("F5 <Delete>");
		btnDel.setForeground(new Color(0, 0, 0));
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("delete");
			}
		});
		btnDel.setBackground(new Color(255, 153, 255));

		JButton btnSearch = new JButton("F6 <Search>");
		btnSearch.setForeground(new Color(0, 0, 0));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("search");
			}
		});
		btnSearch.setBackground(new Color(255, 153, 255));

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);

		JPanel panelBottom = new JPanel();
		panelBottom.setBackground(new Color(255, 204, 255));

		JLabel lblHelp = new JLabel("Label Help");
		lblHelp.setVerticalAlignment(SwingConstants.TOP);
		
		/**
		 *@author Andy - To set the Command Guide label to show
		 *the general guide when the program starts
		 */
		lblHelp.setText(CommandGuide.buildGeneralGuideString());

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg1) {
				try {
					String text = textField.getText();
					/**
					 *@author Andy - Set the Command guide to the
					 *indiviual command guide that the user input
					 */
					lblHelp.setText(CommandGuide.getGuideMessage(text
							+ " "));
					frame.setVisible(true);

					/**
					 *@author Andy - This process the all the HotKeys 
					 *function when the user press the hotkeys on the
					 *keyboard
					 */
					UserInterfaceMain.processHotKeys(arg1);
					
					if(arg1.getKeyCode() == KeyEvent.VK_ENTER){
						String getText = textField.getText();
		
						/**
						 *@author Andy - This process all the feedback label
						 *when the user type an incorrect input
						 */
						lblFeedback.setText(UserInterfaceMain.processFeedbackLabel(getText));
						/**
						 *@author Andy - This clear the feedback label. It is
						 *set at 1000 milli-seconds.
						 */
						UserInterfaceMain.feedbackTimerReset();
					}
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		/**
		 *@author Andy - This sets the style format of the
		 *feedback guide
		 */
		FeedbackGuide.formatFeedbackLabel();
		
		/**
		 *@author Andy - This sets the style format of the 
		 *command guide label
		 */
		CommandGuide.fomatCommandGuideLabel();

		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// String textInput = "";
				// textInput += textField.getText();
				try {
				    ParseResult parseResult = logicManager.processCommand(textField.getText());
				    if(parseResult.isSuccessful())
				    {
				        logicManager.executeCommand(parseResult);
				    }
				    else
				    {
				        //print sth
				    }
				} catch (InvalidCommandException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
				textField.setText("");
				//Andy - reset command guide to general guide
				lblHelp.setText(CommandGuide.buildGeneralGuideString());
			}
		});
		textField.setColumns(10);

		JButton btnEnter = new JButton("ENTER");
		btnEnter.setBackground(new Color(204, 255, 255));
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// lblDisplay.setText(textField.getText());
				// textArea.setText(textField.getText());
				// textField.setText("");
				try {
				    ParseResult parseResult = logicManager.processCommand(textField.getText());
				    if(parseResult.isSuccessful())
                    {
                        logicManager.executeCommand(parseResult);
                    }
                    else
                    {
                        //print sth
                    } 
				} catch (InvalidCommandException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
				textField.setText("");
				//Andy - reset command guide to general guide
				lblHelp.setText(CommandGuide.buildGeneralGuideString());
			}
		});
		
		JLabel lblCommand = new JLabel("Command:");
		lblCommand.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel labelProcessCommand = new JLabel("");
		
		JLabel lblProcessCommand = new JLabel("");
		
		JLabel lblCommandProcess = new JLabel("add");
		lblCommandProcess.setFont(new Font("Tahoma", Font.ITALIC, 11));
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblDateProcess = new JLabel("26/10/2014 to 27/10/2014");
		lblDateProcess.setFont(new Font("Tahoma", Font.ITALIC, 11));
		
		JLabel lblPriority = new JLabel("Priority:");
		lblPriority.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblPriorityProcess = new JLabel("High");
		lblPriorityProcess.setFont(new Font("Tahoma", Font.ITALIC, 11));
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblDescriptionProcess = new JLabel("Meet Dr. Damith");
		lblDescriptionProcess.setFont(new Font("Tahoma", Font.ITALIC, 11));

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTodayDate)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnHelp_1, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnAdd, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnView, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnEdit, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnDel, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnSearch, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)))
							.addGap(18))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(panelBottom, GroupLayout.PREFERRED_SIZE, 716, Short.MAX_VALUE)
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE))
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblCommand)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(labelProcessCommand, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblProcessCommand, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblCommandProcess, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblDate)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblDateProcess, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblPriority)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblPriorityProcess, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblDescription)
									.addGap(6)
									.addComponent(lblDescriptionProcess, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)))
							.addContainerGap(99, Short.MAX_VALUE))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lblTodayDate)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAdd)
						.addComponent(btnView)
						.addComponent(btnEdit)
						.addComponent(btnDel)
						.addComponent(btnSearch)
						.addComponent(btnHelp_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
					.addGap(9)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCommand)
						.addComponent(labelProcessCommand)
						.addComponent(lblProcessCommand)
						.addComponent(lblCommandProcess)
						.addComponent(lblDate)
						.addComponent(lblDateProcess)
						.addComponent(lblPriority)
						.addComponent(lblPriorityProcess)
						.addComponent(lblDescription)
						.addComponent(lblDescriptionProcess))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panelBottom, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		GroupLayout gl_panelBottom = new GroupLayout(panelBottom);
		gl_panelBottom.setHorizontalGroup(
			gl_panelBottom.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelBottom.createSequentialGroup()
					.addGroup(gl_panelBottom.createParallelGroup(Alignment.LEADING)
						.addComponent(lblFeedback, GroupLayout.DEFAULT_SIZE, 629, Short.MAX_VALUE)
						.addGroup(gl_panelBottom.createSequentialGroup()
							.addComponent(textField, GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(btnEnter, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblQuickHelp)
						.addComponent(lblHelp, GroupLayout.DEFAULT_SIZE, 629, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panelBottom.setVerticalGroup(
			gl_panelBottom.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBottom.createSequentialGroup()
					.addComponent(lblFeedback, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelBottom.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEnter))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblQuickHelp)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblHelp, GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE))
		);
		gl_panelBottom.setAutoCreateGaps(true);
		panelBottom.setLayout(gl_panelBottom);

		frame.getContentPane().setLayout(groupLayout);

		interForm = new InteractiveForm();
		interForm.execute(frame); // to display the table
		panel.setLayout(new BorderLayout(0, 0));
		panel.add(interForm);

		/**
		 *@author Andy - This operation puts the focus on the textField
		 *for the user to type immediately when the program runs
		 */
		UserInterfaceMain.addFrameWindowFocusListener();
	}

	private void addFrameWindowFocusListener() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		taskList = observableList.getList();
		assert (taskList != null);
		interForm.updateTable(taskList);
	}
}
