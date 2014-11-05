package ui.logic.command;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.SwingConstants;

import userInterface.UserIntSwing;

/**
 * @author Andy Hsu Wei Qiang
 *This class format all the Swing design of the application
 */
public class FormatHandler {
	
	public static void formatAll() {
		formatFrameMinSize();
		formatTodayDateLabel();
		formatViewTaskLabel();
		formatFeedbackLabel();
		formatCommandGuideLabel();
		formatDynamicParserLabel();
		formatAllButton();
		UserIntSwing.lblHelp.setVerticalAlignment(SwingConstants.TOP);
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
	 * Format the Command Guide Label
	 */
	private static void formatCommandGuideLabel() {
		UserIntSwing.lblQuickHelp.setFont(new Font("Times New Roman", Font.BOLD
				| Font.ITALIC, 14));
	}
	
	/**
	 * Format the Dynamic Parser Labels
	 */
	private static void formatDynamicParserLabel() {
		UserIntSwing.lblCommand.setFont(new Font("Tahoma", Font.BOLD, 12));
		UserIntSwing.lblCommandProcess.setFont(new Font("Tahoma", Font.ITALIC,11));
		UserIntSwing.lblCommandProcess.setForeground(new Color(255, 0, 0));
		UserIntSwing.lblCommandProcess.setBackground(new Color(255, 204, 255));
		UserIntSwing.lblCommandProcess.setOpaque(true);
		
		UserIntSwing.lblDate.setFont(new Font("Tahoma", Font.BOLD, 12));
		UserIntSwing.lblDateProcess.setFont(new Font("Tahoma", Font.ITALIC, 11));
		UserIntSwing.lblDateProcess.setForeground(new Color(0, 128, 0));
		UserIntSwing.lblDateProcess.setBackground(new Color(255, 204, 255));
		UserIntSwing.lblDateProcess.setOpaque(true);
		
		UserIntSwing.lblPriority.setFont(new Font("Tahoma", Font.BOLD, 12));
		UserIntSwing.lblPriorityProcess.setFont(new Font("Tahoma", Font.ITALIC, 11));
		UserIntSwing.lblPriorityProcess.setBackground(new Color(255, 204, 255));
		UserIntSwing.lblPriorityProcess.setHorizontalAlignment(SwingConstants.CENTER);
		UserIntSwing.lblPriorityProcess.setOpaque(true);
		
		UserIntSwing.lblDescription.setFont(new Font("Tahoma", Font.BOLD, 12));
		UserIntSwing.lblDescriptionProcess.setFont(new Font("Tahoma", Font.ITALIC, 11));
		UserIntSwing.lblDescriptionProcess.setBackground(new Color(255, 204, 255));
		UserIntSwing.lblDescriptionProcess.setOpaque(true);
	}
	
	/**
	 * Format all the Buttons
	 */
	private static void formatAllButton() {
		UserIntSwing.btnHelp.setForeground(new Color(0, 0, 0));
		UserIntSwing.btnHelp.setBackground(new Color(255, 153, 255));
		
		UserIntSwing.btnAdd.setForeground(new Color(0, 0, 0));
		UserIntSwing.btnAdd.setBackground(new Color(255, 153, 255));
		
		UserIntSwing.btnView.setForeground(new Color(0, 0, 0));
		UserIntSwing.btnView.setBackground(new Color(255, 153, 255));
		
		UserIntSwing.btnEdit.setForeground(new Color(0, 0, 0));
		UserIntSwing.btnEdit.setBackground(new Color(255, 153, 255));
		
		UserIntSwing.btnDel.setForeground(new Color(0, 0, 0));
		UserIntSwing.btnDel.setBackground(new Color(255, 153, 255));
		
		UserIntSwing.btnSearch.setForeground(new Color(0, 0, 0));
		UserIntSwing.btnSearch.setBackground(new Color(255, 153, 255));
		
		UserIntSwing.btnEnter.setBackground(new Color(255, 153, 255));
	}
}
