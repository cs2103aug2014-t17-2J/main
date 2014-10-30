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
import logic.command.commandList.ViewCommand;
import logic.exception.InvalidCommandException;
import logic.parser.DynamicParseResult;
import logic.parser.ParseResult;
import logic.utility.StringHandler;
import logic.utility.Task;
import net.java.balloontip.BalloonTip;
import net.java.balloontip.BalloonTip.AttachLocation;
import net.java.balloontip.BalloonTip.Orientation;
import net.java.balloontip.styles.BalloonTipStyle;
import net.java.balloontip.styles.EdgedBalloonStyle;
import ui.MinimiseToTray;
import ui.TextfieldHistory;
import ui.UserInterfaceMain;
import ui.guide.CommandGuide;
import ui.guide.FeedbackGuide;
import dataStorage.ObservableList;

import java.awt.Toolkit;
import java.awt.event.WindowStateListener;
import java.awt.event.WindowEvent;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class UserIntSwing extends JPanel implements Observer {

	ArrayList<Task> taskList;

	public static JFrame frame = new JFrame("WeDo");;
	public static JTextField textField = new JTextField();
	public static JLabel lblHelp = new JLabel("Label Help");
	public static JButton btnHelp = new JButton();
	public static final JLabel lblTodayDate = new JLabel("");
	public static final JLabel lblFeedback = new JLabel("");
	public static final JLabel lblQuickHelp = new JLabel("Quick Help");
	public static final JLabel lblCommand = new JLabel("Command:");
	public static final JLabel lblCommandProcess = new JLabel("");
	public static final JLabel lblDate = new JLabel("Date:");
	public static final JLabel lblDateProcess = new JLabel("");
	public static final JLabel lblPriority = new JLabel("Priority:");
	public static final JLabel lblPriorityProcess = new JLabel("");
	public static final JLabel lblDescription = new JLabel("Description:");
	public static final JLabel lblDescriptionProcess = new JLabel("");

	public static InteractiveForm interForm;
	public static LogicManager logicManager;
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

		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(
				UserIntSwing.class.getResource("/ui/icon/WeDo.png")));
		frame.getContentPane().setEnabled(false);
		frame.setForeground(Color.WHITE);
		frame.getContentPane().setBackground(new Color(255, 204, 255));
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 12));
		frame.setBounds(100, 100, 767, 511); // windowSize
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		BalloonTipStyle edgedLook = new EdgedBalloonStyle(Color.WHITE,
				Color.BLUE);

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
				helpBalloonTip.setVisible(true);
			}

			@Override
			public void mouseExited(MouseEvent arg1) {
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

		lblHelp.setVerticalAlignment(SwingConstants.TOP);

		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// String textInput = "";
				// textInput += textField.getText();
				try {
					ParseResult parseResult = logicManager
							.processCommand(textField.getText());
					if (parseResult.isSuccessful()) {
						logicManager.executeCommand(parseResult);
					} else {
						// print sth
					}
				} catch (InvalidCommandException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				textField.setText("");
				// reset command guide to general guide
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
					ParseResult parseResult = logicManager
							.processCommand(textField.getText());
					if (parseResult.isSuccessful()) {
						logicManager.executeCommand(parseResult);
					} else {
						// print sth
					}
				} catch (InvalidCommandException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				textField.setText("");
				// Andy - reset command guide to general guide
				lblHelp.setText(CommandGuide.buildGeneralGuideString());
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
																Alignment.LEADING)
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
																												110,
																												Short.MAX_VALUE)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												btnAdd,
																												GroupLayout.DEFAULT_SIZE,
																												113,
																												Short.MAX_VALUE)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												btnView,
																												GroupLayout.DEFAULT_SIZE,
																												112,
																												Short.MAX_VALUE)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												btnEdit,
																												GroupLayout.DEFAULT_SIZE,
																												113,
																												Short.MAX_VALUE)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												btnDel,
																												GroupLayout.DEFAULT_SIZE,
																												120,
																												Short.MAX_VALUE)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												btnSearch,
																												GroupLayout.DEFAULT_SIZE,
																												122,
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
																								728,
																								Short.MAX_VALUE)
																						.addComponent(
																								panel,
																								GroupLayout.DEFAULT_SIZE,
																								728,
																								Short.MAX_VALUE))
																		.addContainerGap())
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				lblCommand)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				lblCommandProcess,
																				GroupLayout.PREFERRED_SIZE,
																				43,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				lblDate)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				lblDateProcess,
																				GroupLayout.PREFERRED_SIZE,
																				136,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				lblPriority)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				lblPriorityProcess,
																				GroupLayout.PREFERRED_SIZE,
																				45,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				lblDescription)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				lblDescriptionProcess,
																				GroupLayout.PREFERRED_SIZE,
																				218,
																				GroupLayout.PREFERRED_SIZE)
																		.addContainerGap()))));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addComponent(lblTodayDate)
										.addPreferredGap(
												ComponentPlacement.RELATED)
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
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(panel,
												GroupLayout.DEFAULT_SIZE, 224,
												Short.MAX_VALUE)
										.addGap(9)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblCommand)
														.addComponent(
																lblCommandProcess)
														.addComponent(lblDate)
														.addComponent(
																lblDateProcess)
														.addComponent(
																lblPriority)
														.addComponent(
																lblPriorityProcess)
														.addComponent(
																lblDescription)
														.addComponent(
																lblDescriptionProcess))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addComponent(panelBottom,
												GroupLayout.PREFERRED_SIZE,
												153, GroupLayout.PREFERRED_SIZE)
										.addContainerGap()));

		JButton btnColour = new JButton("colour");
		btnColour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelBottom.setBackground(new Color(230, 230, 250));
				frame.getContentPane().setBackground(new Color(230, 230, 250));

			}
		});
		btnColour.setBackground(new Color(240, 230, 140));

		GroupLayout gl_panelBottom = new GroupLayout(panelBottom);
		gl_panelBottom.setHorizontalGroup(
			gl_panelBottom.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelBottom.createSequentialGroup()
					.addGroup(gl_panelBottom.createParallelGroup(Alignment.LEADING)
						.addComponent(lblFeedback, GroupLayout.DEFAULT_SIZE, 731, Short.MAX_VALUE)
						.addGroup(gl_panelBottom.createSequentialGroup()
							.addComponent(textField, GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(btnEnter, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelBottom.createSequentialGroup()
							.addComponent(lblQuickHelp)
							.addPreferredGap(ComponentPlacement.RELATED, 681, Short.MAX_VALUE))
						.addGroup(gl_panelBottom.createSequentialGroup()
							.addComponent(lblHelp, GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE)
							.addGap(26)
							.addComponent(btnColour)))
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
					.addGroup(gl_panelBottom.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panelBottom.createSequentialGroup()
							.addComponent(lblQuickHelp)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblHelp, GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE))
						.addComponent(btnColour)))
		);
		// gl_panelBottom.setAutoCreateGaps(true);
		panelBottom.setLayout(gl_panelBottom);

		frame.getContentPane().setLayout(groupLayout);

		interForm = new InteractiveForm();
		interForm.execute(frame); // to display the table
		panel.setLayout(new BorderLayout(0, 0));
		panel.add(interForm);

		UserInterfaceMain.initProcess();
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
