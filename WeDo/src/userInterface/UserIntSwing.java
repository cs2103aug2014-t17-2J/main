package userInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logic.LogicManager;
import logic.utility.Task;
import ui.UserInterfaceMain;
import ui.guide.FeedbackGuide;
import ui.logic.command.FormatHandler;
import dataStorage.ObservableList;

@SuppressWarnings("serial")
public class UserIntSwing extends JPanel implements Observer {

	ArrayList<Task> taskList;

	public static final JFrame frame = new JFrame("WeDo");
	public static final InteractiveForm interactiveForm = new InteractiveForm();
	public static final JLabel lblBackground = new JLabel();
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
	public static final JLabel lblViewTask = new JLabel(
			FeedbackGuide.formatViewTodayTask());
	public static LogicManager logicManager;
	private ObservableList<Task> observableList;

	//@author A0112675H
	/**
	 * Launch the application.
	 */
	public void execute() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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

	//@author A0112675H
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setBounds(100, 100, 767, 550); // windowSize

		JPanel panel = new JPanel();
		panel.setBounds(10, 121, 750, 227);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		interactiveForm.table.setShowGrid(false);
		interactiveForm.table.setIntercellSpacing(new Dimension(0, 0));
		interactiveForm.table.setFillsViewportHeight(true);
		interactiveForm.table.setBackground(Color.WHITE);
		interactiveForm.scroller.setBounds(0, 0, 750, 227);
		interactiveForm.setBounds(0, 0, 750, 227);
		panel.add(interactiveForm);
		interactiveForm.setLayout(null);

		/* A0112636M - Initialize all components */
		FormatHandler.addtoContentPane();

		/* A0112636M - Initialize all components */
		UserInterfaceMain.initProcess();
	}

	//@author A0112675H
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
	 * @param task
	 * @return
	 */
	private boolean isTaskInstance(Object task) {
		return task instanceof Task;
	}
}
