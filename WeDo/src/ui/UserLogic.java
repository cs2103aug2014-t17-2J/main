//@Andy Hsu Wei Qiang
package ui;

import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

import userInterface.UserIntSwing;

public class UserLogic {
	//private static final String EXIT_PROGRAM = "exit";
	private static final String DATE_FORMAT = "dd/MM/yyyy";
	
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
	
	public static String setTodayDate(){
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		String date = sdf.format(new Date());
		String dateDisplay = "You are viewing: " + date;
		
		return dateDisplay;
	}
	
//	public static void processTextField(KeyEvent arg1) {
//		if (UserIntSwing.textField.getText().length() > 0
//				&& arg1.getKeyCode() == KeyEvent.VK_ENTER) {
//			UserIntSwing.textField.setText("");
//		}
//	}
	
	public static void processHotKeys(KeyEvent arg1){
		String getCommand;
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
}
