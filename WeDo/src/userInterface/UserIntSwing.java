package userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
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
import logic.utility.Task;
import ui.UserInterfaceMain;
import dataStorage.ObservableList;

import java.awt.Toolkit;

@SuppressWarnings("serial")
public class UserIntSwing extends JPanel implements Observer {

	ArrayList<Task> taskList;

	public static final JFrame frame = new JFrame("WeDo");;
	public static final JTextField textField = new JTextField();
	public static final JButton btnHelp = new JButton("F1 <Help>");
	public static final JButton btnAdd = new JButton("F2 <Add>");
	public static final JButton btnView = new JButton("F3 <View>");
	public static final JButton btnEdit = new JButton("F4 <Edit>");
	public static final JButton btnDel = new JButton("F5 <Delete>");
	public static final JButton btnSearch = new JButton("F6 <Search>");
	public static final JButton btnEnter = new JButton("ENTER");
	
	public static final JLabel lblCommand = new JLabel("Command:");
	public static final JLabel lblCommandProcess = new JLabel();
	public static final JLabel lblDate = new JLabel("Date:");
	public static final JLabel lblDateProcess = new JLabel();
	public static final JLabel lblPriority = new JLabel("Priority:");
	public static final JLabel lblPriorityProcess = new JLabel();
	public static final JLabel lblDescription = new JLabel("Description:");
	public static final JLabel lblDescriptionProcess = new JLabel();
	
	public static final JLabel lblQuickHelp = new JLabel("Quick Help");
	public static final JLabel lblHelp = new JLabel("Label Help");
	public static final JLabel lblViewTask = new JLabel("You are viewing today's tasks.");
	public static final JLabel lblFeedback = new JLabel("Feedback");
	public static final JLabel lblTodayDate = new JLabel();
	
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
		frame.pack();
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);

		JPanel panelBottom = new JPanel();
		panelBottom.setBackground(new Color(255, 204, 255));

		lblHelp.setVerticalAlignment(SwingConstants.TOP);

		textField.setColumns(10);

		btnEnter.setBackground(new Color(204, 255, 255));

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(panelBottom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, 731, Short.MAX_VALUE))
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnHelp, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnAdd, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnView, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnEdit, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
								.addComponent(lblViewTask, GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnDel, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnSearch, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))
								.addComponent(lblTodayDate, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE))
							.addGap(18))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblCommand)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblCommandProcess, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblDate)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblDateProcess, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblPriority)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblPriorityProcess, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblDescription)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblDescriptionProcess, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(84, Short.MAX_VALUE))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTodayDate, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblViewTask, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAdd)
						.addComponent(btnView)
						.addComponent(btnEdit)
						.addComponent(btnDel)
						.addComponent(btnSearch)
						.addComponent(btnHelp))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
					.addGap(9)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCommand)
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

//		JButton btnColour = new JButton("colour");
//		btnColour.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				panelBottom.setBackground(new Color(230, 230, 250));
//				frame.getContentPane().setBackground(new Color(230, 230, 250));
//
//			}
//		});
//		btnColour.setBackground(new Color(240, 230, 140));

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
							.addGap(26)))
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
							.addComponent(lblHelp, GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE))))
		);
		// gl_panelBottom.setAutoCreateGaps(true);
		panelBottom.setLayout(gl_panelBottom);

		frame.getContentPane().setLayout(groupLayout);

		interForm = new InteractiveForm();
		interForm.execute(frame); // to display the table
		panel.setLayout(new BorderLayout(0, 0));
		panel.add(interForm);
		
		/* Andy - Initialize all components*/
		UserInterfaceMain.initProcess();
	}

	@Override
	public void update(Observable arg0, Object arg1) 
	{
        taskList = observableList.getList();        
        interForm.updateTable(taskList);
        
	    if(isTaskInstance(arg1))
	    {
	        Task task = (Task) arg1;
	        int changedTaskRow = observableList.indexOf(task);
	        if(isIndexValid(changedTaskRow))
	        {
	            interForm.selectRow(changedTaskRow);
	        }
	    }
	    
		assert (taskList != null);
	}
	

	
    /**
     * @param changedTaskRow
     * @return
     */
    private boolean isIndexValid(int changedTaskRow) 
    {
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


