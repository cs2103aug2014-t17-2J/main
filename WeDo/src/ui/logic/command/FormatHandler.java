package ui.logic.command;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import userInterface.UserIntSwing;

/**
 // @author  A0112636M 
 *This class format all the Swing design of the application
 */
public class FormatHandler {
	
	public static void formatAll() {
		formatFrameMinSize();
		formatTodayDateLabel();
		formatViewTaskLabel();
		formatFeedbackLabel();
		formatDynamicParserLabel();
		formatAllButton();
		UserIntSwing.lblCommandGuide.setVerticalAlignment(SwingConstants.TOP);
	}
	
	/**
	 * Set the minimum size for the frame
	 */
	private static void formatFrameMinSize() {
		int frameWidth = UserIntSwing.frame.getWidth();
		int frameHeight = UserIntSwing.frame.getHeight();
		UserIntSwing.frame.setMinimumSize(new Dimension(frameWidth, frameHeight));
	}
	
	private static void formatTodayDateLabel() {
		UserIntSwing.lblTodayDate.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 16));
		UserIntSwing.lblTodayDate.setHorizontalAlignment(SwingConstants.RIGHT);
		UserIntSwing.lblTodayDate.setVerticalAlignment(SwingConstants.CENTER);
	}
	
	private static void formatViewTaskLabel() {
		UserIntSwing.lblViewTask.setFont(new Font("Calibri", Font.ITALIC, 18));
		UserIntSwing.lblViewTask.setHorizontalAlignment(SwingConstants.LEFT);
		UserIntSwing.lblViewTask.setVerticalAlignment(SwingConstants.TOP);
	}
	
	/**
	 * Format the Feedback label
	 */
	private static void formatFeedbackLabel() {
        UserIntSwing.lblFeedback.setFont(new Font("Calibri", Font.ITALIC, 14));
        UserIntSwing.lblFeedback.setForeground(Color.red);
        UserIntSwing.lblFeedback.setOpaque(false);
	}
	
	/**
	 * Format the Dynamic Parser Labels
	 */
	private static void formatDynamicParserLabel() {
		UserIntSwing.lblCommandProcess.setFont(new Font("Tahoma", Font.ITALIC,12));
		UserIntSwing.lblCommandProcess.setForeground(new Color(255, 0, 0));
		UserIntSwing.lblCommandProcess.setOpaque(false);
		
		UserIntSwing.lblDateProcess.setFont(new Font("Tahoma", Font.ITALIC, 12));
		UserIntSwing.lblDateProcess.setForeground(new Color(0, 128, 0));
		UserIntSwing.lblDateProcess.setOpaque(false);  
		
		UserIntSwing.lblPriorityProcess.setFont(new Font("Tahoma", Font.ITALIC, 12));
		UserIntSwing.lblPriorityProcess.setHorizontalAlignment(SwingConstants.CENTER);
		UserIntSwing.lblPriorityProcess.setBackground(Color.white);
		UserIntSwing.lblPriorityProcess.setOpaque(true);
		
		UserIntSwing.lblDescriptionProcess.setFont(new Font("Tahoma", Font.ITALIC, 12));
		UserIntSwing.lblDescriptionProcess.setOpaque(false);
	}
	
	/**
	 * Format all the Buttons
	 */
	private static void formatAllButton() {

		UserIntSwing.btnHelp.setContentAreaFilled(false);
		UserIntSwing.btnAdd.setContentAreaFilled(false);
		UserIntSwing.btnView.setContentAreaFilled(false);
		UserIntSwing.btnEdit.setContentAreaFilled(false);
		UserIntSwing.btnDelete.setContentAreaFilled(false);
		UserIntSwing.btnSearch.setContentAreaFilled(false);
		UserIntSwing.btnEnter.setContentAreaFilled(false);
		UserIntSwing.btnClose.setContentAreaFilled(false);
		UserIntSwing.btnClose.setBorderPainted(false);
		UserIntSwing.btnMinimize.setContentAreaFilled(false);
		UserIntSwing.btnMinimize.setBorderPainted(false);
		UserIntSwing.btnSetting.setContentAreaFilled(false);
		UserIntSwing.btnSetting.setBorderPainted(false);
		
//		UserIntSwing.btnAdd.setForeground(new Color(0, 0, 0));
//		UserIntSwing.btnAdd.setBackground(new Color(255, 153, 255));
//		UserIntSwing.btnAdd.setBorderPainted(false);
//		UserIntSwing.btnAdd.setOpaque(false);
//		
//		UserIntSwing.btnView.setForeground(new Color(0, 0, 0));
//		UserIntSwing.btnView.setBackground(new Color(255, 153, 255));
//		UserIntSwing.btnView.setBorderPainted(false);
//		UserIntSwing.btnView.setOpaque(false);
//		
//		UserIntSwing.btnEdit.setForeground(new Color(0, 0, 0));
//		UserIntSwing.btnEdit.setBackground(new Color(255, 153, 255));
//		UserIntSwing.btnEdit.setBorderPainted(false);
//		UserIntSwing.btnEdit.setOpaque(false);
//		
//		UserIntSwing.btnDelete.setForeground(new Color(0, 0, 0));
//		UserIntSwing.btnDelete.setBackground(new Color(255, 153, 255));
//		UserIntSwing.btnDelete.setBorderPainted(false);
//		UserIntSwing.btnDelete.setOpaque(false);
//		
//		UserIntSwing.btnSearch.setForeground(new Color(0, 0, 0));
//		UserIntSwing.btnSearch.setBackground(new Color(255, 153, 255));
//		UserIntSwing.btnSearch.setBorderPainted(false);
//		UserIntSwing.btnSearch.setOpaque(false);
		
//		UserIntSwing.btnEnter.setBackground(new Color(255, 153, 255));
//		UserIntSwing.btnEnter.setBorderPainted(false);
//		UserIntSwing.btnEnter.setOpaque(false);
		
//		UserIntSwing.btnClose.setBackground(new Color(255, 153, 255));
//		UserIntSwing.btnClose.setOpaque(false);
//		UserIntSwing.btnClose.setBorderPainted(false);
//		
//		UserIntSwing.btnMinimize.setBackground(new Color(255, 153, 255));
//		UserIntSwing.btnMinimize.setOpaque(false);
//		UserIntSwing.btnMinimize.setBorderPainted(false);
//		
//		UserIntSwing.btnSetting.setBackground(new Color(255, 153, 255));
//		UserIntSwing.btnSetting.setOpaque(false);
//		UserIntSwing.btnSetting.setBorderPainted(false);
	}
}
