package ui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JButton;

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
	
	public static void processHotKeys(){
		UserIntSwing.btnHelp.addKeyListener(new KeyAdapter(){
			@Override
			public void keyReleased(KeyEvent arg){
				if (UserIntSwing.textField.getText().length() == 0
						&& arg.getKeyCode() == KeyEvent.VK_F1) {
					UserIntSwing.textField.setText("-add");
				}
			}
		});
	}
}
