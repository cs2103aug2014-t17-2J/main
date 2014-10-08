package ui;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import userInterface.UserIntSwing;

public class UserInterface {
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

}
