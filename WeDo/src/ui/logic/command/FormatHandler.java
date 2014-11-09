package ui.logic.command;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import userInterface.UserIntSwing;

/**
 // @author A0112636M 
 *This class format all the Swing design of the application
 */
public class FormatHandler {
	
	public static void formatAll() {
		setFrameBackground();
		formatFrameMinSize();
		formatTodayDateLabel();
		formatViewTaskLabel();
		formatCommandGuide();
		formatFeedbackLabel();
		formatDynamicParserLabel();
		formatAllButton();
	}
	
	/**
	 * Set he Frame Background image. Image is found in ui/icon/WeDo_v2.png
	 */
	private static void setFrameBackground() {
		UserIntSwing.lblBackground.setIcon(new ImageIcon(UserIntSwing.class.getResource("/ui/icon/Wedo_v2.png")));
		UserIntSwing.lblBackground.setBounds(0, 0, 767, 550);
		UserIntSwing.frame.getContentPane().add(UserIntSwing.lblBackground);
	}
	
	/**
	 * Set the minimum size for the frame
	 */
	private static void formatFrameMinSize() {
		int frameWidth = UserIntSwing.frame.getWidth();
		int frameHeight = UserIntSwing.frame.getHeight();
		UserIntSwing.frame.setMinimumSize(new Dimension(frameWidth, frameHeight));
	}
	
