package userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import logic.LogicManager;
import logic.utility.Task;
import ui.UserInterfaceMain;
import ui.guide.FeedbackGuide;
import dataStorage.ObservableList;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class UserIntSwing extends JPanel implements Observer {

	ArrayList<Task> taskList;

	public static final JFrame frame = new JFrame("WeDo");
	public static final InteractiveForm interactiveForm = new InteractiveForm();
	public static final JLabel lblBackground = new JLabel("CommandGuide");
	public static final JLabel lblCommandGuide = new JLabel();
	public static final JTextField textField = new JTextField();
	public static final JLabel lblFeedback = new JLabel("Feedback");
	public static final JLabel lblDescriptionProcess = new JLabel();
	public static final JLabel lblPriorityProcess = new JLabel();
	public static final JLabel lblDateProcess = new JLabel();
	public static final JLabel lblCommandProcess = new JLabel();
	public static final JLabel lblTodayDate = new JLabel();
	public static final JButton btnEnter = new JButton();
	public static final JButton btnSearch = new JButton();
	public static final JButton btnDelete = new JButton();
	public static final JButton btnEdit = new JButton();
	public static final JButton btnView = new JButton();
	public static final JButton btnAdd = new JButton();
	public static final JButton btnHelp = new JButton();
	public static final JButton btnClose = new JButton();
	public static final JButton btnMinimize = new JButton();
	public static final JButton btnSetting = new JButton();
	public static final JLabel lblViewTask = new JLabel(FeedbackGuide.formatViewTodayTask());
	public static Point mouseDownCompCoords = null;
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
		UserIntSwing.logicManager = logicManager;
		this.observableList = observableList;
		taskList = observableList.getList();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		//frame.setIconImage(Toolkit.getDefaultToolkit().getImage(
		//		UserIntSwing.class.getResource("/ui/icon/WeDo.png")));
		frame.getContentPane().setEnabled(false);
		frame.setForeground(Color.WHITE);
		frame.getContentPane().setBackground(new Color(255, 204, 255));
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 12));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setUndecorated(true);
		
		frame.addMouseListener(new MouseListener() {
            public void mouseReleased(MouseEvent e) {
                mouseDownCompCoords = null;
            }
            public void mousePressed(MouseEvent e) {
                mouseDownCompCoords = e.getPoint();
            }
            public void mouseExited(MouseEvent e) {
            }
            public void mouseEntered(MouseEvent e) {
            }
            public void mouseClicked(MouseEvent e) {
            }
        });

        frame.addMouseMotionListener(new MouseMotionListener(){
            public void mouseMoved(MouseEvent e) {
            }
            public void mouseDragged(MouseEvent e) {
                Point currCoords = e.getLocationOnScreen();
                frame.setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
            }
        });
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 121, 747, 227);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		interactiveForm.table.setShowGrid(false);
		interactiveForm.table.setIntercellSpacing(new Dimension(0, 0));
		interactiveForm.table.setFillsViewportHeight(true);
		interactiveForm.table.setBackground(Color.WHITE);
		interactiveForm.scroller.setBounds(0, 0, 747, 276);
		interactiveForm.setBounds(0, 0, 747, 244);
		panel.add(interactiveForm);
		interactiveForm.setLayout(null);
		
		btnHelp.setBounds(15, 80, 111, 27);
		frame.getContentPane().add(btnHelp);
		
		btnAdd.setBounds(133, 78, 118, 30);
		frame.getContentPane().add(btnAdd);
		
		btnView.setBounds(254, 78, 118, 32);
		frame.getContentPane().add(btnView);
		
		btnEdit.setBounds(375, 78, 118, 30);
		frame.getContentPane().add(btnEdit);
		
		btnDelete.setBounds(503, 75, 127, 32);
		frame.getContentPane().add(btnDelete);
		
		btnSearch.setBounds(635, 75, 122, 32);
		frame.getContentPane().add(btnSearch);
		
		btnEnter.setBounds(656, 408, 105, 30);
		frame.getContentPane().add(btnEnter);

		btnClose.setBounds(728, 12, 33, 30);
		
		frame.getContentPane().add(btnClose);
		btnMinimize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setExtendedState(JFrame.ICONIFIED);
			}
		});
		btnMinimize.setBounds(693, 12, 33, 30);
		
		frame.getContentPane().add(btnMinimize);
		btnSetting.setBounds(656, 11, 33, 30);
		
		frame.getContentPane().add(btnSetting);
		
		lblViewTask.setBounds(80, 44, 405, 25);
		frame.getContentPane().add(lblViewTask);
		
		lblTodayDate.setBounds(495, 44, 263, 26);
		frame.getContentPane().add(lblTodayDate);
		
		lblCommandProcess.setHorizontalAlignment(SwingConstants.CENTER);
		lblCommandProcess.setBounds(80, 359, 46, 14);
		frame.getContentPane().add(lblCommandProcess);
		
		lblDateProcess.setBounds(176, 359, 132, 14);
		frame.getContentPane().add(lblDateProcess);
		
		lblPriorityProcess.setHorizontalAlignment(SwingConstants.CENTER);
		lblPriorityProcess.setBounds(366, 359, 53, 14);
		frame.getContentPane().add(lblPriorityProcess);
		
		lblDescriptionProcess.setBounds(501, 359, 256, 14);
		frame.getContentPane().add(lblDescriptionProcess);
		
		lblFeedback.setBounds(10, 379, 414, 25);
		frame.getContentPane().add(lblFeedback);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		textField.setBounds(10, 408, 639, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		lblCommandGuide.setBounds(10, 468, 639, 71);
		frame.getContentPane().add(lblCommandGuide);
		
		lblBackground.setIcon(new ImageIcon(UserIntSwing.class.getResource("/ui/icon/Wedo_v2.png")));
		lblBackground.setBounds(0, 0, 767, 550);
		frame.getContentPane().add(lblBackground);
		
		frame.setBounds(100, 100, 767, 550); // windowSize
		
		/* Andy - Initialize all components */
		UserInterfaceMain.initProcess();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		taskList = observableList.getList();
		interactiveForm.updateTable(taskList);

		if (isTaskInstance(arg1)) {
			Task task = (Task) arg1;
			int changedTaskRow = observableList.indexOf(task);
			if (isIndexValid(changedTaskRow)) {
				interactiveForm.selectRow(changedTaskRow);
				lblViewTask.setText(UserInterfaceMain.viewDateTask(task));
			}
		}

		assert (taskList != null);
	}

	/**
	 * @param changedTaskRow
	 * @return
	 */
	private boolean isIndexValid(int changedTaskRow) {
		final int VALID_INDEX = 0;
		return changedTaskRow >= VALID_INDEX;
	}

	/**
	 * @param arg1
	 * @return
	 */
	private boolean isTaskInstance(Object arg1) {
		return arg1 instanceof Task;
	}
}
