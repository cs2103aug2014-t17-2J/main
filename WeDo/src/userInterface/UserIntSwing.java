package userInterface;

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

import logic.InvalidCommandException;
import logic.LogicManager;
import logic.utility.Task;
import net.java.balloontip.BalloonTip;
import net.java.balloontip.BalloonTip.AttachLocation;
import net.java.balloontip.BalloonTip.Orientation;
import net.java.balloontip.styles.BalloonTipStyle;
import net.java.balloontip.styles.EdgedBalloonStyle;
import ui.CommandGuide;
import ui.UserLogic;
import dataStorage.ObservableList;

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
					//initialize(); //reduce the initialize count
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
		initialize(); //reduce the initialize count
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("WeDo");
		frame.getContentPane().setEnabled(false);
		frame.setForeground(Color.WHITE);
		frame.getContentPane().setBackground(new Color(255, 204, 255));
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 12));
		frame.setBounds(100, 100, 650, 480); // windowSize
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		UserLogic.setupFrameLocation();

		BalloonTipStyle edgedLook = new EdgedBalloonStyle(Color.WHITE,
				Color.BLUE);

		JLabel lblWarning_1 = new JLabel("warning");

		JLabel lblTodayDate = new JLabel("");

		lblTodayDate.setText(UserLogic.setTodayDate());

		JLabel lblHelp_1 = new JLabel("Label Help");
		lblHelp_1.setVerticalAlignment(SwingConstants.TOP);

		// Set the Help Label
		lblHelp_1.setText(CommandGuide.buildGeneralGuideString());

		JButton btnHelp_1 = new JButton("F1 <Help>");
		btnHelp_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
			}
		});
		btnHelp_1.setBackground(new Color(153, 204, 255));
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
		btnAdd.setBackground(new Color(153, 204, 255));
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
		btnView.setBackground(new Color(153, 204, 255));

		JButton btnEdit = new JButton("F4 <Edit>");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("edit");
			}
		});
		btnEdit.setBackground(new Color(153, 204, 255));

		// UserLogic.processTextField();

		JButton btnDel = new JButton("F5 <Delete>");
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("delete");
			}
		});
		btnDel.setBackground(new Color(153, 204, 255));

		JButton btnSearch = new JButton("F6 <Search>");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("search");
			}
		});
		btnSearch.setBackground(new Color(153, 204, 255));

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg1) {
				try {
					String command = textField.getText();
					lblHelp_1.setText(CommandGuide.getGuideMessage(command
							+ " "));
					frame.setVisible(true);

					// process the hotkey functions
					UserLogic.processHotKeys(arg1);
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
					logicManager.processCommand(textField.getText());
				} catch (InvalidCommandException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				textField.setText("");

			}
		});
		textField.setColumns(10);

		JLabel lblQuickHelp = new JLabel("Quick Help");
		lblQuickHelp.setFont(new Font("Times New Roman", Font.BOLD
				| Font.ITALIC, 14));

		JButton btnEnter = new JButton("ENTER");
		btnEnter.setBackground(new Color(153, 204, 255));
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
			}
		});

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblTodayDate))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnHelp_1, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAdd, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnView, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEdit, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDel, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSearch, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)))
					.addGap(18))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblHelp_1, GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
					.addGap(18))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblQuickHelp, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
					.addGap(507))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(textField, GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
					.addGap(10)
					.addComponent(btnEnter, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addGap(46))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblWarning_1, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(501, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
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
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(274)
							.addComponent(btnEnter))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblWarning_1)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblQuickHelp)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblHelp_1)
					.addGap(51))
		);
		frame.getContentPane().setLayout(groupLayout);

		interForm = new InteractiveForm();
		interForm.execute(frame); // to display the table

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
