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
import java.util.Timer;
import java.util.TimerTask;

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
import logic.exception.InvalidParseException;
import logic.parser.ParseResult;
import logic.utility.Task;
import net.java.balloontip.BalloonTip;
import net.java.balloontip.BalloonTip.AttachLocation;
import net.java.balloontip.BalloonTip.Orientation;
import net.java.balloontip.styles.BalloonTipStyle;
import net.java.balloontip.styles.EdgedBalloonStyle;
import ui.CommandGuide;
import ui.UserLogic;
import dataStorage.ObservableList;
import java.awt.Toolkit;

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
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(UserIntSwing.class.getResource("/ui/Image/WeDo.png")));
		frame.getContentPane().setEnabled(false);
		frame.setForeground(Color.WHITE);
		frame.getContentPane().setBackground(new Color(255, 204, 255));
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 12));
		frame.setBounds(100, 100, 675, 510); // windowSize
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		UserLogic.setupFrameLocation();

		BalloonTipStyle edgedLook = new EdgedBalloonStyle(Color.WHITE,
				Color.BLUE);

		JLabel lblTodayDate = new JLabel("");

		lblTodayDate.setText(UserLogic.setTodayDate());

		JButton btnHelp_1 = new JButton("F1 <Help>");
		btnHelp_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
			}
		});
		btnHelp_1.setBackground(new Color(204, 255, 255));
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
		btnAdd.setToolTipText("");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("add");
			}
		});
		btnAdd.setBackground(new Color(204, 255, 255));
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
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("view");
			}
		});
		btnView.setToolTipText("Click for Viewing Tasks!");
		btnView.setBackground(new Color(204, 255, 255));

		JButton btnEdit = new JButton("F4 <Edit>");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("edit");
			}
		});
		btnEdit.setBackground(new Color(204, 255, 255));

		// UserLogic.processTextField();

		JButton btnDel = new JButton("F5 <Delete>");
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("delete");
			}
		});
		btnDel.setBackground(new Color(204, 255, 255));

		JButton btnSearch = new JButton("F6 <Search>");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("search");
			}
		});
		btnSearch.setBackground(new Color(204, 255, 255));

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);

		JPanel panelBottom = new JPanel();
		panelBottom.setBackground(new Color(255, 204, 255));

		JLabel lblHelp_1 = new JLabel("Label Help");
		lblHelp_1.setVerticalAlignment(SwingConstants.TOP);
		
		JLabel lblWarning_1 = new JLabel("warning");
		UserLogic.timer();
		// Set the Help Label
		lblHelp_1.setText(CommandGuide.buildGeneralGuideString());

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg1) {
				try {
					String text = textField.getText();
					lblHelp_1.setText(CommandGuide.getGuideMessage(text
							+ " "));
					frame.setVisible(true);

					// process the hotkey functions
					UserLogic.processHotKeys(arg1);
					
					if(arg1.getKeyCode() == KeyEvent.VK_ENTER){
						String getText = textField.getText();
		
						//process the warning label
						lblWarning_1.setText(UserLogic.processWarningLabel(getText));
						
						UserLogic.timer();
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		// Setup the Help label
		// CommandGuide.processGuide();

		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// String textInput = "";
				// textInput += textField.getText();
				try {
				    ParseResult parseResult = logicManager.processCommand(textField.getText());
				    logicManager.executeCommand(parseResult);
				} catch (InvalidParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InvalidCommandException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
				textField.setText("");

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
	                logicManager.executeCommand(parseResult);

				} catch (InvalidParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidCommandException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
				textField.setText("");
			}
		});

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.TRAILING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								lblTodayDate)
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addComponent(
																												btnHelp_1,
																												GroupLayout.DEFAULT_SIZE,
																												97,
																												Short.MAX_VALUE)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												btnAdd,
																												GroupLayout.DEFAULT_SIZE,
																												95,
																												Short.MAX_VALUE)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												btnView,
																												GroupLayout.DEFAULT_SIZE,
																												97,
																												Short.MAX_VALUE)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												btnEdit,
																												GroupLayout.DEFAULT_SIZE,
																												96,
																												Short.MAX_VALUE)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												btnDel,
																												GroupLayout.DEFAULT_SIZE,
																												107,
																												Short.MAX_VALUE)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												btnSearch,
																												GroupLayout.DEFAULT_SIZE,
																												109,
																												Short.MAX_VALUE)))
																		.addGap(18))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.TRAILING)
																						.addComponent(
																								panelBottom,
																								GroupLayout.PREFERRED_SIZE,
																								639,
																								Short.MAX_VALUE)
																						.addComponent(
																								panel,
																								GroupLayout.DEFAULT_SIZE,
																								639,
																								Short.MAX_VALUE))
																		.addContainerGap()))));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.TRAILING)
				.addGroup(
						groupLayout
								.createSequentialGroup()
								.addComponent(lblTodayDate)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(
										groupLayout
												.createParallelGroup(
														Alignment.BASELINE)
												.addComponent(btnAdd)
												.addComponent(btnView)
												.addComponent(btnEdit)
												.addComponent(btnDel)
												.addComponent(btnSearch)
												.addComponent(btnHelp_1))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(panel, GroupLayout.DEFAULT_SIZE,
										224, Short.MAX_VALUE)
								.addGap(34)
								.addComponent(panelBottom,
										GroupLayout.PREFERRED_SIZE, 153,
										GroupLayout.PREFERRED_SIZE)
								.addContainerGap()));

		JLabel lblQuickHelp = new JLabel("Quick Help");
		lblQuickHelp.setFont(new Font("Times New Roman", Font.BOLD
				| Font.ITALIC, 14));
		
		GroupLayout gl_panelBottom = new GroupLayout(panelBottom);
		gl_panelBottom.setHorizontalGroup(
			gl_panelBottom.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelBottom.createSequentialGroup()
					.addGroup(gl_panelBottom.createParallelGroup(Alignment.LEADING)
						.addComponent(lblWarning_1, GroupLayout.DEFAULT_SIZE, 629, Short.MAX_VALUE)
						.addGroup(gl_panelBottom.createSequentialGroup()
							.addComponent(textField, GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(btnEnter, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblQuickHelp)
						.addComponent(lblHelp_1, GroupLayout.DEFAULT_SIZE, 629, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panelBottom.setVerticalGroup(
			gl_panelBottom.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBottom.createSequentialGroup()
					.addComponent(lblWarning_1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelBottom.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEnter))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblQuickHelp)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblHelp_1, GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE))
		);
		gl_panelBottom.setAutoCreateGaps(true);
		panelBottom.setLayout(gl_panelBottom);

		frame.getContentPane().setLayout(groupLayout);

		interForm = new InteractiveForm();
		interForm.execute(frame); // to display the table
		panel.setLayout(new BorderLayout(0, 0));
		panel.add(interForm);

		// This operation puts the focus on the textField
		// for the user to type immediately when the program runs
		UserLogic.addFrameWindowFocusListener();
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
