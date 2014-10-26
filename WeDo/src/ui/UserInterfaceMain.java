package ui;

import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import ui.guide.FeedbackGuide;
import ui.logic.command.Action;
import ui.logic.command.Keywords;
import userInterface.UserIntSwing;

/**
 * @author Andy Hsu Wei Qiang 
 * This class handles all the GUI logic
 * which the user execute.
 * 
 */
public class UserInterfaceMain {
	//private static final String EXIT_PROGRAM = "exit";
	private static final String DATE_FORMAT = "dd/MM/yyyy";
	private static final int taskbarHeight = 47;
	private static final String WHITESPACE_PATTERN = "\\s+";
	
	/**
	 *This operation puts the focus on the textField 
	 *for the user to type immediately when the program runs 
	 */
	public static void addFrameWindowFocusListener() {
		UserIntSwing.frame.addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent arg0) {

				UserIntSwing.textField.requestFocusInWindow();
			}
			public void windowLostFocus(WindowEvent arg0) {}
		});
	}
	
	/**
	 *This operation sets the date for today and display
	 *on the top of the application
	 */
	public static String setTodayDate(){
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		String date = sdf.format(new Date());
		String dateDisplay = "You are viewing: " + date;
		
		return dateDisplay;
	}
	
	/**
	 *This operation sets the program at the 
	 *bottom right hand corner of screen
	 */
	public static void setupFrameLocation() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
		Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
		int Xcoordinate = (int) rect.getMaxX() - UserIntSwing.frame.getWidth();
		int Ycoordinate = (int) rect.getMaxY() - UserIntSwing.frame.getHeight() - taskbarHeight;
		UserIntSwing.frame.setLocation(Xcoordinate, Ycoordinate);
	}
	
	/**
	 *This operation process the hotkeys shortcut function. 
	 */
	public static void processHotKeys(KeyEvent arg1){
		String getCommand;
		if(arg1.getKeyCode() == KeyEvent.VK_F1){
			HelpMenu.main(null);
		}
		if (UserIntSwing.textField.getText().length() == 0){
			if(arg1.getKeyCode() == KeyEvent.VK_F2){
				getCommand = Keywords.getAddTaskIdentifier();
				UserIntSwing.textField.setText(getCommand);
			}
			else if(arg1.getKeyCode() == KeyEvent.VK_F3){
				getCommand = Keywords.getViewTaskIdentifier();
				UserIntSwing.textField.setText(getCommand);
			}
			else if(arg1.getKeyCode() == KeyEvent.VK_F4){
				getCommand = Keywords.getEditTaskIdentifier();
				UserIntSwing.textField.setText(getCommand);
			}
			else if(arg1.getKeyCode() == KeyEvent.VK_F5){
				getCommand = Keywords.getDeleteTaskIdentifier();
				UserIntSwing.textField.setText(getCommand);
			}
		}
	}
	
	/**
	 *This operation process the Feedback Label 
	 */
	public static String processFeedbackLabel(String text){
		
		if(text.isEmpty()){
			return FeedbackGuide.isEmptyString();
		}
		
		text = text.toLowerCase();
		String[] tokens = text.split(WHITESPACE_PATTERN);
		Action action = Keywords.resolveActionIdentifier(tokens[0]);

		switch (action) {
		case ADD: case VIEW: case EDIT: case DELETE: case SEARCH:
			return FeedbackGuide.isValidString();
		default:
			return FeedbackGuide.isInvalidString();
		}
	}
	
	/**
	 *This operation process the timer to clear the Warning 
	 *Label. It is set at 1000 milli-seconds. 
	 */
	public static void feedbackTimerReset(){
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			  @Override
			  public void run() {
				  UserIntSwing.lblFeedback.setText("");
			  }
			}, 1000);
	}
}