	/**
	 * Format the label for today's date
	 */
	private static void formatTodayDateLabel() {
		UserIntSwing.lblTodayDate.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 16));
		UserIntSwing.lblTodayDate.setHorizontalAlignment(SwingConstants.RIGHT);
		UserIntSwing.lblTodayDate.setVerticalAlignment(SwingConstants.CENTER);
		UserIntSwing.lblTodayDate.setBounds(502, 44, 256, 26);
	}
	
	/**
	 * Format the label of the task user is currently viewing
	 */
	private static void formatViewTaskLabel() {
		UserIntSwing.lblViewTask.setFont(new Font("Calibri", Font.ITALIC, 18));
		UserIntSwing.lblViewTask.setHorizontalAlignment(SwingConstants.LEFT);
		UserIntSwing.lblViewTask.setVerticalAlignment(SwingConstants.TOP);
		UserIntSwing.lblViewTask.setBounds(80, 44, 413, 25);
	}
	
	/**
	 * Format the Feedback label
	 */
	private static void formatFeedbackLabel() {
        UserIntSwing.lblFeedback.setFont(new Font("Calibri", Font.ITALIC, 14));
        UserIntSwing.lblFeedback.setForeground(Color.red);
        UserIntSwing.lblFeedback.setOpaque(false);
		UserIntSwing.lblFeedback.setBounds(10, 379, 639, 25);
	}
	
	/**
	 * Format the Command Guide label
	 */
	private static void formatCommandGuide() {
		UserIntSwing.lblCommandGuide.setVerticalAlignment(SwingConstants.TOP);
		UserIntSwing.lblCommandGuide.setBounds(10, 468, 639, 71);
	}
	
	/**
	 * Format the Dynamic Parser Labels
	 */
	private static void formatDynamicParserLabel() {
		UserIntSwing.lblCommandProcess.setFont(new Font("Tahoma", Font.ITALIC,12));
		UserIntSwing.lblCommandProcess.setForeground(new Color(255, 0, 0));
		UserIntSwing.lblCommandProcess.setOpaque(false);
		UserIntSwing.lblCommandProcess.setHorizontalAlignment(SwingConstants.CENTER);
		UserIntSwing.lblCommandProcess.setBounds(80, 359, 46, 14);
		
		UserIntSwing.lblDateProcess.setFont(new Font("Tahoma", Font.ITALIC, 12));
		UserIntSwing.lblDateProcess.setForeground(new Color(0, 128, 0));
		UserIntSwing.lblDateProcess.setOpaque(false);  
		UserIntSwing.lblDateProcess.setBounds(176, 359, 132, 14);
		
		UserIntSwing.lblPriorityProcess.setFont(new Font("Tahoma", Font.ITALIC, 12));
		UserIntSwing.lblPriorityProcess.setOpaque(false);
		UserIntSwing.lblPriorityProcess.setHorizontalAlignment(SwingConstants.CENTER);
		UserIntSwing.lblPriorityProcess.setBounds(366, 359, 53, 14);
		
		UserIntSwing.lblDescriptionProcess.setFont(new Font("Tahoma", Font.ITALIC, 12));
		UserIntSwing.lblDescriptionProcess.setOpaque(false);
		UserIntSwing.lblDescriptionProcess.setBounds(501, 359, 256, 14);
		
		UserIntSwing.textField.setFont(new Font("Calibri", Font.PLAIN, 18));
		UserIntSwing.textField.setBounds(10, 408, 639, 26);
		UserIntSwing.textField.setColumns(10);
	}
	
	/**
	 * Format all the Buttons
	 */
	private static void formatAllButton() {
		UserIntSwing.btnHelp.setContentAreaFilled(false);
		UserIntSwing.btnHelp.setBorderPainted(false);
		UserIntSwing.btnHelp.setBounds(15, 80, 111, 27);

		UserIntSwing.btnAdd.setContentAreaFilled(false);
		UserIntSwing.btnAdd.setBorderPainted(false);
		UserIntSwing.btnAdd.setBounds(133, 78, 118, 30);
		
		UserIntSwing.btnView.setContentAreaFilled(false);
		UserIntSwing.btnView.setBorderPainted(false);
		UserIntSwing.btnView.setBounds(254, 78, 118, 32);
		
		UserIntSwing.btnEdit.setContentAreaFilled(false);
		UserIntSwing.btnEdit.setBorderPainted(false);
		UserIntSwing.btnEdit.setBounds(375, 78, 118, 30);
	
		UserIntSwing.btnDelete.setContentAreaFilled(false);
		UserIntSwing.btnDelete.setBorderPainted(false);
		UserIntSwing.btnDelete.setBounds(503, 75, 127, 32);
		
		UserIntSwing.btnSearch.setContentAreaFilled(false);
		UserIntSwing.btnSearch.setBorderPainted(false);
		UserIntSwing.btnSearch.setBounds(635, 75, 122, 32);
		
		UserIntSwing.btnEnter.setContentAreaFilled(false);
		UserIntSwing.btnEnter.setBorderPainted(false);
		UserIntSwing.btnEnter.setBounds(656, 408, 105, 30);
		
		UserIntSwing.btnClose.setContentAreaFilled(false);
		UserIntSwing.btnClose.setBorderPainted(false);
		UserIntSwing.btnClose.setBounds(728, 12, 33, 30);
			
		UserIntSwing.btnMinimize.setContentAreaFilled(false);
		UserIntSwing.btnMinimize.setBorderPainted(false);
		UserIntSwing.btnMinimize.setBounds(693, 12, 33, 30);
		
		UserIntSwing.btnSetting.setContentAreaFilled(false);
		UserIntSwing.btnSetting.setBorderPainted(false);
		UserIntSwing.btnSetting.setBounds(656, 11, 33, 30);
	
	}
	
	/**
	 * Add All components from contentPane to frame
	 */
	public static void addtoContentPane() {
		UserIntSwing.frame.getContentPane().add(UserIntSwing.btnHelp);
		UserIntSwing.frame.getContentPane().add(UserIntSwing.btnAdd);
		UserIntSwing.frame.getContentPane().add(UserIntSwing.btnView);
		UserIntSwing.frame.getContentPane().add(UserIntSwing.btnEdit);
		UserIntSwing.frame.getContentPane().add(UserIntSwing.btnDelete);
		UserIntSwing.frame.getContentPane().add(UserIntSwing.btnSearch);
		UserIntSwing.frame.getContentPane().add(UserIntSwing.btnEnter);
		UserIntSwing.frame.getContentPane().add(UserIntSwing.btnClose);
		UserIntSwing.frame.getContentPane().add(UserIntSwing.btnMinimize);
		UserIntSwing.frame.getContentPane().add(UserIntSwing.btnSetting);
		
		UserIntSwing.frame.getContentPane().add(UserIntSwing.lblViewTask);
		UserIntSwing.frame.getContentPane().add(UserIntSwing.lblTodayDate);
		UserIntSwing.frame.getContentPane().add(UserIntSwing.lblFeedback);
		UserIntSwing.frame.getContentPane().add(UserIntSwing.lblCommandGuide);
		UserIntSwing.frame.getContentPane().add(UserIntSwing.lblCommandProcess);
		UserIntSwing.frame.getContentPane().add(UserIntSwing.lblDateProcess);
		UserIntSwing.frame.getContentPane().add(UserIntSwing.lblPriorityProcess);
		UserIntSwing.frame.getContentPane().add(UserIntSwing.lblDescriptionProcess);
		
		UserIntSwing.frame.getContentPane().add(UserIntSwing.textField);
	}
}
