package ui.logic.command;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowStateListener;

import javax.swing.JLabel;

import logic.exception.InvalidCommandException;
import logic.parser.DynamicParseResult;
import logic.utility.StringHandler;
import logic.utility.Task;
import net.java.balloontip.BalloonTip;
import net.java.balloontip.BalloonTip.AttachLocation;
import net.java.balloontip.BalloonTip.Orientation;
import net.java.balloontip.styles.BalloonTipStyle;
import net.java.balloontip.styles.EdgedBalloonStyle;
import ui.WeDoSystemTray;
import ui.TextfieldHistory;
import ui.UserInterfaceMain;
import ui.guide.CommandGuide;
import userInterface.UserIntSwing;

/**
 * @author Andy Hsu Wei Qiang
 *This class process all the Listener
 */
public class ListenerHandler {
	private static final BalloonTipStyle edgedLook = new EdgedBalloonStyle(Color.WHITE,
			Color.BLUE);
	private static String userInput = new String();
	/**
	 * Buttons Listener - Process the adding of text to the textfield
	 * when Hotkey is pressed. 
	 * Also process the balloonTip
	 */
	public static void addBtnHelpListener() {
		BalloonTip helpBalloonTip = new BalloonTip(UserIntSwing.btnHelp, new JLabel(
				"Press F1 for Help"), edgedLook, Orientation.RIGHT_BELOW,
				AttachLocation.ALIGNED, 40, 20, false);
		helpBalloonTip.setVisible(false);

		UserIntSwing.btnHelp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				helpBalloonTip.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent arg1) {
				helpBalloonTip.setVisible(false);
			}
		});
	}

	public static void addBtnAddListener() {
		BalloonTip addBalloonTip = new BalloonTip(UserIntSwing.btnAdd, new JLabel(
				"Press F2 to Add"), edgedLook, Orientation.RIGHT_BELOW,
				AttachLocation.ALIGNED, 40, 20, false);
		addBalloonTip.setVisible(false);
		UserIntSwing.btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HotkeyHandler.add();
				focusTextfield();
			}
		});

		UserIntSwing.btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				addBalloonTip.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent arg1) {
				addBalloonTip.setVisible(false);
			}
		});
	}

	public static void addBtnViewListener() {
		BalloonTip viewBalloonTip = new BalloonTip(UserIntSwing.btnView, new JLabel(
				"Press F3 to View"), edgedLook, Orientation.RIGHT_BELOW,
				AttachLocation.ALIGNED, 40, 20, false);
		viewBalloonTip.setVisible(false);

		UserIntSwing.btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HotkeyHandler.view();
				focusTextfield();
			}
		});
		UserIntSwing.btnView.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				viewBalloonTip.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent arg1) {
				viewBalloonTip.setVisible(false);
			}
		});
	}

	public static void addBtnEditListener() {
		BalloonTip editBalloonTip = new BalloonTip(UserIntSwing.btnEdit, new JLabel(
				"Press F4 to Edit"), edgedLook, Orientation.RIGHT_BELOW,
				AttachLocation.ALIGNED, 40, 20, false);
		editBalloonTip.setVisible(false);

		UserIntSwing.btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HotkeyHandler.edit();
				focusTextfield();
			}
		});
		UserIntSwing.btnEdit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				editBalloonTip.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent arg1) {
				editBalloonTip.setVisible(false);
			}
		});
	}

	public static void addBtnDelListener() {
		BalloonTip delBalloonTip = new BalloonTip(UserIntSwing.btnDel, new JLabel(
				"Press F5 to delete"), edgedLook, Orientation.RIGHT_BELOW,
				AttachLocation.ALIGNED, 40, 20, false);
		delBalloonTip.setVisible(false);

		UserIntSwing.btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HotkeyHandler.delete();
				focusTextfield();
			}
		});
		UserIntSwing.btnDel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				delBalloonTip.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent arg1) {
				delBalloonTip.setVisible(false);
			}
		});
	}

	public static void addBtnSearchListener() {
		BalloonTip searchBalloonTip = new BalloonTip(UserIntSwing.btnSearch, new JLabel(
				"Press F6 to search"), edgedLook, Orientation.RIGHT_BELOW,
				AttachLocation.ALIGNED, 40, 20, false);
		searchBalloonTip.setVisible(false);

		UserIntSwing.btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HotkeyHandler.search();
				focusTextfield();
			}
		});
		UserIntSwing.btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				searchBalloonTip.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent arg1) {
				searchBalloonTip.setVisible(false);
			}
		});
	}
	
	public static void focusTextfield() {
		UserIntSwing.textField.requestFocusInWindow();
	}

	/**
	 * Window State Listener
	 * This operation process the SystemTray when minimise
	 * operation is executed
	 */
	public static void addSystemTrayWindowStateListener() {
		UserIntSwing.frame.addWindowStateListener(new WindowStateListener() {
			public void windowStateChanged(WindowEvent arg) {
				WeDoSystemTray.Minimise(arg);
			}
		});
	}

	/**
	 * Window Focus Listener
	 * This operation puts the focus on the textField for the user to type
	 * immediately when the program runs
	 */
	public static void addFrameWindowFocusListener() {
		UserIntSwing.frame.addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent arg0) {
				//UserIntSwing.textField.setText(null);
				UserIntSwing.textField.requestFocusInWindow();
			}

			public void windowLostFocus(WindowEvent arg0) {
				//UserIntSwing.textField.setText(FeedbackGuide.textfieldFeedback());
			}
		});
	}

	/**
	 * Textfield Action Listener - Process all the text that has
	 * been parsed in the Textfield when Enter is pressed
	 */
	public static void addTextFieldActionListener() {
		UserIntSwing.textField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				UserInterfaceMain.processTextfieldString();
			}
		});
	}

	/**
	 * Button Enter Action Listener - Process all the text has
	 * been pased in the Textfield when Eneter is clicked
	 */
	public static void addBtnEnterActionListener() {
		UserIntSwing.btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserInterfaceMain.processTextfieldString();
			}
		});
	}

	/**
	 *Textfield KeyListener
	 *1. Set the Command guide Label to the indiviual command guide that the user input
	 *2. Process all the HotKeys Functions
	 *3. Process the User Typed History
	 *4. Enter KeyListener - Process all the feedback labels when the user type 
	 *an incorrect input
	 */
	public static void addTextfieldKeyListener() {
		UserIntSwing.textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg1) {
				if(arg1.getKeyCode() == VK.enter()) {
					processEnterkey(arg1);
				}
				try {
					UserInterfaceMain.processHotKeys(arg1);
				} catch (InvalidCommandException e) {
					e.printStackTrace();
				}
			}
			@Override
			public void keyReleased(KeyEvent arg1) {
				userInput = UserIntSwing.textField.getText();
				processTextfield(arg1, userInput);
				DynamicParseResult parseResult = 
						UserInterfaceMain.processUserParse(arg1, UserIntSwing.logicManager);
				Task task = parseResult.getTask();
				UserInterfaceMain.clearDynamicParseLabels();
				handleDynamicEdit(parseResult, task);
				UserInterfaceMain.showParseResult(parseResult, task);
			}

			private void handleDynamicEdit(DynamicParseResult parseResult,
					Task task) {
				if (UserInterfaceMain.containsValidEditCommand(parseResult)) 
				{
					String indexString = UserInterfaceMain.getIndexString(task);
					int index = UserInterfaceMain.getTaskToBeEditedIndex(indexString);
					Task taskToBeEdited = UserIntSwing.logicManager.getTaskToBeEdited(index);
					if(taskToBeEdited != null)
					{
						task.setDescription(StringHandler.removeFirstMatched(
								task.getDescription(), indexString));
						UserInterfaceMain.showTaskToBeEdited(taskToBeEdited);
						UserIntSwing.interForm.selectRow(index);
					}else {
						showInvalidIndexMessage(task);
					}
				}
			}
			private void showInvalidIndexMessage(Task task) {
				final String INVALID_INDEX = "The index you are editing is INVALID";
				task.setDescription(INVALID_INDEX);
			}
			/**
			 *Textfield processes
			 *@param arg1 KeyEvent from the textfield
			 *@param userInput Input that the user entered from the textfield
			 * @throws InvalidCommandException 
			 */
			private void processTextfield(KeyEvent arg1, String userInput) {
				UserIntSwing.lblHelp.setText(CommandGuide.getGuideMessage(userInput));
				TextfieldHistory.showTextfieldHistory(arg1);
			}
			/**
			 *Enter Key Listener process
			 *@param arg1 KeyEvent Enter from the textfield
			 */
			private void processEnterkey(KeyEvent arg1) {
				userInput = UserIntSwing.textField.getText();
				TextfieldHistory.getTextfieldString(userInput);
			}
		});
	}
}
