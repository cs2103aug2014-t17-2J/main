//@Andy Hsu Wei Qiang
package ui;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import userInterface.UserIntSwing;

public class UserLogic {
	private static final String EXIT_PROGRAM = "exit";
	
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
	
	public static void processTextField() {

		UserIntSwing.textField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent arg) {
				//UserIntSwing.lblWarning.setText(CommandGuide.getTipMessage(UserIntSwing.textField.getText()));

				if (UserIntSwing.textField.getText().length() > 0
						&& arg.getKeyCode() == KeyEvent.VK_ENTER) {
				}

				UserIntSwing.lblHelp.setText(CommandGuide.getGuideMessage(UserIntSwing.textField.getText()));
			}
		});
	}
	
	public static void processHotKeys(KeyEvent arg1, ActionEvent arg2){
		if (UserIntSwing.textField.getText().length() == 0){
			if(arg1.getKeyCode() == KeyEvent.VK_F2)
				UserIntSwing.textField.setText("-add");
			else if(arg1.getKeyCode() == KeyEvent.VK_F3)
				UserIntSwing.textField.setText("-view");
			else if(arg1.getKeyCode() == KeyEvent.VK_F4)
				UserIntSwing.textField.setText("-edit");
			else if(arg1.getKeyCode() == KeyEvent.VK_F5)
				UserIntSwing.textField.setText("-delete");
		}
	}
}
