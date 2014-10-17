//@Andy Hsu Wei Qiang
package ui;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.java.balloontip.BalloonTip;
import userInterface.UserIntSwing;

public class UserLogic {
	//private static final String EXIT_PROGRAM = "exit";
	private static final String DATE_FORMAT = "dd/MM/yyyy";
	private static final int taskbarHeight = 45;
	
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

//	public static void processTextField(KeyEvent arg1) {
//		if (UserIntSwing.textField.getText().length() > 0
//				&& arg1.getKeyCode() == KeyEvent.VK_ENTER) {
//			UserIntSwing.textField.setText("");
//		}
//	}
	
	/**
	 *This operation process the hotkeys shortcut function. 
	 */
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
	
	public static BalloonTip processDisplayTip(){
		BalloonTip myBalloonTip = new BalloonTip(UserIntSwing.btnHelp, "Press F1 for Help");
		
		return myBalloonTip;
	}
}
